package ItemsManager;

import java.util.HashMap;
import org.json.simple.JSONObject;

public class Equipment implements Item{
    JSONObject itemDetails;

    @Override
    public Item CreateItem() {
        HashMap<String,Object> equipmentDetails = new HashMap<String,Object>();

        equipmentDetails.put("ID", 0);
        equipmentDetails.put("type", "Equipment");
        equipmentDetails.put("desc", "");
        equipmentDetails.put("price", 0);
        equipmentDetails.put("count", 0);
        equipmentDetails.put("defence", 0);

        itemDetails = new JSONObject(equipmentDetails);
        
        return this;
    }

    @Override
    public JSONObject getJsonObject() {
        return itemDetails;
    }
}
