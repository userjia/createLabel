package pro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Sheet;

public class Main {
    public static void main(String[] args) {
    	Xlsx excelFile=new Xlsx();
    	excelFile.setCurrentSheet("/home/jp/Documents/JianguoCloud-link/thework/work-net/端口/端口表-test-format-add-dev.xlsx","ZXJG1-2");
        
        LineNode.lineNodeTypes=new ArrayList<String>();
        LineNode.lineNodeTypes.add("接线点");
        Device.deviceTypes=new ArrayList<String>();
        Device.deviceTypes.add("设备");
        Node.nodeTypes=(ArrayList<String>) Device.deviceTypes.clone();
        Node.nodeTypes.addAll(LineNode.lineNodeTypes);
        Device.portTypes=new ArrayList<String>();
        Device.portTypes.add("端口");

        Sort.getNodeListFromExcel(excelFile.getCuSheet());
        Sort.showNodeMap();
    }
}
