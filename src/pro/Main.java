package pro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Sheet;

public class Main {
    public static ArrayList<String[]> excuteData(ArrayList<ArrayList<String>> arrayList){
        //arrayList.remove(0);
        ArrayList<String[]> labelList=new ArrayList<String[]>();
        for(int i=0;i<arrayList.size();i++ ) {
        	ArrayList<String> row=arrayList.get(i);
        	if(row.size()==1||row.size()==0) {
        		arrayList.remove(i);
        		continue;
        	}
        	//combine the port
        	String temp1=row.get(0)+"-"+row.get(1);
        	row.remove(1);
        	row.set(0, temp1);
        	if(row.get(row.size()-1).equals("unknown")) {//== is not useful
        		row.remove(row.size()-1);
        	}else {
            	String temp2=row.get(row.size()-2)+"-"+row.get(row.size()-1);
            	row.remove(row.size()-1);
            	row.set(row.size()-1, temp2);
        	}
        	//form label
        	for(int j=0;j<row.size();j++) {
        		String[] s=new String[2];
        		if(j==row.size()-1) {
        			s[0]="F:"+row.get(j);
        			s[1]="T:"+row.get(j-1);
        		}else {
        			s[0]="F:"+row.get(j);
        			s[1]="T:"+row.get(j+1);
        		}
        		labelList.add(s);
        	}
            /*int size=row.size()-1;
            String temp=row.get(size-2)+"-"+row.get(size);
            row.remove(size);
            row.remove(size-1);
            row.remove(size-2);
            row.add(temp);
            row.remove(1);*/
        }
        return labelList;
    }
    public static void showData(ArrayList<String[]> list) {
        Iterator<String[]> iterator=list.iterator();
        while(iterator.hasNext()){//?
        	String[] temp=iterator.next();
            System.out.print(temp[0]);
            System.out.print("\t");
            System.out.println(temp[1]);
        }
    }
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Sheet sheet=Excel.getSheet("/home/jp/Documents/JianguoCloud-link/thework/work-net/端口/端口表-test-format-add-dev.xlsx","ZXJG1-2");
        ArrayList<ArrayList<String>> arrayList = Excel.getData(sheet);
        //Utils.showAArrayList(arrayList);
        ArrayList<String[]> data=excuteData(arrayList);
        showData(data);
        //
        try {
			Excel.setData(data,"./resource/newLabel2.xlsx","sheet1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
