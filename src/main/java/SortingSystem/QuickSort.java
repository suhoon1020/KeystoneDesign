package SortingSystem;

import java.util.Collections;
import java.util.List;

import ItemsManager.Item;

public class QuickSort {

  public static void quickSort(List<Item> list) {
    quickSort(list, 0, list.size() - 1);
  }

  private static void quickSort(List<Item> list, int start, int end) {
    if (list.get(start).getName().compareTo(list.get(end).getName()) == 0)
      return;
    
    int pivot = start;
    int lo = start + 1;
    int hi = end;
    
    while (lo <= hi) {
      while (lo <= end && list.get(lo).getName().compareTo(list.get(pivot).getName()) <= 0) 
        lo++;
      while (hi > start && list.get(hi).getName().compareTo(list.get(pivot).getName()) >= 0) 
        hi--;
      if (lo > hi)				
        Collections.swap(list, hi, pivot);
      else
        Collections.swap(list, lo, hi);
      }
	
    quickSort(list, start, hi - 1);
    quickSort(list, hi + 1, end);

  }
}