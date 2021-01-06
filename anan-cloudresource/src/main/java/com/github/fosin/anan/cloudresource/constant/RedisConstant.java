package com.github.fosin.anan.cloudresource.constant;

/**
 * @author fosin
 * @date 2019/3/26
 */
public class RedisConstant {
    public static final String DELIMITER = ":";
    public static final String ALL = "all" + DELIMITER;
    public static final String ONE = "one" + DELIMITER;
    public static final String ANAN = "anan" + RedisConstant.DELIMITER;
    public static final String ANAN_TABLE = ANAN + "table" + RedisConstant.DELIMITER;
    public static final String ANAN_AUTHSERVER = ANAN_TABLE + ServiceConstant.ANAN_AUTHSERVER + RedisConstant.DELIMITER;
    public static final String ANAN_PLATFORMSERVER = ANAN_TABLE + ServiceConstant.ANAN_PLATFORMSERVER + RedisConstant.DELIMITER;


    public static final String ANAN_USER = ANAN_TABLE + TableNameConstant.ANAN_USER + RedisConstant.DELIMITER;
    public static final String ANAN_USER_ROLE = ANAN_TABLE + TableNameConstant.ANAN_USER_ROLE + RedisConstant.DELIMITER;
    public static final String ANAN_USER_PERMISSION = ANAN_TABLE + TableNameConstant.ANAN_USER_PERMISSION + RedisConstant.DELIMITER;
    public static final String ANAN_USER_ALL_PERMISSIONS = ANAN_TABLE + TableNameConstant.ANAN_USER_ALL_PERMISSIONS + RedisConstant.DELIMITER;
    public static final String ANAN_ROLE_PERMISSION = ANAN_TABLE + TableNameConstant.ANAN_ROLE_PERMISSION + RedisConstant.DELIMITER;
    public static final String ANAN_ROLE = ANAN_TABLE + TableNameConstant.ANAN_ROLE + RedisConstant.DELIMITER;
    public static final String ANAN_PERMISSION = ANAN_TABLE + TableNameConstant.ANAN_PERMISSION + RedisConstant.DELIMITER;
    public static final String ANAN_ORGANIZATION = ANAN_TABLE + TableNameConstant.ANAN_ORGANIZATION + RedisConstant.DELIMITER;
    public static final String ANAN_ORGANIZATION_PERMISSION = ANAN_TABLE + TableNameConstant.ANAN_ORGANIZATION_PERMISSION + RedisConstant.DELIMITER;
    public static final String ANAN_DICTIONARY = ANAN_TABLE + TableNameConstant.ANAN_DICTIONARY + RedisConstant.DELIMITER;
    public static final String ANAN_DICTIONARY_DETAIL = ANAN_TABLE + TableNameConstant.ANAN_DICTIONARY_DETAIL + RedisConstant.DELIMITER;
    public static final String ANAN_PARAMETER = ANAN_TABLE + TableNameConstant.ANAN_PARAMETER + RedisConstant.DELIMITER;
    public static final String ANAN_VERSION = ANAN_TABLE + TableNameConstant.ANAN_VERSION + RedisConstant.DELIMITER;
    public static final String ANAN_VERSION_PERMISSION = ANAN_TABLE + TableNameConstant.ANAN_VERSION_PERMISSION + RedisConstant.DELIMITER;
    public static final String ANAN_VERSION_ROLE_PERMISSION = ANAN_TABLE + TableNameConstant.ANAN_VERSION_ROLE_PERMISSION + RedisConstant.DELIMITER;
    public static final String ANAN_VERSION_ROLE = ANAN_TABLE + TableNameConstant.ANAN_VERSION_ROLE + RedisConstant.DELIMITER;
    public static final String ANAN_SERVICE = ANAN_TABLE + TableNameConstant.ANAN_SERVICE + RedisConstant.DELIMITER;

    public static final String ANAN_INTERNATIONAL = ANAN_TABLE + TableNameConstant.ANAN_INTERNATIONAL + RedisConstant.DELIMITER;
    public static final String ANAN_INTERNATIONAL_STATUS = ANAN_INTERNATIONAL + "status" + RedisConstant.DELIMITER;
    public static final String ANAN_INTERNATIONAL_CODE = ANAN_INTERNATIONAL + "code" + RedisConstant.DELIMITER;
    public static final String ANAN_INTERNATIONAL_CHARSET = ANAN_TABLE + TableNameConstant.ANAN_INTERNATIONAL_CHARSET + RedisConstant.DELIMITER;
}
