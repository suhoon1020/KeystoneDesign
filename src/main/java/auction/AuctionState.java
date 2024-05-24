package deu.cse.auction;

import deu.cse.managers.UserFileSystem;
import deu.cse.user.userprivacy.User;

public interface AuctionState {
    public User login(String ID, String password, UserFileSystem userFileSystem);
    public AuctionState changeState();
    public boolean isOpen();
}
