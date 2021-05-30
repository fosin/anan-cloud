package top.fosin.anan.admin;

import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.web.client.BasicAuthHttpHeaderProvider;
import de.codecentric.boot.admin.server.web.client.HttpHeadersProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author fosin
 * @date 2018.8.12
 */
@Component
public class AdminHttpHeadersProvider implements HttpHeadersProvider {
    @Override
    public HttpHeaders getHeaders(Instance instance) {
        String username = instance.getRegistration().getMetadata().get("user.name");
        String password = instance.getRegistration().getMetadata().get("user.password");
        HttpHeaders headers = new HttpHeaders();

        if (StringUtils.hasText(username) && StringUtils.hasText(password)) {
            headers = new BasicAuthHttpHeaderProvider().getHeaders(instance);
        } else {
//            String authorization = null;
//            HttpServletRequest httpServletRequest = getHttpServletRequest();
//            if (httpServletRequest != null) {
//                String accessToken = httpServletRequest.getParameter(OAuth2AccessToken.ACCESS_TOKEN);
//                if (accessToken == null) {
//                    authorization = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
//                } else {
//                    authorization = getAuthorization(OAuth2AccessToken.TokenType.BEARER.getValue(), accessToken);
//                }
//            }
//            if (authorization == null) {
//                OAuth2AccessToken accessToken = oAuth2RestTemplate.getAccessToken();
//                String accessTokenValue = accessToken.getTokenValue();
//                String tokenType = accessToken.getTokenType().getValue();
//                if (StringUtils.hasText(accessTokenValue) && StringUtils.hasText(tokenType)) {
//                    authorization = getAuthorization(tokenType, accessTokenValue);
//                }
//            }
//            if (StringUtils.hasText(authorization)) {
//                headers.set(HttpHeaders.AUTHORIZATION, authorization);
//            }
        }

        return headers;
    }

//    private String getAuthorization(String tokenType, String accessTokenValue) {
//        return tokenType + " " + accessTokenValue;
//    }
//
//    private static HttpServletRequest getHttpServletRequest() {
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        if (requestAttributes instanceof ServletRequestAttributes) {
//            return ((ServletRequestAttributes) requestAttributes).getRequest();
//        }
//        return null;
//    }
}
