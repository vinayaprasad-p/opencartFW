package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "loginData")
	public String[][] getData() throws IOException{
		String path=".\\testData\\OpenCart_Login_Data.xlsx"; //taking xl file from testdata
		//. represent current location of the data
		
		ExcelUtility xls = new ExcelUtility(path); //create an path for excel utility
		
		int totalRow = xls.getRowCount("sheet1");    //getting the data from the excelutility class
		int totalcell = xls.getCellCount("sheet1", 1); //String sheetname, int rownum
		
		String loginData[][] = new String[totalRow][totalcell];//created 2D array which can store
		
		for (int i=1; i<=totalRow; i++) {
			
			for (int j=0; j<totalcell; j++) {
				loginData[i-1][j] = xls.getCellData("sheet1", i, j);
			}
		}
		return loginData;
	}
}
