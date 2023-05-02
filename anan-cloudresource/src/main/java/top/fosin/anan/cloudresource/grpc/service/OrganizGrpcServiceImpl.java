package top.fosin.anan.cloudresource.grpc.service;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import top.fosin.anan.cloudresource.constant.ServiceConstant;
import top.fosin.anan.cloudresource.entity.res.OrganizRespDTO;
import top.fosin.anan.cloudresource.grpc.organiz.*;
import top.fosin.anan.cloudresource.service.inter.rpc.OrganizRpcService;
import top.fosin.anan.data.converter.translate.service.Long2StringTranslateService;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class OrganizGrpcServiceImpl implements OrganizRpcService, Long2StringTranslateService {
    @GrpcClient(ServiceConstant.ANAN_PLATFORMSERVER)
    private OrganizServiceGrpc.OrganizServiceBlockingStub blockingStubService;

    @Override
    public OrganizRespDTO findOneById(Long id) {
        OrganizIdReq build = OrganizIdReq.newBuilder().setId(id).build();
        OrganizResp organizResp = blockingStubService.findOneById(build);
        return toRespDto(organizResp);
    }

    @Override
    public List<OrganizRespDTO> listByIds(List<Long> ids) {
        OrganizIdsReq build = OrganizIdsReq.newBuilder().addAllId(ids).build();
        List<OrganizResp> organizResps = blockingStubService.listByIds(build).getOrganizList();
        return organizResps.stream().map(this::toRespDto).collect(Collectors.toList());
    }

    @Override
    public List<OrganizRespDTO> listChild(Long pid) {
        OrganizPidReq build = OrganizPidReq.newBuilder().setPid(pid).build();
        List<OrganizResp> organizResps = blockingStubService.listChild(build).getOrganizList();
        return organizResps.stream().map(this::toRespDto).collect(Collectors.toList());
    }

    @Override
    public List<OrganizRespDTO> listAllChild(Long pid) {
        OrganizPidReq build = OrganizPidReq.newBuilder().setPid(pid).build();
        List<OrganizResp> organizResps = blockingStubService.listAllChild(build).getOrganizList();
        return organizResps.stream().map(this::toRespDto).collect(Collectors.toList());
    }

    @NotNull
    private OrganizRespDTO toRespDto(OrganizResp organizResp) {
        OrganizRespDTO dto = new OrganizRespDTO();
        dto.setCode(organizResp.getCode());
        dto.setName(organizResp.getName());
        dto.setAddress(organizResp.getAddress());
        dto.setPid(organizResp.getPid());
        dto.setStatus(organizResp.getStatus());
        dto.setFullname(organizResp.getFullname());
        dto.setId(organizResp.getId());
        dto.setLevel(organizResp.getLevel());
        dto.setTelphone(organizResp.getTelphone());
        dto.setTopId(organizResp.getTopId());
        return dto;
    }

    @Override
    public String translate(String dicId, Long key) {
        return this.findOneById(key).getName();
    }
}
