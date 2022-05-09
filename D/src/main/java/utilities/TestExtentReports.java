package utilities;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestExtentReports {

	public ExtentSparkReporter htmlReporter;// creating html file and add configuration

	public ExtentReports extent;// attaching the reporter public
	public static ExtentTest test; // maintaining the test cases ,adding log status pass/fail/skip

	@BeforeTest

	public void setReport() {

		htmlReporter = new ExtentSparkReporter("./report/extent.html");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("XML test results");
		htmlReporter.config().setDocumentTitle("DPNG Comparing XML");
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().getTimeStampFormat();

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("SDET", "User");
		extent.setSystemInfo("Application", "DPNG");
		extent.setSystemInfo("Module", "XML");
	}

	@AfterTest
	public void endtest() {
		extent.flush();
	}

	@AfterMethod
	public void updateTestExecutionResults(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {

			Markup m = MarkupHelper.createLabel("test case failed: " + result.getMethod().getMethodName(),
					ExtentColor.RED);
			test.fail(m);
		}
		if (result.getStatus() == ITestResult.SKIP) {
			Markup m = MarkupHelper.createLabel("test case Skipped" + result.getMethod().getMethodName(),
					ExtentColor.YELLOW);
			test.skip(m);
		}
		if (result.getStatus() == ITestResult.SUCCESS) {
			Markup m = MarkupHelper.createLabel("test case pass" + result.getMethod().getMethodName(),
					ExtentColor.GREEN);
			test.pass(m);
		}
	}

}
