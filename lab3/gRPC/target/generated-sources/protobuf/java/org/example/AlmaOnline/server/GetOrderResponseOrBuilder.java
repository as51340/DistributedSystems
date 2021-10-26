// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: AlmaOnline.proto

package org.example.AlmaOnline.server;

public interface GetOrderResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:almaonline.GetOrderResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string customer = 1;</code>
   * @return Whether the customer field is set.
   */
  boolean hasCustomer();
  /**
   * <code>string customer = 1;</code>
   * @return The customer.
   */
  java.lang.String getCustomer();
  /**
   * <code>string customer = 1;</code>
   * @return The bytes for customer.
   */
  com.google.protobuf.ByteString
      getCustomerBytes();

  /**
   * <code>.almaonline.Date createDate = 2;</code>
   * @return Whether the createDate field is set.
   */
  boolean hasCreateDate();
  /**
   * <code>.almaonline.Date createDate = 2;</code>
   * @return The createDate.
   */
  org.example.AlmaOnline.server.Date getCreateDate();
  /**
   * <code>.almaonline.Date createDate = 2;</code>
   */
  org.example.AlmaOnline.server.DateOrBuilder getCreateDateOrBuilder();

  /**
   * <code>repeated .almaonline.ItemInfo items = 3;</code>
   */
  java.util.List<org.example.AlmaOnline.server.ItemInfo> 
      getItemsList();
  /**
   * <code>repeated .almaonline.ItemInfo items = 3;</code>
   */
  org.example.AlmaOnline.server.ItemInfo getItems(int index);
  /**
   * <code>repeated .almaonline.ItemInfo items = 3;</code>
   */
  int getItemsCount();
  /**
   * <code>repeated .almaonline.ItemInfo items = 3;</code>
   */
  java.util.List<? extends org.example.AlmaOnline.server.ItemInfoOrBuilder> 
      getItemsOrBuilderList();
  /**
   * <code>repeated .almaonline.ItemInfo items = 3;</code>
   */
  org.example.AlmaOnline.server.ItemInfoOrBuilder getItemsOrBuilder(
      int index);
}
