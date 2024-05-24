package deu.cse.auction;

import deu.cse.managers.UserFileSystem;
import deu.cse.user.userprivacy.User;

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
