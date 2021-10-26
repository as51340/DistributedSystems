package org.example.AlmaOnline.required;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.Timestamp;
import org.example.AlmaOnline.provided.client.*;
import org.example.AlmaOnline.provided.client.DineInOrderQuote;
import org.example.AlmaOnline.provided.client.ItemInfo;
import org.example.AlmaOnline.provided.service.Menu;
import org.example.AlmaOnline.server.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// AlmaOnlineClientGrpcAdapter provides your own implementation of the AlmaOnlineClientAdapter
public class AlmaOnlineClientGrpcAdapter implements AlmaOnlineClientAdapter {
    // getRestaurants should retrieve the information on all the available restaurants.
    @Override
    public List<RestaurantInfo> getRestaurants(AlmaOnlineGrpc.AlmaOnlineBlockingStub stub) {
       Iterator<GetRestaurantsResponse> response = stub.getRestaurants(GetRestaurantsRequest.newBuilder().build());
       List<RestaurantInfo> restaurants = new ArrayList<>();
       while(response.hasNext()) {
           GetRestaurantsResponse one_rest = response.next();
           restaurants.add(new RestaurantInfo(one_rest.getId(), one_rest.getName()));
       }
       return restaurants;
    }

    // getMenu should return the menu of a given restaurant
    @Override
    public MenuInfo getMenu(AlmaOnlineGrpc.AlmaOnlineBlockingStub stub, String restaurantId) {
        GetMenuRequest request = GetMenuRequest.newBuilder().setRestaurantId(restaurantId).build(); // it should be restaurant id not reservartion_id :( typo
        GetMenuResponse resp = stub.getMenu(request);
        return new MenuInfo(resp.getItemsMap());
    }

    // createDineInOrder should create the given dine-in order at the AlmaOnline server
    @Override
    public ListenableFuture<?> createDineInOrder(AlmaOnlineGrpc.AlmaOnlineFutureStub stub, org.example.AlmaOnline.provided.client.DineInOrderQuote order) {
        // createDate and reservationDate are obviously the same?
        String businessRestaurantId = order.getRestaurantId();
        Date outputDate = Date.newBuilder().setDay(order.getReservationDate().getDay()).setMonth(order.getReservationDate().getMonth()).setYear(order.getReservationDate().getYear()).build();
        List<String> businessItems = order.getItems();
        OrderQuote outputOrderDineInQuote =
                OrderQuote.newBuilder().setCustomer(order.getCustomer()).setId(order.getOrderId()).setCreationDate(outputDate).addAllLineItems(businessItems).build();
        org.example.AlmaOnline.server.DineInOrderQuote outputDineOrderQuote =
                org.example.AlmaOnline.server.DineInOrderQuote.newBuilder().setQuote(outputOrderDineInQuote).setReservationDate(outputDate).build();

        return stub.createDineInOrder(CreateDineInOrderRequest.newBuilder().setRestaurantId(businessRestaurantId).setQuote(outputDineOrderQuote).build());
    }

    // createDeliveryOrder should create the given delivery order at the AlmaOnline server
    @Override
    public ListenableFuture<?> createDeliveryOrder(AlmaOnlineGrpc.AlmaOnlineFutureStub stub, DeliveryOrder order) {
        java.util.Date today = new java.util.Date();
        Date outputDate = Date.newBuilder().setYear(today.getYear()).setMonth(today.getMonth()).setDay(today.getDay()).build();
        OrderQuote outputParentQuote = OrderQuote.newBuilder().setCustomer(order.getCustomer()).setId(order.getOrderId()).setCreationDate(outputDate).build();
        DeliveryOrderQuote deliveryOrderQuote = DeliveryOrderQuote.newBuilder().setQuote(outputParentQuote).setDeliveryAddress(order.getDeliveryAddress()).build();
        return stub.createDeliveryOrder(CreateDeliveryOrderRequest.newBuilder().setRestaurantId(order.getRestaurantId()).setQuote(deliveryOrderQuote).build());
    }

    // getOrder should retrieve the order information at the AlmaOnline server given the restaurant the order is
    // placed at and the id of the order.
    @Override
    public BaseOrderInfo getOrder(AlmaOnlineGrpc.AlmaOnlineBlockingStub stub, String restaurantId, String orderId) {
        GetOrderRequest request = GetOrderRequest.newBuilder().setOrderId(orderId).setRestaurantId(restaurantId).build();
        GetOrderResponse response = stub.getOrder(request);
        java.util.Date newDate = new java.util.Date(response.getCreateDate().getYear(),
                response.getCreateDate().getMonth(), response.getCreateDate().getDay());
        List<ItemInfo> businessLogicList = new ArrayList<>();
        for (org.example.AlmaOnline.server.ItemInfo protoInfo : response.getItemsList()) {
            businessLogicList.add(new ItemInfo(protoInfo.getName(), protoInfo.getPrice()));
        }
        return new BaseOrderInfo(response.getCustomer(), newDate, businessLogicList);
    }

    // getScript returns the script the application will run during testing.
    // You can leave the default implementation, as it will test most of the functionality.
    // Alternatively, you can provide your own implementation to test your own edge-cases.
    @Override
    public AppScript getScript() {
        return AlmaOnlineClientAdapter.super.getScript();
    }
}
