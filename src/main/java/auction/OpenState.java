package auction;

import managers.UserFileSystem;
import swing.SwingAdmin;
import swing.SwingLogin;
import user.userprivacy.User;

public class OpenState implements AuctionState {

    @Override
    public User login(String ID, String password, UserFileSystem userFileSystem) {

        User user = userFileSystem.getUserByID(ID);

        if(user.getPassword().equals(password)){
            SwingAdmin.getSwingAdmin().setVisible(true);
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

