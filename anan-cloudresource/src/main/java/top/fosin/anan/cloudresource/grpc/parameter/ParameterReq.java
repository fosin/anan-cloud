// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Parameter.proto

package top.fosin.anan.cloudresource.grpc.parameter;

/**
 * <pre>
 *参数请求
 * </pre>
 *
 * Protobuf type {@code top.fosin.anan.cloudresource.grpc.parameter.ParameterReq}
 */
public final class ParameterReq extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:top.fosin.anan.cloudresource.grpc.parameter.ParameterReq)
    ParameterReqOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ParameterReq.newBuilder() to construct.
  private ParameterReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ParameterReq() {
    name_ = "";
    value_ = "";
    scope_ = "";
    defaultValue_ = "";
    description_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ParameterReq();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ParameterReq(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
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
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            name_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            value_ = s;
            break;
          }
          case 24: {

            type_ = input.readInt32();
            break;
          }
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();

            scope_ = s;
            break;
          }
          case 42: {
            java.lang.String s = input.readStringRequireUtf8();

            defaultValue_ = s;
            break;
          }
          case 50: {
            java.lang.String s = input.readStringRequireUtf8();

            description_ = s;
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
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return top.fosin.anan.cloudresource.grpc.parameter.ParameterProto.internal_static_top_fosin_anan_cloudresource_grpc_parameter_ParameterReq_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return top.fosin.anan.cloudresource.grpc.parameter.ParameterProto.internal_static_top_fosin_anan_cloudresource_grpc_parameter_ParameterReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            top.fosin.anan.cloudresource.grpc.parameter.ParameterReq.class, top.fosin.anan.cloudresource.grpc.parameter.ParameterReq.Builder.class);
  }

  public static final int NAME_FIELD_NUMBER = 1;
  private volatile java.lang.Object name_;
  /**
   * <code>string name = 1;</code>
   * @return The name.
   */
  @java.lang.Override
  public java.lang.String getName() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      name_ = s;
      return s;
    }
  }
  /**
   * <code>string name = 1;</code>
   * @return The bytes for name.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getNameBytes() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      name_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int VALUE_FIELD_NUMBER = 2;
  private volatile java.lang.Object value_;
  /**
   * <code>string value = 2;</code>
   * @return The value.
   */
  @java.lang.Override
  public java.lang.String getValue() {
    java.lang.Object ref = value_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      value_ = s;
      return s;
    }
  }
  /**
   * <code>string value = 2;</code>
   * @return The bytes for value.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getValueBytes() {
    java.lang.Object ref = value_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      value_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int TYPE_FIELD_NUMBER = 3;
  private int type_;
  /**
   * <code>int32 type = 3;</code>
   * @return The type.
   */
  @java.lang.Override
  public int getType() {
    return type_;
  }

  public static final int SCOPE_FIELD_NUMBER = 4;
  private volatile java.lang.Object scope_;
  /**
   * <code>string scope = 4;</code>
   * @return The scope.
   */
  @java.lang.Override
  public java.lang.String getScope() {
    java.lang.Object ref = scope_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      scope_ = s;
      return s;
    }
  }
  /**
   * <code>string scope = 4;</code>
   * @return The bytes for scope.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getScopeBytes() {
    java.lang.Object ref = scope_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      scope_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int DEFAULTVALUE_FIELD_NUMBER = 5;
  private volatile java.lang.Object defaultValue_;
  /**
   * <code>string defaultValue = 5;</code>
   * @return The defaultValue.
   */
  @java.lang.Override
  public java.lang.String getDefaultValue() {
    java.lang.Object ref = defaultValue_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      defaultValue_ = s;
      return s;
    }
  }
  /**
   * <code>string defaultValue = 5;</code>
   * @return The bytes for defaultValue.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getDefaultValueBytes() {
    java.lang.Object ref = defaultValue_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      defaultValue_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int DESCRIPTION_FIELD_NUMBER = 6;
  private volatile java.lang.Object description_;
  /**
   * <code>string description = 6;</code>
   * @return The description.
   */
  @java.lang.Override
  public java.lang.String getDescription() {
    java.lang.Object ref = description_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      description_ = s;
      return s;
    }
  }
  /**
   * <code>string description = 6;</code>
   * @return The bytes for description.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getDescriptionBytes() {
    java.lang.Object ref = description_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      description_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
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
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(name_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, name_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(value_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, value_);
    }
    if (type_ != 0) {
      output.writeInt32(3, type_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(scope_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, scope_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(defaultValue_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, defaultValue_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(description_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 6, description_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(name_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, name_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(value_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, value_);
    }
    if (type_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, type_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(scope_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, scope_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(defaultValue_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, defaultValue_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(description_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(6, description_);
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
    if (!(obj instanceof top.fosin.anan.cloudresource.grpc.parameter.ParameterReq)) {
      return super.equals(obj);
    }
    top.fosin.anan.cloudresource.grpc.parameter.ParameterReq other = (top.fosin.anan.cloudresource.grpc.parameter.ParameterReq) obj;

    if (!getName()
        .equals(other.getName())) return false;
    if (!getValue()
        .equals(other.getValue())) return false;
    if (getType()
        != other.getType()) return false;
    if (!getScope()
        .equals(other.getScope())) return false;
    if (!getDefaultValue()
        .equals(other.getDefaultValue())) return false;
    if (!getDescription()
        .equals(other.getDescription())) return false;
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
    hash = (37 * hash) + NAME_FIELD_NUMBER;
    hash = (53 * hash) + getName().hashCode();
    hash = (37 * hash) + VALUE_FIELD_NUMBER;
    hash = (53 * hash) + getValue().hashCode();
    hash = (37 * hash) + TYPE_FIELD_NUMBER;
    hash = (53 * hash) + getType();
    hash = (37 * hash) + SCOPE_FIELD_NUMBER;
    hash = (53 * hash) + getScope().hashCode();
    hash = (37 * hash) + DEFAULTVALUE_FIELD_NUMBER;
    hash = (53 * hash) + getDefaultValue().hashCode();
    hash = (37 * hash) + DESCRIPTION_FIELD_NUMBER;
    hash = (53 * hash) + getDescription().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static top.fosin.anan.cloudresource.grpc.parameter.ParameterReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static top.fosin.anan.cloudresource.grpc.parameter.ParameterReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static top.fosin.anan.cloudresource.grpc.parameter.ParameterReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static top.fosin.anan.cloudresource.grpc.parameter.ParameterReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static top.fosin.anan.cloudresource.grpc.parameter.ParameterReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static top.fosin.anan.cloudresource.grpc.parameter.ParameterReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static top.fosin.anan.cloudresource.grpc.parameter.ParameterReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static top.fosin.anan.cloudresource.grpc.parameter.ParameterReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static top.fosin.anan.cloudresource.grpc.parameter.ParameterReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static top.fosin.anan.cloudresource.grpc.parameter.ParameterReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static top.fosin.anan.cloudresource.grpc.parameter.ParameterReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static top.fosin.anan.cloudresource.grpc.parameter.ParameterReq parseFrom(
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
  public static Builder newBuilder(top.fosin.anan.cloudresource.grpc.parameter.ParameterReq prototype) {
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
   *参数请求
   * </pre>
   *
   * Protobuf type {@code top.fosin.anan.cloudresource.grpc.parameter.ParameterReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:top.fosin.anan.cloudresource.grpc.parameter.ParameterReq)
      top.fosin.anan.cloudresource.grpc.parameter.ParameterReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return top.fosin.anan.cloudresource.grpc.parameter.ParameterProto.internal_static_top_fosin_anan_cloudresource_grpc_parameter_ParameterReq_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return top.fosin.anan.cloudresource.grpc.parameter.ParameterProto.internal_static_top_fosin_anan_cloudresource_grpc_parameter_ParameterReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              top.fosin.anan.cloudresource.grpc.parameter.ParameterReq.class, top.fosin.anan.cloudresource.grpc.parameter.ParameterReq.Builder.class);
    }

    // Construct using top.fosin.anan.cloudresource.grpc.parameter.ParameterReq.newBuilder()
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
      name_ = "";

      value_ = "";

      type_ = 0;

      scope_ = "";

      defaultValue_ = "";

      description_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return top.fosin.anan.cloudresource.grpc.parameter.ParameterProto.internal_static_top_fosin_anan_cloudresource_grpc_parameter_ParameterReq_descriptor;
    }

    @java.lang.Override
    public top.fosin.anan.cloudresource.grpc.parameter.ParameterReq getDefaultInstanceForType() {
      return top.fosin.anan.cloudresource.grpc.parameter.ParameterReq.getDefaultInstance();
    }

    @java.lang.Override
    public top.fosin.anan.cloudresource.grpc.parameter.ParameterReq build() {
      top.fosin.anan.cloudresource.grpc.parameter.ParameterReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public top.fosin.anan.cloudresource.grpc.parameter.ParameterReq buildPartial() {
      top.fosin.anan.cloudresource.grpc.parameter.ParameterReq result = new top.fosin.anan.cloudresource.grpc.parameter.ParameterReq(this);
      result.name_ = name_;
      result.value_ = value_;
      result.type_ = type_;
      result.scope_ = scope_;
      result.defaultValue_ = defaultValue_;
      result.description_ = description_;
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
      if (other instanceof top.fosin.anan.cloudresource.grpc.parameter.ParameterReq) {
        return mergeFrom((top.fosin.anan.cloudresource.grpc.parameter.ParameterReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(top.fosin.anan.cloudresource.grpc.parameter.ParameterReq other) {
      if (other == top.fosin.anan.cloudresource.grpc.parameter.ParameterReq.getDefaultInstance()) return this;
      if (!other.getName().isEmpty()) {
        name_ = other.name_;
        onChanged();
      }
      if (!other.getValue().isEmpty()) {
        value_ = other.value_;
        onChanged();
      }
      if (other.getType() != 0) {
        setType(other.getType());
      }
      if (!other.getScope().isEmpty()) {
        scope_ = other.scope_;
        onChanged();
      }
      if (!other.getDefaultValue().isEmpty()) {
        defaultValue_ = other.defaultValue_;
        onChanged();
      }
      if (!other.getDescription().isEmpty()) {
        description_ = other.description_;
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
      top.fosin.anan.cloudresource.grpc.parameter.ParameterReq parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (top.fosin.anan.cloudresource.grpc.parameter.ParameterReq) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object name_ = "";
    /**
     * <code>string name = 1;</code>
     * @return The name.
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        name_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string name = 1;</code>
     * @return The bytes for name.
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string name = 1;</code>
     * @param value The name to set.
     * @return This builder for chaining.
     */
    public Builder setName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      name_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string name = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearName() {
      
      name_ = getDefaultInstance().getName();
      onChanged();
      return this;
    }
    /**
     * <code>string name = 1;</code>
     * @param value The bytes for name to set.
     * @return This builder for chaining.
     */
    public Builder setNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      name_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object value_ = "";
    /**
     * <code>string value = 2;</code>
     * @return The value.
     */
    public java.lang.String getValue() {
      java.lang.Object ref = value_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        value_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string value = 2;</code>
     * @return The bytes for value.
     */
    public com.google.protobuf.ByteString
        getValueBytes() {
      java.lang.Object ref = value_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        value_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string value = 2;</code>
     * @param value The value to set.
     * @return This builder for chaining.
     */
    public Builder setValue(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      value_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string value = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearValue() {
      
      value_ = getDefaultInstance().getValue();
      onChanged();
      return this;
    }
    /**
     * <code>string value = 2;</code>
     * @param value The bytes for value to set.
     * @return This builder for chaining.
     */
    public Builder setValueBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      value_ = value;
      onChanged();
      return this;
    }

    private int type_ ;
    /**
     * <code>int32 type = 3;</code>
     * @return The type.
     */
    @java.lang.Override
    public int getType() {
      return type_;
    }
    /**
     * <code>int32 type = 3;</code>
     * @param value The type to set.
     * @return This builder for chaining.
     */
    public Builder setType(int value) {
      
      type_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 type = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearType() {
      
      type_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object scope_ = "";
    /**
     * <code>string scope = 4;</code>
     * @return The scope.
     */
    public java.lang.String getScope() {
      java.lang.Object ref = scope_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        scope_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string scope = 4;</code>
     * @return The bytes for scope.
     */
    public com.google.protobuf.ByteString
        getScopeBytes() {
      java.lang.Object ref = scope_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        scope_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string scope = 4;</code>
     * @param value The scope to set.
     * @return This builder for chaining.
     */
    public Builder setScope(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      scope_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string scope = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearScope() {
      
      scope_ = getDefaultInstance().getScope();
      onChanged();
      return this;
    }
    /**
     * <code>string scope = 4;</code>
     * @param value The bytes for scope to set.
     * @return This builder for chaining.
     */
    public Builder setScopeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      scope_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object defaultValue_ = "";
    /**
     * <code>string defaultValue = 5;</code>
     * @return The defaultValue.
     */
    public java.lang.String getDefaultValue() {
      java.lang.Object ref = defaultValue_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        defaultValue_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string defaultValue = 5;</code>
     * @return The bytes for defaultValue.
     */
    public com.google.protobuf.ByteString
        getDefaultValueBytes() {
      java.lang.Object ref = defaultValue_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        defaultValue_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string defaultValue = 5;</code>
     * @param value The defaultValue to set.
     * @return This builder for chaining.
     */
    public Builder setDefaultValue(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      defaultValue_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string defaultValue = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearDefaultValue() {
      
      defaultValue_ = getDefaultInstance().getDefaultValue();
      onChanged();
      return this;
    }
    /**
     * <code>string defaultValue = 5;</code>
     * @param value The bytes for defaultValue to set.
     * @return This builder for chaining.
     */
    public Builder setDefaultValueBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      defaultValue_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object description_ = "";
    /**
     * <code>string description = 6;</code>
     * @return The description.
     */
    public java.lang.String getDescription() {
      java.lang.Object ref = description_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        description_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string description = 6;</code>
     * @return The bytes for description.
     */
    public com.google.protobuf.ByteString
        getDescriptionBytes() {
      java.lang.Object ref = description_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        description_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string description = 6;</code>
     * @param value The description to set.
     * @return This builder for chaining.
     */
    public Builder setDescription(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      description_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string description = 6;</code>
     * @return This builder for chaining.
     */
    public Builder clearDescription() {
      
      description_ = getDefaultInstance().getDescription();
      onChanged();
      return this;
    }
    /**
     * <code>string description = 6;</code>
     * @param value The bytes for description to set.
     * @return This builder for chaining.
     */
    public Builder setDescriptionBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      description_ = value;
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


    // @@protoc_insertion_point(builder_scope:top.fosin.anan.cloudresource.grpc.parameter.ParameterReq)
  }

  // @@protoc_insertion_point(class_scope:top.fosin.anan.cloudresource.grpc.parameter.ParameterReq)
  private static final top.fosin.anan.cloudresource.grpc.parameter.ParameterReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new top.fosin.anan.cloudresource.grpc.parameter.ParameterReq();
  }

  public static top.fosin.anan.cloudresource.grpc.parameter.ParameterReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ParameterReq>
      PARSER = new com.google.protobuf.AbstractParser<ParameterReq>() {
    @java.lang.Override
    public ParameterReq parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ParameterReq(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ParameterReq> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ParameterReq> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public top.fosin.anan.cloudresource.grpc.parameter.ParameterReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

