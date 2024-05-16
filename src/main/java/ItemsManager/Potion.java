package ItemsManager;

public class Potion implements Item {
    private String name;
    private String type;
    private String grade;
    private String desc;
    private int price;
    private int count;
    private int effect;

    @Override
    public Item createItemInfos() {
        name = "";
        type = "Potion";
        grade = "";
        desc = "";
        price = 0;
        count = 0;
        effect = 0;

        return this;
    }

    @Override
    public String getName() {
        return name;
    }
}
