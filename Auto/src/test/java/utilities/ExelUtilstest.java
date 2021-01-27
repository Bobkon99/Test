package utilities;

import utilities.ExcelUtils;

public class ExelUtilstest {
	
	public static void main(String [] args) {
		String excelpath = "./Data/TestData.xlsx";
		String sheetName = "Sheet1";
		ExcelUtils excel = new ExcelUtils(excelpath, sheetName);
		excel.getRowCount();
		excel.getCellData(1,0);
		excel.getCellData(1,1);
		
	}

}
