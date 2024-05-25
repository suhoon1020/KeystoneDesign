package user.userprivacy;

import user.inventoryItem.Item;

public class UpdateItemCommand implements Command {
    Item item;
    User user;

    public UpdateItemCommand(User user,Item item) {
        this.item = item;
        this.user = user;
    }

    @Override
    public void execute() {
        user.updateItem(user, item);
    }
}
