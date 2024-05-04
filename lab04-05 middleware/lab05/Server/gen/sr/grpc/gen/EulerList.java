// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: euler.proto

package sr.grpc.gen;

/**
 * Protobuf type {@code euler.EulerList}
 */
public final class EulerList extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:euler.EulerList)
    EulerListOrBuilder {
private static final long serialVersionUID = 0L;
  // Use EulerList.newBuilder() to construct.
  private EulerList(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private EulerList() {
    terms_ = emptyDoubleList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new EulerList();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return sr.grpc.gen.EulerServiceProto.internal_static_euler_EulerList_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return sr.grpc.gen.EulerServiceProto.internal_static_euler_EulerList_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            sr.grpc.gen.EulerList.class, sr.grpc.gen.EulerList.Builder.class);
  }

  public static final int TERMS_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private com.google.protobuf.Internal.DoubleList terms_;
  /**
   * <code>repeated double terms = 1;</code>
   * @return A list containing the terms.
   */
  @java.lang.Override
  public java.util.List<java.lang.Double>
      getTermsList() {
    return terms_;
  }
  /**
   * <code>repeated double terms = 1;</code>
   * @return The count of terms.
   */
  public int getTermsCount() {
    return terms_.size();
  }
  /**
   * <code>repeated double terms = 1;</code>
   * @param index The index of the element to return.
   * @return The terms at the given index.
   */
  public double getTerms(int index) {
    return terms_.getDouble(index);
  }
  private int termsMemoizedSerializedSize = -1;

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
    if (getTermsList().size() > 0) {
      output.writeUInt32NoTag(10);
      output.writeUInt32NoTag(termsMemoizedSerializedSize);
    }
    for (int i = 0; i < terms_.size(); i++) {
      output.writeDoubleNoTag(terms_.getDouble(i));
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    {
      int dataSize = 0;
      dataSize = 8 * getTermsList().size();
      size += dataSize;
      if (!getTermsList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      termsMemoizedSerializedSize = dataSize;
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
    if (!(obj instanceof sr.grpc.gen.EulerList)) {
      return super.equals(obj);
    }
    sr.grpc.gen.EulerList other = (sr.grpc.gen.EulerList) obj;

    if (!getTermsList()
        .equals(other.getTermsList())) return false;
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
    if (getTermsCount() > 0) {
      hash = (37 * hash) + TERMS_FIELD_NUMBER;
      hash = (53 * hash) + getTermsList().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static sr.grpc.gen.EulerList parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sr.grpc.gen.EulerList parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sr.grpc.gen.EulerList parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sr.grpc.gen.EulerList parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sr.grpc.gen.EulerList parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sr.grpc.gen.EulerList parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sr.grpc.gen.EulerList parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static sr.grpc.gen.EulerList parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static sr.grpc.gen.EulerList parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static sr.grpc.gen.EulerList parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static sr.grpc.gen.EulerList parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static sr.grpc.gen.EulerList parseFrom(
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
  public static Builder newBuilder(sr.grpc.gen.EulerList prototype) {
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
   * Protobuf type {@code euler.EulerList}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:euler.EulerList)
      sr.grpc.gen.EulerListOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return sr.grpc.gen.EulerServiceProto.internal_static_euler_EulerList_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return sr.grpc.gen.EulerServiceProto.internal_static_euler_EulerList_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              sr.grpc.gen.EulerList.class, sr.grpc.gen.EulerList.Builder.class);
    }

    // Construct using sr.grpc.gen.EulerList.newBuilder()
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
      terms_ = emptyDoubleList();
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return sr.grpc.gen.EulerServiceProto.internal_static_euler_EulerList_descriptor;
    }

    @java.lang.Override
    public sr.grpc.gen.EulerList getDefaultInstanceForType() {
      return sr.grpc.gen.EulerList.getDefaultInstance();
    }

    @java.lang.Override
    public sr.grpc.gen.EulerList build() {
      sr.grpc.gen.EulerList result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public sr.grpc.gen.EulerList buildPartial() {
      sr.grpc.gen.EulerList result = new sr.grpc.gen.EulerList(this);
      buildPartialRepeatedFields(result);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartialRepeatedFields(sr.grpc.gen.EulerList result) {
      if (((bitField0_ & 0x00000001) != 0)) {
        terms_.makeImmutable();
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.terms_ = terms_;
    }

    private void buildPartial0(sr.grpc.gen.EulerList result) {
      int from_bitField0_ = bitField0_;
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof sr.grpc.gen.EulerList) {
        return mergeFrom((sr.grpc.gen.EulerList)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(sr.grpc.gen.EulerList other) {
      if (other == sr.grpc.gen.EulerList.getDefaultInstance()) return this;
      if (!other.terms_.isEmpty()) {
        if (terms_.isEmpty()) {
          terms_ = other.terms_;
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          ensureTermsIsMutable();
          terms_.addAll(other.terms_);
        }
        onChanged();
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
            case 9: {
              double v = input.readDouble();
              ensureTermsIsMutable();
              terms_.addDouble(v);
              break;
            } // case 9
            case 10: {
              int length = input.readRawVarint32();
              int limit = input.pushLimit(length);
              ensureTermsIsMutable();
              while (input.getBytesUntilLimit() > 0) {
                terms_.addDouble(input.readDouble());
              }
              input.popLimit(limit);
              break;
            } // case 10
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

    private com.google.protobuf.Internal.DoubleList terms_ = emptyDoubleList();
    private void ensureTermsIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        terms_ = mutableCopy(terms_);
        bitField0_ |= 0x00000001;
      }
    }
    /**
     * <code>repeated double terms = 1;</code>
     * @return A list containing the terms.
     */
    public java.util.List<java.lang.Double>
        getTermsList() {
      return ((bitField0_ & 0x00000001) != 0) ?
               java.util.Collections.unmodifiableList(terms_) : terms_;
    }
    /**
     * <code>repeated double terms = 1;</code>
     * @return The count of terms.
     */
    public int getTermsCount() {
      return terms_.size();
    }
    /**
     * <code>repeated double terms = 1;</code>
     * @param index The index of the element to return.
     * @return The terms at the given index.
     */
    public double getTerms(int index) {
      return terms_.getDouble(index);
    }
    /**
     * <code>repeated double terms = 1;</code>
     * @param index The index to set the value at.
     * @param value The terms to set.
     * @return This builder for chaining.
     */
    public Builder setTerms(
        int index, double value) {

      ensureTermsIsMutable();
      terms_.setDouble(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated double terms = 1;</code>
     * @param value The terms to add.
     * @return This builder for chaining.
     */
    public Builder addTerms(double value) {

      ensureTermsIsMutable();
      terms_.addDouble(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated double terms = 1;</code>
     * @param values The terms to add.
     * @return This builder for chaining.
     */
    public Builder addAllTerms(
        java.lang.Iterable<? extends java.lang.Double> values) {
      ensureTermsIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, terms_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated double terms = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearTerms() {
      terms_ = emptyDoubleList();
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


    // @@protoc_insertion_point(builder_scope:euler.EulerList)
  }

  // @@protoc_insertion_point(class_scope:euler.EulerList)
  private static final sr.grpc.gen.EulerList DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new sr.grpc.gen.EulerList();
  }

  public static sr.grpc.gen.EulerList getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<EulerList>
      PARSER = new com.google.protobuf.AbstractParser<EulerList>() {
    @java.lang.Override
    public EulerList parsePartialFrom(
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

  public static com.google.protobuf.Parser<EulerList> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<EulerList> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public sr.grpc.gen.EulerList getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

