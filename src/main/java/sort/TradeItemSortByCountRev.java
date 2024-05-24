package deu.cse.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import deu.cse.auctionData.TradeItem;

public class TradeItemSortByCountRev implements TradeItemSort{
    @Override
    public List<TradeItem> sort(List<TradeItem> list) {
        List<TradeItem> sortedItemList = new ArrayList<TradeItem>(list);

        Collections.sort(sortedItemList, Comparator.comparingInt(TradeItem::getCount).reversed()); 
        
        return sortedItemList;
    }
}
