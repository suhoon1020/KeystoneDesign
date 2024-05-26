package CommandManage.TradeItems;


import CommandManage.Command;
import CommandManage.Invoker;
import CommandManage.InventoryItems.CreateInvItemCommand;
import Item.Item;
import auctionData.TradeItem;
import managers.TradeItemFileSystem;
import user.User;

public class ItemTradeToInvCommend implements Command {
    User user;
    int tradeId;
    int count;
    Invoker invoker;

    public ItemTradeToInvCommend(User user, int tradeId, int count) {
        this.user = user;
        this.tradeId = tradeId;
        this.count = count;
        invoker = new Invoker();
    }

    @Override
    public void execute() {
        TradeItem auctionTradeItem = TradeItemFileSystem.getTradeItemFileSystem().getTradeItemByTradeId(tradeId);

        if(auctionTradeItem != null){
            Item item = auctionTradeItem.getItem();

            // 거래 후 아이템 정보
            TradeItem tradeItem = new TradeItem(auctionTradeItem.getUserName(), item, auctionTradeItem.getCount() - count, tradeId);

            if(tradeItem.getCount() > 0){
                // 아이템 갯수가 많음 >> 아이템 수량 수정
                UpdateTradeItemCommand updateTradeItemCommand = new UpdateTradeItemCommand(tradeId, tradeItem);
                invoker.setCommand(updateTradeItemCommand);
                invoker.run();
            }
            else if(tradeItem.getCount() == 0){
                // 아이템 갯수가 같음 >> 아이템 삭제
                DeleteTradeItemCommand deleteTradeItemCommand = new DeleteTradeItemCommand(tradeId);
                invoker.setCommand(deleteTradeItemCommand);
                invoker.run();
            }

            // 거래품목 등록
            CreateInvItemCommand createInvItemCommand = new CreateInvItemCommand(user, item, count);
            invoker.setCommand(createInvItemCommand);
            invoker.run();
        }

    }
}
