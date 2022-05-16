package extentlisteners;

import java.io.IOException;
import java.util.Date;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.internal.annotations.TestAnnotation;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ExtentListeners implements ITestListener, ISuiteListener {

	static Date d = new Date();
	static String fileName = "Extent_Report_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";

	private static ExtentReports extent = ExtentManager.CreateReportFile(".\\reports\\" + fileName);

	public static ExtentTest test;
@Override
	public void onTestStart(ITestResult result) {
		test = extent
				.createTest(result.getTestClass().getName() + "     @TestCase : " + result.getMethod().getMethodName()+"_"+result.getMethod().getDescription());

	}
	@Override
	public void onTestSuccess(ITestResult result) {
		
		
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		test.pass(m);

	}
	@Override
	public void onTestFailure(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " FAILED" + "</b>";

		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		test.log(Status.FAIL, m);

	}
	@Override
	public void onTestSkipped(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		test.skip(m);
		

	}
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		//testfail
	}
	@Override
	public void onStart(ITestContext context) {
//onstart empty
	}
	@Override
	public void onFinish(ITestContext context) {

		if (extent != null) {

			extent.flush();
		}

	}
	@Override
	public void onStart(ISuite suite) {
		//Onstart empty
	}
	@Override
	public void onFinish(ISuite suite) {
		//onfinish empty
	}

}
