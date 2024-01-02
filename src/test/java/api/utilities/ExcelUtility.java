package api.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;
    
    public ExcelUtility(String path) { //created constructor so as whenever new object of class ExcelUtility is created they have to pass path
    	
    	this.path=path;
    }
    
    public int getRowCount(String sheetname) throws IOException {
    	
    	fi=new FileInputStream(path); //pass path to to file input stream and store in a variable
    	workbook= new XSSFWorkbook(fi); // pass path to open  workbook
    	sheet=workbook.getSheet(sheetname); //go to the sheet where you want the row count.
    	int rowcount= sheet.getLastRowNum();// get the total number of rows in that sheet
    	workbook.close();// close workbook
    	fi.close(); // close the open excel file
    	return rowcount;
    }
    
    public int getCellCount(String sheetname,int rownumber)throws IOException {
    	
    	fi=new FileInputStream(path); //pass path to to file input stream and store in a variable
    	workbook= new XSSFWorkbook(fi); // pass path to open the  workbook
    	sheet=workbook.getSheet(sheetname); //go to the sheet where you want the row count.
    	row=sheet.getRow(rownumber); //get row number
    	int cellcount= row.getLastCellNum(); // get the cell count of that row
    	workbook.close();
    	fi.close(); // close the excel file
    	return cellcount;
    }
    
    public String getCellData(String sheetname,int rownum,int cellnum)throws IOException{
    	
    	fi=new FileInputStream(path); //pass path to to file input stream and store in a variable
    	workbook= new XSSFWorkbook(fi); // pass path to open a workbook
    	sheet=workbook.getSheet(sheetname); //go to the sheet where you want the row count
    	row=sheet.getRow(rownum);
    	cell=row.getCell(cellnum);
    	
    	DataFormatter data= new DataFormatter(); // Use data formatter class to format cell data to string irrespective of data type present in cell
    	String finaldata;
    	
    	try {
    	finaldata= data.formatCellValue(cell);
    	}
    	catch(Exception e){
    	finaldata="";
    	}
    	return finaldata;
    }
    
    
}
