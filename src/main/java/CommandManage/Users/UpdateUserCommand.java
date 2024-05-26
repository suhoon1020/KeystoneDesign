package CommandManage.Users;

import CommandManage.Command;
import managers.UserFileSystem;
import user.userprivacy.User;

import java.util.List;

import javax.swing.*;

public class UpdateUserCommand implements Command {
    User user;


    public UpdateUserCommand(User user) {
        this.user = user;
    }

    @Override
    public void execute() {
        List<User> users = UserFileSystem.getUserFileSystem().getUserList();

        for (int i = 0; i < users.size(); ++i) {
            if (users.get(i).getId().equals(user.getId())) {
                users.set(i, user);
                UserFileSystem.getUserFileSystem().saveInfosToFile();
                JOptionPane.showMessageDialog(null,"유저 정보가 수정되었습니다");
                return;
            }
        }
        JOptionPane.showMessageDialog(null,"해당 유저 정보를 찾을 수 없습니다");
    }
}
