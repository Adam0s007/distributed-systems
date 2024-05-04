// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: euler.proto

package sr.grpc.gen;

/**
 * Protobuf type {@code euler.EulerInput}
 */
public final class EulerInput extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:euler.EulerInput)
    EulerInputOrBuilder {
private static final long serialVersionUID = 0L;
  // Use EulerInput.newBuilder() to construct.
  private EulerInput(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private EulerInput() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new EulerInput();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return sr.grpc.gen.EulerServiceProto.internal_static_euler_EulerInput_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return sr.grpc.gen.EulerServiceProto.internal_static_euler_EulerInput_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            sr.grpc.gen.EulerInput.class, sr.grpc.gen.EulerInput.Builder.class);
  }

  public static final int TERMS_FIELD_NUMBER = 1;
  private long terms_ = 0L;
  /**
   * <code>int64 terms = 1;</code>
   * @return The terms.
   */
  @java.lang.Override
  public long getTerms() {
    return terms_;
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
    if (terms_ != 0L) {
      output.writeInt64(1, terms_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (terms_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, terms_);
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
    if (!(obj instanceof sr.grpc.gen.EulerInput)) {
      return super.equals(obj);
    }
    sr.grpc.gen.EulerInput other = (sr.grpc.gen.EulerInput) obj;

    if (getTerms()
        != other.getTerms()) return false;
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
    hash = (37 * hash) + TERMS_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getTerms());
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static sr.grpc.gen.EulerInput parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sr.grpc.gen.EulerInput parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sr.grpc.gen.EulerInput parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sr.grpc.gen.EulerInput parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sr.grpc.gen.EulerInput parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sr.grpc.gen.EulerInput parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sr.grpc.gen.EulerInput parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static sr.grpc.gen.EulerInput parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static sr.grpc.gen.EulerInput parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static sr.grpc.gen.EulerInput parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static sr.grpc.gen.EulerInput parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static sr.grpc.gen.EulerInput parseFrom(
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
  public static Builder newBuilder(sr.grpc.gen.EulerInput prototype) {
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
   * Protobuf type {@code euler.EulerInput}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:euler.EulerInput)
      sr.grpc.gen.EulerInputOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return sr.grpc.gen.EulerServiceProto.internal_static_euler_EulerInput_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return sr.grpc.gen.EulerServiceProto.internal_static_euler_EulerInput_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              sr.grpc.gen.EulerInput.class, sr.grpc.gen.EulerInput.Builder.class);
    }

    // Construct using sr.grpc.gen.EulerInput.newBuilder()
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
      terms_ = 0L;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return sr.grpc.gen.EulerServiceProto.internal_static_euler_EulerInput_descriptor;
    }

    @java.lang.Override
    public sr.grpc.gen.EulerInput getDefaultInstanceForType() {
      return sr.grpc.gen.EulerInput.getDefaultInstance();
    }

    @java.lang.Override
    public sr.grpc.gen.EulerInput build() {
      sr.grpc.gen.EulerInput result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public sr.grpc.gen.EulerInput buildPartial() {
      sr.grpc.gen.EulerInput result = new sr.grpc.gen.EulerInput(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(sr.grpc.gen.EulerInput result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.terms_ = terms_;
      }
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof sr.grpc.gen.EulerInput) {
        return mergeFrom((sr.grpc.gen.EulerInput)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(sr.grpc.gen.EulerInput other) {
      if (other == sr.grpc.gen.EulerInput.getDefaultInstance()) return this;
      if (other.getTerms() != 0L) {
        setTerms(other.getTerms());
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
              terms_ = input.readInt64();
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

    private long terms_ ;
    /**
     * <code>int64 terms = 1;</code>
     * @return The terms.
     */
    @java.lang.Override
    public long getTerms() {
      return terms_;
    }
    /**
     * <code>int64 terms = 1;</code>
     * @param value The terms to set.
     * @return This builder for chaining.
     */
    public Builder setTerms(long value) {

      terms_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>int64 terms = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearTerms() {
      bitField0_ = (bitField0_ & ~0x00000001);
      terms_ = 0L;
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


    // @@protoc_insertion_point(builder_scope:euler.EulerInput)
  }

  // @@protoc_insertion_point(class_scope:euler.EulerInput)
  private static final sr.grpc.gen.EulerInput DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new sr.grpc.gen.EulerInput();
  }

  public static sr.grpc.gen.EulerInput getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<EulerInput>
      PARSER = new com.google.protobuf.AbstractParser<EulerInput>() {
    @java.lang.Override
    public EulerInput parsePartialFrom(
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

  public static com.google.protobuf.Parser<EulerInput> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<EulerInput> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public sr.grpc.gen.EulerInput getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

