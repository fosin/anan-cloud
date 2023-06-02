/*
 * Copyright 2002-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package top.fosin.anan.cloudgateway.service;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.ReactiveOAuth2UserService;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.converter.ClaimConversionService;
import org.springframework.security.oauth2.core.converter.ClaimTypeConverter;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;
import top.fosin.anan.security.resource.AnanSecurityProperties;

import java.time.Instant;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * An implementation of an {@link ReactiveOAuth2UserService} that supports OpenID Connect
 * 1.0 Provider's.
 *
 * @author Rob Winch
 * @see ReactiveOAuth2UserService
 * @see OidcUserRequest
 * @see OidcUser
 * @see DefaultOidcUser
 * @see OidcUserInfo
 * @since 5.1
 */
public class CustomOidcReactiveOAuth2UserService implements ReactiveOAuth2UserService<OidcUserRequest, OidcUser> {

    private static final String INVALID_USER_INFO_RESPONSE_ERROR_CODE = "invalid_user_info_response";

    private static final Converter<Map<String, Object>, Map<String, Object>> DEFAULT_CLAIM_TYPE_CONVERTER = new ClaimTypeConverter(createDefaultClaimTypeConverters());

    private ReactiveOAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService;

    private Function<ClientRegistration, Converter<Map<String, Object>, Map<String, Object>>> claimTypeConverterFactory = (clientRegistration) -> DEFAULT_CLAIM_TYPE_CONVERTER;
    private final String authorityPrefix;
    private final String authorityClaimName;

    public CustomOidcReactiveOAuth2UserService(AnanSecurityProperties ananSecurityProperties) {
        AnanSecurityProperties.Oauth2ResourceServer resourceServer = ananSecurityProperties.getOauth2().getResourceServer();
        this.authorityPrefix = resourceServer.getAuthorityPrefix();
        this.authorityClaimName = resourceServer.getAuthorityClaimName();
        this.oauth2UserService = new CustomReactiveOAuth2UserService(ananSecurityProperties);
    }


    /**
     * Returns the default {@link Converter}'s used for type conversion of claim values
     * for an {@link OidcUserInfo}.
     *
     * @return a {@link Map} of {@link Converter}'s keyed by {@link StandardClaimNames
     * claim name}
     * @since 5.2
     */
    public static Map<String, Converter<Object, ?>> createDefaultClaimTypeConverters() {
        Converter<Object, ?> booleanConverter = getConverter(TypeDescriptor.valueOf(Boolean.class));
        Converter<Object, ?> instantConverter = getConverter(TypeDescriptor.valueOf(Instant.class));
        Map<String, Converter<Object, ?>> claimTypeConverters = new HashMap<>();
        claimTypeConverters.put(StandardClaimNames.EMAIL_VERIFIED, booleanConverter);
        claimTypeConverters.put(StandardClaimNames.PHONE_NUMBER_VERIFIED, booleanConverter);
        claimTypeConverters.put(StandardClaimNames.UPDATED_AT, instantConverter);
        return claimTypeConverters;
    }

    private static Converter<Object, ?> getConverter(TypeDescriptor targetDescriptor) {
        final TypeDescriptor sourceDescriptor = TypeDescriptor.valueOf(Object.class);
        return (source) -> ClaimConversionService.getSharedInstance().convert(source, sourceDescriptor, targetDescriptor);
    }

