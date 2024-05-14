package ItemsManager;

import java.util.HashMap;

import org.json.simple.JSONObject;

public class Weapon implements Item {
    JSONObject itemDetails;

    @Override
    public Item CreateItem() {
        HashMap<String, Object> weaponDetails = new HashMap<String, Object>();

        weaponDetails.put("ID", 0);
        weaponDetails.put("type", "Equipment");
        weaponDetails.put("desc", "");
        weaponDetails.put("price", 0);
        weaponDetails.put("count", 0);
        weaponDetails.put("attack", 0);

        itemDetails = new JSONObject(weaponDetails);

        return this;
    }

    @Override
    public JSONObject getJsonObject() {
        return itemDetails;
    }
}
