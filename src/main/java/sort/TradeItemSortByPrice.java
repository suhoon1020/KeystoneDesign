package sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import auctionData.TradeItem;


public class TradeItemSortByPrice implements TradeItemSort{

    @Override
    public List<TradeItem> sort(List<TradeItem> list) {
        List<TradeItem> sortedItemList = new ArrayList<TradeItem>(list);

        Collections.sort(sortedItemList, Comparator.comparingInt(TradeItem::getPrice)); 
        
        return sortedItemList;
    }
}
