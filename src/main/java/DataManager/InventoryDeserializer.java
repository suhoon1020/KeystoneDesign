package DataManager;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import ItemsManager.Item;
import ItemsManager.ItemBuilder;
import UserOption.Inventory;

public class InventoryDeserializer implements JsonDeserializer<Inventory> {
    @Override
    public Inventory deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        String userID = jsonObject.get("userID").getAsString();
        int gold = jsonObject.get("gold").getAsInt();

        JsonArray jsonItemList = jsonObject.getAsJsonArray("itemList");
        List<Item> itemList = new ArrayList<Item>();

        for (JsonElement jsonItem : jsonItemList) {
            String type = jsonItem.getAsJsonObject().get("type").getAsString();
            String name = jsonItem.getAsJsonObject().get("name").getAsString();
            String grade = jsonItem.getAsJsonObject().get("grade").getAsString();
            String desc = jsonItem.getAsJsonObject().get("desc").getAsString();
            int price = jsonItem.getAsJsonObject().get("price").getAsInt();
            int count = jsonItem.getAsJsonObject().get("count").getAsInt();

            int op1 = 0;

            switch (type) {
                case "Equipment":
                    op1 = jsonItem.getAsJsonObject().get("defence").getAsInt();
                    break;
                case "Material":
                    // 아이템 옵션 없음
                    break;
                case "Potion":
                    op1 = jsonItem.getAsJsonObject().get("effect").getAsInt();
                    break;
                case "Weapon":
                    op1 = jsonItem.getAsJsonObject().get("damage").getAsInt();
                    break;
                default:
                    break;
            }
            Item item = new ItemBuilder()
                    .type(type)
                    .name(name)
                    .desc(desc)
                    .grade(grade)
                    .price(price)
                    .count(count)
                    .option1(op1)
                    .build();

            itemList.add(item);
        }

        return new Inventory(userID, gold, itemList);
    }
}