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
import UserOption.User;

public class UserDeserializer implements JsonDeserializer<User> {
    @Override
    public User deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        
        String userID = jsonObject.get("userID").getAsString();
        String userPW = jsonObject.get("userPW").getAsString();
        String userName = jsonObject.get("userName").getAsString();
        String userPhoneNum = jsonObject.get("userPhoneNum").getAsString();
        int userGold = jsonObject.get("userGold").getAsInt();

        JsonArray jsonItemList = jsonObject.getAsJsonArray("userItemList");
        List<Item> useriItemList = new ArrayList<Item>();

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

            useriItemList.add(item);
        }

        return new User.UserBuilder()
                .ID(userID)
                .PW(userPW)
                .name(userName)
                .phone(userPhoneNum)
                .gold(userGold)
                .build();
    }
}