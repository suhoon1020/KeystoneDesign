package ItemsManager;

public class ItemBuilder {
    public String type;
    public String name;
    public String grade;
    public String desc;
    public int price;
    public int count;
    public int option1;
        
    public ItemBuilder type(String type){
        this.type = type;
        return this;
    }

    public ItemBuilder name(String name){
        this.name = name;
        return this;
    }

    public ItemBuilder grade(String grade){
        this.grade = grade;
        return this;
    }

    public ItemBuilder desc(String desc){
        this.desc = desc;
        return this;
    }

    public ItemBuilder price(int price){
        this.price = price;
        return this;
    }

    public ItemBuilder count(int count){
        this.count = count;
        return this;
    }

    public ItemBuilder option1(int option1){
        this.option1 = option1;
        return this;
    }
    
    public Item build(){
        Item item = null;

        switch (type) {
            case "Equipment":
                item = new Equipment().createItemInfos(this);
                break;
            case "Material":
                item = new Material().createItemInfos(this);
                break;
            case "Potion":
                item = new Potion().createItemInfos(this);
                break;
            case "Weapon":
                item = new Weapon().createItemInfos(this);
                break;
            default:
                System.out.println("존재하지 않는 아이템");
                break;
        }

        return item;
    }
}
