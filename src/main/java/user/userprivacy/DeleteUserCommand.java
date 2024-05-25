package user.userprivacy;

import managers.UserFileSystem;

import javax.swing.*;

public class DeleteUserCommand implements Command{
    User user;
    UserFileSystem userFileSystem;

    public DeleteUserCommand(User user,UserFileSystem userFileSystem) {
        this.user = user;
        this.userFileSystem = userFileSystem;
    }

    @Override
    public void execute() {
        if(userFileSystem.deleteUser(user.getId())){
            JOptionPane.showMessageDialog(null,user.getId()+"회원님의 정보가 삭제되었습니다");
        }
        else{
            JOptionPane.showMessageDialog(null,"해당 유저를 찾을 수 없습니다");
        }
    }
}
