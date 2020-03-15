package com.github.fosin.anan.auth.config;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * DescriptionThe type Custom authentication provider.
 *
 * @author fosin
 */
//@Component
@Slf4j
public class AnanAuthenticationProvider implements AuthenticationProvider {
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    public AnanAuthenticationProvider(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

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
//        Object details = authentication.getDetails();
//        if (details instanceof HashMap) {
//            HashMap<String, Object> map = (HashMap<String, Object>) details;
//            String randomStr = map.get("randomStr")+"";
//            String code = map.get("code")+"";
//            if (!randomStr.equals(code)) {
//                throw new BadCredentialsException("验证码错误");
//            }
//
//        }

        String password = credentials == null ? null : credentials.toString();

        log.info("start validate user {} login", username);

        // 通过用户名和密码校验，如果校验不通过会抛出 AuthenticationException
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
            throw new UsernameNotFoundException("Invalid username/password");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Bad password");
        }


        return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
