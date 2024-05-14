package ItemsManager;

public class Weapon implements Item {
    private int ID;
    private String type;
    private String desc;
    private int price;
    private int count;
    private int damage;

    @Override
    public Item createItemInfos() {
        ID = 0;
        type = "Weapon";
        desc = "";
        price = 0;
        count = 0;
        damage = 0;

        return this;
    }

    @Override
    public int getID() {
        return ID;
    }
}
