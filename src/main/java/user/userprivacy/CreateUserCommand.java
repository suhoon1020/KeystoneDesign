package user.userprivacy;

import managers.UserFileSystem;

import javax.swing.*;

public class CreateUserCommand implements Command {
    User user;
    UserFileSystem userFileSystem;

    public CreateUserCommand(User user,UserFileSystem userFileSystem) {
        this.user = user;
        this.userFileSystem = userFileSystem;
    }

    @Override
    public void execute() {
        if(userFileSystem.putUser(user)){
            JOptionPane.showMessageDialog(null,"유저 생성에 성공하였습니다");
        }
        else{
            JOptionPane.showMessageDialog(null,"중복된 ID가 있습니다");
        }
    }
}
