package auctionData;

import ItemManager.Item;

import java.util.List;

public class HistoryDecorator implements TradeHistory{

    protected TradeHistory tradeHistory;

    public HistoryDecorator(TradeHistory tradeHistory){
        this.tradeHistory = tradeHistory;
    }

    @Override
    public void showHistory(String buyerID, String sellerID, TradeItem item) {
        this.tradeHistory.showHistory(buyerID,sellerID,item);
    }
}
