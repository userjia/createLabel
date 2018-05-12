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
	public void setCurrentSheet(String filePath,String sheetName){
        File file=new File(filePath);
        Workbook workbook = null;
		try {
			workbook = WorkbookFactory.create(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Sheet sheet=workbook.getSheet(sheetName);
		this.setCuSheet(sheet);
	}
	
	public static String getCellStringValue(Cell cell) {
		if(cell==null) {
			return null;
		}
		String value=null;
		switch (cell.getCellTypeEnum()) {
		case FORMULA:
			value=String.valueOf(cell.getRichStringCellValue());
			break;
		case NUMERIC:
			value=String.valueOf(cell.getNumericCellValue());
			break;
		case STRING:
			value=String.valueOf(cell.getStringCellValue());
			break;
		default:
			break;
		}
		return value;
	}

	public Sheet getCuSheet() {
		return cuSheet;
	}

	public void setCuSheet(Sheet cuSheet) {
		this.cuSheet = cuSheet;
	}
}
