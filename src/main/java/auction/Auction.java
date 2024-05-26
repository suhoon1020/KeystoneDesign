package auction;

import java.util.List;

import CommandManage.Invoker;
import CommandManage.InventoryItems.ItemInvToTradeCommend;
import CommandManage.TradeItems.ItemTradeToInvCommend;
import auctionData.TradeItem;
import managers.TradeItemFileSystem;
import managers.UserFileSystem;
import swing.SwingLogin;
import user.InventoryItem;

import user.User;

public class Auction {
    private static Auction auction;

    private AuctionState auctionState;
    private User user;
    private Invoker invoker;

    private Auction() {
        auctionState = new OpenState();
        invoker = new Invoker();
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
        //유저 인벤토리에서 아이템 개수 만큼 있는지 확인
        if(user.getItemByName(sellItemName).getCount() >= sellCount){
            //인벤토리 아이템에서 거래 아이템으로 옮기기
            ItemInvToTradeCommend itemInvToTradeCommend = new ItemInvToTradeCommend(user, sellItemName, price, sellCount);
            invoker.setCommand(itemInvToTradeCommend);
            invoker.run();
            return true;
        }

        return false;
    }

    public boolean buyItem(int tradeId, int count){
        TradeItem tradeItem = TradeItemFileSystem.getTradeItemFileSystem().getTradeItemByTradeId(tradeId);
        //구매할 아이템 개수 * 금액 만큼 돈이 있는지 확인
        if(user.getGold() >= tradeItem.getPrice() * count){
            // 거래 기록 남기기
            // 돈 차감
            user.setGold(user.getGold() - tradeItem.getPrice() * count);
            UserFileSystem.getUserFileSystem().saveInfosToFile();
            // 구매자 돈 증가
            User sellUser = UserFileSystem.getUserFileSystem().getUserByName(tradeItem.getUserName());
            if(sellUser != null)
                sellUser.setGold(sellUser.getGold() + count);

            // 거래 아이템에서 인벤토리 아이템으로 옮기기
            ItemTradeToInvCommend itemTradeToInvCommend = new ItemTradeToInvCommend(user, tradeId, count);
            invoker.setCommand(itemTradeToInvCommend);
            invoker.run();
        }
        return false;
    }
}
