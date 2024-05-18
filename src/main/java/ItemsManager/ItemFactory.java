package ItemsManager;

public class ItemFactory {

    private Item createItem(String type, String name, String grade, String desc, int price, int op1) {
        Item item = null;

        switch (type) {
            case "Equipment":
                item = new Equipment().createItemInfos(type, name, grade, desc, price, op1);
                break;
            case "Material":
                item = new Material().createItemInfos(type, name, grade, desc, price, op1);
                break;
            case "Potion":
                item = new Potion().createItemInfos(type, name, grade, desc, price, op1);
                break;
            case "Weapon":
                item = new Weapon().createItemInfos(type, name, grade, desc, price, op1);
                break;
            default:
                System.out.println("존재하지 않는 아이템");
                break;
        }

        return item;
    }

    public static class ItemBuilder {
        private String type;
        private String name;
        private String grade;
        private String desc;
        private int price;
        private int option1;
    
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

        public ItemBuilder option1(int option1){
            this.option1 = option1;
            return this;
        }

        public Item build(){
            ItemFactory itemFactory = new ItemFactory();

            return itemFactory.createItem(type, name, grade, desc, price, option1);
        }
    } 
}
