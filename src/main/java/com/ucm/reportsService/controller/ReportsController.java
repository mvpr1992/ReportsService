package com.ucm.reportsService.controller;

import java.util.Date;
import java.util.Formatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ucm.reportsService.entity.model.CreditCardTransaction;
import com.ucm.reportsService.entity.model.Customer;
import com.ucm.reportsService.entity.model.Transaction;
import com.ucm.reportsService.repo.CreditCardRepo;
import com.ucm.reportsService.repo.ReportsRepo;
import com.ucm.reportsService.repo.TransactionRepo;

@RestController
public class ReportsController {
	
	@Autowired
	private ReportsRepo reportsrepo;
	
	@Autowired
	private TransactionRepo transactionrepo;
	
	@Autowired
	private CreditCardRepo creditrepo;

	@GetMapping("/generateuserdatareport/{userId}")
	public void generateuserdatareport(@PathVariable String userId) {	 

		Customer customer = null;	
		try {
			System.out.print("userId +++++++" + userId);
			customer = reportsrepo.findByUserId(userId);
			
			Formatter output = new Formatter("D:\\eclipse-workspace\\UserInformationReport.xls");
	        output.format("User ID\tFirst Name\tLast Name\tMobile\teMail\tMailing Address\tZip Code\tCity\tCountry\tManagerId\n");	
	                  	
	        String firstName = customer.getFirstName();
     	    String lastName = customer.getLastName();
     		String mobile = customer.getMobile();
     		String email = customer.getEmail();
     		String mailingAddress = customer.getMailingAddress();
     		int zipCode = customer.getZipCode();
     		String city = customer.getCity();
     		String country= customer.getCountry();
     		int manId = customer.getManagerId();
     				
     		output.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n",userId,firstName,lastName,mobile,email,mailingAddress,zipCode,city,country,manId);
	        
     		output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}	
	
	@GetMapping("/alluserdatareportformanager/{managerId}")
	public void alluserdatareportformanager(@PathVariable int managerId) {	 

		List<Customer> customers = null;	
		try {
			System.out.print("alluserdatareport: " );
			customers = reportsrepo.findByManagerId(managerId);
			
			Formatter output = new Formatter("D:\\eclipse-workspace\\AllUserInformationReport.xls");
	        output.format("User ID\tFirst Name\tLast Name\tMobile\teMail\tMailing Address\tZip Code\tCity\tCountry\tManagerId\n");	
	        
	        for(int i=0;i<customers.size();i++) {
  	          
    	    	Customer customer = customers.get(i);
	                  	
    	    	String userId = customer.getUserId();
    	    	String firstName = customer.getFirstName();
    	    	String lastName = customer.getLastName();
    	    	String mobile = customer.getMobile();
    	    	String email = customer.getEmail();
    	    	String mailingAddress = customer.getMailingAddress();
    	    	int zipCode = customer.getZipCode();
    	    	String city = customer.getCity();
    	    	String country= customer.getCountry();
    	    	int manId = customer.getManagerId();
	        	
    	    	output.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n",userId,firstName,lastName,mobile,email,mailingAddress,zipCode,city,country,manId);
	        }
     		output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}	
	
	@GetMapping("/alluserdatareport")
	public void alluserdatareport() {

		List<Customer> customers = null;	
		try {
			System.out.print("alluserdatareport: " );
			customers = reportsrepo.findAll();
			
			Formatter output = new Formatter("D:\\eclipse-workspace\\AllUserInformationReport.xls");
	        output.format("User ID\tFirst Name\tLast Name\tMobile\teMail\tMailing Address\tZip Code\tCity\tCountry\tManagerId\n");	
	        
	        for(int i=0;i<customers.size();i++) {
  	          
    	    	Customer customer = customers.get(i);
	                  	
    	    	String userId = customer.getUserId();
    	    	String firstName = customer.getFirstName();
    	    	String lastName = customer.getLastName();
    	    	String mobile = customer.getMobile();
    	    	String email = customer.getEmail();
    	    	String mailingAddress = customer.getMailingAddress();
    	    	int zipCode = customer.getZipCode();
    	    	String city = customer.getCity();
    	    	String country= customer.getCountry();
    	    	int manId = customer.getManagerId();
	        	
    	    	output.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n",userId,firstName,lastName,mobile,email,mailingAddress,zipCode,city,country,manId);
	        }
     		output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}	

	
	@GetMapping("/transactionstatementreport/{customerId}/{fromdate}/{todate}")
	public void transactionstatementreport(@PathVariable int customerId,@PathVariable Date fromdate,@PathVariable Date todate) {	 
	   	
		List<Transaction> transactions = null;
	
		try {
			
	        transactions = transactionrepo.findByCustomerIdList(customerId,fromdate,todate);
			
	        Formatter output = new Formatter("D:\\eclipse-workspace\\TransactionStatement.xls");
	        output.format("Trasaction Id\tDate of Transaction\tTransaction Type\tTransaction Amount\tAccount Balance\n");	
	           
	           for(int i=0;i<transactions.size();i++) {
	    	          
	    	    	Transaction t = transactions.get(i);
	    	    	
	    	    	int transactionId = t.getTransactionId();
	    	    	String dateStr = t.getDateofTransaction().toString();
	    	    	String transactiontype = t.getTransactiontype();
	    	    	double amount = t.getAmount();
	    	    	double accountBalance = t.getAccountBalance();
   				
	    	    	output.format("%d\t%s\t%s\t%.2f\t%.2f\n",transactionId,dateStr,transactiontype,amount,accountBalance);
	           }
	           output.close();
	           
		} catch (Exception e) {
			e.printStackTrace();
		}	
	} 
	
	@GetMapping("/creditcardstatementreport/{customerId}/{fromdate}/{todate}")
	public void creditcardstatementreport(@PathVariable int customerId,@PathVariable Date fromdate,@PathVariable Date todate) {	 
	   	
		List<CreditCardTransaction> credittransactions = null;
		
		try {
			
			credittransactions = creditrepo.credittransactionlist(customerId, fromdate, todate);
			
	        Formatter output = new Formatter("D:\\eclipse-workspace\\CreditCardStatement.xls");
	        output.format("Trasaction Id\tDate of Transaction\tDescription\tTransaction Amount\tAvailable Limit\n");	
	           
	           for(int i=0;i<credittransactions.size();i++) {
	    	          
	        	   CreditCardTransaction t = credittransactions.get(i);
	    	    	
	    	    	int transactionId = t.getId();
	    	    	String dateStr = t.getDate().toString();
	    	    	String description = t.getDescription();
	    	    	double amount = t.getAmount();
	    	    	double accountBalance = t.getAvailablelimit();
   				
	    	    	output.format("%d\t%s\t%s\t%.2f\t%.2f\n",transactionId,dateStr,description,amount,accountBalance);
	           }
	           output.close();
	           
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
 }