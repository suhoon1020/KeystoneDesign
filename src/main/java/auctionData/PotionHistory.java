package auctionData;

public class PotionHistory extends HistoryDecorator {
    public PotionHistory(TradeHistory tradeHistory) {
        super(tradeHistory);
    }

    @Override
    public void showHistory() {

        super.showHistory();

    }
}
