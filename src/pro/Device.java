package pro;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public class Device {
	private String devName;
	private Map<String,ArrayList<String[]>> pathes;
	private Map<Link,Device> linkMap;
	
	public void showPath() {
		for(Entry<String, ArrayList<String[]>> entry:pathes.entrySet()) {
			System.out.print(entry.getKey()+":\t");
			for(String[] a:entry.getValue()) {
				System.out.print(a[1]+"\t");
			}
			System.out.println("");
		}
	}

	public ArrayList<String[]> showDevicePath() {
		ArrayList<String[]> list=new ArrayList<String[]>();
		for(Entry<String, ArrayList<String[]>> entry:pathes.entrySet()) {
			String[] s= {devName,entry.getKey()};
			if(Sort.showed.contains(s)) {
				return null;
			}
			System.out.print(entry.getKey()+":\t");
			int i=0;
			String[] t=new String[2];
			for(String[] a:entry.getValue()) {
				System.out.print(a[1]+"\t");
				if(i==entry.getValue().size()-2) {
					t[0]=a[1];
				}
				if(i==entry.getValue().size()-1) {
					t[1]=a[1];
				}
				i++;
			}
			Sort.showed.add(s);
			list.add(t);
			System.out.println("");
		}
		return list;
	}
	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public Map<String, ArrayList<String[]>> getPathes() {
		return pathes;
	}

	public void setPathes(Map<String,ArrayList<String[]>> pathes) {
		this.pathes = pathes;
	}

	public Map<Link,Device> getLinkMap() {
		
		return linkMap;
	}

	public void setLinkMap(Map<Link,Device> linkMap) {
		this.linkMap = linkMap;
	}
	
	public void setLinkMap(ArrayList<String[]> arrayList) {
		this.linkMap = linkMap;
	}
	
}
