package DataManager;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ItemManager {
    public static final String ITEM_FILE = "items.json";

    private static JSONArray Items;

    public void SaveInfosToFile() {
        FileWriter fw;

        try{
            fw = new FileWriter(ITEM_FILE);

            fw.write(Items.toJSONString());

            fw.close();
        } catch(IOException err){
            System.err.println(err);
        }
    }

    public void LoadInfosFromFile() {
        Items = new JSONArray();
        Reader reader;
        JSONParser parser = new JSONParser();

        try{
            reader = new FileReader(ITEM_FILE);
            Items = (JSONArray)parser.parse(reader); 

        } catch(IOException err){
            System.err.println(err);
        } catch(ParseException err){
            System.err.println(err);
        }
        
    }

    public void PutData(JSONObject o) {
        Items.add(o);
    }
}
