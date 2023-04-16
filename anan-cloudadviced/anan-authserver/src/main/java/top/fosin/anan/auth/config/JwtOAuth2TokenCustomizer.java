package top.fosin.anan.auth.config;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.oidc.endpoint.OidcParameterNames;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import top.fosin.anan.auth.service.inter.AuthService;
import top.fosin.anan.cloudresource.dto.UserAuthDto;
import top.fosin.anan.cloudresource.dto.UserDetail;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.security.resource.AnanSecurityProperties;

import java.util.Set;
import java.util.stream.Collectors;

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
    private final AnanSecurityProperties ananSecurityProperties;

    @Override
    public void customize(JwtEncodingContext context) {
        JwtClaimsSet.Builder claims = context.getClaims();
        Authentication authorization = context.getPrincipal();
        if (authorization != null) {
            String authorityClaimName = ananSecurityProperties.getOauth2().getResourceServer().getAuthorityClaimName();
            Set<GrantedAuthority> authorities = authorization.getAuthorities().stream()
                    .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                    .collect(Collectors.toSet());
            setClaim(claims, authorityClaimName, authorities);
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
                    UserAuthDto userAuthDto;
                    if (authorization.getPrincipal() instanceof UserDetail) {
                        UserDetail userDetail = (UserDetail) authorization.getPrincipal();
                        userAuthDto = userDetail.getUser();
                    } else {
                        String usercode = authorization.getName();
                        userAuthDto = authService.findByUsercode(usercode);
                    }
                    if (userAuthDto != null) {
                        if (scopes.contains(DefaultOidcScopes.PHONE)) {
                            setClaim(claims, DefaultClaimNames.PHONE, userAuthDto.getPhone());
                            setClaim(claims, DefaultClaimNames.PHONE_VERIFIED, userAuthDto.getPhoneVerified() + "");
                        }
                        if (scopes.contains(DefaultOidcScopes.EMAIL)) {
                            setClaim(claims, DefaultClaimNames.EMAIL, userAuthDto.getEmail());
                            setClaim(claims, DefaultClaimNames.EMAIL_VERIFIED, userAuthDto.getEmailVerified() + "");
                        }
                        if (scopes.contains(DefaultOidcScopes.PROFILE)) {
                            setClaim(claims, DefaultClaimNames.ID, userAuthDto.getId() + "");
                            setClaim(claims, DefaultClaimNames.USER_NAME, userAuthDto.getUsername());
                            setClaim(claims, DefaultClaimNames.REAL_NAME_VERIFIED, userAuthDto.getRealNameVerified() + "");
                            setClaim(claims, DefaultClaimNames.SEX, userAuthDto.getSex() + "");
                            setClaim(claims, DefaultClaimNames.GIVEN_NAME, userAuthDto.getGivenName());
                            setClaim(claims, DefaultClaimNames.FAMILY_NAME, userAuthDto.getFamilyName());
                            setClaim(claims, DefaultClaimNames.MIDDLE_NAME, userAuthDto.getMiddleName());
                            setClaim(claims, DefaultClaimNames.NICKNAME, userAuthDto.getNickname());
                            setClaim(claims, DefaultClaimNames.PREFERRED_USERNAME, userAuthDto.getPreferredUsername());
                            setClaim(claims, DefaultClaimNames.WEBSITE, userAuthDto.getWebsite());
                            setClaim(claims, DefaultClaimNames.AVATAR, userAuthDto.getAvatar());
                            setClaim(claims, DefaultClaimNames.BIRTHDATE, DateTimeUtil.formatTime(userAuthDto.getBirthday(), DateTimeUtil.DATE_PATTERN));
                            setClaim(claims, DefaultClaimNames.UPDATE_BY, userAuthDto.getUpdateBy() + "");
                            setClaim(claims, DefaultClaimNames.UPDATE_TIME, DateTimeUtil.formatTime(userAuthDto.getUpdateTime(), DateTimeUtil.DATETIME_PATTERN));
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
