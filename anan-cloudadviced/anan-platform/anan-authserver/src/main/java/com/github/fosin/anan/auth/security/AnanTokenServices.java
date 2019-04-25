package com.github.fosin.anan.auth.security;

import com.github.fosin.anan.platformapi.dto.AnanUserDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.*;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.Date;
import java.util.UUID;

/**
 * Description:
 *
 * @author fosin
 * @date 2018.8.10
 */
@Slf4j
public class AnanTokenServices extends DefaultTokenServices {
    private TokenStore tokenStore;
    private TokenEnhancer accessTokenEnhancer;

    @Override
    public OAuth2AccessToken createAccessToken(OAuth2Authentication authentication) throws AuthenticationException {
        OAuth2AccessToken existingAccessToken = tokenStore.getAccessToken(authentication);

        OAuth2RefreshToken refreshToken = null;
        if (existingAccessToken != null) {

            //通过IP地址判断是否异地登录,如果是异地登录则先清除之前认证Token信息
            Authentication userAuthentication = authentication.getUserAuthentication();
            boolean landFall = false;
            if (userAuthentication != null) {
                AnanUserDetail principal = (AnanUserDetail) userAuthentication.getPrincipal();
                if (principal != null) {
                    String oldClientIp = "";
                    OAuth2Authentication oldAuthentication = tokenStore.readAuthentication(existingAccessToken);
                    if (oldAuthentication != null) {
                        //获取之前登录IP
                        Authentication oldUserAuthentication = oldAuthentication.getUserAuthentication();

                        //由于直接通过(AnanUserDetail) userAuthentication.getPrincipal()获取oldPrincipal会和springboot-devtools
                        //产生ClassCastException,因此改成利用反射来获取字段值
//                   Object oldPrincipal = oldUserAuthentication.getPrincipal();
//                   oldClientIp = ReflectUtil.getValueByField("clientIp",oldPrincipal);
//                   Client client = ReflectUtil.getValueByField("client",oldPrincipal);
//                   oldClientIp = client.getIp();
                        AnanUserDetail oldPrincipal = (AnanUserDetail) oldUserAuthentication.getPrincipal();
                        oldClientIp = oldPrincipal.getClient().getIp();
                    }

                    //获取当前登录IP
                    String clientIp = principal.getClient().getIp();
                    //不一致则判断为异地登录
                    landFall = !clientIp.equalsIgnoreCase(oldClientIp);
                    log.debug("之前客户端IP:" + oldClientIp);
                    log.debug("当前客户端IP:" + clientIp);
                    log.debug("是否异地登录:" + landFall);
                }
            }

            if (existingAccessToken.isExpired() || landFall) {
                if (existingAccessToken.getRefreshToken() != null) {
                    refreshToken = existingAccessToken.getRefreshToken();
                    // The token store could remove the refresh token when the
                    // access token is removed, but we want to
                    // be sure...
                    tokenStore.removeRefreshToken(refreshToken);
//                    refreshToken = null;
                }
                tokenStore.removeAccessToken(existingAccessToken);
            } else {
                // Re-store the access token in case the authentication has changed
                tokenStore.storeAccessToken(existingAccessToken, authentication);
                return existingAccessToken;
            }
        }

        // Only create a new refresh token if there wasn't an existing one
        // associated with an expired access token.
        // Clients might be holding existing refresh tokens, so we re-use it in
        // the case that the old access token
        // expired.
        if (refreshToken == null) {
            refreshToken = createRefreshToken(authentication);
        }
        // But the refresh token itself might need to be re-issued if it has
        // expired.
        else if (refreshToken instanceof ExpiringOAuth2RefreshToken) {
            ExpiringOAuth2RefreshToken expiring = (ExpiringOAuth2RefreshToken) refreshToken;
            if (System.currentTimeMillis() > expiring.getExpiration().getTime()) {
                refreshToken = createRefreshToken(authentication);
            }
        }

        OAuth2AccessToken accessToken = createAccessToken(authentication, refreshToken);
        tokenStore.storeAccessToken(accessToken, authentication);
        // In case it was modified
        refreshToken = accessToken.getRefreshToken();
        if (refreshToken != null) {
            tokenStore.storeRefreshToken(refreshToken, authentication);
        }
        return accessToken;
    }

    private OAuth2RefreshToken createRefreshToken(OAuth2Authentication authentication) {
        if (!isSupportRefreshToken(authentication.getOAuth2Request())) {
            return null;
        }
        int validitySeconds = getRefreshTokenValiditySeconds(authentication.getOAuth2Request());
        String value = UUID.randomUUID().toString();
        if (validitySeconds > 0) {
            return new DefaultExpiringOAuth2RefreshToken(value, new Date(System.currentTimeMillis()
                    + (validitySeconds * 1000L)));
        }
        return new DefaultOAuth2RefreshToken(value);
    }

    private OAuth2AccessToken createAccessToken(OAuth2Authentication authentication, OAuth2RefreshToken refreshToken) {
        DefaultOAuth2AccessToken token = new DefaultOAuth2AccessToken(UUID.randomUUID().toString());
        int validitySeconds = getAccessTokenValiditySeconds(authentication.getOAuth2Request());
        if (validitySeconds > 0) {
            token.setExpiration(new Date(System.currentTimeMillis() + (validitySeconds * 1000L)));
        }
        token.setRefreshToken(refreshToken);
        token.setScope(authentication.getOAuth2Request().getScope());

        return accessTokenEnhancer != null ? accessTokenEnhancer.enhance(token, authentication) : token;
    }


    @Override
    public void setTokenEnhancer(TokenEnhancer accessTokenEnhancer) {
        this.accessTokenEnhancer = accessTokenEnhancer;
        super.setTokenEnhancer(accessTokenEnhancer);
    }

    @Override
    public void setTokenStore(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
        super.setTokenStore(tokenStore);
    }
}
