package user.userprivacy;

import user.inventoryItem.Item;

public class CreateItemCommand implements Command{
    Item item;
    User user;

    public CreateItemCommand(User user,Item Item) {
        this.item = Item;
        this.user = user;
    }
    @Override
    public void execute() {
        user.addItem(item);
    }
}
