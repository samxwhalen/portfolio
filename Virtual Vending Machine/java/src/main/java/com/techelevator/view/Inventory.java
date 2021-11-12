package com.techelevator.view;

import com.techelevator.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Inventory {

    public TreeMap<String, Item> inventory = new TreeMap<>();

    public Map<String, Item> getInventory() {
        return inventory;
    }

    public Inventory(){}

    public void stock(){
        File inventoryFile = new File("vendingmachine.csv");
        try (Scanner fileScanner = new Scanner(inventoryFile)) {

            while (fileScanner.hasNextLine()) {
                String inventoryLine = fileScanner.nextLine();
                String[] inventoryItem = inventoryLine.split("\\|");

                BigDecimal price = new BigDecimal(inventoryItem[2]);

                if (inventoryItem[3].equals("Candy")) {

                    Candy candyProduct = candyProduct = new Candy(inventoryItem[0], inventoryItem[1], price);
                    inventory.put(inventoryItem[0], candyProduct);

                } else if (inventoryItem[3].equals("Chip")) {

                    Chip chipProduct = chipProduct = new Chip(inventoryItem[0], inventoryItem[1], price);
                    inventory.put(inventoryItem[0], chipProduct);

                } else if (inventoryItem[3].equals("Drink")) {

                    Beverage beverageProduct = beverageProduct = new Beverage(inventoryItem[0], inventoryItem[1], price);
                    inventory.put(inventoryItem[0], beverageProduct);

                } else if (inventoryItem[3].equals("Gum")) {

                    Gum gumProduct = gumProduct = new Gum(inventoryItem[0], inventoryItem[1], price);
                    inventory.put(inventoryItem[0], gumProduct);

                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Pardon us while we restock our machine...");
        }
    }
}
