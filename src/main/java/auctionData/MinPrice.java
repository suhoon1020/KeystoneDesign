package auctionData;

public class MinPrice extends HistoryDecorator{

    public MinPrice(TradeHistory tradeHistory){
        super(tradeHistory);
    }

    @Override
    public void showHistory(String buyerID, String sellerID, TradeItem item) {
        super.showHistory(buyerID, sellerID, item);
        //todo 최소 거래가 표시
    }
}
