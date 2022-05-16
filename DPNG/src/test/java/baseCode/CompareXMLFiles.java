package baseCode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.testng.Assert;
import org.testng.SkipException;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.Diff;

import com.aventstack.extentreports.Status;

import extentlisteners.ExtentListeners;

public class CompareXMLFiles extends ExtentListeners {
	
	
	/*
	 * Compare the ASIS  XML files with TOBE XML files
	 *  1.ignore comments and attributes passed as parameters
	 *  
	 */
	
	public static int NoOfDifferences = 0;

	private static FileInputStream myControlXML;
	private static FileInputStream myTestXML;

	public void filterAttributeandComparefiles(String ASISxmlFile, String TOBExmlFile) throws Exception {

		try {
			myControlXML = new FileInputStream(ASISxmlFile);
			test.log(Status.INFO, "The Source File is read.File name: " + ASISxmlFile);

		} catch (FileNotFoundException e) {
			test.log(Status.SKIP, "File Not Found: " + ASISxmlFile);
			e.printStackTrace();
			throw new SkipException("Source File Not Found");

		} catch (NullPointerException n1) {
			test.log(Status.SKIP, "The Source filepath given is NULL: " + ASISxmlFile);
			n1.printStackTrace();
			// test.log(Status.INFO,"Error: "+n1.getMessage());
			throw new SkipException("Null Pointer Exception");

		}

		try {
			myTestXML = new FileInputStream(TOBExmlFile);
			test.log(Status.INFO, "The Target File is read.File name:" + TOBExmlFile);

		} catch (FileNotFoundException e) {
			test.log(Status.SKIP, "Target File Not Found: " + TOBExmlFile);
			e.printStackTrace();
			throw new SkipException("File Not Found");

		} catch (NullPointerException n2) {
			test.log(Status.SKIP, "The Target filepath given is NULL: " + TOBExmlFile);
			n2.printStackTrace();
			throw new SkipException("Null Pionter Exception");

		}
		
		try {
			// .withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byNameAndText))
			// DifferenceEvaluators.chain(DifferenceEvaluators.Default,

			Diff myDifferences = DiffBuilder.compare(myControlXML).withTest(myTestXML).ignoreComments()
					.withDifferenceEvaluator(new IgnoreAttributeDifferenceEvaluator("procDate")).build();
			
			//System.out.println(myDifferences.getDifferences().spliterator().estimateSize());
			//System.out.println(myDifferences.fullDescription());

			List differences = (List) myDifferences.getDifferences();

			NoOfDifferences = differences.size();
			//System.out.println(NoOfDifferences);
			for (Object eachdifference : differences) {
				
				test.log(Status.WARNING, eachdifference.toString());
				//System.out.println(eachdifference.toString());

				String[] DifferenceFound = eachdifference.toString().split("-");
				if (DifferenceFound[0].contains("Expected child nodelist length")) {
					test.log(Status.INFO, "Number of Differences: " + NoOfDifferences);
					Assert.fail(eachdifference.toString());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			test.log(Status.FAIL, "Unable To Compare,Followiing Error Encountered: " + e.getMessage());
			Assert.fail();
		} finally {
			myControlXML.close();
			myTestXML.close();

		}
	}

}
