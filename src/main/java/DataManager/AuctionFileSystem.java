package DataManager;

import AuctionManager.Auction;
import AuctionManager.RegisterdItem;
import UserOption.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

public class AuctionFileSystem {
    private static final String REGISTER_ITEM_FILE = "registerItem.json";
    private static List<RegisterdItem> registerdItems;


    public void saveRegisterdItems(){
        Gson gson = new Gson();

        try{
            FileWriter fileWriter = new FileWriter(REGISTER_ITEM_FILE);

            String auctionString = gson.toJson(registerdItems);

            fileWriter.write(auctionString);
            fileWriter.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public void loadRegisterdItems(){
        if (!Files.exists(Paths.get(REGISTER_ITEM_FILE))) {
            registerdItems = new ArrayList<>();
        } else {
            try {
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapter(User.class, new UserDeserializer());

                Gson gson = gsonBuilder.create();
                Reader reader = new FileReader(REGISTER_ITEM_FILE);
                JsonElement jsonElement = JsonParser.parseReader(reader);

                registerdItems = gson.fromJson(jsonElement, new TypeToken<List<User>>() {}.getType());
            } catch (IOException err) {
                System.err.println(err);
            }
        }
    }


}
