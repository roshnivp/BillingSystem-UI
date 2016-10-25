import java.util.ArrayList;

/**
 * Created by Roshni Velluva Puthanidam on 15/10/16.
 */
class McPatternsPresenter {
    //This is the class that will handle the model <-> UI communication.
    MenuModel model;
    McPatternsGUI view;
    ArrayList<String[]> itemsToBeAdded;
    void loadFile(){

        FileLoader.loadMenuItems();



    }

    void attachView(McPatternsGUI view) {
        this.view = view;
    }
    // Add functions to return the menu items.
    ArrayList<String[]> getDetails(){
        model=new MenuModel();
        itemsToBeAdded=model.getMenuItems();
        return itemsToBeAdded;
    }

}