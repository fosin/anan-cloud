package top.fosin.anan.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.fosin.anan.auth.service.inter.AuthService;
import top.fosin.anan.cloudresource.entity.res.UserAuthDto;
import top.fosin.anan.cloudresource.entity.UserDetail;
import top.fosin.anan.cloudresource.entity.res.UserAllPermissionsRespDTO;
import top.fosin.anan.security.resource.AnanSecurityProperties;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author fosin
 * @date 2017/12/27
 */
@Service
@Lazy
@Slf4j
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AuthService authService;
    private final String authorityPrefix;

    public UserDetailsServiceImpl(AuthService authService, AnanSecurityProperties ananSecurityProperties) {
        this.authService = authService;
        this.authorityPrefix = ananSecurityProperties.getOauth2().getResourceServer().getAuthorityPrefix();
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        //这里的username对应anan_user.usercode
        UserAuthDto userAuthDto = authService.findByUsercode(username);

        if (userAuthDto == null) {
            throw new UsernameNotFoundException("用户:" + username + "不存在!");
        }

        List<UserAllPermissionsRespDTO> dtos = authService.findByUserId(userAuthDto.getId());
        // 只操作状态为启用的权限，获取用户增权限
        Set<GrantedAuthority> grantedAuthorities = dtos.stream().filter(entity -> entity.getStatus() == 0 && entity.getAddMode() == 0)
                .map(entity -> new SimpleGrantedAuthority(authorityPrefix + entity.getId()))
                .collect(Collectors.toSet());
        // 只操作状态为启用的权限，移除用户减权限
        grantedAuthorities.removeAll(dtos.stream().filter(entity -> entity.getStatus() == 0 && entity.getAddMode() == 1)
                .map(entity -> new SimpleGrantedAuthority(authorityPrefix + entity.getId()))
                .collect(Collectors.toSet()));

        UserDetail user = new UserDetail(userAuthDto, grantedAuthorities);
        log.debug("UserDetailsServiceImpl loadUserByUsername:" + user);
        return user;
    }
}
