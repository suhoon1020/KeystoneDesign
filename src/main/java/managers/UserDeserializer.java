package managers;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import Item.Item;
import Item.ItemBuilder;
import user.InventoryItem;
import user.User;


public class UserDeserializer implements JsonDeserializer<User> {
    @Override
    public User deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        String userID = jsonObject.get("id").getAsString();
        String userPW = jsonObject.get("password").getAsString();
        String userName = jsonObject.get("name").getAsString();
        String userPhoneNum = jsonObject.get("phoneNumber").getAsString();
        int userGold = jsonObject.get("gold").getAsInt();

        JsonArray jsonItemList = jsonObject.getAsJsonArray("itemList");
        List<InventoryItem> useriItemList = new ArrayList<InventoryItem>();

        for (JsonElement jsonItem : jsonItemList) {
            JsonObject inventoryItemInfo = jsonObject.getAsJsonObject("item");
            String type = inventoryItemInfo.getAsJsonObject().get("type").getAsString();
            String name = inventoryItemInfo.getAsJsonObject().get("name").getAsString();
            String grade = inventoryItemInfo.getAsJsonObject().get("grade").getAsString();
            String desc = inventoryItemInfo.getAsJsonObject().get("desc").getAsString();
            int op1 = 0;
    
            switch (type) {
                case "Equipment":
                    op1 = inventoryItemInfo.getAsJsonObject().get("defence").getAsInt();
                    break;
                case "Material":
                    // 아이템 옵션 없음
                    break;
                case "Potion":
                    op1 = inventoryItemInfo.getAsJsonObject().get("effect").getAsInt();
                    break;
                case "Weapon":
                    op1 = inventoryItemInfo.getAsJsonObject().get("damage").getAsInt();
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

            int count = jsonItem.getAsJsonObject().get("count").getAsInt();

            InventoryItem inventoryItem = new InventoryItem(item, count);

            useriItemList.add(inventoryItem);
        }

        return new User.UserBuilder()
                .ID(userID)
                .password(userPW)
                .name(userName)
                .phoneNumber(userPhoneNum)
                .gold(userGold)
                .itemList(useriItemList)
                .build();
    }
}