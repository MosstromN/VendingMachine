/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mostromn.vendingmachinetwo.ui;


import com.mostromn.vendingmachinetwo.dto.Coin;
import com.mostromn.vendingmachinetwo.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 *
 * @author nicholasmostrom
 */

@Component
public class VendingMachineView {

    @Autowired
    private UserIO io;

    @Autowired
    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    
    public boolean anotherSelection() {
        String userAnswer = io.readString("Would you like to make another seleciton? (y/n) then press enter:");
        userAnswer.toLowerCase();
        if (userAnswer.equals("y")) {
            return true;
        } else {
            return false;
        }
    }

    public String getUserSelection() {
        return io.readString("Please Select an Item");
    }

    public void displayWelcome() {
        io.print("=========VENDING MACHINE=========");
    }

    public void displayChange(HashMap<String, Coin> coins) {
        io.print("===Dispensed Change===");

        io.print("Quarters: " + coins.get("Quarter(s)").getNumberOf());
        io.print("Dimes: " + coins.get("Dime(s)").getNumberOf());
        io.print("Nickels: " + coins.get("Nickel(s)").getNumberOf());
        io.print("Pennies: " + coins.get("Pennie(s)").getNumberOf());
    }

    
    
    public void displaySuccessfullDispense(Item selectedItem){
        io.print("=== Successfully Dispensed " + selectedItem.getItemName() + " ===");
    }

    public void displayExitMessage() {
        io.print("Thank you, have a great day!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public void displayNotEnoughMoney() {
        io.print("=======Error=======");
        io.print("Not Enough Money. Dispensing Change: ");
    }

    public BigDecimal getUserMoney() {
        BigDecimal userMoney = BigDecimal.ZERO;
        boolean hasErrors = false;
        do {
            try {
                io.print("Please enter the amount of money you are isterting (0-5$)");
                userMoney = new BigDecimal(io.readString("---------(Or enter -1 to exit Program!)--------"));
                hasErrors = false;
            } catch (NumberFormatException e) {
                io.print("Not a valid number. Please Select Again");
                hasErrors = true;

            }
        } while (hasErrors == true);
        return userMoney;
    }
    
    
    
        public void displayList(List<Item> items) {

        items.stream().filter((currentItem) -> (currentItem.getQuantity() > 0)).forEachOrdered((currentItem) -> {
            io.print("[" + currentItem.getId() + "] - "
                    + currentItem.getItemName() + "  : $"
                    + currentItem.getPrice().toString());
        });
        
        items.stream().filter((currentItem) -> (currentItem.getQuantity() == 0)).forEachOrdered((currentItem) -> {
            io.print("[" + currentItem.getId() + "] - "
                    + currentItem.getItemName() + " (OUT OF STOCK!)");
                   
        });
    }

}
