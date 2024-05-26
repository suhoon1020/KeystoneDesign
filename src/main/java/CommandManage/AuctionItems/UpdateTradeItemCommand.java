package CommandManage.AuctionItems;

import CommandManage.Command;
import auctionData.TradeItem;
import managers.TradeItemFileSystem;
import javax.swing.*;
import java.util.List;

public class UpdateTradeItemCommand implements Command {
    int tradeId;
    TradeItem tradeItem;

    public UpdateTradeItemCommand(int tradeId, TradeItem tradeItem) {
        this.tradeId = tradeId;
        this.tradeItem = tradeItem;
    }

    @Override
    public void execute() {
        List<TradeItem> tradeItems = TradeItemFileSystem.getTradeItemFileSystem().getTradeItemList();

        for (int i = 0; i < tradeItems.size(); ++i) {
            if (tradeItems.get(i).getTradeId() == tradeId) {
                tradeItems.set(i, tradeItem);
                TradeItemFileSystem.getTradeItemFileSystem().saveInfosToFile();
                JOptionPane.showMessageDialog(null,"정보가 수정되었습니다");
                return;
            }
        }
        JOptionPane.showMessageDialog(null,"해당 아이템을 찾을 수 없습니다");
    }
}
