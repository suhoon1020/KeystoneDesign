package DataManager;

import ItemsManager.Item;

public class AuctionItem {
    String userId;
    int price;
    Item registerdItem;
    
    public AuctionItem(String userId, int price, Item registerdItem) {
        this.userId = userId;
        this.price = price;
        this.registerdItem = registerdItem;
    }

}
