// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: DicDetail.proto

package top.fosin.anan.cloudresource.grpc.dicdetail;

public final class DicDetailProto {
  private DicDetailProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_top_fosin_anan_cloudresource_grpc_dicdetail_DicIdReq_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_top_fosin_anan_cloudresource_grpc_dicdetail_DicIdReq_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_top_fosin_anan_cloudresource_grpc_dicdetail_DicDetailReq_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_top_fosin_anan_cloudresource_grpc_dicdetail_DicDetailReq_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_top_fosin_anan_cloudresource_grpc_dicdetail_DicDetailsResp_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_top_fosin_anan_cloudresource_grpc_dicdetail_DicDetailsResp_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_top_fosin_anan_cloudresource_grpc_dicdetail_DicDetailResp_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_top_fosin_anan_cloudresource_grpc_dicdetail_DicDetailResp_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\017DicDetail.proto\022+top.fosin.anan.cloudr" +
      "esource.grpc.dicdetail\032\037google/protobuf/" +
      "timestamp.proto\" \n\010DicIdReq\022\024\n\014dictionar" +
      "yId\030\001 \001(\003\"2\n\014DicDetailReq\022\024\n\014dictionaryI" +
      "d\030\001 \001(\003\022\014\n\004code\030\002 \001(\003\"_\n\016DicDetailsResp\022" +
      "M\n\tdicDetail\030\001 \003(\0132:.top.fosin.anan.clou" +
      "dresource.grpc.dicdetail.DicDetailResp\"\343" +
      "\002\n\rDicDetailResp\022\n\n\002id\030\001 \001(\003\022\024\n\014dictiona" +
      "ryId\030\002 \001(\003\022\014\n\004code\030\003 \001(\003\022\014\n\004name\030\004 \001(\t\022\014" +
      "\n\004sort\030\005 \001(\005\022\016\n\006status\030\006 \001(\005\022\022\n\005scode\030\007 " +
      "\001(\tH\000\210\001\001\022\022\n\005scope\030\010 \001(\tH\001\210\001\001\022\014\n\004used\030\t \001" +
      "(\005\022\030\n\013description\030\n \001(\tH\002\210\001\001\022\020\n\010createBy" +
      "\030\013 \001(\003\022.\n\ncreateTime\030\014 \001(\0132\032.google.prot" +
      "obuf.Timestamp\022\020\n\010updateBy\030\r \001(\003\022.\n\nupda" +
      "teTime\030\016 \001(\0132\032.google.protobuf.Timestamp" +
      "B\010\n\006_scodeB\010\n\006_scopeB\016\n\014_description2\253\002\n" +
      "\020DicDetailService\022\220\001\n\025findOneByDicIdAndC" +
      "ode\0229.top.fosin.anan.cloudresource.grpc." +
      "dicdetail.DicDetailReq\032:.top.fosin.anan." +
      "cloudresource.grpc.dicdetail.DicDetailRe" +
      "sp\"\000\022\203\001\n\013listByDicId\0225.top.fosin.anan.cl" +
      "oudresource.grpc.dicdetail.DicIdReq\032;.to" +
      "p.fosin.anan.cloudresource.grpc.dicdetai" +
      "l.DicDetailsResp\"\000B?\n+top.fosin.anan.clo" +
      "udresource.grpc.dicdetailB\016DicDetailProt" +
      "oP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf.TimestampProto.getDescriptor(),
        });
    internal_static_top_fosin_anan_cloudresource_grpc_dicdetail_DicIdReq_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_top_fosin_anan_cloudresource_grpc_dicdetail_DicIdReq_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_top_fosin_anan_cloudresource_grpc_dicdetail_DicIdReq_descriptor,
        new java.lang.String[] { "DictionaryId", });
    internal_static_top_fosin_anan_cloudresource_grpc_dicdetail_DicDetailReq_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_top_fosin_anan_cloudresource_grpc_dicdetail_DicDetailReq_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_top_fosin_anan_cloudresource_grpc_dicdetail_DicDetailReq_descriptor,
        new java.lang.String[] { "DictionaryId", "Code", });
    internal_static_top_fosin_anan_cloudresource_grpc_dicdetail_DicDetailsResp_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_top_fosin_anan_cloudresource_grpc_dicdetail_DicDetailsResp_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_top_fosin_anan_cloudresource_grpc_dicdetail_DicDetailsResp_descriptor,
        new java.lang.String[] { "DicDetail", });
    internal_static_top_fosin_anan_cloudresource_grpc_dicdetail_DicDetailResp_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_top_fosin_anan_cloudresource_grpc_dicdetail_DicDetailResp_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_top_fosin_anan_cloudresource_grpc_dicdetail_DicDetailResp_descriptor,
        new java.lang.String[] { "Id", "DictionaryId", "Code", "Name", "Sort", "Status", "Scode", "Scope", "Used", "Description", "CreateBy", "CreateTime", "UpdateBy", "UpdateTime", "Scode", "Scope", "Description", });
    com.google.protobuf.TimestampProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
