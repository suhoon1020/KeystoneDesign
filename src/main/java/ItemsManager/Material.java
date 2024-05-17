package ItemsManager;

public class Material implements Item{
    private String type;
    private String name;
    private String grade;
    private String desc;
    private int price;

    @Override
    public Item createItemInfos(String type, String name, String grade, String desc, int price, int op1) {
        this.type = type;
        this.name = name;
        this.grade = grade;
        this.desc = desc;
        this.price = price;
        
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

}
