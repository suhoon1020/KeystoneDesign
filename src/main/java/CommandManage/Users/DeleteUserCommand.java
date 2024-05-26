package commandManage.users;

import commandManage.Command;
import managers.UserFileSystem;
import user.User;

import java.util.List;

import javax.swing.*;

public class DeleteUserCommand implements Command {
    String userId;

    public DeleteUserCommand(String userId) {
        this.userId = userId;

    }

    @Override
    public void execute() {
        List<User> users = UserFileSystem.getUserFileSystem().getUserList();
        
        for (int i = 0; i < users.size(); ++i) {
            if (users.get(i).getId().equals(userId)) {
                users.remove(i);
                UserFileSystem.getUserFileSystem().saveInfosToFile();
                JOptionPane.showMessageDialog(null, userId + "회원님의 정보가 삭제되었습니다");
                return;
            }
        }
        JOptionPane.showMessageDialog(null,"해당 유저를 찾을 수 없습니다");
    }
}
