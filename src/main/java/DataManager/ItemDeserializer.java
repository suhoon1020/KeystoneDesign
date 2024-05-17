package DataManager;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import ItemsManager.Item;
import ItemsManager.ItemFactory;

public class ItemDeserializer implements JsonDeserializer<Item> {
    @Override
    public Item deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        String type = jsonObject.get("type").getAsString();
        String name = jsonObject.get("name").getAsString();
        String grade = jsonObject.get("grade").getAsString();
        String desc = jsonObject.get("desc").getAsString();
        int price = jsonObject.get("price").getAsInt();
        int op1 = 0;

        switch (type) {
            case "Equipment":
                op1 = jsonObject.get("defence").getAsInt();
                break;
            case "Material":
                // 아이템 옵션 없음
                break;
            case "Potion":
                op1 = jsonObject.get("effect").getAsInt();
                break;
            case "Weapon":
                op1 = jsonObject.get("damage").getAsInt();
                break;
            default:
                break;
        }

        return new ItemFactory.ItemBuilder()
            .type(type)
            .name(name)
            .desc(desc)
            .grade(grade)
            .price(price)
            .option1(op1)
            .build();
    }
}