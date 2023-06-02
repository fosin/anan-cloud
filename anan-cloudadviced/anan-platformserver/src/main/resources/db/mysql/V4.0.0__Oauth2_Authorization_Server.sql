/*
IMPORTANT:
    If using PostgreSQL, update ALL columns defined with 'blob' to 'text',
    as PostgreSQL does not support the 'blob' data type.
*/

/*完善字段说明、数据类型为tinyint等*/
alter table anan_dictionary_detail
    change name code int unsigned not null comment '字典明细键，不能重复，字典内明细项唯一代码';

alter table anan_dictionary_detail
    change value name varchar(64) null comment '字典明细字面说明';

alter table anan_dictionary_detail
    modify dictionary_id int unsigned not null comment '取值于字典表anan_dictionary.id' after id;

alter table anan_dictionary_detail
    modify status tinyint not null comment '使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11';

alter table anan_dictionary_detail
    drop key idx_anan_dictionarydetail_dictionaryid_name;

alter table anan_dictionary_detail
    add constraint idx_anan_dictionarydetail_dictionaryid_code
        unique (dictionary_id, code);

alter table anan_service
    modify update_by int unsigned not null comment '更新人';

alter table anan_service
    modify create_by int unsigned not null comment '创建人';

alter table anan_international
    modify update_by int unsigned not null comment '更新人';

alter table anan_international
    modify update_time datetime not null comment '更新时间';

alter table anan_international
    modify create_by int unsigned not null comment '创建人';

alter table anan_international
    modify create_time datetime not null comment '创建时间';

alter table anan_international_charset
    modify update_by int unsigned not null comment '更新人';

alter table anan_international_charset
    modify update_time datetime not null comment '更新时间';

alter table anan_international_charset
    modify create_by int unsigned not null comment '创建人';

alter table anan_international_charset
    modify create_time datetime not null comment '创建时间';

alter table anan_international_charset
    modify status tinyint(1) default 0 not null comment '状态：0=启用，1=禁用' after charset;

alter table anan_user
    modify sex tinyint not null comment '使用状态：具体取值于字典表anan_dictionary.code=15';

alter table anan_user
    modify status tinyint not null comment '使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11';
alter table anan_role
    modify status tinyint not null comment '使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11';

alter table anan_permission
    modify type tinyint not null comment '权限类型：0=按钮、1=组件菜单，对应ur是前端组件、2=链接菜单，对应url是http(s)链接地址、3=目录菜单、4=系统模块，具体取值于字典表anan_dictionary.code=13，当权限类型是1、3、4：目录菜单时表示该节点不是一个叶子节点';

alter table anan_permission
    modify level tinyint not null comment '菜单层级';

alter table anan_permission
    modify sort smallint null comment '排序，用于显示数据时的顺序，数值越小越靠前';

alter table anan_permission
    modify status tinyint not null comment '使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11';

alter table anan_parameter
    modify type tinyint not null comment '参数分类：具体取值于字典表anan_dictionary.code=10';

alter table anan_parameter
    modify status tinyint not null comment '参数状态：0=正常状态、1=修改状态、2=删除状态';

alter table anan_organization
    modify level smallint not null comment '深度';

alter table anan_organization
    modify status tinyint not null comment '使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11';

alter table anan_version_role
    modify status tinyint not null comment '使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11';

alter table anan_user_permission
    modify add_mode tinyint not null comment '补充方式：0=增加权限、1=删除权限';

/*完善用户信息*/
alter table anan_user
    add real_name_verified tinyint default 0 not null comment '实名认证标志' after username;
alter table anan_user
    add email_verified tinyint default 0 not null comment '邮箱认证标志' after email;
alter table anan_user
    add phone_verified tinyint default 0 not null comment '手机验证标志' after phone;
alter table anan_user
    add family_name varchar(32) default null comment '姓氏' after username;
alter table anan_user
    add middle_name varchar(64) default null comment '中间名' after family_name;
alter table anan_user
    add given_name varchar(32) default null comment '名字' after middle_name;
alter table anan_user
    add nickname varchar(32) default null comment '昵称' after given_name;
alter table anan_user
    add preferred_username varchar(32) default null comment '希望被称呼的名字' after nickname;
alter table anan_user
    add website varchar(256) default null comment '网站地址' after preferred_username;

