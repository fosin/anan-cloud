package top.fosin.anan.auth.modules.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.fosin.anan.auth.modules.auth.dao.UserRoleDao;
import top.fosin.anan.auth.modules.auth.po.UserRole;
import top.fosin.anan.auth.modules.auth.service.inter.AuthService;
import top.fosin.anan.cloudresource.entity.SecurityUser;
import top.fosin.anan.cloudresource.entity.res.UserAllPermissionsRespDTO;
import top.fosin.anan.cloudresource.entity.res.UserAuthDTO;
import top.fosin.anan.cloudresource.entity.res.UserDTO;
import top.fosin.anan.cloudresource.service.inter.rpc.UserRpcService;
import top.fosin.anan.core.util.BeanUtil;
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
    private final UserRpcService userRpcService;
    private final UserRoleDao userRoleDao;
    private final String authorityPrefix;

    public UserDetailsServiceImpl(AuthService authService, UserRpcService userRpcService, UserRoleDao userRoleDao, AnanSecurityProperties ananSecurityProperties) {
        this.authService = authService;
        this.userRpcService = userRpcService;
        this.userRoleDao = userRoleDao;
        this.authorityPrefix = ananSecurityProperties.getOauth2().getResourceServer().getAuthorityPrefix();
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        //这里的username对应anan_user.usercode
        UserDTO userDTO = userRpcService.findOneByUsercode(username);
        if (userDTO == null) {
            throw new BadCredentialsException("无效的用户或密码！");
        }

        //增加菜单权限
        List<UserAllPermissionsRespDTO> dtos = authService.listPermissionsByUserId(userDTO.getId());
        // 只操作状态为启用的权限，获取用户增权限
        Set<GrantedAuthority> grantedAuthorities = dtos.stream().filter(entity -> entity.getStatus() == 0 && entity.getAddMode() == 0)
                .map(entity -> new SimpleGrantedAuthority(authorityPrefix + entity.getId()))
                .collect(Collectors.toSet());
        // 只操作状态为启用的权限，移除用户减权限
        grantedAuthorities.removeAll(dtos.stream().filter(entity -> entity.getStatus() == 0 && entity.getAddMode() == 1)
                .map(entity -> new SimpleGrantedAuthority(authorityPrefix + entity.getId()))
                .collect(Collectors.toSet()));

        //增加角色权限
        List<UserRole> userRoles = userRoleDao.findByUserId(userDTO.getId());
        userRoles.forEach(userRole -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole().getValue().toUpperCase()));
        });

        UserAuthDTO userAuthDTO = BeanUtil.copyProperties(userDTO, UserAuthDTO.class);
        SecurityUser user = new SecurityUser(userAuthDTO, grantedAuthorities);
        log.debug("登陆用户信息：" + user);
        return user;
    }
}
