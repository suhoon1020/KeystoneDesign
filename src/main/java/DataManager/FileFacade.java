package DataManager;

import ItemsManager.Item;
import UserOption.User;
import com.google.gson.JsonArray;
import java.util.List;


public class FileFacade {
    private InventoryFileSystem inventoryManager;
    private ItemFileSystem itemManager;
    private UserFileSystem userManager;

    public FileFacade(){
        this.inventoryManager=new InventoryFileSystem();
        this.itemManager=new ItemFileSystem();
        this.userManager=new UserFileSystem();
    }

    public void saveUser(User user){
        userManager.saveInfosToFile(user);
    }

    public JsonArray loadUsers(){
        return userManager.loadInfosFromFile();
    }

    public void getDefinedUser(String id){
        userManager.getUser(id);
    }

    public void saveItem(Item item){
        itemManager.PutData(item.getJsonObject());
    }



}