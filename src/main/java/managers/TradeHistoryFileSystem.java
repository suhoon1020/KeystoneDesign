package managers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import auctionData.TradeHistory;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TradeHistoryFileSystem {
    private static final String TRAGE_HISTORY_FILE = "tradeHistory.json";

    private static List<TradeHistory> TradeHistories;

    private static TradeHistoryFileSystem tradeHistoryFileSystem;

    public static TradeHistoryFileSystem getTradeItemFileSystem(){
        if(tradeHistoryFileSystem == null)
            tradeHistoryFileSystem = new TradeHistoryFileSystem();

        return tradeHistoryFileSystem;
    }

    public void saveInfosToFile(){
        Gson gson = new Gson();

        try{
            FileWriter fw = new FileWriter(TRAGE_HISTORY_FILE);

            fw.write(gson.toJson(TradeHistories));
            fw.close();
        } catch(IOException err){
            System.err.println(err);
        }
    }

    public void loadInfosFromFile(){
        if (!Files.exists(Paths.get(TRAGE_HISTORY_FILE))) {
            TradeHistories = new ArrayList<>();
        } else {
            try {
                Gson gson = new Gson();
                Reader reader = new FileReader(TRAGE_HISTORY_FILE);
                JsonElement jsonElement = JsonParser.parseReader(reader);

                TradeHistories = gson.fromJson(jsonElement, new TypeToken<List<TradeHistory>>() {}.getType());
            } catch (IOException err) {
                System.err.println(err);
            }
        }
    }

    public List<TradeHistory> getTradeHistories(){
        return TradeHistories;
    }

    public Boolean putTradeHistory(TradeHistory newHistory) {
        TradeHistories.add(newHistory);

        return true;
    }
}
