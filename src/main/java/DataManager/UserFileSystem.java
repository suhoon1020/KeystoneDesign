package DataManager;


import UserOption.User;
import com.google.gson.*;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


public class UserFileSystem {

    public static final String USER_FILE = "users.json";




    public void saveInfosToFile(User user) {
        Gson gson = new Gson();
        JsonArray jsonArray = loadInfosFromFile();

        // 사용자 정보를 JsonArray에 추가
        JsonElement userJsonElement = gson.toJsonTree(user); //toJsonTree = Java 객체를 jsonElement 객체로 변환시켜준다
        jsonArray.add(userJsonElement);
        String jsonString = gson.toJson(jsonArray); // 그냥 쓰면 트리형식으로 나오지 않음 , String으로 변환시켜야 나옴

        // 파일에 쓰기
        try (FileWriter fileWriter = new FileWriter(USER_FILE)) {
            fileWriter.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public JsonArray loadInfosFromFile() {
        JsonArray jsonArray = new JsonArray();
        if (Files.exists(Paths.get(USER_FILE))) {
            try (FileReader fileReader = new FileReader(USER_FILE)) {
                JsonElement jsonElement = JsonParser.parseReader(fileReader);
                jsonArray = jsonElement.getAsJsonArray();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return jsonArray;
    }

    public JsonObject getUser(String id){
        JsonArray jsonArray = loadInfosFromFile();
        JsonObject jsonObject = new JsonObject();
        for(JsonElement jsonElement : jsonArray) {
            if (jsonArray.getAsJsonObject().get("userID").getAsString().equals(id)){
                jsonObject = jsonElement.getAsJsonObject();
            }
        }

        return jsonObject;
    }

    public void deleteUsersInfo(User user) {
        //TODO : 유저 삭제
    }

    public void updateUsersInfo(User user) {
        //TODO 유저 업뎃
    }

}
