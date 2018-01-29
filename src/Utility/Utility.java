package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.Qa.Test_Form.TestBase;

public class Utility extends TestBase
{
	public static String TESTDATA_SHEET_PATH = "C:\\workspace\\com.Qa.Test_Form\\Data\\TestForm.xlsx";

	static Workbook book;
	static Sheet sheet;

	public static Object[][] getTestData(String sheetName) throws EncryptedDocumentException, InvalidFormatException, IOException {
		FileInputStream file = null;

		file = new FileInputStream(TESTDATA_SHEET_PATH);
		book = WorkbookFactory.create(file);
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				System.out.println(data[i][k]);
			}
		}
		return data;
	}

	public static String getTestData(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}  

	public static String readExcel(int row, int column) throws IOException
	{
	
		File file =  new File(TESTDATA_SHEET_PATH);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook book = null;
		book = new XSSFWorkbook(inputStream);
		Sheet excelsheet = book.getSheet("Invalid");
		//Find number of rows in excel file
		String cellValue = excelsheet.getRow(row).getCell(column).getStringCellValue();
		System.out.println(cellValue);
		return cellValue;
	}

}
