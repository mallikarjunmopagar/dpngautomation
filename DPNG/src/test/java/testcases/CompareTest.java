package testcases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import baseCode.CompareXMLFiles;
import utilities.ReadXMLFilePathBasedonExecutionStatusfromExcel;

public class CompareTest extends CompareXMLFiles {

	//public static Logger log = LogManager.getLogger(CompareTest.class.getName());

	@Test(dataProvider = "dp", enabled = true)
	public void testcaseimplement1(String i, String j,String k) throws Exception {

		    test.assignAuthor("SAINAGA").assignCategory("FUNCTIONAL").assignDevice("XML");
	
		    test.log(Status.INFO,"Test Case Name: "+k);
	
		   filterAttributeandComparefiles(i, j); //
		  System.out.println(NoOfDifferences); test.log(Status.INFO,
		  "Total No of actual differences after filtering: " + NoOfDifferences);
		  
		  		  
		  Assert.assertFalse(NoOfDifferences > 0);
		  
		  
		 
		}

	@DataProvider(name = "dp")
	public Object[][] getdata() throws IOException {

		return ReadXMLFilePathBasedonExecutionStatusfromExcel.ReadPaths("sheet6");
	}

}
