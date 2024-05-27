package itemObserver;

import itemInfos.Item;

public interface ItemSubject {
    void registerObserver(ItemObserver o);
    void removeObserver(ItemObserver o);
    void notifyObservers(Item item, String option);
}