package testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.aventstack.extentreports.Status;
import com.opencsv.exceptions.CsvException;


import baseCode.APIRequest;
import baseCode.CompareXMLFiles;
import utilities.ReadXMLFilePathFromCSV;

public class Test1 extends CompareXMLFiles {
	@Test(description = "Test_using_CSV", dataProvider = "compare_test1", enabled = true)
	public void compareTest1(String i, String j, String k, ITestContext context) throws Exception {

		test.assignAuthor("SDET1").assignCategory("FUNCTIONAL").assignDevice("XMLComparing");

		test.log(Status.INFO, "TestCase Name: " + k);
		//System.out.println(context.getCurrentXmlTest().getName());
	
		filterAttributeandComparefiles(System.getProperty("user.dir") + "\\src\\test\\resources\\ASIS_Output_XML\\" + i,
				System.getProperty("user.dir") + "\\src\\test\\resources\\TOBE_Output_XML\\" + j);
		// System.out.println(NoOfDifferences);
		test.log(Status.INFO, "Total No of actual differences after filtering: " + NoOfDifferences);

		Assert.assertFalse(NoOfDifferences > 0);

	}

	@Test(description = "2_Test_using_CSV", dataProvider = "compare_test2", enabled = false)
	public void Comparetest2(String i, String j, String k) throws Exception {

		test.assignAuthor("SDET2").assignCategory("REGRESSION").assignDevice("XMLComparing");

		test.log(Status.INFO, "TestCase Name: " + k);
      
		filterAttributeandComparefiles(System.getProperty("user.dir") + "\\src\\test\\resources\\ASIS_Output_XML\\" + i,
				System.getProperty("user.dir") + "\\src\\test\\resources\\TOBE_Output_XML\\" + j);

		test.log(Status.INFO, "Total No of actual differences after filtering: " + NoOfDifferences);

		Assert.assertFalse(NoOfDifferences > 0);

	}
	
	@DataProvider(name = "compare_test1")
	public Object[][] getdata() throws IOException, CsvException {

		return ReadXMLFilePathFromCSV.ReadPaths("testDataworktype2");
	}
   
	@DataProvider(name = "compare_test2")
	public Object[][] getdata2() throws IOException, CsvException {

		return ReadXMLFilePathFromCSV.ReadPaths("testDataworktype4");
	}
	
}



