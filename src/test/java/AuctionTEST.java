import java.io.File;

import DataManager.FileFacade;
import ItemsManager.Item;
import ItemsManager.ItemBuilder;
import UserOption.Inventory;
import UserOption.User;

public class AuctionTEST {
    public static void main(String[] args) {

        Item test1 = new ItemBuilder()
                .type("Weapon")
                .name("TEST1")
                .desc("THIS IS WEAPON")
                .grade("Common")
                .price(10000)
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
                .build();

        FileFacade.getFacade().putUser(test2);;
        FileFacade.getFacade().saveUsers();

        // Inventory test3 = new Inventory("shhoon");
        // test3.gainItem(test1);
        // FileFacade.getFacade().putInventory(test3);
        // FileFacade.getFacade().saveInventories();


        Inventory test3 = FileFacade.getFacade().getInventory("suhoon");

        if(test3 == null)
            System.out.println("없음");
        else
            System.out.println(test3.getItemList());
    }
}
