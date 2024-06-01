package auction;

import java.util.List;

import ChargeManager.BasicCharge;
import ChargeManager.ChangeChargeByPrice;
import ChargeManager.Charge;
import ChargeManager.ChangeChargeByCount;
import auctionData.TradeHistory;
import auctionData.TradeHistoryFileSystem;
import auctionData.TradeItem;
import auctionData.TradeItemFileSystem;
import itemInfos.Item;
import swing.SwingLogin;
import user.InventoryItem;

import user.User;
import user.UserFileSystem;

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

    public String getId() {
        return user.getId();
    }

    public String getName() {
        return user.getName();
    }

    public int getGold() {
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

    public void changeState() {
        auctionState = auctionState.changeState();
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
        List<User> users = UserFileSystem.getUserFileSystem().getUsersByName(name);

        if (!users.isEmpty()) {
            for (User user : users) {
                if (user.getName().equals(name) && user.getPhoneNumber().equals(phoneNumber)) {
                    return user.getId();
                }
            }
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


    public boolean sellItem(String sellItemName, int sellCount, int price) {
        InventoryItem inventoryItem = user.getItemByName(sellItemName);

        //유저 인벤토리에서 아이템 개수 만큼 있는지 확인
        if (inventoryItem.getCount() >= sellCount) {
            Item item = inventoryItem.getItem();

            // 판매 후 아이템 정보
            InventoryItem newInventoryItem = new InventoryItem(item.clone(), inventoryItem.getCount() - sellCount);

            // 인벤토리 아이템 정보 수정 / 삭제
            if (newInventoryItem.getCount() > 0) {
                // 아이템 갯수가 많음 >> 아이템 수량 수정
                user.updateItem(newInventoryItem);

            } else if (newInventoryItem.getCount() == 0) {
                // 아이템 갯수가 같음 >> 아이템 삭제
                user.deleteItem(inventoryItem.getName());
            }

            // 경매장 아이템 등록
            TradeItem tradeItem = new TradeItem(user.getId(), item.clone(), sellCount, price);
            TradeItemFileSystem.getTradeItemFileSystem().getTradeItemList().add(tradeItem);

            // 저장
            UserFileSystem.getUserFileSystem().saveInfosToFile();
            TradeItemFileSystem.getTradeItemFileSystem().saveInfosToFile();
            return true;
        }

        return false;
    }


    public boolean cancleSellItem(int tradeId) {
        TradeItem auctionTradeItem = TradeItemFileSystem.getTradeItemFileSystem().getTradeItemByTradeId(tradeId);

        if (auctionTradeItem != null) {
            Item item = auctionTradeItem.getItem();

            List<TradeItem> tradeItems = TradeItemFileSystem.getTradeItemFileSystem().getTradeItemList();

            // 거래 목록에서 아이템 삭제
            for (int i = 0; i < tradeItems.size(); ++i) {
                if (tradeItems.get(i).getTradeId() == tradeId) {
                    tradeItems.remove(i);
                }
            }
            // 아이템 인벤토리로 옮기기
            InventoryItem inventoryItem = new InventoryItem(item.clone(), auctionTradeItem.getCount());
            user.addItemCount(inventoryItem);

            // 저장
            UserFileSystem.getUserFileSystem().saveInfosToFile();
            TradeItemFileSystem.getTradeItemFileSystem().saveInfosToFile();
            return true;
        }

        return false;
    }

    public boolean buyItem(int tradeId, int buyCount) {
        TradeItem auctionTradeItem = TradeItemFileSystem.getTradeItemFileSystem().getTradeItemByTradeId(tradeId);
        //구매할 아이템 개수 * 금액 만큼 돈이 있는지 && 아이템 구매 개수가 사는 개수보다 많은지 확인
        if (auctionTradeItem.getCount() >= buyCount && user.getGold() >= auctionTradeItem.getPrice() * buyCount) {
            Item item = auctionTradeItem.getItem();

            // 수수료 계산
            Charge charge = new BasicCharge();
            charge = new ChangeChargeByCount(charge);
            charge = new ChangeChargeByPrice(charge);

            int fianlcharge = charge.checkCharge(auctionTradeItem);

            // 거래 기록 남기기
            TradeHistory tradeHistory = new TradeHistory(user.getName(), auctionTradeItem.getUserId(), auctionTradeItem.getName(),
                    auctionTradeItem.getPrice(), fianlcharge);
            TradeHistoryFileSystem.getTradeHistoryFileSystem().putTradeHistory(tradeHistory);

            // 돈 차감
            user.setGold(user.getGold() - auctionTradeItem.getPrice() * buyCount);

            // 판매자 돈 증가
            User seller = UserFileSystem.getUserFileSystem().getUserById(auctionTradeItem.getUserId());
            //수수료 만큼은 금액 차감!!
            if (seller != null)
                seller.setGold(seller.getGold() + auctionTradeItem.getPrice() * buyCount - fianlcharge);

            // 거래 아이템에서 인벤토리 아이템으로 옮기기

            // 거래 후 거래 아이템 정보
            List<TradeItem> tradeItems = TradeItemFileSystem.getTradeItemFileSystem().getTradeItemList();
            TradeItem tradeItem = new TradeItem(auctionTradeItem.getUserId(), item.clone(), auctionTradeItem.getCount() - buyCount, auctionTradeItem.getPrice());

            // 거래 아이템 정보 수정 삭제
            changeItemInfo(tradeId, tradeItems, tradeItem);

            // 유저 아이템 등록
            InventoryItem inventoryItem = new InventoryItem(item.clone(), buyCount);
            user.addItemCount(inventoryItem);

            // 저장
            UserFileSystem.getUserFileSystem().saveInfosToFile();
            TradeItemFileSystem.getTradeItemFileSystem().saveInfosToFile();
            TradeHistoryFileSystem.getTradeHistoryFileSystem().saveInfosToFile();
            return true;
        }

        return false;
    }


    public void changeItemInfo(int tradeId, List<TradeItem> tradeItems, TradeItem tradeItem) {
        // 거래 아이템 정보 수정 / 삭제

        if (tradeItem.getCount() > 0) {
            // 아이템 갯수가 많음 >> 아이템 수량 수정
            for (int i = 0; i < tradeItems.size(); ++i) {
                if (tradeItems.get(i).getTradeId() == tradeId) {
                    tradeItems.set(i, tradeItem);

                }
            }
        } else if (tradeItem.getCount() == 0) {
            // 아이템 갯수가 같음 >> 아이템 삭제
            for (int i = 0; i < tradeItems.size(); ++i) {
                if (tradeItems.get(i).getTradeId() == tradeId) {
                    tradeItems.remove(i);
                }
            }
        }
    }
}
