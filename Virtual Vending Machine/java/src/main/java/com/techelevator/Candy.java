package com.techelevator;

import java.math.BigDecimal;

public class Candy extends Item {

    public Candy(String slotId, String name, BigDecimal price){

        super(slotId, name, price);
        setDispenseSound("Munch Munch, Yum!");
    }

}
