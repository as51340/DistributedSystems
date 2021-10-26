package org.example.AlmaOnline.server;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.38.0)",
    comments = "Source: AlmaOnline.proto")
public final class AlmaOnlineGrpc {

  private AlmaOnlineGrpc() {}

  public static final String SERVICE_NAME = "almaonline.AlmaOnline";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.example.AlmaOnline.server.GetRestaurantsRequest,
      org.example.AlmaOnline.server.GetRestaurantsResponse> getGetRestaurantsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetRestaurants",
      requestType = org.example.AlmaOnline.server.GetRestaurantsRequest.class,
      responseType = org.example.AlmaOnline.server.GetRestaurantsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<org.example.AlmaOnline.server.GetRestaurantsRequest,
      org.example.AlmaOnline.server.GetRestaurantsResponse> getGetRestaurantsMethod() {
    io.grpc.MethodDescriptor<org.example.AlmaOnline.server.GetRestaurantsRequest, org.example.AlmaOnline.server.GetRestaurantsResponse> getGetRestaurantsMethod;
    if ((getGetRestaurantsMethod = AlmaOnlineGrpc.getGetRestaurantsMethod) == null) {
      synchronized (AlmaOnlineGrpc.class) {
        if ((getGetRestaurantsMethod = AlmaOnlineGrpc.getGetRestaurantsMethod) == null) {
          AlmaOnlineGrpc.getGetRestaurantsMethod = getGetRestaurantsMethod =
              io.grpc.MethodDescriptor.<org.example.AlmaOnline.server.GetRestaurantsRequest, org.example.AlmaOnline.server.GetRestaurantsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetRestaurants"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.example.AlmaOnline.server.GetRestaurantsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.example.AlmaOnline.server.GetRestaurantsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AlmaOnlineMethodDescriptorSupplier("GetRestaurants"))
              .build();
        }
      }
    }
    return getGetRestaurantsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.example.AlmaOnline.server.GetMenuRequest,
      org.example.AlmaOnline.server.GetMenuResponse> getGetMenuMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetMenu",
      requestType = org.example.AlmaOnline.server.GetMenuRequest.class,
      responseType = org.example.AlmaOnline.server.GetMenuResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.example.AlmaOnline.server.GetMenuRequest,
      org.example.AlmaOnline.server.GetMenuResponse> getGetMenuMethod() {
    io.grpc.MethodDescriptor<org.example.AlmaOnline.server.GetMenuRequest, org.example.AlmaOnline.server.GetMenuResponse> getGetMenuMethod;
    if ((getGetMenuMethod = AlmaOnlineGrpc.getGetMenuMethod) == null) {
      synchronized (AlmaOnlineGrpc.class) {
        if ((getGetMenuMethod = AlmaOnlineGrpc.getGetMenuMethod) == null) {
          AlmaOnlineGrpc.getGetMenuMethod = getGetMenuMethod =
              io.grpc.MethodDescriptor.<org.example.AlmaOnline.server.GetMenuRequest, org.example.AlmaOnline.server.GetMenuResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetMenu"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.example.AlmaOnline.server.GetMenuRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.example.AlmaOnline.server.GetMenuResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AlmaOnlineMethodDescriptorSupplier("GetMenu"))
              .build();
        }
      }
    }
    return getGetMenuMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.example.AlmaOnline.server.CreateDineInOrderRequest,
      org.example.AlmaOnline.server.CreateDineInOrderResponse> getCreateDineInOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateDineInOrder",
      requestType = org.example.AlmaOnline.server.CreateDineInOrderRequest.class,
      responseType = org.example.AlmaOnline.server.CreateDineInOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.example.AlmaOnline.server.CreateDineInOrderRequest,
      org.example.AlmaOnline.server.CreateDineInOrderResponse> getCreateDineInOrderMethod() {
    io.grpc.MethodDescriptor<org.example.AlmaOnline.server.CreateDineInOrderRequest, org.example.AlmaOnline.server.CreateDineInOrderResponse> getCreateDineInOrderMethod;
    if ((getCreateDineInOrderMethod = AlmaOnlineGrpc.getCreateDineInOrderMethod) == null) {
      synchronized (AlmaOnlineGrpc.class) {
        if ((getCreateDineInOrderMethod = AlmaOnlineGrpc.getCreateDineInOrderMethod) == null) {
          AlmaOnlineGrpc.getCreateDineInOrderMethod = getCreateDineInOrderMethod =
              io.grpc.MethodDescriptor.<org.example.AlmaOnline.server.CreateDineInOrderRequest, org.example.AlmaOnline.server.CreateDineInOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateDineInOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.example.AlmaOnline.server.CreateDineInOrderRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.example.AlmaOnline.server.CreateDineInOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AlmaOnlineMethodDescriptorSupplier("CreateDineInOrder"))
              .build();
        }
      }
    }
    return getCreateDineInOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.example.AlmaOnline.server.CreateDeliveryOrderRequest,
      org.example.AlmaOnline.server.CreateDeliveryOrderResponse> getCreateDeliveryOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateDeliveryOrder",
      requestType = org.example.AlmaOnline.server.CreateDeliveryOrderRequest.class,
      responseType = org.example.AlmaOnline.server.CreateDeliveryOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.example.AlmaOnline.server.CreateDeliveryOrderRequest,
      org.example.AlmaOnline.server.CreateDeliveryOrderResponse> getCreateDeliveryOrderMethod() {
    io.grpc.MethodDescriptor<org.example.AlmaOnline.server.CreateDeliveryOrderRequest, org.example.AlmaOnline.server.CreateDeliveryOrderResponse> getCreateDeliveryOrderMethod;
    if ((getCreateDeliveryOrderMethod = AlmaOnlineGrpc.getCreateDeliveryOrderMethod) == null) {
      synchronized (AlmaOnlineGrpc.class) {
        if ((getCreateDeliveryOrderMethod = AlmaOnlineGrpc.getCreateDeliveryOrderMethod) == null) {
          AlmaOnlineGrpc.getCreateDeliveryOrderMethod = getCreateDeliveryOrderMethod =
              io.grpc.MethodDescriptor.<org.example.AlmaOnline.server.CreateDeliveryOrderRequest, org.example.AlmaOnline.server.CreateDeliveryOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateDeliveryOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.example.AlmaOnline.server.CreateDeliveryOrderRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.example.AlmaOnline.server.CreateDeliveryOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AlmaOnlineMethodDescriptorSupplier("CreateDeliveryOrder"))
              .build();
        }
      }
    }
    return getCreateDeliveryOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.example.AlmaOnline.server.GetOrderRequest,
      org.example.AlmaOnline.server.GetOrderResponse> getGetOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetOrder",
      requestType = org.example.AlmaOnline.server.GetOrderRequest.class,
      responseType = org.example.AlmaOnline.server.GetOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.example.AlmaOnline.server.GetOrderRequest,
      org.example.AlmaOnline.server.GetOrderResponse> getGetOrderMethod() {
    io.grpc.MethodDescriptor<org.example.AlmaOnline.server.GetOrderRequest, org.example.AlmaOnline.server.GetOrderResponse> getGetOrderMethod;
    if ((getGetOrderMethod = AlmaOnlineGrpc.getGetOrderMethod) == null) {
      synchronized (AlmaOnlineGrpc.class) {
        if ((getGetOrderMethod = AlmaOnlineGrpc.getGetOrderMethod) == null) {
          AlmaOnlineGrpc.getGetOrderMethod = getGetOrderMethod =
              io.grpc.MethodDescriptor.<org.example.AlmaOnline.server.GetOrderRequest, org.example.AlmaOnline.server.GetOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.example.AlmaOnline.server.GetOrderRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.example.AlmaOnline.server.GetOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AlmaOnlineMethodDescriptorSupplier("GetOrder"))
              .build();
        }
      }
    }
    return getGetOrderMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AlmaOnlineStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AlmaOnlineStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AlmaOnlineStub>() {
        @java.lang.Override
        public AlmaOnlineStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AlmaOnlineStub(channel, callOptions);
        }
      };
    return AlmaOnlineStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AlmaOnlineBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AlmaOnlineBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AlmaOnlineBlockingStub>() {
        @java.lang.Override
        public AlmaOnlineBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AlmaOnlineBlockingStub(channel, callOptions);
        }
      };
    return AlmaOnlineBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AlmaOnlineFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AlmaOnlineFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AlmaOnlineFutureStub>() {
        @java.lang.Override
        public AlmaOnlineFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AlmaOnlineFutureStub(channel, callOptions);
        }
      };
    return AlmaOnlineFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class AlmaOnlineImplBase implements io.grpc.BindableService {

    /**
     */
    public void getRestaurants(org.example.AlmaOnline.server.GetRestaurantsRequest request,
        io.grpc.stub.StreamObserver<org.example.AlmaOnline.server.GetRestaurantsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetRestaurantsMethod(), responseObserver);
    }

    /**
     */
    public void getMenu(org.example.AlmaOnline.server.GetMenuRequest request,
        io.grpc.stub.StreamObserver<org.example.AlmaOnline.server.GetMenuResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetMenuMethod(), responseObserver);
    }

    /**
     */
    public void createDineInOrder(org.example.AlmaOnline.server.CreateDineInOrderRequest request,
        io.grpc.stub.StreamObserver<org.example.AlmaOnline.server.CreateDineInOrderResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateDineInOrderMethod(), responseObserver);
    }

    /**
     */
    public void createDeliveryOrder(org.example.AlmaOnline.server.CreateDeliveryOrderRequest request,
        io.grpc.stub.StreamObserver<org.example.AlmaOnline.server.CreateDeliveryOrderResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateDeliveryOrderMethod(), responseObserver);
    }

    /**
     */
    public void getOrder(org.example.AlmaOnline.server.GetOrderRequest request,
        io.grpc.stub.StreamObserver<org.example.AlmaOnline.server.GetOrderResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetOrderMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetRestaurantsMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                org.example.AlmaOnline.server.GetRestaurantsRequest,
                org.example.AlmaOnline.server.GetRestaurantsResponse>(
                  this, METHODID_GET_RESTAURANTS)))
          .addMethod(
            getGetMenuMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                org.example.AlmaOnline.server.GetMenuRequest,
                org.example.AlmaOnline.server.GetMenuResponse>(
                  this, METHODID_GET_MENU)))
          .addMethod(
            getCreateDineInOrderMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                org.example.AlmaOnline.server.CreateDineInOrderRequest,
                org.example.AlmaOnline.server.CreateDineInOrderResponse>(
                  this, METHODID_CREATE_DINE_IN_ORDER)))
          .addMethod(
            getCreateDeliveryOrderMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                org.example.AlmaOnline.server.CreateDeliveryOrderRequest,
                org.example.AlmaOnline.server.CreateDeliveryOrderResponse>(
                  this, METHODID_CREATE_DELIVERY_ORDER)))
          .addMethod(
            getGetOrderMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                org.example.AlmaOnline.server.GetOrderRequest,
                org.example.AlmaOnline.server.GetOrderResponse>(
                  this, METHODID_GET_ORDER)))
          .build();
    }
  }

  /**
   */
  public static final class AlmaOnlineStub extends io.grpc.stub.AbstractAsyncStub<AlmaOnlineStub> {
    private AlmaOnlineStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AlmaOnlineStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AlmaOnlineStub(channel, callOptions);
    }

    /**
     */
    public void getRestaurants(org.example.AlmaOnline.server.GetRestaurantsRequest request,
        io.grpc.stub.StreamObserver<org.example.AlmaOnline.server.GetRestaurantsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getGetRestaurantsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getMenu(org.example.AlmaOnline.server.GetMenuRequest request,
        io.grpc.stub.StreamObserver<org.example.AlmaOnline.server.GetMenuResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetMenuMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createDineInOrder(org.example.AlmaOnline.server.CreateDineInOrderRequest request,
        io.grpc.stub.StreamObserver<org.example.AlmaOnline.server.CreateDineInOrderResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateDineInOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createDeliveryOrder(org.example.AlmaOnline.server.CreateDeliveryOrderRequest request,
        io.grpc.stub.StreamObserver<org.example.AlmaOnline.server.CreateDeliveryOrderResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateDeliveryOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getOrder(org.example.AlmaOnline.server.GetOrderRequest request,
        io.grpc.stub.StreamObserver<org.example.AlmaOnline.server.GetOrderResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetOrderMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class AlmaOnlineBlockingStub extends io.grpc.stub.AbstractBlockingStub<AlmaOnlineBlockingStub> {
    private AlmaOnlineBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AlmaOnlineBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AlmaOnlineBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<org.example.AlmaOnline.server.GetRestaurantsResponse> getRestaurants(
        org.example.AlmaOnline.server.GetRestaurantsRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getGetRestaurantsMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.example.AlmaOnline.server.GetMenuResponse getMenu(org.example.AlmaOnline.server.GetMenuRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetMenuMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.example.AlmaOnline.server.CreateDineInOrderResponse createDineInOrder(org.example.AlmaOnline.server.CreateDineInOrderRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateDineInOrderMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.example.AlmaOnline.server.CreateDeliveryOrderResponse createDeliveryOrder(org.example.AlmaOnline.server.CreateDeliveryOrderRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateDeliveryOrderMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.example.AlmaOnline.server.GetOrderResponse getOrder(org.example.AlmaOnline.server.GetOrderRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetOrderMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class AlmaOnlineFutureStub extends io.grpc.stub.AbstractFutureStub<AlmaOnlineFutureStub> {
    private AlmaOnlineFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AlmaOnlineFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AlmaOnlineFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.example.AlmaOnline.server.GetMenuResponse> getMenu(
        org.example.AlmaOnline.server.GetMenuRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetMenuMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.example.AlmaOnline.server.CreateDineInOrderResponse> createDineInOrder(
        org.example.AlmaOnline.server.CreateDineInOrderRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateDineInOrderMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.example.AlmaOnline.server.CreateDeliveryOrderResponse> createDeliveryOrder(
        org.example.AlmaOnline.server.CreateDeliveryOrderRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateDeliveryOrderMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.example.AlmaOnline.server.GetOrderResponse> getOrder(
        org.example.AlmaOnline.server.GetOrderRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetOrderMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_RESTAURANTS = 0;
  private static final int METHODID_GET_MENU = 1;
  private static final int METHODID_CREATE_DINE_IN_ORDER = 2;
  private static final int METHODID_CREATE_DELIVERY_ORDER = 3;
  private static final int METHODID_GET_ORDER = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AlmaOnlineImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AlmaOnlineImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_RESTAURANTS:
          serviceImpl.getRestaurants((org.example.AlmaOnline.server.GetRestaurantsRequest) request,
              (io.grpc.stub.StreamObserver<org.example.AlmaOnline.server.GetRestaurantsResponse>) responseObserver);
          break;
        case METHODID_GET_MENU:
          serviceImpl.getMenu((org.example.AlmaOnline.server.GetMenuRequest) request,
              (io.grpc.stub.StreamObserver<org.example.AlmaOnline.server.GetMenuResponse>) responseObserver);
          break;
        case METHODID_CREATE_DINE_IN_ORDER:
          serviceImpl.createDineInOrder((org.example.AlmaOnline.server.CreateDineInOrderRequest) request,
              (io.grpc.stub.StreamObserver<org.example.AlmaOnline.server.CreateDineInOrderResponse>) responseObserver);
          break;
        case METHODID_CREATE_DELIVERY_ORDER:
          serviceImpl.createDeliveryOrder((org.example.AlmaOnline.server.CreateDeliveryOrderRequest) request,
              (io.grpc.stub.StreamObserver<org.example.AlmaOnline.server.CreateDeliveryOrderResponse>) responseObserver);
          break;
        case METHODID_GET_ORDER:
          serviceImpl.getOrder((org.example.AlmaOnline.server.GetOrderRequest) request,
              (io.grpc.stub.StreamObserver<org.example.AlmaOnline.server.GetOrderResponse>) responseObserver);
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

  private static abstract class AlmaOnlineBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AlmaOnlineBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.example.AlmaOnline.server.AlmaOnlineProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AlmaOnline");
    }
  }

  private static final class AlmaOnlineFileDescriptorSupplier
      extends AlmaOnlineBaseDescriptorSupplier {
    AlmaOnlineFileDescriptorSupplier() {}
  }

  private static final class AlmaOnlineMethodDescriptorSupplier
      extends AlmaOnlineBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AlmaOnlineMethodDescriptorSupplier(String methodName) {
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
      synchronized (AlmaOnlineGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AlmaOnlineFileDescriptorSupplier())
              .addMethod(getGetRestaurantsMethod())
              .addMethod(getGetMenuMethod())
              .addMethod(getCreateDineInOrderMethod())
              .addMethod(getCreateDeliveryOrderMethod())
              .addMethod(getGetOrderMethod())
              .build();
        }
      }
    }
    return result;
  }
}
