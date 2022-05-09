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

public class TestSecondDesignStream extends ExtentListeners {

	@Test
	public static void aDoLogin() {
		test.log(Status.INFO, "i am first");
		Assert.fail();
	}

	@Test()
	public static void bDoLogin() {
		test.log(Status.INFO, "i am Second");
		Assert.fail();
	}

	@Test(dataProvider = "dp", enabled = true)
	public static void ValidateTheSecondScenarioXMLcomparsion(String i, String j) throws Exception {

		test.log(Status.INFO, "Read source path and target path");
		test.log(Status.INFO, "the two given files are compared");
		CompareXMLFiles.ignoreTheDesiredAttributesAndCompare(i, j);
		test.log(Status.INFO, "the two given files are compared");

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
