/**
 * Created by Roshni Velluva Puthanidam on 15/10/16.
 */

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.concurrent.Exchanger;

class FileLoader {
    static ArrayList<String> item_list=new ArrayList<>();
    static void  loadMenuItems(){
        //read text file & create an arraylist of menu items
            File inputFile = new File("src/menu.txt");

        try{
            Scanner in = new Scanner(inputFile);
            //String label;
            while(in.hasNextLine()){
                String line = in.nextLine();
                item_list.add(line);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Unable to load order from " + inputFile + ".");
        }

    }
    static ArrayList<String> getMenu(){
        return item_list;
    }
}
