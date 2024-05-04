// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: fibonacci.proto

package sr.grpc.gen;

/**
 * Protobuf type {@code fibonacci.FibonacciList}
 */
public final class FibonacciList extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:fibonacci.FibonacciList)
    FibonacciListOrBuilder {
private static final long serialVersionUID = 0L;
  // Use FibonacciList.newBuilder() to construct.
  private FibonacciList(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private FibonacciList() {
    numbers_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new FibonacciList();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return sr.grpc.gen.FibonacciServiceProto.internal_static_fibonacci_FibonacciList_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return sr.grpc.gen.FibonacciServiceProto.internal_static_fibonacci_FibonacciList_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            sr.grpc.gen.FibonacciList.class, sr.grpc.gen.FibonacciList.Builder.class);
  }

  public static final int NUMBERS_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private java.util.List<sr.grpc.gen.FibonacciInput> numbers_;
  /**
   * <code>repeated .fibonacci.FibonacciInput numbers = 1;</code>
   */
  @java.lang.Override
  public java.util.List<sr.grpc.gen.FibonacciInput> getNumbersList() {
    return numbers_;
  }
  /**
   * <code>repeated .fibonacci.FibonacciInput numbers = 1;</code>
   */
  @java.lang.Override
  public java.util.List<? extends sr.grpc.gen.FibonacciInputOrBuilder> 
      getNumbersOrBuilderList() {
    return numbers_;
  }
  /**
   * <code>repeated .fibonacci.FibonacciInput numbers = 1;</code>
   */
  @java.lang.Override
  public int getNumbersCount() {
    return numbers_.size();
  }
  /**
   * <code>repeated .fibonacci.FibonacciInput numbers = 1;</code>
   */
  @java.lang.Override
  public sr.grpc.gen.FibonacciInput getNumbers(int index) {
    return numbers_.get(index);
  }
  /**
   * <code>repeated .fibonacci.FibonacciInput numbers = 1;</code>
   */
  @java.lang.Override
  public sr.grpc.gen.FibonacciInputOrBuilder getNumbersOrBuilder(
      int index) {
    return numbers_.get(index);
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
    for (int i = 0; i < numbers_.size(); i++) {
      output.writeMessage(1, numbers_.get(i));
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < numbers_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, numbers_.get(i));
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
    if (!(obj instanceof sr.grpc.gen.FibonacciList)) {
      return super.equals(obj);
    }
    sr.grpc.gen.FibonacciList other = (sr.grpc.gen.FibonacciList) obj;

    if (!getNumbersList()
        .equals(other.getNumbersList())) return false;
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
    if (getNumbersCount() > 0) {
      hash = (37 * hash) + NUMBERS_FIELD_NUMBER;
      hash = (53 * hash) + getNumbersList().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static sr.grpc.gen.FibonacciList parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sr.grpc.gen.FibonacciList parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sr.grpc.gen.FibonacciList parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sr.grpc.gen.FibonacciList parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sr.grpc.gen.FibonacciList parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sr.grpc.gen.FibonacciList parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sr.grpc.gen.FibonacciList parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static sr.grpc.gen.FibonacciList parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static sr.grpc.gen.FibonacciList parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static sr.grpc.gen.FibonacciList parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static sr.grpc.gen.FibonacciList parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static sr.grpc.gen.FibonacciList parseFrom(
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
  public static Builder newBuilder(sr.grpc.gen.FibonacciList prototype) {
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
   * Protobuf type {@code fibonacci.FibonacciList}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:fibonacci.FibonacciList)
      sr.grpc.gen.FibonacciListOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return sr.grpc.gen.FibonacciServiceProto.internal_static_fibonacci_FibonacciList_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return sr.grpc.gen.FibonacciServiceProto.internal_static_fibonacci_FibonacciList_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              sr.grpc.gen.FibonacciList.class, sr.grpc.gen.FibonacciList.Builder.class);
    }

    // Construct using sr.grpc.gen.FibonacciList.newBuilder()
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
      if (numbersBuilder_ == null) {
        numbers_ = java.util.Collections.emptyList();
      } else {
        numbers_ = null;
        numbersBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return sr.grpc.gen.FibonacciServiceProto.internal_static_fibonacci_FibonacciList_descriptor;
    }

    @java.lang.Override
    public sr.grpc.gen.FibonacciList getDefaultInstanceForType() {
      return sr.grpc.gen.FibonacciList.getDefaultInstance();
    }

    @java.lang.Override
    public sr.grpc.gen.FibonacciList build() {
      sr.grpc.gen.FibonacciList result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public sr.grpc.gen.FibonacciList buildPartial() {
      sr.grpc.gen.FibonacciList result = new sr.grpc.gen.FibonacciList(this);
      buildPartialRepeatedFields(result);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartialRepeatedFields(sr.grpc.gen.FibonacciList result) {
      if (numbersBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          numbers_ = java.util.Collections.unmodifiableList(numbers_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.numbers_ = numbers_;
      } else {
        result.numbers_ = numbersBuilder_.build();
      }
    }

    private void buildPartial0(sr.grpc.gen.FibonacciList result) {
      int from_bitField0_ = bitField0_;
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof sr.grpc.gen.FibonacciList) {
        return mergeFrom((sr.grpc.gen.FibonacciList)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(sr.grpc.gen.FibonacciList other) {
      if (other == sr.grpc.gen.FibonacciList.getDefaultInstance()) return this;
      if (numbersBuilder_ == null) {
        if (!other.numbers_.isEmpty()) {
          if (numbers_.isEmpty()) {
            numbers_ = other.numbers_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureNumbersIsMutable();
            numbers_.addAll(other.numbers_);
          }
          onChanged();
        }
      } else {
        if (!other.numbers_.isEmpty()) {
          if (numbersBuilder_.isEmpty()) {
            numbersBuilder_.dispose();
            numbersBuilder_ = null;
            numbers_ = other.numbers_;
            bitField0_ = (bitField0_ & ~0x00000001);
            numbersBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getNumbersFieldBuilder() : null;
          } else {
            numbersBuilder_.addAllMessages(other.numbers_);
          }
        }
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
            case 10: {
              sr.grpc.gen.FibonacciInput m =
                  input.readMessage(
                      sr.grpc.gen.FibonacciInput.parser(),
                      extensionRegistry);
              if (numbersBuilder_ == null) {
                ensureNumbersIsMutable();
                numbers_.add(m);
              } else {
                numbersBuilder_.addMessage(m);
              }
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

    private java.util.List<sr.grpc.gen.FibonacciInput> numbers_ =
      java.util.Collections.emptyList();
    private void ensureNumbersIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        numbers_ = new java.util.ArrayList<sr.grpc.gen.FibonacciInput>(numbers_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        sr.grpc.gen.FibonacciInput, sr.grpc.gen.FibonacciInput.Builder, sr.grpc.gen.FibonacciInputOrBuilder> numbersBuilder_;

    /**
     * <code>repeated .fibonacci.FibonacciInput numbers = 1;</code>
     */
    public java.util.List<sr.grpc.gen.FibonacciInput> getNumbersList() {
      if (numbersBuilder_ == null) {
        return java.util.Collections.unmodifiableList(numbers_);
      } else {
        return numbersBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .fibonacci.FibonacciInput numbers = 1;</code>
     */
    public int getNumbersCount() {
      if (numbersBuilder_ == null) {
        return numbers_.size();
      } else {
        return numbersBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .fibonacci.FibonacciInput numbers = 1;</code>
     */
    public sr.grpc.gen.FibonacciInput getNumbers(int index) {
      if (numbersBuilder_ == null) {
        return numbers_.get(index);
      } else {
        return numbersBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .fibonacci.FibonacciInput numbers = 1;</code>
     */
    public Builder setNumbers(
        int index, sr.grpc.gen.FibonacciInput value) {
      if (numbersBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureNumbersIsMutable();
        numbers_.set(index, value);
        onChanged();
      } else {
        numbersBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .fibonacci.FibonacciInput numbers = 1;</code>
     */
    public Builder setNumbers(
        int index, sr.grpc.gen.FibonacciInput.Builder builderForValue) {
      if (numbersBuilder_ == null) {
        ensureNumbersIsMutable();
        numbers_.set(index, builderForValue.build());
        onChanged();
      } else {
        numbersBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .fibonacci.FibonacciInput numbers = 1;</code>
     */
    public Builder addNumbers(sr.grpc.gen.FibonacciInput value) {
      if (numbersBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureNumbersIsMutable();
        numbers_.add(value);
        onChanged();
      } else {
        numbersBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .fibonacci.FibonacciInput numbers = 1;</code>
     */
    public Builder addNumbers(
        int index, sr.grpc.gen.FibonacciInput value) {
      if (numbersBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureNumbersIsMutable();
        numbers_.add(index, value);
        onChanged();
      } else {
        numbersBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .fibonacci.FibonacciInput numbers = 1;</code>
     */
    public Builder addNumbers(
        sr.grpc.gen.FibonacciInput.Builder builderForValue) {
      if (numbersBuilder_ == null) {
        ensureNumbersIsMutable();
        numbers_.add(builderForValue.build());
        onChanged();
      } else {
        numbersBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .fibonacci.FibonacciInput numbers = 1;</code>
     */
    public Builder addNumbers(
        int index, sr.grpc.gen.FibonacciInput.Builder builderForValue) {
      if (numbersBuilder_ == null) {
        ensureNumbersIsMutable();
        numbers_.add(index, builderForValue.build());
        onChanged();
      } else {
        numbersBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .fibonacci.FibonacciInput numbers = 1;</code>
     */
    public Builder addAllNumbers(
        java.lang.Iterable<? extends sr.grpc.gen.FibonacciInput> values) {
      if (numbersBuilder_ == null) {
        ensureNumbersIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, numbers_);
        onChanged();
      } else {
        numbersBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .fibonacci.FibonacciInput numbers = 1;</code>
     */
    public Builder clearNumbers() {
      if (numbersBuilder_ == null) {
        numbers_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        numbersBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .fibonacci.FibonacciInput numbers = 1;</code>
     */
    public Builder removeNumbers(int index) {
      if (numbersBuilder_ == null) {
        ensureNumbersIsMutable();
        numbers_.remove(index);
        onChanged();
      } else {
        numbersBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .fibonacci.FibonacciInput numbers = 1;</code>
     */
    public sr.grpc.gen.FibonacciInput.Builder getNumbersBuilder(
        int index) {
      return getNumbersFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .fibonacci.FibonacciInput numbers = 1;</code>
     */
    public sr.grpc.gen.FibonacciInputOrBuilder getNumbersOrBuilder(
        int index) {
      if (numbersBuilder_ == null) {
        return numbers_.get(index);  } else {
        return numbersBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .fibonacci.FibonacciInput numbers = 1;</code>
     */
    public java.util.List<? extends sr.grpc.gen.FibonacciInputOrBuilder> 
         getNumbersOrBuilderList() {
      if (numbersBuilder_ != null) {
        return numbersBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(numbers_);
      }
    }
    /**
     * <code>repeated .fibonacci.FibonacciInput numbers = 1;</code>
     */
    public sr.grpc.gen.FibonacciInput.Builder addNumbersBuilder() {
      return getNumbersFieldBuilder().addBuilder(
          sr.grpc.gen.FibonacciInput.getDefaultInstance());
    }
    /**
     * <code>repeated .fibonacci.FibonacciInput numbers = 1;</code>
     */
    public sr.grpc.gen.FibonacciInput.Builder addNumbersBuilder(
        int index) {
      return getNumbersFieldBuilder().addBuilder(
          index, sr.grpc.gen.FibonacciInput.getDefaultInstance());
    }
    /**
     * <code>repeated .fibonacci.FibonacciInput numbers = 1;</code>
     */
    public java.util.List<sr.grpc.gen.FibonacciInput.Builder> 
         getNumbersBuilderList() {
      return getNumbersFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        sr.grpc.gen.FibonacciInput, sr.grpc.gen.FibonacciInput.Builder, sr.grpc.gen.FibonacciInputOrBuilder> 
        getNumbersFieldBuilder() {
      if (numbersBuilder_ == null) {
        numbersBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            sr.grpc.gen.FibonacciInput, sr.grpc.gen.FibonacciInput.Builder, sr.grpc.gen.FibonacciInputOrBuilder>(
                numbers_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        numbers_ = null;
      }
      return numbersBuilder_;
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


    // @@protoc_insertion_point(builder_scope:fibonacci.FibonacciList)
  }

  // @@protoc_insertion_point(class_scope:fibonacci.FibonacciList)
  private static final sr.grpc.gen.FibonacciList DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new sr.grpc.gen.FibonacciList();
  }

  public static sr.grpc.gen.FibonacciList getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<FibonacciList>
      PARSER = new com.google.protobuf.AbstractParser<FibonacciList>() {
    @java.lang.Override
    public FibonacciList parsePartialFrom(
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

  public static com.google.protobuf.Parser<FibonacciList> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<FibonacciList> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public sr.grpc.gen.FibonacciList getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
