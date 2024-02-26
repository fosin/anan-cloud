// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Parameter.proto

package top.fosin.anan.cloudresource.grpc.parameter;

/**
 * <pre>
 *参数序号请求
 * </pre>
 *
 * Protobuf type {@code top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq}
 */
public final class ParameterIdReq extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq)
    ParameterIdReqOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ParameterIdReq.newBuilder() to construct.
  private ParameterIdReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ParameterIdReq() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ParameterIdReq();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return top.fosin.anan.cloudresource.grpc.parameter.ParameterProto.internal_static_top_fosin_anan_cloudresource_grpc_parameter_ParameterIdReq_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return top.fosin.anan.cloudresource.grpc.parameter.ParameterProto.internal_static_top_fosin_anan_cloudresource_grpc_parameter_ParameterIdReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq.class, top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq.Builder.class);
  }

  public static final int ID_FIELD_NUMBER = 1;
  private long id_ = 0L;
  /**
   * <code>int64 id = 1;</code>
   * @return The id.
   */
  @java.lang.Override
  public long getId() {
    return id_;
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
    if (id_ != 0L) {
      output.writeInt64(1, id_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (id_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, id_);
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
    if (!(obj instanceof top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq)) {
      return super.equals(obj);
    }
    top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq other = (top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq) obj;

    if (getId()
        != other.getId()) return false;
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
    hash = (37 * hash) + ID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getId());
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq parseFrom(
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
  public static Builder newBuilder(top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq prototype) {
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
   *参数序号请求
   * </pre>
   *
   * Protobuf type {@code top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq)
      top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return top.fosin.anan.cloudresource.grpc.parameter.ParameterProto.internal_static_top_fosin_anan_cloudresource_grpc_parameter_ParameterIdReq_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return top.fosin.anan.cloudresource.grpc.parameter.ParameterProto.internal_static_top_fosin_anan_cloudresource_grpc_parameter_ParameterIdReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq.class, top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq.Builder.class);
    }

    // Construct using top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq.newBuilder()
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
      id_ = 0L;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return top.fosin.anan.cloudresource.grpc.parameter.ParameterProto.internal_static_top_fosin_anan_cloudresource_grpc_parameter_ParameterIdReq_descriptor;
    }

    @java.lang.Override
    public top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq getDefaultInstanceForType() {
      return top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq.getDefaultInstance();
    }

    @java.lang.Override
    public top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq build() {
      top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq buildPartial() {
      top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq result = new top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.id_ = id_;
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
      if (other instanceof top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq) {
        return mergeFrom((top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq other) {
      if (other == top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq.getDefaultInstance()) return this;
      if (other.getId() != 0L) {
        setId(other.getId());
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
              id_ = input.readInt64();
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

    private long id_ ;
    /**
     * <code>int64 id = 1;</code>
     * @return The id.
     */
    @java.lang.Override
    public long getId() {
      return id_;
    }
    /**
     * <code>int64 id = 1;</code>
     * @param value The id to set.
     * @return This builder for chaining.
     */
    public Builder setId(long value) {

      id_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>int64 id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      id_ = 0L;
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


    // @@protoc_insertion_point(builder_scope:top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq)
  }

  // @@protoc_insertion_point(class_scope:top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq)
  private static final top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq();
  }

  public static top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ParameterIdReq>
      PARSER = new com.google.protobuf.AbstractParser<ParameterIdReq>() {
    @java.lang.Override
    public ParameterIdReq parsePartialFrom(
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

  public static com.google.protobuf.Parser<ParameterIdReq> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ParameterIdReq> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

