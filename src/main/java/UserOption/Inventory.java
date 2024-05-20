package UserOption;

import java.util.ArrayList;
import java.util.List;

import ItemsManager.Item;

public class Inventory {
    private String userID;
    private int gold;
    private List<Item> itemList;

    public Inventory(String userID){
        this.userID = userID;
        gold = 1000;
        itemList = new ArrayList<Item>();
    }
    
    public Inventory(String userID, int gold, List<Item> itemList){
        this.userID = userID;
        this.gold = gold;
        this.itemList = itemList;
    }

    public String getUserID(){
        return userID;
    }

    public int getGold(){
        return gold;
    }

    public List<Item> getItemList(){
        return itemList;
    }

    public int getItemCount(String itemName){
        for(Item I : itemList){
            if(I.getName().equals(itemName)){
                return I.getCount();
            }
        }
        return -1;
    }

    public void gainItem(Item item) {
        for (Item I : itemList) {
            if(I.getName().equals(item.getName())){
                I.gainItem(item.getCount());
                return;
            }
        }

        itemList.add(item);
        return;
    }

    public boolean loseItem(Item item){
        for (Item I : itemList) {
            if(I.getName().equals(item.getName())){
                if(I.loseItem(item.getCount()))
                    return true;
                else
                    return false;
            }
        }

        return false;
    }
}