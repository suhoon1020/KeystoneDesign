package auctionData;

import user.inventoryItem.Item;
import user.inventoryItem.ItemBuilder;

public class TradeItem {
    private static int TRADE = 0;
    private int tradeId;
    private String userName;
    private Item item;
    private int price;

    public TradeItem(String userName, Item item, int count, int price) {
        this.tradeId = TRADE++;
        this.userName = userName;
        this.item = new ItemBuilder()
                .type(item.getType())
                .name(item.getName())
                .desc(item.getDesc())
                .grade(item.getGrade())
                .count(count)
                .option1(item.getOption1())
                .build();
        this.price = price;
    }

    public int getTradeId(){
        return tradeId;
    }

    public String getUserName() {
        return userName;
    }

    public int getPrice() {
        return price;
    }

    public Item getItem() {
        return item;
    }

    public String getType(){
        return item.getType();
    }

    public String getGrade(){
        return item.getGrade();
    }

    public String getName(){
        return item.getName();
    }

    public int getCount(){
        return item.getCount();
    }

    public String[] getListData(){
        return new String[]{Integer.toString(tradeId),
                userName, 
                item.getType(),
                item.getName(), 
                item.getGrade(), 
                item.getDesc(),
                Integer.toString(item.getCount()), 
                Integer.toString(item.getOption1()),
                Integer.toString(price)};
    }
}