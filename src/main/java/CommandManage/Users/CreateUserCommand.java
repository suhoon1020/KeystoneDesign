package commandManage.users;

import commandManage.Command;
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
            if(UserFileSystem.getUserFileSystem().getUserByName(user.getName()) == null){
                UserFileSystem.getUserFileSystem().getUserList().add(user);
                UserFileSystem.getUserFileSystem().saveInfosToFile();
                JOptionPane.showMessageDialog(null,"유저 생성에 성공하였습니다");
                return;
            }
            JOptionPane.showMessageDialog(null,"중복된 이름이 있습니다");
            return;
        }

        JOptionPane.showMessageDialog(null,"중복된 ID가 있습니다");
        return;
    }
}
