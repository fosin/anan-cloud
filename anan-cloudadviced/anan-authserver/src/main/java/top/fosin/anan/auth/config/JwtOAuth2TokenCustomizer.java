package top.fosin.anan.auth.config;

import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.core.oidc.endpoint.OidcParameterNames;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import top.fosin.anan.auth.service.inter.AuthService;
import top.fosin.anan.cloudresource.dto.UserAuthDto;
import top.fosin.anan.core.util.DateTimeUtil;

import java.util.Set;

/**
 * JWT 自定义Token内容
 *
 * @author fosin
 * @date 2023-2-18
 * @since 3.3.0
 */
@AllArgsConstructor
public class JwtOAuth2TokenCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {
    private final AuthService authService;

    @Override
    public void customize(JwtEncodingContext context) {
        JwtClaimsSet.Builder claims = context.getClaims();
        OAuth2Authorization authorization = context.getAuthorization();
        if (authorization != null) {
            if (context.getTokenType().equals(OAuth2TokenType.ACCESS_TOKEN)) {
                // Customize headers/claims for access_token
            } else if (context.getTokenType().getValue().equals(OidcParameterNames.ID_TOKEN)) {
                // Customize headers/claims for id_token
                Set<String> scopes = context.getAuthorizedScopes();
                if (scopes.contains(DefaultOidcScopes.ADDRESS)) {
                    //setClaim(claims, DefaultClaimNames.ADDRESS, "");
                    //setClaim(claims, DefaultClaimNames.STREET_ADDRESS, "");
                    //setClaim(claims, DefaultClaimNames.FORMATTED, "");
                }
                if (scopes.contains(DefaultOidcScopes.ID_NO)) {
                    //setClaim(claims, DefaultClaimNames.ID_NO, "522223199001010520");
                    //setClaim(claims, DefaultClaimNames.ID_NO_TYPE, "身份证号");
                }
                if (scopes.stream().anyMatch(s -> s.equals(DefaultOidcScopes.PHONE) || s.equals(DefaultOidcScopes.EMAIL) || s.equals(DefaultOidcScopes.PROFILE))) {
                    String usercode = authorization.getPrincipalName();
                    UserAuthDto user = authService.findByUsercode(usercode);

                    if (user != null) {
                        if (scopes.contains(DefaultOidcScopes.PHONE)) {
                            setClaim(claims, DefaultClaimNames.PHONE, user.getPhone());
                            setClaim(claims, DefaultClaimNames.PHONE_VERIFIED, user.getPhoneVerified() + "");
                        }
                        if (scopes.contains(DefaultOidcScopes.EMAIL)) {
                            setClaim(claims, DefaultClaimNames.EMAIL, user.getEmail());
                            setClaim(claims, DefaultClaimNames.EMAIL_VERIFIED, user.getEmailVerified() + "");
                        }
                        if (scopes.contains(DefaultOidcScopes.PROFILE)) {
                            setClaim(claims, DefaultClaimNames.ID, user.getId() + "");
                            setClaim(claims, DefaultClaimNames.USER_NAME, user.getUsername());
                            setClaim(claims, DefaultClaimNames.REAL_NAME_VERIFIED, user.getRealNameVerified() + "");
                            setClaim(claims, DefaultClaimNames.SEX, user.getSex() + "");
                            setClaim(claims, DefaultClaimNames.GIVEN_NAME, user.getGivenName());
                            setClaim(claims, DefaultClaimNames.FAMILY_NAME, user.getFamilyName());
                            setClaim(claims, DefaultClaimNames.MIDDLE_NAME, user.getMiddleName());
                            setClaim(claims, DefaultClaimNames.NICKNAME, user.getNickname());
                            setClaim(claims, DefaultClaimNames.PREFERRED_USERNAME, user.getPreferredUsername());
                            setClaim(claims, DefaultClaimNames.WEBSITE, user.getWebsite());
                            setClaim(claims, DefaultClaimNames.AVATAR, user.getAvatar());
                            setClaim(claims, DefaultClaimNames.BIRTHDATE, DateTimeUtil.formatTime(user.getBirthday(), DateTimeUtil.DATE_PATTERN));
                            setClaim(claims, DefaultClaimNames.UPDATE_BY, user.getUpdateBy() + "");
                            setClaim(claims, DefaultClaimNames.UPDATE_TIME, DateTimeUtil.formatTime(user.getUpdateTime(), DateTimeUtil.DATETIME_PATTERN));
                        }
                    }
                }
            }
        }
    }

    private void setClaim(JwtClaimsSet.Builder claims, String name, Object value) {
        if (value != null) {
            claims.claim(name, value);
        }
    }

}
