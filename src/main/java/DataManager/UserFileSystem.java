package DataManager;


import UserOption.User;
import com.google.gson.*;
import com.google.gson.internal.bind.JsonTreeReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


public class UserFileSystem {

    public static final String USER_FILE = "users.json";
    private JsonArray Users;

    public void saveInfosToFile() {
        FileWriter fw;
        Gson gson = new Gson();
        try {
            fw = new FileWriter(USER_FILE);

            String usersString = gson.toJson(Users);

            fw.write(usersString);

            fw.close();
        } catch (IOException err) {
            System.err.println(err);
        }
    }


    public void loadInfosFromFile() {

        Users = new JsonArray();
        Reader reader;


        try {
            reader = new FileReader(USER_FILE);
            JsonElement jsonElement = JsonParser.parseReader(reader);
            Users = jsonElement.getAsJsonArray();
        } catch (IOException err) {
            System.err.println(err);
        } catch (ParseException err) {
            System.err.println(err);
        }

    }

    public JSONObject getUser(String id) {
        for (Object obj : Users) {
            JSONObject jsonObject = (JSONObject) obj;

            // 오브젝트를 찾았다면
            if (id.equals(jsonObject.get("ID"))) return jsonObject;
        }

        // 찾는값이 없다면
        return null;
    }

    public Boolean putUser(User user) {

        String ID = user.getUserID();
        Gson gson = new Gson();
        JSONObject jsonObject;

        for (Object obj : Users) {
            jsonObject = (JSONObject) obj;

            // 아이디가 같은 값이 있다면 중단
            if (ID.equals(jsonObject.get("ID"))) return false;
        }

        Users.add(jsonObject);
        return true;
    }

    public void deleteUsersInfo(User user) {
        //TODO : 유저 삭제
    }

    public void updateUsersInfo(User user) {
        //TODO 유저 업뎃
    }

}