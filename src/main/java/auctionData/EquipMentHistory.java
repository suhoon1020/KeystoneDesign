package auctionData;

import managers.TradeHistoryFileSystem;

import java.util.List;

public class EquipMentHistory extends HistoryDecorator {

    public EquipMentHistory(TradeHistory tradeHistory) {
        super(tradeHistory);
    }

    List<BasicHistory> currentHistory = TradeHistoryFileSystem.getTradeHistoryItemFileSystem().getFilterTradeHistories();

    @Override
    public void setHistory() {
        super.setHistory();
        for(BasicHistory currentHistory : currentHistory){
            if(currentHistory.getTradeItem().getType().equals())
        }
    }

    public boolean checkEquipMent(){

    }
}
