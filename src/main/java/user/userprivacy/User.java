package user.userprivacy;

import java.util.ArrayList;
import java.util.List;

import managers.UserFileSystem;
import user.inventoryItem.Item;

public class User {
    private String id;
    private String password;
    private String name;
    private String phoneNumber;
    private int gold;
    private List<Item> itemList;


    public List<Item> getItemList() {
        return itemList;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getGold(){
        return gold;
    }

    public void setGold(int gold){
        this.gold = gold;
    }

    public String[] getListData(){
        return new String[]{id, password, name, phoneNumber, Integer.toString(gold)};
    }

    //Product
    private User(UserBuilder builder) {
        this.id = builder.ID;
        this.password = builder.password;
        this.name = builder.name;
        this.phoneNumber = builder.phoneNumber;
        this.gold = builder.gold;
        
        if(builder.itemList == null)
            this.itemList = new ArrayList<Item>();
        else
            this.itemList = builder.itemList;
    }

    //빌더 세팅
    public static class UserBuilder {
        private String ID;
        private String password;
        private String name;
        private String phoneNumber;
        private int gold;
        private List<Item> itemList;

        public UserBuilder ID(String ID) {
            this.ID = ID;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserBuilder gold(int gold){
            this.gold = gold;
            return this;
        }

        public UserBuilder itemList(List<Item> itemList){
            this.itemList = itemList;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public static User getUserById(String id) {
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
            UserFileSystem.getUserFileSystem().saveInfosToFile();
            return true;
        }
        return false;
    }


    public Boolean updateUser(String id, User user) {
        for (int i = 0; i < users.size(); ++i) {
            if (users.get(i).getId().equals(id)) {
                users.set(i, user);
                UserFileSystem.getUserFileSystem().saveInfosToFile();
                return true;
            }
        }
        return false;
    }

    public Boolean deleteUser(String id) {
        for (int i = 0; i < users.size(); ++i) {
            if (users.get(i).getId().equals(id)) {
                users.remove(i);
                UserFileSystem.getUserFileSystem().saveInfosToFile();
                return true;
            }
        }
        return false;
    }

    /*
             User InvItem 관리 시스템
     */

    public boolean addItem(User user, Item addItem){
        List<Item> userItemList = user.getItemList();
        String addItemName = addItem.getName();
        /*
        유저의 아이템 이름이 중복이 되면 추가 x
         */
        for(int i = 0; i < userItemList.size(); ++i){
            if(userItemList.get(i).getName().equals(addItemName)){
                return false;
            }
        }
        /*
        그게아니면 그냥 add
         */
        userItemList.add(addItem);
        return true;
    }

    public void updateCount(User user, Item item, int count){
        List<Item> userItemList = user.getItemList();
        String addItemName = item.getName();
        /*
        additem이 이미 있으면
         */
        for(int i = 0; i < userItemList.size(); ++i){
            if(userItemList.get(i).getName().equals(addItemName)){
                item.setCount(count);
                userItemList.set(i, item);
                return;
            }
        }

    }

    public boolean updateItem(User user, Item updateItem){
        List<Item> userItemList = user.getItemList();
        String updateItemName = updateItem.getName();

        for(int i = 0; i < userItemList.size(); ++i){
            if(userItemList.get(i).getName().equals(updateItemName)){
                userItemList.set(i, updateItem);
                return true;
            }
        }

        return false;
    }

    public boolean deleteItem(User user, String deleteItemName){
        List<Item> userItemList = user.getItemList();

        for(int i = 0; i < userItemList.size(); ++i){
            if(userItemList.get(i).getName().equals(deleteItemName)){
                userItemList.remove(i);
                return true;
            }
        }

        return false;
    }
}
