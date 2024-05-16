import DataManager.FileFacade;
import ItemsManager.Item;
import ItemsManager.ItemFactory;
import UserOption.User;

public class AuctionTEST {
    public static void main(String[] args) {
        Item test1 = new ItemFactory().createItem("Weapon");

        FileFacade.getFacade().putItem(test1);

        User test2 = new User.UserBuilder()
                .ID("suhoon")
                .PW("1020")
                .name("심수훈")
                .phone("010151124312")
                .build();

        FileFacade.getFacade().putUser(test2);;
        FileFacade.getFacade().saveUsers();
    }
}
