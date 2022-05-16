package testcases;

import java.io.IOException;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.opencsv.exceptions.CsvException;

import baseCode.APIRequest;
import baseCode.CompareXMLFiles;
import utilities.FetchValuesFromDataPropertiesFiles;
import utilities.ReadXMLFilePathFromCSV;

public class TestToHitApiNcompareXMLs extends CompareXMLFiles {
	@Test(description = "TestToHitApiNcompareXMLs", dataProvider = "compare_test1", enabled = true)
	public void hitApiandCompareXML(String ASISFilename, String TOBEFilename, String comment, ITestContext context)
			throws Exception {
	
		// Categorize the Tests in Extent reports
		test.assignAuthor("SDET").assignCategory("FUNCTIONAL").assignDevice("API+XMLComparing");
		test.log(Status.INFO, "TestCase Name: " + comment);

		// Reading File paths from The Data Properties file
		String UserDirectoryPath = System.getProperty("user.dir");
		String ASIS_FilePath = FetchValuesFromDataPropertiesFiles.ReadValueOf("ASIS_XMLFilePath");

		// System.out.println(context.getCurrentXmlTest().getName());
		// System.out.println(APIRequest.ApiRequest1(j,k));
		
		//Hit APi,Compare XML files and validate Outcome
		filterAttributeandComparefiles(UserDirectoryPath + ASIS_FilePath + ASISFilename, APIRequest
				.ApiRequest1(UserDirectoryPath + "\\src\\test\\resources\\API_Request\\" + TOBEFilename, comment));
		// System.out.println(NoOfDifferences);

		
		test.log(Status.INFO, "Total No of actual differences after filtering: " + NoOfDifferences);

		Assert.assertFalse(NoOfDifferences > 0);

	}

	@DataProvider(name = "compare_test1")
	public Object[][] getdata() throws IOException, CsvException {

		return ReadXMLFilePathFromCSV.ReadPaths("apidataworktype");
	}

}
