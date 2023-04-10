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

package top.fosin.anan.auth.config;

/**
 * The names of the &quot;Standard Claims&quot; defined by the OpenID Connect Core 1.0
 * specification that can be returned either in the UserInfo Response or the ID Token.
 *
 * @author Joe Grandja
 * @author fosin
 * @date 2023-1-25
 * @see <a target="_blank" href=
 * "https://openid.net/specs/openid-connect-core-1_0.html#StandardClaims">Standard
 * Claims</a>
 * @see <a target="_blank" href=
 * "https://openid.net/specs/openid-connect-core-1_0.html#UserInfoResponse">UserInfo
 * Response</a>
 * @see <a target="_blank" href=
 * "https://openid.net/specs/openid-connect-core-1_0.html#IDToken">ID Token</a>
 * @since 3.3.0、spring security 5.0
 */
public interface DefaultClaimNames {

    /**
     * 用户唯一内部ID
     */
    String ID = "id";

    /**
     * 唯一标识，一般为用户账号
     */
    String SUB = "sub";

    /**
     * 姓名
     */
    String USER_NAME = "username";

    /**
     * 实名验证
     */
    String REAL_NAME_VERIFIED = "real_name_verified";

    /**
     * 身份证件证号
     */
    String ID_NO = "id_no";

    /**
     * 身份证件证号类型（身份证号、驾驶证号、社保证号、军人证号等）
     */
    String ID_NO_TYPE = "id_no_type";

    /**
     * 名字
     */
    String GIVEN_NAME = "given_name";

    /**
     * 姓氏
     */
    String FAMILY_NAME = "family_name";

    /**
     * 中间名
     */
    String MIDDLE_NAME = "middle_name";

    /**
     * 昵称
     */
    String NICKNAME = "nickname";

    /**
     * 希望被称呼的名字
     */
    String PREFERRED_USERNAME = "preferred_username";

    /**
     * 头像
     */
    String AVATAR = "avatar";

    /**
     * 个人网站链接
     */
    String WEBSITE = "website";

    /**
     * 电子邮箱
     */
    String EMAIL = "email";

    /**
     * 邮箱认证标志
     */
    String EMAIL_VERIFIED = "email_verified";

    /**
     * 性别
     */
    String SEX = "sex";

    /**
     * 生日
     */
    String BIRTHDATE = "birthdate";


    /**
     * 手机号
     */
    String PHONE = "phone";

    /**
     * 验证手机号
     */
    String PHONE_VERIFIED = "phone_verified";

    /**
     * 详细地址
     */
    String ADDRESS = "address";

    /**
     * 具体地址
     */
    String FORMATTED = "formatted";

    /**
     * 街道地址
     */
    String STREET_ADDRESS = "street_address";

    /**
     * 区域
     */
    String LOCALE = "locale";

    /**
     * 城市
     */
    String LOCALITY = "locality";

    /**
     * 省份
     */
    String REGION = "region";

    /**
     * 国家
     */
    String COUNTRY = "country";

    /**
     * 邮编
     */
    String POSTAL_CODE = "postal_code";

    /**
     * 时区
     */
    String ZONEINFO = "zoneinfo";

    /**
     * 更新时间
     */
    String UPDATE_TIME = "update_time";

    /**
     * 更新人
     */
    String UPDATE_BY = "update_by";
}
