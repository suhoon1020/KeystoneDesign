package auction;

import java.util.List;

import auctionData.TradeItem;
import managers.FileFacade;
import managers.UserFileSystem;
import swing.SwingLogin;
import user.inventoryItem.Item;
import user.userprivacy.User;

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

    public List<Item> getInventory() {
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
        List<Item> userItemList = user.getItemList();

        for(int i = 0; i < userItemList.size(); ++i){
            if(userItemList.get(i).getName().equals(sellItemName)){
                if(userItemList.get(i).getCount() >= sellCount){
                    TradeItem tradeItem = userItemList.get(i).getTradeItem(user.getName(), sellCount, price);
                    
                    userItemList.get(i).setCount(userItemList.get(i).getCount() - sellCount);

                    // 아이템 갯수가 0 일시 제거
                    if(userItemList.get(i).getCount() == 0)
                        userItemList.remove(i);

                    // 경매장에 아이템 등록
                    FileFacade.getFacade().putTradeItem(tradeItem);
                    return true;
                }
                else{
                    // 아이템 갯수가 충분하지 않음
                    return false;
                }
            }
        }
        // 아이템이 없음
        return false;
    }

    public boolean buyItem(int tradeId){
        TradeItem tradeItem = FileFacade.getFacade().getTradeItemById(tradeId);
        if(tradeItem != null){
            if(user.getGold() >= tradeItem.getPrice()){
                Item item = tradeItem.getItem();
    
                FileFacade.getFacade().deleteTradeItem(tradeId);
    
                user.setGold(user.getGold() - tradeItem.getPrice());
                UserFileSystem.getUserFileSystem().addItem(user, item);
    
                return true;
            }
            else{
                // 돈없음
                return false;
            }
        }
        else{
            // 아이템 없음
            return false;
        }
    }
}
