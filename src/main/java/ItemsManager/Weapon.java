package ItemsManager;

public class Weapon implements Item {
    private String type;
    private String name;
    private String grade;
    private String desc;
    private int price;
    private int damage;

    @Override
    public Item createItemInfos(String type, String name, String grade, String desc, int price, int op1) {
        this.type = type;
        this.name = name;
        this.grade = grade;
        this.desc = desc;
        this.price = price;
        this.damage = op1;

        return this;
    }

    @Override
    public String getName() {
        return name;
    }
}
