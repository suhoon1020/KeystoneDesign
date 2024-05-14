package DataManager;

import ItemsManager.Item;
import org.json.simple.JSONObject;


public class FileFacade {
    private InventoryFileSystem inventoryManager;
    private ItemFileSystem itemManager;
    private UserFileSystem userManager;

    public FileFacade(){
        this.inventoryManager=new InventoryFileSystem();
        this.itemManager=new ItemFileSystem();
        this.userManager=new UserFileSystem();
    }

    public void saveUser(){
        userManager.saveInfosToFile();
    }

    public void loadUsers(){
        userManager.loadInfosFromFile();
    }

    public void getDefinedUser(String id){
        userManager.getUser(id);
    }

    public void putUser(JSONObject o){
        userManager.putUser(o);
    }

    public void saveItem(Item item){
        itemManager.putItem(item.getJsonObject());
    }



}