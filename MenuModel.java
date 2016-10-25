import java.util.ArrayList;

/**
 * Created by Roshni Velluva Puthanidam on 15/10/16.
 */



class MenuModel {
    // Add your implementation for Menu Items here.
    // Determine what data you want to store for each item.
    String item_name;
    double item_cost;
    ArrayList<String[]> items=new ArrayList<>();

    ArrayList<String[]> getMenuItems(){
        ArrayList<String> item_menu= FileLoader.getMenu();
        for(int i=0;i<item_menu.size();i++){
            String[] products=item_menu.get(i).split("\\|");
            items.add(products);
        }
        return items;
    }

    public String getItem_name() {

        return item_name;
    }

    public double getItem_cost() {
        return item_cost;
    }

}