package auctionData;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import itemInfos.Item;
import itemInfos.ItemBuilder;

public class TradeItemDeserializer implements JsonDeserializer<TradeItem> {
    @Override
    public TradeItem deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        
        String userID = jsonObject.get("userId").getAsString();

        JsonObject item = jsonObject.getAsJsonObject("item");
        String type = item.getAsJsonObject().get("type").getAsString();
        String name = item.getAsJsonObject().get("name").getAsString();
        String grade = item.getAsJsonObject().get("grade").getAsString();
        String desc = item.getAsJsonObject().get("desc").getAsString();
        int op1 = 0;

        switch (type) {
            case "Equipment":
                op1 = item.getAsJsonObject().get("defence").getAsInt();
                break;
            case "Material":
                // 아이템 옵션 없음
                break;
            case "Potion":
                op1 = item.getAsJsonObject().get("effect").getAsInt();
                break;
            case "Weapon":
                op1 = item.getAsJsonObject().get("damage").getAsInt();
                break;
            default:
                break;
        }

        Item i = new ItemBuilder()
                .type(type)
                .name(name)
                .desc(desc)
                .grade(grade)
                .option1(op1)
                .build();

        int count = jsonObject.getAsJsonObject().get("count").getAsInt();
        int price = jsonObject.get("price").getAsInt();

        return new TradeItem(userID, i, count, price);
    }
}