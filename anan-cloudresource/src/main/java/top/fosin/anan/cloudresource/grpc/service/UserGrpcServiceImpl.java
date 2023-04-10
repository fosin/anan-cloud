package top.fosin.anan.cloudresource.grpc.service;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;
import top.fosin.anan.cloudresource.constant.ServiceConstant;
import top.fosin.anan.cloudresource.grpc.service.inter.UserGrpcService;
import top.fosin.anan.cloudresource.grpc.user.*;

import java.util.List;


@Component
public class UserGrpcServiceImpl implements UserGrpcService {
    @GrpcClient(ServiceConstant.ANAN_PLATFORMSERVER)
    private UserServiceGrpc.UserServiceBlockingStub blockingStubService;

    @Override
    public UserResp findOneById(Long id) {
        UserIdReq build = UserIdReq.newBuilder().setId(id).build();
        return blockingStubService.findOneById(build);
    }

    @Override
    public UserResp findOneByUsercode(String usercode) {
        UsercodeReq build = UsercodeReq.newBuilder().setUsercode(usercode).build();
        return blockingStubService.findOneByUsercode(build);
    }

    @Override
    public List<UserResp> listByIds(List<Long> ids) {
        UserIdsReq build = UserIdsReq.newBuilder().addAllId(ids).build();
        return blockingStubService.listByIds(build).getUserList();
    }

    @Override
    public List<UserResp> listByOrganizId(Long organizId, Integer status) {
        OrganizReq build = OrganizReq.newBuilder().setOrganizId(organizId).setStatus(status).build();
        return blockingStubService.listByOrganizId(build).getUserList();
    }

    @Override
    public List<UserResp> listAllChildByTopId(Long topId, Integer status) {
        TopOrganizReq build = TopOrganizReq.newBuilder().setTopId(topId).setStatus(status).build();
        return blockingStubService.listAllChildByTopId(build).getUserList();
    }
}