CREATE TABLE anan_user_address
(
    id             int unsigned       NOT NULL COMMENT '主键序号',
    user_id        int unsigned       NOT NULL COMMENT '用户序号',
    type           tinyint            NOT NULL COMMENT '地址类型',
    address        varchar(128)       NOT NULL comment '完整详细地址',
    country        smallint           NOT NULL comment '国家',
    region         smallint           NOT NULL comment '省份',
    locality       mediumint unsigned NOT NULL comment '城市',
    locale         int unsigned       NOT NULL comment '区域',
    street_address int unsigned       NOT NULL comment '街道地址',
    formatted      varchar(32)        NOT NULL comment '具体地址',
    zoneinfo       varchar(16)        NOT NULL comment '时区',
    postal_code    varchar(24)        NOT NULL comment '邮编',
    create_by      int unsigned       NOT NULL COMMENT '创建人',
    create_time    datetime           NOT NULL COMMENT '创建日期',
    update_by      int unsigned       NOT NULL COMMENT '修改人',
    update_time    datetime           NOT NULL COMMENT '修改日期',
    PRIMARY KEY (id)
) DEFAULT CHARSET = utf8mb4
  collate = utf8mb4_general_ci COMMENT ='用户地址表';

CREATE TABLE anan_user_certificate
(
    id          int unsigned     NOT NULL COMMENT '主键序号',
    user_id     int unsigned     NOT NULL COMMENT '用户序号',
    type        tinyint          NOT NULL COMMENT '证件类型',
    id_number   varchar(64)      NOT NULL COMMENT '证件号码',
    create_time datetime         NOT NULL COMMENT '创建日期',
    update_time datetime         NOT NULL COMMENT '修改日期',
    PRIMARY KEY (id)
) DEFAULT CHARSET = utf8mb4
  collate = utf8mb4_general_ci COMMENT ='用户证件表';

CREATE TABLE anan_user_thirdparty
(
    id            int unsigned     NOT NULL COMMENT '主键序号',
    user_id       int unsigned     NOT NULL COMMENT '用户序号',
    type          tinyint          NOT NULL COMMENT '认证类型',
    third_account varchar(64)      NOT NULL COMMENT '第三方账号',
    create_time   datetime         NOT NULL COMMENT '创建日期',
    PRIMARY KEY (id)
) DEFAULT CHARSET = utf8mb4
  collate = utf8mb4_general_ci COMMENT ='用户第三方认证表';

CREATE TABLE oauth2_authorization
(
    id                            varchar(100) NOT NULL COMMENT '主键序号',
    registered_client_id          varchar(100) NOT NULL COMMENT '客户端序号',
    principal_name                varchar(200) NOT NULL COMMENT '委托名称',
    authorization_grant_type      varchar(100) NOT NULL COMMENT '授权类型',
    attributes                    blob              DEFAULT NULL COMMENT '属性',
    /*Spring authorization Server 0.4.0在增加字段authorized_scopes */
    authorized_scopes             varchar(1000)     DEFAULT NULL comment '授权的作用域',
    state                         varchar(500)      DEFAULT NULL COMMENT '状态',
    authorization_code_value      blob              DEFAULT NULL COMMENT '授权码值',
    authorization_code_issued_at  timestamp    NULL DEFAULT NULL COMMENT '授权码发布日期',
    authorization_code_expires_at timestamp    NULL DEFAULT NULL COMMENT '授权码过期日期',
    authorization_code_metadata   blob              DEFAULT NULL COMMENT '授权码扩展属性',
    access_token_value            blob              DEFAULT NULL COMMENT '访问令牌值',
    access_token_issued_at        timestamp    NULL DEFAULT NULL COMMENT '访问令牌发布日期',
    access_token_expires_at       timestamp    NULL DEFAULT NULL COMMENT '访问令牌过期日期',
    access_token_metadata         blob              DEFAULT NULL COMMENT '访问令牌扩展属性',
    access_token_type             varchar(100)      DEFAULT NULL COMMENT '访问令牌类型',
    access_token_scopes           varchar(1000)     DEFAULT NULL COMMENT '访问令牌作用域',
    oidc_id_token_value           blob              DEFAULT NULL COMMENT 'oidc令牌值',
    oidc_id_token_issued_at       timestamp    NULL DEFAULT NULL COMMENT 'oidc令牌发布日期',
    oidc_id_token_expires_at      timestamp    NULL DEFAULT NULL COMMENT 'oidc令牌过期日期',
    oidc_id_token_metadata        blob              DEFAULT NULL COMMENT 'oidc令牌扩展属性',
    refresh_token_value           blob              DEFAULT NULL COMMENT '刷新令牌值',
    refresh_token_issued_at       timestamp    NULL DEFAULT NULL COMMENT '刷新令牌发布日期',
    refresh_token_expires_at      timestamp    NULL DEFAULT NULL COMMENT '刷新令牌过期日期',
    refresh_token_metadata        blob              DEFAULT NULL COMMENT '刷新令牌扩展属性',
    PRIMARY KEY (id)
) DEFAULT CHARSET = utf8mb4
  collate = utf8mb4_general_ci COMMENT ='OAUTH2认证信息表';

