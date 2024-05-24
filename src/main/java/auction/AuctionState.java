package auction;

import managers.UserFileSystem;
import user.userprivacy.User;

public interface AuctionState {
    public User login(String ID, String password, UserFileSystem userFileSystem);
    public AuctionState changeState();
    public boolean isOpen();
}
