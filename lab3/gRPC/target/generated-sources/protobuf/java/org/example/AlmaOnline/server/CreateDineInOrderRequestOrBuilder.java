// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: AlmaOnline.proto

package org.example.AlmaOnline.server;

public interface CreateDineInOrderRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:almaonline.CreateDineInOrderRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string restaurantId = 1;</code>
   * @return Whether the restaurantId field is set.
   */
  boolean hasRestaurantId();
  /**
   * <code>string restaurantId = 1;</code>
   * @return The restaurantId.
   */
  java.lang.String getRestaurantId();
  /**
   * <code>string restaurantId = 1;</code>
   * @return The bytes for restaurantId.
   */
  com.google.protobuf.ByteString
      getRestaurantIdBytes();

  /**
   * <code>.almaonline.DineInOrderQuote quote = 2;</code>
   * @return Whether the quote field is set.
   */
  boolean hasQuote();
  /**
   * <code>.almaonline.DineInOrderQuote quote = 2;</code>
   * @return The quote.
   */
  org.example.AlmaOnline.server.DineInOrderQuote getQuote();
  /**
   * <code>.almaonline.DineInOrderQuote quote = 2;</code>
   */
  org.example.AlmaOnline.server.DineInOrderQuoteOrBuilder getQuoteOrBuilder();
}