package com.tbd.test_scripts;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelSave {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		// TODO Auto-generated method stub

		
		
		FileInputStream fis = new FileInputStream("F:/OCM/OCMWORKSPACE/TBD/pdfverify.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		wb.getSheet("Sheet1").getRow(2).createCell(5).setCellValue("oooooSuccessfully Written By WriteDataIntoExcelSheet class ");
		
		//OPEN THE FILE IN WRITE MODE
		FileOutputStream fos = new FileOutputStream("F:/OCM/OCMWORKSPACE/TBD/pdfverify.xlsx"); 
		wb.write(fos);   // INSIDE Workbook INTERFACE ONE METHOD IS THERE--> work() 
		System.out.println("Written Successfully");
		
	}

}
