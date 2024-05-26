package auctionData;

public abstract class TradeHistory {
    String buyer;
    String seller;
    String itemName;
    int price;
    
    public TradeHistory(String buyer, String seller, String itemName, int price) {
        this.buyer = buyer;
        this.seller = seller;
        this.itemName = itemName;
        this.price = price;
    }
}
