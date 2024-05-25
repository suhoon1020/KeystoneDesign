
package user.inventoryItem;

import auctionData.TradeItem;

public class Material extends Item{

    @Override
    public void setSpecificAttributes(ItemBuilder itemBuilder){

    }

    @Override
    public int getOption1() {
        return 0;
    }

    @Override
    public String[] getListData() {
        return new String[]{type, name, grade, desc, Integer.toString(count), "0"};
    }

    @Override
    public TradeItem getTradeItem(String userName, int count, int price) {
        Item item = new ItemBuilder()
            .type(this.type)
            .name(this.name)
            .grade(this.grade)
            .desc(this.desc)
            .count(count)
            .build();

        return new TradeItem(userName, price, item);
    }
}
