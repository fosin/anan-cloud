package top.fosin.anan.cloudresource.grpc.service;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import top.fosin.anan.cloudresource.constant.ServiceConstant;
import top.fosin.anan.cloudresource.entity.res.UserRespDTO;
import top.fosin.anan.cloudresource.service.inter.rpc.UserRpcService;
import top.fosin.anan.cloudresource.grpc.user.*;
import top.fosin.anan.data.converter.translate.service.Long2StringTranslateService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class UserGrpcServiceImpl implements UserRpcService, Long2StringTranslateService {
    @GrpcClient(ServiceConstant.ANAN_PLATFORMSERVER)
    private UserServiceGrpc.UserServiceBlockingStub blockingStubService;

    @Override
    public UserRespDTO findOneById(Long id) {
        UserIdReq build = UserIdReq.newBuilder().setId(id).build();
        UserResp userResp = blockingStubService.findOneById(build);
        return toRespDto(userResp);
    }

    @Override
    public UserRespDTO findOneByUsercode(String usercode) {
        UsercodeReq build = UsercodeReq.newBuilder().setUsercode(usercode).build();
        UserResp userResp = blockingStubService.findOneByUsercode(build);
        return toRespDto(userResp);
    }

    @Override
    public List<UserRespDTO> listByIds(List<Long> ids) {
        UserIdsReq build = UserIdsReq.newBuilder().addAllId(ids).build();
        List<UserResp> userResps = blockingStubService.listByIds(build).getUserList();
        return userResps.stream().map(this::toRespDto).collect(Collectors.toList());
    }

    @Override
    public List<UserRespDTO> listByOrganizId(Long organizId, Integer status) {
        OrganizReq build = OrganizReq.newBuilder().setOrganizId(organizId).setStatus(status).build();
        List<UserResp> userResps = blockingStubService.listByOrganizId(build).getUserList();
        return userResps.stream().map(this::toRespDto).collect(Collectors.toList());
    }

    @Override
    public List<UserRespDTO> listAllChildByTopId(Long topId, Integer status) {
        TopOrganizReq build = TopOrganizReq.newBuilder().setTopId(topId).setStatus(status).build();
        List<UserResp> userResps = blockingStubService.listAllChildByTopId(build).getUserList();
        return userResps.stream().map(this::toRespDto).collect(Collectors.toList());
    }

    @NotNull
    private UserRespDTO toRespDto(UserResp user) {
        UserRespDTO dto = new UserRespDTO();
        dto.setUsercode(user.getUsercode());
        dto.setUsername(user.getUsername());
        dto.setFamilyName(user.getFamilyName());
        dto.setMiddleName(user.getMiddleName());
        dto.setGivenName(user.getGivenName());
        dto.setNickname(user.getNickname());
        dto.setPreferredUsername(user.getPreferredUsername());
        dto.setRealNameVerified(user.getRealNameVerified());
        dto.setOrganizId(user.getOrganizId());
        dto.setTopId(user.getTopId());
        dto.setBirthday(new Date(user.getBirthday().getSeconds() * 1000));
        dto.setSex(user.getSex());
        dto.setEmail(user.getEmail());
        dto.setEmailVerified(user.getEmailVerified());
        dto.setPhone(user.getPhone());
        dto.setPhoneVerified(user.getPhoneVerified());
        dto.setStatus(user.getStatus());
        dto.setAvatar(user.getAvatar());
        dto.setWebsite(user.getWebsite());
        dto.setExpireTime(new Date(user.getExpireTime().getSeconds() * 1000));
        dto.setId(user.getId());
        dto.setCreateBy(user.getCreateBy());
        dto.setCreateTime(new Date(user.getCreateTime().getSeconds() * 1000));
        dto.setUpdateBy(user.getUpdateBy());
        dto.setUpdateTime(new Date(user.getUpdateTime().getSeconds() * 1000));
        return dto;
    }

    @Override
    public String translate(String dicId, Long key) {
        return this.findOneById(key).getUsername();
    }
}
