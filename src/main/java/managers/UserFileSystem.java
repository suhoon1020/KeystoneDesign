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

    public boolean updateItem(String userId, Item updateItem){
        User user = getUserById(userId);
        String findItemName = updateItem.getName();

        if(user != null){
            if(updateItem.getCount() > 0){
                // 아이템 플러스
                List<Item> userItemList = user.getItemList();

                for(int i = 0; i < userItemList.size(); i++){
                    if(userItemList.get(i).getName().equals(findItemName)){
                        // 아이템 정보 있음
                        userItemList.get(i).setCount(updateItem.getCount());
                        return true;
                    }
                }
                // 아이템 생성 후 리스트 추가
                userItemList.add(updateItem);
                return true;
            }
            else if(updateItem.getCount() < 0){
                // 아이템 마이너스
                List<Item> userItemList = user.getItemList();

                for(int i = 0; i < userItemList.size(); ++i){
                    if(userItemList.get(i).getName().equals(findItemName)){
                        // 아이템 정보 있음
                        if(userItemList.get(i).setCount(updateItem.getCount())){
                            if(userItemList.get(i).getCount() == 0){
                                // 아이템 사라짐
                                userItemList.remove(i);
                                return true;
                            }
                            // 감소 완료 리턴
                            return true;
                        }
                        else{
                            // 아이템 수량부족
                            return false;
                        }
                    }
                        
                }
                // 아이템 정보 없음 마이너스 불가
                return false;
            }
            else{
                // 무의미
                return false;
            }
        }
        else{
            // 유저 정보 없음
            return false;
        }

    }
}