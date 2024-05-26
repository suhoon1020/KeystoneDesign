package auctionData;

public class MaxPrice extends HistoryDecorator{

   public MaxPrice(TradeHistory tradeHistory){
       super(tradeHistory);
   }
    @Override
    public void showHistory(String buyerID, String sellerID, TradeItem item) {
        super.showHistory(buyerID, sellerID, item);
        //todo max 거래가 표시
    }
}
