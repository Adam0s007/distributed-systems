package sr.grpc.gen;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.0)",
    comments = "Source: fibonacci.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class FibonacciServiceGrpc {

  private FibonacciServiceGrpc() {}

  public static final String SERVICE_NAME = "fibonacci.FibonacciService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sr.grpc.gen.FibonacciList,
      sr.grpc.gen.FibonacciNumber> getFindFibonacciNumbersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FindFibonacciNumbers",
      requestType = sr.grpc.gen.FibonacciList.class,
      responseType = sr.grpc.gen.FibonacciNumber.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<sr.grpc.gen.FibonacciList,
      sr.grpc.gen.FibonacciNumber> getFindFibonacciNumbersMethod() {
    io.grpc.MethodDescriptor<sr.grpc.gen.FibonacciList, sr.grpc.gen.FibonacciNumber> getFindFibonacciNumbersMethod;
    if ((getFindFibonacciNumbersMethod = FibonacciServiceGrpc.getFindFibonacciNumbersMethod) == null) {
      synchronized (FibonacciServiceGrpc.class) {
        if ((getFindFibonacciNumbersMethod = FibonacciServiceGrpc.getFindFibonacciNumbersMethod) == null) {
          FibonacciServiceGrpc.getFindFibonacciNumbersMethod = getFindFibonacciNumbersMethod =
              io.grpc.MethodDescriptor.<sr.grpc.gen.FibonacciList, sr.grpc.gen.FibonacciNumber>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FindFibonacciNumbers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.FibonacciList.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.FibonacciNumber.getDefaultInstance()))
              .setSchemaDescriptor(new FibonacciServiceMethodDescriptorSupplier("FindFibonacciNumbers"))
              .build();
        }
      }
    }
    return getFindFibonacciNumbersMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FibonacciServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FibonacciServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FibonacciServiceStub>() {
        @java.lang.Override
        public FibonacciServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FibonacciServiceStub(channel, callOptions);
        }
      };
    return FibonacciServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FibonacciServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FibonacciServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FibonacciServiceBlockingStub>() {
        @java.lang.Override
        public FibonacciServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FibonacciServiceBlockingStub(channel, callOptions);
        }
      };
    return FibonacciServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FibonacciServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FibonacciServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FibonacciServiceFutureStub>() {
        @java.lang.Override
        public FibonacciServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FibonacciServiceFutureStub(channel, callOptions);
        }
      };
    return FibonacciServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void findFibonacciNumbers(sr.grpc.gen.FibonacciList request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.FibonacciNumber> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFindFibonacciNumbersMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service FibonacciService.
   */
  public static abstract class FibonacciServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return FibonacciServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service FibonacciService.
   */
  public static final class FibonacciServiceStub
      extends io.grpc.stub.AbstractAsyncStub<FibonacciServiceStub> {
    private FibonacciServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FibonacciServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FibonacciServiceStub(channel, callOptions);
    }

    /**
     */
    public void findFibonacciNumbers(sr.grpc.gen.FibonacciList request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.FibonacciNumber> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getFindFibonacciNumbersMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service FibonacciService.
   */
  public static final class FibonacciServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<FibonacciServiceBlockingStub> {
    private FibonacciServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FibonacciServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FibonacciServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<sr.grpc.gen.FibonacciNumber> findFibonacciNumbers(
        sr.grpc.gen.FibonacciList request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getFindFibonacciNumbersMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service FibonacciService.
   */
  public static final class FibonacciServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<FibonacciServiceFutureStub> {
    private FibonacciServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FibonacciServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FibonacciServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_FIND_FIBONACCI_NUMBERS = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_FIND_FIBONACCI_NUMBERS:
          serviceImpl.findFibonacciNumbers((sr.grpc.gen.FibonacciList) request,
              (io.grpc.stub.StreamObserver<sr.grpc.gen.FibonacciNumber>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getFindFibonacciNumbersMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              sr.grpc.gen.FibonacciList,
              sr.grpc.gen.FibonacciNumber>(
                service, METHODID_FIND_FIBONACCI_NUMBERS)))
        .build();
  }

  private static abstract class FibonacciServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FibonacciServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return sr.grpc.gen.FibonacciServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FibonacciService");
    }
  }

  private static final class FibonacciServiceFileDescriptorSupplier
      extends FibonacciServiceBaseDescriptorSupplier {
    FibonacciServiceFileDescriptorSupplier() {}
  }

  private static final class FibonacciServiceMethodDescriptorSupplier
      extends FibonacciServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    FibonacciServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (FibonacciServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FibonacciServiceFileDescriptorSupplier())
              .addMethod(getFindFibonacciNumbersMethod())
              .build();
        }
      }
    }
    return result;
  }
}
