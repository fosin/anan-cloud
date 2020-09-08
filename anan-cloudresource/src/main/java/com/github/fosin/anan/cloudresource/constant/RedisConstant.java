package com.github.fosin.anan.cloudresource.constant;

/**
 * @author fosin
 * @date 2019/3/26
 */
public class RedisConstant {
    public static final String DELIMITER = ":";
    public static final String ANAN = "anan" + RedisConstant.DELIMITER;

    public static final String ANAN_AUTHSERVER = RedisConstant.ANAN + ServiceConstant.ANAN_AUTHSERVER + RedisConstant.DELIMITER;
    public static final String ANAN_PLATFORMSERVER = RedisConstant.ANAN + ServiceConstant.ANAN_PLATFORMSERVER + RedisConstant.DELIMITER;


    public static final String ANAN_USER = RedisConstant.ANAN + TableNameConstant.ANAN_USER + RedisConstant.DELIMITER;
    public static final String ANAN_USER_ROLE = RedisConstant.ANAN + TableNameConstant.ANAN_USER_ROLE + RedisConstant.DELIMITER;
    public static final String ANAN_USER_PERMISSION = RedisConstant.ANAN + TableNameConstant.ANAN_USER_PERMISSION + RedisConstant.DELIMITER;
    public static final String ANAN_USER_ALL_PERMISSIONS = RedisConstant.ANAN + TableNameConstant.ANAN_USER_ALL_PERMISSIONS + RedisConstant.DELIMITER;
    public static final String ANAN_ROLE_PERMISSION = RedisConstant.ANAN + TableNameConstant.ANAN_ROLE_PERMISSION + RedisConstant.DELIMITER;
    public static final String ANAN_ROLE = RedisConstant.ANAN + TableNameConstant.ANAN_ROLE + RedisConstant.DELIMITER;
    public static final String ANAN_PERMISSION = RedisConstant.ANAN + TableNameConstant.ANAN_PERMISSION + RedisConstant.DELIMITER;
    public static final String ANAN_ORGANIZATION = RedisConstant.ANAN + TableNameConstant.ANAN_ORGANIZATION + RedisConstant.DELIMITER;
    public static final String ANAN_ORGANIZATION_PERMISSION = RedisConstant.ANAN + TableNameConstant.ANAN_ORGANIZATION_PERMISSION + RedisConstant.DELIMITER;
    public static final String ANAN_DICTIONARY = RedisConstant.ANAN + TableNameConstant.ANAN_DICTIONARY + RedisConstant.DELIMITER;
    public static final String ANAN_DICTIONARY_DETAIL = RedisConstant.ANAN + TableNameConstant.ANAN_DICTIONARY_DETAIL + RedisConstant.DELIMITER;
    public static final String ANAN_PARAMETER = RedisConstant.ANAN + TableNameConstant.ANAN_PARAMETER + RedisConstant.DELIMITER;
    public static final String ANAN_VERSION = RedisConstant.ANAN + TableNameConstant.ANAN_VERSION + RedisConstant.DELIMITER;
    public static final String ANAN_VERSION_PERMISSION = RedisConstant.ANAN + TableNameConstant.ANAN_VERSION_PERMISSION + RedisConstant.DELIMITER;
    public static final String ANAN_VERSION_ROLE_PERMISSION = RedisConstant.ANAN + TableNameConstant.ANAN_VERSION_ROLE_PERMISSION + RedisConstant.DELIMITER;
    public static final String ANAN_VERSION_ROLE = RedisConstant.ANAN + TableNameConstant.ANAN_VERSION_ROLE + RedisConstant.DELIMITER;
}
