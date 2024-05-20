package DataManager;

import ItemsManager.Item;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
/*
 거래소에 올라간 물품들 관리하는 클래스
 */
public class AuctionFileSystem {
    public static final String AUCTION_FILE = "auction.json";

    private static List<Item> items;

    public void saveAuctionToFile() {
        Gson gson = new Gson();

        try {
            FileWriter fw = new FileWriter(AUCTION_FILE);

            fw.write(gson.toJson(items));
            fw.close();
        } catch (IOException err) {
            System.err.println(err);
        }
    }

    public void loadAuctionFromFile() {
        if (!Files.exists(Paths.get(AUCTION_FILE))) {
            items = new ArrayList<>();
        } else {
            try {
                Gson gson = new Gson();
                Reader reader = new FileReader(AUCTION_FILE);
                JsonElement jsonElement = JsonParser.parseReader(reader);

                items = gson.fromJson(jsonElement, new TypeToken<ItemsManager.Item>() {
                }.getType());
            } catch (IOException err) {
                System.err.println(err);
            }
        }
    }

    public Item getAuctionItems(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) return item;
        }

        return null;
    }

    public boolean updateAuctionItems(String name, Item newItem) {
        for (int i = 0; i < items.size(); ++i) {
            if (items.get(i).getName().equals(name)) {
                items.set(i, newItem);
                return true;
            }
        }

        return false;
    }

    public Boolean deleteAuctionItem(String name) {
        for (int i = 0; i < items.size(); ++i) {
            if (items.get(i).getName().equals(name)) {
                items.remove(i);
                return true;
            }
        }

        return false;
    }

}
