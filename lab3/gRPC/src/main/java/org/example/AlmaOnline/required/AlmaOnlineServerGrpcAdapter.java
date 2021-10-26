package org.example.AlmaOnline.required;


import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import org.example.AlmaOnline.defaults.Initializer;
import org.example.AlmaOnline.provided.client.AlmaOnlineClientAdapter;
import org.example.AlmaOnline.provided.server.AlmaOnlineServerAdapter;
import org.example.AlmaOnline.provided.service.*;
import org.example.AlmaOnline.server.*;
import org.example.AlmaOnline.provided.service.exceptions.OrderException;
import org.example.AlmaOnline.server.DeliveryOrderQuote;
import org.example.AlmaOnline.server.DineInOrderQuote;
import org.example.AlmaOnline.server.OrderQuote;

import java.util.*;
import java.util.Date;
import java.util.stream.Collectors;

// AlmaOnlineServerGrpcAdapter implements the grpc-server side of the application.
// The implementation should not contain any additional business logic, only implement
// the code here that is required to couple your IDL definitions to the provided business logic.
public class AlmaOnlineServerGrpcAdapter extends AlmaOnlineGrpc.AlmaOnlineImplBase implements AlmaOnlineServerAdapter {

    // the service field contains the AlmaOnline service that the server will
    // call during testing.
    private final AlmaOnlineService service;

    public AlmaOnlineServerGrpcAdapter() {
        this.service = this.getInitializer().initialize();
    }

    @Override
    public void getRestaurants(GetRestaurantsRequest request, StreamObserver<GetRestaurantsResponse> responseObserver) {
        Iterator<Restaurant> restaurants = service.getRestaurants().iterator();
        while(restaurants.hasNext()) {
            Restaurant restaurant = restaurants.next();
            responseObserver.onNext(GetRestaurantsResponse.newBuilder().setName(restaurant.getName()).setId(restaurant.getId()).build());
        }
        responseObserver.onCompleted();
    }

    @Override
    public void getMenu(GetMenuRequest request, StreamObserver<GetMenuResponse> responseObserver) {
        //Optional<Menu> menu = service.getRestaurantMenu(request.getReservationId());
        //Map<String, Item> menuItems = menu.

    }

    @Override
    public void createDineInOrder(CreateDineInOrderRequest request, StreamObserver<CreateDineInOrderResponse> responseObserver) {
        DineInOrderQuote protoDineInOrderQuote = request.getQuote();
        OrderQuote protoOrderQuote = protoDineInOrderQuote.getQuote();
        Date businessDate = new Date(protoOrderQuote.getCreationDate().getYear(), protoOrderQuote.getCreationDate().getMonth(), protoOrderQuote.getCreationDate().getDay());
        Date businessReservationDate = new Date(protoDineInOrderQuote.getReservationDate().getYear(), protoDineInOrderQuote.getReservationDate().getMonth(), protoDineInOrderQuote.getReservationDate().getDay());
        org.example.AlmaOnline.provided.service.DineInOrderQuote businessQuote = new org.example.AlmaOnline.provided.service.DineInOrderQuote(
                protoOrderQuote.getId(), businessDate, protoOrderQuote.getCustomer(), protoOrderQuote.getLineItemsList(), businessReservationDate
        );
        try {
            service.createDineInOrder(request.getRestaurantId(), businessQuote);
        } catch (OrderException e) {
            e.printStackTrace();
        }
        responseObserver.onNext(CreateDineInOrderResponse.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void createDeliveryOrder(CreateDeliveryOrderRequest request, StreamObserver<CreateDeliveryOrderResponse> responseObserver) {
        DeliveryOrderQuote protoDeliverOrderQuote = request.getQuote();
        OrderQuote protoOrderQuote = protoDeliverOrderQuote.getQuote(); // parent class - it's embedded
        Date businessDate = new Date(protoOrderQuote.getCreationDate().getYear(), protoOrderQuote.getCreationDate().getMonth(), protoOrderQuote.getCreationDate().getDay());
        org.example.AlmaOnline.provided.service.DeliveryOrderQuote businessQuote = new org.example.AlmaOnline.provided.service.DeliveryOrderQuote(
                protoOrderQuote.getId(), businessDate, protoOrderQuote.getCustomer(),
                protoOrderQuote.getLineItemsList(), protoDeliverOrderQuote.getDeliveryAddress());
        try {
            service.createDeliveryOrder(request.getRestaurantId(), businessQuote);
        } catch (OrderException e) {
            e.printStackTrace();
        }
        responseObserver.onNext(CreateDeliveryOrderResponse.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void getOrder(GetOrderRequest request, StreamObserver<GetOrderResponse> responseObserver) {
        //Optional<Order> order = service.getOrder(request.getRestaurantId(), request.getOrderId());
        //responseObserver.onNext(GetOrderResponse.newBuilder().);

    }

    // -- Put the code for your implementation down below -- //
}
