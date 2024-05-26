package CommandManage;

import managers.UserFileSystem;
import user.userprivacy.User;

import javax.swing.*;

public class DeleteUserCommand implements Command{
    User user;

    public DeleteUserCommand(User user) {
        this.user = user;

    }

    @Override
    public void execute() {
        if(user.deleteUser(user.getId())){
            JOptionPane.showMessageDialog(null,user.getId()+"회원님의 정보가 삭제되었습니다");
        }
        else{
            JOptionPane.showMessageDialog(null,"해당 유저를 찾을 수 없습니다");
        }
    }
}
