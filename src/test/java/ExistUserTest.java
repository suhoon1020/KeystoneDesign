import DataManager.FileFacade;

public class ExistUserTest {

    public static void main(String[] args) {
        if(FileFacade.getFacade().isExistingUser("2")){
            System.out.println("Exist User");
        }
    }

}
