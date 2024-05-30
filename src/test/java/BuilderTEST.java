import java.util.ArrayList;
import java.util.List;

import itemInfos.Item;
import itemInfos.ItemBuilder;
import user.InventoryItem;
import user.User;

public class BuilderTEST {
    public static void main(String[] args) {

        /*
         *      아이템 빌더 적용 코드
         */

        Item equipment = new ItemBuilder()
            .type("Equipment")
            .name("equipmentTEST")
            .grade("Common")
            .desc("NONE")
            .option1(10)
            .build();

        Item material = new ItemBuilder()
            .type("Material")
            .name("materialTEST")
            .grade("Uncommon")
            .desc("NONE")
            .build();

        Item potion = new ItemBuilder()
            .type("Potion")
            .name("potionTEST")
            .grade("Eqic")
            .desc("NONE")
            .option1(10)
            .build();

        Item weapon = new ItemBuilder()
            .type("Weapon")
            .name("weaponTEST")
            .grade("Legendary")
            .desc("NONE")
            .option1(10)
            .build();

        /*
         *          유저 빌더 적용 코드
         */

        User user1 = new User.UserBuilder()
            .id("userID")
            .password("userPW")
            .name("userName")
            .phoneNumber("userPhoneNum")
            .isAdmin(false)
            .gold(1000)
            .build();

        // 유저 2에 초기 아이템 설정을 하기 위한 인벤토리 아이템 초기화
        List<InventoryItem> userItems = new ArrayList<InventoryItem>();

        InventoryItem userItem1 = new InventoryItem(weapon, 1);
        userItems.add(userItem1);
        InventoryItem userItem2 = new InventoryItem(potion, 1);
        userItems.add(userItem2);

        User user2 = new User.UserBuilder()
            .id("userID")
            .password("userPW")
            .name("userName")
            .phoneNumber("userPhoneNum")
            .isAdmin(false)
            .gold(1000)
            .itemList(userItems)
            .build();


        // 출력
        System.out.println("장비 타입 : " + equipment.getType());
        System.out.println("재료 이름 : " + material.getName());
        System.out.println("포션 등급 : " + potion.getGrade());
        System.out.println("무기 설명 : " + weapon.getDesc());

        System.out.println("유저1 골드 : " + user1.getGold());
        for(InventoryItem inventoryItem : user2.getItemList()){
            System.out.print("아이템 : ");
            for(String iteminfo : inventoryItem.getListData())
                System.out.print(iteminfo + ", ");
            System.out.println();
        }
    }
}
