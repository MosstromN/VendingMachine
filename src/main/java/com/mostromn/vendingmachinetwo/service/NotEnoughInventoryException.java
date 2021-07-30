/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mostromn.vendingmachinetwo.service;

/**
 *
 * @author nicholasmostrom
 */
public class NotEnoughInventoryException extends Exception{
   
    public NotEnoughInventoryException(String message) {
        super(message);
    }
    
    public NotEnoughInventoryException(String message, Throwable cause) {
        super(message, cause);
    }
        
}
