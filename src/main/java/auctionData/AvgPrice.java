package auctionData;

import managers.TradeHistoryFileSystem;

import javax.swing.*;
import java.util.List;

public class AvgPrice extends HistoryDecorator {

    public AvgPrice(TradeHistory tradeHistory) {
        super(tradeHistory);
    }

    @Override
    public void showHistory(String buyerID, String sellerID, TradeItem item) {
        super.showHistory(buyerID, sellerID, item);
        //todo 평균 거래가 표시
        getAvgPrice(item);
    }

    public void getAvgPrice(TradeItem tradeItem) {
        List<BasicHistory> basicHistories = TradeHistoryFileSystem.getTradeHistoryFileSystem().getTradeHistories();
        int avgPrice = 0;

        for (BasicHistory basicHistory : basicHistories) {
            if (basicHistory.getTradeItem().getName().equals(tradeItem.getName())) {
                avgPrice += basicHistory.getTradeItem().getPrice();
            }
        }
        JOptionPane.showMessageDialog(null,"해당 아이템의 평균 거래가는" + avgPrice + "입니다");

    }
}
