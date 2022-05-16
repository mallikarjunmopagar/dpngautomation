package baseCodePool;

import java.io.FileInputStream;
import java.util.List;

import org.testng.Assert;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.DifferenceEvaluators;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;

import extentlisteners.ExtentListeners;


public class CompareXMLFiles extends ExtentListeners{
	public static void ignoreTheDesiredAttributesAndCompare(String Srcpath, String Trgtpath) throws Exception {
		
		FileInputStream myControlXML = new FileInputStream(Srcpath);
		FileInputStream myTestXML = new FileInputStream(Trgtpath);

		Diff myDiffSimilar = DiffBuilder.compare(myControlXML).withTest(myTestXML)
				.ignoreComments().ignoreWhitespace(). withDifferenceEvaluator(
			             DifferenceEvaluators.chain(DifferenceEvaluators.Default,new IgnoreAttributeDifferenceEvaluator("designId", "designNo", "effDate",
						"procDate", "requestDate", "requestId", "requester", "workOrderNo", "workRequestId",
						"workRequestNo", "requestDate")))
				.checkForSimilar().build();

		List differences = (List) myDiffSimilar.getDifferences();
		int totalDifference = differences.size();
		if (totalDifference != 0) {

			System.out.println("Test is failed:\n" + "Srcfilepath: " + Srcpath + "\ntargtFilepath: " + Trgtpath);

			System.out.println("==========================");
			System.out.println("Total differences : " + totalDifference);
			System.out.println("==========================");

			for (Object i : differences) {

				System.out.println(i);
				
			}

			System.out.println();

			Assert.fail("test failed");

		} else {
			System.out.println("The test is passed: ");
			System.out.println("-------------------- ");
			System.out.println("The given XMl files are identical after Compared");
			System.out.println();
		}


		/*
		 * Iterator<Difference> differ = myDiffSimilar.getDifferences().iterator();
		 * 
		 * int size = 0; while (differ.hasNext()) {
		 * System.out.println(differ.next().toString()); size++; }
		 * System.out.println(size);
		

		
	
		/*
		 * System.out.println(differences.size()); for(Object differ: differences) {
		 * System.out.println(differ); }
		 */
		myControlXML.close();
		myTestXML.close();
	}

}


