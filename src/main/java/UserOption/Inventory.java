package UserOption;

import ItemsManager.Item;

import java.util.List;
import java.util.Map;

public class Inventory {
    private String userID;
    private int gold;
    private List<Item> itemList;

    public Inventory(){

    }

    public String getUserID(){
        return userID;
    }

    public void setDefault(){

    }
}