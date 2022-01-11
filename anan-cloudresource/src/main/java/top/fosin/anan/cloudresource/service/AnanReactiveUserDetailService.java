package top.fosin.anan.cloudresource.service;

import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import top.fosin.anan.cloudresource.constant.SystemConstant;
import top.fosin.anan.cloudresource.dto.AnanClient;
import top.fosin.anan.cloudresource.dto.AnanUserAuthDto;
import top.fosin.anan.cloudresource.dto.AnanUserDetail;
import top.fosin.anan.security.util.AnanReactiveJwtTool;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @author fosin
 * @date 2021.12.25
 * @since 3.0.0
 */
public class AnanReactiveUserDetailService extends AnanReactiveJwtTool<AnanUserDetail>
        implements AuditorAware<Long> {

    public AnanReactiveUserDetailService(ReactiveJwtDecoder jwtDecoder) {
        super(jwtDecoder);
    }

    @Override
    public void removeCachedUser(Mono<AnanUserDetail> userDetail) {
        Assert.notNull(userDetail, "用户信息不能为空!");
        userDetail.subscribe(ananUserDetail -> {
            AnanUserAuthDto user = ananUserDetail.getUser();
            Set<String> needDelKeys = new HashSet<>();
            Map<String, Mono<AnanUserDetail>> userCaches = this.getUserCaches();
            for (String key : userCaches.keySet()) {
                userCaches.get(key).subscribe(ananUserDetail1 -> {
                    AnanUserAuthDto userEntity = ananUserDetail1.getUser();
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
     * @return Mono<AnanUserDetail>
     */

    public Mono<AnanUserAuthDto> getAnanUser() {
        return this.getCachedUser().map(AnanUserDetail::getUser);
    }

    /**
     * 得到当前登录用户的ID
     *
     * @return Long 前登录用户的ID
     */
    public Mono<Long> getAnanUserId() {
        return this.getAnanUser().map(AnanUserAuthDto::getId);
    }

    /**
     * 得到当前登录用户名
     *
     * @return Long 前登录用户名
     */
    public Mono<String> getAnanUserName() {
        return this.getAnanUser().map(AnanUserAuthDto::getUsername);
    }

    /**
     * 得到当前登录用户的机构ID
     *
     * @return Long 前登录用户的机构ID
     */
    public Mono<Long> getAnanOrganizId() {
        return this.getAnanUser().map(AnanUserAuthDto::getOrganizId);
    }

    /**
     * 得到当前登录用户的顶级机构ID
     *
     * @return Long 顶级机构ID
     */
    public Mono<Long> getAnanTopId() {
        return this.getAnanUser().map(AnanUserAuthDto::getTopId);
    }

    /**
     * 得到当前登录用户工号
     *
     * @return Long 前登录用户工号
     */
    public Mono<String> getAnanUserCode() {
        return this.getAnanUser().map(AnanUserAuthDto::getUsercode);
    }

    /**
     * 得到当前登录用户登录的客户端信息
     *
     * @return Client
     */
    public Mono<AnanClient> getAnanClient() {
        return this.getCachedUser().map(AnanUserDetail::getAnanClient);
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
                .map(AnanUserAuthDto::getUserRoles)
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
                .map(AnanUserAuthDto::getUserRoles)
                .flatMapMany(Flux::fromIterable)
                .any(userRole -> SystemConstant.ANAN_ROLE_NAME.equals(userRole.getValue()));
    }

    @Override
    @NonNull
    public Optional<Long> getCurrentAuditor() {
        return Optional.ofNullable(this.getAnanUserId().block());
    }
}
