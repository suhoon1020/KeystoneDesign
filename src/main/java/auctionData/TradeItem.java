package auctionData;

import user.inventoryItem.Item;

public class TradeItem {
    String userId;
    int price;
    Item auctionItem;

    public TradeItem(String userId, int price, Item registerdItem) {
        this.userId = userId;
        this.price = price;
        this.auctionItem = registerdItem;
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
