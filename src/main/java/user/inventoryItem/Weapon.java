package user.inventoryItem;

import auctionData.TradeItem;

public class Weapon extends Item {
    protected int damage;

    @Override
    protected void setSpecificAttributes(ItemBuilder itemBuilder) {
        this.damage = itemBuilder.option1;
    }
    
    @Override
    public int getOption1() {
        return damage;
    }

    @Override
    public String[] getListData() {
        return new String[]{type, name, grade, desc, Integer.toString(count), Integer.toString(damage)};
    }

    @Override
    public TradeItem getTradeItem(String userName, int count, int price) {
        Item item = new ItemBuilder()
            .type(this.type)
            .name(this.name)
            .grade(this.grade)
            .desc(this.desc)
            .count(count)
            .option1(this.damage)
            .build();

        return new TradeItem(userName, price, item);
    }
}
