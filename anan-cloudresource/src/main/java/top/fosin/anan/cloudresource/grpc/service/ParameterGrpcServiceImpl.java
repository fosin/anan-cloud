package top.fosin.anan.cloudresource.grpc.service;

import com.google.protobuf.StringValue;
import com.google.protobuf.Timestamp;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import top.fosin.anan.cloudresource.constant.ServiceConstant;
import top.fosin.anan.cloudresource.dto.req.ParameterReqDto;
import top.fosin.anan.cloudresource.dto.res.ParameterRespDto;
import top.fosin.anan.cloudresource.grpc.parameter.*;
import top.fosin.anan.cloudresource.service.inter.PermissionRpcService;

import java.util.Date;
import java.util.List;


@Component
public class ParameterGrpcServiceImpl implements PermissionRpcService {
    @GrpcClient(ServiceConstant.ANAN_PLATFORMSERVER)
    private ParameterServiceGrpc.ParameterServiceBlockingStub blockingStubService;

    @Override
    public void processUpdate(ParameterReqDto reqDto) {
        ParameterReq build = ParameterReq.newBuilder()
                .setStatus(reqDto.getStatus())
                .setApplyTime(Timestamp.newBuilder().setSeconds(reqDto.getApplyTime().getTime()))
                .setDescription(reqDto.getDescription())
                .setValue(reqDto.getValue())
                .setDefaultValue(reqDto.getDefaultValue())
                .setName(reqDto.getName())
                .setType(reqDto.getType())
                .setScope(reqDto.getScope())
                .build();
        blockingStubService.processUpdate(build);
    }

    @Override
    public String getOrCreateParameter(int type, String scope, String name, String defaultValue, String description) {
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
    public ParameterRespDto getParameter(Integer type, String scope, String name) {
        ParameterThreeArgsReq build = ParameterThreeArgsReq.newBuilder()
                .setName(name).setScope(scope).setType(type).build();
        ParameterResp parameterResp = blockingStubService.getParameter(build);
        return getParameterRespDto(parameterResp);
    }

    @NotNull
    private ParameterRespDto getParameterRespDto(ParameterResp parameterResp) {
        ParameterRespDto respDto = new ParameterRespDto();
        respDto.setId(parameterResp.getId());
        respDto.setName(parameterResp.getName());
        respDto.setType(parameterResp.getType());
        respDto.setScope(parameterResp.getScope());
        respDto.setValue(parameterResp.getValue());
        respDto.setDefaultValue(parameterResp.getDefaultValue());
        respDto.setDefaultValue(parameterResp.getDefaultValue());
        respDto.setStatus(parameterResp.getStatus());
        respDto.setApplyTime(new Date(parameterResp.getApplyTime().getSeconds() * 1000));
        return respDto;
    }

    @Override
    public ParameterRespDto getNearestParameter(int type, String scope, String name) {
        ParameterThreeArgsReq build = ParameterThreeArgsReq.newBuilder()
                .setName(name).setScope(scope).setType(type).build();
        ParameterResp parameterResp = blockingStubService.getNearestParameter(build);
        return getParameterRespDto(parameterResp);
    }

}
