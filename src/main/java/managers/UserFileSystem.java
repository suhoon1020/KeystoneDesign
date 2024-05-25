package managers;


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import user.inventoryItem.Item;
import user.userprivacy.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class UserFileSystem {

    public static final String USER_FILE = "users.json";
    private static List<User> users;
    private static List<Item> items =
    private static UserFileSystem userFileSystem = new UserFileSystem();


    public static UserFileSystem getUserFileSystem() {
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

    public User getUserById(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) 
                return user;
        }

        return null;
    }

    public User getUserByName(String name) {
        for (User user : users) {
            if (user.getName().equals(name)) 
                return user;
        }

        return null;
    }

    public List<User> getUserList() {
        return users;
    }

    public Boolean putUser(User newUser) {
        if (getUserById(newUser.getId()) == null){
            users.add(newUser);
            saveInfosToFile();
            return true;
        }
            return false;
    }


    public Boolean updateUser(String id, User user) {
        for (int i = 0; i < users.size(); ++i) {
            if (users.get(i).getId().equals(id)) {
                users.set(i, user);
                saveInfosToFile();
                return true;
            }
        }
        return false;
    }

    public Boolean deleteUser(String id) {
        for (int i = 0; i < users.size(); ++i) {
            if (users.get(i).getId().equals(id)) {
                users.remove(i);
                saveInfosToFile();
                return true;
            }
        }
        return false;
    }

    public Boolean checkItemByName(User user, String name) {
        for (Item item : user.getItemList()) {
            if (item.getName().equals(name))
                return true;
        }
        return false;
    }

    //유저한테 아이템 생성?
    public boolean addItem(String userID,Item newItem){
        User user = getUserById(userID);

        if (checkItemByName(user,newItem.getName())) {
            return false;
        }

        user.getItemList().add(newItem);
        updateUser(userID,user);
        return true;
    }



    public boolean updateItem(String name, Item item){
        for (int i = 0; i < itemList.size(); ++i) {
            if (itemList.get(i).getName().equals(name)) {
                itemList.set(i, item);
                return true;
            }
        }
        return false;
    }

    public Boolean deleteItem(String name) {
        for (int i = 0; i < itemList.size(); ++i) {
            if (itemList.get(i).getName().equals(name)) {
                itemList.remove(i);
                return true;
            }
        }
        return false;
    }
}