/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmicorba2;


import HelloApp.*;     
import org.omg.CosNaming.*;  
import org.omg.CORBA.*;
/**
 *
 * @author pc
 */
public class HelloClient {
    public static void main(String args[]) { 
        try{ 
            ORB orb = ORB.init(args, null); 
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService"); 
            NamingContext ncRef = NamingContextHelper.narrow(objRef); 
            NameComponent nc = new NameComponent("Hello", ""); 
            NameComponent path[] = {nc}; 
            Hello helloRef = HelloHelper.narrow(ncRef.resolve(path)); 
            String Hello = helloRef.sayHello(); 
            System.out.println(Hello);       
        } catch(Exception e) { 
            System.out.println("ERROR : " + e); 
            e.printStackTrace(System.out); 
        }   
    } 
}
