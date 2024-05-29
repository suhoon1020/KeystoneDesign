package commandManage.users;

import commandManage.Command;
import itemInfos.ItemFileSystem;
import user.User;
import user.UserFileSystem;

import java.util.List;

import javax.swing.*;

public class DeleteUserCommand implements Command {
    User user;

    public DeleteUserCommand(User user) {
        this.user = user;

    }

    @Override
    public void execute() {
        List<User> users = UserFileSystem.getUserFileSystem().getUserList();
        String userId = user.getId();

        for (int i = 0; i < users.size(); ++i) {
            if (users.get(i).getId().equals(userId)) {
                users.remove(i);
                
                ItemFileSystem.getItemFileSystem().removeObserver(user);

                UserFileSystem.getUserFileSystem().saveInfosToFile();
                JOptionPane.showMessageDialog(null, userId + " 회원님의 정보가 삭제되었습니다");
                return;
            }
        }
        JOptionPane.showMessageDialog(null,"해당 유저를 찾을 수 없습니다");
    }
}
