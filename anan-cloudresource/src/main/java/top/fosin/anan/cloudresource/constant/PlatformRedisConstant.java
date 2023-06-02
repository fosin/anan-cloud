package top.fosin.anan.cloudresource.constant;

/**
 * @author fosin
 * @date 2019/3/26
 */
public class PlatformRedisConstant {
    public static final String DELIMITER = ":";
    public static final String ALL = "all";
    public static final String ONE = "one";
    public static final String ANAN = "anan";
    public static final String ANAN_TABLE = ANAN + DELIMITER + "table";
    public static final String ANAN_AUTHSERVER =
            ANAN_TABLE + DELIMITER + ServiceConstant.ANAN_AUTHSERVER;
    public static final String ANAN_PLATFORMSERVER =
            ANAN_TABLE + DELIMITER + ServiceConstant.ANAN_PLATFORMSERVER;

    public static final String ANAN_USER = ANAN_TABLE + DELIMITER + TableNameConstant.ANAN_USER;

    public static final String ANAN_USER_USERCODE = ANAN_USER + DELIMITER + "usercode";
    public static final String ANAN_USER_ROLE = ANAN_TABLE + DELIMITER + TableNameConstant.ANAN_USER_ROLE;
    public static final String ANAN_USER_PERMISSION = ANAN_TABLE + DELIMITER + TableNameConstant.ANAN_USER_PERMISSION;
    public static final String ANAN_USER_ALL_PERMISSIONS = ANAN_TABLE + DELIMITER + TableNameConstant.ANAN_USER_ALL_PERMISSIONS;
    public static final String ANAN_USER_PERMISSION_TREE = ANAN_USER_PERMISSION + DELIMITER + "tree";
    public static final String ANAN_ROLE_PERMISSION = ANAN_TABLE + DELIMITER + TableNameConstant.ANAN_ROLE_PERMISSION;
    public static final String ANAN_ROLE = ANAN_TABLE + DELIMITER + TableNameConstant.ANAN_ROLE;
    public static final String ANAN_PERMISSION = ANAN_TABLE + DELIMITER + TableNameConstant.ANAN_PERMISSION;
    public static final String ANAN_ORGANIZATION = ANAN_TABLE + DELIMITER + TableNameConstant.ANAN_ORGANIZATION;
    public static final String ANAN_ORGANIZATION_PERMISSION = ANAN_TABLE + DELIMITER + TableNameConstant.ANAN_ORGANIZATION_PERMISSION;
    public static final String ANAN_DICTIONARY = ANAN_TABLE + DELIMITER + TableNameConstant.ANAN_DICTIONARY;
    public static final String ANAN_DICTIONARY_DETAIL = ANAN_TABLE + DELIMITER + TableNameConstant.ANAN_DICTIONARY_DETAIL;
    public static final String ANAN_PARAMETER = ANAN_TABLE + DELIMITER + TableNameConstant.ANAN_PARAMETER;
    public static final String ANAN_VERSION = ANAN_TABLE + DELIMITER + TableNameConstant.ANAN_VERSION;
    public static final String ANAN_VERSION_PERMISSION = ANAN_TABLE + DELIMITER + TableNameConstant.ANAN_VERSION_PERMISSION;
    public static final String ANAN_VERSION_ROLE_PERMISSION = ANAN_TABLE + DELIMITER + TableNameConstant.ANAN_VERSION_ROLE_PERMISSION;
    public static final String ANAN_VERSION_ROLE = ANAN_TABLE + DELIMITER + TableNameConstant.ANAN_VERSION_ROLE;
    public static final String ANAN_SERVICE = ANAN_TABLE + DELIMITER + TableNameConstant.ANAN_SERVICE;

    public static final String ANAN_INTERNATIONAL = ANAN_TABLE + DELIMITER + TableNameConstant.ANAN_INTERNATIONAL;
    public static final String ANAN_INTERNATIONAL_STATUS = ANAN_INTERNATIONAL + DELIMITER + "status";
    public static final String ANAN_INTERNATIONAL_CODE = ANAN_INTERNATIONAL + DELIMITER + "code";
    public static final String ANAN_INTERNATIONAL_CHARSET = ANAN_TABLE + DELIMITER + TableNameConstant.ANAN_INTERNATIONAL_CHARSET;
    public static final String ANAN_INTERNATIONAL_CHARSET_ALL =
            ANAN_INTERNATIONAL_CHARSET + DELIMITER + ALL;
}
