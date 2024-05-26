package managers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import Item.Item;
import Item.ItemBuilder;
import auctionData.TradeItem;

public class TradeItemDeserializer implements JsonDeserializer<TradeItem> {
    @Override
    public TradeItem deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        
        String userID = jsonObject.get("userName").getAsString();

        JsonObject Item = jsonObject.getAsJsonObject("item");
        String type = Item.getAsJsonObject().get("type").getAsString();
        String name = Item.getAsJsonObject().get("name").getAsString();
        String grade = Item.getAsJsonObject().get("grade").getAsString();
        String desc = Item.getAsJsonObject().get("desc").getAsString();
        int op1 = 0;

        switch (type) {
            case "Equipment":
                op1 = Item.getAsJsonObject().get("defence").getAsInt();
                break;
            case "Material":
                // 아이템 옵션 없음
                break;
            case "Potion":
                op1 = Item.getAsJsonObject().get("effect").getAsInt();
                break;
            case "Weapon":
                op1 = Item.getAsJsonObject().get("damage").getAsInt();
                break;
            default:
                break;
        }

        Item item = new ItemBuilder()
                .type(type)
                .name(name)
                .desc(desc)
                .grade(grade)
                .option1(op1)
                .build();

        int count = Item.getAsJsonObject().get("count").getAsInt();
        int price = jsonObject.get("price").getAsInt();

        return new TradeItem(userID, item, count, price);
    }
}