package sr.grpc.gen;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.0)",
    comments = "Source: euler.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class EulerServiceGrpc {

  private EulerServiceGrpc() {}

  public static final String SERVICE_NAME = "euler.EulerService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sr.grpc.gen.EulerInput,
      sr.grpc.gen.EulerStream> getStreamEulerNumberMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StreamEulerNumber",
      requestType = sr.grpc.gen.EulerInput.class,
      responseType = sr.grpc.gen.EulerStream.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<sr.grpc.gen.EulerInput,
      sr.grpc.gen.EulerStream> getStreamEulerNumberMethod() {
    io.grpc.MethodDescriptor<sr.grpc.gen.EulerInput, sr.grpc.gen.EulerStream> getStreamEulerNumberMethod;
    if ((getStreamEulerNumberMethod = EulerServiceGrpc.getStreamEulerNumberMethod) == null) {
      synchronized (EulerServiceGrpc.class) {
        if ((getStreamEulerNumberMethod = EulerServiceGrpc.getStreamEulerNumberMethod) == null) {
          EulerServiceGrpc.getStreamEulerNumberMethod = getStreamEulerNumberMethod =
              io.grpc.MethodDescriptor.<sr.grpc.gen.EulerInput, sr.grpc.gen.EulerStream>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StreamEulerNumber"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.EulerInput.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.EulerStream.getDefaultInstance()))
              .setSchemaDescriptor(new EulerServiceMethodDescriptorSupplier("StreamEulerNumber"))
              .build();
        }
      }
    }
    return getStreamEulerNumberMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sr.grpc.gen.EulerInput,
      sr.grpc.gen.EulerList> getListEulerNumberMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListEulerNumber",
      requestType = sr.grpc.gen.EulerInput.class,
      responseType = sr.grpc.gen.EulerList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sr.grpc.gen.EulerInput,
      sr.grpc.gen.EulerList> getListEulerNumberMethod() {
    io.grpc.MethodDescriptor<sr.grpc.gen.EulerInput, sr.grpc.gen.EulerList> getListEulerNumberMethod;
    if ((getListEulerNumberMethod = EulerServiceGrpc.getListEulerNumberMethod) == null) {
      synchronized (EulerServiceGrpc.class) {
        if ((getListEulerNumberMethod = EulerServiceGrpc.getListEulerNumberMethod) == null) {
          EulerServiceGrpc.getListEulerNumberMethod = getListEulerNumberMethod =
              io.grpc.MethodDescriptor.<sr.grpc.gen.EulerInput, sr.grpc.gen.EulerList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListEulerNumber"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.EulerInput.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.grpc.gen.EulerList.getDefaultInstance()))
              .setSchemaDescriptor(new EulerServiceMethodDescriptorSupplier("ListEulerNumber"))
              .build();
        }
      }
    }
    return getListEulerNumberMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EulerServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EulerServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EulerServiceStub>() {
        @java.lang.Override
        public EulerServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EulerServiceStub(channel, callOptions);
        }
      };
    return EulerServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EulerServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EulerServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EulerServiceBlockingStub>() {
        @java.lang.Override
        public EulerServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EulerServiceBlockingStub(channel, callOptions);
        }
      };
    return EulerServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static EulerServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EulerServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EulerServiceFutureStub>() {
        @java.lang.Override
        public EulerServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EulerServiceFutureStub(channel, callOptions);
        }
      };
    return EulerServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void streamEulerNumber(sr.grpc.gen.EulerInput request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.EulerStream> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStreamEulerNumberMethod(), responseObserver);
    }

    /**
     */
    default void listEulerNumber(sr.grpc.gen.EulerInput request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.EulerList> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListEulerNumberMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service EulerService.
   */
  public static abstract class EulerServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return EulerServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service EulerService.
   */
  public static final class EulerServiceStub
      extends io.grpc.stub.AbstractAsyncStub<EulerServiceStub> {
    private EulerServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EulerServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EulerServiceStub(channel, callOptions);
    }

    /**
     */
    public void streamEulerNumber(sr.grpc.gen.EulerInput request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.EulerStream> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getStreamEulerNumberMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listEulerNumber(sr.grpc.gen.EulerInput request,
        io.grpc.stub.StreamObserver<sr.grpc.gen.EulerList> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListEulerNumberMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service EulerService.
   */
  public static final class EulerServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<EulerServiceBlockingStub> {
    private EulerServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EulerServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EulerServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<sr.grpc.gen.EulerStream> streamEulerNumber(
        sr.grpc.gen.EulerInput request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getStreamEulerNumberMethod(), getCallOptions(), request);
    }

    /**
     */
    public sr.grpc.gen.EulerList listEulerNumber(sr.grpc.gen.EulerInput request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListEulerNumberMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service EulerService.
   */
  public static final class EulerServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<EulerServiceFutureStub> {
    private EulerServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EulerServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EulerServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sr.grpc.gen.EulerList> listEulerNumber(
        sr.grpc.gen.EulerInput request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListEulerNumberMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_STREAM_EULER_NUMBER = 0;
  private static final int METHODID_LIST_EULER_NUMBER = 1;

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
        case METHODID_STREAM_EULER_NUMBER:
          serviceImpl.streamEulerNumber((sr.grpc.gen.EulerInput) request,
              (io.grpc.stub.StreamObserver<sr.grpc.gen.EulerStream>) responseObserver);
          break;
        case METHODID_LIST_EULER_NUMBER:
          serviceImpl.listEulerNumber((sr.grpc.gen.EulerInput) request,
              (io.grpc.stub.StreamObserver<sr.grpc.gen.EulerList>) responseObserver);
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
          getStreamEulerNumberMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              sr.grpc.gen.EulerInput,
              sr.grpc.gen.EulerStream>(
                service, METHODID_STREAM_EULER_NUMBER)))
        .addMethod(
          getListEulerNumberMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sr.grpc.gen.EulerInput,
              sr.grpc.gen.EulerList>(
                service, METHODID_LIST_EULER_NUMBER)))
        .build();
  }

  private static abstract class EulerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    EulerServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return sr.grpc.gen.EulerServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("EulerService");
    }
  }

  private static final class EulerServiceFileDescriptorSupplier
      extends EulerServiceBaseDescriptorSupplier {
    EulerServiceFileDescriptorSupplier() {}
  }

  private static final class EulerServiceMethodDescriptorSupplier
      extends EulerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    EulerServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (EulerServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EulerServiceFileDescriptorSupplier())
              .addMethod(getStreamEulerNumberMethod())
              .addMethod(getListEulerNumberMethod())
              .build();
        }
      }
    }
    return result;
  }
}
