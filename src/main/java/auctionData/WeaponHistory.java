package auctionData;

public class WeaponHistory extends HistoryDecorator{
    public WeaponHistory(TradeHistory tradeHistory){
        super(tradeHistory);
    }

    @Override
    public void setHistory() {
        if(item.getType().equals("Weapon")) {
            super.setHistory();
        }
    }
}
