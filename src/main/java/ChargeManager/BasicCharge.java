package ChargeManager;

import auctionData.TradeItem;

public class BasicCharge implements Charge{


    @Override
    public double checkCharge(TradeItem tradeItem) {
        return tradeItem.getPrice() * 0.05;
    }
}
