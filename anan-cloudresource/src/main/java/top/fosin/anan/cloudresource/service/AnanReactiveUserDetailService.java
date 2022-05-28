package top.fosin.anan.cloudresource.service;

import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import top.fosin.anan.cloudresource.constant.SystemConstant;
import top.fosin.anan.cloudresource.dto.Client;
import top.fosin.anan.cloudresource.dto.UserAuthDto;
import top.fosin.anan.cloudresource.dto.UserDetail;
import top.fosin.anan.model.aware.reactive.OrganizAware;
import top.fosin.anan.model.aware.reactive.UserAware;
import top.fosin.anan.security.util.AnanReactiveJwtTool;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author fosin
 * @date 2021.12.25
 * @since 3.0.0
 */
public class AnanReactiveUserDetailService extends AnanReactiveJwtTool<UserDetail>
        implements ReactiveAuditorAware<Long>,
        UserAware<Long>,
        OrganizAware<Long> {

    public AnanReactiveUserDetailService(ReactiveJwtDecoder jwtDecoder) {
        super(jwtDecoder);
    }

    @Override
    public void removeCachedUser(Mono<UserDetail> userDetail) {
        Assert.notNull(userDetail, "用户信息不能为空!");
        userDetail.subscribe(ananUserDetail -> {
            UserAuthDto user = ananUserDetail.getUser();
            Set<String> needDelKeys = new HashSet<>();
            Map<String, Mono<UserDetail>> userCaches = this.getUserCaches();
            for (String key : userCaches.keySet()) {
                userCaches.get(key).subscribe(ananUserDetail1 -> {
                    UserAuthDto userEntity = ananUserDetail1.getUser();
                    if (user.getId().equals(userEntity.getId())) {
                        needDelKeys.add(key);
                    }
                });
            }
            for (String key : needDelKeys) {
                userCaches.remove(key);
            }
        });
    }

    /**
     * 得到当前登录用户的上下文信息
     *
     * @return Mono<UserDetail>
     */

    public Mono<UserAuthDto> getAnanUser() {
        return this.getCachedUser().map(UserDetail::getUser);
    }

    /**
     * 得到当前登录用户的ID
     *
     * @return Long 前登录用户的ID
     */
    public Mono<Long> getAnanUserId() {
        return this.getAnanUser().map(UserAuthDto::getId);
    }

    /**
     * 得到当前登录用户名
     *
     * @return Long 前登录用户名
     */
    public Mono<String> getAnanUserName() {
        return this.getAnanUser().map(UserAuthDto::getUsername);
    }

    /**
     * 得到当前登录用户的机构ID
     *
     * @return Long 前登录用户的机构ID
     */
    public Mono<Long> getAnanOrganizId() {
        return this.getAnanUser().map(UserAuthDto::getOrganizId);
    }

    /**
     * 得到当前登录用户的顶级机构ID
     *
     * @return Long 顶级机构ID
     */
    public Mono<Long> getAnanTopId() {
        return this.getAnanUser().map(UserAuthDto::getTopId);
    }

    /**
     * 得到当前登录用户工号
     *
     * @return Long 前登录用户工号
     */
    public Mono<String> getAnanUserCode() {
        return this.getAnanUser().map(UserAuthDto::getUsercode);
    }

    /**
     * 得到当前登录用户登录的客户端信息
     *
     * @return Client
     */
    public Mono<Client> getAnanClient() {
        return this.getCachedUser().map(UserDetail::getClient);
    }

    /**
     * 当前操作者是否是超管用户
     *
     * @return boolean true：是 false：否
     */
    public Mono<Boolean> isSysAdminUser() {
        return this.getAnanUser().map(dto -> isSysAdminUser(dto.getUsercode()));
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
        return this.getAnanUser()
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
        return this.getAnanUser()
                .map(UserAuthDto::getUserRoles)
                .flatMapMany(Flux::fromIterable)
                .any(userRole -> SystemConstant.ANAN_ROLE_NAME.equals(userRole.getValue()));
    }

    @Override
    @NonNull
    public Mono<Long> getCurrentAuditor() {
        return this.getAnanUserId();
    }

    @Override
    public Mono<Long> getOrganizId() {
        return this.getAnanOrganizId();
    }

    @Override
    public Mono<Long> getTopId() {
        return this.getAnanTopId();
    }

    @Override
    public Mono<Long> getUserId() {
        return this.getAnanUserId();
    }
}
