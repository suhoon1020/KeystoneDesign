package DataManager;

import UserOption.User;
import com.google.gson.JsonArray;

import ItemsManager.Item;

public class FileFacade {
    private InventoryFileSystem inventoryFileSystem;
    private ItemFileSystem itemFileSystem;
    private UserFileSystem userFileSystem;

    public FileFacade(){
        this.inventoryFileSystem =new InventoryFileSystem();
        this.itemFileSystem =new ItemFileSystem();
        this.userFileSystem =new UserFileSystem();
    }

    public void saveUser(User user){
        userFileSystem.saveInfosToFile(user);
    }

    public JsonArray loadUsers(){
        return userFileSystem.loadInfosFromFile();
    }

    public void saveItem(Item item){
        itemFileSystem.PutData(item.getJsonObject());
    }



}