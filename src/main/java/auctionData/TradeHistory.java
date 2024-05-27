package auctionData;


public class TradeHistory {
    String buyerName;
    String sellerName;
    String itemName;
    int price;
    double charge;


    public TradeHistory(String buyerID, String sellerID, String itemName,int price, double charge) {
        this.buyerName = buyerID;
        this.sellerName = sellerID;
        this.itemName = itemName;
        this.price = price;
        this.charge = charge;
    }

}


