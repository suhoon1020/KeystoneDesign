package Items;

import org.json.simple.JSONObject;

public interface Item {
    Item CreateItem();
    JSONObject getJsonObject();
}