/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mostromn.vendingmachinetwo.dao;

/**
 *
 * @author nicholasmostrom
 */

import com.mostromn.vendingmachinetwo.dao.VendingMachineDao;
import com.mostromn.vendingmachinetwo.service.NotEnoughInventoryException;
import com.mostromn.vendingmachinetwo.service.NotEnoughMoneyException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import com.mostromn.vendingmachinetwo.controller.VendingMachineController;
import java.time.LocalDateTime;
import java.util.stream.Stream;
import org.aspectj.lang.JoinPoint;

public class AuditDaoFileImpl {

VendingMachineDao dao;
VendingMachineController controller;    
  

public AuditDaoFileImpl (VendingMachineDao dao) {
        this.dao = dao;
        this.controller = controller;
    }

    
/**VMPersistenceException (VendingMachinePersistenceException, boolean VendingMachinePersistenceException){
    System.out.print(VendingMachinePersistenceException); 
    return writeFileB();
}
    
VMInventoryException (NotEnoughInventoryException, boolean NotEnoughInventoryException){
    System.out.print(NotEnoughInventoryException);
    return writeFileB();
}

VMMoneyException (NotEnoughMoneyException, boolean NotEnoughMoneyException){
    System.out.print(NotEnoughMoneyException); 
    return writeFileB();
}
*/

   
    
    public static void method(String m) {
try{
  // Create file 
  FileWriter fstream = new FileWriter("AuditDaoFile");
  BufferedWriter out = new BufferedWriter(fstream);
   LocalDateTime timestamp = LocalDateTime.now();
        System.out.println(timestamp.toString() + " : " );
        out.flush();
  }catch (IOException e){
     
   }
  }
     
     

    }
 
    



  

    

