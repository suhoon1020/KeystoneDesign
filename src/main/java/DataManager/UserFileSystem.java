package DataManager;


import UserOption.User;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class UserFileSystem {

    public static final String USER_FILE = "users.json";
    private static List<User> Users;

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
        if (!Files.exists(Paths.get(USER_FILE))) {
            Users = new ArrayList<>();
        } else {
            try {
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapter(User.class, new UserDeserializer());

                Gson gson = gsonBuilder.create();
                Reader reader = new FileReader(USER_FILE);
                JsonElement jsonElement = JsonParser.parseReader(reader);

                Users = gson.fromJson(jsonElement, new TypeToken<List<User>>() {}.getType());
            } catch (IOException err) {
                System.err.println(err);
            }
        }
    }

    public User getUser(String id) {

        for (User user : Users) {
            if (user.getUserID().equals(id)) return user;
        }

        // 찾는값이 없다면
        return null;
    }

    public List<User> getUsersList() {
        return Users;
    }

    public Boolean putUser(User newUser) {
        if (isExistID(newUser.getUserID())) {
            return false;
        }
        Users.add(newUser);
        return true;
    }

    public Boolean isExistID(String id) {
        for (User user : Users) {
            if (user.getUserID().equals(id))
                return true;
        }
        return false;
    }

    public Boolean updateUser(String id, User user) {
        for (int i = 0; i < Users.size(); ++i) {
            if (Users.get(i).getUserID().equals(id)) {
                Users.set(i, user);
                return true;
            }
        }
        return false;
    }

    public Boolean deleteUser(String id) {
        for (int i = 0; i < Users.size(); ++i) {
            if (Users.get(i).getUserID().equals(id)) {
                Users.remove(i);
                return true;
            }
        }
        return false;
    }

}