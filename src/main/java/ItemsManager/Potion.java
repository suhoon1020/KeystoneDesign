package ItemsManager;

public class Potion implements Item {
    private String type;
    private String name;
    private String grade;
    private String desc;
    private int price;
    private int effect;

    @Override
    public Item createItemInfos(String type, String name, String grade, String desc, int price, int op1) {
        this.type = type;
        this.name = name;
        this.grade = grade;
        this.desc = desc;
        this.price = price;
        this.effect = op1;
        
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String[] getData() {
        return new String[]{type, name, grade, desc, Integer.toString(price), Integer.toString(effect)};
    }
}
