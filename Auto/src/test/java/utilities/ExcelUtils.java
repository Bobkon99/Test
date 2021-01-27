package utilities;
/////
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class ExcelUtils {
	
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	
	public ExcelUtils(String excelpath, String sheetName) {
		try {
		workbook = new XSSFWorkbook(excelpath);
		sheet = workbook.getSheet(sheetName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getCellData(int rowRum, int colNum) {
				
//			String excelpath = "./Data/TestData.xlsx";
//			workbook = new XSSFWorkbook(excelpath);
//			sheet = workbook.getSheet("sheet1");
			
			DataFormatter formatter = new DataFormatter();
			//Object value = formatter.formatCellValue(sheet.getRow(1).getCell(0));
			Object value = formatter.formatCellValue(sheet.getRow(rowRum).getCell(colNum));
			System.out.println(value);
			//double value = sheet.getRow(1).getCell(0).getNumericCellValue();
		
	}

	public static void getRowCount() {
		//try {
			
//			String projDir = System.getProperty("user.dir");
//			System.out.println(projDir);
//			String excelpath = "./Data/TestData.xlsx";
//			XSSFWorkbook workbook = new XSSFWorkbook(excelpath);
//			XSSFSheet sheet = workbook.getSheet("sheet1");
			int rowcnt = sheet.getPhysicalNumberOfRows();
			System.out.println("No of rows : " +rowcnt);
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//			System.out.println(e.getCause());
//		}
	}
}
