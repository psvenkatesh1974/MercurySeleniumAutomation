package framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public String[][] getData(String filename,String sheetname) throws IOException{			
		File datafile = new File(filename);
		FileInputStream fis = new FileInputStream(datafile);
		//FileInputStream fis = new FileInputStream(filename); //Overriden method that works with String filename rather than the File object
		String[][] data = new String[0][0];
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(sheetname);
		String value;
		
		try{			
			int rows = sheet.getLastRowNum();
			int cols = sheet.getRow(0).getLastCellNum(); 
			if(rows > 0){
				data = new String[rows][cols];
			}
			else{
				data=new String[1][2];
				data[0][0] = null;
				data[0][1]=null;
			}
			
			for (int i = 0; i <rows; i++) { 
				//Row row = sheet.getRow(i);
				for (int j = 0; j < cols; j++) {
					value = new DataFormatter().formatCellValue(sheet.getRow(i+1).getCell(j));				
					data[i][j] = value;
				}
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
			
		finally{
			workbook.close();
			fis.close();
		}
		return data;
	}
	
	
	
	public void writeData(String filename,String sheetName, int rowNo, String columnName, String value){
		
		FileInputStream fis=null;
		FileOutputStream fos=null;
		XSSFWorkbook workbook=null;
		
		try {
			File file = new File(filename);
			fis = new FileInputStream(file);
			workbook = new XSSFWorkbook(fis);
			fis.close();	
			XSSFSheet sheet = workbook.getSheet(sheetName);			
			for(int i = 0; i<sheet.getRow(0).getLastCellNum();i++){
				if(sheet.getRow(0).getCell(i).toString().equalsIgnoreCase(columnName)){
					sheet.getRow(rowNo).createCell(i).setCellValue(value);					
				}
			}
			 fos = new FileOutputStream(filename);
			workbook.write(fos);
			//workbook.close();
			fos.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
}
