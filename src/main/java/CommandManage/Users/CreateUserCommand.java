package CommandManage.Users;

import CommandManage.Command;
import user.userprivacy.User;

import javax.swing.*;

public class CreateUserCommand implements Command {
    User user;


    public CreateUserCommand(User user) {
        this.user = user;
    }

    @Override
    public void execute() {
        if(user.putUser(user)){
            JOptionPane.showMessageDialog(null,"유저 생성에 성공하였습니다");
        }
        else{
            JOptionPane.showMessageDialog(null,"중복된 ID가 있습니다");
        }
    }
}
