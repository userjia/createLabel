package pro;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Xlsx {
	private String name;
	private String path;
	private Sheet cuSheet;
	public void setMainSheet(String filePath,String sheetName){
        File file=new File(filePath);
        Workbook workbook = null;
		try {
			workbook = WorkbookFactory.create(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Sheet sheet=workbook.getSheet(sheetName);
		this.cuSheet = sheet;
	}
}
