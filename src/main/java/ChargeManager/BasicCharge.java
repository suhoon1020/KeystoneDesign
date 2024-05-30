package ChargeManager;

import auctionData.TradeItem;

public class BasicCharge implements Charge{

    @Override
    public int checkCharge(TradeItem tradeItem) {
        return (int)(tradeItem.getPrice() * 0.05);
    }
}
