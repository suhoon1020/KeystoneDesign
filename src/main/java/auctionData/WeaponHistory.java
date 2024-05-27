package auctionData;

public class WeaponHistory extends HistoryDecorator{
    public WeaponHistory(TradeHistory tradeHistory){
        super(tradeHistory);
    }

    @Override
    public void showHistory() {
        if(item.getType().equals("Weapon")) {
            super.showHistory();
        }
    }
}
