package auctionData;

import managers.TradeHistoryFileSystem;

import java.util.List;

public class EquipMentHistory extends HistoryDecorator {

    public EquipMentHistory(TradeHistory tradeHistory) {
        super(tradeHistory);
    }

    List<BasicHistory> currentHistory = TradeHistoryFileSystem.getTradeItemFileSystem().getFilterHistory();

    @Override
    public void showHistory() {
        super.showHistory();
        for(BasicHistory currentHistory : currentHistory){
            if(currentHistory.getTradeItem().getType().equals())
        }
    }

    public boolean checkEquipMent(){

    }
}
