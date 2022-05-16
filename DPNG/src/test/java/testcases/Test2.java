package testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.opencsv.exceptions.CsvException;

import baseCode.CompareXMLFiles;
import utilities.ReadXMLFilePathBasedonExecutionStatusfromExcel;
import utilities.ReadXMLFilePathFromCSV;

public class Test2 extends CompareXMLFiles {
	@Test(description="SS_Test_using_CSV",dataProvider = "dp", enabled = true)
	public void testcaseimplement2(String i, String j, String k) throws Exception {

		test.assignAuthor("Team2").assignCategory("REGRESSION").assignDevice("XML");

		test.log(Status.INFO, "TestCase Name: " + k);

		filterAttributeandComparefiles(System.getProperty("user.dir") + "\\src\\test\\resources\\ASIS_Output_XML\\" + i,
				System.getProperty("user.dir") + "\\src\\test\\resources\\TOBE_Output_XML\\" + j); //
		//System.out.println(NoOfDifferences);
		test.log(Status.INFO, "Total No of actual differences after filtering: " + NoOfDifferences);

		// test.log(Status.INFO, "test counter: ");

		Assert.assertFalse(NoOfDifferences > 0);

	}

	@DataProvider(name = "dp")
	public Object[][] getdata() throws IOException, CsvException {

		return ReadXMLFilePathFromCSV.ReadPaths("testDataworktype1");
	}

}
