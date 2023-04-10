package top.fosin.anan.cloudresource.grpc.service;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;
import top.fosin.anan.cloudresource.constant.ServiceConstant;
import top.fosin.anan.cloudresource.grpc.organiz.*;
import top.fosin.anan.cloudresource.grpc.service.inter.OrganizGrpcService;

import java.util.List;


@Component
public class OrganizGrpcServiceImpl implements OrganizGrpcService {
    @GrpcClient(ServiceConstant.ANAN_PLATFORMSERVER)
    private OrganizServiceGrpc.OrganizServiceBlockingStub blockingStubService;

    @Override
    public OrganizResp findOneById(Long id) {
        OrganizIdReq build = OrganizIdReq.newBuilder().setId(id).build();
        return blockingStubService.findOneById(build);
    }

    @Override
    public List<OrganizResp> listByIds(List<Long> ids) {
        OrganizIdsReq build = OrganizIdsReq.newBuilder().addAllId(ids).build();
        return blockingStubService.listByIds(build).getOrganizList();
    }

    @Override
    public List<OrganizResp> listChild(Long pid) {
        OrganizPidReq build = OrganizPidReq.newBuilder().setPid(pid).build();
        return blockingStubService.listChild(build).getOrganizList();
    }

    @Override
    public List<OrganizResp> listAllChild(Long pid) {
        OrganizPidReq build = OrganizPidReq.newBuilder().setPid(pid).build();
        return blockingStubService.listAllChild(build).getOrganizList();
    }

}
