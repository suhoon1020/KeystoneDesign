package DataManager;

import UserOption.User;

import javax.swing.*;
import java.util.List;

public class Utility {

    private static List<User> users = FileFacade.getFacade().getUsersList();

    public static Boolean isRightID(String ID) {
        for (User user : users) {
            if (user.getUserID().equals(ID))
                return true;
        }

        return false;
    }

    public static Boolean isRightPW(String PW) {
        for (User user : users) {
            if (user.getUserPW().equals(PW))
                return true;
        }

        return false;
    }

    public static Boolean isRightName(String name) {
        for (User user : users) {
            if (user.getUserName().equals(name))
                return true;
        }

        return false;
    }

    public static Boolean isRightPhoneNum(String phoneNum) {
        for (User user : users) {
            if (user.getUserPhoneNum().equals(phoneNum))
                return true;
        }

        return false;
    }

    public static Boolean findPW(String ID, String name, String phoneNumber) {

        if (isRightID(ID) && isRightName(name) && isRightPhoneNum(phoneNumber)) {
            return true;
        } else {
            return false;
        }
    }

}
