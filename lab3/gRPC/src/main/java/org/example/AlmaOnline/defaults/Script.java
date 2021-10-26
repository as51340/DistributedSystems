package org.example.AlmaOnline.defaults;

import org.example.AlmaOnline.provided.client.*;
import org.example.AlmaOnline.server.AlmaOnlineGrpc;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class Script implements AppScript {

    private final static String ALMA1_ID = "1";
    private final static String ALMA2_ID = "2";

    private <T> void doAssert (boolean b, T expected, T actual) {
        if (!b) {
            throw new IllegalStateException("Assertion Failed, expected: " + expected.toString() + ", actual: " +  actual.toString());
        }
    }

    @Override
    public void Execute(
        AlmaOnlineClientAdapter adapter,
        AlmaOnlineGrpc.AlmaOnlineBlockingStub blocking,
        AlmaOnlineGrpc.AlmaOnlineFutureStub future
        ) throws Exception {

        System.out.println("Testing getting restaurant information");
        var restaurants = adapter.getRestaurants(blocking);
        doAssert(restaurants.size() == 2, 2, restaurants.size());
        var expectedRestaurants = Arrays.asList(
            new RestaurantInfo(ALMA1_ID, "alma1"),
            new RestaurantInfo(ALMA2_ID, "alma2")
        );

        doAssert (restaurants.containsAll(expectedRestaurants), expectedRestaurants, restaurants);
        System.out.println("Getting restaurant information OK");

        System.out.println("Testing getting menu information");
        var menu = adapter.getMenu(blocking, ALMA1_ID);
        var items = menu.getItems();
        doAssert(items.size() == 5, 5, items.size());
        var expectedItems = new HashMap<String, Double>();
        expectedItems.put("vol-au-vent", 4.25);
        expectedItems.put("bigAlma", 5.0);
        expectedItems.put("moussaka", 3.30);
        expectedItems.put("spaghetti", 3.30);
        expectedItems.put("beef stew", 6.00);
        doAssert(items.equals(expectedItems), expectedItems, items);
        System.out.println("Getting menu information OK");


        System.out.println("Testing dine-in orders");
        var dineInOrderID = UUID.randomUUID().toString();
        var dineInQuote = Arrays.asList(
            "vol-au-vent",
            "beef stew"
        );
        var dineInDate = new Date(1000 * (new Date().getTime() / 1000));
        var dineInOrder = new DineInOrderQuote(
            ALMA1_ID,
            dineInOrderID,
            "Earnie", dineInQuote,
            dineInDate
        );

        var f1 = adapter.createDineInOrder(future, dineInOrder);
        try {
            f1.get();
        } catch(Exception e) {
            e.printStackTrace();
            // should not throw an error
            doAssert(false, "no error", e);
        }

        var baseInfo1 = adapter.getOrder(blocking, ALMA1_ID, dineInOrderID);
        doAssert(baseInfo1 instanceof DineInOrderInfo, baseInfo1.getClass().getName(), DineInOrderInfo.class.getName());
        var dineInInfo = (DineInOrderInfo) baseInfo1;

        // round only to seconds, not milliseconds
        doAssert(dineInInfo.getReservationDate().getTime() == dineInDate.getTime(), 1000 * (dineInDate.getTime() / 1000), dineInInfo.getReservationDate().getTime());
        doAssert(dineInInfo.getCustomer().equals("Earnie"), "Earnie", dineInInfo.getCustomer());
        doAssert(dineInInfo.getCreateDate() != null, "not null", null);
        var expectedDineInItems = Arrays.asList(
            new ItemInfo("vol-au-vent", 4.25),
            new ItemInfo("beef stew", 6.00)
        );
        doAssert(dineInInfo.getItems().containsAll(expectedDineInItems), expectedDineInItems, dineInInfo.getItems());

        System.out.println("Dine-in orders OK");

        System.out.println("Testing delivery orders");
        var deliveryOrderID = UUID.randomUUID().toString();
        var deliveryQuote = Arrays.asList(
            "red curry",
            "deluxe wrap"
        );
        var address = "Sesame Street 32 NY";
        var deliveryOrder = new DeliveryOrder(
            ALMA2_ID,
            deliveryOrderID,
            "Cookie-monster",
            deliveryQuote,
            address
        );

        var f2 = adapter.createDeliveryOrder(future, deliveryOrder);
        try {
            f2.get();
        } catch (Exception e) {
            // should not happen
            e.printStackTrace();
            doAssert(false, "no error", e);
        }

        var baseInfo2 = adapter.getOrder(blocking, ALMA2_ID, deliveryOrderID);
        doAssert(baseInfo2 instanceof DeliveryOrderInfo, DeliveryOrderInfo.class.getName(),  baseInfo2.getClass().getName());
        var deliveryOrderInfo = (DeliveryOrderInfo) baseInfo2;
        doAssert(deliveryOrderInfo.getDeliveryAddress().equals(address), address, deliveryOrderInfo.getDeliveryAddress());
        doAssert(deliveryOrderInfo.getCustomer().equals("Cookie-monster"), "Cookie-monster", deliveryOrderInfo.getCustomer());
        doAssert( deliveryOrderInfo.getCreateDate() != null, "not null", null);
        var deliveryItems = Arrays.asList(
            new ItemInfo("red curry", 3.50),
            new ItemInfo("deluxe wrap", 4.50)
        );
        doAssert(deliveryOrderInfo.getItems().containsAll(deliveryItems), deliveryItems, deliveryOrderInfo.getItems());
        System.out.println("Delivery order OK");
    }
}
