// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: AlmaOnline.proto

package org.example.AlmaOnline.server;

public interface DineInOrderQuoteOrBuilder extends
    // @@protoc_insertion_point(interface_extends:almaonline.DineInOrderQuote)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.almaonline.Date reservationDate = 1;</code>
   * @return Whether the reservationDate field is set.
   */
  boolean hasReservationDate();
  /**
   * <code>.almaonline.Date reservationDate = 1;</code>
   * @return The reservationDate.
   */
  org.example.AlmaOnline.server.Date getReservationDate();
  /**
   * <code>.almaonline.Date reservationDate = 1;</code>
   */
  org.example.AlmaOnline.server.DateOrBuilder getReservationDateOrBuilder();

  /**
   * <code>.almaonline.OrderQuote quote = 2;</code>
   * @return Whether the quote field is set.
   */
  boolean hasQuote();
  /**
   * <code>.almaonline.OrderQuote quote = 2;</code>
   * @return The quote.
   */
  org.example.AlmaOnline.server.OrderQuote getQuote();
  /**
   * <code>.almaonline.OrderQuote quote = 2;</code>
   */
  org.example.AlmaOnline.server.OrderQuoteOrBuilder getQuoteOrBuilder();
}