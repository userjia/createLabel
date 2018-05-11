package pro;

import java.util.ArrayList;

public class Link {
	 private String linkName;
	 private String linkProperty;
	 private ArrayList<String> nodeList;
	 private Node node1;
	 private Node node2;
	 private String port1;
	 private String port2;
	public Link(Node node1, Node node2) {
		this.node1=node1;
		this.node2=node2;
		// TODO Auto-generated constructor stub
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public String getLinkProperty() {
		return linkProperty;
	}
	public void setLinkProperty(String linkProperty) {
		this.linkProperty = linkProperty;
	}
	public ArrayList<String> getNodeList() {
		return nodeList;
	}
	public void setNodeList(ArrayList<String> nodeList) {
		this.nodeList = nodeList;
	}
	public String getPort1() {
		return port1;
	}
	public void setPort1(String port1) {
		this.port1 = port1;
	}
	public String getPort2() {
		return port2;
	}
	public void setPort2(String port2) {
		this.port2 = port2;
	}
}
