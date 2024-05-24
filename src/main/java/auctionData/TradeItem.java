package auctionData;

import user.inventoryItem.Item;

public class TradeItem {
    private static int TRADE = 0;
    private int tradeID;
    private String userId;
    private int price;
    private Item auctionItem;

    public TradeItem(String userId, int price, Item registerdItem) {
        this.tradeID = TRADE++;
        this.userId = userId;
        this.price = price;
        this.auctionItem = registerdItem;
    }

    public int getTradeId(){
        return tradeID;
    }

    public String getUserId() {
        return userId;
    }

    public int getPrice() {
        return price;
    }

    public Item getAuctionItem() {
        return auctionItem;
    }

    public int getCount(){
        return auctionItem.getCount();
    }

    public String getName(){
        return auctionItem.getName();
    }

    public String[] getListData(){
        return new String[]{userId, 
                            auctionItem.getName(), 
                            auctionItem.getGrade(), 
                            auctionItem.getDesc(),
                            Integer.toString(auctionItem.getCount()), 
                            Integer.toString(auctionItem.getOption1()),
                            Integer.toString(price)};
    }
}
