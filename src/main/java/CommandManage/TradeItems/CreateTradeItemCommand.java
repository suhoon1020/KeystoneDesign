package commandManage.tradeItems;

import commandManage.Command;
import itemInfos.Item;

import javax.swing.*;

import auctionData.TradeItem;
import auctionData.TradeItemFileSystem;

public class CreateTradeItemCommand implements Command {
    String userId;
    Item item;
    int count;
    int price;

    public CreateTradeItemCommand(String userId, Item item, int count, int price) {
        this.userId = userId;
        this.item = item;
        this.price = price;
    }

    @Override
    public void execute() {
        TradeItem newitem = new TradeItem(userId, item, count, price);
        TradeItemFileSystem.getTradeItemFileSystem().getTradeItemList().add(newitem);
        TradeItemFileSystem.getTradeItemFileSystem().saveInfosToFile();
        JOptionPane.showMessageDialog(null,"아이템이 등록 되었습니다");
    }
}

