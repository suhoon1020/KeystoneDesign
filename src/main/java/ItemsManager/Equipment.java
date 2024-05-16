package ItemsManager;

public class Equipment implements Item{
    private String name;
    private String type;
    private String desc;
    private int price;
    private int count;
    private int defence;


    @Override
    public Item createItemInfos() {
        name = "";
        type = "Equipment";
        desc = "";
        price = 0;
        count = 0;
        defence = 0;
        
        return this;
    }
    
    @Override
    public String getName() {
        return name;
    }
}
