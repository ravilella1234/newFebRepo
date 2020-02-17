package com.excels;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadDataByColoumName 
{

	public static void main(String[] args) throws Exception 
	{
		FileInputStream fis=new FileInputStream("C:\\Users\\DELL\\Desktop\\testdata.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("login");
		XSSFRow row = sheet.getRow(0);
		XSSFCell cell;
		
		int colNum=-1;
		for(int i=0;i<row.getLastCellNum();i++)
		{
			if(row.getCell(i).getStringCellValue().trim().equals("Password"))
				colNum=i;
		}
		
		row=sheet.getRow(3);
		cell=row.getCell(colNum);
		String val = cell.getStringCellValue();
		System.out.println(val);
		wb.close();
		fis.close();
	}

}
