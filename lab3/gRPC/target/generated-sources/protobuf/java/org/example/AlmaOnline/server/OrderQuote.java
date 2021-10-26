// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: AlmaOnline.proto

package org.example.AlmaOnline.server;

/**
 * Protobuf type {@code almaonline.OrderQuote}
 */
public final class OrderQuote extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:almaonline.OrderQuote)
    OrderQuoteOrBuilder {
private static final long serialVersionUID = 0L;
  // Use OrderQuote.newBuilder() to construct.
  private OrderQuote(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private OrderQuote() {
    id_ = "";
    customer_ = "";
    lineItems_ = com.google.protobuf.LazyStringArrayList.EMPTY;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new OrderQuote();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private OrderQuote(
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
            id_ = s;
            break;
          }
          case 18: {
            org.example.AlmaOnline.server.Date.Builder subBuilder = null;
            if (creationDate_ != null) {
              subBuilder = creationDate_.toBuilder();
            }
            creationDate_ = input.readMessage(org.example.AlmaOnline.server.Date.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(creationDate_);
              creationDate_ = subBuilder.buildPartial();
            }

            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();
            bitField0_ |= 0x00000002;
            customer_ = s;
            break;
          }
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();
            if (!((mutable_bitField0_ & 0x00000004) != 0)) {
              lineItems_ = new com.google.protobuf.LazyStringArrayList();
              mutable_bitField0_ |= 0x00000004;
            }
            lineItems_.add(s);
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
      if (((mutable_bitField0_ & 0x00000004) != 0)) {
        lineItems_ = lineItems_.getUnmodifiableView();
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.example.AlmaOnline.server.AlmaOnlineProto.internal_static_almaonline_OrderQuote_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.example.AlmaOnline.server.AlmaOnlineProto.internal_static_almaonline_OrderQuote_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.example.AlmaOnline.server.OrderQuote.class, org.example.AlmaOnline.server.OrderQuote.Builder.class);
  }

  private int bitField0_;
  public static final int ID_FIELD_NUMBER = 1;
  private volatile java.lang.Object id_;
  /**
   * <code>string id = 1;</code>
   * @return Whether the id field is set.
   */
  @java.lang.Override
  public boolean hasId() {
    return ((bitField0_ & 0x00000001) != 0);
  }
  /**
   * <code>string id = 1;</code>
   * @return The id.
   */
  @java.lang.Override
  public java.lang.String getId() {
    java.lang.Object ref = id_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      id_ = s;
      return s;
    }
  }
  /**
   * <code>string id = 1;</code>
   * @return The bytes for id.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getIdBytes() {
    java.lang.Object ref = id_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      id_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int CREATIONDATE_FIELD_NUMBER = 2;
  private org.example.AlmaOnline.server.Date creationDate_;
  /**
   * <code>.almaonline.Date creationDate = 2;</code>
   * @return Whether the creationDate field is set.
   */
  @java.lang.Override
  public boolean hasCreationDate() {
    return creationDate_ != null;
  }
  /**
   * <code>.almaonline.Date creationDate = 2;</code>
   * @return The creationDate.
   */
  @java.lang.Override
  public org.example.AlmaOnline.server.Date getCreationDate() {
    return creationDate_ == null ? org.example.AlmaOnline.server.Date.getDefaultInstance() : creationDate_;
  }
  /**
   * <code>.almaonline.Date creationDate = 2;</code>
   */
  @java.lang.Override
  public org.example.AlmaOnline.server.DateOrBuilder getCreationDateOrBuilder() {
    return getCreationDate();
  }

  public static final int CUSTOMER_FIELD_NUMBER = 3;
  private volatile java.lang.Object customer_;
  /**
   * <code>string customer = 3;</code>
   * @return Whether the customer field is set.
   */
  @java.lang.Override
  public boolean hasCustomer() {
    return ((bitField0_ & 0x00000002) != 0);
  }
  /**
   * <code>string customer = 3;</code>
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
   * <code>string customer = 3;</code>
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

  public static final int LINEITEMS_FIELD_NUMBER = 4;
  private com.google.protobuf.LazyStringList lineItems_;
  /**
   * <code>repeated string lineItems = 4;</code>
   * @return A list containing the lineItems.
   */
  public com.google.protobuf.ProtocolStringList
      getLineItemsList() {
    return lineItems_;
  }
  /**
   * <code>repeated string lineItems = 4;</code>
   * @return The count of lineItems.
   */
  public int getLineItemsCount() {
    return lineItems_.size();
  }
  /**
   * <code>repeated string lineItems = 4;</code>
   * @param index The index of the element to return.
   * @return The lineItems at the given index.
   */
  public java.lang.String getLineItems(int index) {
    return lineItems_.get(index);
  }
  /**
   * <code>repeated string lineItems = 4;</code>
   * @param index The index of the value to return.
   * @return The bytes of the lineItems at the given index.
   */
  public com.google.protobuf.ByteString
      getLineItemsBytes(int index) {
    return lineItems_.getByteString(index);
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
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, id_);
    }
    if (creationDate_ != null) {
      output.writeMessage(2, getCreationDate());
    }
    if (((bitField0_ & 0x00000002) != 0)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, customer_);
    }
    for (int i = 0; i < lineItems_.size(); i++) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, lineItems_.getRaw(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) != 0)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, id_);
    }
    if (creationDate_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getCreationDate());
    }
    if (((bitField0_ & 0x00000002) != 0)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, customer_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < lineItems_.size(); i++) {
        dataSize += computeStringSizeNoTag(lineItems_.getRaw(i));
      }
      size += dataSize;
      size += 1 * getLineItemsList().size();
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
    if (!(obj instanceof org.example.AlmaOnline.server.OrderQuote)) {
      return super.equals(obj);
    }
    org.example.AlmaOnline.server.OrderQuote other = (org.example.AlmaOnline.server.OrderQuote) obj;

    if (hasId() != other.hasId()) return false;
    if (hasId()) {
      if (!getId()
          .equals(other.getId())) return false;
    }
    if (hasCreationDate() != other.hasCreationDate()) return false;
    if (hasCreationDate()) {
      if (!getCreationDate()
          .equals(other.getCreationDate())) return false;
    }
    if (hasCustomer() != other.hasCustomer()) return false;
    if (hasCustomer()) {
      if (!getCustomer()
          .equals(other.getCustomer())) return false;
    }
    if (!getLineItemsList()
        .equals(other.getLineItemsList())) return false;
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
    if (hasId()) {
      hash = (37 * hash) + ID_FIELD_NUMBER;
      hash = (53 * hash) + getId().hashCode();
    }
    if (hasCreationDate()) {
      hash = (37 * hash) + CREATIONDATE_FIELD_NUMBER;
      hash = (53 * hash) + getCreationDate().hashCode();
    }
    if (hasCustomer()) {
      hash = (37 * hash) + CUSTOMER_FIELD_NUMBER;
      hash = (53 * hash) + getCustomer().hashCode();
    }
    if (getLineItemsCount() > 0) {
      hash = (37 * hash) + LINEITEMS_FIELD_NUMBER;
      hash = (53 * hash) + getLineItemsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.example.AlmaOnline.server.OrderQuote parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.example.AlmaOnline.server.OrderQuote parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.example.AlmaOnline.server.OrderQuote parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.example.AlmaOnline.server.OrderQuote parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.example.AlmaOnline.server.OrderQuote parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.example.AlmaOnline.server.OrderQuote parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.example.AlmaOnline.server.OrderQuote parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.example.AlmaOnline.server.OrderQuote parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.example.AlmaOnline.server.OrderQuote parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static org.example.AlmaOnline.server.OrderQuote parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.example.AlmaOnline.server.OrderQuote parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.example.AlmaOnline.server.OrderQuote parseFrom(
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
  public static Builder newBuilder(org.example.AlmaOnline.server.OrderQuote prototype) {
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
   * Protobuf type {@code almaonline.OrderQuote}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:almaonline.OrderQuote)
      org.example.AlmaOnline.server.OrderQuoteOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.example.AlmaOnline.server.AlmaOnlineProto.internal_static_almaonline_OrderQuote_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.example.AlmaOnline.server.AlmaOnlineProto.internal_static_almaonline_OrderQuote_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.example.AlmaOnline.server.OrderQuote.class, org.example.AlmaOnline.server.OrderQuote.Builder.class);
    }

    // Construct using org.example.AlmaOnline.server.OrderQuote.newBuilder()
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
      id_ = "";
      bitField0_ = (bitField0_ & ~0x00000001);
      if (creationDateBuilder_ == null) {
        creationDate_ = null;
      } else {
        creationDate_ = null;
        creationDateBuilder_ = null;
      }
      customer_ = "";
      bitField0_ = (bitField0_ & ~0x00000002);
      lineItems_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000004);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.example.AlmaOnline.server.AlmaOnlineProto.internal_static_almaonline_OrderQuote_descriptor;
    }

    @java.lang.Override
    public org.example.AlmaOnline.server.OrderQuote getDefaultInstanceForType() {
      return org.example.AlmaOnline.server.OrderQuote.getDefaultInstance();
    }

    @java.lang.Override
    public org.example.AlmaOnline.server.OrderQuote build() {
      org.example.AlmaOnline.server.OrderQuote result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public org.example.AlmaOnline.server.OrderQuote buildPartial() {
      org.example.AlmaOnline.server.OrderQuote result = new org.example.AlmaOnline.server.OrderQuote(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        to_bitField0_ |= 0x00000001;
      }
      result.id_ = id_;
      if (creationDateBuilder_ == null) {
        result.creationDate_ = creationDate_;
      } else {
        result.creationDate_ = creationDateBuilder_.build();
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        to_bitField0_ |= 0x00000002;
      }
      result.customer_ = customer_;
      if (((bitField0_ & 0x00000004) != 0)) {
        lineItems_ = lineItems_.getUnmodifiableView();
        bitField0_ = (bitField0_ & ~0x00000004);
      }
      result.lineItems_ = lineItems_;
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
      if (other instanceof org.example.AlmaOnline.server.OrderQuote) {
        return mergeFrom((org.example.AlmaOnline.server.OrderQuote)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.example.AlmaOnline.server.OrderQuote other) {
      if (other == org.example.AlmaOnline.server.OrderQuote.getDefaultInstance()) return this;
      if (other.hasId()) {
        bitField0_ |= 0x00000001;
        id_ = other.id_;
        onChanged();
      }
      if (other.hasCreationDate()) {
        mergeCreationDate(other.getCreationDate());
      }
      if (other.hasCustomer()) {
        bitField0_ |= 0x00000002;
        customer_ = other.customer_;
        onChanged();
      }
      if (!other.lineItems_.isEmpty()) {
        if (lineItems_.isEmpty()) {
          lineItems_ = other.lineItems_;
          bitField0_ = (bitField0_ & ~0x00000004);
        } else {
          ensureLineItemsIsMutable();
          lineItems_.addAll(other.lineItems_);
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
      org.example.AlmaOnline.server.OrderQuote parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (org.example.AlmaOnline.server.OrderQuote) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.lang.Object id_ = "";
    /**
     * <code>string id = 1;</code>
     * @return Whether the id field is set.
     */
    public boolean hasId() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>string id = 1;</code>
     * @return The id.
     */
    public java.lang.String getId() {
      java.lang.Object ref = id_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        id_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string id = 1;</code>
     * @return The bytes for id.
     */
    public com.google.protobuf.ByteString
        getIdBytes() {
      java.lang.Object ref = id_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        id_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string id = 1;</code>
     * @param value The id to set.
     * @return This builder for chaining.
     */
    public Builder setId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      id_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      id_ = getDefaultInstance().getId();
      onChanged();
      return this;
    }
    /**
     * <code>string id = 1;</code>
     * @param value The bytes for id to set.
     * @return This builder for chaining.
     */
    public Builder setIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      bitField0_ |= 0x00000001;
      id_ = value;
      onChanged();
      return this;
    }

    private org.example.AlmaOnline.server.Date creationDate_;
    private com.google.protobuf.SingleFieldBuilderV3<
        org.example.AlmaOnline.server.Date, org.example.AlmaOnline.server.Date.Builder, org.example.AlmaOnline.server.DateOrBuilder> creationDateBuilder_;
    /**
     * <code>.almaonline.Date creationDate = 2;</code>
     * @return Whether the creationDate field is set.
     */
    public boolean hasCreationDate() {
      return creationDateBuilder_ != null || creationDate_ != null;
    }
    /**
     * <code>.almaonline.Date creationDate = 2;</code>
     * @return The creationDate.
     */
    public org.example.AlmaOnline.server.Date getCreationDate() {
      if (creationDateBuilder_ == null) {
        return creationDate_ == null ? org.example.AlmaOnline.server.Date.getDefaultInstance() : creationDate_;
      } else {
        return creationDateBuilder_.getMessage();
      }
    }
    /**
     * <code>.almaonline.Date creationDate = 2;</code>
     */
    public Builder setCreationDate(org.example.AlmaOnline.server.Date value) {
      if (creationDateBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        creationDate_ = value;
        onChanged();
      } else {
        creationDateBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.almaonline.Date creationDate = 2;</code>
     */
    public Builder setCreationDate(
        org.example.AlmaOnline.server.Date.Builder builderForValue) {
      if (creationDateBuilder_ == null) {
        creationDate_ = builderForValue.build();
        onChanged();
      } else {
        creationDateBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.almaonline.Date creationDate = 2;</code>
     */
    public Builder mergeCreationDate(org.example.AlmaOnline.server.Date value) {
      if (creationDateBuilder_ == null) {
        if (creationDate_ != null) {
          creationDate_ =
            org.example.AlmaOnline.server.Date.newBuilder(creationDate_).mergeFrom(value).buildPartial();
        } else {
          creationDate_ = value;
        }
        onChanged();
      } else {
        creationDateBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.almaonline.Date creationDate = 2;</code>
     */
    public Builder clearCreationDate() {
      if (creationDateBuilder_ == null) {
        creationDate_ = null;
        onChanged();
      } else {
        creationDate_ = null;
        creationDateBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.almaonline.Date creationDate = 2;</code>
     */
    public org.example.AlmaOnline.server.Date.Builder getCreationDateBuilder() {
      
      onChanged();
      return getCreationDateFieldBuilder().getBuilder();
    }
    /**
     * <code>.almaonline.Date creationDate = 2;</code>
     */
    public org.example.AlmaOnline.server.DateOrBuilder getCreationDateOrBuilder() {
      if (creationDateBuilder_ != null) {
        return creationDateBuilder_.getMessageOrBuilder();
      } else {
        return creationDate_ == null ?
            org.example.AlmaOnline.server.Date.getDefaultInstance() : creationDate_;
      }
    }
    /**
     * <code>.almaonline.Date creationDate = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        org.example.AlmaOnline.server.Date, org.example.AlmaOnline.server.Date.Builder, org.example.AlmaOnline.server.DateOrBuilder> 
        getCreationDateFieldBuilder() {
      if (creationDateBuilder_ == null) {
        creationDateBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            org.example.AlmaOnline.server.Date, org.example.AlmaOnline.server.Date.Builder, org.example.AlmaOnline.server.DateOrBuilder>(
                getCreationDate(),
                getParentForChildren(),
                isClean());
        creationDate_ = null;
      }
      return creationDateBuilder_;
    }

    private java.lang.Object customer_ = "";
    /**
     * <code>string customer = 3;</code>
     * @return Whether the customer field is set.
     */
    public boolean hasCustomer() {
      return ((bitField0_ & 0x00000002) != 0);
    }
    /**
     * <code>string customer = 3;</code>
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
     * <code>string customer = 3;</code>
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
     * <code>string customer = 3;</code>
     * @param value The customer to set.
     * @return This builder for chaining.
     */
    public Builder setCustomer(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      customer_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string customer = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearCustomer() {
      bitField0_ = (bitField0_ & ~0x00000002);
      customer_ = getDefaultInstance().getCustomer();
      onChanged();
      return this;
    }
    /**
     * <code>string customer = 3;</code>
     * @param value The bytes for customer to set.
     * @return This builder for chaining.
     */
    public Builder setCustomerBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      bitField0_ |= 0x00000002;
      customer_ = value;
      onChanged();
      return this;
    }

    private com.google.protobuf.LazyStringList lineItems_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    private void ensureLineItemsIsMutable() {
      if (!((bitField0_ & 0x00000004) != 0)) {
        lineItems_ = new com.google.protobuf.LazyStringArrayList(lineItems_);
        bitField0_ |= 0x00000004;
       }
    }
    /**
     * <code>repeated string lineItems = 4;</code>
     * @return A list containing the lineItems.
     */
    public com.google.protobuf.ProtocolStringList
        getLineItemsList() {
      return lineItems_.getUnmodifiableView();
    }
    /**
     * <code>repeated string lineItems = 4;</code>
     * @return The count of lineItems.
     */
    public int getLineItemsCount() {
      return lineItems_.size();
    }
    /**
     * <code>repeated string lineItems = 4;</code>
     * @param index The index of the element to return.
     * @return The lineItems at the given index.
     */
    public java.lang.String getLineItems(int index) {
      return lineItems_.get(index);
    }
    /**
     * <code>repeated string lineItems = 4;</code>
     * @param index The index of the value to return.
     * @return The bytes of the lineItems at the given index.
     */
    public com.google.protobuf.ByteString
        getLineItemsBytes(int index) {
      return lineItems_.getByteString(index);
    }
    /**
     * <code>repeated string lineItems = 4;</code>
     * @param index The index to set the value at.
     * @param value The lineItems to set.
     * @return This builder for chaining.
     */
    public Builder setLineItems(
        int index, java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureLineItemsIsMutable();
      lineItems_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string lineItems = 4;</code>
     * @param value The lineItems to add.
     * @return This builder for chaining.
     */
    public Builder addLineItems(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureLineItemsIsMutable();
      lineItems_.add(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string lineItems = 4;</code>
     * @param values The lineItems to add.
     * @return This builder for chaining.
     */
    public Builder addAllLineItems(
        java.lang.Iterable<java.lang.String> values) {
      ensureLineItemsIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, lineItems_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string lineItems = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearLineItems() {
      lineItems_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000004);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string lineItems = 4;</code>
     * @param value The bytes of the lineItems to add.
     * @return This builder for chaining.
     */
    public Builder addLineItemsBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      ensureLineItemsIsMutable();
      lineItems_.add(value);
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


    // @@protoc_insertion_point(builder_scope:almaonline.OrderQuote)
  }

  // @@protoc_insertion_point(class_scope:almaonline.OrderQuote)
  private static final org.example.AlmaOnline.server.OrderQuote DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.example.AlmaOnline.server.OrderQuote();
  }

  public static org.example.AlmaOnline.server.OrderQuote getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<OrderQuote>
      PARSER = new com.google.protobuf.AbstractParser<OrderQuote>() {
    @java.lang.Override
    public OrderQuote parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new OrderQuote(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<OrderQuote> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<OrderQuote> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public org.example.AlmaOnline.server.OrderQuote getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
