package Items;

import org.json.simple.JSONObject;

public class Potion implements Item{
    String type;

    @Override
    public JSONObject ToJsonObj() {
        JSONObject obj = new JSONObject();

        obj.put("type", type);

        return obj;
    }
    
}
