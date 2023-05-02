package top.fosin.anan.cloudresource.grpc.service;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import top.fosin.anan.cloudresource.constant.ServiceConstant;
import top.fosin.anan.cloudresource.entity.res.DictionaryDetailRespDTO;
import top.fosin.anan.cloudresource.grpc.dicdetail.*;
import top.fosin.anan.cloudresource.service.inter.rpc.DicDetailRpcService;
import top.fosin.anan.data.converter.translate.service.Long2StringTranslateService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class DicDetailGrpcServiceImpl implements DicDetailRpcService, Long2StringTranslateService {
    @GrpcClient(ServiceConstant.ANAN_PLATFORMSERVER)
    private DicDetailServiceGrpc.DicDetailServiceBlockingStub blockingStubService;

    @NotNull
    private DictionaryDetailRespDTO toRespDto(DicDetailResp dicDetailResp) {
        DictionaryDetailRespDTO dto = new DictionaryDetailRespDTO();
        dto.setId(dicDetailResp.getId());
        dto.setDictionaryId(dicDetailResp.getDictionaryId());
        dto.setCode(dicDetailResp.getCode());
        dto.setName(dicDetailResp.getName());
        dto.setSort(dicDetailResp.getSort());
        dto.setStatus(dicDetailResp.getStatus());
        dto.setScode(dicDetailResp.getScode());
        dto.setScope(dicDetailResp.getScope());
        dto.setUsed(dicDetailResp.getUsed());
        dto.setDescription(dicDetailResp.getDescription());
        dto.setCreateBy(dicDetailResp.getCreateBy());
        dto.setCreateTime(new Date(dicDetailResp.getCreateTime().getSeconds() * 1000));
        dto.setUpdateBy(dicDetailResp.getUpdateBy());
        dto.setUpdateTime(new Date(dicDetailResp.getUpdateTime().getSeconds() * 1000));
        return dto;
    }

    @Override
    public String translate(String dicId, Long key) {
        DictionaryDetailRespDTO dicDetailResp = this.findOneByDicIdAndCode(Long.valueOf(dicId), key);
        return dicDetailResp.getName();
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
