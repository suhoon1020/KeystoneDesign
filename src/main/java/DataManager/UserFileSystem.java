package DataManager;


import UserOption.User;
import com.google.gson.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


public class UserFileSystem {

    public static final String USER_FILE = "users.json";
    private JSONArray Users;

    public void saveInfosToFile() {
        FileWriter fw;

        try{
            fw = new FileWriter(USER_FILE);

            fw.write(Users.toJSONString());

            fw.close();
        } catch(IOException err){
            System.err.println(err);
        }
    }


    public void loadInfosFromFile() {

        Users = new JSONArray();
        Reader reader;
        JSONParser parser = new JSONParser();

        try{
            reader = new FileReader(USER_FILE);
            Users = (JSONArray)parser.parse(reader);

        } catch(IOException err){
            System.err.println(err);
        } catch(ParseException err){
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

    public Boolean putUser(JSONObject o) {

        String ID = (String)o.get("ID");

        for (Object obj : Users) {
            JSONObject jsonObject = (JSONObject) obj;

            // 아이디가 같은 값이 있다면 중단
            if (ID.equals(jsonObject.get("ID"))) return false;
        }

        Users.add(o);
        return true;
    }

    public void deleteUsersInfo(User user) {
        //TODO : 유저 삭제
    }

    public void updateUsersInfo(User user) {
        //TODO 유저 업뎃
    }

}