package ItemsManager;

public class Potion implements Item {
    private int ID;
    private String type;
    private String desc;
    private int price;
    private int count;
    private int effect;


    @Override
    public Item createItemInfos() {
        ID = 0;
        type = "Potion";
        desc = "";
        price = 0;
        count = 0;
        effect = 0;

        return this;
    }

    @Override
    public int getID() {
        return ID;
    }
}
