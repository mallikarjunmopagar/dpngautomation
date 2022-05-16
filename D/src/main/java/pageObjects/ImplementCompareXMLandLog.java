package pageObjects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.DifferenceEvaluators;

import com.aventstack.extentreports.Status;

import extentlisteners.ExtentListeners;

public class ImplementCompareXMLandLog extends ExtentListeners {

	public static int NoOfDifferences = 0;
	public int h=0;
	private static FileInputStream myControlXML;
	private static FileInputStream myTestXML;

	public void filterAttributeandComparefiles(String Srcpath, String Trgtpath) throws Exception {
		 
		try {
			try {
				myControlXML = new FileInputStream(Srcpath);
				test.log(Status.INFO, "The Source File is read.File name: " + Srcpath);

			} catch (FileNotFoundException e) {
				test.log(Status.FAIL, "File Not Found" + Srcpath);
				e.printStackTrace();
			}
			try {
				myTestXML = new FileInputStream(Trgtpath);
				test.log(Status.INFO, "The Target File is read.File name:" + Trgtpath);

			} catch (FileNotFoundException e) {
				test.log(Status.FAIL, "File Not Found" + Trgtpath);
				e.printStackTrace();

			}
			Diff myDiffSimilar = DiffBuilder.compare(myControlXML).withTest(myTestXML).ignoreComments()
					.ignoreWhitespace()
					.withDifferenceEvaluator(DifferenceEvaluators.chain(DifferenceEvaluators.Default,
							new ImplementIgnoreAttributeDifferenceEvaluator("designId", "designNo", "effDate",
									"procDate", "requestDate", "requestId", "requester", "workOrderNo", "workRequestId",
									"workRequestNo", "requestDate")))
					.checkForSimilar().build();

			List differences = (List) myDiffSimilar.getDifferences();

			NoOfDifferences = differences.size();

			for (Object eachdifference : differences) {
				test.log(Status.WARNING, eachdifference.toString());
				
			}

		} finally {
			myControlXML.close();
			myTestXML.close();

		}
	}

}
