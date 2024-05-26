package CommandManage.AuctionItems;

import CommandManage.Command;
import auctionData.TradeItem;
import managers.TradeItemFileSystem;
import user.inventoryItem.Item;

import javax.swing.*;

public class CreateTItemCommand implements Command {
    String userName;
    int price;
    Item item;

    public CreateTItemCommand(String userName, int price, Item item) {
        this.userName = userName;
        this.price = price;
        this.item = item;
    }

    @Override
    public void execute() {
        TradeItem newitem = new TradeItem(userName, item, item.getCount(), price);
        TradeItemFileSystem.getTradeItemFileSystem().getTradeItemList().add(newitem);
        TradeItemFileSystem.getTradeItemFileSystem().saveInfosToFile();
        JOptionPane.showMessageDialog(null,"아이템이 등록 되었습니다");
    }
}

