package com.techelevator;


import com.techelevator.view.Inventory;
import java.math.BigDecimal;


public class VendingMachine {

    public Inventory currentInventory = new Inventory();
    private BigDecimal currentBalance;
    public Logger audit = new Logger();

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }
    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    //CONSTRUCTOR
    public VendingMachine() {
        this.currentBalance = new BigDecimal("0");
    }

    public void displayMenu() {
        for (Item item : currentInventory.getInventory().values()) {
            System.out.println(item);
        }
    }

    public String exitDialogue(){
        return "Umbrella Corp. thanks you for using Vendo-Matic 800 for your snacking needs.\nCome back again soon!";
    }

    public String purchaseProduct(String selection){

        BigDecimal startingBalance = currentBalance;
        String result = "";
        String toAdd = "";

        if(!currentInventory.getInventory().containsKey(selection)) {
            result = "NOT A VALID OPTION. TRY AGAIN.";
        }

        if(currentInventory.getInventory().get(selection).getQuantity() <= 0){
            result = "SOLD OUT";
        }

        if(currentInventory.getInventory().containsKey(selection) && currentInventory.getInventory().get(selection).getQuantity() > 0)  {

            BigDecimal itemPriceCheck = currentInventory.getInventory().get(selection).getPrice();
            BigDecimal moneyAvailable = getCurrentBalance();

            if ( itemPriceCheck.compareTo(moneyAvailable) < 0){

                BigDecimal newBalance = getCurrentBalance().subtract(currentInventory.getInventory().get(selection).getPrice()); //.subtract
                setCurrentBalance(newBalance);

                //subtract 1 from the quantity
                int newQuantity = currentInventory.getInventory().get(selection).getQuantity() - 1;
                currentInventory.getInventory().get(selection).setQuantity(newQuantity);

                //dispenses item
                result = currentInventory.getInventory().get(selection).getName() + " $" + currentInventory.getInventory().get(selection).getPrice() +
                        " | Remaining Balance: $" + getCurrentBalance() + "\n" + currentInventory.getInventory().get(selection).getDispenseSound();

            } else {
                result = "You know... these things cost money.....";
            }

            //LOGGER ISSUE POSSIBLE SOLUTION:
            //can we pass this to the logger right from here?

        }

        //LOGGER ISSUE - PLAYING AROUND WITH PLACEMENT.
        String productName = currentInventory.getInventory().get(selection).getName();
        String slotIdAudit = currentInventory.getInventory().get(selection).getSlotId();
        toAdd = productName + " " + slotIdAudit;

        String event = toAdd + "," + startingBalance.toString() + "," + getCurrentBalance().toString();
        audit.log(event);

        return result;

    }

    public void feedMoney(String amountToDeposit){

        BigDecimal startingBalance = currentBalance;

        if (amountToDeposit.equals("$1")){
            BigDecimal dollar = new BigDecimal(1.00);
            this.currentBalance = this.currentBalance.add(dollar);
        }
        if (amountToDeposit.equals("$2")){
            BigDecimal twoDollar = new BigDecimal(2.00);
            this.currentBalance = this.currentBalance.add(twoDollar);
        }
        if (amountToDeposit.equals("$5")){
            BigDecimal fiveDollar = new BigDecimal(5.00);
            this.currentBalance = this.currentBalance.add(fiveDollar);
        }
        if (amountToDeposit.equals("$10")){
            BigDecimal tenDollar = new BigDecimal(10.00);
            this.currentBalance = this.currentBalance.add(tenDollar);
        }

        // LOGGER ISSUE: TRYING A DIFFERENT ROUTE TO GET AROUND ISSUE
        String event = "FEED MONEY "+ "," + startingBalance + "," + getCurrentBalance() + ",";
        audit.log(event);

    }

    public String returnChange() {

        BigDecimal startingBalance = this.currentBalance;
        BigDecimal currentBalance = startingBalance;

        BigDecimal quarter = new BigDecimal("0.25");
        BigDecimal dime = new BigDecimal("0.10");
        BigDecimal nickel = new BigDecimal("0.05");

        BigDecimal zero = new BigDecimal("0.00");

        int numberOfQuarters = 0;
        int numberOfDimes = 0;
        int numberOfNickels = 0;

        String changeReturnStatement = "";

        while (currentBalance.compareTo(zero) > 0) {
            if (currentBalance.compareTo(quarter) >= 0){
                numberOfQuarters++;
                currentBalance = currentBalance.subtract(quarter);
            } else if (currentBalance.compareTo(dime) >= 0) {
                numberOfDimes++;
                currentBalance = currentBalance.subtract(dime);
            } else if (currentBalance.compareTo(nickel) >= 0) {
                numberOfNickels++;
                currentBalance = currentBalance.subtract(nickel);
            }
        }

        changeReturnStatement = "Your change is $" + this.currentBalance + " in \n" + numberOfQuarters + " quarter(s), " + numberOfDimes + " dime(s), " + numberOfNickels + " nickel(s).";
        this.currentBalance = currentBalance;

        //LOGGER ISSUE: MAY NEED TO MOVE?
        audit.log("GIVE CHANGE" + "," + startingBalance + "," + getCurrentBalance());

        return changeReturnStatement;
    }





}
