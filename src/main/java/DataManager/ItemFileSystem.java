package DataManager;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ItemFileSystem {
    public static final String ITEM_FILE = "items.json";

    private static JSONArray Items;

    public void saveInfosToFile() {
        FileWriter fw;

        try{
            fw = new FileWriter(ITEM_FILE);

            fw.write(Items.toJSONString());

            fw.close();
        } catch(IOException err){
            System.err.println(err);
        }
    }

    public void loadInfosFromFile() {
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

    public Boolean putItem(JSONObject o) {

        String ID = (String)o.get("ID");

        for (Object obj : Items) {
            JSONObject jsonObject = (JSONObject) obj;

            // 아이디가 같은 값이 있다면 중단
            if (ID.equals(jsonObject.get("ID"))) return false;
        }

        Items.add(o);
        return true;
    }

    public JSONObject getItem(String ID) {
        for (Object obj : Items) {
            JSONObject jsonObject = (JSONObject) obj;

            // 오브젝트를 찾았다면
            if (ID.equals(jsonObject.get("ID"))) return jsonObject;
        }

        // 찾는값이 없다면
        return null;
    }


}
