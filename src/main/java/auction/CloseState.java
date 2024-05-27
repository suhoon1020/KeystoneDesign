package auction;

import swing.SwingAdmin;
import user.User;
import user.UserFileSystem;

public class CloseState implements AuctionState {

    @Override
    public boolean login(String id, String password) {
        User loginUser = UserFileSystem.getUserFileSystem().getUserById(id);

        if(loginUser != null){
            if(loginUser.getPassword().equals(password)){
                Auction.getAuction().setUser(loginUser);

                if(loginUser.isAdmin())
                    SwingAdmin.getSwingAdmin().setVisible(true);
                else
                    return false;

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
        return new OpenState();
    }

    @Override
    public boolean isOpen() {
        return false;
    }
}
