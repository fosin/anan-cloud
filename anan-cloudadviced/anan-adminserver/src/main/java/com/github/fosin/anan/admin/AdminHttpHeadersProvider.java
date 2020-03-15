package com.github.fosin.anan.admin;

import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.web.client.BasicAuthHttpHeaderProvider;
import de.codecentric.boot.admin.server.web.client.HttpHeadersProvider;

import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Description:
 *
 * @author fosin
 * @date 2018.8.12
 */
@Component
public class AdminHttpHeadersProvider implements HttpHeadersProvider {
    private final OAuth2RestTemplate oAuth2RestTemplate;

    public AdminHttpHeadersProvider(OAuth2RestTemplate oAuth2RestTemplate) {
        this.oAuth2RestTemplate = oAuth2RestTemplate;
    }

    @Override
    public HttpHeaders getHeaders(Instance instance) {
        String username = instance.getRegistration().getMetadata().get("user.name");
        String password = instance.getRegistration().getMetadata().get("user.password");
        HttpHeaders headers = new HttpHeaders();

        if (StringUtils.hasText(username) && StringUtils.hasText(password)) {
            headers = new BasicAuthHttpHeaderProvider().getHeaders(instance);
        } else {
            String authorization = null;
            HttpServletRequest httpServletRequest = getHttpServletRequest();
            if (httpServletRequest != null) {
                String accessToken = httpServletRequest.getParameter(OAuth2AccessToken.ACCESS_TOKEN);
                if (accessToken == null) {
                    authorization = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
                } else {
                    authorization = getAuthorization(OAuth2AccessToken.BEARER_TYPE, accessToken);
                }
            }
            if (authorization == null) {
                OAuth2AccessToken accessToken = oAuth2RestTemplate.getAccessToken();
                String accessTokenValue = accessToken.getValue();
                String tokenType = accessToken.getTokenType();
                if (StringUtils.hasText(accessTokenValue) && StringUtils.hasText(tokenType)) {
                    authorization = getAuthorization(tokenType, accessTokenValue);
                }
            }
            if (StringUtils.hasText(authorization)) {
                headers.set(HttpHeaders.AUTHORIZATION, authorization);
            }
        }

        return headers;
    }

    private String getAuthorization(String tokenType, String accessTokenValue) {
        return tokenType + " " + accessTokenValue;
    }

    private static HttpServletRequest getHttpServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        }
        return null;
    }
}
