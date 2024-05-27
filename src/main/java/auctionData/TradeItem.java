package auctionData;

import itemInfos.Item;

public class TradeItem {
    private static int TRADE = 0;
    private int tradeId;
    private String userId;
    private Item item;
    private int price;
    private int count;

    public TradeItem(String userId, Item item, int count, int price) {
        this.tradeId = TRADE++;
        this.userId = userId;
        this.item = item;
        this.price = price;
        this.count = count;
    }

    public int getTradeId(){
        return tradeId;
    }

    public String getUserId() {
        return userId;
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

    public void setItem(Item item){
        this.item = item;
    }

    public String[] getSimpleListData(){
        return new String[]{Integer.toString(tradeId), userId, item.getName(), item.getGrade(), Integer.toString(count), Integer.toString(price)};
    }

    public String[] getListData(){
        return new String[]{Integer.toString(tradeId),
                userId, 
                item.getType(),
                item.getName(), 
                item.getGrade(), 
                item.getDesc(),
                Integer.toString(item.getOption1()),
                Integer.toString(count), 
                Integer.toString(price)};
    }
}