package auctionData;

import user.inventoryItem.Item;

public class TradeItem {
    private static int TRADE = 0;
    private int tradeId;
    private String userId;
    private int price;
    private Item auctionItem;

    public TradeItem(String userId, int price, Item registerdItem) {
        this.tradeId = TRADE++;
        this.userId = userId;
        this.price = price;
        this.auctionItem = registerdItem;
    }

    public int getTradeId(){
        return tradeId;
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
        return new String[]{Integer.toString(tradeId),
                            userId, 
                            auctionItem.getType(),
                            auctionItem.getName(), 
                            auctionItem.getGrade(), 
                            auctionItem.getDesc(),
                            Integer.toString(auctionItem.getCount()), 
                            Integer.toString(auctionItem.getOption1()),
                            Integer.toString(price)};
    }
}
