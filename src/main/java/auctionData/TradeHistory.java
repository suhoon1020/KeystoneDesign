package auctionData;


public class TradeHistory {
    String buyerName;
    String sellerName;
    String itemName;
    int price;
    int charge;

    public int getPrice() {
        return price;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public String getSellerName() {
        return sellerName;
    }

    public String getItemName() {
        return itemName;
    }

    public int getCharge() {
        return charge;
    }


    public String[] getListData() {
        return new String[]{buyerName, sellerName, itemName, Integer.toString(price),Integer.toString(charge)};
    }


    public TradeHistory(String buyerID, String sellerID, String itemName, int price, int charge) {
        this.buyerName = buyerID;
        this.sellerName = sellerID;
        this.itemName = itemName;
        this.price = price;
        this.charge = charge;
    }

}


