package auctionData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import itemInfos.Item;
import itemObserver.ItemObserver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TradeItemFileSystem implements ItemObserver{
    private static final String TRADE_ITEM_FILE = "TradeItems.json";

    private static List<TradeItem> tradeItems;

    private static TradeItemFileSystem tradeHistoryFileSystem;

    public static TradeItemFileSystem getTradeItemFileSystem() {
        if(tradeHistoryFileSystem == null)
            tradeHistoryFileSystem = new TradeItemFileSystem();
        return tradeHistoryFileSystem;
    }

    private TradeItemFileSystem(){
        loadInfosFromFile();
    }

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

    public TradeItem getTradeItemByTradeId(int tradeId){
        for (TradeItem tradeItem : tradeItems) {
            if (tradeItem.getTradeId() == tradeId)
                return tradeItem;
        }
        return null;
    }

    public List<TradeItem> getTradeItemList(){
        return tradeItems;
    }

    @Override
    public void updateItem(Item newItem, String option) {
        String itemName = newItem.getName();
        
        switch (option) {
            case "Update":
                for (int i = 0; i < tradeItems.size(); ++i) {
                    if (tradeItems.get(i).getName().equals(itemName)) {
                        TradeItem tradeItem = tradeItems.get(i);
                        tradeItem = new TradeItem(tradeItem.getUserId(), newItem.clone(), tradeItem.getCount(), tradeItem.getPrice());
                        tradeItems.set(i, tradeItem);
                    }
                }
                break;
            case "Delete":
                for (int i = 0; i < tradeItems.size(); ++i) {
                    if (tradeItems.get(i).getName().equals(itemName)) {
                        tradeItems.remove(i);
                    }
                }
                break;
        }

        // 저장
        saveInfosToFile();
    }
}
