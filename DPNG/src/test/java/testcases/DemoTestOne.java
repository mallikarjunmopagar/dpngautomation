package testcases;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.aventstack.extentreports.Status;
import com.opencsv.exceptions.CsvException;

import baseCode.CompareXMLFiles;
import utilities.FetchValuesFromDataPropertiesFiles;
import utilities.ReadTestDataFromXMLFile;
import utilities.ReadXMLFilePathFromCSV;

public class DemoTestOne extends CompareXMLFiles {

	//Previously Test3
	
	@Test(description = "Comparison of UG case", dataProvider = "dp", enabled = true)
	public void testcase3(String AsIsFileName, String ToBeFilename, String k) throws Exception {

		//Categorize the Tests in Extent reports
		test.assignAuthor("Team2").assignCategory("REGRESSION").assignDevice("XML");
		test.log(Status.INFO, "TestCase Name: " + k);

		// Reading File paths from The Data Properties file
		String UserDirectoryPath = System.getProperty("user.dir");
		String ASIS_FilePath = FetchValuesFromDataPropertiesFiles.ReadValueOf("ASIS_XMLFilePath");
		String TOBE_FilePath = FetchValuesFromDataPropertiesFiles.ReadValueOf("TOBE_XMLFilePath");

		//Compare XML files and validate Outcome
		filterAttributeandComparefiles(UserDirectoryPath + ASIS_FilePath + AsIsFileName + ".xml",
				UserDirectoryPath + TOBE_FilePath + ToBeFilename + ".xml"); 
		
		// System.out.println(NoOfDifferences);
		test.log(Status.INFO, "Total No of actual differences after filtering: " + NoOfDifferences);

		Assert.assertFalse(NoOfDifferences > 0);

	}

	@DataProvider(name = "dp")
	public Object[][] getdata() throws IOException, CsvException, ParserConfigurationException, SAXException {

		return ReadXMLFilePathFromCSV.ReadPaths("TestDataForDemoTestOne");
	}

}
