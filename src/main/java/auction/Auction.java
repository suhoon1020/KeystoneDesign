package auction;

import java.util.List;

import auctionData.TradeItem;
import managers.TradeHistoryFileSystem;
import managers.TradeItemFileSystem;
import managers.UserFileSystem;
import swing.SwingLogin;
import user.inventoryItem.Item;
import user.userprivacy.User;


public class Auction {
    private static Auction auction;
    private static UserFileSystem userFileSystem;
    private static TradeItemFileSystem tradeItemFileSystem;
    private static TradeHistoryFileSystem tradeHistoryFileSystem;

    private AuctionState auctionState;
    private User user;

    private Auction(){
        userFileSystem = new UserFileSystem();
        tradeItemFileSystem = new TradeItemFileSystem();
        tradeHistoryFileSystem = new TradeHistoryFileSystem();

        userFileSystem.loadInfosFromFile();
        tradeItemFileSystem.loadInfosFromFile();
        tradeHistoryFileSystem.loadInfosFromFile();

        auctionState = new OpenState();
    }
    
    public static Auction getAuction(){
        if(auction == null)
            auction = new Auction();
        return auction;
    }

    // 거래소 실행
    public void run(){
        SwingLogin.getSwingLogin().setVisible(true);
    }
    
    public boolean isOpen(){
        return auctionState.isOpen();
    }

    public void changeState(){
        auctionState = auctionState.changeState();
    }

    // 개인
    public List<Item> getInventory() {
        return user.getItems();
    }


    public boolean login(String ID, String password){
        User newUser = auctionState.login(ID, password, userFileSystem);

        if(newUser != null){
            user = newUser;
            return true;
        }
        else{
            return false;
        }
    }

    public String findID(String name, String phoneNumber){
        User user = userFileSystem.getUserByName(name);
        if(user != null){
            if(user.getPhoneNumber().equals(phoneNumber))
                return user.getID();
            else
                return "";
        }
        
        return "";
    }

    public String findPassword(String ID, String name, String phoneNumber){
        User user = userFileSystem.getUserByID(ID);

        if(user != null){
            if(user.getName().equals(name) && user.getPhoneNumber().equals(phoneNumber))
                return user.getPassword();
            else
                return "";
        }
        return "";
    }


    /*
     *      USER MANAGE
     */

     public boolean putUser(User newUser){
        return userFileSystem.putUser(newUser);
    }

    public boolean updateUser(String id, User user){
        return userFileSystem.updateUser(id, user);
    }

    public Boolean deleteUser(String id){
        return userFileSystem.deleteUser(id);
    }

    public List<User> getUserList(){
        return userFileSystem.getUserList();
    }

    /*
     *      TRADE ITEM MANAGE
     */

    public List<TradeItem> getAuctionItemList(){
        return tradeItemFileSystem.getAuctionItemList();
    }




}
