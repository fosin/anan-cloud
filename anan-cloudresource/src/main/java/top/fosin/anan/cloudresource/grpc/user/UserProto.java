// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: User.proto

package top.fosin.anan.cloudresource.grpc.user;

public final class UserProto {
  private UserProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_top_fosin_anan_cloudresource_grpc_user_OrganizReq_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_top_fosin_anan_cloudresource_grpc_user_OrganizReq_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_top_fosin_anan_cloudresource_grpc_user_TopOrganizReq_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_top_fosin_anan_cloudresource_grpc_user_TopOrganizReq_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_top_fosin_anan_cloudresource_grpc_user_UsercodeReq_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_top_fosin_anan_cloudresource_grpc_user_UsercodeReq_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_top_fosin_anan_cloudresource_grpc_user_UserIdReq_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_top_fosin_anan_cloudresource_grpc_user_UserIdReq_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_top_fosin_anan_cloudresource_grpc_user_UserIdsReq_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_top_fosin_anan_cloudresource_grpc_user_UserIdsReq_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_top_fosin_anan_cloudresource_grpc_user_UsersResp_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_top_fosin_anan_cloudresource_grpc_user_UsersResp_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_top_fosin_anan_cloudresource_grpc_user_UserResp_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_top_fosin_anan_cloudresource_grpc_user_UserResp_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\nUser.proto\022&top.fosin.anan.cloudresour" +
      "ce.grpc.user\032\037google/protobuf/timestamp." +
      "proto\"/\n\nOrganizReq\022\021\n\torganizId\030\001 \001(\003\022\016" +
      "\n\006status\030\002 \001(\005\".\n\rTopOrganizReq\022\r\n\005topId" +
      "\030\001 \001(\003\022\016\n\006status\030\002 \001(\005\"\037\n\013UsercodeReq\022\020\n" +
      "\010usercode\030\001 \001(\t\"\027\n\tUserIdReq\022\n\n\002id\030\001 \001(\003" +
      "\"\030\n\nUserIdsReq\022\n\n\002id\030\001 \003(\003\"K\n\tUsersResp\022" +
      ">\n\004user\030\001 \003(\01320.top.fosin.anan.cloudreso" +
      "urce.grpc.user.UserResp\"\361\005\n\010UserResp\022\020\n\010" +
      "usercode\030\001 \001(\t\022\020\n\010username\030\002 \001(\t\022\027\n\nfami" +
      "lyName\030\003 \001(\tH\000\210\001\001\022\027\n\nmiddleName\030\004 \001(\tH\001\210" +
      "\001\001\022\026\n\tgivenName\030\005 \001(\tH\002\210\001\001\022\025\n\010nickname\030\006" +
      " \001(\tH\003\210\001\001\022\036\n\021preferredUsername\030\007 \001(\tH\004\210\001" +
      "\001\022\030\n\020realNameVerified\030\010 \001(\005\022\r\n\005topId\030\t \001" +
      "(\003\022,\n\010birthday\030\n \001(\0132\032.google.protobuf.T" +
      "imestamp\022\013\n\003sex\030\013 \001(\005\022\022\n\005email\030\014 \001(\tH\005\210\001" +
      "\001\022\025\n\remailVerified\030\r \001(\005\022\022\n\005phone\030\016 \001(\tH" +
      "\006\210\001\001\022\025\n\rphoneVerified\030\017 \001(\005\022\016\n\006status\030\020 " +
      "\001(\005\022\023\n\006avatar\030\021 \001(\tH\007\210\001\001\022\024\n\007website\030\022 \001(" +
      "\tH\010\210\001\001\022.\n\nexpireTime\030\023 \001(\0132\032.google.prot" +
      "obuf.Timestamp\022\n\n\002id\030\024 \001(\003\022\021\n\torganizId\030" +
      "\025 \001(\003\022\020\n\010createBy\030\026 \001(\003\022.\n\ncreateTime\030\027 " +
      "\001(\0132\032.google.protobuf.Timestamp\022\020\n\010updat" +
      "eBy\030\030 \001(\003\022.\n\nupdateTime\030\031 \001(\0132\032.google.p" +
      "rotobuf.TimestampB\r\n\013_familyNameB\r\n\013_mid" +
      "dleNameB\014\n\n_givenNameB\013\n\t_nicknameB\024\n\022_p" +
      "referredUsernameB\010\n\006_emailB\010\n\006_phoneB\t\n\007" +
      "_avatarB\n\n\010_website2\367\004\n\013UserService\022t\n\013f" +
      "indOneById\0221.top.fosin.anan.cloudresourc" +
      "e.grpc.user.UserIdReq\0320.top.fosin.anan.c" +
      "loudresource.grpc.user.UserResp\"\000\022t\n\tlis" +
      "tByIds\0222.top.fosin.anan.cloudresource.gr" +
      "pc.user.UserIdsReq\0321.top.fosin.anan.clou" +
      "dresource.grpc.user.UsersResp\"\000\022|\n\021findO" +
      "neByUsercode\0223.top.fosin.anan.cloudresou" +
      "rce.grpc.user.UsercodeReq\0320.top.fosin.an" +
      "an.cloudresource.grpc.user.UserResp\"\000\022z\n" +
      "\017listByOrganizId\0222.top.fosin.anan.cloudr" +
      "esource.grpc.user.OrganizReq\0321.top.fosin" +
      ".anan.cloudresource.grpc.user.UsersResp\"" +
      "\000\022\201\001\n\023listAllChildByTopId\0225.top.fosin.an" +
      "an.cloudresource.grpc.user.TopOrganizReq" +
      "\0321.top.fosin.anan.cloudresource.grpc.use" +
      "r.UsersResp\"\000B5\n&top.fosin.anan.cloudres" +
      "ource.grpc.userB\tUserProtoP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf.TimestampProto.getDescriptor(),
        });
    internal_static_top_fosin_anan_cloudresource_grpc_user_OrganizReq_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_top_fosin_anan_cloudresource_grpc_user_OrganizReq_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_top_fosin_anan_cloudresource_grpc_user_OrganizReq_descriptor,
        new java.lang.String[] { "OrganizId", "Status", });
    internal_static_top_fosin_anan_cloudresource_grpc_user_TopOrganizReq_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_top_fosin_anan_cloudresource_grpc_user_TopOrganizReq_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_top_fosin_anan_cloudresource_grpc_user_TopOrganizReq_descriptor,
        new java.lang.String[] { "TopId", "Status", });
    internal_static_top_fosin_anan_cloudresource_grpc_user_UsercodeReq_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_top_fosin_anan_cloudresource_grpc_user_UsercodeReq_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_top_fosin_anan_cloudresource_grpc_user_UsercodeReq_descriptor,
        new java.lang.String[] { "Usercode", });
    internal_static_top_fosin_anan_cloudresource_grpc_user_UserIdReq_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_top_fosin_anan_cloudresource_grpc_user_UserIdReq_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_top_fosin_anan_cloudresource_grpc_user_UserIdReq_descriptor,
        new java.lang.String[] { "Id", });
    internal_static_top_fosin_anan_cloudresource_grpc_user_UserIdsReq_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_top_fosin_anan_cloudresource_grpc_user_UserIdsReq_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_top_fosin_anan_cloudresource_grpc_user_UserIdsReq_descriptor,
        new java.lang.String[] { "Id", });
    internal_static_top_fosin_anan_cloudresource_grpc_user_UsersResp_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_top_fosin_anan_cloudresource_grpc_user_UsersResp_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_top_fosin_anan_cloudresource_grpc_user_UsersResp_descriptor,
        new java.lang.String[] { "User", });
    internal_static_top_fosin_anan_cloudresource_grpc_user_UserResp_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_top_fosin_anan_cloudresource_grpc_user_UserResp_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_top_fosin_anan_cloudresource_grpc_user_UserResp_descriptor,
        new java.lang.String[] { "Usercode", "Username", "FamilyName", "MiddleName", "GivenName", "Nickname", "PreferredUsername", "RealNameVerified", "TopId", "Birthday", "Sex", "Email", "EmailVerified", "Phone", "PhoneVerified", "Status", "Avatar", "Website", "ExpireTime", "Id", "OrganizId", "CreateBy", "CreateTime", "UpdateBy", "UpdateTime", "FamilyName", "MiddleName", "GivenName", "Nickname", "PreferredUsername", "Email", "Phone", "Avatar", "Website", });
    com.google.protobuf.TimestampProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
