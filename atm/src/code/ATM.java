package code;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ATM {

public static void main(String[] args) {

	//Read input to list
    List<String> input = Collections.emptyList();
    input = CommonMethods.getDataAsListFromFile("users.txt");
    
    //retrieve total available
    double totalAtmCash = Double.parseDouble(input.get(0));
    System.out.println(totalAtmCash);
    
    //list for each transaction and one for all
    List<String> transaction = new ArrayList<String>();
    ArrayList<ArrayList<String>> listOfTransactions = new ArrayList<ArrayList<String>>();
    
    //read in each transaction group into a list and from 3rd row
    for(int i=2;i<input.size();i++)
    {
    	//if empty line no lines left,read lines into a list
    	if(!input.get(i).isEmpty() || !input.iterator().hasNext())
    	{
    		transaction.add(input.get(i));
    	} else {
    	listOfTransactions.add(new ArrayList<>(transaction));
    	//clear the list for the next tranaction group
    	transaction.clear();
    	}

    }//end for
    
    int i = 0;
    //iterate through transaction groups.split strings on space delimeter.
    for (List<String> d : listOfTransactions)
    {
    	
    	int rows = d.size();
    	String accDetails = d.get(0);    		
    	String parts[] = accDetails.split(" ");
//        if (parts.length == 3)
//        {
        	String actual = parts[1];
        	String expected = parts[2];
        	//verify pin
        	Boolean pinVerify = CommonMethods.validatePin(expected, actual);
        	if (pinVerify == true) {
        		} else {
        		   System.out.println("ACCOUNT_ERR");	
        		   continue;
        		}
         
        String custBal = d.get(1);
        String balAndOd[] = custBal.split(" ");
        double custBalance = Double.parseDouble(balAndOd[0]);
        double custOverdraft = Double.parseDouble(balAndOd[1]);
        
        //loop through the transaction types
        int counter = 2;
        while(counter < d.size())
        	
        {
        if (i > rows+1)
        {
        	break;
        } else {
        String action = d.get(counter);
        String actionDetails[] = action.split(" ");
        
        //verify actions and calculations displaying error messages 
        //where necessary
        switch (actionDetails[0]) {
        case "B" : System.out.println(custBalance);break;
        case "W" :
        	//check atm has cash,and that the withdrawal amount is less the balance + overdraft
        	//subtract the withdrawal amount from the customer balance and atm total balance.
        	double withdrawalAmount = Double.parseDouble(actionDetails[1]);
        	if (withdrawalAmount > totalAtmCash){
        		System.out.println("ATM_ERR");
        	} 
        	else if (withdrawalAmount > custBalance + custOverdraft)
        	{
        		System.out.println("FUNDS_ERR");
        	} else {
        		custBalance = custBalance - withdrawalAmount;
        		totalAtmCash = totalAtmCash - withdrawalAmount;
        		System.out.println(custBalance);        		
        	} break;
        	default : System.out.println("ACCOUNT_ERR");
        		break;
        
        }
        }
        counter++;
        } // end while 


       i++; 	      	
      } // end for
    }//end main

}// end class
