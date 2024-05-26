package CommandManage.AuctionItems;

import CommandManage.Command;
import auctionData.TradeItem;
import managers.TradeItemFileSystem;

import javax.swing.*;
import java.util.List;

public class DeleteTItemCommand implements Command {
    int itemID;
    List<TradeItem> tradeItems = TradeItemFileSystem.getTradeItemFileSystem().getTradeItemList();

    public DeleteTItemCommand(int itemID) {
        this.itemID = itemID;
    }

    @Override
    public void execute() {
        for (int i = 0; i < tradeItems.size(); ++i) {
            if (tradeItems.get(i).getTradeId() == itemID) {
                tradeItems.remove(i);
                TradeItemFileSystem.getTradeItemFileSystem().saveInfosToFile();
                JOptionPane.showMessageDialog(null, "아이템이 삭제되었습니다");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "해당 아이템을 삭제 할 수 없습니다");
    }
}

