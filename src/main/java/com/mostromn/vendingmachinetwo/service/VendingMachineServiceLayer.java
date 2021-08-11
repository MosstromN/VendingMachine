/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mostromn.vendingmachinetwo.service;

import com.mostromn.vendingmachinetwo.dao.VendingMachineDao;
import com.mostromn.vendingmachinetwo.dao.VendingMachinePersistenceException;
import com.mostromn.vendingmachinetwo.dto.Coin;
import com.mostromn.vendingmachinetwo.dto.Item;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author nicholasmostrom
 */

@Component
public class VendingMachineServiceLayer {

    VendingMachineDao dao;          
    HashMap<String, Coin> coins = new HashMap<>();

    @Autowired
    public VendingMachineServiceLayer(VendingMachineDao dao) {
        this.dao = dao;
    }

    
    public Item getItem(String itemId) throws VendingMachinePersistenceException {
        return dao.getItem(itemId);

    }

    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        return dao.getAllItems();
    }

    public void updateItem(String id, Item item, int x) throws VendingMachinePersistenceException {

        Item editedItem = item;
        int quantityInitial = editedItem.getQuantity();
        int quantityNew = quantityInitial - 1;
        editedItem.setQuantity(x);
        dao.editItem(quantityNew, editedItem);

    }

    public void ValidateMoneyAmount(BigDecimal userMoney) throws NotEnoughMoneyException {
        try {
            if (BigDecimal.ZERO.equals(userMoney) || userMoney == null) {
                throw new NotEnoughMoneyException(
                        "Invalid amount. Money must be greater than 0 and not null!");
            }
        } catch (NumberFormatException e) {
            throw new NotEnoughMoneyException(
                    "Invalid amount. Money must be greater than 0 and not null!");
        }
    }

    public void hasEnoughMoney(BigDecimal userMoney, Item selectedItem) throws NotEnoughMoneyException {

        BigDecimal price = selectedItem.getPrice();
        if (userMoney.compareTo(price) < 0) {
            throw new NotEnoughMoneyException(
                    "You do not have enough money!");
        }

    }

    public boolean validSelection(Item selectedItem) throws VendingMachinePersistenceException, NotEnoughInventoryException {

        if (selectedItem != null) {
            if (selectedItem.getQuantity() != 0) {
                return true;
            } else {
                throw new NotEnoughInventoryException(
                        "Sorry, that item is out of stock!");
            }
        } else {
            throw new VendingMachinePersistenceException(
                    "Sorry that is not a valid selection!");
        }
    }

    public HashMap<String, Coin> getChange(BigDecimal userMoney, Item selectedItem) {

        int quarters = 0;
        int dimes = 0;
        int nickels = 0;
        int pennies = 0;
        Coin quarter = new Coin("Quarter(s)");
        Coin dime = new Coin("Dime(s)");
        Coin nickel = new Coin("Nickel(s)");
        Coin pennie = new Coin("Pennie(s)");
        BigDecimal change = userMoney.subtract(selectedItem.getPrice());
        change = change.multiply(new BigDecimal(100));

        while (change.compareTo(BigDecimal.ZERO) > 0) {

            if (change.compareTo(new BigDecimal(25)) >= 0) {
                change = change.subtract(new BigDecimal(25));
                quarters++;
            } else if (change.compareTo(new BigDecimal(10)) >= 0) {
                change = change.subtract(new BigDecimal(10));
                dimes++;
            } else if (change.compareTo(new BigDecimal(5)) >= 0) {
                change = change.subtract(new BigDecimal(5));
                nickels++;
            } else if (change.compareTo(new BigDecimal(0)) >= 0) {
                change = change.subtract(new BigDecimal(1));
                pennies++;

            } else {
                break;
            }

        }
        quarter.setNumberOf(quarters);
        dime.setNumberOf(dimes);
        nickel.setNumberOf(nickels);
        pennie.setNumberOf(pennies);

        coins.put(quarter.getName(), quarter);
        coins.put(dime.getName(), dime);
        coins.put(nickel.getName(), nickel);
        coins.put(pennie.getName(), pennie);

        return coins;

    }
   
    public static final String AUDIT_FILE = "AuditDaoFile";
  
    public void writeAudit(String entry) throws VendingMachinePersistenceException {
        
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException("Could not audit information.", e);
        }
 
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " );
        out.flush();
    }
}
