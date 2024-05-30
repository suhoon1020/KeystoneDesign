import ChargeManager.BasicCharge;
import ChargeManager.ChangeChargeByCount;
import ChargeManager.ChangeChargeByPrice;
import ChargeManager.Charge;
import auctionData.TradeItem;
import itemInfos.Item;
import itemInfos.ItemBuilder;

public class DecoratorTEST {
    public static void main(String[] args) {
        Item equipment = new ItemBuilder()
            .type("Equipment")
            .name("equipmentTEST")
            .grade("Common")
            .desc("NONE")
            .option1(10)
            .build();

        TradeItem tradeItem = new TradeItem("Auction", equipment, 30, 10000);


        // 기본적인 수수료 방식
        Charge charge1 = new BasicCharge();
        System.out.println("수수료 : " + charge1.checkCharge(tradeItem));

        // 개수에 따른 수수료 방식
        charge1 = new ChangeChargeByCount(charge1);
        System.out.println("수수료 : " + charge1.checkCharge(tradeItem));

        // 가격에 따른 수수료 방식
        Charge charge2 = new BasicCharge();
        charge2 = new ChangeChargeByPrice(charge2);
        System.out.println("수수료 : " + charge2.checkCharge(tradeItem));

        // 개수 + 가격에 따른 수수료 방식
        charge1 = new ChangeChargeByPrice(charge1);
        System.out.println("수수료 : " + charge1.checkCharge(tradeItem));
    }
}
