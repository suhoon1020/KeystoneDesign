package ItemsManager;

public class ItemFactory {

    public Item CreateItem(String type) {

        Item item = null;

        switch (type) {
            case "Equipment":
                item = new Equipment().CreateItem();
                break;
            case "Material":
                item = new Material().CreateItem();
                break;
            case "Potion":
                item = new Potion().CreateItem();
                break;
            case "Weapon":
                item = new Weapon().CreateItem();
                break;
            default:
                System.out.println("존재하지 않는 아이템");
                break;
        }

        return item;
    }
}
