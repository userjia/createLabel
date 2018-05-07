package pro;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Excel {

	public static Sheet getSheet(String filePath,String sheetName){
        File file=new File(filePath);
        Workbook workbook = null;
		try {
			workbook = WorkbookFactory.create(file);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Sheet sheet=workbook.getSheet(sheetName);
		return sheet;
	}
    public static ArrayList<ArrayList<String>> getData(Sheet sheet) {
        ArrayList<ArrayList<String>> rowArrayList=new ArrayList<>();
        for (Row row:sheet){
        	if(row.getCell(0) != null) {//ensure not to read null value row
	            ArrayList<String> cellArrayList=new ArrayList<>();
	            for(Cell cell:row){
	                if (cell!=null){
	                	switch (cell.getCellTypeEnum()) {
						case BLANK:
							break;
						case BOOLEAN:
							break;
						case ERROR:
							break;
						case FORMULA:
							break;
						case NUMERIC:
							double cellValue=cell.getNumericCellValue();
							if(cellValue!=0){
		                    	cellArrayList.add(String.valueOf(cellValue));
							}
							break;
						case STRING:
							String cellValue2=cell.getStringCellValue();
							if(cellValue2!=""){
		                    	cellArrayList.add(String.valueOf(cellValue2));
							}
							break;
						case _NONE:
							break;
						default:
							break;
	                	}
	                }
	            }
	            rowArrayList.add(cellArrayList);
	        }else {
	        	break;
	        }
        }
        return rowArrayList;
    }
    public static void setData(ArrayList<String[]> arrayList, String fileName, String sheetName) throws IOException {
    	XSSFWorkbook wb = new XSSFWorkbook();
    	XSSFSheet sheet=wb.createSheet(sheetName);
    	for(int i=0;i<arrayList.size();i++) {
    		String[] valueRow=arrayList.get(i);
    		XSSFRow row=sheet.createRow(i);
    		XSSFCell cell1= row.createCell(0);
    		XSSFCell cell2= row.createCell(1);
    		cell1.setCellValue(valueRow[0]);
    		cell2.setCellValue(valueRow[1]);
    	}
    	FileOutputStream fos=new FileOutputStream(new File(fileName));
		wb.write(fos);
    	wb.close();
		fos.close();
    }
}
