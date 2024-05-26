package managers;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import itemInfos.Item;

public class ItemFileSystem {
    public static final String ITEM_FILE = "items.json";

    private static List<Item> Items;

    private static ItemFileSystem itemFileSystem;

    public static ItemFileSystem getItemFileSystem(){
        if(itemFileSystem == null)
        itemFileSystem = new ItemFileSystem();

        return itemFileSystem;
    }

    public void saveInfosToFile() {
        Gson gson = new Gson();

        try{
            FileWriter fw = new FileWriter(ITEM_FILE);

            fw.write(gson.toJson(Items));
            fw.close();
        } catch(IOException err){
            System.err.println(err);
        }
    }

    public void loadInfosFromFile() {
        if (!Files.exists(Paths.get(ITEM_FILE))) {
            Items = new ArrayList<>();
        } else {
            try {
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapter(Item.class, new ItemDeserializer());

                Gson gson = gsonBuilder.create();
                Reader reader = new FileReader(ITEM_FILE);
                JsonElement jsonElement = JsonParser.parseReader(reader);

                Items = gson.fromJson(jsonElement, new TypeToken<List<Item>>() {}.getType());
            } catch (IOException err) {
                System.err.println(err);
            }
        }
    }

    public Item getItem(String name) {
        for (Item i : Items) {
            if(i.getName().equals(name)) return i;
        }

        return null;
    }

    public List<Item> getItemList(){
        return Items;
    }
}
