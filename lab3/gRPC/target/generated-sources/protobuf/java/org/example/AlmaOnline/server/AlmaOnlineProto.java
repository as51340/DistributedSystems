// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: AlmaOnline.proto

package org.example.AlmaOnline.server;

public final class AlmaOnlineProto {
  private AlmaOnlineProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_almaonline_OrderQuote_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_almaonline_OrderQuote_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_almaonline_DeliveryOrderQuote_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_almaonline_DeliveryOrderQuote_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_almaonline_DineInOrderQuote_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_almaonline_DineInOrderQuote_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_almaonline_ItemInfo_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_almaonline_ItemInfo_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_almaonline_Date_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_almaonline_Date_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_almaonline_GetOrderResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_almaonline_GetOrderResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_almaonline_GetOrderRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_almaonline_GetOrderRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_almaonline_CreateDeliveryOrderResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_almaonline_CreateDeliveryOrderResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_almaonline_CreateDeliveryOrderRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_almaonline_CreateDeliveryOrderRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_almaonline_CreateDineInOrderResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_almaonline_CreateDineInOrderResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_almaonline_CreateDineInOrderRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_almaonline_CreateDineInOrderRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_almaonline_GetMenuResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_almaonline_GetMenuResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_almaonline_GetMenuResponse_ItemsEntry_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_almaonline_GetMenuResponse_ItemsEntry_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_almaonline_GetMenuRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_almaonline_GetMenuRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_almaonline_GetRestaurantsRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_almaonline_GetRestaurantsRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_almaonline_GetRestaurantsResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_almaonline_GetRestaurantsResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\020AlmaOnline.proto\022\nalmaonline\"\203\001\n\nOrder" +
      "Quote\022\017\n\002id\030\001 \001(\tH\000\210\001\001\022&\n\014creationDate\030\002" +
      " \001(\0132\020.almaonline.Date\022\025\n\010customer\030\003 \001(\t" +
      "H\001\210\001\001\022\021\n\tlineItems\030\004 \003(\tB\005\n\003_idB\013\n\t_cust" +
      "omer\"m\n\022DeliveryOrderQuote\022\034\n\017deliveryAd" +
      "dress\030\001 \001(\tH\000\210\001\001\022%\n\005quote\030\002 \001(\0132\026.almaon" +
      "line.OrderQuoteB\022\n\020_deliveryAddress\"d\n\020D" +
      "ineInOrderQuote\022)\n\017reservationDate\030\001 \001(\013" +
      "2\020.almaonline.Date\022%\n\005quote\030\002 \001(\0132\026.alma" +
      "online.OrderQuote\"5\n\010ItemInfo\022\021\n\004name\030\001 " +
      "\001(\tH\000\210\001\001\022\r\n\005price\030\002 \001(\001B\007\n\005_name\"0\n\004Date" +
      "\022\014\n\004year\030\001 \001(\005\022\r\n\005month\030\002 \001(\005\022\013\n\003day\030\003 \001" +
      "(\005\"\201\001\n\020GetOrderResponse\022\025\n\010customer\030\001 \001(" +
      "\tH\000\210\001\001\022$\n\ncreateDate\030\002 \001(\0132\020.almaonline." +
      "Date\022#\n\005items\030\003 \003(\0132\024.almaonline.ItemInf" +
      "oB\013\n\t_customer\"_\n\017GetOrderRequest\022\031\n\014res" +
      "taurantId\030\001 \001(\tH\000\210\001\001\022\024\n\007orderId\030\002 \001(\tH\001\210" +
      "\001\001B\017\n\r_restaurantIdB\n\n\010_orderId\"\035\n\033Creat" +
      "eDeliveryOrderResponse\"w\n\032CreateDelivery" +
      "OrderRequest\022\031\n\014restaurantId\030\001 \001(\tH\000\210\001\001\022" +
      "-\n\005quote\030\002 \001(\0132\036.almaonline.DeliveryOrde" +
      "rQuoteB\017\n\r_restaurantId\"\033\n\031CreateDineInO" +
      "rderResponse\"s\n\030CreateDineInOrderRequest" +
      "\022\031\n\014restaurantId\030\001 \001(\tH\000\210\001\001\022+\n\005quote\030\002 \001" +
      "(\0132\034.almaonline.DineInOrderQuoteB\017\n\r_res" +
      "taurantId\"v\n\017GetMenuResponse\0225\n\005items\030\001 " +
      "\003(\0132&.almaonline.GetMenuResponse.ItemsEn" +
      "try\032,\n\nItemsEntry\022\013\n\003key\030\001 \001(\t\022\r\n\005value\030" +
      "\002 \001(\001:\0028\001\"<\n\016GetMenuRequest\022\031\n\014restauran" +
      "tId\030\001 \001(\tH\000\210\001\001B\017\n\r_restaurantId\"\027\n\025GetRe" +
      "staurantsRequest\"L\n\026GetRestaurantsRespon" +
      "se\022\017\n\002id\030\001 \001(\tH\000\210\001\001\022\021\n\004name\030\002 \001(\tH\001\210\001\001B\005" +
      "\n\003_idB\007\n\005_name2\306\003\n\nAlmaOnline\022[\n\016GetRest" +
      "aurants\022!.almaonline.GetRestaurantsReque" +
      "st\032\".almaonline.GetRestaurantsResponse\"\000" +
      "0\001\022D\n\007GetMenu\022\032.almaonline.GetMenuReques" +
      "t\032\033.almaonline.GetMenuResponse\"\000\022b\n\021Crea" +
      "teDineInOrder\022$.almaonline.CreateDineInO" +
      "rderRequest\032%.almaonline.CreateDineInOrd" +
      "erResponse\"\000\022h\n\023CreateDeliveryOrder\022&.al" +
      "maonline.CreateDeliveryOrderRequest\032\'.al" +
      "maonline.CreateDeliveryOrderResponse\"\000\022G" +
      "\n\010GetOrder\022\033.almaonline.GetOrderRequest\032" +
      "\034.almaonline.GetOrderResponse\"\000B7\n\035org.e" +
      "xample.AlmaOnline.serverB\017AlmaOnlineProt" +
      "oP\001\242\002\002AOb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_almaonline_OrderQuote_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_almaonline_OrderQuote_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_almaonline_OrderQuote_descriptor,
        new java.lang.String[] { "Id", "CreationDate", "Customer", "LineItems", "Id", "Customer", });
    internal_static_almaonline_DeliveryOrderQuote_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_almaonline_DeliveryOrderQuote_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_almaonline_DeliveryOrderQuote_descriptor,
        new java.lang.String[] { "DeliveryAddress", "Quote", "DeliveryAddress", });
    internal_static_almaonline_DineInOrderQuote_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_almaonline_DineInOrderQuote_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_almaonline_DineInOrderQuote_descriptor,
        new java.lang.String[] { "ReservationDate", "Quote", });
    internal_static_almaonline_ItemInfo_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_almaonline_ItemInfo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_almaonline_ItemInfo_descriptor,
        new java.lang.String[] { "Name", "Price", "Name", });
    internal_static_almaonline_Date_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_almaonline_Date_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_almaonline_Date_descriptor,
        new java.lang.String[] { "Year", "Month", "Day", });
    internal_static_almaonline_GetOrderResponse_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_almaonline_GetOrderResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_almaonline_GetOrderResponse_descriptor,
        new java.lang.String[] { "Customer", "CreateDate", "Items", "Customer", });
    internal_static_almaonline_GetOrderRequest_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_almaonline_GetOrderRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_almaonline_GetOrderRequest_descriptor,
        new java.lang.String[] { "RestaurantId", "OrderId", "RestaurantId", "OrderId", });
    internal_static_almaonline_CreateDeliveryOrderResponse_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_almaonline_CreateDeliveryOrderResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_almaonline_CreateDeliveryOrderResponse_descriptor,
        new java.lang.String[] { });
    internal_static_almaonline_CreateDeliveryOrderRequest_descriptor =
      getDescriptor().getMessageTypes().get(8);
    internal_static_almaonline_CreateDeliveryOrderRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_almaonline_CreateDeliveryOrderRequest_descriptor,
        new java.lang.String[] { "RestaurantId", "Quote", "RestaurantId", });
    internal_static_almaonline_CreateDineInOrderResponse_descriptor =
      getDescriptor().getMessageTypes().get(9);
    internal_static_almaonline_CreateDineInOrderResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_almaonline_CreateDineInOrderResponse_descriptor,
        new java.lang.String[] { });
    internal_static_almaonline_CreateDineInOrderRequest_descriptor =
      getDescriptor().getMessageTypes().get(10);
    internal_static_almaonline_CreateDineInOrderRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_almaonline_CreateDineInOrderRequest_descriptor,
        new java.lang.String[] { "RestaurantId", "Quote", "RestaurantId", });
    internal_static_almaonline_GetMenuResponse_descriptor =
      getDescriptor().getMessageTypes().get(11);
    internal_static_almaonline_GetMenuResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_almaonline_GetMenuResponse_descriptor,
        new java.lang.String[] { "Items", });
    internal_static_almaonline_GetMenuResponse_ItemsEntry_descriptor =
      internal_static_almaonline_GetMenuResponse_descriptor.getNestedTypes().get(0);
    internal_static_almaonline_GetMenuResponse_ItemsEntry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_almaonline_GetMenuResponse_ItemsEntry_descriptor,
        new java.lang.String[] { "Key", "Value", });
    internal_static_almaonline_GetMenuRequest_descriptor =
      getDescriptor().getMessageTypes().get(12);
    internal_static_almaonline_GetMenuRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_almaonline_GetMenuRequest_descriptor,
        new java.lang.String[] { "RestaurantId", "RestaurantId", });
    internal_static_almaonline_GetRestaurantsRequest_descriptor =
      getDescriptor().getMessageTypes().get(13);
    internal_static_almaonline_GetRestaurantsRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_almaonline_GetRestaurantsRequest_descriptor,
        new java.lang.String[] { });
    internal_static_almaonline_GetRestaurantsResponse_descriptor =
      getDescriptor().getMessageTypes().get(14);
    internal_static_almaonline_GetRestaurantsResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_almaonline_GetRestaurantsResponse_descriptor,
        new java.lang.String[] { "Id", "Name", "Id", "Name", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}