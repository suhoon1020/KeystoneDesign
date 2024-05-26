package CommandManage;

import managers.UserFileSystem;
import user.userprivacy.User;

import javax.swing.*;

public class UpdateUserCommand implements Command{
    User user;


    public UpdateUserCommand(User user) {
        this.user = user;
    }

    @Override
    public void execute() {
        if(user.updateUser(user.getId(),user)){
            JOptionPane.showMessageDialog(null,"유저 정보가 수정되었습니다");
        }
        else{
            JOptionPane.showMessageDialog(null,"해당 유저 정보를 찾을 수 없습니다");
        }
    }
}
