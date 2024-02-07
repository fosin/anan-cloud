package top.fosin.anan.cloudresource.grpc.user;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@jakarta.annotation.Generated(
    value = "by gRPC proto compiler (version 1.51.0)",
    comments = "Source: User.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class UserServiceGrpc {

  private UserServiceGrpc() {}

  public static final String SERVICE_NAME = "top.fosin.anan.cloudresource.grpc.user.UserService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.user.UserIdReq,
      top.fosin.anan.cloudresource.grpc.user.UserResp> getFindOneByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "findOneById",
      requestType = top.fosin.anan.cloudresource.grpc.user.UserIdReq.class,
      responseType = top.fosin.anan.cloudresource.grpc.user.UserResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.user.UserIdReq,
      top.fosin.anan.cloudresource.grpc.user.UserResp> getFindOneByIdMethod() {
    io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.user.UserIdReq, top.fosin.anan.cloudresource.grpc.user.UserResp> getFindOneByIdMethod;
    if ((getFindOneByIdMethod = UserServiceGrpc.getFindOneByIdMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getFindOneByIdMethod = UserServiceGrpc.getFindOneByIdMethod) == null) {
          UserServiceGrpc.getFindOneByIdMethod = getFindOneByIdMethod =
              io.grpc.MethodDescriptor.<top.fosin.anan.cloudresource.grpc.user.UserIdReq, top.fosin.anan.cloudresource.grpc.user.UserResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "findOneById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  top.fosin.anan.cloudresource.grpc.user.UserIdReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  top.fosin.anan.cloudresource.grpc.user.UserResp.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("findOneById"))
              .build();
        }
      }
    }
    return getFindOneByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.user.UserIdsReq,
      top.fosin.anan.cloudresource.grpc.user.UsersResp> getListByIdsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "listByIds",
      requestType = top.fosin.anan.cloudresource.grpc.user.UserIdsReq.class,
      responseType = top.fosin.anan.cloudresource.grpc.user.UsersResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.user.UserIdsReq,
      top.fosin.anan.cloudresource.grpc.user.UsersResp> getListByIdsMethod() {
    io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.user.UserIdsReq, top.fosin.anan.cloudresource.grpc.user.UsersResp> getListByIdsMethod;
    if ((getListByIdsMethod = UserServiceGrpc.getListByIdsMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getListByIdsMethod = UserServiceGrpc.getListByIdsMethod) == null) {
          UserServiceGrpc.getListByIdsMethod = getListByIdsMethod =
              io.grpc.MethodDescriptor.<top.fosin.anan.cloudresource.grpc.user.UserIdsReq, top.fosin.anan.cloudresource.grpc.user.UsersResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "listByIds"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  top.fosin.anan.cloudresource.grpc.user.UserIdsReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  top.fosin.anan.cloudresource.grpc.user.UsersResp.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("listByIds"))
              .build();
        }
      }
    }
    return getListByIdsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.user.UsercodeReq,
      top.fosin.anan.cloudresource.grpc.user.UserResp> getFindOneByUsercodeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "findOneByUsercode",
      requestType = top.fosin.anan.cloudresource.grpc.user.UsercodeReq.class,
      responseType = top.fosin.anan.cloudresource.grpc.user.UserResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.user.UsercodeReq,
      top.fosin.anan.cloudresource.grpc.user.UserResp> getFindOneByUsercodeMethod() {
    io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.user.UsercodeReq, top.fosin.anan.cloudresource.grpc.user.UserResp> getFindOneByUsercodeMethod;
    if ((getFindOneByUsercodeMethod = UserServiceGrpc.getFindOneByUsercodeMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getFindOneByUsercodeMethod = UserServiceGrpc.getFindOneByUsercodeMethod) == null) {
          UserServiceGrpc.getFindOneByUsercodeMethod = getFindOneByUsercodeMethod =
              io.grpc.MethodDescriptor.<top.fosin.anan.cloudresource.grpc.user.UsercodeReq, top.fosin.anan.cloudresource.grpc.user.UserResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "findOneByUsercode"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  top.fosin.anan.cloudresource.grpc.user.UsercodeReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  top.fosin.anan.cloudresource.grpc.user.UserResp.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("findOneByUsercode"))
              .build();
        }
      }
    }
    return getFindOneByUsercodeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.user.OrganizReq,
      top.fosin.anan.cloudresource.grpc.user.UsersResp> getListByOrganizIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "listByOrganizId",
      requestType = top.fosin.anan.cloudresource.grpc.user.OrganizReq.class,
      responseType = top.fosin.anan.cloudresource.grpc.user.UsersResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.user.OrganizReq,
      top.fosin.anan.cloudresource.grpc.user.UsersResp> getListByOrganizIdMethod() {
    io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.user.OrganizReq, top.fosin.anan.cloudresource.grpc.user.UsersResp> getListByOrganizIdMethod;
    if ((getListByOrganizIdMethod = UserServiceGrpc.getListByOrganizIdMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getListByOrganizIdMethod = UserServiceGrpc.getListByOrganizIdMethod) == null) {
          UserServiceGrpc.getListByOrganizIdMethod = getListByOrganizIdMethod =
              io.grpc.MethodDescriptor.<top.fosin.anan.cloudresource.grpc.user.OrganizReq, top.fosin.anan.cloudresource.grpc.user.UsersResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "listByOrganizId"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  top.fosin.anan.cloudresource.grpc.user.OrganizReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  top.fosin.anan.cloudresource.grpc.user.UsersResp.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("listByOrganizId"))
              .build();
        }
      }
    }
    return getListByOrganizIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.user.TopOrganizReq,
      top.fosin.anan.cloudresource.grpc.user.UsersResp> getListAllChildByTopIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "listAllChildByTopId",
      requestType = top.fosin.anan.cloudresource.grpc.user.TopOrganizReq.class,
      responseType = top.fosin.anan.cloudresource.grpc.user.UsersResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.user.TopOrganizReq,
      top.fosin.anan.cloudresource.grpc.user.UsersResp> getListAllChildByTopIdMethod() {
    io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.user.TopOrganizReq, top.fosin.anan.cloudresource.grpc.user.UsersResp> getListAllChildByTopIdMethod;
    if ((getListAllChildByTopIdMethod = UserServiceGrpc.getListAllChildByTopIdMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getListAllChildByTopIdMethod = UserServiceGrpc.getListAllChildByTopIdMethod) == null) {
          UserServiceGrpc.getListAllChildByTopIdMethod = getListAllChildByTopIdMethod =
              io.grpc.MethodDescriptor.<top.fosin.anan.cloudresource.grpc.user.TopOrganizReq, top.fosin.anan.cloudresource.grpc.user.UsersResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "listAllChildByTopId"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  top.fosin.anan.cloudresource.grpc.user.TopOrganizReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  top.fosin.anan.cloudresource.grpc.user.UsersResp.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("listAllChildByTopId"))
              .build();
        }
      }
    }
    return getListAllChildByTopIdMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UserServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserServiceStub>() {
        @java.lang.Override
        public UserServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserServiceStub(channel, callOptions);
        }
      };
    return UserServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UserServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserServiceBlockingStub>() {
        @java.lang.Override
        public UserServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserServiceBlockingStub(channel, callOptions);
        }
      };
    return UserServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UserServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserServiceFutureStub>() {
        @java.lang.Override
        public UserServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserServiceFutureStub(channel, callOptions);
        }
      };
    return UserServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class UserServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *通过用户序号查询用户数据
     * </pre>
     */
    public void findOneById(top.fosin.anan.cloudresource.grpc.user.UserIdReq request,
        io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.user.UserResp> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFindOneByIdMethod(), responseObserver);
    }

    /**
     * <pre>
     *通过多个用户序号查询用户数据集合
     * </pre>
     */
    public void listByIds(top.fosin.anan.cloudresource.grpc.user.UserIdsReq request,
        io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.user.UsersResp> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListByIdsMethod(), responseObserver);
    }

    /**
     * <pre>
     *通过用户工号查询用户数据
     * </pre>
     */
    public void findOneByUsercode(top.fosin.anan.cloudresource.grpc.user.UsercodeReq request,
        io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.user.UserResp> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFindOneByUsercodeMethod(), responseObserver);
    }

    /**
     * <pre>
     *通过多个用户序号查询用户数据集合
     * </pre>
     */
    public void listByOrganizId(top.fosin.anan.cloudresource.grpc.user.OrganizReq request,
        io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.user.UsersResp> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListByOrganizIdMethod(), responseObserver);
    }

    /**
     * <pre>
     *通过多个用户序号查询用户数据集合
     * </pre>
     */
    public void listAllChildByTopId(top.fosin.anan.cloudresource.grpc.user.TopOrganizReq request,
        io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.user.UsersResp> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListAllChildByTopIdMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getFindOneByIdMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                top.fosin.anan.cloudresource.grpc.user.UserIdReq,
                top.fosin.anan.cloudresource.grpc.user.UserResp>(
                  this, METHODID_FIND_ONE_BY_ID)))
          .addMethod(
            getListByIdsMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                top.fosin.anan.cloudresource.grpc.user.UserIdsReq,
                top.fosin.anan.cloudresource.grpc.user.UsersResp>(
                  this, METHODID_LIST_BY_IDS)))
          .addMethod(
            getFindOneByUsercodeMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                top.fosin.anan.cloudresource.grpc.user.UsercodeReq,
                top.fosin.anan.cloudresource.grpc.user.UserResp>(
                  this, METHODID_FIND_ONE_BY_USERCODE)))
          .addMethod(
            getListByOrganizIdMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                top.fosin.anan.cloudresource.grpc.user.OrganizReq,
                top.fosin.anan.cloudresource.grpc.user.UsersResp>(
                  this, METHODID_LIST_BY_ORGANIZ_ID)))
          .addMethod(
            getListAllChildByTopIdMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                top.fosin.anan.cloudresource.grpc.user.TopOrganizReq,
                top.fosin.anan.cloudresource.grpc.user.UsersResp>(
                  this, METHODID_LIST_ALL_CHILD_BY_TOP_ID)))
          .build();
    }
  }

  /**
   */
  public static final class UserServiceStub extends io.grpc.stub.AbstractAsyncStub<UserServiceStub> {
    private UserServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *通过用户序号查询用户数据
     * </pre>
     */
    public void findOneById(top.fosin.anan.cloudresource.grpc.user.UserIdReq request,
        io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.user.UserResp> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFindOneByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *通过多个用户序号查询用户数据集合
     * </pre>
     */
    public void listByIds(top.fosin.anan.cloudresource.grpc.user.UserIdsReq request,
        io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.user.UsersResp> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListByIdsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *通过用户工号查询用户数据
     * </pre>
     */
    public void findOneByUsercode(top.fosin.anan.cloudresource.grpc.user.UsercodeReq request,
        io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.user.UserResp> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFindOneByUsercodeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *通过多个用户序号查询用户数据集合
     * </pre>
     */
    public void listByOrganizId(top.fosin.anan.cloudresource.grpc.user.OrganizReq request,
        io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.user.UsersResp> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListByOrganizIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *通过多个用户序号查询用户数据集合
     * </pre>
     */
    public void listAllChildByTopId(top.fosin.anan.cloudresource.grpc.user.TopOrganizReq request,
        io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.user.UsersResp> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListAllChildByTopIdMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class UserServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<UserServiceBlockingStub> {
    private UserServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *通过用户序号查询用户数据
     * </pre>
     */
    public top.fosin.anan.cloudresource.grpc.user.UserResp findOneById(top.fosin.anan.cloudresource.grpc.user.UserIdReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFindOneByIdMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *通过多个用户序号查询用户数据集合
     * </pre>
     */
    public top.fosin.anan.cloudresource.grpc.user.UsersResp listByIds(top.fosin.anan.cloudresource.grpc.user.UserIdsReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListByIdsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *通过用户工号查询用户数据
     * </pre>
     */
    public top.fosin.anan.cloudresource.grpc.user.UserResp findOneByUsercode(top.fosin.anan.cloudresource.grpc.user.UsercodeReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFindOneByUsercodeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *通过多个用户序号查询用户数据集合
     * </pre>
     */
    public top.fosin.anan.cloudresource.grpc.user.UsersResp listByOrganizId(top.fosin.anan.cloudresource.grpc.user.OrganizReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListByOrganizIdMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *通过多个用户序号查询用户数据集合
     * </pre>
     */
    public top.fosin.anan.cloudresource.grpc.user.UsersResp listAllChildByTopId(top.fosin.anan.cloudresource.grpc.user.TopOrganizReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListAllChildByTopIdMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class UserServiceFutureStub extends io.grpc.stub.AbstractFutureStub<UserServiceFutureStub> {
    private UserServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *通过用户序号查询用户数据
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<top.fosin.anan.cloudresource.grpc.user.UserResp> findOneById(
        top.fosin.anan.cloudresource.grpc.user.UserIdReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFindOneByIdMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *通过多个用户序号查询用户数据集合
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<top.fosin.anan.cloudresource.grpc.user.UsersResp> listByIds(
        top.fosin.anan.cloudresource.grpc.user.UserIdsReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListByIdsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *通过用户工号查询用户数据
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<top.fosin.anan.cloudresource.grpc.user.UserResp> findOneByUsercode(
        top.fosin.anan.cloudresource.grpc.user.UsercodeReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFindOneByUsercodeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *通过多个用户序号查询用户数据集合
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<top.fosin.anan.cloudresource.grpc.user.UsersResp> listByOrganizId(
        top.fosin.anan.cloudresource.grpc.user.OrganizReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListByOrganizIdMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *通过多个用户序号查询用户数据集合
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<top.fosin.anan.cloudresource.grpc.user.UsersResp> listAllChildByTopId(
        top.fosin.anan.cloudresource.grpc.user.TopOrganizReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListAllChildByTopIdMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_FIND_ONE_BY_ID = 0;
  private static final int METHODID_LIST_BY_IDS = 1;
  private static final int METHODID_FIND_ONE_BY_USERCODE = 2;
  private static final int METHODID_LIST_BY_ORGANIZ_ID = 3;
  private static final int METHODID_LIST_ALL_CHILD_BY_TOP_ID = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final UserServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(UserServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_FIND_ONE_BY_ID:
          serviceImpl.findOneById((top.fosin.anan.cloudresource.grpc.user.UserIdReq) request,
              (io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.user.UserResp>) responseObserver);
          break;
        case METHODID_LIST_BY_IDS:
          serviceImpl.listByIds((top.fosin.anan.cloudresource.grpc.user.UserIdsReq) request,
              (io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.user.UsersResp>) responseObserver);
          break;
        case METHODID_FIND_ONE_BY_USERCODE:
          serviceImpl.findOneByUsercode((top.fosin.anan.cloudresource.grpc.user.UsercodeReq) request,
              (io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.user.UserResp>) responseObserver);
          break;
        case METHODID_LIST_BY_ORGANIZ_ID:
          serviceImpl.listByOrganizId((top.fosin.anan.cloudresource.grpc.user.OrganizReq) request,
              (io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.user.UsersResp>) responseObserver);
          break;
        case METHODID_LIST_ALL_CHILD_BY_TOP_ID:
          serviceImpl.listAllChildByTopId((top.fosin.anan.cloudresource.grpc.user.TopOrganizReq) request,
              (io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.user.UsersResp>) responseObserver);
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

  private static abstract class UserServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UserServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return top.fosin.anan.cloudresource.grpc.user.UserProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("UserService");
    }
  }

  private static final class UserServiceFileDescriptorSupplier
      extends UserServiceBaseDescriptorSupplier {
    UserServiceFileDescriptorSupplier() {}
  }

  private static final class UserServiceMethodDescriptorSupplier
      extends UserServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    UserServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (UserServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UserServiceFileDescriptorSupplier())
              .addMethod(getFindOneByIdMethod())
              .addMethod(getListByIdsMethod())
              .addMethod(getFindOneByUsercodeMethod())
              .addMethod(getListByOrganizIdMethod())
              .addMethod(getListAllChildByTopIdMethod())
              .build();
        }
      }
    }
    return result;
  }
}
