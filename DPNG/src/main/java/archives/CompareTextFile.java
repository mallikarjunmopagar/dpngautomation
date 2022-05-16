package archives;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.testng.Assert;
import org.testng.SkipException;

import com.aventstack.extentreports.Status;

import extentlisteners.ExtentListeners;

public class CompareTextFile extends ExtentListeners{
	
	public void logcomparision(String Srcpath, String Trgtpath,String K) throws IOException {
		
		try {
			test.log(Status.INFO, "source file.: "+ Srcpath);
			test.log(Status.INFO, "source file.: "+ Trgtpath);
			BufferedReader reader1 = new BufferedReader(new FileReader(Srcpath));
		BufferedReader reader2 = new BufferedReader(new FileReader(Trgtpath));
		
		String line1 = reader1.readLine();
		String line2 = reader2.readLine();
		boolean areEqual = true;
		int lineNum = 1;
		
		while (line1 != null || line2 != null) {
			if (line1 == null || line2 == null) {
				areEqual = false;
				break;
			} else if (!line1.equalsIgnoreCase(line2)) {
				areEqual = false;
				test.log(Status.WARNING,"File1 has " + line1 + " and File2 has " + line2 + " at line " + lineNum);
			}
			line1 = reader1.readLine();
			line2 = reader2.readLine();
			lineNum++;
		}
		System.out.println(lineNum);
		if (areEqual) {
			System.out.println("Two files have same content.");
			test.log(Status.INFO, "Two files have same content.: " );
		} else {
			System.out.println("Two files have different content. They differ at line " + lineNum);
			
			Assert.fail();
			
		}

		reader1.close();

		reader2.close();
	}catch (FileNotFoundException e) {
		test.log(Status.SKIP, "File Not Found: " + e.getMessage());
		e.printStackTrace();
		throw new SkipException("File Not Found");

	} catch (NullPointerException n2) {
		test.log(Status.SKIP, "The Target filepath given is NULL: " + n2.getMessage());
		n2.printStackTrace();
		throw new SkipException("Null Pionter Exception");

	}
		}
}