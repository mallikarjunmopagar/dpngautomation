package testcases;

import java.io.IOException;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.opencsv.exceptions.CsvException;


import baseCode.APIRequest;
import baseCode.CompareTextFile;
import baseCode.CompareXMLFiles;

import utilities.ReadXMLFilePathFromCSV;

public class LogComp extends CompareTextFile {
	@Test(description = "Test_using_CSV", dataProvider = "log_compare_test1", enabled = true)
	public void logcompareTest1(String i, String j, String k, ITestContext context) throws Exception {

		test.assignAuthor("SDETLOG").assignCategory("FUNCTIONAL").assignDevice("LOGComparision");

		test.log(Status.INFO, "TestCase Name: " + k);
	
		
		//System.out.println(context.getCurrentXmlTest().getName());
	//System.out.println(APIRequest.ApiRequest1(j,k));
		logcomparision(System.getProperty("user.dir") + "\\src\\test\\resources\\ASIS_LOGS\\" + i,
			System.getProperty("user.dir") + "\\src\\test\\resources\\TO_BE_LOGS\\" +j, k); 
		// System.out.println(NoOfDifferences);
		
		//wait(1);
		//test.log(Status.INFO, "Total No of actual differences after filtering: " + NoOfDifferences);

		//Assert.assertFalse(NoOfDifferences > 0);

	}

	
	




	@DataProvider(name = "log_compare_test1")
	public Object[][] getdata() throws IOException, CsvException {

		return ReadXMLFilePathFromCSV.ReadPaths("loginput");
	}
   
	
	
}



