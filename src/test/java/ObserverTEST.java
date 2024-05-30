import commandManage.Invoker;
import commandManage.inventoryItems.CreateInvItemCommand;
import commandManage.items.CreateItemCommand;
import commandManage.items.DeleteItemCommand;
import commandManage.items.UpdateItemCommand;
import commandManage.users.CreateUserCommand;
import commandManage.users.DeleteUserCommand;
import itemInfos.Item;
import itemInfos.ItemBuilder;
import itemInfos.ItemFileSystem;
import user.User;
import user.UserFileSystem;

public class ObserverTEST {
    public static void main(String[] args) {
        Invoker invoker = new Invoker();

        // 임시 아이템 정보 등록
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

        System.out.println("생성한 아이템 설명 : " + ItemFileSystem.getItemFileSystem().getItemByName("itemTEST").getDesc());

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

        // 유저 아이템 생성
        CreateInvItemCommand createInvItemCommand = new CreateInvItemCommand(userCreate, itemCreate.clone(), 10);
        invoker.setCommand(createInvItemCommand);
        invoker.run();

        System.out.println("생성한 유저 아이템 설명 : " + UserFileSystem.getUserFileSystem().getUserById("userID").getItemByName("itemTEST").getDesc());

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

        System.out.println("수정한 아이템 설명 : " + ItemFileSystem.getItemFileSystem().getItemByName("itemTEST").getDesc());
        System.out.println("수정된 유저 아이템 설명 : " + UserFileSystem.getUserFileSystem().getUserById("userID").getItemByName("itemTEST").getDesc());
        
        // 유저 삭제
        DeleteUserCommand deleteUserCommand = new DeleteUserCommand(userCreate);
        invoker.setCommand(deleteUserCommand);
        invoker.run();

        // 아이템 정보 삭제
        DeleteItemCommand deleteItemCommand = new DeleteItemCommand(itemUpdate);
        invoker.setCommand(deleteItemCommand);
        invoker.run();
    }
    
}
