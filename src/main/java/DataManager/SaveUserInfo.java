package DataManager;

import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class SaveUserInfo implements DataOption {
    //todo : 파일 저장 시스템
    public static final String USER_FILE = "users.json";

    public void saveInfo(String id, String pw, String name, String phoneNum) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("id", id);
        jsonObject.put("pw", pw);
        jsonObject.put("name", name);
        jsonObject.put("phoneNum", phoneNum);

        try (FileWriter jsonWriter = new FileWriter(USER_FILE)) {
            jsonWriter.write(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
