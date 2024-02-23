/*测试时用于回滚flyway发布的版本：V4.0.0__Oauth2_Authorization_Server.sql*/
alter table anan_user drop column family_name;
alter table anan_user drop column middle_name;
alter table anan_user drop column given_name;
alter table anan_user drop column nickname;
alter table anan_user drop column preferred_username;
alter table anan_user drop column real_name_verified;
alter table anan_user drop column email_verified;
alter table anan_user drop column phone_verified;
alter table anan_user drop column website;

drop table anan_user_address;
drop table anan_user_certificate;
drop table anan_user_thirdparty;

alter table oauth_approvals drop primary key;
alter table oauth_code drop primary key;
alter table oauth_refresh_token drop primary key;

drop table oauth2_authorization;
drop table oauth2_authorization_consent;
drop table oauth2_registered_client;

alter table anan_dictionary_detail
    change name value varchar(64) null comment '字典明细字面说明';
alter table anan_dictionary_detail
    change code name int unsigned not null comment '字典明细键，不能重复，字典内明细项唯一代码';

alter table anan_dictionary_detail
    drop key idx_anan_dictionarydetail_dictionaryid_code;

alter table anan_dictionary_detail
    add constraint idx_anan_dictionarydetail_dictionaryid_name
        unique (dictionary_id, name);

delete from flyway_schema_history where version ='4.0.0' and description='Oauth2 Authorization Server';

# V5.0.0__Upgrade.sql回滚SQL
alter table oauth2_authorization drop column user_code_value;
alter table oauth2_authorization drop column user_code_issued_at;
alter table oauth2_authorization drop column user_code_expires_at;
alter table oauth2_authorization drop column user_code_metadata;
alter table oauth2_authorization drop column device_code_value;
alter table oauth2_authorization drop column device_code_issued_at;
alter table oauth2_authorization drop column device_code_expires_at;
alter table oauth2_authorization drop column device_code_metadata;
alter table oauth2_registered_client drop column post_logout_redirect_uris;
