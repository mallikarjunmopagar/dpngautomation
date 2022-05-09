package pageObjects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.poi.ss.formula.functions.Count;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalSheetName;

import com.aventstack.extentreports.Status;

import extentlisteners.ExtentListeners;

public class ImplementReadPathBasedonExecutionStatusfromExcel extends ExtentListeners {
	public static XSSFWorkbook workbook;
	public static FileInputStream fis;

	public static Object[][] ReadPaths(String WorktypeSheetname) throws IOException {
		try {

			final Properties prop;

			Object[][] data = null;
			prop = new Properties();
			fis = new FileInputStream(
					"C:\\Users\\Mallikarjun.Mopagar\\eclipse-workspace\\D\\D\\src\\main\\java\\resources\\data.properties");
			prop.load(fis);
			workbook = new XSSFWorkbook(prop.getProperty("excel"));
			XSSFSheet sheetname = workbook.getSheet(WorktypeSheetname);

			if (sheetname.getSheetName().equalsIgnoreCase(WorktypeSheetname)) {

				int rowcount = sheetname.getLastRowNum();

				int executionstatusindex = 0;
				int countNoOfYesScenarios = 0;
				while (executionstatusindex < rowcount) {
					String status1 = sheetname.getRow(executionstatusindex + 1).getCell(0).getStringCellValue();

					if (status1.equals("Y")) {
						countNoOfYesScenarios++;
					}
					executionstatusindex++;

				}
				// create a object array
				data = new Object[countNoOfYesScenarios][2];

				int i = 0, j = 0, counter = 0;
				// runs the loop for row count we captured abosve in sheet given
				while (i < rowcount) {
					String status = sheetname.getRow(i + 1).getCell(0).getStringCellValue();

					// if the execution status in sheet is yes executes if block
					if (status.equals("Y")) {

						{
							data[j][0] = sheetname.getRow(i + 1).getCell(1).getStringCellValue();

						}

						{
							data[j][1] = sheetname.getRow(i + 1).getCell(2).getStringCellValue();

						}
						j++;// increment the list index to store the value fetched from excel
						counter++;// no of times execution status is Yes/Y/true

						// return data;

					}

					else {
						
						// test.log(Status.SKIP, sheetname.getRow(i +
						// 1).getCell(3).getStringCellValue());
						System.out.println("The execution status of Test case:"
								+ sheetname.getRow(i + 1).getCell(3).getStringCellValue() + " is false");
					}
					i++;

				}

			}
			return data;
		}
		finally {
			workbook.close();

		}

	}

}
