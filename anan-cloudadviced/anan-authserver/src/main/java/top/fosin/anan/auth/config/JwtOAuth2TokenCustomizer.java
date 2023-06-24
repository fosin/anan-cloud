package top.fosin.anan.auth.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.endpoint.OidcParameterNames;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import top.fosin.anan.cloudresource.config.DefaultClaimNames;
import top.fosin.anan.cloudresource.config.DefaultOidcScopes;
import top.fosin.anan.cloudresource.entity.SecurityUser;
import top.fosin.anan.cloudresource.entity.res.UserAuthDTO;
import top.fosin.anan.cloudresource.service.inter.rpc.UserRpcService;
import top.fosin.anan.core.util.BeanUtil;
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

public class JwtOAuth2TokenCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {
    private final UserRpcService userRpcService;
    private final String authorityClaimName;

    public JwtOAuth2TokenCustomizer(UserRpcService userRpcService, AnanSecurityProperties ananSecurityProperties) {
        this.userRpcService = userRpcService;
        this.authorityClaimName = ananSecurityProperties.getOauth2().getResourceServer().getAuthorityClaimName();
    }

    @Override
    public void customize(JwtEncodingContext context) {
        JwtClaimsSet.Builder claims = context.getClaims();
        Authentication authorization = context.getPrincipal();
        if (authorization != null) {
            UserAuthDTO userAuthDto;
            if (authorization.getPrincipal() instanceof SecurityUser) {
                SecurityUser securityUser = (SecurityUser) authorization.getPrincipal();
                userAuthDto = securityUser.getUser();
            } else {
                String usercode = authorization.getName();
                userAuthDto = BeanUtil.copyProperties(userRpcService.findOneByUsercode(usercode), UserAuthDTO.class);
            }
            if (context.getTokenType().equals(OAuth2TokenType.ACCESS_TOKEN)) {
                setClaim(claims, DefaultClaimNames.ID, String.valueOf(userAuthDto.getId()));
                setClaim(claims, DefaultClaimNames.ORGANIZ_ID, String.valueOf(userAuthDto.getOrganizId()));
                setClaim(claims, DefaultClaimNames.TOP_ID, String.valueOf(userAuthDto.getTopId()));
                setClaim(claims, DefaultClaimNames.USER_NAME, userAuthDto.getUsername());
                Set<String> authorities = authorization.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toSet());
                setClaim(claims, authorityClaimName, authorities);
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
                    setClaim(claims, DefaultClaimNames.REAL_NAME_VERIFIED, String.valueOf(userAuthDto.getRealNameVerified()));
                }
                if (scopes.contains(DefaultOidcScopes.PHONE)) {
                    setClaim(claims, DefaultClaimNames.PHONE, userAuthDto.getPhone());
                    setClaim(claims, DefaultClaimNames.PHONE_VERIFIED, String.valueOf(userAuthDto.getPhoneVerified()));
                }
                if (scopes.contains(DefaultOidcScopes.EMAIL)) {
                    setClaim(claims, DefaultClaimNames.EMAIL, userAuthDto.getEmail());
                    setClaim(claims, DefaultClaimNames.EMAIL_VERIFIED, String.valueOf(userAuthDto.getEmailVerified()));
                }
                if (scopes.contains(DefaultOidcScopes.PROFILE)) {
                    setClaim(claims, DefaultClaimNames.ID, String.valueOf(userAuthDto.getId()));
                    setClaim(claims, DefaultClaimNames.ORGANIZ_ID, String.valueOf(userAuthDto.getOrganizId()));
                    setClaim(claims, DefaultClaimNames.TOP_ID, String.valueOf(userAuthDto.getTopId()));
                    setClaim(claims, DefaultClaimNames.USER_NAME, userAuthDto.getUsername());
                    setClaim(claims, DefaultClaimNames.SEX, String.valueOf(userAuthDto.getSex()));
                    setClaim(claims, DefaultClaimNames.AVATAR, userAuthDto.getAvatar());
                    setClaim(claims, DefaultClaimNames.WEBSITE, userAuthDto.getWebsite());
                    setClaim(claims, DefaultClaimNames.BIRTHDATE, DateTimeUtil.formatTime(userAuthDto.getBirthday(), DateTimeUtil.DATE_PATTERN));
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
