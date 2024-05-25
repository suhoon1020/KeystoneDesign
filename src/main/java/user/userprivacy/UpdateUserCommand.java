package user.userprivacy;

import managers.UserFileSystem;

import javax.swing.*;

public class UpdateUserCommand implements Command{
    User user;
    UserFileSystem userFileSystem;

    public UpdateUserCommand(User user,UserFileSystem userFileSystem) {
        this.user = user;
        this.userFileSystem = userFileSystem;
    }

    @Override
    public void execute() {
        if(userFileSystem.updateUser(user.getId(),user)){
            JOptionPane.showMessageDialog(null,"유저 정보가 수정되었습니다");
        }
        else{
            JOptionPane.showMessageDialog(null,"해당 유저 정보를 찾을 수 없습니다");
        }
    }
}
