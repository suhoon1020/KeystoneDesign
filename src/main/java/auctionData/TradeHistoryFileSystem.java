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
    private static final String TRADE_HISTORY_FILE = "tradeHistory.json";
    private static List<TradeHistory> tradeHistories;

    private static TradeHistoryFileSystem tradeHistoryFileSystem;

    public static TradeHistoryFileSystem getTradeItemFileSystem() {
        if (tradeHistoryFileSystem == null)
            tradeHistoryFileSystem = new TradeHistoryFileSystem();

        return tradeHistoryFileSystem;
    }

    public void saveInfosToFile() {
        Gson gson = new Gson();

        try {
            FileWriter fw = new FileWriter(TRADE_HISTORY_FILE);

            fw.write(gson.toJson(tradeHistories));
            fw.close();
        } catch (IOException err) {
            System.err.println(err);
        }
    }

    public void loadInfosFromFile() {
        if (!Files.exists(Paths.get(TRADE_HISTORY_FILE))) {
            tradeHistories = new ArrayList<>();
        } else {
            try {
                Gson gson = new Gson();
                Reader reader = new FileReader(TRADE_HISTORY_FILE);
                JsonElement jsonElement = JsonParser.parseReader(reader);

                tradeHistories = gson.fromJson(jsonElement, new TypeToken<List<TradeHistory>>() {
                }.getType());
            } catch (IOException err) {
                System.err.println(err);
            }
        }
    }

    public List<TradeHistory> getTradeHistories() {
        return tradeHistories;
    }

    public void putTradeHistory(TradeHistory newHistory) {
        tradeHistories.add(newHistory);
    }
}
