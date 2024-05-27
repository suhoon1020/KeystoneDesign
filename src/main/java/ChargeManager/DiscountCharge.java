package ChargeManager;

import auctionData.TradeItem;

public class DiscountCharge extends ChargeDecorator {

    public DiscountCharge(Charge charge) {
        super(charge);
    }

    @Override
    public double checkCharge(TradeItem tradeItem) {
        return super.checkCharge(tradeItem) *(super.checkCharge(tradeItem) * discount(tradeItem.getCount()));
    }

    public double discount(int count) {
        if(count>=5) {
            return 0.5;
        }
        return 0;
    }
}

