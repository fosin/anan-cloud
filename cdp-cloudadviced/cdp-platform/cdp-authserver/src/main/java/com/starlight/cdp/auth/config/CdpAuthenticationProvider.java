package com.starlight.cdp.auth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * DescriptionThe type Custom authentication provider.
 *
 * @author fosin
 */
//@Component
@Slf4j
public class CdpAuthenticationProvider implements AuthenticationProvider {


    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Validate user info is correct form database
     *
     * @param authentication
     * @return
     */
    @Override
    public Authentication authenticate(Authentication authentication) {
        String username = authentication.getName();
        Object credentials = authentication.getCredentials();
        Object details = authentication.getDetails();
        if (details instanceof HashMap) {
            HashMap<String, Object> map = (HashMap<String, Object>) details;
            String randomStr = map.get("randomStr")+"";
            String code = map.get("code")+"";
            if (!randomStr.equals(code)) {
                throw new BadCredentialsException("验证码错误");
            }

        }
        String password = credentials == null ? null : credentials.toString();

        log.info("start validate user {} login", username);

        // 通过用户名和密码校验，如果校验不通过会抛出 AuthenticationException
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}