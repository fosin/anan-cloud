package top.fosin.anan.cloudresource.grpc.service;

import com.google.protobuf.StringValue;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.cloudresource.constant.ServiceConstant;
import top.fosin.anan.cloudresource.entity.req.ParameterUpdateDTO;
import top.fosin.anan.cloudresource.entity.res.ParameterDTO;
import top.fosin.anan.cloudresource.grpc.parameter.*;
import top.fosin.anan.cloudresource.service.inter.rpc.ParameterRpcService;

import java.util.Date;
import java.util.List;


@Component
public class ParameterGrpcServiceImpl implements ParameterRpcService {
    @GrpcClient(ServiceConstant.ANAN_PLATFORMSERVER)
    private ParameterServiceGrpc.ParameterServiceBlockingStub blockingStubService;

    @Override
    public void processUpdate(ParameterUpdateDTO reqDto) {
        ParameterReq build = ParameterReq.newBuilder()
                .setName(reqDto.getName())
                .setValue(reqDto.getValue())
                .setType(reqDto.getType())
                .setScope(reqDto.getScope())
                .setDefaultValue(reqDto.getDefaultValue())
                .setDescription(reqDto.getDescription())
                .build();
        blockingStubService.processUpdate(build);
    }

    @Override
    @Cacheable(value = PlatformRedisConstant.ANAN_PARAMETER, key = "#root.target.getCacheKey(#type,#scope,#name)")
    public String getOrCreateParameter(Byte type, String scope, String name, String defaultValue, String description) {
        getOrCreateReq build = getOrCreateReq.newBuilder()
                .setDescription(description)
                .setDefaultValue(defaultValue)
                .setName(name)
                .setType(type)
                .setScope(scope)
                .build();
        StringValue parameterResp = blockingStubService.getOrCreateParameter(build);
        return parameterResp.getValue();
    }

    @Override
    public Boolean applyChange(Long id) {
        ParameterIdReq build = ParameterIdReq.newBuilder().setId(id).build();
        return blockingStubService.applyChange(build).getValue();
    }

    @Override
    public Boolean applyChanges(List<Long> ids) {
        ParameterIdsReq build = ParameterIdsReq.newBuilder().addAllId(ids).build();
        return blockingStubService.applyChanges(build).getValue();
    }

    @Override
    public Boolean applyChangeAll() {
        ParameterIdReq build = ParameterIdReq.newBuilder().build();
        return blockingStubService.applyChange(build).getValue();
    }

    @Override
    public void cancelDelete(List<Long> ids) {
        ParameterIdsReq build = ParameterIdsReq.newBuilder().addAllId(ids).build();
        blockingStubService.cancelDelete(build);
    }

    @Override
    @Cacheable(value = PlatformRedisConstant.ANAN_PARAMETER, key = "#root.target.getCacheKey(#type,#scope,#name)")
    public ParameterDTO getParameter(Byte type, String scope, String name) {
        ParameterThreeArgsReq build = ParameterThreeArgsReq.newBuilder()
                .setName(name).setScope(scope).setType(type).build();
        ParameterResp parameterResp = blockingStubService.getParameter(build);
        return getParameterRespDto(parameterResp);
    }

    private ParameterDTO getParameterRespDto(ParameterResp parameterResp) {
        ParameterDTO respDto = new ParameterDTO();
        respDto.setId(parameterResp.getId());
        respDto.setName(parameterResp.getName());
        respDto.setType((byte) parameterResp.getType());
        respDto.setScope(parameterResp.getScope());
        respDto.setValue(parameterResp.getValue());
        respDto.setDefaultValue(parameterResp.getDefaultValue());
        respDto.setDefaultValue(parameterResp.getDefaultValue());
        respDto.setStatus((byte) parameterResp.getStatus());
        respDto.setApplyTime(new Date(parameterResp.getApplyTime().getSeconds() * 1000));
        return respDto;
    }

    @Override
    @Cacheable(value = PlatformRedisConstant.ANAN_PARAMETER, key = "#root.target.getCacheKey(#type,#scope,#name)")
    public ParameterDTO getNearestParameter(Byte type, String scope, String name) {
        ParameterThreeArgsReq build = ParameterThreeArgsReq.newBuilder()
                .setName(name).setScope(scope).setType(type).build();
        ParameterResp parameterResp = blockingStubService.getNearestParameter(build);
        return getParameterRespDto(parameterResp);
    }

}
