package auction;

import javax.swing.JOptionPane;

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
    public void setOpen() {
        Auction.getAuction().setState(new OpenState());
        JOptionPane.showMessageDialog(null, "경매장이 열렸습니다.");
    }

    @Override
    public void setClose() {
        JOptionPane.showMessageDialog(null, "이미 닫힌 상태입니다.");
    }

    @Override
    public String getStateString() {
        return "Close";
    }
}
