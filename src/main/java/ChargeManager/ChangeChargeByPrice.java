package ChargeManager;

import auctionData.TradeItem;

public class ChangeChargeByPrice extends ChargeDecorator{

    public ChangeChargeByPrice(Charge charge) {
        super(charge);
    }

    @Override
    public int checkCharge(TradeItem tradeItem) {
        return (int)(super.checkCharge(tradeItem) - (super.checkCharge(tradeItem) * discount(tradeItem.getPrice()*tradeItem.getCount())));
    }

    public double discount(int tradePrice) {
        if(tradePrice >= 10000) {
            return 0.5;
        }
        return 0;
    }

}
