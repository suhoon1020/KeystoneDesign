package auctionData;

public class HistoryDecorator implements TradeHistory{

    protected TradeHistory tradeHistory;

    public HistoryDecorator(TradeHistory tradeHistory){
        this.tradeHistory = tradeHistory;
    }

    @Override
    public void setHistory() {
        this.tradeHistory.setHistory();
    }
}
