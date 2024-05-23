package DataManager;

import ItemsManager.Item;

public class AuctionItem {

    String userId;
    int price;
    Item auctionItem;

    public String getUserId() {
        return userId;
    }

    public int getPrice() {
        return price;
    }

    public Item getAuctionItem() {
        return auctionItem;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAuctionItem(Item auctionItem) {
        this.auctionItem = auctionItem;
    }

    public AuctionItem(String userId, int price, Item registerdItem) {
        this.userId = userId;
        this.price = price;
        this.auctionItem = registerdItem;
    }

}
