package com.techelevator.view;

import com.techelevator.Item;
import com.techelevator.VendingMachine;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.math.BigDecimal;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class VendingMachineTests {

    VendingMachine tester = new VendingMachine();

    @Before
    public void setup(){
        System.out.println("Start Vending Machine Tests");
        tester.currentInventory.stock();
    }

    @After
    public void teardown() {
        System.out.println("End Vending Machine Tests");
    }

    @Test
    public void test01DoesPurchaseProductReturnItemWhenNoBalance(){
        String selection = "C4";
        String expectedReturn = "You know... these things cost money.....";

        Assert.assertEquals(expectedReturn, tester.purchaseProduct(selection));
    }

    @Test
    public void test02doesPurchaseProductReturnItem(){
        String selection = "B2";
        String expectedReturn = "Cowtales $1.50 | Remaining Balance: $3.50\n" +
                "Munch Munch, Yum!";

        tester.feedMoney("$5");

        Assert.assertEquals(expectedReturn, tester.purchaseProduct(selection));
    }

    @Test
    public void test03DoesPurchaseProductReturnItemWhenItemSoldOut(){
        String selection = "B2";
        tester.currentInventory.getInventory().get("B2").setQuantity(0);
        String expectedReturn = "SOLD OUT";

        tester.feedMoney("$5");

        Assert.assertEquals(expectedReturn, tester.purchaseProduct(selection));
    }


    @Test
    public void test04DoesFeedMoneyIncreaseCurrentBalance(){
        String amountToDeposit = "$5";
        BigDecimal expectedReturn = new BigDecimal(5.00);
        tester.feedMoney("$5");

        Assert.assertEquals(expectedReturn, tester.getCurrentBalance());
    }

    @Test
    public void test05ItemToStringTestPrintsItem(){
        String expectedReturn = "B3 | Wonka Bar                  $1.50 | Qty 5";
        String itemToReturn = tester.currentInventory.getInventory().get("B3").toString();

        Assert.assertEquals(expectedReturn, itemToReturn);
    }

    @Test
    public void test06DoesCurrentChangeReturnCorrectAmountWith5Dollars(){
        String amountToDeposit = "$5";
        String expectedReturn = "Your change is $5 in \n20 quarter(s), 0 dime(s), 0 nickel(s).";
        tester.feedMoney("$5");

        Assert.assertEquals(expectedReturn, tester.returnChange());
    }

    @Test
    public void test07DoesCurrentChangeReturnCorrectAmountWithPurchase(){
        BigDecimal testDeposit = new BigDecimal("2.00");
        tester.setCurrentBalance(testDeposit);

        tester.purchaseProduct("D1");
        String expectedReturn = "Your change is $1.15 in \n4 quarter(s), 1 dime(s), 1 nickel(s).";

        Assert.assertEquals(expectedReturn, tester.returnChange());
    }

    @Test
    public void test08DoesExitDialoguePrintExitStatement(){

        String expectedReturn = "Umbrella Corp. thanks you for using Vendo-Matic 800 for your snacking needs.\nCome back again soon!";
        Assert.assertEquals(expectedReturn, tester.exitDialogue());
    }




}
