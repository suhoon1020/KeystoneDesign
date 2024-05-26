package user;

import ItemManager.Item;

public class InventoryItem {
    Item item;
    int count;

    public InventoryItem(Item item, int count) {
        this.item = item;
        this.count = count;
    }

    public Item getItem(){
        return item.clone();
    }

    public String getType(){
        return item.getType();
    }
    
    public String getName(){
        return item.getName();
    }

    public String getGrade(){
        return item.getGrade();
    }

    public String getDesc(){
        return item.getDesc();
    }

    public int getOption1(){
        return item.getOption1();
    }

    public int getCount(){
        return count;
    }

    public void setCount(int count){
        this.count = count;
    }

    public String[] getListData() {
        return new String[]{getType(), 
                getName(), 
                getGrade(), 
                getDesc(), 
                Integer.toString(getOption1()), 
                Integer.toString(getCount())};
    }
}
