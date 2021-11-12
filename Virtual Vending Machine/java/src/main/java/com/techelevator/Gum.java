package com.techelevator;

import java.math.BigDecimal;

public class Gum extends Item {

    public Gum(String slotId, String name, BigDecimal price){
        super(slotId, name, price);
        setDispenseSound("Chew Chew, Yum!");
    }


}
