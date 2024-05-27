package itemObserver;

import itemInfos.Item;

public interface ItemObserver {
    void updateItem(Item newItem, String option);
}
