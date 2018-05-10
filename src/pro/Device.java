package pro;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public class Device {
	private String devName;
	private Map<String,ArrayList<String>> pathes;
	
	public void showPath() {
		for(Entry<String, ArrayList<String>> entry:pathes.entrySet()) {
			System.out.print(entry.getKey()+":\t");
			for(String a:entry.getValue()) {
				System.out.print(a+"\t");
			}
			System.out.println("");
		}
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public Map<String,ArrayList<String>> getPathes() {
		return pathes;
	}

	public void setPathes(Map<String,ArrayList<String>> pathes) {
		this.pathes = pathes;
	}
}
