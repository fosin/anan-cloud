package top.fosin.anan.platform.modules.dictionary.service;

import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.cloudresource.constant.SystemConstant;
import top.fosin.anan.cloudresource.entity.req.DictionaryDetailReqDTO;
import top.fosin.anan.cloudresource.entity.res.DictionaryDetailRespDTO;
import top.fosin.anan.cloudresource.grpc.dicdetail.*;
import top.fosin.anan.cloudresource.grpc.service.DicDetailGrpcServiceImpl;
import top.fosin.anan.cloudresource.grpc.util.StringUtil;
import top.fosin.anan.cloudresource.service.CurrentUserService;
import top.fosin.anan.core.exception.AnanServiceException;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.data.converter.translate.StringTranslateCacheUtil;
import top.fosin.anan.platform.modules.dictionary.dao.DictionaryDao;
import top.fosin.anan.platform.modules.dictionary.dao.DictionaryDetailDao;
import top.fosin.anan.platform.modules.dictionary.po.Dictionary;
import top.fosin.anan.platform.modules.dictionary.service.inter.DictionaryDetailService;
import top.fosin.anan.redis.cache.AnanCacheManger;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 字典表服务
 *
 * @author fosin
 * @date 2018-7-29
 */
@GrpcService
@Lazy
@AllArgsConstructor
public class DictionaryDetailServiceImpl extends DicDetailServiceGrpc.DicDetailServiceImplBase
        implements DictionaryDetailService {
    private final DictionaryDetailDao dictionaryDetailDao;
    private final CurrentUserService currentUserService;
    private final DictionaryDao dictionaryDao;
    private final AnanCacheManger ananCacheManger;

    @Override
    public void findOneByDicIdAndCode(DicDetailReq request, StreamObserver<DicDetailResp> responseObserver) {
        long id = request.getDictionaryId();
        long code = request.getCode();
        DictionaryDetailRespDTO vo = this.listByForeingKey(id).stream().filter(detailVO -> detailVO.getCode() == code).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("未找到对应的数据"));
        DicDetailResp dicDetailResp = toGrpcResp(vo);
        responseObserver.onNext(dicDetailResp);
        responseObserver.onCompleted();
    }

    @Override
    public void listByDicId(DicIdReq request, StreamObserver<DicDetailsResp> responseObserver) {
        long id = request.getDictionaryId();
        DicDetailsResp vos = DicDetailsResp.newBuilder().addAllDicDetail(this.listByForeingKey(id)
                .stream().map(this::toGrpcResp).collect(Collectors.toList())).build();
        responseObserver.onNext(vos);
        responseObserver.onCompleted();
    }

    @NotNull
    private DicDetailResp toGrpcResp(DictionaryDetailRespDTO vo) {
        return DicDetailResp.newBuilder()
                .setId(vo.getId())
                .setDictionaryId(vo.getDictionaryId())
                .setCode(vo.getCode())
                .setName(vo.getName())
                .setSort(vo.getSort())
                .setStatus(vo.getStatus())
                .setScode(StringUtil.getNonNullValue(vo.getScode()))
                .setScope(StringUtil.getNonNullValue(vo.getScope()))
                .setUsed(vo.getUsed())
                .setDescription(StringUtil.getNonNullValue(vo.getDescription()))
                .setCreateBy(vo.getCreateBy())
                .setCreateTime(Timestamp.newBuilder().setSeconds(vo.getCreateTime().getTime() / 1000).build())
                .setUpdateBy(vo.getUpdateBy())
                .setUpdateTime(Timestamp.newBuilder().setSeconds(vo.getUpdateTime().getTime() / 1000).build())
                .build();
    }


    @Override
    public void preCreate(DictionaryDetailReqDTO reqDTO) {
        DictionaryDetailService.super.preCreate(reqDTO);
        hasModifiedPrivileges(reqDTO.getDictionaryId());
        ananCacheManger.evict(PlatformRedisConstant.ANAN_DICTIONARY_DETAIL, reqDTO.getDictionaryId() + "");
    }

    @Override
    public void preUpdate(DictionaryDetailReqDTO reqDTO) {
        DictionaryDetailService.super.preUpdate(reqDTO);
        hasModifiedPrivileges(reqDTO.getDictionaryId());
        ananCacheManger.evict(PlatformRedisConstant.ANAN_DICTIONARY_DETAIL, reqDTO.getDictionaryId() + "");
    }

    @Override
    public void postUpdate(DictionaryDetailReqDTO reqDTO) {
        DictionaryDetailService.super.postUpdate(reqDTO);
        ananCacheManger.evict(PlatformRedisConstant.ANAN_DICTIONARY_DETAIL, reqDTO.getDictionaryId() + "");
        StringTranslateCacheUtil.put(DicDetailGrpcServiceImpl.class, String.valueOf(reqDTO.getDictionaryId()), reqDTO.getCode(), reqDTO.getName());
    }

    private void hasModifiedPrivileges(Long dictionaryId) {
        Dictionary dictionaryEntity = dictionaryDao.findById(dictionaryId).orElse(new Dictionary());
        if (SystemConstant.SYSTEM_DICTIONARY_TYPE.equals(dictionaryEntity.getType())) {
            //非超级管理员不能修改系统字典
            Assert.isTrue(currentUserService.hasSysAdminRole(), "没有权限增删改系统字典!");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        dictionaryDetailDao.findById(id).ifPresent(entity -> {
            hasModifiedPrivileges(entity.getDictionaryId());
            dictionaryDetailDao.deleteById(id);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new AnanServiceException(e);
            }
            ananCacheManger.evict(PlatformRedisConstant.ANAN_DICTIONARY_DETAIL, entity.getDictionaryId() + "");
        });
    }

    /**
     * 根据主键删除多条数据
     *
     * @param ids 主键编号集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = PlatformRedisConstant.ANAN_DICTIONARY_DETAIL, allEntries = true)
    public void deleteByIds(Collection<Long> ids) {
        DictionaryDetailService.super.deleteByIds(ids);
    }

    @Override
    public List<DictionaryDetailRespDTO> listByForeingKey(Long dictionaryId) {
        List<DictionaryDetailRespDTO> list = ananCacheManger.get(PlatformRedisConstant.ANAN_DICTIONARY_DETAIL, dictionaryId + "", List.class);
        if (list == null) {
            Sort sort = Sort.by(Sort.Direction.fromString("ASC"), "sort");
            list = BeanUtil.copyProperties(dictionaryDetailDao.findAllByDictionaryId(dictionaryId, sort), DictionaryDetailRespDTO.class);
            ananCacheManger.put(PlatformRedisConstant.ANAN_DICTIONARY_DETAIL, dictionaryId + "", list);
        }
        return list;
    }

    /**
     * 根据主键集合批量更新一个字段
     *
     * @param name  更新的字段名
     * @param value 更新的值
     * @param ids   主键集合
     * @return 更新的数量
     */
    @Override
    public long updateOneField(String name, Serializable value, Collection<Long> ids) {
        long count = DictionaryDetailService.super.updateOneField(name, value, ids);
        ids.forEach(id -> ananCacheManger.evict(PlatformRedisConstant.ANAN_DICTIONARY_DETAIL, id + ""));
        return count;
    }

    @Override
    public DictionaryDetailDao getDao() {
        return dictionaryDetailDao;
    }

}
