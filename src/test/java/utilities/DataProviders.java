package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders 
{
	@DataProvider(name="LoginData")
public String [][] getData() throws IOException
{
	String path=".\\TestData\\ScanOnline_LoginData_DDT.xlsx";//taking excel file from test data
	ExcelUtility xlutil = new ExcelUtility(path);//creating Object of ExcelUtility class
	
	int totalRows=xlutil.getRowCount("Sheet1");
	int neededcols=3;
    int totalcols=xlutil.getCellCount("Sheet1",1);//1 is row number here 
	
	String logindata[][]=new String[totalRows][totalcols];//created two dimentional array which can store data
	for (int i=1;i<=totalRows;i++)//1  //read data from excel and storing into 2 dimentional array
	{
		for(int j=0;j<neededcols;j++)
		{
			logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);//1, 0
			
		}
		
	}
	return logindata;//Returning 2 dimentional array
	
	
}
}