    @Override
    public Mono<OidcUser> loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        Assert.notNull(userRequest, "userRequest cannot be null");
        // @formatter:off
        return getUserInfo(userRequest)
                .map((userInfo) ->
                        new OidcUserAuthority(userRequest.getIdToken(), userInfo)
                )
                .defaultIfEmpty(new OidcUserAuthority(userRequest.getIdToken(), null))
                .map((authority) -> {
                    OidcUserInfo userInfo = authority.getUserInfo();
                    Map<String,Object> attrs = authority.getAttributes();
                    List<String> list = (List<String>) attrs.get(authorityClaimName);
                    Set<GrantedAuthority> authorities = new HashSet<>();
                    if (list != null) {
                        authorities = list.stream().map(s -> new SimpleGrantedAuthority(authorityPrefix + s)).collect(Collectors.toSet());
                    }
                    authorities.add(authority);
                    OAuth2AccessToken token = userRequest.getAccessToken();
                    for (String scope : token.getScopes()) {
                        authorities.add(new SimpleGrantedAuthority("SCOPE_" + scope));
                    }
                    String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                            .getUserInfoEndpoint().getUserNameAttributeName();
                    if (StringUtils.hasText(userNameAttributeName)) {
                        return new DefaultOidcUser(authorities, userRequest.getIdToken(), userInfo,
                                userNameAttributeName);
                    }
                    return new DefaultOidcUser(authorities, userRequest.getIdToken(), userInfo);
                });
        // @formatter:on
    }

    private boolean shouldRetrieveUserInfo(OidcUserRequest userRequest) {
        // Auto-disabled if UserInfo Endpoint URI is not provided
        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        if (ObjectUtils.isEmpty(clientRegistration.getProviderDetails().getUserInfoEndpoint().getUri())) {
            return false;
        }
        // The Claims requested by the profile, email, address, and phone scope values
        // are returned from the UserInfo Endpoint (as described in Section 5.3.2),
        // when a response_type value is used that results in an Access Token being
        // issued.
        // However, when no Access Token is issued, which is the case for the
        // response_type=id_token,
        // the resulting Claims are returned in the ID Token.
        // The Authorization Code Grant Flow, which is response_type=code, results in an
        // Access Token being issued.
        if (AuthorizationGrantType.AUTHORIZATION_CODE.equals(clientRegistration.getAuthorizationGrantType())) {
            // Return true if there is at least one match between the authorized scope(s)
            // and UserInfo scope(s)
            return CollectionUtils.containsAny(userRequest.getAccessToken().getScopes(), userRequest.getClientRegistration().getScopes());
        }
        return false;
    }

    private Mono<OidcUserInfo> getUserInfo(OidcUserRequest userRequest) {
        if (!shouldRetrieveUserInfo(userRequest)) {
            return Mono.empty();
        }
        // @formatter:off
        return this.oauth2UserService
                .loadUser(userRequest)
                .map(OAuth2User::getAttributes)
                .map((claims) -> convertClaims(claims, userRequest.getClientRegistration()))
                .map(OidcUserInfo::new)
                .doOnNext((userInfo) -> {
                    String subject = userInfo.getSubject();
                    if (subject == null || !subject.equals(userRequest.getIdToken().getSubject())) {
                        OAuth2Error oauth2Error = new OAuth2Error(INVALID_USER_INFO_RESPONSE_ERROR_CODE);
                        throw new OAuth2AuthenticationException(oauth2Error, oauth2Error.toString());
                    }
                });
        // @formatter:on
    }

    private Map<String, Object> convertClaims(Map<String, Object> claims, ClientRegistration clientRegistration) {
        Converter<Map<String, Object>, Map<String, Object>> claimTypeConverter = this.claimTypeConverterFactory.apply(clientRegistration);
        return (claimTypeConverter != null) ? claimTypeConverter.convert(claims) : DEFAULT_CLAIM_TYPE_CONVERTER.convert(claims);
    }

    public void setOauth2UserService(ReactiveOAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService) {
        Assert.notNull(oauth2UserService, "oauth2UserService cannot be null");
        this.oauth2UserService = oauth2UserService;
    }

    /**
     * Sets the factory that provides a {@link Converter} used for type conversion of
     * claim values for an {@link OidcUserInfo}. The default is {@link ClaimTypeConverter}
     * for all {@link ClientRegistration clients}.
     *
     * @param claimTypeConverterFactory the factory that provides a {@link Converter} used
     *                                  for type conversion of claim values for a specific {@link ClientRegistration
     *                                  client}
     * @since 5.2
     */
    public final void setClaimTypeConverterFactory(Function<ClientRegistration, Converter<Map<String, Object>, Map<String, Object>>> claimTypeConverterFactory) {
        Assert.notNull(claimTypeConverterFactory, "claimTypeConverterFactory cannot be null");
        this.claimTypeConverterFactory = claimTypeConverterFactory;
    }

}
