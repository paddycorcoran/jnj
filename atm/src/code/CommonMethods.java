package code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class CommonMethods {
	
	public static List<String> getDataAsListFromFile(String filename) {

	    List<String> lines = Collections.emptyList();
	    
	    try 
	    {
	       lines = Files.readAllLines(Paths.get(filename)); 
	    } 
	    catch (IOException e) {
	    	System.out.println("File does Not Exist Please Try Again: ");
	    } 
	    System.out.println("Data input :"); 
	    System.out.println(lines); 
	    return lines;
	    }
	
	public static Boolean validatePin(String expected,String actual)
	{
		if (expected.equals(actual))
		{
		return true;
		} else {return false;}
	}

}
