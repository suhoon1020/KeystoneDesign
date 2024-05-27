package auctionData;

public class MaterialHistory extends HistoryDecorator{

    public MaterialHistory(TradeHistory tradeHistory){
        super(tradeHistory);
    }

    @Override
    public void setHistory() {
        if(item.getType().equals("Material")) {
            super.setHistory(buyerID, sellerID, item);
        }
    }
}
