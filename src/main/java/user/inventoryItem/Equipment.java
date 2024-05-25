package user.inventoryItem;

import auctionData.TradeItem;

public class Equipment extends Item{
    protected int defence;

    @Override
    protected void setSpecificAttributes(ItemBuilder itemBuilder) {
        this.defence=itemBuilder.option1;
    }

    @Override
    public int getOption1() {
        return defence;
    }

    @Override
    public String[] getListData() {
        return new String[]{type, name, grade, desc, Integer.toString(count), Integer.toString(defence)};
    }

    @Override
    public TradeItem getTradeItem(String userName, int count, int price) {
        Item item = new ItemBuilder()
            .type(this.type)
            .name(this.name)
            .grade(this.grade)
            .desc(this.desc)
            .count(count)
            .option1(defence)
            .build();

        return new TradeItem(userName, price, item);
    }
}
