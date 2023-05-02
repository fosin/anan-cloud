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

delete from flyway_schema_history where version ='4.0.0' and description='Oauth2 Authorization Server';
