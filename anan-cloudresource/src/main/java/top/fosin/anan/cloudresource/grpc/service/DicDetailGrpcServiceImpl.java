package top.fosin.anan.cloudresource.grpc.service;

import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import top.fosin.anan.cloudresource.constant.ServiceConstant;
import top.fosin.anan.cloudresource.entity.res.DictionaryDetailRespDTO;
import top.fosin.anan.cloudresource.grpc.dicdetail.*;
import top.fosin.anan.cloudresource.service.inter.rpc.DicDetailRpcService;
import top.fosin.anan.data.converter.translate.service.Object2StringTranslateService;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Component
@Slf4j
public class DicDetailGrpcServiceImpl implements DicDetailRpcService, Object2StringTranslateService {
    @GrpcClient(ServiceConstant.ANAN_PLATFORMSERVER)
    private DicDetailServiceGrpc.DicDetailServiceBlockingStub blockingStubService;

    @NotNull
    private DictionaryDetailRespDTO toRespDto(DicDetailResp dicDetailResp) {
        DictionaryDetailRespDTO dto = new DictionaryDetailRespDTO();
        dto.setId(dicDetailResp.getId());
        dto.setDictionaryId(dicDetailResp.getDictionaryId());
        dto.setCode(dicDetailResp.getCode());
        dto.setName(dicDetailResp.getName());
        dto.setSort((short) dicDetailResp.getSort());
        dto.setStatus((byte) dicDetailResp.getStatus());
        dto.setScode(dicDetailResp.getScode());
        dto.setScope(dicDetailResp.getScope());
        dto.setUsed((byte) dicDetailResp.getUsed());
        dto.setDescription(dicDetailResp.getDescription());
        dto.setCreateBy(dicDetailResp.getCreateBy());
        dto.setCreateTime(new Date(dicDetailResp.getCreateTime().getSeconds() * 1000));
        dto.setUpdateBy(dicDetailResp.getUpdateBy());
        dto.setUpdateTime(new Date(dicDetailResp.getUpdateTime().getSeconds() * 1000));
        return dto;
    }

    @Override
    public String translate(String dicId, Object key) {
        Long id;
        if (key instanceof Long) {
            id = (Long) key;
        } else if (key instanceof Integer) {
            id = Long.valueOf((Integer) key);
        } else if (key instanceof String) {
            id = Long.valueOf((String) key);
        } else if (key instanceof Byte) {
            id = Long.valueOf((Byte) key);
        } else {
            id = -9999L;
            log.warn("翻译数据失败，不被支持的转换值类型：" + key + " 字典序号：" + dicId);
        }
        Assert.isTrue(StringUtils.hasText(dicId) && Long.parseLong(dicId) > 0, "dicId必须大于0!");
        String value = String.valueOf(key);
        if (id != -9999L) {
            DictionaryDetailRespDTO respDTO = this.listByDicId(Long.parseLong(dicId)).stream()
                    .filter(detailVO -> Objects.equals(detailVO.getCode(), id)).findFirst().orElse(null);
            if (respDTO == null) {
                log.warn("翻译数据失败，根据值类型：" + key + " 字典序号：" + dicId + "未能找到对应数据!");
            } else {
                value = respDTO.getName();
            }
        } else {
            log.warn("翻译数据失败，值类型必须大于0：" + key + " 字典序号：" + dicId);
        }
        return value;
    }

    @Override
    public DictionaryDetailRespDTO findOneByDicIdAndCode(Long dicId, Long code) {
        DicDetailReq build = DicDetailReq.newBuilder().setDictionaryId(dicId).setCode(code).build();
        DicDetailResp dicDetailResp = blockingStubService.findOneByDicIdAndCode(build);
        return toRespDto(dicDetailResp);
    }

    @Override
    public List<DictionaryDetailRespDTO> listByDicId(Long dicId) {
        DicIdReq build = DicIdReq.newBuilder().setDictionaryId(dicId).build();
        DicDetailsResp dicDetailsResp = blockingStubService.listByDicId(build);
        return dicDetailsResp.getDicDetailList().stream().map(this::toRespDto).collect(Collectors.toList());
    }
}
