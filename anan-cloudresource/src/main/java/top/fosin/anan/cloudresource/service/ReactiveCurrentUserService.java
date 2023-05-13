package top.fosin.anan.cloudresource.service;

import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import top.fosin.anan.cloudresource.constant.SystemConstant;
import top.fosin.anan.cloudresource.entity.Client;
import top.fosin.anan.cloudresource.entity.res.UserAuthDto;
import top.fosin.anan.cloudresource.entity.UserDetail;
import top.fosin.anan.data.aware.reactive.OrganizAware;
import top.fosin.anan.data.aware.reactive.UserAware;

import java.util.Objects;

/**
 * @author fosin
 * @date 2021.12.25
 * @since 3.0.0
 */
public class ReactiveCurrentUserService implements ReactiveAuditorAware<Long>, UserAware<Long>, OrganizAware<Long> {


    /**
     * 当前请求是否是用户请求（携带token的请求）
     *
     * @return boolean
     */
    public Mono<Boolean> isUserLogin() {
        return ReactiveSecurityContextHolder.getContext().map(Objects::nonNull);
    }

    public Mono<UserDetail> getUserDetail() {
        return ReactiveSecurityContextHolder.getContext().map(securityContext -> {
            Authentication authentication = securityContext.getAuthentication();
            Assert.notNull(authentication, "未找到当前用户的认证登录态!");
            Object principal = authentication.getPrincipal();
            Assert.isTrue(principal instanceof UserDetail, "认证信息有误，不是UserDetail对象，请检查！");
            return (UserDetail) principal;
        });
    }

    /**
     * 得到当前登录用户的上下文信息
     *
     * @return Mono<UserDetail>
     */

    public Mono<UserAuthDto> getUser() {
        return this.getUserDetail().map(UserDetail::getUser);
    }

    /**
     * 得到当前登录用户登录的客户端信息
     *
     * @return Client
     */
    public Mono<Client> getClient() {
        return this.getUserDetail().map(UserDetail::getClient);
    }

    /**
     * 当前操作者是否是超管用户
     *
     * @return boolean true：是 false：否
     */
    public Mono<Boolean> isSysAdminUser() {
        return this.getUser().map(dto -> isSysAdminUser(dto.getUsercode()));
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
    public Mono<Boolean> isAdminUser() {
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
    public Mono<Boolean> hasAdminRole() {
        return this.getUser()
                .map(UserAuthDto::getUserRoles)
                .flatMapMany(Flux::fromIterable)
                .any(userRole -> SystemConstant.ADMIN_ROLE_NAME.equals(userRole.getValue()));
    }

    /**
     * 当前用户是否是拥有超级管理员角色
     *
     * @return boolean true：是 false：否
     */
    public Mono<Boolean> hasSysAdminRole() {
        return this.getUser()
                .map(UserAuthDto::getUserRoles)
                .flatMapMany(Flux::fromIterable)
                .any(userRole -> SystemConstant.ANAN_ROLE_NAME.equals(userRole.getValue()));
    }

    @Override
    @NonNull
    public Mono<Long> getCurrentAuditor() {
        return this.getUser().map(UserAuthDto::getId);
    }

    @Override
    public Mono<Long> getOrganizId() {
        return this.getUser().map(UserAuthDto::getOrganizId);
    }

    @Override
    public Mono<Long> getTopId() {
        return this.getUser().map(UserAuthDto::getTopId);
    }

    @Override
    public Mono<Long> getUserId() {
        return this.getUser().map(UserAuthDto::getId);
    }
}
