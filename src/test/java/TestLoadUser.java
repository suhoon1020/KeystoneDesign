import DataManager.FileFacade;
import UserOption.User;

import java.util.ArrayList;
import java.util.List;

public class TestLoadUser {

    public static void main(String[] args) {
        FileFacade fileFacade = new FileFacade();

        List<User> userList = new ArrayList<>();
        userList=fileFacade.loadUser();

        for(User user:userList){
            System.out.println(user.getUserID());
            }
        }
    }

