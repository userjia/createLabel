package pro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class Sort {
	public static Map<String,Device> devMap;
	public static ArrayList<String[]> showed=new ArrayList<String[]>(); 
	public static ArrayList<Device> devList;//Devices.
	
	public void getNodeListFromExcel(Sheet sheet) {
		Row tittle=sheet.getRow(0);
		sheet.removeRow(sheet.getRow(0));
		for(Row row:sheet) {
			for(Cell cell:row) {
				switch (cell.getCellTypeEnum()) {
				case FORMULA:
					cell.getRichStringCellValue();
					break;
				case NUMERIC:
					cell.getNumericCellValue();
					break;
				case STRING:
					cell.getStringCellValue();
					break;
				default:
					break;
				
				}
				
			}
		}
	}
	
	//update version of getEquipment
	public static Map<String, Device> getDevices(ArrayList<ArrayList<String[]>> arrayList) {
		devMap=new HashMap<String,Device>();
		for(int i=0;i<arrayList.size();i++) {
			ArrayList<String[]> row=arrayList.get(i);
			if(row.size()==0) {//There is a null row....
				continue;
			}
			int c=0;
			Device dev;
			Map<String, ArrayList<String[]>> map;
			//String port;
			for(String[] cell:row) {
				if(cell[0].equals("设备1")||cell[0].equals("设备2")) {//!For String == is not useful.
					if(!devMap.containsKey(cell[1])) {
						dev=new Device();
						map=new HashMap<String, ArrayList<String[]>>();
						dev.setDevName(cell[1]);
					}else {
						dev=devMap.get(cell[1]);
						map=dev.getPathes();
					}
					if(cell[0]=="设备2") {
						ArrayList<String[]> as=new ArrayList<String[]>();
						for(int j=row.size()-1;j>=0;j--) {
							if(row.get(j)[0]=="端口") {
								as.add(row.get(j-1));
								as.add(row.get(j));
								j--;
							}
							as.add(row.get(j));
						}
						//row.clear();//If that, some of the line will be deleted. Why?
						row=as;
					}
					map.put(row.get(c+1)[1], row);//Can change to tittle's check.
					dev.setPathes(map);
					devMap.put(dev.getDevName(),dev);
				}
				c++;
			}
		}
		return devMap;
	}
	
	public static void showDevices(Map<String, Device> data) {
		//Have to ensure the port isn't duplicated
		for(String k:data.keySet()) {
			Device d=data.get(k);
			System.out.println(d.getDevName());
			d.showPath();
		}
	}
	
	public static void showLinkedDevices(String head) {
		if(devMap.get(head)!= null) {
			ArrayList<String[]> list=devMap.get(head).showDevicePath();
			if(list!=null&&list.size()!=0) {
				for(String[] s:list) {
					showLinkedDevices(s[0]);
				}
			}
		}
	}
	
	public static ArrayList<String[]> getConnection(ArrayList<ArrayList<String>> arrayList){
        //arrayList.remove(0);
        ArrayList<String[]> labelList=new ArrayList<String[]>();
        for(int i=0;i<arrayList.size();i++ ) {
        	ArrayList<String> row=arrayList.get(i);
        	if(row.size()==1) {
        		arrayList.remove(i);
        		continue;
        	}
        	//combine the port, specific operation
        	String temp1=row.get(0)+"-"+row.get(1);
        	row.remove(1);
        	row.set(0, temp1);
        	if(row.get(row.size()-1).equals("unknown")) {//== is not useful
        		row.remove(row.size()-1);
        	}else if(row.get(row.size()-1).equals("undone")&&row.get(row.size()-2).equals("undone")){
        		row.remove(row.size()-1);
        		row.remove(row.size()-2);
        	}else {
            	String temp2=row.get(row.size()-2)+"-"+row.get(row.size()-1);
            	row.remove(row.size()-1);
            	row.set(row.size()-1, temp2);
        	}
        	//form label
        	for(int j=0;j<row.size();j++) {
        		String[] s=new String[2];
    			s[0]=row.get(j);
    			s[1]=row.get(j+1);
        		labelList.add(s);
        	}
        }
        return labelList;
    }
}
