package CommandManage.InvItem;

import CommandManage.Command;
import CommandManage.Invoker;
import auctionData.TradeItem;
import user.User;
import user.inventoryItem.Item;

public class ExtractItemToTItemCommend implements Command {
    User user;
    String itemName;
    int price;
    int count;
    Invoker invoker;

    public ExtractItemToTItemCommend(User user, String itemName, int price, int count) {
        this.user = user;
        this.itemName = itemName;
        this.price = price;
        this.count = count;
        invoker = new Invoker();
    }

    @Override
    public void execute() {
        Item item = user.getItemByName(itemName);

        if(item != null){
            TradeItem tradeItem = new TradeItem(user.getName(), item, count, price);
            // 아이템 창에서 아이템 삭제
            // 거래품목 등록
        }

    }
}
