package user;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class UserFileSystem {
    public static final String USER_FILE = "users.json";

    private static List<User> userList;

    private static UserFileSystem userFileSystem;

    public static UserFileSystem getUserFileSystem() {
        if(userFileSystem == null)
            userFileSystem = new UserFileSystem();
        return userFileSystem;
    }

    private UserFileSystem() {
        loadInfosFromFile();
    }

    public void saveInfosToFile() {
        FileWriter fw;
        Gson gson = new Gson();
        try {
            fw = new FileWriter(USER_FILE);

            String usersString = gson.toJson(userList);

            fw.write(usersString);

            fw.close();
        } catch (IOException err) {
            System.err.println(err);
        }
    }

    public void loadInfosFromFile() {
        if (!Files.exists(Paths.get(USER_FILE))) {
            userList = new ArrayList<>();
        } else {
            try {
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapter(User.class, new UserDeserializer());

                Gson gson = gsonBuilder.create();
                Reader reader = new FileReader(USER_FILE);
                JsonElement jsonElement = JsonParser.parseReader(reader);

                userList = gson.fromJson(jsonElement, new TypeToken<List<User>>() {}.getType());
            } catch (IOException err) {
                System.err.println(err);
            }
        }
    }

    public List<User> getUserList() {
        return userList;
    }

    public User getUserById(String id) {
        for (User user : userList) {
            if (user.getId().equals(id))
                return user;
        }
        return null;
    }

    public List<User> getUsersByName(String name) {
        List<User> resultUserList = new ArrayList<>();

        for (User user : userList) {
            if (user.getName().equals(name)){
                resultUserList.add(user);
            }
        }
        
        return resultUserList;
    }
}