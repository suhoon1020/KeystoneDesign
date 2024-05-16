package DataManager;

import ItemsManager.Item;
import UserOption.User;


public class FileFacade {
    private InventoryFileSystem inventoryManager;
    private ItemFileSystem itemManager;
    private UserFileSystem userManager;

    public FileFacade() {
        this.inventoryManager = new InventoryFileSystem();
        this.itemManager = new ItemFileSystem();
        this.userManager = new UserFileSystem();
    }

    public void saveUsers() {
        userManager.saveInfosToFile();
    }

    public void loadUsers() {
        userManager.loadInfosFromFile();
    }

    public void getUser(String id) {
        userManager.getUser(id);
    }

    public void putUser(User user) {
        userManager.putUser(user);
    }

    public void saveItem(Item item) {
        itemManager.putItem(item);
    }


}