package com.github.fosin.cdp.platformapi.util;

import com.github.fosin.cdp.platformapi.dto.CdpUserDetail;
import com.github.fosin.cdp.platformapi.dto.Client;
import com.github.fosin.cdp.platformapi.entity.CdpSysPermissionEntity;
import com.github.fosin.cdp.platformapi.entity.CdpSysUserEntity;
import com.github.fosin.cdp.util.BeanUtil;
import com.github.fosin.cdp.util.ClassUtil;
import com.github.fosin.cdp.util.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.reflect.*;
import java.security.Principal;
import java.util.*;

import static com.github.fosin.cdp.util.ClientUtil.PATTERN;

/**
 * Description:
 *
 * @author fosin
 * @date 2018.7.23
 */
@Slf4j
public class LoginUserUtil {
    private static Map<String, CdpUserDetail> userDetailMap = new HashMap<>();

    private static CdpUserDetail getCurrentUserInfo(HttpServletRequest request) {
        Authentication userAuthentication = getUserAuthentication(request);
        Assert.notNull(userAuthentication, "userAuthentication不能为空，请检查是否没有登录用户!");

        Map details = (Map) userAuthentication.getDetails();
        Object principal = details.get("principal");
        Assert.isTrue(principal instanceof Map, "principal不是Map类型，请检查是否没有登录用户!");

        CdpUserDetail cdpUserDetail;
        try {
            cdpUserDetail = BeanUtil.mapToBean((Map) principal, CdpUserDetail.class);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        return cdpUserDetail;
    }

    private static HttpServletRequest getHttpServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        Assert.isTrue(requestAttributes instanceof ServletRequestAttributes, "requestAttributes不是ServletRequestAttributes类型，请检查是否没有登录用户!");

        return ((ServletRequestAttributes) requestAttributes).getRequest();
    }

    /**
     * 得到当前登录用户的上下文信息
     *
     * @return CdpUserDetail
     */
    private static OAuth2Authentication getOAuth2Authentication(HttpServletRequest request) {
        Assert.notNull(request, "请求对象HttpServletRequest不能为空!");
        Principal userPrincipal = request.getUserPrincipal();
        Assert.isTrue(userPrincipal instanceof OAuth2Authentication, "不是Oauth2认证方式UserPrincipal!");
        return (OAuth2Authentication) userPrincipal;
    }

    /**
     * 得到当前登录用户的上下文信息
     *
     * @return CdpUserDetail
     */
    private static Authentication getUserAuthentication(HttpServletRequest request) {
        Assert.notNull(request, "请求对象HttpServletRequest不能为空!");
        OAuth2Authentication oAuth2Authentication = getOAuth2Authentication(request);
        return oAuth2Authentication.getUserAuthentication();
    }

    /**
     * 得到当前登录用户的上下文信息
     *
     * @return CdpUserDetail
     */
    public static OAuth2Authentication getOAuth2Authentication() {
        return getOAuth2Authentication(getHttpServletRequest());
    }

    /**
     * 得到当前登录用户的上下文信息
     *
     * @return CdpUserDetail
     */
    public static Authentication getUserAuthentication() {
        return getUserAuthentication(getHttpServletRequest());
    }

    /**
     * 得到当前登录用户的上下文信息
     *
     * @return CdpUserDetail
     */
    public static CdpUserDetail getUserDetail() {
        HttpServletRequest request = getHttpServletRequest();
        Assert.notNull(request, "请求对象HttpServletRequest不能为空!");
        String authentication = request.getHeader(HttpHeaders.AUTHORIZATION);
        CdpUserDetail userDetail;
        if (authentication == null || "".equals(authentication)) {
            userDetail = getCurrentUserInfo(request);
            removeOldUserDetail(userDetail);
        } else {
            userDetail = userDetailMap.get(authentication);
            if (userDetail == null || userDetail.getUser() == null) {
                userDetail = getCurrentUserInfo(request);
                if (userDetail != null && userDetail.getUser() != null) {
                    removeOldUserDetail(userDetail);
                    userDetailMap.put(authentication, userDetail);
                }
            }
        }
        return userDetail;
    }

    private static void removeOldUserDetail(CdpUserDetail userDetail) {
        Assert.notNull(userDetail, "用户信息不能为空!");
        CdpSysUserEntity user = userDetail.getUser();
        Set<String> needDelKeys = new HashSet<>();
        for (String key : userDetailMap.keySet()) {
            CdpSysUserEntity userEntity = userDetailMap.get(key).getUser();
            if (user.getId().equals(userEntity.getId())) {
                needDelKeys.add(key);
            }
        }
        for (String key : needDelKeys) {
            userDetailMap.remove(key);
        }
    }

    public static void clear() {
        userDetailMap.clear();
    }

    /**
     * 得到当前登录用户的上下文信息
     *
     * @return CdpUserDetail
     */

    public static CdpSysUserEntity getUser() {
        return getUserDetail().getUser();
    }

    /**
     * 得到当前登录用户的权限数
     *
     * @return CdpSysPermissionEntity
     */
    public static CdpSysPermissionEntity getPermissionTree() {
        return getUserDetail().getPermissionTree();
    }

    /**
     * 得到当前登录用户登录的客户端信息
     *
     * @return Client
     */
    public static Client getClient() {
        return getUserDetail().getClient();
    }
}
