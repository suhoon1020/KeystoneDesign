package DataManager;


import UserOption.User;
import com.google.gson.*;



import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;




public class UserManager {

    public static final String USER_FILE = "users.json";


    public void saveInfosToFile(User user) {
        Gson gson = new Gson();
        JsonArray jsonArray = loadInfosFromFile();

        // 사용자 정보를 JsonArray에 추가
        JsonElement userJsonElement = gson.toJsonTree(user);
        jsonArray.add(userJsonElement);
        String jsonString = gson.toJson(jsonArray);

        // 파일에 쓰기
        try (FileWriter fileWriter = new FileWriter(USER_FILE)) {
            fileWriter.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public JsonArray loadInfosFromFile() {
        JsonArray jsonArray=new JsonArray();
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


    public void deleteUsersInfo(User user) {
        //TODO: 유저 삭제
    }

    public void updateUsersInfo(User user) {
        //TODO 유저 업뎃
    }

}
