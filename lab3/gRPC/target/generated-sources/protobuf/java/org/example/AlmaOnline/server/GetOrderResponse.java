// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: AlmaOnline.proto

package org.example.AlmaOnline.server;

/**
 * Protobuf type {@code almaonline.GetOrderResponse}
 */
public final class GetOrderResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:almaonline.GetOrderResponse)
    GetOrderResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GetOrderResponse.newBuilder() to construct.
  private GetOrderResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GetOrderResponse() {
    customer_ = "";
    items_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new GetOrderResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GetOrderResponse(
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
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();
            bitField0_ |= 0x00000001;
            customer_ = s;
            break;
          }
          case 18: {
            org.example.AlmaOnline.server.Date.Builder subBuilder = null;
            if (createDate_ != null) {
              subBuilder = createDate_.toBuilder();
            }
            createDate_ = input.readMessage(org.example.AlmaOnline.server.Date.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(createDate_);
              createDate_ = subBuilder.buildPartial();
            }

            break;
          }
          case 26: {
            if (!((mutable_bitField0_ & 0x00000002) != 0)) {
              items_ = new java.util.ArrayList<org.example.AlmaOnline.server.ItemInfo>();
              mutable_bitField0_ |= 0x00000002;
            }
            items_.add(
                input.readMessage(org.example.AlmaOnline.server.ItemInfo.parser(), extensionRegistry));
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
      if (((mutable_bitField0_ & 0x00000002) != 0)) {
        items_ = java.util.Collections.unmodifiableList(items_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.example.AlmaOnline.server.AlmaOnlineProto.internal_static_almaonline_GetOrderResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.example.AlmaOnline.server.AlmaOnlineProto.internal_static_almaonline_GetOrderResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.example.AlmaOnline.server.GetOrderResponse.class, org.example.AlmaOnline.server.GetOrderResponse.Builder.class);
  }

  private int bitField0_;
  public static final int CUSTOMER_FIELD_NUMBER = 1;
  private volatile java.lang.Object customer_;
  /**
   * <code>string customer = 1;</code>
   * @return Whether the customer field is set.
   */
  @java.lang.Override
  public boolean hasCustomer() {
    return ((bitField0_ & 0x00000001) != 0);
  }
  /**
   * <code>string customer = 1;</code>
   * @return The customer.
   */
  @java.lang.Override
  public java.lang.String getCustomer() {
    java.lang.Object ref = customer_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      customer_ = s;
      return s;
    }
  }
  /**
   * <code>string customer = 1;</code>
   * @return The bytes for customer.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getCustomerBytes() {
    java.lang.Object ref = customer_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      customer_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int CREATEDATE_FIELD_NUMBER = 2;
  private org.example.AlmaOnline.server.Date createDate_;
  /**
   * <code>.almaonline.Date createDate = 2;</code>
   * @return Whether the createDate field is set.
   */
  @java.lang.Override
  public boolean hasCreateDate() {
    return createDate_ != null;
  }
  /**
   * <code>.almaonline.Date createDate = 2;</code>
   * @return The createDate.
   */
  @java.lang.Override
  public org.example.AlmaOnline.server.Date getCreateDate() {
    return createDate_ == null ? org.example.AlmaOnline.server.Date.getDefaultInstance() : createDate_;
  }
  /**
   * <code>.almaonline.Date createDate = 2;</code>
   */
  @java.lang.Override
  public org.example.AlmaOnline.server.DateOrBuilder getCreateDateOrBuilder() {
    return getCreateDate();
  }

  public static final int ITEMS_FIELD_NUMBER = 3;
  private java.util.List<org.example.AlmaOnline.server.ItemInfo> items_;
  /**
   * <code>repeated .almaonline.ItemInfo items = 3;</code>
   */
  @java.lang.Override
  public java.util.List<org.example.AlmaOnline.server.ItemInfo> getItemsList() {
    return items_;
  }
  /**
   * <code>repeated .almaonline.ItemInfo items = 3;</code>
   */
  @java.lang.Override
  public java.util.List<? extends org.example.AlmaOnline.server.ItemInfoOrBuilder> 
      getItemsOrBuilderList() {
    return items_;
  }
  /**
   * <code>repeated .almaonline.ItemInfo items = 3;</code>
   */
  @java.lang.Override
  public int getItemsCount() {
    return items_.size();
  }
  /**
   * <code>repeated .almaonline.ItemInfo items = 3;</code>
   */
  @java.lang.Override
  public org.example.AlmaOnline.server.ItemInfo getItems(int index) {
    return items_.get(index);
  }
  /**
   * <code>repeated .almaonline.ItemInfo items = 3;</code>
   */
  @java.lang.Override
  public org.example.AlmaOnline.server.ItemInfoOrBuilder getItemsOrBuilder(
      int index) {
    return items_.get(index);
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
    if (((bitField0_ & 0x00000001) != 0)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, customer_);
    }
    if (createDate_ != null) {
      output.writeMessage(2, getCreateDate());
    }
    for (int i = 0; i < items_.size(); i++) {
      output.writeMessage(3, items_.get(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) != 0)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, customer_);
    }
    if (createDate_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getCreateDate());
    }
    for (int i = 0; i < items_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, items_.get(i));
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
    if (!(obj instanceof org.example.AlmaOnline.server.GetOrderResponse)) {
      return super.equals(obj);
    }
    org.example.AlmaOnline.server.GetOrderResponse other = (org.example.AlmaOnline.server.GetOrderResponse) obj;

    if (hasCustomer() != other.hasCustomer()) return false;
    if (hasCustomer()) {
      if (!getCustomer()
          .equals(other.getCustomer())) return false;
    }
    if (hasCreateDate() != other.hasCreateDate()) return false;
    if (hasCreateDate()) {
      if (!getCreateDate()
          .equals(other.getCreateDate())) return false;
    }
    if (!getItemsList()
        .equals(other.getItemsList())) return false;
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
    if (hasCustomer()) {
      hash = (37 * hash) + CUSTOMER_FIELD_NUMBER;
      hash = (53 * hash) + getCustomer().hashCode();
    }
    if (hasCreateDate()) {
      hash = (37 * hash) + CREATEDATE_FIELD_NUMBER;
      hash = (53 * hash) + getCreateDate().hashCode();
    }
    if (getItemsCount() > 0) {
      hash = (37 * hash) + ITEMS_FIELD_NUMBER;
      hash = (53 * hash) + getItemsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.example.AlmaOnline.server.GetOrderResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.example.AlmaOnline.server.GetOrderResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.example.AlmaOnline.server.GetOrderResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.example.AlmaOnline.server.GetOrderResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.example.AlmaOnline.server.GetOrderResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.example.AlmaOnline.server.GetOrderResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.example.AlmaOnline.server.GetOrderResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.example.AlmaOnline.server.GetOrderResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.example.AlmaOnline.server.GetOrderResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static org.example.AlmaOnline.server.GetOrderResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.example.AlmaOnline.server.GetOrderResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.example.AlmaOnline.server.GetOrderResponse parseFrom(
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
  public static Builder newBuilder(org.example.AlmaOnline.server.GetOrderResponse prototype) {
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
   * Protobuf type {@code almaonline.GetOrderResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:almaonline.GetOrderResponse)
      org.example.AlmaOnline.server.GetOrderResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.example.AlmaOnline.server.AlmaOnlineProto.internal_static_almaonline_GetOrderResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.example.AlmaOnline.server.AlmaOnlineProto.internal_static_almaonline_GetOrderResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.example.AlmaOnline.server.GetOrderResponse.class, org.example.AlmaOnline.server.GetOrderResponse.Builder.class);
    }

    // Construct using org.example.AlmaOnline.server.GetOrderResponse.newBuilder()
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
        getItemsFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      customer_ = "";
      bitField0_ = (bitField0_ & ~0x00000001);
      if (createDateBuilder_ == null) {
        createDate_ = null;
      } else {
        createDate_ = null;
        createDateBuilder_ = null;
      }
      if (itemsBuilder_ == null) {
        items_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        itemsBuilder_.clear();
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.example.AlmaOnline.server.AlmaOnlineProto.internal_static_almaonline_GetOrderResponse_descriptor;
    }

    @java.lang.Override
    public org.example.AlmaOnline.server.GetOrderResponse getDefaultInstanceForType() {
      return org.example.AlmaOnline.server.GetOrderResponse.getDefaultInstance();
    }

    @java.lang.Override
    public org.example.AlmaOnline.server.GetOrderResponse build() {
      org.example.AlmaOnline.server.GetOrderResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public org.example.AlmaOnline.server.GetOrderResponse buildPartial() {
      org.example.AlmaOnline.server.GetOrderResponse result = new org.example.AlmaOnline.server.GetOrderResponse(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        to_bitField0_ |= 0x00000001;
      }
      result.customer_ = customer_;
      if (createDateBuilder_ == null) {
        result.createDate_ = createDate_;
      } else {
        result.createDate_ = createDateBuilder_.build();
      }
      if (itemsBuilder_ == null) {
        if (((bitField0_ & 0x00000002) != 0)) {
          items_ = java.util.Collections.unmodifiableList(items_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.items_ = items_;
      } else {
        result.items_ = itemsBuilder_.build();
      }
      result.bitField0_ = to_bitField0_;
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
      if (other instanceof org.example.AlmaOnline.server.GetOrderResponse) {
        return mergeFrom((org.example.AlmaOnline.server.GetOrderResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.example.AlmaOnline.server.GetOrderResponse other) {
      if (other == org.example.AlmaOnline.server.GetOrderResponse.getDefaultInstance()) return this;
      if (other.hasCustomer()) {
        bitField0_ |= 0x00000001;
        customer_ = other.customer_;
        onChanged();
      }
      if (other.hasCreateDate()) {
        mergeCreateDate(other.getCreateDate());
      }
      if (itemsBuilder_ == null) {
        if (!other.items_.isEmpty()) {
          if (items_.isEmpty()) {
            items_ = other.items_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureItemsIsMutable();
            items_.addAll(other.items_);
          }
          onChanged();
        }
      } else {
        if (!other.items_.isEmpty()) {
          if (itemsBuilder_.isEmpty()) {
            itemsBuilder_.dispose();
            itemsBuilder_ = null;
            items_ = other.items_;
            bitField0_ = (bitField0_ & ~0x00000002);
            itemsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getItemsFieldBuilder() : null;
          } else {
            itemsBuilder_.addAllMessages(other.items_);
          }
        }
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
      org.example.AlmaOnline.server.GetOrderResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (org.example.AlmaOnline.server.GetOrderResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.lang.Object customer_ = "";
    /**
     * <code>string customer = 1;</code>
     * @return Whether the customer field is set.
     */
    public boolean hasCustomer() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>string customer = 1;</code>
     * @return The customer.
     */
    public java.lang.String getCustomer() {
      java.lang.Object ref = customer_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        customer_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string customer = 1;</code>
     * @return The bytes for customer.
     */
    public com.google.protobuf.ByteString
        getCustomerBytes() {
      java.lang.Object ref = customer_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        customer_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string customer = 1;</code>
     * @param value The customer to set.
     * @return This builder for chaining.
     */
    public Builder setCustomer(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      customer_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string customer = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearCustomer() {
      bitField0_ = (bitField0_ & ~0x00000001);
      customer_ = getDefaultInstance().getCustomer();
      onChanged();
      return this;
    }
    /**
     * <code>string customer = 1;</code>
     * @param value The bytes for customer to set.
     * @return This builder for chaining.
     */
    public Builder setCustomerBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      bitField0_ |= 0x00000001;
      customer_ = value;
      onChanged();
      return this;
    }

    private org.example.AlmaOnline.server.Date createDate_;
    private com.google.protobuf.SingleFieldBuilderV3<
        org.example.AlmaOnline.server.Date, org.example.AlmaOnline.server.Date.Builder, org.example.AlmaOnline.server.DateOrBuilder> createDateBuilder_;
    /**
     * <code>.almaonline.Date createDate = 2;</code>
     * @return Whether the createDate field is set.
     */
    public boolean hasCreateDate() {
      return createDateBuilder_ != null || createDate_ != null;
    }
    /**
     * <code>.almaonline.Date createDate = 2;</code>
     * @return The createDate.
     */
    public org.example.AlmaOnline.server.Date getCreateDate() {
      if (createDateBuilder_ == null) {
        return createDate_ == null ? org.example.AlmaOnline.server.Date.getDefaultInstance() : createDate_;
      } else {
        return createDateBuilder_.getMessage();
      }
    }
    /**
     * <code>.almaonline.Date createDate = 2;</code>
     */
    public Builder setCreateDate(org.example.AlmaOnline.server.Date value) {
      if (createDateBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        createDate_ = value;
        onChanged();
      } else {
        createDateBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.almaonline.Date createDate = 2;</code>
     */
    public Builder setCreateDate(
        org.example.AlmaOnline.server.Date.Builder builderForValue) {
      if (createDateBuilder_ == null) {
        createDate_ = builderForValue.build();
        onChanged();
      } else {
        createDateBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.almaonline.Date createDate = 2;</code>
     */
    public Builder mergeCreateDate(org.example.AlmaOnline.server.Date value) {
      if (createDateBuilder_ == null) {
        if (createDate_ != null) {
          createDate_ =
            org.example.AlmaOnline.server.Date.newBuilder(createDate_).mergeFrom(value).buildPartial();
        } else {
          createDate_ = value;
        }
        onChanged();
      } else {
        createDateBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.almaonline.Date createDate = 2;</code>
     */
    public Builder clearCreateDate() {
      if (createDateBuilder_ == null) {
        createDate_ = null;
        onChanged();
      } else {
        createDate_ = null;
        createDateBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.almaonline.Date createDate = 2;</code>
     */
    public org.example.AlmaOnline.server.Date.Builder getCreateDateBuilder() {
      
      onChanged();
      return getCreateDateFieldBuilder().getBuilder();
    }
    /**
     * <code>.almaonline.Date createDate = 2;</code>
     */
    public org.example.AlmaOnline.server.DateOrBuilder getCreateDateOrBuilder() {
      if (createDateBuilder_ != null) {
        return createDateBuilder_.getMessageOrBuilder();
      } else {
        return createDate_ == null ?
            org.example.AlmaOnline.server.Date.getDefaultInstance() : createDate_;
      }
    }
    /**
     * <code>.almaonline.Date createDate = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        org.example.AlmaOnline.server.Date, org.example.AlmaOnline.server.Date.Builder, org.example.AlmaOnline.server.DateOrBuilder> 
        getCreateDateFieldBuilder() {
      if (createDateBuilder_ == null) {
        createDateBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            org.example.AlmaOnline.server.Date, org.example.AlmaOnline.server.Date.Builder, org.example.AlmaOnline.server.DateOrBuilder>(
                getCreateDate(),
                getParentForChildren(),
                isClean());
        createDate_ = null;
      }
      return createDateBuilder_;
    }

    private java.util.List<org.example.AlmaOnline.server.ItemInfo> items_ =
      java.util.Collections.emptyList();
    private void ensureItemsIsMutable() {
      if (!((bitField0_ & 0x00000002) != 0)) {
        items_ = new java.util.ArrayList<org.example.AlmaOnline.server.ItemInfo>(items_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        org.example.AlmaOnline.server.ItemInfo, org.example.AlmaOnline.server.ItemInfo.Builder, org.example.AlmaOnline.server.ItemInfoOrBuilder> itemsBuilder_;

    /**
     * <code>repeated .almaonline.ItemInfo items = 3;</code>
     */
    public java.util.List<org.example.AlmaOnline.server.ItemInfo> getItemsList() {
      if (itemsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(items_);
      } else {
        return itemsBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .almaonline.ItemInfo items = 3;</code>
     */
    public int getItemsCount() {
      if (itemsBuilder_ == null) {
        return items_.size();
      } else {
        return itemsBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .almaonline.ItemInfo items = 3;</code>
     */
    public org.example.AlmaOnline.server.ItemInfo getItems(int index) {
      if (itemsBuilder_ == null) {
        return items_.get(index);
      } else {
        return itemsBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .almaonline.ItemInfo items = 3;</code>
     */
    public Builder setItems(
        int index, org.example.AlmaOnline.server.ItemInfo value) {
      if (itemsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureItemsIsMutable();
        items_.set(index, value);
        onChanged();
      } else {
        itemsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .almaonline.ItemInfo items = 3;</code>
     */
    public Builder setItems(
        int index, org.example.AlmaOnline.server.ItemInfo.Builder builderForValue) {
      if (itemsBuilder_ == null) {
        ensureItemsIsMutable();
        items_.set(index, builderForValue.build());
        onChanged();
      } else {
        itemsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .almaonline.ItemInfo items = 3;</code>
     */
    public Builder addItems(org.example.AlmaOnline.server.ItemInfo value) {
      if (itemsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureItemsIsMutable();
        items_.add(value);
        onChanged();
      } else {
        itemsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .almaonline.ItemInfo items = 3;</code>
     */
    public Builder addItems(
        int index, org.example.AlmaOnline.server.ItemInfo value) {
      if (itemsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureItemsIsMutable();
        items_.add(index, value);
        onChanged();
      } else {
        itemsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .almaonline.ItemInfo items = 3;</code>
     */
    public Builder addItems(
        org.example.AlmaOnline.server.ItemInfo.Builder builderForValue) {
      if (itemsBuilder_ == null) {
        ensureItemsIsMutable();
        items_.add(builderForValue.build());
        onChanged();
      } else {
        itemsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .almaonline.ItemInfo items = 3;</code>
     */
    public Builder addItems(
        int index, org.example.AlmaOnline.server.ItemInfo.Builder builderForValue) {
      if (itemsBuilder_ == null) {
        ensureItemsIsMutable();
        items_.add(index, builderForValue.build());
        onChanged();
      } else {
        itemsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .almaonline.ItemInfo items = 3;</code>
     */
    public Builder addAllItems(
        java.lang.Iterable<? extends org.example.AlmaOnline.server.ItemInfo> values) {
      if (itemsBuilder_ == null) {
        ensureItemsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, items_);
        onChanged();
      } else {
        itemsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .almaonline.ItemInfo items = 3;</code>
     */
    public Builder clearItems() {
      if (itemsBuilder_ == null) {
        items_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        itemsBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .almaonline.ItemInfo items = 3;</code>
     */
    public Builder removeItems(int index) {
      if (itemsBuilder_ == null) {
        ensureItemsIsMutable();
        items_.remove(index);
        onChanged();
      } else {
        itemsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .almaonline.ItemInfo items = 3;</code>
     */
    public org.example.AlmaOnline.server.ItemInfo.Builder getItemsBuilder(
        int index) {
      return getItemsFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .almaonline.ItemInfo items = 3;</code>
     */
    public org.example.AlmaOnline.server.ItemInfoOrBuilder getItemsOrBuilder(
        int index) {
      if (itemsBuilder_ == null) {
        return items_.get(index);  } else {
        return itemsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .almaonline.ItemInfo items = 3;</code>
     */
    public java.util.List<? extends org.example.AlmaOnline.server.ItemInfoOrBuilder> 
         getItemsOrBuilderList() {
      if (itemsBuilder_ != null) {
        return itemsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(items_);
      }
    }
    /**
     * <code>repeated .almaonline.ItemInfo items = 3;</code>
     */
    public org.example.AlmaOnline.server.ItemInfo.Builder addItemsBuilder() {
      return getItemsFieldBuilder().addBuilder(
          org.example.AlmaOnline.server.ItemInfo.getDefaultInstance());
    }
    /**
     * <code>repeated .almaonline.ItemInfo items = 3;</code>
     */
    public org.example.AlmaOnline.server.ItemInfo.Builder addItemsBuilder(
        int index) {
      return getItemsFieldBuilder().addBuilder(
          index, org.example.AlmaOnline.server.ItemInfo.getDefaultInstance());
    }
    /**
     * <code>repeated .almaonline.ItemInfo items = 3;</code>
     */
    public java.util.List<org.example.AlmaOnline.server.ItemInfo.Builder> 
         getItemsBuilderList() {
      return getItemsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        org.example.AlmaOnline.server.ItemInfo, org.example.AlmaOnline.server.ItemInfo.Builder, org.example.AlmaOnline.server.ItemInfoOrBuilder> 
        getItemsFieldBuilder() {
      if (itemsBuilder_ == null) {
        itemsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            org.example.AlmaOnline.server.ItemInfo, org.example.AlmaOnline.server.ItemInfo.Builder, org.example.AlmaOnline.server.ItemInfoOrBuilder>(
                items_,
                ((bitField0_ & 0x00000002) != 0),
                getParentForChildren(),
                isClean());
        items_ = null;
      }
      return itemsBuilder_;
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


    // @@protoc_insertion_point(builder_scope:almaonline.GetOrderResponse)
  }

  // @@protoc_insertion_point(class_scope:almaonline.GetOrderResponse)
  private static final org.example.AlmaOnline.server.GetOrderResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.example.AlmaOnline.server.GetOrderResponse();
  }

  public static org.example.AlmaOnline.server.GetOrderResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GetOrderResponse>
      PARSER = new com.google.protobuf.AbstractParser<GetOrderResponse>() {
    @java.lang.Override
    public GetOrderResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GetOrderResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GetOrderResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GetOrderResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public org.example.AlmaOnline.server.GetOrderResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

