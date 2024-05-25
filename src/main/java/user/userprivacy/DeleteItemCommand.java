package user.userprivacy;

import user.inventoryItem.Item;

public class DeleteItemCommand implements Command{
    Item item;
    User user;

    public DeleteItemCommand(User user,Item Item) {
        this.item = Item;
        this.user = user;
    }

    @Override
    public void execute() {
        user.deleteItem(item.getName());
    }
}
