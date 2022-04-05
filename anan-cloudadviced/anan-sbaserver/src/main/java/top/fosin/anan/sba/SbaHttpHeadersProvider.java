package top.fosin.anan.sba;

import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.web.client.BasicAuthHttpHeaderProvider;
import de.codecentric.boot.admin.server.web.client.HttpHeadersProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;

/**
 * @author fosin
 * @date 2018.8.12
 */
//@Component
public class SbaHttpHeadersProvider implements HttpHeadersProvider {

    @Override
    public HttpHeaders getHeaders(@NonNull Instance instance) {

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
        return new BasicAuthHttpHeaderProvider().getHeaders(instance);
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
