/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mostromn.vendingmachinetwo.app;

import com.mostromn.vendingmachinetwo.controller.VendingMachineController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author nicholasmostrom
 */
public class App {
public static void main(String[] args)  {
       
//   UserIO myIo = new UserIOConsoleImpl();
//   VendingMachineView myView = new VendingMachineView(myIo);
//   VendingMachineDao myDao = new VendingMachineDaoFileImpl();
//   AuditDaoFileImpl myAuditDao = new AuditDaoFileImpl(myDao);
//   VendingMachineServiceLayer myService = new VendingMachineServiceLayer(myDao);
//   VendingMachineController controller = new VendingMachineController(myService, myView, myIo);
//        
//       controller.run();


ApplicationContext ctx = 
           new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingMachineController controller = 
           ctx.getBean("controller", VendingMachineController.class);
        controller.run();
 
 



    }       
}

    