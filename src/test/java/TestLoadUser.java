import DataManager.FileFacade;
import UserOption.User;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

public class TestLoadUser {

    public static void main(String[] args) {
        FileFacade fileFacade = new FileFacade();
        JsonArray jsonArray = fileFacade.loadUsers();

        for(JsonElement jsonElement : jsonArray) {
            System.out.println(jsonElement.toString());
        }
    }


    }

