package pro;

import java.util.Map;

public class Node {
	private String nodeName;
	private String nodeProperty;
	private Map<Link,Node> linkMap;
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
}
