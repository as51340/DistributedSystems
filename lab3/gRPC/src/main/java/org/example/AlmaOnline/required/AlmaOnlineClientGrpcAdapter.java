package org.example.AlmaOnline.required;

import com.google.common.util.concurrent.ListenableFuture;
import org.example.AlmaOnline.provided.client.*;
import java.util.List;

// AlmaOnlineClientGrpcAdapter provides your own implementation of the AlmaOnlineClientAdapter
public class AlmaOnlineClientGrpcAdapter implements AlmaOnlineClientAdapter {

	// getRestaurants should retrieve the information on all the available restaurants.
    @Override
    public List<RestaurantInfo> getRestaurants(AlmaOnlineGrpc.AlmaOnlineBlockingStub stub) {
        return null;
    }

    // getMenu should return the menu of a given restaurant
    @Override
    public MenuInfo getMenu(AlmaOnlineGrpc.AlmaOnlineBlockingStub stub, String restaurantId) {
        return null;
    }

    // createDineInOrder should create the given dine-in order at the AlmaOnline server
    @Override
    public ListenableFuture<?> createDineInOrder(AlmaOnlineGrpc.AlmaOnlineFutureStub stub, DineInOrderQuote order) {
        return null;
    }

    // createDeliveryOrder should create the given delivery order at the AlmaOnline server
    @Override
    public ListenableFuture<?> createDeliveryOrder(AlmaOnlineGrpc.AlmaOnlineFutureStub stub, DeliveryOrder order) {
        return null;
    }

    // getOrder should retrieve the order information at the AlmaOnline server given the restaurant the order is
    // placed at and the id of the order.
    @Override
    public BaseOrderInfo getOrder(AlmaOnlineGrpc.AlmaOnlineBlockingStub stub, String restaurantId, String orderId) {
        return null;
    }

    // getScript returns the script the application will run during testing.
    // You can leave the default implementation, as it will test most of the functionality.
    // Alternatively, you can provide your own implementation to test your own edge-cases.
    @Override
    public AppScript getScript() {
        return AlmaOnlineClientAdapter.super.getScript();
    }
}
