package managers;


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import user.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class UserFileSystem {
    public static final String USER_FILE = "users.json";

    private static List<User> users;

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

            String usersString = gson.toJson(users);

            fw.write(usersString);

            fw.close();
        } catch (IOException err) {
            System.err.println(err);
        }
    }

    public void loadInfosFromFile() {
        if (!Files.exists(Paths.get(USER_FILE))) {
            users = new ArrayList<>();
        } else {
            try {
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapter(User.class, new UserDeserializer());

                Gson gson = gsonBuilder.create();
                Reader reader = new FileReader(USER_FILE);
                JsonElement jsonElement = JsonParser.parseReader(reader);

                users = gson.fromJson(jsonElement, new TypeToken<List<User>>() {}.getType());
            } catch (IOException err) {
                System.err.println(err);
            }
        }
    }

    public List<User> getUserList() {
        return users;
    }

    public User getUserById(String id) {
        for (User user : users) {
            if (user.getId().equals(id))
                return user;
        }
        return null;
    }

    public User getUserByName(String name) {
        for (User user : users) {
            if (user.getId().equals(name))
                return user;
        }
        return null;
    }
}