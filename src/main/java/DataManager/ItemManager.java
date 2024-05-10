package DataManager;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ItemManager {
    public static final String USER_FILE = "items.json";

    private static JSONArray Items;

    public void SaveInfosToFile() {
        List<String> ItemsString = new ArrayList<String>();

        for(int i = 0; i < Items.size(); ++i){
            ItemsString.add(((JSONObject)Items.get(i)).toString());
        }
    }

    public void LoadInfosFromFile() {
        Items = new JSONArray();
        Reader reader;
        JSONParser parser = new JSONParser();

        try{
            reader = new FileReader(USER_FILE);
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
