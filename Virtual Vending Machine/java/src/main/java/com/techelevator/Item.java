package com.techelevator;

import java.math.BigDecimal;
import java.util.Formatter;

public abstract class Item {

    //VARIABLES
    private String name;
    private String slotId;
    private BigDecimal price;
    private int quantity = 5;  // Design decision : use an int counter vs. nested list for simplification
    private String dispenseSound;


    //GETTERS & SETTERS
    public String getName() {
        return name;
    }

    public String getSlotId() {
        return slotId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setDispenseSound(String dispenseSound) {
        this.dispenseSound = dispenseSound;
    }

    public String getDispenseSound() {
        return dispenseSound;

    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //CONSTRUCTOR
    public Item(String slotId, String name, BigDecimal price) {
        this.slotId = slotId;
        this.name = name;
        this.price = price;
        this.dispenseSound = "";
    }

    //METHODS
    @Override
    public String toString() {

        String quantity = String.valueOf(this.quantity);

        if(this.quantity == 0){
            quantity = "** SOLD OUT **";
        }

        StringBuilder itemFormatting = new StringBuilder();
        Formatter itemToPrint = new Formatter(itemFormatting);
        itemToPrint.format("%s | %-25s  $%s | Qty %s", this.slotId, this.name, this.price, quantity);

        return itemFormatting.toString();
    }
}
