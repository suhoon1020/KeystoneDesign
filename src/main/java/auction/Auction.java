package auction;

import java.util.List;

import managers.UserFileSystem;
import swing.SwingLogin;
import user.InventoryItem;
import user.User;

public class Auction {
    private static Auction auction;

    private AuctionState auctionState;
    private User user;

    private Auction() {
        auctionState = new OpenState();
    }

    public static Auction getAuction() {
        if (auction == null)
            auction = new Auction();
        return auction;
    }

    public void changeState() {
        auctionState = auctionState.changeState();
    }

    public int getGold(){
        return user.getGold();
    }

    public List<InventoryItem> getInventory() {
        return user.getItemList();
    }

    public void setUser(User user) {
        this.user = user;
    }

    // 거래소 실행
    public void run() {
        SwingLogin.getSwingLogin().setVisible(true);
    }

    public boolean isOpen() {
        return auctionState.isOpen();
    }

    public boolean login(String ID, String password) {
        return auctionState.login(ID, password);
    }


    /*
     *      LOGIN
     */

    public String findID(String name, String phoneNumber) {
        User user = UserFileSystem.getUserFileSystem().getUserByName(name);

        if (user != null) {
            if (user.getPhoneNumber().equals(phoneNumber))
                return user.getId();
            else
                return "";
        }

        return "";
    }

    public String findPassword(String ID, String name, String phoneNumber) {
        User user = UserFileSystem.getUserFileSystem().getUserById(ID);

        if (user != null) {
            if (user.getName().equals(name) && user.getPhoneNumber().equals(phoneNumber))
                return user.getPassword();
            else
                return "";
        }
        return "";
    }


    public boolean sellItem(String sellItemName, int sellCount, int price){
       return false;
    }

    public boolean buyItem(int tradeId){
        return false;
    }
}
