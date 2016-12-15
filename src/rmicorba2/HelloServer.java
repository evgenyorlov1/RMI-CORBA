/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmicorba2;


import HelloApp.*; 
import org.omg.CosNaming.*; 
import org.omg.CosNaming.NamingContextPackage.*; 
import org.omg.CORBA.*; 
/**
 *
 * @author pc
 */
public class HelloServer {
        
    public static void main(String args[]) { 
        try{     
            ORB orb = ORB.init(args, null);    
            HelloServant helloRef = new HelloServant(); 
            orb.connect(helloRef);         
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");        
            NamingContext ncRef = NamingContextHelper.narrow(objRef);             
            NameComponent nc = new NameComponent("Hello", ""); 
            NameComponent path[] = {nc}; 
            ncRef.rebind(path, helloRef); 
            // Wait for invocations from clients 
            java.lang.Object sync = new java.lang.Object(); 
            synchronized(sync){ 
              sync.wait(); 
            } 
    } catch(Exception e) { 
        System.err.println("ERROR: " + e); 
        e.printStackTrace(System.out); 
      }   
  } 
}

class HelloServant extends _HelloImplBase { 
    public String sayHello() { 
        return "\nHello world!!\n"; 
    } 
} 