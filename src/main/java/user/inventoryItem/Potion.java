package user.inventoryItem;

import auctionData.TradeItem;

public class Potion extends Item {
    protected int effect;

    @Override
    protected void setSpecificAttributes(ItemBuilder itemBuilder) {
        effect = itemBuilder.option1;
    }
    @Override
    public int getOption1() {
        return effect;
    }

    @Override
    public String[] getListData() {
        return new String[]{type, name, grade, desc, Integer.toString(count), Integer.toString(effect)};
    }

    @Override
    public TradeItem getTradeItem(String userName, int count, int price) {
        Item item = new ItemBuilder()
            .type(this.type)
            .name(this.name)
            .grade(this.grade)
            .desc(this.desc)
            .count(count)
            .option1(this.effect)
            .build();

        return new TradeItem(userName, price, item);
    }
}
