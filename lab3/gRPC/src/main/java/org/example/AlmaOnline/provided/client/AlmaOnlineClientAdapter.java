package org.example.AlmaOnline.provided.client;

import com.google.common.util.concurrent.ListenableFuture;
import org.example.AlmaOnline.defaults.Script;
import org.example.AlmaOnline.server.AlmaOnlineGrpc;

import java.util.List;

public interface AlmaOnlineClientAdapter {
    List<RestaurantInfo> getRestaurants(AlmaOnlineGrpc.AlmaOnlineBlockingStub stub);

    MenuInfo getMenu(AlmaOnlineGrpc.AlmaOnlineBlockingStub stub, String restaurantId);

    ListenableFuture<?> createDineInOrder(AlmaOnlineGrpc.AlmaOnlineFutureStub stub, DineInOrderQuote order);

    ListenableFuture<?> createDeliveryOrder(AlmaOnlineGrpc.AlmaOnlineFutureStub stub, DeliveryOrder order);

    BaseOrderInfo getOrder(AlmaOnlineGrpc.AlmaOnlineBlockingStub stub, String restaurantId, String orderId);

    default AppScript getScript() {
        return new Script();
    }
}
