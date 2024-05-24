package auction;

import managers.UserFileSystem;
import user.userprivacy.User;

public class OpenState implements AuctionState {

    @Override
    public User login(String ID, String password, UserFileSystem userFileSystem) {

        User user = userFileSystem.getUserByID(ID);

        if(user.getPassword().equals(password))
            return user;
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

