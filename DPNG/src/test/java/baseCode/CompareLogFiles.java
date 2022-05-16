package baseCode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.testng.Assert;
import org.testng.SkipException;

import com.aventstack.extentreports.Status;

import extentlisteners.ExtentListeners;

public class CompareLogFiles extends ExtentListeners {
	/**
	 * ***IMportant*** We are comparing two log files by reading and comparing Line
	 * to Line 1.after (trimming)deleted leading and trailing spaces 2.After
	 * (replacing)deleted spaces in both files.
	 */

	// NullPointer,FilenotFound are reported in extent reports

	public static int NoOfdifferences;

	public static void compareLogs(String ASISLogFile, String TOBELogFile) throws IOException {
		BufferedReader readerOfASISfile = null;
		BufferedReader readerOfTOBEfile = null;
		String lineInAsisFile = null;
		String lineInTobeFile = null;
		int counter = 0;

		System.out.println("---Output---");
		try {
			readerOfASISfile = new BufferedReader(new FileReader(ASISLogFile), 5000000);
			test.log(Status.INFO, "Source file is successfully Loaded: " + ASISLogFile);

		} catch (FileNotFoundException e1) {
			test.log(Status.WARNING, "The specified ASIS file not found: " + ASISLogFile);
			System.out.println("the specified ASIS file not found:" + ASISLogFile);
			// e1.printStackTrace();
			throw new SkipException("The specified ASIS file not found");

		}

		try {
			readerOfTOBEfile = new BufferedReader(new FileReader(TOBELogFile), 5000000);
			test.log(Status.INFO, "Target file is successfully Loaded: " + TOBELogFile);

		} catch (FileNotFoundException e) {
			test.log(Status.WARNING, "The specified TOBE file not found: " + TOBELogFile);
			System.out.println("The specified TOBE file not found:" + TOBELogFile);
			// e.printStackTrace();
			throw new SkipException("The specified TOBE file not found");
		}
		try {
			lineInAsisFile = readerOfASISfile.readLine().trim().replaceAll("\\s+", "");
		} catch (NullPointerException e) {
			counter--;
			System.out.println("Null point exception encountered: " + ASISLogFile);
			test.log(Status.WARNING, "First line of ASIS file is NULL in : " + ASISLogFile);
			throw new SkipException("First line of ASIS file is Null");

		}
		try {
			lineInTobeFile = readerOfTOBEfile.readLine().trim().replaceAll("\\s+", "");

		} catch (NullPointerException e) {
			counter--;
			System.out.println("Null pointer exception encountered: " + TOBELogFile);
			test.log(Status.WARNING, "First line of TOBE is NULL in : " + TOBELogFile);
			throw new SkipException("First line of TOBE is Null");

		}
		int lineNum = 1;
		try {

			while (lineInAsisFile != null || lineInTobeFile != null) {

				// if (line1 == null || line2 == null) {
				if (lineInAsisFile == null) {

					System.out.println("Reached End of file--Null data Encountered ins: " + ASISLogFile);
					test.log(Status.WARNING, "Reached End of file--Null data Encountered in: " + ASISLogFile);
					break;

				} else if (lineInTobeFile == null) {
					System.out.println("Reached End of file--Null data Encountered in: " + TOBELogFile);
					test.log(Status.WARNING, "Reached End of file--Null data Encountered in :" + TOBELogFile);
					break;

				}

				// break;
				// }

				else if (!lineInAsisFile.trim().replaceAll("\\s+", "")
						.equals(lineInTobeFile.trim().replaceAll("\\s+", ""))) {

					counter++;
					if (counter > 100) {
						Assert.fail();
					} else {
						System.out.println("ASIS File has " + lineInAsisFile + "' and TOBE File has '" + lineInTobeFile
								+ "' at line " + lineNum);
						test.log(Status.WARNING, "ASIS File has '" + lineInAsisFile + "' and TOBE File has '" + lineInTobeFile
								+ "' at line " + lineNum);
					}
				}
				lineInAsisFile = readerOfASISfile.readLine();
				lineInTobeFile = readerOfTOBEfile.readLine();

				lineNum++;
			}
			NoOfdifferences = counter;
			System.out.println("counter: " + counter);
			// System.out.println("linenum: " + lineNum);
			if (counter == 0) {
				System.out.println("Two files have same content.");
				test.log(Status.INFO, "Two files have same content.");
			} else if (counter != 0) {
				System.out.println("Two files have different content.");
				test.log(Status.INFO, "Two files have different content.");
			}

		} finally {
			readerOfASISfile.close();

			readerOfTOBEfile.close();
		}
	}
}
