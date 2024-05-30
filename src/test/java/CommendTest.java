import commandManage.Invoker;
import commandManage.inventoryItems.CreateInvItemCommand;
import commandManage.inventoryItems.DeleteInvItemCommand;
import commandManage.inventoryItems.UpdateInvItemCommand;
import commandManage.items.CreateItemCommand;
import commandManage.items.DeleteItemCommand;
import commandManage.items.UpdateItemCommand;
import commandManage.users.CreateUserCommand;
import commandManage.users.DeleteUserCommand;
import commandManage.users.UpdateUserCommand;
import itemInfos.Item;
import itemInfos.ItemBuilder;
import itemInfos.ItemFileSystem;
import user.InventoryItem;
import user.User;
import user.UserFileSystem;

public class CommendTest {
    public static void main(String[] args) {
        Invoker invoker = new Invoker();

        // 유저 생성
        User userCreate = new User.UserBuilder()
            .id("userID")
            .password("userPW")
            .name("userName")
            .phoneNumber("userPhoneNum")
            .isAdmin(false)
            .gold(1000)
            .build();
        
        CreateUserCommand createUserCommand = new CreateUserCommand(userCreate);
        invoker.setCommand(createUserCommand);
        invoker.run();

        System.out.println("생성한 유저 이름 : " + UserFileSystem.getUserFileSystem().getUserById("userID").getName());

        // 유저 수정
        User userUpdate = new User.UserBuilder()
            .id("userID")
            .password("userPW")
            .name("userNameUpdate")
            .phoneNumber("userPhoneNum")
            .isAdmin(false)
            .gold(1000)
            .build();

        UpdateUserCommand updateUserCommand = new UpdateUserCommand(userUpdate);
        invoker.setCommand(updateUserCommand);
        invoker.run();

        System.out.println("수정한 유저 이름 : " + UserFileSystem.getUserFileSystem().getUserById("userID").getName());

        // 아이템 생성
        Item itemCreate = new ItemBuilder()
            .type("Equipment")
            .name("itemTEST")
            .grade("Common")
            .desc("NONE")
            .option1(10)
            .build();

        CreateItemCommand createItemCommand = new CreateItemCommand(itemCreate);
        invoker.setCommand(createItemCommand);
        invoker.run();

        System.out.println("생성한 아이템 이름 : " + ItemFileSystem.getItemFileSystem().getItemByName("itemTEST").getDesc());

        // 아이템 수정
        Item itemUpdate = new ItemBuilder()
            .type("Equipment")
            .name("itemTEST")
            .grade("Common")
            .desc("UPDATE NONE")
            .option1(10)
            .build();

        UpdateItemCommand updateItemCommand = new UpdateItemCommand(itemUpdate);
        invoker.setCommand(updateItemCommand);
        invoker.run();

        System.out.println("수정한 아이템 아름 : " + ItemFileSystem.getItemFileSystem().getItemByName("itemTEST").getDesc());


        // 유저 아이템 생성
        CreateInvItemCommand createInvItemCommand = new CreateInvItemCommand(userUpdate, itemUpdate.clone(), 10);
        invoker.setCommand(createInvItemCommand);
        invoker.run();

        System.out.println("생성한 유저 아이템 개수 : " + UserFileSystem.getUserFileSystem().getUserById("userID").getItemByName("itemTEST").getCount());

        // 유저 아이템 수정
        InventoryItem inventoryItemUpdate = new InventoryItem(itemUpdate, 5);

        UpdateInvItemCommand updateInvItemCommand = new UpdateInvItemCommand(userUpdate, inventoryItemUpdate);
        invoker.setCommand(updateInvItemCommand);
        invoker.run();

        System.out.println("수정한 유저 아이템 개수 : " + UserFileSystem.getUserFileSystem().getUserById("userID").getItemByName("itemTEST").getCount());
        
        // 유저 아이템 삭제
        boolean isUserItemExist = UserFileSystem.getUserFileSystem().getUserById("userID").getItemByName("itemTEST") != null;
        System.out.println("삭제 전 유저 아이템 존재 여부 : " + Boolean.toString(isUserItemExist));

        DeleteInvItemCommand deleteInvItemCommand = new DeleteInvItemCommand(userUpdate, "itemTEST");
        invoker.setCommand(deleteInvItemCommand);
        invoker.run();

        isUserItemExist = UserFileSystem.getUserFileSystem().getUserById("userID").getItemByName("itemTEST") != null;
        System.out.println("삭제 후 유저 아이템 존재 여부 : " + Boolean.toString(isUserItemExist));

        // 아이템 삭제
        boolean isItemExist = ItemFileSystem.getItemFileSystem().getItemByName("itemTEST") != null;
        System.out.println("삭제 전 아이템 존재 여부 : " + Boolean.toString(isItemExist));

        DeleteItemCommand deleteItemCommand = new DeleteItemCommand(itemUpdate);
        invoker.setCommand(deleteItemCommand);
        invoker.run();

        isItemExist = ItemFileSystem.getItemFileSystem().getItemByName("itemTEST") != null;
        System.out.println("삭제 후 아이템 존재 여부 : " + Boolean.toString(isItemExist));

        // 유저 삭제
        boolean isUserExist = UserFileSystem.getUserFileSystem().getUserById("userID") != null;
        System.out.println("삭제 전 유저 존재 여부 : " + Boolean.toString(isUserExist));

        DeleteUserCommand deleteUserCommand = new DeleteUserCommand(userUpdate);
        invoker.setCommand(deleteUserCommand);
        invoker.run();

        isUserExist = UserFileSystem.getUserFileSystem().getUserById("userID") != null;
        System.out.println("삭제 후 유저 존재 여부 : " + Boolean.toString(isUserExist));
    }
}
