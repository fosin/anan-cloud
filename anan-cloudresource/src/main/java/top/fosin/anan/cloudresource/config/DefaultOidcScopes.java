/*
 * Copyright 2002-2017 the original author or authors.
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

package top.fosin.anan.cloudresource.config;

import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;

/**
 * The scope values defined by the OpenID Connect Core 1.0 specification that can be used
 * to request {@link StandardClaimNames claims}.
 * <p>
 * The scope(s) associated to an {@link OAuth2AccessToken} determine what claims
 * (resources) will be available when they are used to access OAuth 2.0 Protected
 * Endpoints, such as the UserInfo Endpoint.
 *
 * @author Joe Grandja
 * @author fosin
 * @date 2023-1-25
 * @see StandardClaimNames
 * @see <a target="_blank" href=
 * "https://openid.net/specs/openid-connect-core-1_0.html#ScopeClaims">Requesting Claims
 * using Scope Values</a>
 * @since 3.3.0、spring security 5.0
 */
public interface DefaultOidcScopes {

    /**
     * OpenID Connect 的ID.
     */
    String OPENID = "openid";

    /**
     * 个人信息
     */
    String PROFILE = "profile";

    /**
     * 电子邮箱
     */
    String EMAIL = "email";

    /**
     * 所有地址
     */
    String ADDRESS = "address";

    /**
     * 手机号码
     */
    String PHONE = "phone";

    /**
     * 证件号码
     */
    String ID_NO = "idno";

}
