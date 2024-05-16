package ItemsManager;

public class Material implements Item{
    private String name;
    private String type;
    private String desc;
    private int price;
    private int count;

    @Override
    public Item createItemInfos() {
        name = "";
        type = "Material";
        desc = "";
        price = 0;
        count = 0;

        return this;
    }

    @Override
    public String getName() {
        return name;
    }

}
