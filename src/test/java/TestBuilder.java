import ItemsManager.Item;
import ItemsManager.ItemFactory;
import UserOption.User;


public class TestBuilder {


    public static void main(String[] args) {
        User user = new User.UserBuilder()
                .ID("suhoon")
                .PW("1020")
                .name("심수훈")
                .phone("010151124312")
                .build();
        System.out.println(user.toString());

        // 아이템 테스트
        Item weapon1 = new ItemFactory().CreateItem("Weapon");
        System.out.println(weapon1.getJsonObject());
    }


}
