package top.fosin.anan.cloudresource.constant;

/**
 * @author fosin
 * @date 2019/3/28
 */
public class UrlPrefixConstant {
    public static final String API = "api";
    public static final String API_VERSION_NAME = "version";
    public static final String API_VERSION_VALUE = "2018-10-17";
    public static final String DEFAULT_VERSION_PARAM = API_VERSION_NAME + "=" + API_VERSION_VALUE;

    public static final String PARAMETER = API + "/parameter";
    public static final String PERMISSION = API + "/permission";
    public static final String ORGANIZATION = API + "/organiz";
    public static final String PAY = API + "/pay";
    public static final String PAY_INVOICE = PAY + "/invoice";
    public static final String PAY_DETAIL = PAY + "/detail";
    public static final String PAY_ORDER = PAY + "/order";

    public static final String USER = API + "/user";
    public static final String USER_ROLE = USER + "/role";
    public static final String USER_PERMISSION = USER + "/permission";
    public static final String ROLE = API + "/role";
    public static final String ROLE_USER = ROLE + "/user";
    public static final String ROLE_PERMISSION = ROLE + "/permission";
    public static final String DICTIONARY = API + "/dictionary";
    public static final String DICTIONARY_DETAIL = API + "/dictionarydetail";

    public static final String OAUTH_CLIENT = API + "/oauthclient";
    public static final String APPLICATION = API + "/application";
    public static final String SERVICE = API + "/service";

    public static final String VERSION = API + "/version";
    public static final String VERSION_ROLE = VERSION + "/role";

    public static final String INTERNATIONAL = API + "/international";
    public static final String INTERNATIONAL_CHARSET = INTERNATIONAL + "/charset";

}
