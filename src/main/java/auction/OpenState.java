package auction;

import javax.swing.JOptionPane;

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

                if(loginUser.isAdmin())
                    SwingAdmin.getSwingAdmin().setVisible(true);
                else
                    SwingAuction.getSwingAuction().setVisible(true);

                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }

    @Override
    public void setOpen() {
        JOptionPane.showMessageDialog(null, "경매장이 이미 열렸습니다.");
    }

    @Override
    public void setClose() {
        Auction.getAuction().setState(new CloseState());
        JOptionPane.showMessageDialog(null, "경매장이 닫혔습니다.");
    }

    @Override
    public String getStateString() {
        return "Open";
    }
}

