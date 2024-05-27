package auctionData;

public class MaterialHistory extends HistoryDecorator{

    public MaterialHistory(TradeHistory tradeHistory){
        super(tradeHistory);
    }

    @Override
    public void showHistory() {
        if(item.getType().equals("Material")) {
            super.showHistory(buyerID, sellerID, item);
        }
    }
}
