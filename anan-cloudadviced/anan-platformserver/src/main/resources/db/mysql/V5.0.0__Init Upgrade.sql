/*
IMPORTANT:
    If using PostgreSQL, update ALL columns defined with 'blob' to 'text',
    as PostgreSQL does not support the 'blob' data type.
*/
ALTER TABLE oauth2_authorization ADD user_code_value blob DEFAULT NULL COMMENT '用户编码值';
ALTER TABLE oauth2_authorization ADD user_code_issued_at timestamp DEFAULT NULL COMMENT '用户编码发生时间';
ALTER TABLE oauth2_authorization ADD user_code_expires_at timestamp DEFAULT NULL COMMENT '用户编码过期时间';
ALTER TABLE oauth2_authorization ADD user_code_metadata blob DEFAULT NULL COMMENT '用户编码元信息';
ALTER TABLE oauth2_authorization ADD device_code_value blob DEFAULT NULL COMMENT '设备编码值';
ALTER TABLE oauth2_authorization ADD device_code_issued_at timestamp DEFAULT NULL COMMENT '设备编码发生时间';
ALTER TABLE oauth2_authorization ADD device_code_expires_at timestamp DEFAULT NULL COMMENT '设备编码过期时间';
ALTER TABLE oauth2_authorization ADD device_code_metadata blob DEFAULT NULL COMMENT '设备编码元信息';
ALTER TABLE oauth2_registered_client ADD post_logout_redirect_uris varchar(1000) DEFAULT NULL comment '延迟退出登陆调整地址' after redirect_uris;

