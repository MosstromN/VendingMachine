/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mostromn.vendingmachinetwo.servicelayer;

import com.mostromn.vendingmachinetwo.service.VendingMachineServiceLayer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class VendingMachineServiceLayerTest {
ApplicationContext ctx = 
        new ClassPathXmlApplicationContext("applicationContext.xml");
     VendingMachineServiceLayer service = ctx.getBean("serviceLayer", VendingMachineServiceLayer.class);    
}