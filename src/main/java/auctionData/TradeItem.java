package auctionData;

import item.Item;

public class TradeItem {
    private static int TRADE = 0;
    private int tradeId;
    private String userName;
    private Item item;
    private int price;
    private int count;

    public TradeItem(String userName, Item item, int count, int price) {
        this.tradeId = TRADE++;
        this.userName = userName;
        this.item = item;
        this.price = price;
        this.count = count;
    }

    public int getTradeId(){
        return tradeId;
    }

    public String getUserName() {
        return userName;
    }

    public Item getItem(){
        return item.clone();
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

    public int getPrice() {
        return price;
    }

    public int getCount(){
        return count;
    }

    public String[] getListData(){
        return new String[]{Integer.toString(tradeId),
                userName, 
                item.getType(),
                item.getName(), 
                item.getGrade(), 
                item.getDesc(),
                Integer.toString(item.getOption1()),
                Integer.toString(count), 
                Integer.toString(price)};
    }
}