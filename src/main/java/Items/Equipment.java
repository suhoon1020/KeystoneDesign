package Items;

import org.json.simple.JSONObject;

public class Equipment implements Item{
    String type;
    int damage;

    @Override
    public JSONObject ToJsonObj() {
        JSONObject obj = new JSONObject();

        obj.put("type", type);
        obj.put("damage", damage);

        return obj;
    }
    
}
