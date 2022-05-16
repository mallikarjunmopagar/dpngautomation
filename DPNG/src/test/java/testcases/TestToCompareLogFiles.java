package testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.opencsv.exceptions.CsvException;

import baseCode.CompareLogFiles;
import utilities.FetchValuesFromDataPropertiesFiles;
import utilities.ReadXMLFilePathFromCSV;

public class TestToCompareLogFiles extends CompareLogFiles {
	
	@Test(description = "Log File comparison", dataProvider = "log_compare_test1", enabled = true)
	public void logcompareTest1(String AsIsFileName, String ToBeFilename, String Comment, ITestContext context)
			throws Exception {
		
		//Reading File paths from The Data Properties file
		String UserDirectoryPath = System.getProperty("user.dir");
		String ASIS_FilePath = FetchValuesFromDataPropertiesFiles.ReadValueOf("ASIS_LogFilePath");
		String TOBE_FilePath = FetchValuesFromDataPropertiesFiles.ReadValueOf("TOBE_LogFilePath");
		
		//Categorize the Tests in Extent reports
		test.assignAuthor("SDETLOG").assignCategory("FUNCTIONAL");
		test.log(Status.INFO, "TestCase Name: " + Comment);

		//Comparing Log files and validate the outcome
		compareLogs(UserDirectoryPath + ASIS_FilePath + AsIsFileName, UserDirectoryPath + TOBE_FilePath + ToBeFilename);
		//System.out.println(NoOfdifferences);
		test.log(Status.INFO, "Total No of differences: " + NoOfdifferences);
		Assert.assertFalse(NoOfdifferences != 0);

	}

	@DataProvider(name = "log_compare_test1")
	public Object[][] getdata() throws IOException, CsvException {

		return ReadXMLFilePathFromCSV.ReadPaths("loginput");
	}

}
