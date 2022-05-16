package top.fosin.anan.auth.config;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import top.fosin.anan.cloudresource.dto.UserDetail;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author fosin
 * @date 2020/8/9
 * @since 2.1.0
 */
@AllArgsConstructor
public class AnanUserAuthenticationConverter extends DefaultUserAuthenticationConverter {
    private final UserDetailsService userDetailsService;

    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        String name = authentication.getName();
        response.put(USERNAME, name);

        Object principal = authentication.getPrincipal();
        UserDetail userDetail;
        if (principal instanceof UserDetail) {
            userDetail = (UserDetail) principal;
        } else {
            //refresh_token默认不去调用userdetailService获取用户信息，这里我们手动去调用，得到 UserDetail
            UserDetails userDetails = userDetailsService.loadUserByUsername(name);
            userDetail = (UserDetail) userDetails;
        }

        response.put("user", userDetail.getUser());
        response.put("client", userDetail.getClient());
        response.put("username", userDetail.getUsername());
        response.put("password", userDetail.getUsername());
        response.put("accountNonExpired", userDetail.isAccountNonExpired());
        response.put("accountNonLocked", userDetail.isAccountNonLocked());
        response.put("credentialsNonExpired", userDetail.isCredentialsNonExpired());
        response.put("enabled", userDetail.isEnabled());

        if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
            response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
        }
        return response;
    }

}
