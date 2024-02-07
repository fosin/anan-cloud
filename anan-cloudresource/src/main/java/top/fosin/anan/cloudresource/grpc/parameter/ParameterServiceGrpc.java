package top.fosin.anan.cloudresource.grpc.parameter;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@jakarta.annotation.Generated(
    value = "by gRPC proto compiler (version 1.51.0)",
    comments = "Source: Parameter.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ParameterServiceGrpc {

  private ParameterServiceGrpc() {}

  public static final String SERVICE_NAME = "top.fosin.anan.cloudresource.grpc.parameter.ParameterService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq,
      com.google.protobuf.BoolValue> getApplyChangeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "applyChange",
      requestType = top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq.class,
      responseType = com.google.protobuf.BoolValue.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq,
      com.google.protobuf.BoolValue> getApplyChangeMethod() {
    io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq, com.google.protobuf.BoolValue> getApplyChangeMethod;
    if ((getApplyChangeMethod = ParameterServiceGrpc.getApplyChangeMethod) == null) {
      synchronized (ParameterServiceGrpc.class) {
        if ((getApplyChangeMethod = ParameterServiceGrpc.getApplyChangeMethod) == null) {
          ParameterServiceGrpc.getApplyChangeMethod = getApplyChangeMethod =
              io.grpc.MethodDescriptor.<top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq, com.google.protobuf.BoolValue>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "applyChange"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.BoolValue.getDefaultInstance()))
              .setSchemaDescriptor(new ParameterServiceMethodDescriptorSupplier("applyChange"))
              .build();
        }
      }
    }
    return getApplyChangeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.parameter.ParameterIdsReq,
      com.google.protobuf.BoolValue> getApplyChangesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "applyChanges",
      requestType = top.fosin.anan.cloudresource.grpc.parameter.ParameterIdsReq.class,
      responseType = com.google.protobuf.BoolValue.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.parameter.ParameterIdsReq,
      com.google.protobuf.BoolValue> getApplyChangesMethod() {
    io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.parameter.ParameterIdsReq, com.google.protobuf.BoolValue> getApplyChangesMethod;
    if ((getApplyChangesMethod = ParameterServiceGrpc.getApplyChangesMethod) == null) {
      synchronized (ParameterServiceGrpc.class) {
        if ((getApplyChangesMethod = ParameterServiceGrpc.getApplyChangesMethod) == null) {
          ParameterServiceGrpc.getApplyChangesMethod = getApplyChangesMethod =
              io.grpc.MethodDescriptor.<top.fosin.anan.cloudresource.grpc.parameter.ParameterIdsReq, com.google.protobuf.BoolValue>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "applyChanges"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  top.fosin.anan.cloudresource.grpc.parameter.ParameterIdsReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.BoolValue.getDefaultInstance()))
              .setSchemaDescriptor(new ParameterServiceMethodDescriptorSupplier("applyChanges"))
              .build();
        }
      }
    }
    return getApplyChangesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.google.protobuf.BoolValue> getApplyChangeAllMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "applyChangeAll",
      requestType = com.google.protobuf.Empty.class,
      responseType = com.google.protobuf.BoolValue.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.google.protobuf.BoolValue> getApplyChangeAllMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, com.google.protobuf.BoolValue> getApplyChangeAllMethod;
    if ((getApplyChangeAllMethod = ParameterServiceGrpc.getApplyChangeAllMethod) == null) {
      synchronized (ParameterServiceGrpc.class) {
        if ((getApplyChangeAllMethod = ParameterServiceGrpc.getApplyChangeAllMethod) == null) {
          ParameterServiceGrpc.getApplyChangeAllMethod = getApplyChangeAllMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, com.google.protobuf.BoolValue>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "applyChangeAll"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.BoolValue.getDefaultInstance()))
              .setSchemaDescriptor(new ParameterServiceMethodDescriptorSupplier("applyChangeAll"))
              .build();
        }
      }
    }
    return getApplyChangeAllMethod;
  }

  private static volatile io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.parameter.ParameterIdsReq,
      com.google.protobuf.Empty> getCancelDeleteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "cancelDelete",
      requestType = top.fosin.anan.cloudresource.grpc.parameter.ParameterIdsReq.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.parameter.ParameterIdsReq,
      com.google.protobuf.Empty> getCancelDeleteMethod() {
    io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.parameter.ParameterIdsReq, com.google.protobuf.Empty> getCancelDeleteMethod;
    if ((getCancelDeleteMethod = ParameterServiceGrpc.getCancelDeleteMethod) == null) {
      synchronized (ParameterServiceGrpc.class) {
        if ((getCancelDeleteMethod = ParameterServiceGrpc.getCancelDeleteMethod) == null) {
          ParameterServiceGrpc.getCancelDeleteMethod = getCancelDeleteMethod =
              io.grpc.MethodDescriptor.<top.fosin.anan.cloudresource.grpc.parameter.ParameterIdsReq, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "cancelDelete"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  top.fosin.anan.cloudresource.grpc.parameter.ParameterIdsReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ParameterServiceMethodDescriptorSupplier("cancelDelete"))
              .build();
        }
      }
    }
    return getCancelDeleteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.parameter.ParameterThreeArgsReq,
      top.fosin.anan.cloudresource.grpc.parameter.ParameterResp> getGetParameterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getParameter",
      requestType = top.fosin.anan.cloudresource.grpc.parameter.ParameterThreeArgsReq.class,
      responseType = top.fosin.anan.cloudresource.grpc.parameter.ParameterResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.parameter.ParameterThreeArgsReq,
      top.fosin.anan.cloudresource.grpc.parameter.ParameterResp> getGetParameterMethod() {
    io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.parameter.ParameterThreeArgsReq, top.fosin.anan.cloudresource.grpc.parameter.ParameterResp> getGetParameterMethod;
    if ((getGetParameterMethod = ParameterServiceGrpc.getGetParameterMethod) == null) {
      synchronized (ParameterServiceGrpc.class) {
        if ((getGetParameterMethod = ParameterServiceGrpc.getGetParameterMethod) == null) {
          ParameterServiceGrpc.getGetParameterMethod = getGetParameterMethod =
              io.grpc.MethodDescriptor.<top.fosin.anan.cloudresource.grpc.parameter.ParameterThreeArgsReq, top.fosin.anan.cloudresource.grpc.parameter.ParameterResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getParameter"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  top.fosin.anan.cloudresource.grpc.parameter.ParameterThreeArgsReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  top.fosin.anan.cloudresource.grpc.parameter.ParameterResp.getDefaultInstance()))
              .setSchemaDescriptor(new ParameterServiceMethodDescriptorSupplier("getParameter"))
              .build();
        }
      }
    }
    return getGetParameterMethod;
  }

  private static volatile io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.parameter.ParameterThreeArgsReq,
      top.fosin.anan.cloudresource.grpc.parameter.ParameterResp> getGetNearestParameterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getNearestParameter",
      requestType = top.fosin.anan.cloudresource.grpc.parameter.ParameterThreeArgsReq.class,
      responseType = top.fosin.anan.cloudresource.grpc.parameter.ParameterResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.parameter.ParameterThreeArgsReq,
      top.fosin.anan.cloudresource.grpc.parameter.ParameterResp> getGetNearestParameterMethod() {
    io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.parameter.ParameterThreeArgsReq, top.fosin.anan.cloudresource.grpc.parameter.ParameterResp> getGetNearestParameterMethod;
    if ((getGetNearestParameterMethod = ParameterServiceGrpc.getGetNearestParameterMethod) == null) {
      synchronized (ParameterServiceGrpc.class) {
        if ((getGetNearestParameterMethod = ParameterServiceGrpc.getGetNearestParameterMethod) == null) {
          ParameterServiceGrpc.getGetNearestParameterMethod = getGetNearestParameterMethod =
              io.grpc.MethodDescriptor.<top.fosin.anan.cloudresource.grpc.parameter.ParameterThreeArgsReq, top.fosin.anan.cloudresource.grpc.parameter.ParameterResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getNearestParameter"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  top.fosin.anan.cloudresource.grpc.parameter.ParameterThreeArgsReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  top.fosin.anan.cloudresource.grpc.parameter.ParameterResp.getDefaultInstance()))
              .setSchemaDescriptor(new ParameterServiceMethodDescriptorSupplier("getNearestParameter"))
              .build();
        }
      }
    }
    return getGetNearestParameterMethod;
  }

  private static volatile io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.parameter.ParameterReq,
      com.google.protobuf.Empty> getProcessUpdateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "processUpdate",
      requestType = top.fosin.anan.cloudresource.grpc.parameter.ParameterReq.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.parameter.ParameterReq,
      com.google.protobuf.Empty> getProcessUpdateMethod() {
    io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.parameter.ParameterReq, com.google.protobuf.Empty> getProcessUpdateMethod;
    if ((getProcessUpdateMethod = ParameterServiceGrpc.getProcessUpdateMethod) == null) {
      synchronized (ParameterServiceGrpc.class) {
        if ((getProcessUpdateMethod = ParameterServiceGrpc.getProcessUpdateMethod) == null) {
          ParameterServiceGrpc.getProcessUpdateMethod = getProcessUpdateMethod =
              io.grpc.MethodDescriptor.<top.fosin.anan.cloudresource.grpc.parameter.ParameterReq, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "processUpdate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  top.fosin.anan.cloudresource.grpc.parameter.ParameterReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ParameterServiceMethodDescriptorSupplier("processUpdate"))
              .build();
        }
      }
    }
    return getProcessUpdateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.parameter.getOrCreateReq,
      com.google.protobuf.StringValue> getGetOrCreateParameterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getOrCreateParameter",
      requestType = top.fosin.anan.cloudresource.grpc.parameter.getOrCreateReq.class,
      responseType = com.google.protobuf.StringValue.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.parameter.getOrCreateReq,
      com.google.protobuf.StringValue> getGetOrCreateParameterMethod() {
    io.grpc.MethodDescriptor<top.fosin.anan.cloudresource.grpc.parameter.getOrCreateReq, com.google.protobuf.StringValue> getGetOrCreateParameterMethod;
    if ((getGetOrCreateParameterMethod = ParameterServiceGrpc.getGetOrCreateParameterMethod) == null) {
      synchronized (ParameterServiceGrpc.class) {
        if ((getGetOrCreateParameterMethod = ParameterServiceGrpc.getGetOrCreateParameterMethod) == null) {
          ParameterServiceGrpc.getGetOrCreateParameterMethod = getGetOrCreateParameterMethod =
              io.grpc.MethodDescriptor.<top.fosin.anan.cloudresource.grpc.parameter.getOrCreateReq, com.google.protobuf.StringValue>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getOrCreateParameter"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  top.fosin.anan.cloudresource.grpc.parameter.getOrCreateReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.StringValue.getDefaultInstance()))
              .setSchemaDescriptor(new ParameterServiceMethodDescriptorSupplier("getOrCreateParameter"))
              .build();
        }
      }
    }
    return getGetOrCreateParameterMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ParameterServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ParameterServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ParameterServiceStub>() {
        @java.lang.Override
        public ParameterServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ParameterServiceStub(channel, callOptions);
        }
      };
    return ParameterServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ParameterServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ParameterServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ParameterServiceBlockingStub>() {
        @java.lang.Override
        public ParameterServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ParameterServiceBlockingStub(channel, callOptions);
        }
      };
    return ParameterServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ParameterServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ParameterServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ParameterServiceFutureStub>() {
        @java.lang.Override
        public ParameterServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ParameterServiceFutureStub(channel, callOptions);
        }
      };
    return ParameterServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class ParameterServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *应用单个变更参数
     * </pre>
     */
    public void applyChange(top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq request,
        io.grpc.stub.StreamObserver<com.google.protobuf.BoolValue> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getApplyChangeMethod(), responseObserver);
    }

    /**
     * <pre>
     *应用指定的变更参数
     * </pre>
     */
    public void applyChanges(top.fosin.anan.cloudresource.grpc.parameter.ParameterIdsReq request,
        io.grpc.stub.StreamObserver<com.google.protobuf.BoolValue> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getApplyChangesMethod(), responseObserver);
    }

    /**
     * <pre>
     *应用所有变更得参数
     * </pre>
     */
    public void applyChangeAll(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.google.protobuf.BoolValue> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getApplyChangeAllMethod(), responseObserver);
    }

    /**
     * <pre>
     *取消删除的参数
     * </pre>
     */
    public void cancelDelete(top.fosin.anan.cloudresource.grpc.parameter.ParameterIdsReq request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCancelDeleteMethod(), responseObserver);
    }

    /**
     * <pre>
     *根据3参数获取参数详细信息
     * </pre>
     */
    public void getParameter(top.fosin.anan.cloudresource.grpc.parameter.ParameterThreeArgsReq request,
        io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.parameter.ParameterResp> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetParameterMethod(), responseObserver);
    }

    /**
     * <pre>
     *根据3参数获取最新的参数详细信息
     * </pre>
     */
    public void getNearestParameter(top.fosin.anan.cloudresource.grpc.parameter.ParameterThreeArgsReq request,
        io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.parameter.ParameterResp> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetNearestParameterMethod(), responseObserver);
    }

    /**
     * <pre>
     *更新参数
     * </pre>
     */
    public void processUpdate(top.fosin.anan.cloudresource.grpc.parameter.ParameterReq request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getProcessUpdateMethod(), responseObserver);
    }

    /**
     * <pre>
     *获取参数，如果没有就创建
     * </pre>
     */
    public void getOrCreateParameter(top.fosin.anan.cloudresource.grpc.parameter.getOrCreateReq request,
        io.grpc.stub.StreamObserver<com.google.protobuf.StringValue> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetOrCreateParameterMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getApplyChangeMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq,
                com.google.protobuf.BoolValue>(
                  this, METHODID_APPLY_CHANGE)))
          .addMethod(
            getApplyChangesMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                top.fosin.anan.cloudresource.grpc.parameter.ParameterIdsReq,
                com.google.protobuf.BoolValue>(
                  this, METHODID_APPLY_CHANGES)))
          .addMethod(
            getApplyChangeAllMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                com.google.protobuf.BoolValue>(
                  this, METHODID_APPLY_CHANGE_ALL)))
          .addMethod(
            getCancelDeleteMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                top.fosin.anan.cloudresource.grpc.parameter.ParameterIdsReq,
                com.google.protobuf.Empty>(
                  this, METHODID_CANCEL_DELETE)))
          .addMethod(
            getGetParameterMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                top.fosin.anan.cloudresource.grpc.parameter.ParameterThreeArgsReq,
                top.fosin.anan.cloudresource.grpc.parameter.ParameterResp>(
                  this, METHODID_GET_PARAMETER)))
          .addMethod(
            getGetNearestParameterMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                top.fosin.anan.cloudresource.grpc.parameter.ParameterThreeArgsReq,
                top.fosin.anan.cloudresource.grpc.parameter.ParameterResp>(
                  this, METHODID_GET_NEAREST_PARAMETER)))
          .addMethod(
            getProcessUpdateMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                top.fosin.anan.cloudresource.grpc.parameter.ParameterReq,
                com.google.protobuf.Empty>(
                  this, METHODID_PROCESS_UPDATE)))
          .addMethod(
            getGetOrCreateParameterMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                top.fosin.anan.cloudresource.grpc.parameter.getOrCreateReq,
                com.google.protobuf.StringValue>(
                  this, METHODID_GET_OR_CREATE_PARAMETER)))
          .build();
    }
  }

  /**
   */
  public static final class ParameterServiceStub extends io.grpc.stub.AbstractAsyncStub<ParameterServiceStub> {
    private ParameterServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ParameterServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ParameterServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *应用单个变更参数
     * </pre>
     */
    public void applyChange(top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq request,
        io.grpc.stub.StreamObserver<com.google.protobuf.BoolValue> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getApplyChangeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *应用指定的变更参数
     * </pre>
     */
    public void applyChanges(top.fosin.anan.cloudresource.grpc.parameter.ParameterIdsReq request,
        io.grpc.stub.StreamObserver<com.google.protobuf.BoolValue> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getApplyChangesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *应用所有变更得参数
     * </pre>
     */
    public void applyChangeAll(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.google.protobuf.BoolValue> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getApplyChangeAllMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *取消删除的参数
     * </pre>
     */
    public void cancelDelete(top.fosin.anan.cloudresource.grpc.parameter.ParameterIdsReq request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCancelDeleteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *根据3参数获取参数详细信息
     * </pre>
     */
    public void getParameter(top.fosin.anan.cloudresource.grpc.parameter.ParameterThreeArgsReq request,
        io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.parameter.ParameterResp> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetParameterMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *根据3参数获取最新的参数详细信息
     * </pre>
     */
    public void getNearestParameter(top.fosin.anan.cloudresource.grpc.parameter.ParameterThreeArgsReq request,
        io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.parameter.ParameterResp> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetNearestParameterMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *更新参数
     * </pre>
     */
    public void processUpdate(top.fosin.anan.cloudresource.grpc.parameter.ParameterReq request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getProcessUpdateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *获取参数，如果没有就创建
     * </pre>
     */
    public void getOrCreateParameter(top.fosin.anan.cloudresource.grpc.parameter.getOrCreateReq request,
        io.grpc.stub.StreamObserver<com.google.protobuf.StringValue> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetOrCreateParameterMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ParameterServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<ParameterServiceBlockingStub> {
    private ParameterServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ParameterServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ParameterServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *应用单个变更参数
     * </pre>
     */
    public com.google.protobuf.BoolValue applyChange(top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getApplyChangeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *应用指定的变更参数
     * </pre>
     */
    public com.google.protobuf.BoolValue applyChanges(top.fosin.anan.cloudresource.grpc.parameter.ParameterIdsReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getApplyChangesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *应用所有变更得参数
     * </pre>
     */
    public com.google.protobuf.BoolValue applyChangeAll(com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getApplyChangeAllMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *取消删除的参数
     * </pre>
     */
    public com.google.protobuf.Empty cancelDelete(top.fosin.anan.cloudresource.grpc.parameter.ParameterIdsReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCancelDeleteMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *根据3参数获取参数详细信息
     * </pre>
     */
    public top.fosin.anan.cloudresource.grpc.parameter.ParameterResp getParameter(top.fosin.anan.cloudresource.grpc.parameter.ParameterThreeArgsReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetParameterMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *根据3参数获取最新的参数详细信息
     * </pre>
     */
    public top.fosin.anan.cloudresource.grpc.parameter.ParameterResp getNearestParameter(top.fosin.anan.cloudresource.grpc.parameter.ParameterThreeArgsReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetNearestParameterMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *更新参数
     * </pre>
     */
    public com.google.protobuf.Empty processUpdate(top.fosin.anan.cloudresource.grpc.parameter.ParameterReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getProcessUpdateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *获取参数，如果没有就创建
     * </pre>
     */
    public com.google.protobuf.StringValue getOrCreateParameter(top.fosin.anan.cloudresource.grpc.parameter.getOrCreateReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetOrCreateParameterMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ParameterServiceFutureStub extends io.grpc.stub.AbstractFutureStub<ParameterServiceFutureStub> {
    private ParameterServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ParameterServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ParameterServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *应用单个变更参数
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.BoolValue> applyChange(
        top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getApplyChangeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *应用指定的变更参数
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.BoolValue> applyChanges(
        top.fosin.anan.cloudresource.grpc.parameter.ParameterIdsReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getApplyChangesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *应用所有变更得参数
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.BoolValue> applyChangeAll(
        com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getApplyChangeAllMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *取消删除的参数
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> cancelDelete(
        top.fosin.anan.cloudresource.grpc.parameter.ParameterIdsReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCancelDeleteMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *根据3参数获取参数详细信息
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<top.fosin.anan.cloudresource.grpc.parameter.ParameterResp> getParameter(
        top.fosin.anan.cloudresource.grpc.parameter.ParameterThreeArgsReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetParameterMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *根据3参数获取最新的参数详细信息
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<top.fosin.anan.cloudresource.grpc.parameter.ParameterResp> getNearestParameter(
        top.fosin.anan.cloudresource.grpc.parameter.ParameterThreeArgsReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetNearestParameterMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *更新参数
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> processUpdate(
        top.fosin.anan.cloudresource.grpc.parameter.ParameterReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getProcessUpdateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *获取参数，如果没有就创建
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.StringValue> getOrCreateParameter(
        top.fosin.anan.cloudresource.grpc.parameter.getOrCreateReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetOrCreateParameterMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_APPLY_CHANGE = 0;
  private static final int METHODID_APPLY_CHANGES = 1;
  private static final int METHODID_APPLY_CHANGE_ALL = 2;
  private static final int METHODID_CANCEL_DELETE = 3;
  private static final int METHODID_GET_PARAMETER = 4;
  private static final int METHODID_GET_NEAREST_PARAMETER = 5;
  private static final int METHODID_PROCESS_UPDATE = 6;
  private static final int METHODID_GET_OR_CREATE_PARAMETER = 7;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ParameterServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ParameterServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_APPLY_CHANGE:
          serviceImpl.applyChange((top.fosin.anan.cloudresource.grpc.parameter.ParameterIdReq) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.BoolValue>) responseObserver);
          break;
        case METHODID_APPLY_CHANGES:
          serviceImpl.applyChanges((top.fosin.anan.cloudresource.grpc.parameter.ParameterIdsReq) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.BoolValue>) responseObserver);
          break;
        case METHODID_APPLY_CHANGE_ALL:
          serviceImpl.applyChangeAll((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.BoolValue>) responseObserver);
          break;
        case METHODID_CANCEL_DELETE:
          serviceImpl.cancelDelete((top.fosin.anan.cloudresource.grpc.parameter.ParameterIdsReq) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_GET_PARAMETER:
          serviceImpl.getParameter((top.fosin.anan.cloudresource.grpc.parameter.ParameterThreeArgsReq) request,
              (io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.parameter.ParameterResp>) responseObserver);
          break;
        case METHODID_GET_NEAREST_PARAMETER:
          serviceImpl.getNearestParameter((top.fosin.anan.cloudresource.grpc.parameter.ParameterThreeArgsReq) request,
              (io.grpc.stub.StreamObserver<top.fosin.anan.cloudresource.grpc.parameter.ParameterResp>) responseObserver);
          break;
        case METHODID_PROCESS_UPDATE:
          serviceImpl.processUpdate((top.fosin.anan.cloudresource.grpc.parameter.ParameterReq) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_GET_OR_CREATE_PARAMETER:
          serviceImpl.getOrCreateParameter((top.fosin.anan.cloudresource.grpc.parameter.getOrCreateReq) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.StringValue>) responseObserver);
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

  private static abstract class ParameterServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ParameterServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return top.fosin.anan.cloudresource.grpc.parameter.ParameterProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ParameterService");
    }
  }

  private static final class ParameterServiceFileDescriptorSupplier
      extends ParameterServiceBaseDescriptorSupplier {
    ParameterServiceFileDescriptorSupplier() {}
  }

  private static final class ParameterServiceMethodDescriptorSupplier
      extends ParameterServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ParameterServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (ParameterServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ParameterServiceFileDescriptorSupplier())
              .addMethod(getApplyChangeMethod())
              .addMethod(getApplyChangesMethod())
              .addMethod(getApplyChangeAllMethod())
              .addMethod(getCancelDeleteMethod())
              .addMethod(getGetParameterMethod())
              .addMethod(getGetNearestParameterMethod())
              .addMethod(getProcessUpdateMethod())
              .addMethod(getGetOrCreateParameterMethod())
              .build();
        }
      }
    }
    return result;
  }
}
