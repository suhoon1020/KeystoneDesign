package CommandManage.InventoryItems;


import CommandManage.Command;
import CommandManage.Invoker;
import CommandManage.TradeItems.CreateTradeItemCommand;
import Item.Item;
import user.InventoryItem;
import user.User;


public class ItemInvToTradeCommend implements Command {
    User user;
    String itemName;
    int price;
    int count;
    Invoker invoker;

    public ItemInvToTradeCommend(User user, String itemName, int price, int count) {
        this.user = user;
        this.itemName = itemName;
        this.price = price;
        this.count = count;
        invoker = new Invoker();
    }

    @Override
    public void execute() {
        InventoryItem userInventoryItem = user.getItemByName(itemName);

        if(userInventoryItem != null){
            Item item = userInventoryItem.getItem();

            // 거래 후 아이템 정보
            InventoryItem inventoryItem = new InventoryItem(item, userInventoryItem.getCount() - count);

            if(inventoryItem.getCount() > 0){
                // 아이템 갯수가 많음 >> 아이템 수량 수정
                UpdateInvItemCommand updateInvItemCommand = new UpdateInvItemCommand(user, inventoryItem);
                invoker.setCommand(updateInvItemCommand);
                invoker.run();
            }
            else if(inventoryItem.getCount() == 0){
                // 아이템 갯수가 같음 >> 아이템 삭제
                DeleteInvItemCommand deleteInvItemCommand = new DeleteInvItemCommand(user, itemName);
                invoker.setCommand(deleteInvItemCommand);
                invoker.run();
            }

            // 거래품목 등록
            CreateTradeItemCommand createTradeItemCommand = new CreateTradeItemCommand(user.getName(), item, count, price);
            invoker.setCommand(createTradeItemCommand);
            invoker.run();
        }

    }
}
