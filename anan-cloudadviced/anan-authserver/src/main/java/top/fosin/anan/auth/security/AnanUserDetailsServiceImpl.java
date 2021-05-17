package top.fosin.anan.auth.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.fosin.anan.auth.entity.AnanUserEntity;
import top.fosin.anan.auth.service.inter.AuthService;
import top.fosin.anan.cloudresource.dto.AnanUserDetail;
import top.fosin.anan.cloudresource.dto.res.AnanUserAllPermissionsRespDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 2017/12/27.
 * Time:16:37
 *
 * @author fosin
 */
@Service
@Lazy
@Slf4j
@Primary
public class AnanUserDetailsServiceImpl implements UserDetailsService {
    private final AuthService authService;

    public AnanUserDetailsServiceImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        //这里的username对应anan_user.usercode
        AnanUserEntity userEntity = authService.findByUsercode(username);

        if (userEntity == null) {
            throw new UsernameNotFoundException("用户:" + username + "不存在!");
        }

        List<AnanUserAllPermissionsRespDto> permissionEntities = authService.findByUserId(userEntity.getId());
        // 只操作状态为启用的权限，获取用户增权限
        Set<GrantedAuthority> grantedAuthoritySet = permissionEntities.stream().filter(entity -> entity.getStatus() == 0 && entity.getAddMode() == 0)
                .map(entity -> new SimpleGrantedAuthority(entity.getId() + ""))
                .collect(Collectors.toSet());
        // 只操作状态为启用的权限，移除用户减权限
        grantedAuthoritySet.removeAll(permissionEntities.stream().filter(entity -> entity.getStatus() == 0 && entity.getAddMode() == 1)
                .map(entity -> new SimpleGrantedAuthority(entity.getId() + ""))
                .collect(Collectors.toSet()));

        AnanUserDetail user = new AnanUserDetail(userEntity.conert2Dto(), grantedAuthoritySet);
        log.debug("UserDetailsServiceImpl User:" + user);
        return user;
    }
}
