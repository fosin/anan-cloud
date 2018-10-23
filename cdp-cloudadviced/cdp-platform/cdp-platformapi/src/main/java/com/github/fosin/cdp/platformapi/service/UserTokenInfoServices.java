package com.github.fosin.cdp.platformapi.service;

import com.github.fosin.cdp.platformapi.dto.CdpUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * Description:
 *
 * @author fosin
 * @date 2018.8.28
 */
//@Component
public class UserTokenInfoServices {
    @Autowired
    private ResourceServerTokenServices resourceServerTokenServices;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private UserInfoRestTemplateFactory userInfoRestTemplateFactory;

    public CdpUserDetail getUserDetail() {
        OAuth2RestTemplate userInfoRestTemplate = userInfoRestTemplateFactory.getUserInfoRestTemplate();
        OAuth2AccessToken oAuth2AccessToken = userInfoRestTemplate.getAccessToken();
        Assert.notNull(oAuth2AccessToken, "OAuth2AccessToken不能为空!");

//        OAuth2Authentication oAuth2Authentication = resourceServerTokenServices.loadAuthentication(oAuth2AccessToken.getValue());
        OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(oAuth2AccessToken.getValue());

        Authentication userAuthentication = oAuth2Authentication.getUserAuthentication();
        Map details = (Map) userAuthentication.getDetails();
        return  (CdpUserDetail)details.get("principal");
    }

}
