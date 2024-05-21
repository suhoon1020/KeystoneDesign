package UserOption;

import java.util.ArrayList;
import java.util.List;

import ItemsManager.Item;

public class User {
    private String userID;
    private String userPW;
    private String userName;
    private String userPhoneNum;
    private int userGold;


    private List<Item> userItemList;

    public String getUserName() {
        return userName;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserPW() {
        return userPW;
    }

    public String getUserPhoneNum() {
        return userPhoneNum;
    }

    public int getGold(){
        return userGold;
    }

    public void setUserGold(int userGold){
        this.userGold = userGold;
    }


    public List<Item> getItems(){
        return userItemList;
    }

    public String[] getData(){
        return new String[]{userID, userPW, userName, userPhoneNum, Integer.toString(userGold)};
    }

    @Override
    public String toString() {
        return userID + userPW + userName + userPhoneNum;
    }

    //Product
    private User(UserBuilder builder) {
        this.userID = builder.userID;
        this.userPW = builder.userPW;
        this.userName = builder.userName;
        this.userPhoneNum = builder.userPhoneNum;
        this.userGold = builder.userGold;
        
        if(builder.userItemList == null)
            this.userItemList = new ArrayList<Item>();
        else
            this.userItemList = builder.userItemList;
    }

    //빌더 세팅
    public static class UserBuilder {
        private String userID;
        private String userPW;
        private String userName;
        private String userPhoneNum;
        private int userGold;
        private List<Item> userItemList;

        public UserBuilder ID(String userID) {
            this.userID = userID;
            return this;
        }

        public UserBuilder PW(String userPW) {
            this.userPW = userPW;
            return this;
        }

        public UserBuilder name(String userName) {
            this.userName = userName;
            return this;
        }

        public UserBuilder phone(String userPhoneNum) {
            this.userPhoneNum = userPhoneNum;
            return this;
        }

        public UserBuilder gold(int userGold){
            this.userGold = userGold;
            return this;
        }

        public UserBuilder itemList(List<Item> userItemList){
            this.userItemList = userItemList;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
