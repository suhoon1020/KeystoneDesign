package auction;

import swing.SwingAdmin;
import swing.SwingAuction;
import user.User;
import user.UserFileSystem;

public class OpenState implements AuctionState {

    @Override
    public boolean login(String id, String password) {
        User loginUser = UserFileSystem.getUserFileSystem().getUserById(id);

        if(loginUser != null){
            if(loginUser.getPassword().equals(password)){
                Auction.getAuction().setUser(loginUser);
                SwingAdmin.getSwingAdmin().setVisible(true);
                return true;
            }
            else{
                return false;
            }
        }
        return false;
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

