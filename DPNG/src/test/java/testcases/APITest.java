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

public class APITest extends CompareXMLFiles {
	@Test(description = "Test_using_CSV", dataProvider = "compare_test1", enabled = true)
	public void compareTest1(String i, String j, String k, ITestContext context) throws Exception {

		test.assignAuthor("SDETAPI").assignCategory("FUNCTIONAL").assignDevice("XMLComparing+API");
		test.log(Status.INFO, "TestCase Name: " + k);

		// Reading File paths from The Data Properties file
		String UserDirectoryPath = System.getProperty("user.dir");
		String ASIS_FilePath = FetchValuesFromDataPropertiesFiles.ReadValueOf("ASIS_XMLFilePath");
		String TOBE_FilePath = FetchValuesFromDataPropertiesFiles.ReadValueOf("TOBE_TOBEFilePath");

		// System.out.println(context.getCurrentXmlTest().getName());
		// System.out.println(APIRequest.ApiRequest1(j,k));

		filterAttributeandComparefiles(UserDirectoryPath + "\\src\\test\\resources\\ASIS_Output_XML\\" + i,
				APIRequest.ApiRequest1(UserDirectoryPath + "\\src\\test\\resources\\API_Request\\" + j,
						k));
		// System.out.println(NoOfDifferences);

		// wait(1);
		test.log(Status.INFO, "Total No of actual differences after filtering: " + NoOfDifferences);

		Assert.assertFalse(NoOfDifferences > 0);

	}

	@DataProvider(name = "compare_test1")
	public Object[][] getdata() throws IOException, CsvException {

		return ReadXMLFilePathFromCSV.ReadPaths("apidataworktype");
	}

}
