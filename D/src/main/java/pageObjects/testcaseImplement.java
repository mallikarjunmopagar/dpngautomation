package pageObjects;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import baseCodePool.ReadPathBasedonExecutionStatusfromExcel;
import pageObjects.ImplementIgnoreAttributeDifferenceEvaluator;

public class testcaseImplement extends ImplementCompareXMLandLog{
	@Test
	public  void aDoLogin() {
		test.assignAuthor("K");
		test.log(Status.INFO, "i am first");
		Assert.fail();

	}

	@Test
	public  void cDoLogin() {
		test.assignAuthor("S");
		test.log(Status.INFO, "i am Second");
		Assert.fail();
	}

	@Test(dataProvider = "dp", enabled = true)
	public void testcaseimplement1(String i, String j) throws Exception {
		test.assignAuthor("Part3");
		ImplementCompareXMLandLog f=new ImplementCompareXMLandLog();
		
		filterAttributeandComparefiles(i, j);
         //System.out.println(NoOfDifferences);
		test.log(Status.INFO, "Total No of actual differences after filtering: "
				+NoOfDifferences);
		//test.log(Status.INFO, "test counter: ");
	

		Assert.assertFalse(NoOfDifferences > 0);

	}

	@DataProvider(name = "dp")
	public Object[][] getdata() throws IOException {

		return ImplementReadPathBasedonExecutionStatusfromExcel.ReadPaths("sheet5");
	}

}
