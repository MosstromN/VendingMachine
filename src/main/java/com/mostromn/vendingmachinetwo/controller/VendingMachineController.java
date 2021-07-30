/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mostromn.vendingmachinetwo.controller;


import com.mostromn.vendingmachinetwo.dao.VendingMachinePersistenceException;
import com.mostromn.vendingmachinetwo.dto.Coin;
import com.mostromn.vendingmachinetwo.dto.Item;
import com.mostromn.vendingmachinetwo.service.NotEnoughInventoryException;
import com.mostromn.vendingmachinetwo.service.NotEnoughMoneyException;
import com.mostromn.vendingmachinetwo.service.VendingMachineServiceLayer;
import com.mostromn.vendingmachinetwo.ui.UserIO;
import com.mostromn.vendingmachinetwo.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import static java.util.Map.entry;

/**
 *
 * @author nicholasmostrom
 */
    
 public class VendingMachineController {

    private VendingMachineView view;
    private VendingMachineServiceLayer service;

    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view, UserIO myIO) {
        this.service = service;
        this.view = view;
        
    }

    public String entry = new String();

    /**
     *
     */
    public void run(){ 
              
        boolean again = true;
        do {
            try {

                displayWelcomeMessage();
                displayAllItems();
                BigDecimal userMoney = getUsersMoney();
                if (userMoney.intValue() == -1) {
                    displayExitMessage();
                    System.exit(0);
                }
                Item selectedItem = getUserItem();
                int x = 0;
                getChange(userMoney, selectedItem, x);
                again = anotherSelection();

            }catch (VendingMachinePersistenceException e) {
                view.displayErrorMessage(e.getMessage());
            } 

        } while (again == true);

        displayExitMessage();       
        
        
}

    public void displayAllItems() throws VendingMachinePersistenceException {
        List<Item> itemList = service.getAllItems();
        view.displayList(itemList);

    }

    public boolean anotherSelection() {
        return view.anotherSelection();
    }
    
    public void displayExitMessage() {
        view.displayExitMessage();
    }

    public void displayWelcomeMessage() {
        view.displayWelcome();
    }


    

    public Item getUserItem() throws VendingMachinePersistenceException {
        String userSelection = "";

        Item selectedItem = new Item("", "", BigDecimal.ZERO); 
        BigDecimal price = BigDecimal.ZERO;
        boolean isValid;
        do {
            try {
                userSelection = view.getUserSelection();
                selectedItem = service.getItem(userSelection);
                service.validSelection(selectedItem);
                isValid = true;
                
            } catch (NotEnoughInventoryException e) {
                isValid = false;
                view.displayErrorMessage(e.getMessage());
                service.writeAudit(entry);
            }catch (VendingMachinePersistenceException e) {
                isValid = false;
                view.displayErrorMessage(e.getMessage());
                service.writeAudit(entry);
            }
            

        } while (isValid == false);

        return selectedItem;
    }

    public void getChange(BigDecimal userMoney, Item selectedItem, int x) throws VendingMachinePersistenceException {
        Item blankItem = new Item("FAKE", "ITEM", BigDecimal.ZERO);
        try {
            service.hasEnoughMoney(userMoney, selectedItem);
            HashMap<String, Coin> change = service.getChange(userMoney, selectedItem);
            view.displaySuccessfullDispense(selectedItem);
            view.displayChange(change);
            service.updateItem(selectedItem.getId(), selectedItem, x);
        } catch (NotEnoughMoneyException e) {
            view.displayNotEnoughMoney();
            HashMap<String, Coin> change = service.getChange(userMoney, blankItem);
            view.displayChange(change);
            service.writeAudit(entry);
        }

    }

    
    public BigDecimal getUsersMoney() throws VendingMachinePersistenceException {
        boolean isValid = true;
        BigDecimal userMoney = BigDecimal.ZERO;
        do {
            try {
                userMoney = view.getUserMoney();
                if (userMoney.equals(-1)) {
                    break;
                }
                service.ValidateMoneyAmount(userMoney);
                isValid = true;

            } catch (NotEnoughMoneyException e) {
                isValid = false;
                view.displayErrorMessage(e.getMessage());
                service.writeAudit(entry);

            }
        } while (isValid == false);

        return userMoney;
    }
}

