package auctionData;

public class PotionHistory extends HistoryDecorator{
    public PotionHistory(TradeHistory tradeHistory){
        super(tradeHistory);
    }

    @Override
    public void setHistory() {
        if(item.getType().equals("Material")) {
            super.setHistory();
        }
    }
}
