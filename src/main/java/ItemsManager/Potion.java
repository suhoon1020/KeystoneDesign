package ItemsManager;

import java.util.HashMap;

import org.json.simple.JSONObject;

public class Potion implements Item {
    JSONObject itemDetails;

    @Override
    public Item CreateItem() {
        HashMap<String, Object> potionDetails = new HashMap<String, Object>();

        potionDetails.put("ID", 0);
        potionDetails.put("type", "Equipment");
        potionDetails.put("desc", "");
        potionDetails.put("price", 0);
        potionDetails.put("count", 0);
        potionDetails.put("effect", 0);

        itemDetails = new JSONObject(potionDetails);

        return this;
    }

    @Override
    public JSONObject getJsonObject() {
        return itemDetails;
    }
}
