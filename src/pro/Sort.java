package pro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Sort {
	//collect the information of equipment connection 
	public static Map<String, ArrayList<ArrayList<String>>> getEquipment(ArrayList<ArrayList<String>> data) {
		Map<String, ArrayList<ArrayList<String>>> map=new HashMap<String, ArrayList<ArrayList<String>>>();
		for(int i=0;i<data.size();i++) {
			ArrayList<String> row=data.get(i);
			String e1=row.get(0);
			String e2=row.get(row.size()-2);
			String[] e={e1,e2};
			for(int k=0;k<2;k++) {
				ArrayList<ArrayList<String>> list;
				if(!map.containsKey(e[k])) {
					list=new ArrayList<ArrayList<String>>();
				}else {
					list=map.get(e[k]);
				}
				if(k==1) {
					ArrayList<String> as=new ArrayList<String>();
					for(int j=row.size()-1;j>=0;j--) {
						as.add(row.get(j));
					}
					//row.clear();//If that, some of the line will be deleted. Why?
					row=as;
				}
				list.add(row);
				map.put(e[k], list);
			}
		}
		return map;
	}
	
	//update version of getEquipment
	public static Map<String, Device> getDevices(ArrayList<ArrayList<String>> data) {
		Map<String,Device> devMap=new HashMap<String,Device>();
		for(int i=0;i<data.size();i++) {
			ArrayList<String> row=data.get(i);
			if(row.size()==0) {//There is a null row....
				continue;
			}
			String e1=row.get(0);
			String e2=row.get(row.size()-2);
			String[] e={e1,e2};
			for(int k=0;k<2;k++) {
				//ArrayList<ArrayList<String>> list;
				Device dev;
				Map<String, ArrayList<String>> map;
				if(!devMap.containsKey(e[k])) {
					dev=new Device();
					map=new HashMap<String, ArrayList<String>>();
					dev.setDevName(e[k]);
				}else {
					dev=devMap.get(e[k]);
					map=dev.getPathes();
				}
				if(k==1) {
					ArrayList<String> as=new ArrayList<String>();
					for(int j=row.size()-1;j>=0;j--) {
						as.add(row.get(j));
					}
					//row.clear();//If that, some of the line will be deleted. Why?
					
					row=as;
				}
				/*
				String port=row.get(1);
				row.remove(row.size()-1);
				row.remove(1);
				map.put(port, row);*/
				map.put(String.valueOf(map.size()+1), row);
				dev.setPathes(map);
				devMap.put(dev.getDevName(),dev);
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
	
	public static void showEquipment(Map<String, ArrayList<ArrayList<String>>> data) {
		for(Entry<String, ArrayList<ArrayList<String>>> entry:data.entrySet()) {
			for(ArrayList<String> a:entry.getValue()) {
				System.out.print(entry.getKey()+":\t");
				for(String s:a){
					System.out.print(s+"\t");
				}
				System.out.println("");
			}
			//System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
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
