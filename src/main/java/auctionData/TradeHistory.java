package auctionData;

public class TradeHistory {
    String buyerID;
    String sellerID;
    TradeItem tradeItems;
    double charge;


    public TradeHistory(String buyerID, String sellerID, TradeItem tradeItems, double charge) {
        this.buyerID = buyerID;
        this.sellerID = sellerID;
        this.tradeItems = tradeItems;
        this.charge = charge;
    }

}


