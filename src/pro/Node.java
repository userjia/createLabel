package pro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Node {
	private String nodeName;
	private String nodeProperty;
	private Map<Link,Node> linkMap;
	public static ArrayList<String> nodeTypes;
	public static ArrayList<Node> showedNode;
	public void addLink(Link link,Node node) {
		if(linkMap==null) {
			linkMap=new HashMap<Link,Node>();
		}
		linkMap.put(link, node);
	}
	
	public void showSelf() {
		System.out.print(nodeName+":");
	}
	
	public void showPath(int layer) {
		if(showedNode==null) {
			showedNode=new ArrayList<Node>();
		}else if(showedNode.contains(this)) {
			return;
		}
		if(this.getClass()==Device.class) {
			showSelf();
			System.out.println();
			for(int i=0;i<layer;i++) {
				System.out.print("\t");
			}
		}else if(this.getClass()==LineNode.class) {
			showSelf();
			System.out.print("\t");
		}
		
		showedNode.add(this);
		for(Node node:linkMap.values()) {
			node.showPath(layer+1);
		}
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getNodeProperty() {
		return nodeProperty;
	}
	public void setNodeProperty(String nodeProperty) {
		this.nodeProperty = nodeProperty;
	}
	public ArrayList<String> getNodeType() {
		return nodeTypes;
	}
	public void setNodeType(ArrayList<String> nodeType) {
		this.nodeTypes = nodeType;
	}
	public Map<Link,Node> getLinkMap() {
		return linkMap;
	}
	public void setLinkMap(Map<Link,Node> linkMap) {
		this.linkMap = linkMap;
	}

	
}
