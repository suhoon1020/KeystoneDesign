package CommandManage;

import auctionData.TradeItem;
import managers.TradeHistoryFileSystem;
import managers.TradeItemFileSystem;
import managers.UserFileSystem;

public class LoadCommand implements Command{

    @Override
    public void execute() {
        UserFileSystem.getUserFileSystem().loadInfosFromFile();
        TradeItemFileSystem.getTradeItemFileSystem().loadInfosFromFile();
        TradeHistoryFileSystem.getTradeItemFileSystem().loadInfosFromFile();
    }

}
