package auction;

import java.io.File;
import java.util.List;

import auctionData.TradeItem;
import managers.FileFacade;
import managers.TradeHistoryFileSystem;
import managers.TradeItemFileSystem;
import managers.UserFileSystem;
import swing.SwingAdmin;
import swing.SwingLogin;
import user.inventoryItem.Item;
import user.userprivacy.User;

public class Auction {
    private static Auction auction;

    private AuctionState auctionState;
    private User user;

    private Auction(){
        auctionState = new OpenState();
    }
    
    public static Auction getAuction(){
        if(auction == null)
            auction = new Auction();
        return auction;
    }

    public void changeState(){
        auctionState = auctionState.changeState();
    }

    public List<Item> getInventory() {
        return user.getItems();
    }

    public void setUser(User user){
        this.user = user;
    }

    // 거래소 실행
    public void run(){
        SwingLogin.getSwingLogin().setVisible(true);
    }
    
    public boolean isOpen(){
        return auctionState.isOpen();
    }

    public boolean login(String ID, String password){
        return auctionState.login(ID, password);
    }


    /*
     *      LOGIN
     */

    public String findID(String name, String phoneNumber){
        User user = FileFacade.getFacade().getUserByName(name);

        if(user != null){
            if(user.getPhoneNumber().equals(phoneNumber))
                return user.getId();
            else
                return "";
        }
        
        return "";
    }

    public String findPassword(String ID, String name, String phoneNumber){
        User user = FileFacade.getFacade().getUserById(ID);

        if(user != null){
            if(user.getName().equals(name) && user.getPhoneNumber().equals(phoneNumber))
                return user.getPassword();
            else
                return "";
        }
        return "";
    }



}
