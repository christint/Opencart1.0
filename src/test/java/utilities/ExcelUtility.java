package utilities;

import java.io.File;
import java.io.FileInputStream;
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
	
	public  FileInputStream fi;
	public  FileOutputStream fo;
	public  XSSFWorkbook wb;
	public  XSSFSheet ws;
	public  XSSFRow row;
	public  XSSFCell cell;
	public  CellStyle style;
	String path;
	
	public ExcelUtility(String path) {
		this.path=path;
	}
	
	public  int getrowcount(String xlsheetname) throws IOException {
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheetname);
		int rowcount=ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;		
	}
	public  int getcellcount(String xlsheetname,int rownum) throws IOException{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheetname);
		row=ws.getRow(rownum);		
		int cellcount=row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;
	}
	public  String getcelldata(String xlsheetname,int rownum,int colnum) throws IOException{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheetname);
		row=ws.getRow(rownum);	
		cell=row.getCell(colnum);
		String data;
		try {
			DataFormatter formatter=new DataFormatter();
			data=formatter.formatCellValue(cell);
		}catch (Exception e) {
			data="";
		}
		wb.close();
		fi.close();
		return data;
	}
	public  void setcelldata(String xlsheetname,int rownum,int colnum,String data) throws IOException{
		File xlfile=new File(path);
		if (!xlfile.exists()) {
			fo=new FileOutputStream(path);
			wb=new XSSFWorkbook();
			wb.write(fo);
		}
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		if(wb.getSheetIndex(xlsheetname)==-1) {
			ws=wb.createSheet(xlsheetname);
		}
		ws=wb.getSheet(xlsheetname);
		if (ws.getRow(rownum)==null) {
			row=ws.createRow(rownum);
		}
		row=ws.getRow(rownum);	
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	public  void fillgreencolor(String xlsheetname,int rownum,int colnum) throws IOException{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheetname);
		row=ws.getRow(rownum);	
		cell=row.getCell(colnum);
		
		style =wb.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		fo=new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	public  void fillredcolor(String xlsheetname,int rownum,int colnum) throws IOException{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheetname);
		row=ws.getRow(rownum);	
		cell=row.getCell(colnum);
		
		style =wb.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		fo=new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	
}
