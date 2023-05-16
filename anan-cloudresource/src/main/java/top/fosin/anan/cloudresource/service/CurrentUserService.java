package top.fosin.anan.cloudresource.service;

import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import top.fosin.anan.cloudresource.constant.SystemConstant;
import top.fosin.anan.cloudresource.entity.Client;
import top.fosin.anan.cloudresource.entity.UserDetail;
import top.fosin.anan.cloudresource.entity.req.RoleUpdateDTO;
import top.fosin.anan.cloudresource.entity.res.UserAuthDto;
import top.fosin.anan.data.aware.OrganizAware;
import top.fosin.anan.data.aware.UserAware;

import java.util.List;
import java.util.Optional;

/**
 * @author fosin
 * @date 2018.7.23
 */
public class CurrentUserService
        implements AuditorAware<Long>, UserAware<Long>, OrganizAware<Long> {

    /**
     * 当前用户是否有登录态
     *
     * @return true：是，false：否
     */
    public boolean isUserLogin() {
        return SecurityContextHolder.getContext().getAuthentication() != null;
    }

    public UserDetail getUserDetail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Assert.notNull(authentication, "未找到当前用户的认证登录态!");
        Object principal = authentication.getPrincipal();
        Assert.isTrue(principal instanceof UserDetail, "认证信息有误，不是UserDetail对象，请检查！");
        return (UserDetail) principal;
    }

    /**
     * 得到当前登录用户的上下文信息
     *
     * @return UserDetail
     */

    public UserAuthDto getUser() {
        return this.getUserDetail().getUser();
    }

    /**
     * 得到当前登录用户登录的客户端信息
     *
     * @return Client
     */
    public Client getClient() {
        return this.getUserDetail().getClient();
    }

    /**
     * 当前操作者是否是超管用户
     *
     * @return boolean true：是 false：否
     */
    public boolean isSysAdminUser() {
        return isSysAdminUser(this.getUser().getUsercode());
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
    public boolean isAdminUser() {
        return hasAdminRole();
    }

    /**
     * 当前操作者是否是管理员用户
     *
     * @return boolean true：是 false：否
     */
    public boolean isAdminUser(String usercode) {
        return SystemConstant.ADMIN_USER_CODE.equals(usercode);
    }

    /**
     * 当前用户是否是拥有管理员角色
     *
     * @return boolean true：是 false：否
     */
    public boolean hasAdminRole() {
        List<RoleUpdateDTO> userRoles = this.getUser().getUserRoles();
        return userRoles.stream().anyMatch(userRole -> SystemConstant.ADMIN_ROLE_NAME.equals(userRole.getValue()));
    }

    /**
     * 当前用户是否是拥有超级管理员角色
     *
     * @return boolean true：是 false：否
     */
    public boolean hasSysAdminRole() {
        List<RoleUpdateDTO> userRoles = this.getUser().getUserRoles();
        return userRoles.stream().anyMatch(userRole -> SystemConstant.ANAN_ROLE_NAME.equals(userRole.getValue()));
    }

    @Override
    @NonNull
    public Optional<Long> getCurrentAuditor() {
        return Optional.of(this.getUser().getId());
    }

    @Override
    public Long getOrganizId() {
        return this.getUser().getOrganizId();
    }

    @Override
    public Long getTopId() {
        return this.getUser().getTopId();
    }

    @Override
    public Long getUserId() {
        return this.getUser().getId();
    }
}
