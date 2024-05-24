package sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import auctionData.TradeItem;

public class TradeItemSortByNameRev implements TradeItemSort{
    @Override
    public List<TradeItem> sort(List<TradeItem> list) {
        List<TradeItem> sortedItemList = new ArrayList<TradeItem>(list);

        Collections.sort(sortedItemList, Comparator.comparing(TradeItem::getName).reversed()); 
        
        return sortedItemList;
    }
}
