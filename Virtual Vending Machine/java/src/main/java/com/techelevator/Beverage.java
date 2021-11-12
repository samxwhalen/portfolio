package com.techelevator;

import java.math.BigDecimal;

public class Beverage extends Item {

    public Beverage(String slotId, String name, BigDecimal price){

        super(slotId, name, price);
        setDispenseSound("Glug Glug, Yum!");
    }
}
