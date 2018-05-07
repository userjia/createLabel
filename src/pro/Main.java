package pro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Sheet;

public class Main {
    public static void main(String[] args) {
        Sheet sheet=Excel.getSheet("/home/jp/Documents/JianguoCloud-link/thework/work-net/端口/端口表-test-format-add-dev.xlsx","ZXJG1-2");
        ArrayList<ArrayList<String>> arrayList = Excel.getData(sheet);
        Sort.showEquipment(Sort.getEquipment(arrayList));
    }
}
