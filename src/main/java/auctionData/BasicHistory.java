package auctionData;


import java.util.List;

public class BasicHistory implements TradeHistory{

    String buyerID;
    String sellerID;
    TradeItem tradeItem;

    public TradeItem getTradeItem() {
        return tradeItem;
    }

    public String getBuyerID() {
        return buyerID;
    }

    public String getSellerID() {
        return sellerID;
    }

    public BasicHistory(String buyerID, String sellerID, TradeItem tradeItem) {
        this.buyerID = buyerID;
        this.sellerID = sellerID;
        this.tradeItem = tradeItem;
    }

    @Override
    public void showHistory(String buyerID, String sellerID, TradeItem item) {
        //todo 거래내역 조회 기능
    }

}
