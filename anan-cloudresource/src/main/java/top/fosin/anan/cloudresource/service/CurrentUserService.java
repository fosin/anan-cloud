package top.fosin.anan.cloudresource.service;

import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.Assert;
import top.fosin.anan.cloudresource.config.DefaultClaimNames;
import top.fosin.anan.cloudresource.constant.SystemConstant;
import top.fosin.anan.cloudresource.entity.SecurityUser;
import top.fosin.anan.data.aware.OrganizAware;
import top.fosin.anan.data.aware.UserAware;
import top.fosin.anan.security.resource.AnanSecurityProperties;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author fosin
 * @date 2018.7.23
 */
public class CurrentUserService
        implements AuditorAware<Long>, UserAware<Long>, OrganizAware<Long> {

    private final String authorityClaimName;

    public CurrentUserService(AnanSecurityProperties ananSecurityProperties) {
        AnanSecurityProperties.Oauth2ResourceServer resourceServer = ananSecurityProperties.getOauth2().getResourceServer();
        this.authorityClaimName = resourceServer.getAuthorityClaimName();
    }

    /**
     * 当前用户是否有登录态
     *
     * @return true：是，false：否
     */
    public boolean hasAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication() != null;
    }

    /**
     * 当前操作者是否是超管用户
     *
     * @return boolean true：是 false：否
     */
    public boolean isSysAdminUser() {
        Object principal = getPrincipal();
        String usercode;
        if (principal instanceof SecurityUser) {
            usercode = ((SecurityUser) principal).getUser().getUsercode();
        } else if (principal instanceof Jwt) {
            usercode = ((Jwt) principal).getClaim(DefaultClaimNames.SUB);
        } else {
            throw new AuthenticationServiceException("认证信息有误，未找到认证用户对象，请检查！");
        }
        return isSysAdminUser(usercode);
    }

    /**
     * 当前操作者是否是超管用户
     *
     * @return boolean true：是 false：否
     */
    public boolean isSysAdminUser(String usercode) {
        return SystemConstant.ANAN_USER_CODE.equals(usercode);
    }

    /**
     * 当前操作者是否是管理员用户
     *
     * @return boolean true：是 false：否
     */
    public boolean isAdminUser(String usercode) {
        throw new UnsupportedOperationException("目前不支持！");
    }

    public Collection<String> getAuthorities() {
        Object principal = getPrincipal();
        Collection<String> authorities;
        if (principal instanceof SecurityUser) {
            authorities = ((SecurityUser) principal).getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        } else if (principal instanceof Jwt) {
            authorities = ((Jwt) principal).getClaim(authorityClaimName);
        } else {
            throw new AuthenticationServiceException("认证信息有误，未找到认证用户对象，请检查！");
        }
        return authorities;
    }

    private Object getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Assert.notNull(authentication, "未找到当前用户的认证登录态!");
        return authentication.getPrincipal();
    }

    /**
     * 当前用户是否是拥有管理员角色
     *
     * @return boolean true：是 false：否
     */
    public boolean hasAdminRole() {
        return this.getAuthorities().stream().anyMatch(authority -> authority.equals(SystemConstant.ADMIN_ROLE_NAME));
    }

    /**
     * 当前用户是否是拥有超级管理员角色
     *
     * @return boolean true：是 false：否
     */
    public boolean hasSysAdminRole() {
        return this.getAuthorities().stream().anyMatch(authority -> authority.equals(SystemConstant.ANAN_ROLE_NAME));
    }

    @Override
    @NonNull
    public Optional<Long> getCurrentAuditor() {
        return Optional.of(getUserId());
    }

    @Override
    public Long getOrganizId() {
        Object principal = getPrincipal();
        Long organizId;
        if (principal instanceof SecurityUser) {
            organizId = ((SecurityUser) principal).getUser().getOrganizId();
        } else if (principal instanceof Jwt) {
            organizId = ((Jwt) principal).getClaim(DefaultClaimNames.ORGANIZ_ID);
        } else {
            throw new AuthenticationServiceException("认证信息有误，未找到认证用户对象，请检查！");
        }
        return organizId;
    }

    @Override
    public Long getTopId() {
        Object principal = getPrincipal();
        Long topId;
        if (principal instanceof SecurityUser) {
            topId = ((SecurityUser) principal).getUser().getTopId();
        } else if (principal instanceof Jwt) {
            topId = ((Jwt) principal).getClaim(DefaultClaimNames.TOP_ID);
        } else {
            throw new AuthenticationServiceException("认证信息有误，未找到认证用户对象，请检查！");
        }
        return topId;
    }

    @Override
    public Long getUserId() {
        Object principal = getPrincipal();
        Long id;
        if (principal instanceof SecurityUser) {
            id = ((SecurityUser) principal).getUser().getId();
        } else if (principal instanceof Jwt) {
            id = ((Jwt) principal).getClaim(DefaultClaimNames.ID);
        } else {
            throw new AuthenticationServiceException("认证信息有误，未找到认证用户对象，请检查！");
        }
        return id;
    }
}
