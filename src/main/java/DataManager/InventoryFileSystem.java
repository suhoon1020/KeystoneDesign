package DataManager;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import UserOption.Inventory;

public class InventoryFileSystem {
    public static final String INVENTORY_FILE = "invs.json";
    public static final String AUCTION_FILE = "auction.json";

    private static List<Inventory> inventories;
    private static Inventory auction;

    public void saveInfosToFile() {
        Gson gson = new Gson();

        try{
            FileWriter fw = new FileWriter(INVENTORY_FILE);

            fw.write(gson.toJson(inventories));
            fw.close();
        } catch(IOException err){
            System.err.println(err);
        }
    }

    public void loadInfosFromFile() {
        if (!Files.exists(Paths.get(INVENTORY_FILE))) {
            inventories = new ArrayList<>();
        } else {
            try {
                Gson gson = new Gson();
                Reader reader = new FileReader(INVENTORY_FILE);
                JsonElement jsonElement = JsonParser.parseReader(reader);

                inventories = gson.fromJson(jsonElement, new TypeToken<List<Inventory>>() {}.getType());
            } catch (IOException err) {
                System.err.println(err);
            }
        }
    }

    public void saveAuctionToFile() {
        Gson gson = new Gson();

        try{
            FileWriter fw = new FileWriter(AUCTION_FILE);

            fw.write(gson.toJson(auction));
            fw.close();
        } catch(IOException err){
            System.err.println(err);
        }
    }

    public void loadAuctionFromFile() {
        if (!Files.exists(Paths.get(AUCTION_FILE))) {
            auction = new Inventory();
        } else {
            try {
                Gson gson = new Gson();
                Reader reader = new FileReader(AUCTION_FILE);
                JsonElement jsonElement = JsonParser.parseReader(reader);

                auction = gson.fromJson(jsonElement, new TypeToken<Inventory>() {}.getType());
            } catch (IOException err) {
                System.err.println(err);
            }
        }
    }

    public Inventory getIventory(String userID) {
        for (Inventory i : inventories) {
            if(i.getUserID() == userID) return i;
        }

        return null;
    }

    public boolean updateInventory(String userID, Inventory newInventory){
        for(int i = 0; i < inventories.size(); ++i){
            if(inventories.get(i).getUserID() == userID) {
                inventories.set(i, newInventory);
                return true;
            }
        }

        return false;
    }


}
