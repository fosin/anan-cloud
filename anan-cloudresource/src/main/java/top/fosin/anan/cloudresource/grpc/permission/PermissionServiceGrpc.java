package top.fosin.anan.cloudresource.grpc.permission;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.51.0)",
    comments = "Source: Permission.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class PermissionServiceGrpc {

  private PermissionServiceGrpc() {}

  public static final String SERVICE_NAME = "top.fosin.anan.cloudresource.grpc.permission.PermissionService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.permission.ServiceCodeReq,
      top.fosin.anan.cloudresource.grpc.permission.PermissionsResp> getFindByServiceCodeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "findByServiceCode",
      requestType = top.fosin.anan.cloudresource.grpc.permission.ServiceCodeReq.class,
      responseType = top.fosin.anan.cloudresource.grpc.permission.PermissionsResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.permission.ServiceCodeReq,
      top.fosin.anan.cloudresource.grpc.permission.PermissionsResp> getFindByServiceCodeMethod() {
    io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.permission.ServiceCodeReq, top.fosin.anan.cloudresource.grpc.permission.PermissionsResp> getFindByServiceCodeMethod;
    if ((getFindByServiceCodeMethod = PermissionServiceGrpc.getFindByServiceCodeMethod) == null) {
      synchronized (PermissionServiceGrpc.class) {
        if ((getFindByServiceCodeMethod = PermissionServiceGrpc.getFindByServiceCodeMethod) == null) {
          PermissionServiceGrpc.getFindByServiceCodeMethod = getFindByServiceCodeMethod =
              io.grpc.MethodDescriptor.<top.fosin.anan.cloudresource.grpc.permission.ServiceCodeReq, top.fosin.anan.cloudresource.grpc.permission.PermissionsResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "findByServiceCode"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  top.fosin.anan.cloudresource.grpc.permission.ServiceCodeReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  top.fosin.anan.cloudresource.grpc.permission.PermissionsResp.getDefaultInstance()))
              .setSchemaDescriptor(new PermissionServiceMethodDescriptorSupplier("findByServiceCode"))
              .build();
        }
      }
    }
    return getFindByServiceCodeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.permission.ServiceCodesReq,
      top.fosin.anan.cloudresource.grpc.permission.PermissionsResp> getFindByServiceCodesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "findByServiceCodes",
      requestType = top.fosin.anan.cloudresource.grpc.permission.ServiceCodesReq.class,
      responseType = top.fosin.anan.cloudresource.grpc.permission.PermissionsResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.permission.ServiceCodesReq,
      top.fosin.anan.cloudresource.grpc.permission.PermissionsResp> getFindByServiceCodesMethod() {
    io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.permission.ServiceCodesReq, top.fosin.anan.cloudresource.grpc.permission.PermissionsResp> getFindByServiceCodesMethod;
    if ((getFindByServiceCodesMethod = PermissionServiceGrpc.getFindByServiceCodesMethod) == null) {
      synchronized (PermissionServiceGrpc.class) {
        if ((getFindByServiceCodesMethod = PermissionServiceGrpc.getFindByServiceCodesMethod) == null) {
          PermissionServiceGrpc.getFindByServiceCodesMethod = getFindByServiceCodesMethod =
              io.grpc.MethodDescriptor.<top.fosin.anan.cloudresource.grpc.permission.ServiceCodesReq, top.fosin.anan.cloudresource.grpc.permission.PermissionsResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "findByServiceCodes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  top.fosin.anan.cloudresource.grpc.permission.ServiceCodesReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  top.fosin.anan.cloudresource.grpc.permission.PermissionsResp.getDefaultInstance()))
              .setSchemaDescriptor(new PermissionServiceMethodDescriptorSupplier("findByServiceCodes"))
              .build();
        }
      }
    }
    return getFindByServiceCodesMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PermissionServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PermissionServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PermissionServiceStub>() {
        @java.lang.Override
        public PermissionServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PermissionServiceStub(channel, callOptions);
        }
      };
    return PermissionServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PermissionServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PermissionServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PermissionServiceBlockingStub>() {
        @java.lang.Override
        public PermissionServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PermissionServiceBlockingStub(channel, callOptions);
        }
      };
    return PermissionServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PermissionServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PermissionServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PermissionServiceFutureStub>() {
        @java.lang.Override
        public PermissionServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PermissionServiceFutureStub(channel, callOptions);
        }
      };
    return PermissionServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class PermissionServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *通过单个服务编码获取权限清单
     * </pre>
     */
    public void findByServiceCode(top.fosin.anan.cloudresource.grpc.permission.ServiceCodeReq request,
        io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.permission.PermissionsResp> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFindByServiceCodeMethod(), responseObserver);
    }

    /**
     * <pre>
     *通过多个服务编码获取权限清单
     * </pre>
     */
    public void findByServiceCodes(top.fosin.anan.cloudresource.grpc.permission.ServiceCodesReq request,
        io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.permission.PermissionsResp> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFindByServiceCodesMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getFindByServiceCodeMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                top.fosin.anan.cloudresource.grpc.permission.ServiceCodeReq,
                top.fosin.anan.cloudresource.grpc.permission.PermissionsResp>(
                  this, METHODID_FIND_BY_SERVICE_CODE)))
          .addMethod(
            getFindByServiceCodesMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                top.fosin.anan.cloudresource.grpc.permission.ServiceCodesReq,
                top.fosin.anan.cloudresource.grpc.permission.PermissionsResp>(
                  this, METHODID_FIND_BY_SERVICE_CODES)))
          .build();
    }
  }

  /**
   */
  public static final class PermissionServiceStub extends io.grpc.stub.AbstractAsyncStub<PermissionServiceStub> {
    private PermissionServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PermissionServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PermissionServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *通过单个服务编码获取权限清单
     * </pre>
     */
    public void findByServiceCode(top.fosin.anan.cloudresource.grpc.permission.ServiceCodeReq request,
        io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.permission.PermissionsResp> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFindByServiceCodeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *通过多个服务编码获取权限清单
     * </pre>
     */
    public void findByServiceCodes(top.fosin.anan.cloudresource.grpc.permission.ServiceCodesReq request,
        io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.permission.PermissionsResp> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFindByServiceCodesMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class PermissionServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<PermissionServiceBlockingStub> {
    private PermissionServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PermissionServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PermissionServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *通过单个服务编码获取权限清单
     * </pre>
     */
    public top.fosin.anan.cloudresource.grpc.permission.PermissionsResp findByServiceCode(top.fosin.anan.cloudresource.grpc.permission.ServiceCodeReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFindByServiceCodeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *通过多个服务编码获取权限清单
     * </pre>
     */
    public top.fosin.anan.cloudresource.grpc.permission.PermissionsResp findByServiceCodes(top.fosin.anan.cloudresource.grpc.permission.ServiceCodesReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFindByServiceCodesMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class PermissionServiceFutureStub extends io.grpc.stub.AbstractFutureStub<PermissionServiceFutureStub> {
    private PermissionServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PermissionServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PermissionServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *通过单个服务编码获取权限清单
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<top.fosin.anan.cloudresource.grpc.permission.PermissionsResp> findByServiceCode(
        top.fosin.anan.cloudresource.grpc.permission.ServiceCodeReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFindByServiceCodeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *通过多个服务编码获取权限清单
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<top.fosin.anan.cloudresource.grpc.permission.PermissionsResp> findByServiceCodes(
        top.fosin.anan.cloudresource.grpc.permission.ServiceCodesReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFindByServiceCodesMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_FIND_BY_SERVICE_CODE = 0;
  private static final int METHODID_FIND_BY_SERVICE_CODES = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final PermissionServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(PermissionServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_FIND_BY_SERVICE_CODE:
          serviceImpl.findByServiceCode((top.fosin.anan.cloudresource.grpc.permission.ServiceCodeReq) request,
              (io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.permission.PermissionsResp>) responseObserver);
          break;
        case METHODID_FIND_BY_SERVICE_CODES:
          serviceImpl.findByServiceCodes((top.fosin.anan.cloudresource.grpc.permission.ServiceCodesReq) request,
              (io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.permission.PermissionsResp>) responseObserver);
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

  private static abstract class PermissionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PermissionServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return top.fosin.anan.cloudresource.grpc.permission.PermissionProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("PermissionService");
    }
  }

  private static final class PermissionServiceFileDescriptorSupplier
      extends PermissionServiceBaseDescriptorSupplier {
    PermissionServiceFileDescriptorSupplier() {}
  }

  private static final class PermissionServiceMethodDescriptorSupplier
      extends PermissionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    PermissionServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (PermissionServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PermissionServiceFileDescriptorSupplier())
              .addMethod(getFindByServiceCodeMethod())
              .addMethod(getFindByServiceCodesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
