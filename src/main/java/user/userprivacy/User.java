package deu.cse.user.userprivacy;

import java.util.ArrayList;
import java.util.List;

import deu.cse.user.inventoryItem.Item;

public class User {
    private String ID;
    private String password;
    private String name;
    private String phoneNumber;
    private int gold;
    private List<Item> itemList;

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
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

    public List<Item> getItems(){
        return itemList;
    }

    public String[] getListData(){
        return new String[]{ID, password, name, phoneNumber, Integer.toString(gold)};
    }

    public void sell(){

    }

    public void buy(){

    }

    //Product
    private User(UserBuilder builder) {
        this.ID = builder.ID;
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
}
