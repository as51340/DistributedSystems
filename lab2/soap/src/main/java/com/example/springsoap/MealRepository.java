package com.example.springsoap;

import javax.annotation.PostConstruct;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import io.foodmenu.cs.webservice.*;


import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class MealRepository {
    private static final Map<String, Meal> meals = new HashMap<String, Meal>();

    @PostConstruct
    public void initData() {

        Meal a = new Meal();
        a.setName("Steak");
        a.setDescription("Steak with fries");
        a.setMealtype(Mealtype.MEAT);
        a.setKcal(1100);
        a.setPrice(35.0f);


        meals.put(a.getName(), a);

        Meal b = new Meal();
        b.setName("Portobello");
        b.setDescription("Portobello Mushroom Burger");
        b.setMealtype(Mealtype.VEGAN);
        b.setKcal(637);
        b.setPrice(30.0f);


        meals.put(b.getName(), b);

        Meal c = new Meal();
        c.setName("Fish and Chips");
        c.setDescription("Fried fish with chips");
        c.setMealtype(Mealtype.FISH);
        c.setKcal(950);
        c.setPrice(15.0f);


        meals.put(c.getName(), c);
    }
   
    public Meal findMeal(String name) {
        Assert.notNull(name, "The meal's code must not be null");
        return meals.get(name);
    }

    public Meal findBiggestMeal() {

        if (meals == null) return null;
        if (meals.size() == 0) return null;

        var values = meals.values();
        return values.stream().max(Comparator.comparing(Meal::getKcal)).orElseThrow(NoSuchElementException::new);

    }
    
    public Meal getCheapestMeal() {
    	if(meals == null) return null;
    	if(meals.size() == 0) return null;
    	
    	Collection<Meal> mealValues = meals.values();
    	
    	return mealValues.stream().min(Comparator.comparing(Meal::getPrice)).orElseThrow(NoSuchElementException::new);	
    }
    
    public OrderConfirmation createNewOrder(Order order) throws DatatypeConfigurationException {
    	Assert.notNull(order, "Order must not be null!");
    	OrderConfirmation oc = new OrderConfirmation();
    	System.out.println(order.getOrderEntries().size());
    	for(OrderEntry oe: order.getOrderEntries()) {
    		System.out.println(oe.getMealName());
    		for(Meal meal: meals.values()) {
        		if(meal.getName().equals(oe.getMealName())) {
        			oc.getMeals().add(meal);
        		}
        	}
    	}
    	
    	LocalDateTime currentDateTime = LocalDateTime.now();
    	currentDateTime = currentDateTime.plusMinutes(120);
    	ZonedDateTime zoneDateTime = ZonedDateTime.of(currentDateTime, ZoneId.systemDefault());
    	//Creating GregorianCalendar instance
    	GregorianCalendar gregorianCalendar = GregorianCalendar.from(zoneDateTime);
    	XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
    	
    	oc.setDeliveryTime(xmlGregorianCalendar);
    	oc.setAddress(order.getAddress());
    	
    	return oc;
    }


}