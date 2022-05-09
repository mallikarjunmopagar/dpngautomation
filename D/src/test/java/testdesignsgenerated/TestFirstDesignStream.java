package testdesignsgenerated;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import baseCodePool.CompareXMLFiles;
import baseCodePool.ReadPathBasedonExecutionStatusfromExcel;
import extentlisteners.ExtentListeners;
import utilities.TestExtentReports;


public class TestFirstDesignStream extends ExtentListeners {
	@Test
	public static void aDoLogin() {
	
		Assert.fail();
	
	}
	@Test
	public static void cDoLogin() {
		
		Assert.fail();
	}
	@Test(dataProvider = "dp", enabled = true)
	public void ValidateThefristScenarioXMLcomparsion(String i, String j) throws Exception {

		//test=extent.createTest("ValidateThefristScenarioXMLcomparsion");
		//.log(Status.INFO,"Read source path and target path");
		//test.log(Status.INFO,"the two given files are compared");
		CompareXMLFiles.ignoreTheDesiredAttributesAndCompare(i, j);
		//test.log(Status.INFO,"the two given files are compared");
		//extent.flush();
	}
	
	@DataProvider(name = "dp")
	public Object[][] getdata() throws IOException {

		return ReadPathBasedonExecutionStatusfromExcel.ReadPaths("sheet6");
	}

	@BeforeMethod
	public void beforeMethod(ITestResult res) {
		System.out.println();
		System.out.println("The current test/method name is: " + res.getMethod().getMethodName());
		System.out.println("---------------------------------");
	}
	
}
