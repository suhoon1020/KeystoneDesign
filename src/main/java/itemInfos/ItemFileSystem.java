package itemInfos;

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

import auctionData.TradeItemFileSystem;
import itemObserver.ItemObserver;
import itemObserver.ItemSubject;
import user.UserFileSystem;

public class ItemFileSystem implements ItemSubject{
    public static final String ITEM_FILE = "items.json";

    private static List<Item> items;
    private List<ItemObserver> observers;

    private static ItemFileSystem itemFileSystem;

    public static ItemFileSystem getItemFileSystem(){
        if(itemFileSystem == null)
            itemFileSystem = new ItemFileSystem();

        return itemFileSystem;
    }

    private ItemFileSystem(){
        loadInfosFromFile();
        observers = new ArrayList<ItemObserver>();
        observers.add(TradeItemFileSystem.getTradeItemFileSystem());
        for(ItemObserver observer : UserFileSystem.getUserFileSystem().getUserList()){
            observers.add(observer);
        }
    }

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
        if (!Files.exists(Paths.get(ITEM_FILE))) {
            items = new ArrayList<>();
        } else {
            try {
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapter(Item.class, new ItemDeserializer());

                Gson gson = gsonBuilder.create();
                Reader reader = new FileReader(ITEM_FILE);
                JsonElement jsonElement = JsonParser.parseReader(reader);

                items = gson.fromJson(jsonElement, new TypeToken<List<Item>>() {}.getType());
            } catch (IOException err) {
                System.err.println(err);
            }
        }
    }

    public Item getItemByName(String name) {
        for (Item i : items) {
            if(i.getName().equals(name)) return i;
        }

        return null;
    }

    public List<Item> getItemList(){
        return items;
    }

    @Override
    public void registerObserver(ItemObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(ItemObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(Item item, String option) {
        for (ItemObserver o : observers){
            o.mobifiedOriginItem(item, option);
        }
    }
}
