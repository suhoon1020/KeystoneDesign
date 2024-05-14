package ItemsManager;

public class ItemFactory {

    public Item createItem(String type) {

        Item item = null;

        switch (type) {
            case "Equipment":
                item = new Equipment().createItemInfos();
                break;
            case "Material":
                item = new Material().createItemInfos();
                break;
            case "Potion":
                item = new Potion().createItemInfos();
                break;
            case "Weapon":
                item = new Weapon().createItemInfos();
                break;
            default:
                System.out.println("존재하지 않는 아이템");
                break;
        }

        return item;
    }
}
