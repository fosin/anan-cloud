package com.github.fosin.anan.cloudresource.util;

import com.github.fosin.anan.cloudresource.dto.AnanUserDetail;
import com.github.fosin.anan.cloudresource.dto.AnanUserDto;
import com.github.fosin.anan.cloudresource.dto.AnanClient;
import com.github.fosin.anan.security.util.AnanJwtTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

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
@Component
public class AnanUserDetailUtil extends AnanJwtTool<AnanUserDetail> {

    public AnanUserDetailUtil(JwtDecoder jwtDecoder) {
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
     * 得到当前登录用户登录的客户端信息
     *
     * @return Client
     */
    public AnanClient getAnanClient() {
        return this.getCachedUser().getAnanClient();
    }
}
