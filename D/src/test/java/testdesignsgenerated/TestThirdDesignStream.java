package testdesignsgenerated;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.DifferenceEvaluators;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import baseCodePool.CompareXMLFiles;
import baseCodePool.IgnoreAttributeDifferenceEvaluator;
import baseCodePool.ReadPathBasedonExecutionStatusfromExcel;
import extentlisteners.ExtentListeners;

public class TestThirdDesignStream extends ExtentListeners {
	@Test
	public static void cDoLogin() {
		test.log(Status.INFO, "i am first");
		Assert.fail();
	}

	@Test()
	public static void dDoLogin() {
		test.log(Status.INFO, "i am Second");
		Assert.fail();
	}

	@Test(dataProvider = "dp", enabled = true)
	public static void ValidateTheSecondScenarioXMLcomparsion(String i, String j) throws Exception {

		FileInputStream myControlXML = new FileInputStream(i);
		FileInputStream myTestXML = new FileInputStream(j);

		Diff myDiffSimilar = DiffBuilder.compare(myControlXML).withTest(myTestXML).ignoreComments().ignoreWhitespace()
				.withDifferenceEvaluator(DifferenceEvaluators.chain(DifferenceEvaluators.Default,
						new IgnoreAttributeDifferenceEvaluator("designId", "designNo", "effDate", "procDate",
								"requestDate", "requestId", "requester", "workOrderNo", "workRequestId",
								"workRequestNo", "requestDate")))
				.checkForSimilar().build();

		List differences = (List) myDiffSimilar.getDifferences();
		int totalDifference = differences.size();
		if (totalDifference != 0) {

			for (Object i1 : differences) {
				// String RecordedWarning = (String) i1;
				// test.warning(RecordedWarning);
				System.out.println(i1);
				// test.log(Status.WARNING, RecordedWarning);
			

			}

			System.out.println();

			test.log(Status.FAIL, "The given XMl files are not identical");

		} else {
			// System.out.println("The test is passed: ");
			// System.out.println("-------------------- ");
			// System.out.println("The given XMl files are identical after Compared");
			// System.out.println();
			test.log(Status.PASS,"The given XMl files are identical after Compared");
		}

		/*
		 * Iterator<Difference> differ = myDiffSimilar.getDifferences().iterator();
		 * 
		 * int size = 0; while (differ.hasNext()) {
		 * System.out.println(differ.next().toString()); size++; }
		 * System.out.println(size);
		 * 
		 * 
		 * 
		 * 
		 * /* System.out.println(differences.size()); for(Object differ: differences) {
		 * System.out.println(differ); }
		 */
		myControlXML.close();
		myTestXML.close();
	}

	@DataProvider(name = "dp")
	public Object[][] getdata() throws IOException {

		return ReadPathBasedonExecutionStatusfromExcel.ReadPaths("sheet6");
	}

}
