// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: DicDetail.proto

package top.fosin.anan.cloudresource.grpc.dicdetail;

public interface DicDetailRespOrBuilder extends
    // @@protoc_insertion_point(interface_extends:top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailResp)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 id = 1;</code>
   * @return The id.
   */
  long getId();

  /**
   * <code>int64 dictionaryId = 2;</code>
   * @return The dictionaryId.
   */
  long getDictionaryId();

  /**
   * <code>int64 code = 3;</code>
   * @return The code.
   */
  long getCode();

  /**
   * <code>string name = 4;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <code>string name = 4;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>int32 sort = 5;</code>
   * @return The sort.
   */
  int getSort();

  /**
   * <code>int32 status = 6;</code>
   * @return The status.
   */
  int getStatus();

  /**
   * <code>optional string scode = 7;</code>
   * @return Whether the scode field is set.
   */
  boolean hasScode();
  /**
   * <code>optional string scode = 7;</code>
   * @return The scode.
   */
  java.lang.String getScode();
  /**
   * <code>optional string scode = 7;</code>
   * @return The bytes for scode.
   */
  com.google.protobuf.ByteString
      getScodeBytes();

  /**
   * <code>optional string scope = 8;</code>
   * @return Whether the scope field is set.
   */
  boolean hasScope();
  /**
   * <code>optional string scope = 8;</code>
   * @return The scope.
   */
  java.lang.String getScope();
  /**
   * <code>optional string scope = 8;</code>
   * @return The bytes for scope.
   */
  com.google.protobuf.ByteString
      getScopeBytes();

  /**
   * <code>int32 used = 9;</code>
   * @return The used.
   */
  int getUsed();

  /**
   * <code>optional string description = 10;</code>
   * @return Whether the description field is set.
   */
  boolean hasDescription();
  /**
   * <code>optional string description = 10;</code>
   * @return The description.
   */
  java.lang.String getDescription();
  /**
   * <code>optional string description = 10;</code>
   * @return The bytes for description.
   */
  com.google.protobuf.ByteString
      getDescriptionBytes();

  /**
   * <code>int64 createBy = 11;</code>
   * @return The createBy.
   */
  long getCreateBy();

  /**
   * <code>.google.protobuf.Timestamp createTime = 12;</code>
   * @return Whether the createTime field is set.
   */
  boolean hasCreateTime();
  /**
   * <code>.google.protobuf.Timestamp createTime = 12;</code>
   * @return The createTime.
   */
  com.google.protobuf.Timestamp getCreateTime();
  /**
   * <code>.google.protobuf.Timestamp createTime = 12;</code>
   */
  com.google.protobuf.TimestampOrBuilder getCreateTimeOrBuilder();

  /**
   * <code>int64 updateBy = 13;</code>
   * @return The updateBy.
   */
  long getUpdateBy();

  /**
   * <code>.google.protobuf.Timestamp updateTime = 14;</code>
   * @return Whether the updateTime field is set.
   */
  boolean hasUpdateTime();
  /**
   * <code>.google.protobuf.Timestamp updateTime = 14;</code>
   * @return The updateTime.
   */
  com.google.protobuf.Timestamp getUpdateTime();
  /**
   * <code>.google.protobuf.Timestamp updateTime = 14;</code>
   */
  com.google.protobuf.TimestampOrBuilder getUpdateTimeOrBuilder();
}