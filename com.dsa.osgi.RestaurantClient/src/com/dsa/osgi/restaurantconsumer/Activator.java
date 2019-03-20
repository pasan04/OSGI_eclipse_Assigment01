package com.dsa.osgi.restaurantconsumer;

import com.dsa.osgi.restaurantservice.utils.MathUtils;
import com.dsa.osgi.restaurantservice.utils.TableQueue;
import com.dsa.osgi.restaurantserviceresturant.MathService;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {
	 
    private static BundleContext context;
 
    static BundleContext getContext() {
        return context;
    }
 
    public void start(BundleContext bundleContext) throws Exception {
        Activator.context = bundleContext;
        System.out.println("MathConsumer Starting...");
 
        ServiceReference<MathService> serviceReference = context
                .getServiceReference(MathService.class);
        MathService service = (MathService) context
                .getService(serviceReference);
        Scanner myscanner = new Scanner(System.in);
        int intialstate = 1;
        int noOfTables = 10;
        
        while(intialstate != 0) {
        	
	        System.out.println("~WELCOME TO OLIVE GARDEN RESTAURANT RESERVATION~");
	        System.out.println("================================================");
	        System.out.print("Enter number of person :");
	        int noOfPerson = myscanner.nextInt();
	        System.out.println("-----------------------------------");
	        System.out.print("Enter Date :");
	        int bookingDate = myscanner.nextInt();
	        System.out.println("-----------------------------------");
	        System.out.print("Enter the time :");
	        float bookingTime = myscanner.nextFloat();
	        System.out.println("-----------------------------------");
	        
	        //The restaurant having tables with maximum of 10 person(10 chairs)
	        boolean allowAllocation = MathUtils.allocation(noOfPerson);
	        
	        //creating an object from Tablequeue inorder to check the availabity of the table in the restaurant
	        TableQueue table = new TableQueue(noOfTables);
	        if(allowAllocation == true) {
	        	
	        	if(table.isFull() == false) {
		        	System.out.println("You can book the table !");
		        	intialstate = 0;	        		
	        	}
	        	else {       		
	        		table.allocateTable(noOfPerson);
	        	}
	        }
	        else {
	        	System.out.println("Sorry, We do have tables containing maximum 10 seats !");
	        	System.out.print("Do you want to exit(Press 0 to exit, press 1 to continue :");
	        	intialstate = myscanner.nextInt();
	        	
	        }
        
        }
        
        
        
        
        System.out.println("Enter a value :");
        int a = myscanner.nextInt();
        System.out.println("5+3 = " + service.sum(a, 3));
 
        System.out.println("MathConsumer Started");
       
        System.out.println("5 * 6 = " + service.multiplication(5, 6));
        
        System.out.println("100/10 = " + service.division(100, 10));
    }
 
    public void stop(BundleContext bundleContext) throws Exception {
        Activator.context = null;
        System.out.println("MathConsumer Stopped");
    }
 
}