package SortingSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ItemsManager.Item;

public class ItemSortByCount implements ItemSort{
    @Override
    public List<Item> sort(List<Item> list) {
        List<Item> sortedItemList = new ArrayList<Item>(list);

        Collections.sort(sortedItemList, Comparator.comparingInt(Item::getCount)); 

        return sortedItemList;
    }
}
