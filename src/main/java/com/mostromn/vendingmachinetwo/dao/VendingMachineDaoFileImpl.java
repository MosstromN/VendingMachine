/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mostromn.vendingmachinetwo.dao;

import com.mostromn.vendingmachinetwo.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/**
 *
 * @author nicholasmostrom
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {

    private Map<String, Item> itemList = new HashMap();
    public static final String ITEM_FILE = "vendingMachineStock";
    public static final String DELIMITER = "::";
    public static final String AUDIT_FILE = "AuditDaoFile";
    
     
     
     
    @Override
    public Item getItem(String itemId)throws VendingMachinePersistenceException {
        loadFile();
        return itemList.get(itemId);
    }
    
    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        loadFile();
        return new ArrayList<Item>(itemList.values());
    }

    @Override
    public void removeItem(String id, Item item) throws VendingMachinePersistenceException {
        itemList.remove(id);
        writeItem();
        
    }
   
    @Override
    public void editItem(int quantityNew, Item item) throws VendingMachinePersistenceException {
        item.setQuantity(quantityNew);
        writeItem();
    }

    
    private void loadFile() throws VendingMachinePersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(ITEM_FILE)));

        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "-_- Could not load items data into memory.", e);
        }

        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Item currentItem = new Item(currentTokens[0], currentTokens[1], 
            new BigDecimal(currentTokens[3]));
            currentItem.setQuantity(Integer.parseInt(currentTokens[2]));

        
            itemList.put(currentItem.getId(), currentItem);
        }
      
        scanner.close();
    }

    
    
    
    
    private void writeItem() throws VendingMachinePersistenceException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ITEM_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "Could not save items.", e);
        }

        List<Item> itemList = this.getAllItems();
        for (Item currentItem : itemList) {

            out.println(currentItem.getId() + DELIMITER
                    + currentItem.getItemName() + DELIMITER
                    + currentItem.getQuantity() + DELIMITER
                    + currentItem.getPrice());        
            out.flush();
        }

        out.close();
    }
    
    /**
     *
     * @param entry
     * @throws VendingMachinePersistenceException
     */
    @Override
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

    

   