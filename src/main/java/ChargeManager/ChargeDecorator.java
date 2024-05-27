package ChargeManager;

import auctionData.TradeItem;

public class ChargeDecorator implements Charge{
    protected Charge charge;


    public ChargeDecorator(Charge charge) {
        this.charge = charge;
    }

    @Override
    public double checkCharge(TradeItem tradeItem) {
        return this.charge.checkCharge(tradeItem);
    }
}
