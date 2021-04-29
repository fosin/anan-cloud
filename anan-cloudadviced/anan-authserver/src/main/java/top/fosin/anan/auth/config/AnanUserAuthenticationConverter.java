package top.fosin.anan.auth.config;

import top.fosin.anan.cloudresource.dto.AnanUserDetail;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

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
        AnanUserDetail ananUserDetail;
        if (principal instanceof AnanUserDetail) {
            ananUserDetail = (AnanUserDetail) principal;
        } else {
            //refresh_token默认不去调用userdetailService获取用户信息，这里我们手动去调用，得到 AnanUserDetail
            UserDetails userDetails = userDetailsService.loadUserByUsername(name);
            ananUserDetail = (AnanUserDetail) userDetails;
        }

        response.put("user", ananUserDetail.getUser());
        response.put("client", ananUserDetail.getAnanClient());
        response.put("username", ananUserDetail.getUsername());
        response.put("password", ananUserDetail.getUsername());
        response.put("accountNonExpired", ananUserDetail.isAccountNonExpired());
        response.put("accountNonLocked", ananUserDetail.isAccountNonLocked());
        response.put("credentialsNonExpired", ananUserDetail.isCredentialsNonExpired());
        response.put("enabled", ananUserDetail.isEnabled());

        if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
            response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
        }
        return response;
    }

}
