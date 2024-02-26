// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: DicDetail.proto

package top.fosin.anan.cloudresource.grpc.dicdetail;

/**
 * <pre>
 *字典序号请求
 * </pre>
 *
 * Protobuf type {@code top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq}
 */
public final class DicIdReq extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq)
    DicIdReqOrBuilder {
private static final long serialVersionUID = 0L;
  // Use DicIdReq.newBuilder() to construct.
  private DicIdReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private DicIdReq() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new DicIdReq();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailProto.internal_static_top_fosin_anan_cloudresource_grpc_dicdetail_DicIdReq_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailProto.internal_static_top_fosin_anan_cloudresource_grpc_dicdetail_DicIdReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq.class, top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq.Builder.class);
  }

  public static final int DICTIONARYID_FIELD_NUMBER = 1;
  private long dictionaryId_ = 0L;
  /**
   * <code>int64 dictionaryId = 1;</code>
   * @return The dictionaryId.
   */
  @java.lang.Override
  public long getDictionaryId() {
    return dictionaryId_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (dictionaryId_ != 0L) {
      output.writeInt64(1, dictionaryId_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (dictionaryId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, dictionaryId_);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq)) {
      return super.equals(obj);
    }
    top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq other = (top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq) obj;

    if (getDictionaryId()
        != other.getDictionaryId()) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + DICTIONARYID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getDictionaryId());
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   *字典序号请求
   * </pre>
   *
   * Protobuf type {@code top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq)
      top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailProto.internal_static_top_fosin_anan_cloudresource_grpc_dicdetail_DicIdReq_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailProto.internal_static_top_fosin_anan_cloudresource_grpc_dicdetail_DicIdReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq.class, top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq.Builder.class);
    }

    // Construct using top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      dictionaryId_ = 0L;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailProto.internal_static_top_fosin_anan_cloudresource_grpc_dicdetail_DicIdReq_descriptor;
    }

    @java.lang.Override
    public top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq getDefaultInstanceForType() {
      return top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq.getDefaultInstance();
    }

    @java.lang.Override
    public top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq build() {
      top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq buildPartial() {
      top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq result = new top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.dictionaryId_ = dictionaryId_;
      }
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq) {
        return mergeFrom((top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq other) {
      if (other == top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq.getDefaultInstance()) return this;
      if (other.getDictionaryId() != 0L) {
        setDictionaryId(other.getDictionaryId());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {
              dictionaryId_ = input.readInt64();
              bitField0_ |= 0x00000001;
              break;
            } // case 8
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private long dictionaryId_ ;
    /**
     * <code>int64 dictionaryId = 1;</code>
     * @return The dictionaryId.
     */
    @java.lang.Override
    public long getDictionaryId() {
      return dictionaryId_;
    }
    /**
     * <code>int64 dictionaryId = 1;</code>
     * @param value The dictionaryId to set.
     * @return This builder for chaining.
     */
    public Builder setDictionaryId(long value) {

      dictionaryId_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>int64 dictionaryId = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearDictionaryId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      dictionaryId_ = 0L;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq)
  }

  // @@protoc_insertion_point(class_scope:top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq)
  private static final top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq();
  }

  public static top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<DicIdReq>
      PARSER = new com.google.protobuf.AbstractParser<DicIdReq>() {
    @java.lang.Override
    public DicIdReq parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<DicIdReq> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<DicIdReq> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

