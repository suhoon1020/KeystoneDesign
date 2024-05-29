package commandManage.tradeItems;

import commandManage.Command;

import javax.swing.*;

import auctionData.TradeItem;
import auctionData.TradeItemFileSystem;

import java.util.List;

public class DeleteTradeItemCommand implements Command {
    TradeItem tradeItem;

    public DeleteTradeItemCommand(TradeItem tradeItem) {
        this.tradeItem = tradeItem;
    }
    
    @Override
    public void execute() {
        List<TradeItem> tradeItems = TradeItemFileSystem.getTradeItemFileSystem().getTradeItemList();
        int tradeId = tradeItem.getTradeId();

        for (int i = 0; i < tradeItems.size(); ++i) {
            if (tradeItems.get(i).getTradeId() == tradeId) {
                tradeItems.remove(i);
                TradeItemFileSystem.getTradeItemFileSystem().saveInfosToFile();
                JOptionPane.showMessageDialog(null, "아이템이 삭제되었습니다");
                return;
            }
        }
        
        JOptionPane.showMessageDialog(null, "해당 아이템을 찾을 수 없습니다");
    }
}

