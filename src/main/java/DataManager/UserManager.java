package DataManager;


import UserOption.User;
import com.google.gson.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class UserManager {
    public static final String USER_FILE = "users.json";


    public void SaveInfosToFile(User user) {
        // TODO 파일 저장 시스템

        List<User> users = LoadInfosFromFile();
        Gson gson = new Gson();
        users.add(user);

        try (FileWriter fw = new FileWriter(USER_FILE,false)) {
            gson.toJson(users, fw);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<User> LoadInfosFromFile() {
        // TODO : 유저 파일 불러오기
        Gson gson = new Gson();
        List<User> userList = new ArrayList<>();
        try (FileReader reader = new FileReader(USER_FILE)) {
            JsonElement jsonElement = JsonParser.parseReader(reader);

            if(jsonElement.isJsonArray()) {
                JsonArray jsonArray = jsonElement.getAsJsonArray();
                for (JsonElement element : jsonArray) {
                    User user = gson.fromJson(element, User.class);
                    userList.add(user);
                }
            }
            else if(jsonElement.isJsonObject()) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                User user = gson.fromJson(jsonObject, User.class);
                userList.add(user);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public void deleteUsersInfo(User user) {
        //TODO: 유저 삭제
    }

    public void updateUsersInfo(User user) {
        //TODO 유저 업뎃
    }

}