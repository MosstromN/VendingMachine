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
public class NotEnoughMoneyException extends Exception{
  
    public NotEnoughMoneyException(String message) {
        super(message);
    }
    
    public NotEnoughMoneyException(String message, Throwable cause) {
        super(message, cause);
    }    
}
