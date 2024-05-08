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
    }


}
