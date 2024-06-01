import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import auctionData.TradeItem;
import itemInfos.Item;
import itemInfos.ItemFileSystem;
import sort.TradeItemSort;
import sort.TradeItemSortByName;
import sort.TradeItemSortByPrice;

public class StrategyTEST {
    public static void main(String[] args) {
        List<Item> itemList = ItemFileSystem.getItemFileSystem().getItemList();
        List<TradeItem> tradeItemList = new ArrayList<TradeItem>();
        List<TradeItem> sortedTradeItems;

        TradeItemSort tradeItemSort;
        Random random = new Random();

        // 랜덤 거래 아이템 생성
        for(Item item : itemList){
            int count = random.nextInt(1, 100);
            int price = random.nextInt(500, 10000);

            TradeItem tradeItem = new TradeItem("Auction", item.clone(), count, price);
            tradeItemList.add(tradeItem);
        }

        // 아이템 이름 정렬
        tradeItemSort = new TradeItemSortByName();
        sortedTradeItems = tradeItemSort.sort(tradeItemList);

        for(TradeItem tradeItem : sortedTradeItems){
            System.out.println("이름 : " + tradeItem.getName() + "\t\t개수 : " + tradeItem.getCount() + "\t\t가격 : " + tradeItem.getPrice());
        }

        System.out.println();
        System.out.println();

        // 아이템 가격 정렬
        tradeItemSort = new TradeItemSortByPrice();
        sortedTradeItems = tradeItemSort.sort(tradeItemList);

        for(TradeItem tradeItem : sortedTradeItems){
            System.out.println("이름 : " + tradeItem.getName() + "\t\t개수 : " + tradeItem.getCount() + "\t\t가격 : " + tradeItem.getPrice());
        }
    }
}
