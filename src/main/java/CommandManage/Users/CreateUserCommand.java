package CommandManage.Users;

import CommandManage.Command;
import managers.UserFileSystem;
import user.User;

import javax.swing.*;

public class CreateUserCommand implements Command {
    User user;


    public CreateUserCommand(User user) {
        this.user = user;
    }

    @Override
    public void execute() {
        if (UserFileSystem.getUserFileSystem().getUserById(user.getId()) == null){
            UserFileSystem.getUserFileSystem().getUserList().add(user);
            UserFileSystem.getUserFileSystem().saveInfosToFile();
            JOptionPane.showMessageDialog(null,"유저 생성에 성공하였습니다");
            return;
        }

        JOptionPane.showMessageDialog(null,"중복된 ID가 있습니다");
    }
}
