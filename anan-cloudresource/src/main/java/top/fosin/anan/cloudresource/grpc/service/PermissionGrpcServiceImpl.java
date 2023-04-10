package top.fosin.anan.cloudresource.grpc.service;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;
import top.fosin.anan.cloudresource.constant.ServiceConstant;
import top.fosin.anan.cloudresource.grpc.permission.PermissionResp;
import top.fosin.anan.cloudresource.grpc.permission.PermissionServiceGrpc;
import top.fosin.anan.cloudresource.grpc.permission.ServiceCodeReq;
import top.fosin.anan.cloudresource.grpc.permission.ServiceCodesReq;
import top.fosin.anan.cloudresource.grpc.service.inter.PermissionGrpcService;

import java.util.List;

/**
 * 权限远程调用封装入口服务
 *
 * @author fosin
 * @date 2023/4/8
 * @since 3.0.0
 */
@Component
public class PermissionGrpcServiceImpl implements PermissionGrpcService {
    @GrpcClient(ServiceConstant.ANAN_PLATFORMSERVER)
    private PermissionServiceGrpc.PermissionServiceBlockingStub blockingStubService;

    @Override
    public List<PermissionResp> findByServiceCode(String serviceCode) {
        ServiceCodeReq build = ServiceCodeReq.newBuilder().setServiceCode(serviceCode).build();
        return blockingStubService.findByServiceCode(build).getPermissionList();
    }

    @Override
    public List<PermissionResp> findByServiceCodes(List<String> serviceCodes) {
        ServiceCodesReq build = ServiceCodesReq.newBuilder().addAllServiceCodes(serviceCodes).build();
        return blockingStubService.findByServiceCodes(build).getPermissionList();
    }
}
