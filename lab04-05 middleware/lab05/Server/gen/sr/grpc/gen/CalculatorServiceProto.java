// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: calculator.proto

package sr.grpc.gen;

public final class CalculatorServiceProto {
  private CalculatorServiceProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_calculator_ArithmeticOpArguments_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_calculator_ArithmeticOpArguments_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_calculator_ArithmeticOpResult_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_calculator_ArithmeticOpResult_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\020calculator.proto\022\ncalculator\"P\n\025Arithm" +
      "eticOpArguments\022)\n\006opType\030\001 \001(\0162\031.calcul" +
      "ator.OperationType\022\014\n\004args\030\002 \003(\001\"!\n\022Arit" +
      "hmeticOpResult\022\013\n\003res\030\001 \001(\001*u\n\rOperation" +
      "Type\022\007\n\003SUM\020\000\022\007\n\003AVG\020\001\022\010\n\004MULT\020\002\022\007\n\003MIN\020" +
      "\003\022\007\n\003MAX\020\004\022\014\n\010VARIANCE\020\005\022\n\n\006STDDEV\020\006\022\021\n\r" +
      "MOST_FREQUENT\020\007\022\t\n\005RANGE\020\0102e\n\021Calculator" +
      "Service\022P\n\tOperation\022!.calculator.Arithm" +
      "eticOpArguments\032\036.calculator.ArithmeticO" +
      "pResult\"\000B\'\n\013sr.grpc.genB\026CalculatorServ" +
      "iceProtoP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_calculator_ArithmeticOpArguments_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_calculator_ArithmeticOpArguments_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_calculator_ArithmeticOpArguments_descriptor,
        new java.lang.String[] { "OpType", "Args", });
    internal_static_calculator_ArithmeticOpResult_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_calculator_ArithmeticOpResult_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_calculator_ArithmeticOpResult_descriptor,
        new java.lang.String[] { "Res", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
