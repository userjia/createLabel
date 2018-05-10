package pro;

import java.util.ArrayList;

public class Link {
	 private String linkName;
	 private String linkProperty;
	 private ArrayList<String> nodeList;
	 private Node node1;
	 private Node node2;
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
}
