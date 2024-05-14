package ItemsManager;

import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.simple.JSONObject;

public class Material implements Item{
    JSONObject itemDetails;

    @Override
    public Item createItemInfos() {
        HashMap<String,Object> materialDetails = new HashMap<String,Object>();

        materialDetails.put("ID", 0);
        materialDetails.put("type", "Equipment");
        materialDetails.put("desc", "");
        materialDetails.put("price", 0);
        materialDetails.put("count", 0);
        materialDetails.put("defence", 0);

        itemDetails = new JSONObject(materialDetails);
        return this;
    }

    @Override
    public JSONObject getJsonObject() {
        return itemDetails;
    }
}
