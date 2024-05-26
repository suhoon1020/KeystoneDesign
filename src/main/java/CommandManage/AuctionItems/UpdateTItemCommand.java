package CommandManage.AuctionItems;

import CommandManage.Command;
import auctionData.TradeItem;
import managers.TradeItemFileSystem;
import user.inventoryItem.Item;

import javax.swing.*;
import java.util.List;

public class UpdateTItemCommand implements Command {
    int id;
    TradeItem tradeItem;
    List<TradeItem> tradeItems = TradeItemFileSystem.getTradeItemFileSystem().getTradeItemList();

    public UpdateTItemCommand(int id, TradeItem tradeItem) {
        this.id = id;
        this.tradeItem = tradeItem;
    }

    @Override
    public void execute() {
            for (int i = 0; i < tradeItems.size(); ++i) {
                if (tradeItems.get(i).getTradeId() == id) {
                    tradeItems.set(i, tradeItem);
                    TradeItemFileSystem.getTradeItemFileSystem().saveInfosToFile();
                    JOptionPane.showMessageDialog(null,"정보가 수정되었습니다");
                    return;
                }
            }
            JOptionPane.showMessageDialog(null,"정보를 수정 할 수 없습니다");
        }


}
