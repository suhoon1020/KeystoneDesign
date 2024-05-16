package DataManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import ItemsManager.Item;

public class ItemFileSystem {
    public static final String ITEM_FILE = "items.json";

    private static List<Item> items;

    public void saveInfosToFile() {
        Gson gson = new Gson();

        try{
            FileWriter fw = new FileWriter(ITEM_FILE);

            fw.write(gson.toJson(items));
            fw.close();
        } catch(IOException err){
            System.err.println(err);
        }
    }

    public void loadInfosFromFile() {
        
        if (Files.exists(Paths.get(ITEM_FILE))) {
            items = new ArrayList<>();
        }
        else{
            try {
                Gson gson = new Gson();
                Reader reader = new FileReader(ITEM_FILE);
                JsonElement jsonElement = JsonParser.parseReader(reader);
    
                items = gson.fromJson(jsonElement, new TypeToken<List<Item>>() {}.getType());
            } catch(IOException err){
                System.err.println(err);
            }
        }
    }

    public Boolean putItem(Item newItem) {
        for (Item I : items) {
            if(I.getName() == newItem.getName())
            return false;
        }

        items.add(newItem);

        return true;
    }

    public Item getItem(String name) {
        for (Item i : items) {
            if(i.getName() == name) return i;
        }

        return null;
    }

    public boolean updateItem(String name, Item newItem){
        for(int i = 0; i < items.size(); ++i){
            if(items.get(i).getName() == name) {
                items.set(i, newItem);
                return true;
            }
        }

        return false;
    }

    public boolean deleteItem(String name){
        for(int i = 0; i < items.size(); ++i){
            if(items.get(i).getName() == name) {
                items.remove(i);
                return true;
            }
        }

        return false;
    }

}
