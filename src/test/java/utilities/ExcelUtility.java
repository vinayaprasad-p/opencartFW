package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	//to read the excel file
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook wo;
	public XSSFSheet ws;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;
	
	//constructor
	public ExcelUtility(String path) {   //path is location if the excel file
		this.path = path;
	}
	
	public int getRowCount(String sheetname) throws IOException {
		fi = new FileInputStream(path);
		wo = new XSSFWorkbook(fi);
		ws = wo.getSheet(sheetname);
		int rowcount = ws.getLastRowNum();
		wo.close();
		fi.close();
		return rowcount;		
	}
	
	public int getCellCount(String sheetname, int rownum) throws IOException {
		fi = new FileInputStream(path);
		wo = new XSSFWorkbook(fi);
		ws = wo.getSheet(sheetname);
		row = ws.getRow(rownum);
		int cellcount = row.getLastCellNum();
		wo.close();
		fi.close();
		return cellcount;
	}
	
	public String getCellData(String sheetname, int rownum, int cellnum) throws IOException {
		fi = new FileInputStream(path);
		wo = new XSSFWorkbook(fi);
		ws = wo.getSheet(sheetname);
		row = ws.getRow(rownum);
		cell = row.getCell(cellnum);
		
		DataFormatter formatter = new DataFormatter(); //Apache POI is used to read cell values as a formatted String exactly the way they appear in Excel.
		//Converts any Excel cell value into a String 
		String data;
		try {
			data = formatter.formatCellValue(cell);
			//Reads the cell
			//Converts it into a formatted String
			//Stores result in data
		}
		catch(Exception e) {
			data="";
		}
		wo.close();
		fi.close();
		return data;
		
	}
	
	public void setCellData(String sheetname, int rownum, int cellnum, String data) throws IOException {
		File xlfile = new File(path); //Creates a File object pointing to your Excel file (path)
		if (!xlfile.exists()) {    //if file not exist then create new file
			wo = new XSSFWorkbook();  //A new Excel workbook (XSSFWorkbook) is created
			fo = new FileOutputStream(path);  //A new file is created using FileOutputStream
			wo.write(fo); //The empty workbook is written to disk
		} 
		
		fi = new FileInputStream(path);  //Opens the file for reading (FileInputStream)
		wo = new XSSFWorkbook(fi); //Loads it into an Excel workbook object (XSSFWorkbook)
		
		//if sheet not exist then create new sheet
		if(wo.getSheetIndex(sheetname)==-1) //Checks if the sheet exists: getSheetIndex() returns -1 if not found
			wo.createSheet(sheetname); //If it doesn’t exist → create a new sheet
		ws=wo.getSheet(sheetname); //Then get the sheet reference
		
		if(ws.getRow(rownum)==null) //if row not exist then create new row
			ws.createRow(rownum);  //If not → create a new row
		row=ws.getRow(rownum); //Then retrieve the row
		
		cell=row.createCell(cellnum);  //Creates a cell at given column index
		cell.setCellValue(data);  //Sets the value (data) into that cell
		
		fo=new FileOutputStream(path); //Opens file for writing
		wo.write(fo);  //Writes updated workbook back to disk
		wo.close();
		fi.close();
		fo.close();
		
	}
	
	public void fillGreenColor(String sheetname, int rownum, int cellnum) throws IOException {
		fi = new FileInputStream(path);
		wo = new XSSFWorkbook(fi);
		ws = wo.getSheet(sheetname);
		row = ws.getRow(rownum);
		cell = row.getCell(cellnum);
		
		style = wo.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		fo = new FileOutputStream(sheetname);
		wo.write(fo);
		wo.close();
		fi.close();
		fo.close();
	}
	
	public void fillRedColor(String sheetname, int rownum, int cellnum) throws IOException {
		fi = new FileInputStream(path);
		wo = new XSSFWorkbook(fi);
		ws = wo.getSheet(sheetname);
		row = ws.getRow(rownum);
		cell = row.getCell(cellnum);
		
		style = wo.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		fo = new FileOutputStream(sheetname);
		wo.write(fo);
		wo.close();
		fi.close();
		fo.close();
	}

}
