package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviderExcel {
	
	@DataProvider(name="Data")
	public String[][] getAllExcelData() throws IOException{
		
		String path=System.getProperty("user.dir")+"//testData//ExcelData.xlsx"; // create path
		ExcelUtility excelObject= new ExcelUtility(path); //create object of excel utility and pass path
		
		int rowcount= excelObject.getRowCount("Sheet1"); //get row count
		int cellcount= excelObject.getCellCount("Sheet1", rowcount); //get cell count
		
		String [][]apidata= new String[rowcount][cellcount]; //create 2d array to store data based on row and column count
		
		for(int i=1;i<=rowcount;i++) {  // keep two loop to iterate over 2d array
			for(int j=0;j<cellcount;j++) {
				apidata[i-1][j]=excelObject.getCellData("Sheet1", i, j);
				
		  }
		}
		return apidata; // return 2D  String array 
	}
	
	@DataProvider(name="usernames")
	public String[] getUserNames() throws IOException {
		String path=System.getProperty("user.dir")+"//testData//ExcelData.xlsx"; // create path
		ExcelUtility excelObject= new ExcelUtility(path); //create object of excel utility and pass path
		
		int rowcount= excelObject.getRowCount("Sheet1"); //get row count
		String []usernamedata= new String[rowcount];
		for(int i=1;i<=rowcount;i++){
			
			usernamedata[i-1]=excelObject.getCellData("Sheet1", rowcount, i);
		}
		return usernamedata;
	}
}
