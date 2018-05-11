package pro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Device extends Node{
	private Map<String,Link> portMap;
	public static ArrayList<String> portTypes;
	public static ArrayList<String> deviceTypes;
	private String currentPort;
	private Link currentLink;
	public Link getCurrentLink() {
		return currentLink;
	}
	public void setCurrentLink(Link currentLink) {
		this.currentLink = currentLink;
	}
	public void addPort(String port,Link link) {
		if(portMap==null) {
			portMap = new HashMap<String,Link>();
		}
		portMap.put(port,link);
	}
	public String getCurrentPort() {
		return currentPort;
	}
	public void setCurrentPort(String currentPort) {
		this.currentPort = currentPort;
		if (portMap!=null&&!portMap.containsKey(currentPort)&&currentLink!=null) {
			this.addPort(currentPort, currentLink);
		}
	}
}
