package ItemsManager;

public class Equipment implements Item{
    private int ID;
    private String type;
    private String desc;
    private int price;
    private int count;
    private int defence;


    @Override
    public Item createItemInfos() {
        ID = 0;
        type = "Equipment";
        desc = "";
        price = 0;
        count = 0;
        defence = 0;
        
        return this;
    }
    
    @Override
    public int getID() {
        return ID;
    }
}
