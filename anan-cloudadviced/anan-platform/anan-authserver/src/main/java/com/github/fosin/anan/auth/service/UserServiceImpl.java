package com.github.fosin.anan.auth.service;

import com.github.fosin.anan.auth.service.inter.UserService;
import com.github.fosin.anan.platformapi.constant.TableNameConstant;
import com.github.fosin.anan.platformapi.entity.AnanUserEntity;
import com.github.fosin.anan.platformapi.entity.AnanUserRoleEntity;
import com.github.fosin.anan.platformapi.repository.UserRepository;
import com.github.fosin.anan.pojo.dto.AnanUserDto;
import com.github.fosin.anan.pojo.dto.request.AnanUserRoleRetrieveDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * 2017/12/27.
 * Time:15:13
 *
 * @author fosin
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Cacheable(value = TableNameConstant.ANAN_USER, key = "#usercode")
    @Transactional(readOnly = true)
    public AnanUserEntity findByUsercode(String usercode) {
        Assert.notNull(usercode, "用户工号不能为空!");
        //该代码看似无用，其实是为了解决懒加载和缓存先后问题
        AnanUserEntity userEntity = userRepository.findByUsercode(usercode);
        if (userEntity != null) {
            List<AnanUserRoleEntity> userRoles = userEntity.getUserRoles();
            log.debug(userRoles.toString());
        }
        return userEntity;
    }

    @Override
    public AnanUserDto copyUserData(AnanUserEntity userEntity) {
        AnanUserDto userDto = new AnanUserDto();
        userDto.setId(userEntity.getId());
        userDto.setAvatar(userEntity.getAvatar());
        userDto.setPassword(userEntity.getPassword());
        userDto.setBirthday(userEntity.getBirthday());
        userDto.setEmail(userEntity.getEmail());
        userDto.setExpireTime(userEntity.getExpireTime());
        userDto.setOrganizId(userEntity.getOrganizId());
        userDto.setPhone(userEntity.getPhone());
        userDto.setSex(userEntity.getSex());
        userDto.setStatus(userEntity.getStatus());
        userDto.setUsercode(userEntity.getUsercode());
        userDto.setUsername(userEntity.getUsername());

        List<AnanUserRoleEntity> userRoles = userEntity.getUserRoles();
        List<AnanUserRoleRetrieveDto> userRoles2 = new ArrayList<>();
        if (userRoles != null && userRoles.size() > 0) {
            userRoles.forEach(role -> {
                AnanUserRoleRetrieveDto role2 = new AnanUserRoleRetrieveDto();
                role2.setId(role.getId());
                role2.setCreateBy(role.getCreateBy());
                role2.setCreateTime(role.getCreateTime());
                role2.setOrganizId(role.getOrganizId());
                role2.setRoleId(role.getRole().getId());
                role2.setUserId(role.getUserId());
                userRoles2.add(role2);
            });
        }
        userDto.setUserRoles(userRoles2);
        return userDto;
    }
}
