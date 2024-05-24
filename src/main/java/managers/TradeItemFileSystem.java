package managers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import auctionData.TradeItem;
import user.userprivacy.User;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TradeItemFileSystem {
    private static final String TRADE_ITEM_FILE = "TradeItems.json";
    private static List<TradeItem> tradeItems;

    public void saveInfosToFile(){
        Gson gson = new Gson();

        try{
            FileWriter fileWriter = new FileWriter(TRADE_ITEM_FILE);

            String auctionString = gson.toJson(tradeItems);

            fileWriter.write(auctionString);
            fileWriter.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public void loadInfosFromFile(){
        if (!Files.exists(Paths.get(TRADE_ITEM_FILE))) {
            tradeItems = new ArrayList<>();
        } else {
            try {
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapter(TradeItem.class, new TradeItemDeserializer());

                Gson gson = gsonBuilder.create();
                Reader reader = new FileReader(TRADE_ITEM_FILE);
                JsonElement jsonElement = JsonParser.parseReader(reader);

                tradeItems = gson.fromJson(jsonElement, new TypeToken<List<TradeItem>>() {}.getType());
            } catch (IOException err) {
                System.err.println(err);
            }
        }
    }



    public TradeItem getTradeItem(int id) {
        for (TradeItem item : tradeItems) {
            if (item.getTradeId() == id) 
                return item;
        }

        return null;
    }

    public List<TradeItem> getTradeItemList(){
        return tradeItems;
    }


    public void putTradeItem(TradeItem newTradeItem) {
        tradeItems.add(newTradeItem);
        saveInfosToFile();
    }

    public Boolean updateItem(int id, TradeItem tradeItem) {
        for (int i = 0; i < tradeItems.size(); ++i) {
            if (tradeItems.get(i).getTradeId() == id) {
                tradeItems.set(i, tradeItem);
                return true;
            }
        }
        return false;
    }

    public Boolean deleteTradeItem(int id) {
        for (int i = 0; i < tradeItems.size(); ++i) {
            if (tradeItems.get(i).getTradeId() == id) {
                tradeItems.remove(i);
                return true;
            }
        }
        return false;
    }
}
