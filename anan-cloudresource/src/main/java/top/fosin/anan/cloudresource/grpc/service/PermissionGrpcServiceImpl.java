package top.fosin.anan.cloudresource.grpc.service;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;
import top.fosin.anan.cloudresource.constant.ServiceConstant;
import top.fosin.anan.cloudresource.entity.res.PermissionDTO;
import top.fosin.anan.cloudresource.grpc.permission.PermissionResp;
import top.fosin.anan.cloudresource.grpc.permission.PermissionServiceGrpc;
import top.fosin.anan.cloudresource.grpc.permission.ServiceCodeReq;
import top.fosin.anan.cloudresource.grpc.permission.ServiceCodesReq;
import top.fosin.anan.cloudresource.service.inter.rpc.PermissionRpcService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限远程调用封装入口服务
 *
 * @author fosin
 * @date 2023/4/8
 * @since 3.0.0
 */
@Component
public class PermissionGrpcServiceImpl implements PermissionRpcService {
    @GrpcClient(ServiceConstant.ANAN_PLATFORMSERVER)
    private PermissionServiceGrpc.PermissionServiceBlockingStub blockingStubService;

    @Override
    public List<PermissionDTO> findByServiceCode(String serviceCode) {
        ServiceCodeReq build = ServiceCodeReq.newBuilder().setServiceCode(serviceCode).build();
        List<PermissionResp> permissionResps = blockingStubService.findByServiceCode(build).getPermissionList();
        return permissionResps.stream().map(this::toRespDto).collect(Collectors.toList());
    }

    @Override
    public List<PermissionDTO> findByServiceCodes(List<String> serviceCodes) {
        ServiceCodesReq build = ServiceCodesReq.newBuilder().addAllServiceCodes(serviceCodes).build();
        List<PermissionResp> permissionResps = blockingStubService.findByServiceCodes(build).getPermissionList();
        return permissionResps.stream().map(this::toRespDto).collect(Collectors.toList());
    }

    private PermissionDTO toRespDto(PermissionResp resp) {
        PermissionDTO dto = new PermissionDTO();
        dto.setId(resp.getId());
        dto.setPid(resp.getPid());
        dto.setCode(resp.getCode());
        dto.setName(resp.getName());
        dto.setUrl(resp.getUrl());
        dto.setRoutePath(resp.getRoutePath());
        dto.setType((byte) resp.getType());
        dto.setLevel(resp.getLevel());
        dto.setSort(resp.getSort());
        dto.setStatus((byte) resp.getStatus());
        dto.setServiceId(resp.getServiceId());
        dto.setServiceCode(resp.getServiceCode());
        dto.setPath(resp.getPath());
        dto.setMethod(resp.getMethod());
        dto.setIcon(resp.getIcon());
        return dto;
    }
}
