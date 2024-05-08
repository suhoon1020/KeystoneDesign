package UserOption;

public class User {

    private String userID;
    private String userPW;
    private String userName;
    private String userPhoneNum;

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
    }

    //빌더 세팅
    public static class UserBuilder {
        private String userID;
        private String userPW;
        private String userName;
        private String userPhoneNum;


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

        public User build() {
            return new User(this);
        }
    }




}
