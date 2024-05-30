package ChargeManager;

import auctionData.TradeItem;

public class ChangeChargeByCount extends ChargeDecorator {

    public ChangeChargeByCount(Charge charge) {
        super(charge);
    }

    @Override
    public int checkCharge(TradeItem tradeItem) {
        return (int)(super.checkCharge(tradeItem) - (super.checkCharge(tradeItem) * discount(tradeItem.getCount())));
    }

    public double discount(int count) {
        if(count >= 5) {
            return 0.5;
        }
        return 0;
    }
}

