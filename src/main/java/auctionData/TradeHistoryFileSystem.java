package auctionData;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TradeHistoryFileSystem {
    private static final String TRADE_HISTORY_FILE = "tradeHistories.json";

    private static List<TradeHistory> tradeHistoryList;
    
    private static TradeHistoryFileSystem tradeHistoryFileSystem;

    public static TradeHistoryFileSystem getTradeHistoryFileSystem() {
        if (tradeHistoryFileSystem == null)
            tradeHistoryFileSystem = new TradeHistoryFileSystem();

        return tradeHistoryFileSystem;
    }

    private TradeHistoryFileSystem() {
        loadInfosFromFile();
    }

    public void saveInfosToFile() {
        Gson gson = new Gson();

        try {
            FileWriter fw = new FileWriter(TRADE_HISTORY_FILE);

            fw.write(gson.toJson(tradeHistoryList));
            fw.close();
        } catch (IOException err) {
            System.err.println(err);
        }
    }

    public void loadInfosFromFile() {
        if (!Files.exists(Paths.get(TRADE_HISTORY_FILE))) {
            tradeHistoryList = new ArrayList<TradeHistory>();
        } else {
            try {
                Gson gson = new Gson();
                Reader reader = new FileReader(TRADE_HISTORY_FILE);
                JsonElement jsonElement = JsonParser.parseReader(reader);

                tradeHistoryList = gson.fromJson(jsonElement, new TypeToken<List<TradeHistory>>() {}.getType());
            } catch (IOException err) {
                System.err.println(err);
            }
        }
    }

    public void putTradeHistory(TradeHistory newHistory) {
        tradeHistoryList.add(newHistory);
    }

    public List<TradeHistory> getTradeHistoryList() {
        return tradeHistoryList;
    }

    public void checkPrice(String itemName) {
        int max;
        int min;
        int avg;
        int sum = 0;

        List<Integer> price = new ArrayList<>();

        for (TradeHistory tradeHistory : tradeHistoryList) {
            if (itemName.equals(tradeHistory.getItemName())) {
                price.add(tradeHistory.getPrice());
                sum += tradeHistory.getPrice();
            }
        }
        if (!price.isEmpty()) {
            max = Collections.max(price);
            min = Collections.min(price);
            avg = sum / price.size();

            JOptionPane.showMessageDialog(null, itemName +" 아이템의 \n최대 값은 : " + max + "\n최소값은 : " + min + "\n평균 값은 : " + avg + " 입니다");
        } else {
            JOptionPane.showMessageDialog(null, "해당 아이템의 최근 거래 내역이 없습니다");
        }
    }


}
