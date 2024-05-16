package DataManager;

import java.util.List;

import ItemsManager.Item;
import UserOption.User;


public class FileFacade {
    private static FileFacade fileFacade;

    private InventoryFileSystem inventoryFileSystem;
    private ItemFileSystem itemFileSystem;
    private UserFileSystem userFileSystem;

    private FileFacade() {
        this.inventoryFileSystem = new InventoryFileSystem();
        this.itemFileSystem = new ItemFileSystem();
        this.userFileSystem = new UserFileSystem();

        itemFileSystem.loadInfosFromFile();
        userFileSystem.loadInfosFromFile();
    }

    public static FileFacade getFacade(){
        if(fileFacade == null){
            fileFacade = new FileFacade();
        }
        return fileFacade;
    }

    /* 
     *      USERS
     */

    public void saveUsers() {
        userFileSystem.saveInfosToFile();
    }

    public void loadUsers() {
        userFileSystem.loadInfosFromFile();
    }

    public void getUser(String id) {
        userFileSystem.getUser(id);
    }

    public List<User> getUsersList(){
        return userFileSystem.getUsersList();
    }

    public void putUser(User user) {
        userFileSystem.putUser(user);
    }

    /* 
     *      ITEMS
     */

    public void saveItems() {
        itemFileSystem.saveInfosToFile();
    }

    public void loadItems() {
        itemFileSystem.loadInfosFromFile();
    }

    public void putItem(Item item) {
        itemFileSystem.putItem(item);
        itemFileSystem.saveInfosToFile();
    }

    public Item getItem(String name){
        return itemFileSystem.getItem(name);
    }

    public boolean updateItem(String name, Item item){
        return itemFileSystem.updateItem(name, item);
    }

    public boolean deleteItem(String name){
        return itemFileSystem.deleteItem(name);
    }

    public void getItems(){
        itemFileSystem.getItems();
    }
}