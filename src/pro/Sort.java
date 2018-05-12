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
	public static Map<String,Node> nodeMap;//Devices.
	
	public static void getNodeListFromExcel(Sheet sheet) {
		nodeMap=new HashMap<String,Node>();
		Row title=sheet.getRow(0);
		for(Row row:sheet) {
			if(row==sheet.getRow(0)) {
				continue;
			}
			Node preNode=null;
			String port=null;
			for(Cell cell:row) {
				String cellValue=Xlsx.getCellStringValue(cell);
				Cell titleCell = title.getCell((cell.getColumnIndex()));
				String cellBelong=null;
				if(titleCell==null) {
					continue;
					//no title.	
				}else {
					cellBelong=Xlsx.getCellStringValue(titleCell);
				}
				if(cellValue!= null&&cellBelong!=null) {
					
					if(Device.deviceTypes.contains(cellBelong)||LineNode.lineNodeTypes.contains(cellBelong)) {//Can this be improved?
						Node node;
						if (nodeMap.containsKey(cellValue)) {
							node=nodeMap.get(cellValue);
						}else {
							if(Device.deviceTypes.contains(cellBelong)) {
								node=new Device();
							}else if(LineNode.lineNodeTypes.contains(cellBelong)){
								node=new LineNode();
							}else {
								node=new Node();
							}
							node.setNodeProperty(cellBelong);
							node.setNodeName(cellValue);
						}
						if(preNode!=null) {
							Link link=new Link(preNode,node);
							preNode.addLink(link,node);
							node.addLink(link, preNode);
							/*if(Device.nodeTypes.contains(cellBelong)) {
								node.setCurrentLink(link);
								if(preNode.getCurrentPort()!=null) {
									preNode.addPort(preNode.getCurrentPort(), link);
								}
							}*/
						}
						

						nodeMap.put(cellValue, node);
						preNode=node;
					}
					/*if(Device.portTypes.contains(cellBelong)) {
						if(preNode!=null) {
							preNode.setCurrentPort(cellValue);
						}
					}*/
				}
			}
		}
		System.out.println("");
	}
	
	public static void showNodeMap() {
		nodeMap.get("园区核心虚拟1").showPath(0);
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
