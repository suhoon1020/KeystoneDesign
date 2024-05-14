package DataManager;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import ItemsManager.Item;
import UserOption.User;

public class ItemFileSystem {
    public static final String ITEM_FILE = "items.json";

    private static List<Item> items;

    public void saveInfosToFile() {
        Gson gson = new Gson();
        FileWriter fw;

        try{
            fw = new FileWriter(ITEM_FILE);

            fw.write(gson.toJson(items));

            fw.close();
        } catch(IOException err){
            System.err.println(err);
        }
    }

    public void loadInfosFromFile() {
        Gson gson = new Gson();

        try {
            Reader reader = new FileReader(ITEM_FILE);
            JsonElement jsonElement = JsonParser.parseReader(reader);

            items = gson.fromJson(jsonElement, new TypeToken<List<User>>() {}.getType());

        } catch(IOException err){
            System.err.println(err);
        }
        
    }

    public Boolean putItem(Item newItem) {
        for (Item I : items) {
            if(I.getID() == newItem.getID())
            return false;
        }

        items.add(newItem);

        return true;
    }

    public Item getItem(int ID) {
        for (Item i : items) {
            if(i.getID() == ID) return i;
        }

        return null;
    }

    public boolean updateItem(int ID, Item newItem){
        for(int i = 0; i < items.size(); ++i){
            if(items.get(i).getID() == ID) {
                items.set(i, newItem);
                return true;
            }
        }

        return false;
    }

    public boolean deleteItem(int ID){
        for(int i = 0; i < items.size(); ++i){
            if(items.get(i).getID() == ID) {
                items.remove(i);
                return true;
            }
        }

        return false;
    }

}
