package top.fosin.anan.auth.config;

import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationContext;
import top.fosin.anan.cloudresource.config.DefaultClaimNames;
import top.fosin.anan.cloudresource.config.DefaultOidcScopes;

import java.util.*;
import java.util.function.Function;

/**
 * 默认OIDC用户信息映射关系
 *
 * @author fosin
 * @date 2023-1-25
 * @since 3.3.0
 */
public class DefaultOidcUserInfoMapper implements Function<OidcUserInfoAuthenticationContext, OidcUserInfo> {
    private final List<String> EMAIL_CLAIMS = Arrays.asList(
            DefaultClaimNames.EMAIL,
            DefaultClaimNames.EMAIL_VERIFIED
    );
    private final List<String> ADDRESS_CLAIMS = Arrays.asList(
            DefaultClaimNames.COUNTRY,
            DefaultClaimNames.REGION,
            DefaultClaimNames.LOCALITY,
            DefaultClaimNames.LOCALE,
            DefaultClaimNames.FORMATTED,
            DefaultClaimNames.STREET_ADDRESS,
            DefaultClaimNames.ADDRESS,
            DefaultClaimNames.POSTAL_CODE,
            DefaultClaimNames.ZONEINFO
    );
    private final List<String> PHONE_CLAIMS = Arrays.asList(
            DefaultClaimNames.PHONE,
            DefaultClaimNames.PHONE_VERIFIED
    );
    private final List<String> ID_NO_CLAIMS = Arrays.asList(
            DefaultClaimNames.ID_NO,
            DefaultClaimNames.ID_NO_TYPE,
            DefaultClaimNames.REAL_NAME_VERIFIED
    );
    private final List<String> PROFILE_CLAIMS = Arrays.asList(
            DefaultClaimNames.ID,
            DefaultClaimNames.ORGANIZ_ID,
            DefaultClaimNames.TOP_ID,
            DefaultClaimNames.SUB,
            DefaultClaimNames.USER_NAME,
            DefaultClaimNames.AVATAR,
            DefaultClaimNames.WEBSITE,
            DefaultClaimNames.SEX,
            DefaultClaimNames.BIRTHDATE
    );

    @Override
    public OidcUserInfo apply(OidcUserInfoAuthenticationContext authenticationContext) {
        OAuth2Authorization authorization = authenticationContext.getAuthorization();
        OidcIdToken idToken = authorization.getToken(OidcIdToken.class).getToken();
        OAuth2AccessToken accessToken = authenticationContext.getAccessToken();
        Map<String, Object> scopeRequestedClaims = getClaimsRequestedByScope(idToken.getClaims(),
                accessToken.getScopes());

        return new OidcUserInfo(scopeRequestedClaims);
    }

    private Map<String, Object> getClaimsRequestedByScope(Map<String, Object> claims, Set<String> requestedScopes) {
        Set<String> scopeRequestedClaimNames = new HashSet<>(32);
        scopeRequestedClaimNames.add(DefaultClaimNames.SUB);

        if (requestedScopes.contains(DefaultOidcScopes.ADDRESS)) {
            scopeRequestedClaimNames.addAll(ADDRESS_CLAIMS);
        }
        if (requestedScopes.contains(DefaultOidcScopes.EMAIL)) {
            scopeRequestedClaimNames.addAll(EMAIL_CLAIMS);
        }
        if (requestedScopes.contains(DefaultOidcScopes.ID_NO)) {
            scopeRequestedClaimNames.addAll(ID_NO_CLAIMS);
        }
        if (requestedScopes.contains(DefaultOidcScopes.PHONE)) {
            scopeRequestedClaimNames.addAll(PHONE_CLAIMS);
        }
        if (requestedScopes.contains(DefaultOidcScopes.PROFILE)) {
            scopeRequestedClaimNames.addAll(PROFILE_CLAIMS);
        }

        Map<String, Object> requestedClaims = new HashMap<>(claims);
        requestedClaims.keySet().removeIf(claimName -> !scopeRequestedClaimNames.contains(claimName));

        return requestedClaims;
    }

}
