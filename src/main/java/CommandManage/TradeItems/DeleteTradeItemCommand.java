package CommandManage.TradeItems;

import CommandManage.Command;
import auctionData.TradeItem;
import managers.TradeItemFileSystem;

import javax.swing.*;
import java.util.List;

public class DeleteTradeItemCommand implements Command {
    int itemID;

    public DeleteTradeItemCommand(int itemID) {
        this.itemID = itemID;
    }

    @Override
    public void execute() {
        List<TradeItem> tradeItems = TradeItemFileSystem.getTradeItemFileSystem().getTradeItemList();

        for (int i = 0; i < tradeItems.size(); ++i) {
            if (tradeItems.get(i).getTradeId() == itemID) {
                tradeItems.remove(i);
                TradeItemFileSystem.getTradeItemFileSystem().saveInfosToFile();
                JOptionPane.showMessageDialog(null, "아이템이 삭제되었습니다");
                return;
            }
        }
        
        JOptionPane.showMessageDialog(null, "해당 아이템을 찾을 수 없습니다");
    }
}

