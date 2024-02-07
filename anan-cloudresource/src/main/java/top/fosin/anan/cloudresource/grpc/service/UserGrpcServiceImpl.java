package top.fosin.anan.cloudresource.grpc.service;

import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.cloudresource.constant.ServiceConstant;
import top.fosin.anan.cloudresource.entity.res.UserDTO;
import top.fosin.anan.cloudresource.grpc.user.*;
import top.fosin.anan.cloudresource.service.inter.rpc.UserRpcService;
import top.fosin.anan.data.converter.translate.service.Object2StringTranslateService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Component
@Slf4j
public class UserGrpcServiceImpl implements UserRpcService, Object2StringTranslateService {
    @GrpcClient(ServiceConstant.ANAN_PLATFORMSERVER)
    private UserServiceGrpc.UserServiceBlockingStub blockingStubService;

    @Override
    @Cacheable(value = PlatformRedisConstant.ANAN_USER, key = "#id", unless = "#result == null")
    public UserDTO findOneById(Long id) {
        UserIdReq build = UserIdReq.newBuilder().setId(id).build();
        UserResp userResp = blockingStubService.findOneById(build);
        return toRespDto(userResp);
    }

    @Override
    @Cacheable(value = PlatformRedisConstant.ANAN_USER_USERCODE, key = "#usercode", unless = "#result == null")
    public UserDTO findOneByUsercode(String usercode) {
        UsercodeReq build = UsercodeReq.newBuilder().setUsercode(usercode).build();
        UserResp userResp = blockingStubService.findOneByUsercode(build);
        return toRespDto(userResp);
    }

    @Override
    public List<UserDTO> listByIds(List<Long> ids) {
        UserIdsReq build = UserIdsReq.newBuilder().addAllId(ids).build();
        List<UserResp> userResps = blockingStubService.listByIds(build).getUserList();
        return userResps.stream().map(this::toRespDto).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> listByOrganizId(Long organizId, Byte status) {
        OrganizReq build = OrganizReq.newBuilder().setOrganizId(organizId).setStatus(status).build();
        List<UserResp> userResps = blockingStubService.listByOrganizId(build).getUserList();
        return userResps.stream().map(this::toRespDto).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> listAllChildByTopId(Long topId, Byte status) {
        TopOrganizReq build = TopOrganizReq.newBuilder().setTopId(topId).setStatus(status).build();
        List<UserResp> userResps = blockingStubService.listAllChildByTopId(build).getUserList();
        return userResps.stream().map(this::toRespDto).collect(Collectors.toList());
    }

    private UserDTO toRespDto(UserResp user) {
        UserDTO dto = new UserDTO();
        dto.setUsercode(user.getUsercode());
        dto.setUsername(user.getUsername());
        dto.setFamilyName(user.getFamilyName());
        dto.setMiddleName(user.getMiddleName());
        dto.setGivenName(user.getGivenName());
        dto.setNickname(user.getNickname());
        dto.setPreferredUsername(user.getPreferredUsername());
        dto.setRealNameVerified((byte) user.getRealNameVerified());
        dto.setOrganizId(user.getOrganizId());
        dto.setTopId(user.getTopId());
        dto.setBirthday(new Date(user.getBirthday().getSeconds() * 1000));
        dto.setSex((byte) user.getSex());
        dto.setEmail(user.getEmail());
        dto.setEmailVerified((byte) user.getEmailVerified());
        dto.setPhone(user.getPhone());
        dto.setPhoneVerified((byte) user.getPhoneVerified());
        dto.setStatus((byte) user.getStatus());
        dto.setAvatar(user.getAvatar());
        dto.setWebsite(user.getWebsite());
        dto.setExpireTime(new Date(user.getExpireTime().getSeconds() * 1000));
        dto.setId(user.getId());
        dto.setPassword(user.getPassword());
        dto.setCreateBy(user.getCreateBy());
        dto.setCreateTime(new Date(user.getCreateTime().getSeconds() * 1000));
        dto.setUpdateBy(user.getUpdateBy());
        dto.setUpdateTime(new Date(user.getUpdateTime().getSeconds() * 1000));
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
            UserDTO respDTO = this.findOneById(id);
            if (respDTO == null) {
                log.warn("翻译数据失败，根据值类型：" + key + "未能找到对应数据!");
            } else {
                value = respDTO.getUsername();
            }
        } else {
            log.warn("翻译数据失败，值类型必须大于0：" + key);
        }
        return value;
    }
}
