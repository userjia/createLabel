package pro;
import java.util.ArrayList;
import java.util.Iterator;

public class Utils {
    public static void showArrayList(ArrayList<String> arrayList){
        Iterator<String> iterator=arrayList.iterator();
        while(iterator.hasNext()){//?
            System.out.println(iterator.next());
        }
    }
    public static void showAArrayList(ArrayList<ArrayList<String>> arrayList) {
    	Iterator<ArrayList<String>> iterator=arrayList.iterator();
        while(iterator.hasNext()){//?
            showArrayList(iterator.next());//only this?
        }
    }

}
