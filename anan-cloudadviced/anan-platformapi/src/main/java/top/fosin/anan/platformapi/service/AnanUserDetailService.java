package top.fosin.anan.platformapi.service;

import top.fosin.anan.cloudresource.constant.SystemConstant;
import top.fosin.anan.cloudresource.dto.AnanClient;
import top.fosin.anan.cloudresource.dto.AnanUserDetail;
import top.fosin.anan.cloudresource.dto.AnanUserDto;
import top.fosin.anan.cloudresource.dto.request.AnanRoleRetrieveDto;
import top.fosin.anan.security.util.AnanJwtTool;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Description:
 *
 * @author fosin
 * @date 2018.7.23
 */
public class AnanUserDetailService extends AnanJwtTool<AnanUserDetail> {

    public AnanUserDetailService(JwtDecoder jwtDecoder) {
        super(jwtDecoder);
    }

    @Override
    public void removeCachedUser(AnanUserDetail userDetail) {
        Assert.notNull(userDetail, "用户信息不能为空!");
        AnanUserDto user = userDetail.getUser();
        Set<String> needDelKeys = new HashSet<>();
        Map<String, AnanUserDetail> userCaches = this.getUserCaches();
        for (String key : userCaches.keySet()) {
            AnanUserDto userEntity = userCaches.get(key).getUser();
            if (user.getId().equals(userEntity.getId())) {
                needDelKeys.add(key);
            }
        }
        for (String key : needDelKeys) {
            userCaches.remove(key);
        }
    }

    /**
     * 得到当前登录用户的上下文信息
     *
     * @return AnanUserDetail
     */

    public AnanUserDto getAnanUser() {
        return this.getCachedUser().getUser();
    }

    /**
     * 得到当前登录用户的ID
     *
     * @return Long 前登录用户的ID
     */
    public Long getAnanUserId() {
        return this.getAnanUser().getId();
    }

    /**
     * 得到当前登录用户名
     *
     * @return Long 前登录用户名
     */
    public String getAnanUserName() {
        return this.getAnanUser().getUsername();
    }

    /**
     * 得到当前登录用户的机构ID
     *
     * @return Long 前登录用户的机构ID
     */
    public Long getAnanOrganizId() {
        return this.getAnanUser().getOrganizId();
    }

    /**
     * 得到当前登录用户工号
     *
     * @return Long 前登录用户工号
     */
    public String getAnanUserCode() {
        return this.getAnanUser().getUsercode();
    }

    /**
     * 得到当前登录用户登录的客户端信息
     *
     * @return Client
     */
    public AnanClient getAnanClient() {
        return this.getCachedUser().getAnanClient();
    }

    /**
     * 当前操作者是否是超管用户
     *
     * @return boolean true：是 false：否
     */
    public boolean isSysAdminUser() {
        return isSysAdminUser(this.getAnanUser().getUsercode());
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
        List<AnanRoleRetrieveDto> userRoles = this.getAnanUser().getUserRoles();
        return userRoles.stream().anyMatch(userRole -> SystemConstant.ADMIN_ROLE_NAME.equals(userRole.getValue()));
    }

    /**
     * 当前用户是否是拥有超级管理员角色
     *
     * @return boolean true：是 false：否
     */
    public boolean hasSysAdminRole() {
        List<AnanRoleRetrieveDto> userRoles = this.getAnanUser().getUserRoles();
        return userRoles.stream().anyMatch(userRole -> SystemConstant.ANAN_ROLE_NAME.equals(userRole.getValue()));
    }
}
