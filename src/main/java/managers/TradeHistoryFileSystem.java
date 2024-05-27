package managers;

import auctionData.BasicHistory;
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



    private static List<BasicHistory> tradeHistories;

    private static List<BasicHistory> filterHistory;

    public static void setFilterHistory(List<BasicHistory> filterHistory) {
        TradeHistoryFileSystem.filterHistory = filterHistory;
    }

    public List<BasicHistory> getFilterHistory() {
        return filterHistory;
    }

    private static TradeHistoryFileSystem tradeHistoryFileSystem;

    public static TradeHistoryFileSystem getTradeItemFileSystem() {
        if (tradeHistoryFileSystem == null)
            tradeHistoryFileSystem = new TradeHistoryFileSystem();

        return tradeHistoryFileSystem;
    }

    public void saveInfosToFile() {
        Gson gson = new Gson();

        try {
            FileWriter fw = new FileWriter(TRAGE_HISTORY_FILE);

            fw.write(gson.toJson(tradeHistories));
            fw.close();
        } catch (IOException err) {
            System.err.println(err);
        }
    }

    public void loadInfosFromFile() {
        if (!Files.exists(Paths.get(TRAGE_HISTORY_FILE))) {
            tradeHistories = new ArrayList<>();
        } else {
            try {
                Gson gson = new Gson();
                Reader reader = new FileReader(TRAGE_HISTORY_FILE);
                JsonElement jsonElement = JsonParser.parseReader(reader);

                tradeHistories = gson.fromJson(jsonElement, new TypeToken<List<TradeHistory>>() {
                }.getType());
            } catch (IOException err) {
                System.err.println(err);
            }
        }
    }

    public List<BasicHistory> getTradeHistories() {
        return tradeHistories;
    }

    public Boolean putTradeHistory(BasicHistory newHistory) {
        tradeHistories.add(newHistory);

        return true;
    }
}
