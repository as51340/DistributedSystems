package org.example.AlmaOnline.defaults;

import org.example.AlmaOnline.provided.service.*;

import java.util.Arrays;

public class Initializer implements ServiceInitializer {

    @Override
    public AlmaOnlineService initialize() {

        var alma1Menu = new Menu()
            .addItem(new Item("vol-au-vent", 4.25))
            .addItem(new Item("bigAlma", 5.0))
            .addItem(new Item("moussaka", 3.30))
            .addItem(new Item("spaghetti", 3.30))
            .addItem(new Item("beef stew", 6.00));
        var alma2Menu = new Menu()
            .addItem(new Item("couscous with feta and olives", 5.00))
            .addItem(new Item("casar salad", 5.00))
            .addItem(new Item("quiche", 4.00))
            .addItem(new Item("red curry", 3.50))
            .addItem(new Item("deluxe wrap", 4.50));

        var alma1 = new Restaurant("1", "alma1", alma1Menu);
        var alma2 = new Restaurant("2", "alma2", alma2Menu);

        return new AlmaOnlineService(Arrays.asList(alma1, alma2));
    }
}
