package auctionData;


import managers.TradeHistoryFileSystem;

import java.util.ArrayList;
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
    public void setHistory() {
        //todo 거래내역 전부 조회
        List<BasicHistory> loadFiles = TradeHistoryFileSystem.getTradeHistoryItemFileSystem().getTradeHistories();
        TradeHistoryFileSystem.setFilterTradeHistories(loadFiles);
    }

}
