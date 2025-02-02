package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name="logindata")
	public String[][] getData() throws IOException{		
		String path=".\\testData\\OpenCart_TestData.xlsx";
		ExcelUtility xlUtil=new ExcelUtility(path);
		int totalrows=xlUtil.getrowcount("Sheet1");
		int totalcolumns=xlUtil.getcellcount("Sheet1",1);
		
		String logindata[][] = new String[totalrows][totalcolumns];
		
		for(int i=1;i<=totalrows;i++) {
			for (int j=0;j<totalcolumns;j++) {
				logindata[i-1][j]=xlUtil.getcelldata("Sheet1", i, j);
			}
		}
		return logindata;
	}
//dataprovider2

//dataprovider3
}
