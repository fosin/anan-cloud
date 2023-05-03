package top.fosin.anan.cloudresource.grpc.service;

import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import top.fosin.anan.cloudresource.constant.ServiceConstant;
import top.fosin.anan.cloudresource.entity.res.OrganizRespDTO;
import top.fosin.anan.cloudresource.grpc.organiz.*;
import top.fosin.anan.cloudresource.service.inter.rpc.OrganizRpcService;
import top.fosin.anan.data.converter.translate.service.Object2StringTranslateService;

import java.util.List;
import java.util.stream.Collectors;


@Component
@Slf4j
public class OrganizGrpcServiceImpl implements OrganizRpcService, Object2StringTranslateService {
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
    public String translate(String dicId, Object key) {
        long id = 0;
        if (key instanceof Long) {
            id = (Long) key;
        } else if (key instanceof String) {
            id = Long.parseLong((String) key);
        } else {
            log.warn("翻译数据失败，不被支持的转换值类型：" + key);
        }
        String value = String.valueOf(key);
        if (id > 0) {
            OrganizRespDTO respDTO = this.findOneById(id);
            if (respDTO == null) {
                log.warn("翻译数据失败，根据值类型：" + key + "未能找到对应数据!");
            } else {
                value = respDTO.getFullname();
            }
        } else {
            log.warn("翻译数据失败，值类型必须大于0：" + key);
        }
        return value;
    }
}
