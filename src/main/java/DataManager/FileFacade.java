package DataManager;

import UserOption.User;
import com.google.gson.JsonArray;

public class FileFacade {
    private InventoryManager inventoryManager;
    private ItemManager itemManager;
    private UserManager userManager;

    public FileFacade(){
        this.inventoryManager=new InventoryManager();
        this.itemManager=new ItemManager();
        this.userManager=new UserManager();
    }

    public void saveUser(User user){
        userManager.saveInfosToFile(user);
    }

    public JsonArray loadUsers(){
        return userManager.loadInfosFromFile();
    }



}