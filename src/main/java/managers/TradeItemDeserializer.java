package managers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import auctionData.TradeItem;
import user.inventoryItem.Item;
import user.inventoryItem.ItemBuilder;



public class TradeItemDeserializer implements JsonDeserializer<TradeItem> {
    @Override
    public TradeItem deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        
        String userID = jsonObject.get("userId").getAsString();
        int price = jsonObject.get("price").getAsInt();

        JsonObject jsonItem = jsonObject.getAsJsonObject("auctionItem");

        String type = jsonItem.getAsJsonObject().get("type").getAsString();
        String name = jsonItem.getAsJsonObject().get("name").getAsString();
        String grade = jsonItem.getAsJsonObject().get("grade").getAsString();
        String desc = jsonItem.getAsJsonObject().get("desc").getAsString();
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
                .count(count)
                .option1(op1)
                .build();

        return new TradeItem(userID, price, item);
    }
}