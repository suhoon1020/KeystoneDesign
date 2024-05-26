package commandManage.tradeItems;

import commandManage.Command;
import item.Item;
import auctionData.TradeItem;
import managers.TradeItemFileSystem;

import javax.swing.*;

public class CreateTradeItemCommand implements Command {
    String userName;
    Item item;
    int count;
    int price;

    public CreateTradeItemCommand(String userName, Item item, int count, int price) {
        this.userName = userName;
        this.item = item;
        this.price = price;
    }

    @Override
    public void execute() {
        TradeItem newitem = new TradeItem(userName, item, count, price);
        TradeItemFileSystem.getTradeItemFileSystem().getTradeItemList().add(newitem);
        TradeItemFileSystem.getTradeItemFileSystem().saveInfosToFile();
        JOptionPane.showMessageDialog(null,"아이템이 등록 되었습니다");
    }
}

