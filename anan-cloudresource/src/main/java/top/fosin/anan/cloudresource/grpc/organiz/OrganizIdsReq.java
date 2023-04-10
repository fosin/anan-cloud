// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Oranization.proto

package top.fosin.anan.cloudresource.grpc.organiz;

/**
 * <pre>
 *机构序号集合请求
 * </pre>
 *
 * Protobuf type {@code top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq}
 */
public final class OrganizIdsReq extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq)
    OrganizIdsReqOrBuilder {
private static final long serialVersionUID = 0L;
  // Use OrganizIdsReq.newBuilder() to construct.
  private OrganizIdsReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private OrganizIdsReq() {
    id_ = emptyLongList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new OrganizIdsReq();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private OrganizIdsReq(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              id_ = newLongList();
              mutable_bitField0_ |= 0x00000001;
            }
            id_.addLong(input.readInt64());
            break;
          }
          case 10: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (!((mutable_bitField0_ & 0x00000001) != 0) && input.getBytesUntilLimit() > 0) {
              id_ = newLongList();
              mutable_bitField0_ |= 0x00000001;
            }
            while (input.getBytesUntilLimit() > 0) {
              id_.addLong(input.readInt64());
            }
            input.popLimit(limit);
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        id_.makeImmutable(); // C
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return top.fosin.anan.cloudresource.grpc.organiz.OrganizProto.internal_static_top_fosin_anan_cloudresource_grpc_organiz_OrganizIdsReq_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return top.fosin.anan.cloudresource.grpc.organiz.OrganizProto.internal_static_top_fosin_anan_cloudresource_grpc_organiz_OrganizIdsReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq.class, top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq.Builder.class);
  }

  public static final int ID_FIELD_NUMBER = 1;
  private com.google.protobuf.Internal.LongList id_;
  /**
   * <code>repeated int64 id = 1;</code>
   * @return A list containing the id.
   */
  @java.lang.Override
  public java.util.List<java.lang.Long>
      getIdList() {
    return id_;
  }
  /**
   * <code>repeated int64 id = 1;</code>
   * @return The count of id.
   */
  public int getIdCount() {
    return id_.size();
  }
  /**
   * <code>repeated int64 id = 1;</code>
   * @param index The index of the element to return.
   * @return The id at the given index.
   */
  public long getId(int index) {
    return id_.getLong(index);
  }
  private int idMemoizedSerializedSize = -1;

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
    getSerializedSize();
    if (getIdList().size() > 0) {
      output.writeUInt32NoTag(10);
      output.writeUInt32NoTag(idMemoizedSerializedSize);
    }
    for (int i = 0; i < id_.size(); i++) {
      output.writeInt64NoTag(id_.getLong(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    {
      int dataSize = 0;
      for (int i = 0; i < id_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeInt64SizeNoTag(id_.getLong(i));
      }
      size += dataSize;
      if (!getIdList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      idMemoizedSerializedSize = dataSize;
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq)) {
      return super.equals(obj);
    }
    top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq other = (top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq) obj;

    if (!getIdList()
        .equals(other.getIdList())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getIdCount() > 0) {
      hash = (37 * hash) + ID_FIELD_NUMBER;
      hash = (53 * hash) + getIdList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq parseFrom(
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
  public static Builder newBuilder(top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq prototype) {
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
   *机构序号集合请求
   * </pre>
   *
   * Protobuf type {@code top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq)
      top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return top.fosin.anan.cloudresource.grpc.organiz.OrganizProto.internal_static_top_fosin_anan_cloudresource_grpc_organiz_OrganizIdsReq_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return top.fosin.anan.cloudresource.grpc.organiz.OrganizProto.internal_static_top_fosin_anan_cloudresource_grpc_organiz_OrganizIdsReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq.class, top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq.Builder.class);
    }

    // Construct using top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      id_ = emptyLongList();
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return top.fosin.anan.cloudresource.grpc.organiz.OrganizProto.internal_static_top_fosin_anan_cloudresource_grpc_organiz_OrganizIdsReq_descriptor;
    }

    @java.lang.Override
    public top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq getDefaultInstanceForType() {
      return top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq.getDefaultInstance();
    }

    @java.lang.Override
    public top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq build() {
      top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq buildPartial() {
      top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq result = new top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq(this);
      int from_bitField0_ = bitField0_;
      if (((bitField0_ & 0x00000001) != 0)) {
        id_.makeImmutable();
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.id_ = id_;
      onBuilt();
      return result;
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
      if (other instanceof top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq) {
        return mergeFrom((top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq other) {
      if (other == top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq.getDefaultInstance()) return this;
      if (!other.id_.isEmpty()) {
        if (id_.isEmpty()) {
          id_ = other.id_;
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          ensureIdIsMutable();
          id_.addAll(other.id_);
        }
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
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
      top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private com.google.protobuf.Internal.LongList id_ = emptyLongList();
    private void ensureIdIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        id_ = mutableCopy(id_);
        bitField0_ |= 0x00000001;
       }
    }
    /**
     * <code>repeated int64 id = 1;</code>
     * @return A list containing the id.
     */
    public java.util.List<java.lang.Long>
        getIdList() {
      return ((bitField0_ & 0x00000001) != 0) ?
               java.util.Collections.unmodifiableList(id_) : id_;
    }
    /**
     * <code>repeated int64 id = 1;</code>
     * @return The count of id.
     */
    public int getIdCount() {
      return id_.size();
    }
    /**
     * <code>repeated int64 id = 1;</code>
     * @param index The index of the element to return.
     * @return The id at the given index.
     */
    public long getId(int index) {
      return id_.getLong(index);
    }
    /**
     * <code>repeated int64 id = 1;</code>
     * @param index The index to set the value at.
     * @param value The id to set.
     * @return This builder for chaining.
     */
    public Builder setId(
        int index, long value) {
      ensureIdIsMutable();
      id_.setLong(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int64 id = 1;</code>
     * @param value The id to add.
     * @return This builder for chaining.
     */
    public Builder addId(long value) {
      ensureIdIsMutable();
      id_.addLong(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int64 id = 1;</code>
     * @param values The id to add.
     * @return This builder for chaining.
     */
    public Builder addAllId(
        java.lang.Iterable<? extends java.lang.Long> values) {
      ensureIdIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, id_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int64 id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearId() {
      id_ = emptyLongList();
      bitField0_ = (bitField0_ & ~0x00000001);
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


    // @@protoc_insertion_point(builder_scope:top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq)
  }

  // @@protoc_insertion_point(class_scope:top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq)
  private static final top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq();
  }

  public static top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<OrganizIdsReq>
      PARSER = new com.google.protobuf.AbstractParser<OrganizIdsReq>() {
    @java.lang.Override
    public OrganizIdsReq parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new OrganizIdsReq(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<OrganizIdsReq> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<OrganizIdsReq> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

