package com.github.fosin.cdp.auth.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Description
 * @author fosin
 */
public class CdpUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

    public CdpUsernamePasswordAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
