package auction;

import managers.UserFileSystem;
import user.userprivacy.User;

public class CloseState implements AuctionState {

    @Override
    public User login(String ID, String password, UserFileSystem userFileSystem) {
        // TODO 관리자 로그인만 구성
        return null;
    }

    @Override
    public AuctionState changeState() {
        return new OpenState();
    }

    @Override
    public boolean isOpen() {
        return false;
    }
}
