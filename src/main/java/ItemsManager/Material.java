package ItemsManager;

public class Material implements Item{
    private int ID;
    private String type;
    private String desc;
    private int price;
    private int count;

    @Override
    public Item createItemInfos() {
        ID = 0;
        type = "Material";
        desc = "";
        price = 0;
        count = 0;

        return this;
    }

    @Override
    public int getID() {
        return ID;
    }

}
