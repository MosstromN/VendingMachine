/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mostromn.vendingmachinetwo.dao;

import com.mostromn.vendingmachinetwo.dto.Item;
import java.util.List;

/**
 *
 * @author nicholasmostrom
 */

public interface VendingMachineDao {

    public List<Item> getAllItems() throws VendingMachinePersistenceException;

    public void editItem(int quantity, Item item) throws VendingMachinePersistenceException;

    public void removeItem(String id, Item item) throws VendingMachinePersistenceException;

    public Item getItem(String itemId) throws VendingMachinePersistenceException;

    /**
     *
     * @param entry
     * @throws VendingMachinePersistenceException
     */
    public void writeAudit(String entry) throws VendingMachinePersistenceException;
    

}

