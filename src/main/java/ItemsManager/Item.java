package ItemsManager;

import org.json.simple.JSONObject;

public interface Item {
    Item createItemInfos();
    JSONObject getJsonObject();
}