package top.fosin.anan.cloudresource.grpc.organiz;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@jakarta.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: Oranization.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class OrganizServiceGrpc {

  private OrganizServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "top.fosin.anan.cloudresource.grpc.organiz.OrganizService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.organiz.OrganizIdReq,
      top.fosin.anan.cloudresource.grpc.organiz.OrganizResp> getFindOneByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "findOneById",
      requestType = top.fosin.anan.cloudresource.grpc.organiz.OrganizIdReq.class,
      responseType = top.fosin.anan.cloudresource.grpc.organiz.OrganizResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.organiz.OrganizIdReq,
      top.fosin.anan.cloudresource.grpc.organiz.OrganizResp> getFindOneByIdMethod() {
    io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.organiz.OrganizIdReq, top.fosin.anan.cloudresource.grpc.organiz.OrganizResp> getFindOneByIdMethod;
    if ((getFindOneByIdMethod = OrganizServiceGrpc.getFindOneByIdMethod) == null) {
      synchronized (OrganizServiceGrpc.class) {
        if ((getFindOneByIdMethod = OrganizServiceGrpc.getFindOneByIdMethod) == null) {
          OrganizServiceGrpc.getFindOneByIdMethod = getFindOneByIdMethod =
              io.grpc.MethodDescriptor.<top.fosin.anan.cloudresource.grpc.organiz.OrganizIdReq, top.fosin.anan.cloudresource.grpc.organiz.OrganizResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "findOneById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  top.fosin.anan.cloudresource.grpc.organiz.OrganizIdReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  top.fosin.anan.cloudresource.grpc.organiz.OrganizResp.getDefaultInstance()))
              .setSchemaDescriptor(new OrganizServiceMethodDescriptorSupplier("findOneById"))
              .build();
        }
      }
    }
    return getFindOneByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq,
      top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp> getListByIdsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "listByIds",
      requestType = top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq.class,
      responseType = top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq,
      top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp> getListByIdsMethod() {
    io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq, top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp> getListByIdsMethod;
    if ((getListByIdsMethod = OrganizServiceGrpc.getListByIdsMethod) == null) {
      synchronized (OrganizServiceGrpc.class) {
        if ((getListByIdsMethod = OrganizServiceGrpc.getListByIdsMethod) == null) {
          OrganizServiceGrpc.getListByIdsMethod = getListByIdsMethod =
              io.grpc.MethodDescriptor.<top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq, top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "listByIds"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp.getDefaultInstance()))
              .setSchemaDescriptor(new OrganizServiceMethodDescriptorSupplier("listByIds"))
              .build();
        }
      }
    }
    return getListByIdsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.organiz.OrganizPidReq,
      top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp> getListChildMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "listChild",
      requestType = top.fosin.anan.cloudresource.grpc.organiz.OrganizPidReq.class,
      responseType = top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.organiz.OrganizPidReq,
      top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp> getListChildMethod() {
    io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.organiz.OrganizPidReq, top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp> getListChildMethod;
    if ((getListChildMethod = OrganizServiceGrpc.getListChildMethod) == null) {
      synchronized (OrganizServiceGrpc.class) {
        if ((getListChildMethod = OrganizServiceGrpc.getListChildMethod) == null) {
          OrganizServiceGrpc.getListChildMethod = getListChildMethod =
              io.grpc.MethodDescriptor.<top.fosin.anan.cloudresource.grpc.organiz.OrganizPidReq, top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "listChild"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  top.fosin.anan.cloudresource.grpc.organiz.OrganizPidReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp.getDefaultInstance()))
              .setSchemaDescriptor(new OrganizServiceMethodDescriptorSupplier("listChild"))
              .build();
        }
      }
    }
    return getListChildMethod;
  }

  private static volatile io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.organiz.OrganizPidReq,
      top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp> getListAllChildMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "listAllChild",
      requestType = top.fosin.anan.cloudresource.grpc.organiz.OrganizPidReq.class,
      responseType = top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.organiz.OrganizPidReq,
      top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp> getListAllChildMethod() {
    io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.organiz.OrganizPidReq, top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp> getListAllChildMethod;
    if ((getListAllChildMethod = OrganizServiceGrpc.getListAllChildMethod) == null) {
      synchronized (OrganizServiceGrpc.class) {
        if ((getListAllChildMethod = OrganizServiceGrpc.getListAllChildMethod) == null) {
          OrganizServiceGrpc.getListAllChildMethod = getListAllChildMethod =
              io.grpc.MethodDescriptor.<top.fosin.anan.cloudresource.grpc.organiz.OrganizPidReq, top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "listAllChild"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  top.fosin.anan.cloudresource.grpc.organiz.OrganizPidReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp.getDefaultInstance()))
              .setSchemaDescriptor(new OrganizServiceMethodDescriptorSupplier("listAllChild"))
              .build();
        }
      }
    }
    return getListAllChildMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static OrganizServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<OrganizServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<OrganizServiceStub>() {
        @java.lang.Override
        public OrganizServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new OrganizServiceStub(channel, callOptions);
        }
      };
    return OrganizServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static OrganizServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<OrganizServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<OrganizServiceBlockingStub>() {
        @java.lang.Override
        public OrganizServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new OrganizServiceBlockingStub(channel, callOptions);
        }
      };
    return OrganizServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static OrganizServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<OrganizServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<OrganizServiceFutureStub>() {
        @java.lang.Override
        public OrganizServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new OrganizServiceFutureStub(channel, callOptions);
        }
      };
    return OrganizServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     * <pre>
     *通过机构序号查询机构数据
     * </pre>
     */
    default void findOneById(top.fosin.anan.cloudresource.grpc.organiz.OrganizIdReq request,
        io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.organiz.OrganizResp> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFindOneByIdMethod(), responseObserver);
    }

    /**
     * <pre>
     *通过多个机构序号查询机构数据集合
     * </pre>
     */
    default void listByIds(top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq request,
        io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListByIdsMethod(), responseObserver);
    }

    /**
     * <pre>
     *通过上级机构序号查询其直接子机构数据
     * </pre>
     */
    default void listChild(top.fosin.anan.cloudresource.grpc.organiz.OrganizPidReq request,
        io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListChildMethod(), responseObserver);
    }

    /**
     * <pre>
     *通过上级机构序号查询其所有子机构数据
     * </pre>
     */
    default void listAllChild(top.fosin.anan.cloudresource.grpc.organiz.OrganizPidReq request,
        io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListAllChildMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service OrganizService.
   */
  public static abstract class OrganizServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return OrganizServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service OrganizService.
   */
  public static final class OrganizServiceStub
      extends io.grpc.stub.AbstractAsyncStub<OrganizServiceStub> {
    private OrganizServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OrganizServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new OrganizServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *通过机构序号查询机构数据
     * </pre>
     */
    public void findOneById(top.fosin.anan.cloudresource.grpc.organiz.OrganizIdReq request,
        io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.organiz.OrganizResp> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFindOneByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *通过多个机构序号查询机构数据集合
     * </pre>
     */
    public void listByIds(top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq request,
        io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListByIdsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *通过上级机构序号查询其直接子机构数据
     * </pre>
     */
    public void listChild(top.fosin.anan.cloudresource.grpc.organiz.OrganizPidReq request,
        io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListChildMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *通过上级机构序号查询其所有子机构数据
     * </pre>
     */
    public void listAllChild(top.fosin.anan.cloudresource.grpc.organiz.OrganizPidReq request,
        io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListAllChildMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service OrganizService.
   */
  public static final class OrganizServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<OrganizServiceBlockingStub> {
    private OrganizServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OrganizServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new OrganizServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *通过机构序号查询机构数据
     * </pre>
     */
    public top.fosin.anan.cloudresource.grpc.organiz.OrganizResp findOneById(top.fosin.anan.cloudresource.grpc.organiz.OrganizIdReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFindOneByIdMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *通过多个机构序号查询机构数据集合
     * </pre>
     */
    public top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp listByIds(top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListByIdsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *通过上级机构序号查询其直接子机构数据
     * </pre>
     */
    public top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp listChild(top.fosin.anan.cloudresource.grpc.organiz.OrganizPidReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListChildMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *通过上级机构序号查询其所有子机构数据
     * </pre>
     */
    public top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp listAllChild(top.fosin.anan.cloudresource.grpc.organiz.OrganizPidReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListAllChildMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service OrganizService.
   */
  public static final class OrganizServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<OrganizServiceFutureStub> {
    private OrganizServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OrganizServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new OrganizServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *通过机构序号查询机构数据
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<top.fosin.anan.cloudresource.grpc.organiz.OrganizResp> findOneById(
        top.fosin.anan.cloudresource.grpc.organiz.OrganizIdReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFindOneByIdMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *通过多个机构序号查询机构数据集合
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp> listByIds(
        top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListByIdsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *通过上级机构序号查询其直接子机构数据
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp> listChild(
        top.fosin.anan.cloudresource.grpc.organiz.OrganizPidReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListChildMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *通过上级机构序号查询其所有子机构数据
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp> listAllChild(
        top.fosin.anan.cloudresource.grpc.organiz.OrganizPidReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListAllChildMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_FIND_ONE_BY_ID = 0;
  private static final int METHODID_LIST_BY_IDS = 1;
  private static final int METHODID_LIST_CHILD = 2;
  private static final int METHODID_LIST_ALL_CHILD = 3;

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
        case METHODID_FIND_ONE_BY_ID:
          serviceImpl.findOneById((top.fosin.anan.cloudresource.grpc.organiz.OrganizIdReq) request,
              (io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.organiz.OrganizResp>) responseObserver);
          break;
        case METHODID_LIST_BY_IDS:
          serviceImpl.listByIds((top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq) request,
              (io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp>) responseObserver);
          break;
        case METHODID_LIST_CHILD:
          serviceImpl.listChild((top.fosin.anan.cloudresource.grpc.organiz.OrganizPidReq) request,
              (io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp>) responseObserver);
          break;
        case METHODID_LIST_ALL_CHILD:
          serviceImpl.listAllChild((top.fosin.anan.cloudresource.grpc.organiz.OrganizPidReq) request,
              (io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp>) responseObserver);
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
          getFindOneByIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              top.fosin.anan.cloudresource.grpc.organiz.OrganizIdReq,
              top.fosin.anan.cloudresource.grpc.organiz.OrganizResp>(
                service, METHODID_FIND_ONE_BY_ID)))
        .addMethod(
          getListByIdsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              top.fosin.anan.cloudresource.grpc.organiz.OrganizIdsReq,
              top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp>(
                service, METHODID_LIST_BY_IDS)))
        .addMethod(
          getListChildMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              top.fosin.anan.cloudresource.grpc.organiz.OrganizPidReq,
              top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp>(
                service, METHODID_LIST_CHILD)))
        .addMethod(
          getListAllChildMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              top.fosin.anan.cloudresource.grpc.organiz.OrganizPidReq,
              top.fosin.anan.cloudresource.grpc.organiz.OrganizsResp>(
                service, METHODID_LIST_ALL_CHILD)))
        .build();
  }

  private static abstract class OrganizServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    OrganizServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return top.fosin.anan.cloudresource.grpc.organiz.OrganizProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("OrganizService");
    }
  }

  private static final class OrganizServiceFileDescriptorSupplier
      extends OrganizServiceBaseDescriptorSupplier {
    OrganizServiceFileDescriptorSupplier() {}
  }

  private static final class OrganizServiceMethodDescriptorSupplier
      extends OrganizServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    OrganizServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (OrganizServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new OrganizServiceFileDescriptorSupplier())
              .addMethod(getFindOneByIdMethod())
              .addMethod(getListByIdsMethod())
              .addMethod(getListChildMethod())
              .addMethod(getListAllChildMethod())
              .build();
        }
      }
    }
    return result;
  }
}
