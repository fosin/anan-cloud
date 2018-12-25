package com.github.fosin.cdp.platformapi.util;

import com.github.fosin.cdp.platformapi.dto.CdpUserDetail;
import com.github.fosin.cdp.platformapi.entity.CdpSysPermissionEntity;
import com.github.fosin.cdp.platformapi.dto.Client;
import com.github.fosin.cdp.platformapi.entity.CdpSysUserEntity;
import com.github.fosin.cdp.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Description:
 *
 * @author fosin
 * @date 2018.7.23
 */
@Slf4j
public class LoginUserUtil {
    private static Map<String, CdpUserDetail> userDetailMap = new HashMap<>();

    private static CdpUserDetail getCurrentUserInfo(HttpServletRequest request) throws Exception {
        Authentication userAuthentication = getUserAuthentication(request);
        if (userAuthentication == null) {
            return null;
        }
        Map details = (Map) userAuthentication.getDetails();
        Object principal = details.get("principal");
        if (principal instanceof Map) {
            return BeanUtil.toBean(CdpUserDetail.class, (Map) principal);
        }
        return null;
    }

    private static HttpServletRequest getHttpServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        }
        return null;
    }

    /**
     * 得到当前登录用户的上下文信息
     *
     * @return CdpUserDetail
     */
    private static OAuth2Authentication getOAuth2Authentication(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        Principal userPrincipal = request.getUserPrincipal();
        if (userPrincipal instanceof OAuth2Authentication) {
            return (OAuth2Authentication) userPrincipal;
        }
        return null;
    }

    /**
     * 得到当前登录用户的上下文信息
     *
     * @return CdpUserDetail
     */
    private static Authentication getUserAuthentication(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        OAuth2Authentication oAuth2Authentication = getOAuth2Authentication(request);
        return oAuth2Authentication == null ? null : oAuth2Authentication.getUserAuthentication();
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
        if (request == null) {
            return null;
        }
        String authentication = request.getHeader(HttpHeaders.AUTHORIZATION);
        CdpUserDetail userDetail = null;
        if (authentication == null || "".equals(authentication)) {
            try {
                userDetail = getCurrentUserInfo(request);
                removeOldUserDetail(userDetail);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            userDetail = userDetailMap.get(authentication);
            if (userDetail == null) {
                try {
                    userDetail = getCurrentUserInfo(request);
                    if (userDetail != null) {
                        removeOldUserDetail(userDetail);
                        userDetailMap.put(authentication, userDetail);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return userDetail;
    }

    private static void removeOldUserDetail(CdpUserDetail userDetail) {
        if (userDetail == null) {
            return;
        }
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
        CdpUserDetail userDetail = getUserDetail();
        return userDetail == null ? null : userDetail.getUser();
    }

    /**
     * 得到当前登录用户的权限数
     *
     * @return CdpSysPermissionEntity
     */
    public static CdpSysPermissionEntity getPermissionTree() {
        CdpUserDetail userDetail = getUserDetail();
        return userDetail == null ? null : userDetail.getPermissionTree();
    }

    /**
     * 得到当前登录用户登录的客户端信息
     *
     * @return Client
     */
    public static Client getClient() {
        CdpUserDetail userDetail = getUserDetail();
        return userDetail == null ? null : userDetail.getClient();
    }
}
