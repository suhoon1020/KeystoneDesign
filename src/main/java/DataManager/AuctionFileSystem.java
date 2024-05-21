package DataManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import ItemsManager.Item;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AuctionFileSystem {
    private static final String AUCTION_ITEM_FILE = "auctionItems.json";

    private static List<AuctionItem> AuctionItems;

    public void saveInfosToFile(){
        Gson gson = new Gson();

        try{
            FileWriter fw = new FileWriter(AUCTION_ITEM_FILE);

            fw.write(gson.toJson(AuctionItems));
            fw.close();
        } catch(IOException err){
            System.err.println(err);
        }
    }

    public void loadInfosFromFile(){
        if (!Files.exists(Paths.get(AUCTION_ITEM_FILE))) {
            AuctionItems = new ArrayList<>();
        } else {
            try {
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapter(AuctionItem.class, new UserDeserializer());

                Gson gson = gsonBuilder.create();
                Reader reader = new FileReader(AUCTION_ITEM_FILE);
                JsonElement jsonElement = JsonParser.parseReader(reader);

                AuctionItems = gson.fromJson(jsonElement, new TypeToken<List<AuctionItem>>() {}.getType());
            } catch (IOException err) {
                System.err.println(err);
            }
        }
    }

    public Boolean putAuctionItem(AuctionItem newAuctionItem) {
        AuctionItems.add(newAuctionItem);

        return true;
    }
}
