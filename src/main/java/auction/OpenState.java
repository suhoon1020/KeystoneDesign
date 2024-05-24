package deu.cse.auction;

import deu.cse.managers.UserFileSystem;
import deu.cse.swing.SwingLogin;
import deu.cse.user.userprivacy.User;

public class OpenState implements AuctionState {

    @Override
    public User login(String ID, String password, UserFileSystem userFileSystem) {

        User user = userFileSystem.getUserByID(ID);

        if(user.getPassword().equals(password)){
            SwingLogin.getSwingLogin().setVisible(true);
            return user;

        }
        else
            return null;
    }

    @Override
    public AuctionState changeState() {
        return new CloseState();
    }

    @Override
    public boolean isOpen() {
        return true;
    }
}

