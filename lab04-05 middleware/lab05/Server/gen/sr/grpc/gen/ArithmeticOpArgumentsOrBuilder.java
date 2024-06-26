// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: calculator.proto

package sr.grpc.gen;

public interface ArithmeticOpArgumentsOrBuilder extends
    // @@protoc_insertion_point(interface_extends:calculator.ArithmeticOpArguments)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.calculator.OperationType opType = 1;</code>
   * @return The enum numeric value on the wire for opType.
   */
  int getOpTypeValue();
  /**
   * <code>.calculator.OperationType opType = 1;</code>
   * @return The opType.
   */
  sr.grpc.gen.OperationType getOpType();

  /**
   * <code>repeated double args = 2;</code>
   * @return A list containing the args.
   */
  java.util.List<java.lang.Double> getArgsList();
  /**
   * <code>repeated double args = 2;</code>
   * @return The count of args.
   */
  int getArgsCount();
  /**
   * <code>repeated double args = 2;</code>
   * @param index The index of the element to return.
   * @return The args at the given index.
   */
  double getArgs(int index);
}
