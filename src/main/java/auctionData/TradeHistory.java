package auctionData;


public class TradeHistory {
    String buyerId;
    String sellerId;
    String itemName;
    int price;
    int charge;

    public int getPrice() {
        return price;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getCharge() {
        return charge;
    }

    public String[] getListData() {
        return new String[]{buyerId, sellerId, itemName, Integer.toString(price), Integer.toString(charge)};
    }

    public TradeHistory(String buyerID, String sellerID, String itemName, int price, int charge) {
        this.buyerId = buyerID;
        this.sellerId = sellerID;
        this.itemName = itemName;
        this.price = price;
        this.charge = charge;
    }

}


