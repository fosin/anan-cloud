package top.fosin.anan.platform.modules.organization.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.cloudresource.dto.res.OrgRespDto;
import top.fosin.anan.cloudresource.dto.res.OrgTreeDto;
import top.fosin.anan.cloudresource.service.AnanUserDetailService;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.jpa.repository.IJpaRepository;
import top.fosin.anan.platform.modules.organization.dao.OrgDao;
import top.fosin.anan.platform.modules.organization.dto.OrgReqDto;
import top.fosin.anan.platform.modules.organization.entity.Organization;
import top.fosin.anan.platform.modules.organization.service.inter.OrgEntryService;
import top.fosin.anan.platform.modules.organization.service.inter.OrgService;
import top.fosin.anan.redis.cache.AnanCacheManger;

import java.util.Collection;
import java.util.List;

/**
 * @author fosin
 * @date 2017/12/29
 */
@Service
@Lazy
@AllArgsConstructor
public class OrgServiceImpl implements OrgService {
    private final OrgDao orgDao;
    private final AnanUserDetailService ananUserDetailService;
    private final AnanCacheManger ananCacheManger;
    private final OrgEntryService orgEntryService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = PlatformRedisConstant.ANAN_ORGANIZATION, key = "#result.id")
    public OrgRespDto create(OrgReqDto reqDto) {
        Organization createEntity = new Organization();
        BeanUtils.copyProperties(reqDto, createEntity);
        Long pid = reqDto.getPid();
        int level = 1;
        if (pid == 0) {
            ananUserDetailService.clear();
            Assert.isTrue(ananUserDetailService.hasSysAdminRole(), "只有超级管理员才能创建顶级机构!");
            createEntity.setTopId(0L);
        } else {
            Organization parentEntity = orgDao.findById(pid).orElse(null);
            Assert.notNull(parentEntity, "传入的创建数据实体找不到对于的父节点数据!");
            level = parentEntity.getLevel() + 1;
        }
        createEntity.setLevel(level);
        Organization result = orgDao.save(createEntity);
        if (pid == 0) {
            result.setTopId(result.getId());
            result = orgDao.save(result);
        }
        return BeanUtil.copyProperties(result, OrgRespDto.class);
    }

    @Override
    @CacheEvict(value = PlatformRedisConstant.ANAN_ORGANIZATION, key = "#updateDto.id")
    @Transactional(rollbackFor = Exception.class)
    public void update(OrgReqDto updateDto) {
        Long id = updateDto.getId();
        Assert.notNull(id, "无效的字典代码code");
        Organization updateEntity = orgDao.findById(id).orElseThrow(() -> new IllegalArgumentException("根据传入的机构序号" + id + "在数据库中未能找到对于数据!"));
        BeanUtils.copyProperties(updateDto, updateEntity);
        Long pid = updateDto.getPid();
        if (!updateEntity.getPid().equals(pid)) {
            orgDao.findById(pid).ifPresent(sentity -> updateEntity.setLevel(sentity.getLevel() + 1));
        }
        orgDao.save(updateEntity);
    }

    @Override
    @Cacheable(value = PlatformRedisConstant.ANAN_ORGANIZATION, key = "#id")
    public OrgRespDto findOneById(Long id) {
        return OrgService.super.findOneById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = PlatformRedisConstant.ANAN_ORGANIZATION, key = "#id")
    public void deleteById(Long id) {
        List<OrgTreeDto> dtos = orgEntryService.listChild(id);
        Assert.isTrue(dtos == null || dtos.size() == 0, "该节点还存在子节点不能直接删除!");
        orgDao.deleteById(id);
    }

    /**
     * 根据主键删除多条数据
     *
     * @param ids 主键编号集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(Collection<Long> ids) {
        List<Organization> entities = orgDao.findAllById(ids);
        for (Organization entity : entities) {
            Long id = entity.getId();
            List<OrgTreeDto> dtos = orgEntryService.listChild(id);
            Assert.isTrue(dtos == null || dtos.size() == 0, "该节点还存在子节点不能直接删除!");
            orgDao.delete(entity);
        }
        for (Organization entity : entities) {
            ananCacheManger.evict(PlatformRedisConstant.ANAN_ORGANIZATION, entity.getId() + "");
        }
    }

    @Override
    public IJpaRepository<Organization, Long> getDao() {
        return orgDao;
    }
}
