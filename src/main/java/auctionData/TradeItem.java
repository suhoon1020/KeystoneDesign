package auctionData;

import managers.TradeItemFileSystem;
import user.inventoryItem.Item;
import user.inventoryItem.ItemBuilder;

import java.util.List;

public class TradeItem {
    private static int TRADE = 0;
    private int tradeId;
    private String userName;
    private int price;
    private Item auctionItem;


    public TradeItem(String userName, int price, Item registerdItem) {
        this.tradeId = TRADE++;
        this.userName = userName;
        this.price = price;
        this.auctionItem = registerdItem;
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

    public Item getAuctionItem() {
        return auctionItem;
    }

    public String getType(){
        return auctionItem.getType();
    }

    public String getGrade(){
        return auctionItem.getGrade();
    }

    public String getName(){
        return auctionItem.getName();
    }

    public int getCount(){
        return auctionItem.getCount();
    }


    public String[] getListData(){
        return new String[]{Integer.toString(tradeId),
                userName, 
                auctionItem.getType(),
                auctionItem.getName(), 
                auctionItem.getGrade(), 
                auctionItem.getDesc(),
                Integer.toString(auctionItem.getCount()), 
                Integer.toString(auctionItem.getOption1()),
                Integer.toString(price)};
    }

    public Item getItem(){
        return new ItemBuilder()
                .type(auctionItem.getType())
                .name(auctionItem.getName())
                .grade(auctionItem.getGrade())
                .desc(auctionItem.getDesc())
                .count(auctionItem.getCount())
                .option1(auctionItem.getOption1())
                .build();
    }

    public TradeItem getTradeItemById(int id) {
        for (TradeItem tradeItem : TradeItemFileSystem.getTradeItemFileSystem().getTradeItemList()) {
            if (tradeItem.getTradeId() == id)
                return tradeItem;
        }

        return null;
    }

}
