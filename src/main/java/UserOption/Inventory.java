package UserOption;

import java.util.Map;

public class Inventory {
    private String userID;
    private int gold;
    private Map<String, Integer> itemList;

    public String getUserID(){
        return userID;
    }

}