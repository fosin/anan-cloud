package top.fosin.anan.cloudresource.grpc.dicdetail;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@jakarta.annotation.Generated(
    value = "by gRPC proto compiler (version 1.51.0)",
    comments = "Source: DicDetail.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class DicDetailServiceGrpc {

  private DicDetailServiceGrpc() {}

  public static final String SERVICE_NAME = "top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailReq,
      top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailResp> getFindOneByDicIdAndCodeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "findOneByDicIdAndCode",
      requestType = top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailReq.class,
      responseType = top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailReq,
      top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailResp> getFindOneByDicIdAndCodeMethod() {
    io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailReq, top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailResp> getFindOneByDicIdAndCodeMethod;
    if ((getFindOneByDicIdAndCodeMethod = DicDetailServiceGrpc.getFindOneByDicIdAndCodeMethod) == null) {
      synchronized (DicDetailServiceGrpc.class) {
        if ((getFindOneByDicIdAndCodeMethod = DicDetailServiceGrpc.getFindOneByDicIdAndCodeMethod) == null) {
          DicDetailServiceGrpc.getFindOneByDicIdAndCodeMethod = getFindOneByDicIdAndCodeMethod =
              io.grpc.MethodDescriptor.<top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailReq, top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "findOneByDicIdAndCode"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailResp.getDefaultInstance()))
              .setSchemaDescriptor(new DicDetailServiceMethodDescriptorSupplier("findOneByDicIdAndCode"))
              .build();
        }
      }
    }
    return getFindOneByDicIdAndCodeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq,
      top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailsResp> getListByDicIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "listByDicId",
      requestType = top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq.class,
      responseType = top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailsResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq,
      top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailsResp> getListByDicIdMethod() {
    io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq, top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailsResp> getListByDicIdMethod;
    if ((getListByDicIdMethod = DicDetailServiceGrpc.getListByDicIdMethod) == null) {
      synchronized (DicDetailServiceGrpc.class) {
        if ((getListByDicIdMethod = DicDetailServiceGrpc.getListByDicIdMethod) == null) {
          DicDetailServiceGrpc.getListByDicIdMethod = getListByDicIdMethod =
              io.grpc.MethodDescriptor.<top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq, top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailsResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "listByDicId"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailsResp.getDefaultInstance()))
              .setSchemaDescriptor(new DicDetailServiceMethodDescriptorSupplier("listByDicId"))
              .build();
        }
      }
    }
    return getListByDicIdMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DicDetailServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DicDetailServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DicDetailServiceStub>() {
        @java.lang.Override
        public DicDetailServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DicDetailServiceStub(channel, callOptions);
        }
      };
    return DicDetailServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DicDetailServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DicDetailServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DicDetailServiceBlockingStub>() {
        @java.lang.Override
        public DicDetailServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DicDetailServiceBlockingStub(channel, callOptions);
        }
      };
    return DicDetailServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DicDetailServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DicDetailServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DicDetailServiceFutureStub>() {
        @java.lang.Override
        public DicDetailServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DicDetailServiceFutureStub(channel, callOptions);
        }
      };
    return DicDetailServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class DicDetailServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *通过字典序号查询字典数据
     * </pre>
     */
    public void findOneByDicIdAndCode(top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailReq request,
        io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailResp> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFindOneByDicIdAndCodeMethod(), responseObserver);
    }

    /**
     * <pre>
     *通过字典序号查询字典明细数据
     * </pre>
     */
    public void listByDicId(top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq request,
        io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailsResp> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListByDicIdMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getFindOneByDicIdAndCodeMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailReq,
                top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailResp>(
                  this, METHODID_FIND_ONE_BY_DIC_ID_AND_CODE)))
          .addMethod(
            getListByDicIdMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq,
                top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailsResp>(
                  this, METHODID_LIST_BY_DIC_ID)))
          .build();
    }
  }

  /**
   */
  public static final class DicDetailServiceStub extends io.grpc.stub.AbstractAsyncStub<DicDetailServiceStub> {
    private DicDetailServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DicDetailServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DicDetailServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *通过字典序号查询字典数据
     * </pre>
     */
    public void findOneByDicIdAndCode(top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailReq request,
        io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailResp> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFindOneByDicIdAndCodeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *通过字典序号查询字典明细数据
     * </pre>
     */
    public void listByDicId(top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq request,
        io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailsResp> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListByDicIdMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class DicDetailServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<DicDetailServiceBlockingStub> {
    private DicDetailServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DicDetailServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DicDetailServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *通过字典序号查询字典数据
     * </pre>
     */
    public top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailResp findOneByDicIdAndCode(top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFindOneByDicIdAndCodeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *通过字典序号查询字典明细数据
     * </pre>
     */
    public top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailsResp listByDicId(top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListByDicIdMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class DicDetailServiceFutureStub extends io.grpc.stub.AbstractFutureStub<DicDetailServiceFutureStub> {
    private DicDetailServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DicDetailServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DicDetailServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *通过字典序号查询字典数据
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailResp> findOneByDicIdAndCode(
        top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFindOneByDicIdAndCodeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *通过字典序号查询字典明细数据
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailsResp> listByDicId(
        top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListByDicIdMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_FIND_ONE_BY_DIC_ID_AND_CODE = 0;
  private static final int METHODID_LIST_BY_DIC_ID = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final DicDetailServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(DicDetailServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_FIND_ONE_BY_DIC_ID_AND_CODE:
          serviceImpl.findOneByDicIdAndCode((top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailReq) request,
              (io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailResp>) responseObserver);
          break;
        case METHODID_LIST_BY_DIC_ID:
          serviceImpl.listByDicId((top.fosin.anan.cloudresource.grpc.dicdetail.DicIdReq) request,
              (io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailsResp>) responseObserver);
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

  private static abstract class DicDetailServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DicDetailServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return top.fosin.anan.cloudresource.grpc.dicdetail.DicDetailProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("DicDetailService");
    }
  }

  private static final class DicDetailServiceFileDescriptorSupplier
      extends DicDetailServiceBaseDescriptorSupplier {
    DicDetailServiceFileDescriptorSupplier() {}
  }

  private static final class DicDetailServiceMethodDescriptorSupplier
      extends DicDetailServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    DicDetailServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (DicDetailServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DicDetailServiceFileDescriptorSupplier())
              .addMethod(getFindOneByDicIdAndCodeMethod())
              .addMethod(getListByDicIdMethod())
              .build();
        }
      }
    }
    return result;
  }
}
