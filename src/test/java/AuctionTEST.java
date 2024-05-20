import DataManager.FileFacade;
import ItemsManager.Item;
import ItemsManager.ItemBuilder;
import UserOption.User;

public class AuctionTEST {
    public static void main(String[] args) {

        Item test1 = new ItemBuilder()
                .type("Weapon")
                .name("TEST1")
                .desc("THIS IS WEAPON")
                .grade("Common")
                .count(1)
                .option1(10)
                .build();

        FileFacade.getFacade().putItem(test1);
        FileFacade.getFacade().saveItems();

        User test2 = new User.UserBuilder()
                .ID("suhoon")
                .PW("1020")
                .name("심수훈")
                .phone("010151124312")
                .gold(100)
                .build();

        FileFacade.getFacade().putUser(test2);;
        FileFacade.getFacade().saveUsers();

    }
}
