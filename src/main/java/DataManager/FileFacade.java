package DataManager;

import java.util.List;

import ItemsManager.Item;
import UserOption.Inventory;
import UserOption.User;


public class FileFacade {
    private static FileFacade fileFacade;

    private ItemFileSystem itemFileSystem;
    private UserFileSystem userFileSystem;
    private InventoryFileSystem inventoryFileSystem;
    private AuctionFileSystem auctionFileSystem;


    private FileFacade() {
        this.itemFileSystem = new ItemFileSystem();
        this.userFileSystem = new UserFileSystem();
        this.auctionFileSystem = new AuctionFileSystem();
        this.inventoryFileSystem = new InventoryFileSystem();

        itemFileSystem.loadInfosFromFile();
        userFileSystem.loadInfosFromFile();
        inventoryFileSystem.loadInfosFromFile();
        auctionFileSystem.loadAuctionFromFile();
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

    public User getUser(String id) {
        return userFileSystem.getUser(id);
    }

    public List<User> getUsersList(){
        return userFileSystem.getUsersList();
    }

    public boolean putUser(User user) {
        return userFileSystem.putUser(user);
    }

    public Boolean isExistingUser(String id) {return userFileSystem.isExistID(id);}

    public boolean updateUser(String id, User user){
        return userFileSystem.updateUser(id, user);
    }

    public Boolean deleteUser(String id){
        return userFileSystem.deleteUser(id);
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

    public boolean putItem(Item item) {
        return itemFileSystem.putItem(item);
    }

    public Item getItem(String name){
        return itemFileSystem.getItem(name);
    }

    public List<Item> getItemList(){
        return itemFileSystem.getItemList();
    }

    public boolean updateItem(String name, Item item){
        return itemFileSystem.updateItem(name, item);
    }

    public boolean deleteItem(String name){
        return itemFileSystem.deleteItem(name);
    }

    /*
     *      INVENTORY
     */

    public boolean putInventory(Inventory newInventory){
        return inventoryFileSystem.putInventory(newInventory);
    }

    public Inventory getInventory(String userID){
        return inventoryFileSystem.getInventory(userID);
    }

    public void saveInventories() {
        inventoryFileSystem.saveInfosToFile();
    }

    public void loadInventories() {
        inventoryFileSystem.loadInfosFromFile();
    }

    /*

    TRADING
     */
    public void tradeItem(){

    }
}