CREATE TABLE oauth2_registered_client
(
    id                            varchar(100)  NOT NULL COMMENT '主键序号',
    client_id                     varchar(100)  NOT NULL COMMENT '客户端序号',
    client_id_issued_at           timestamp          DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '发布日期',
    client_secret                 varchar(200)       DEFAULT NULL COMMENT '客户端密钥',
    client_secret_expires_at      timestamp     NULL DEFAULT NULL COMMENT '过期时间',
    client_name                   varchar(200)  NOT NULL COMMENT '客户端名称',
    client_authentication_methods varchar(1000) NOT NULL COMMENT '认证方法',
    authorization_grant_types     varchar(1000) NOT NULL COMMENT '认证类型',
    redirect_uris                 varchar(1000)      DEFAULT NULL COMMENT '跳转地址',
    scopes                        varchar(1000) NOT NULL COMMENT '作用域',
    client_settings               varchar(2000) NOT NULL COMMENT '客户端设置',
    token_settings                varchar(2000) NOT NULL COMMENT '令牌设置',
    PRIMARY KEY (id)
) DEFAULT CHARSET = utf8mb4
  collate = utf8mb4_general_ci COMMENT ='OAUTH2认证客户端注册表';

CREATE TABLE oauth2_authorization_consent
(
    registered_client_id varchar(100)  NOT NULL COMMENT '客户端序号',
    principal_name       varchar(200)  NOT NULL COMMENT '委托名称',
    authorities          varchar(1000) NOT NULL COMMENT '授权信息',
    PRIMARY KEY (registered_client_id, principal_name)
) DEFAULT CHARSET = utf8mb4
  collate = utf8mb4_general_ci COMMENT ='OAUTH2认证信息对照表';

alter table oauth_approvals
    modify lastModifiedAt TIMESTAMP default current_timestamp() not null comment '修改时间';

alter table oauth_approvals
    add primary key (userId, clientId);

alter table oauth_code
    add primary key (code);

alter table oauth_refresh_token
    add primary key (token_id);

INSERT INTO oauth2_registered_client (id, client_id, client_id_issued_at, client_secret,
                                      client_secret_expires_at, client_name,
                                      client_authentication_methods, authorization_grant_types,
                                      redirect_uris, scopes, client_settings, token_settings)
VALUES ('b36960d3-0e9f-4442-a6f7-0243c0b6a407', 'webApp', '2023-02-09 10:10:18',
        '{bcrypt}$2a$10$xKfDcbOc1Ibh0VRWRIsQ4O3Vk9JxbF/30Wdz.e2hBNAAQKR5UziIK', null, 'webApp', 'client_secret_basic',
        'refresh_token,client_credentials,password,authorization_code,implicit',
        'https://exam.assc.pro:11011/auth/swagger-ui/index.html,https://exam.assc.pro:11011/login/oauth2/code/anan,http://127.0.0.1:6140/auth/swagger-ui/index.html,http://100.100.1.198:6140/auth/swagger-ui/index.html,https://exam.assc.pro:11011/gateway/swagger-ui/index.html',
        'address,phone,openid,profile,email,idno',
        '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":true}',
        '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",86400.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",259200.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000]}');

/*修复权限表数据，path字段由于批量替换最后错误变成/api，应该是/*才对 */
update anan_permission
set path=concat(left(path, length(path) - 3), '*')
where path like '%/api';

/*修复权限表数据，path机构相关的路径由organization修改成organiz */
update anan_permission
set path=replace(path, 'organization', 'organiz')
where id in (4, 5, 6);

/*修复权限表数据，由于anan-data变更之后根据主键查询默认只支持GET方法，这里同步修复数据 */
update anan_permission
set method='GET',
    update_time = current_timestamp
where type = 5
  and method in ('GET,POST', 'POST,GET')
  and path like '%/*';

