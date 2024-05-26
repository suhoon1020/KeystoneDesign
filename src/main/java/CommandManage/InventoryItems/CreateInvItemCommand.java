package CommandManage.InventoryItems;

import CommandManage.Command;
import CommandManage.Invoker;
import CommandManage.Users.UpdateUserCommand;
import Item.Item;
import user.InventoryItem;
import user.User;

import javax.swing.*;

public class CreateInvItemCommand implements Command {
    User user;
    Item item;
    int count;

    public CreateInvItemCommand(User user, Item Item, int count) {
        this.user = user;
        this.item = Item;
        this.count = count;
    }

    @Override
    public void execute() {
        InventoryItem inventoryItem = new InventoryItem(item, count);

        if(user.addItem(inventoryItem)){
            UpdateUserCommand updateUserCommand = new UpdateUserCommand(user);
            Invoker invoker = new Invoker();
            invoker.setCommand(updateUserCommand);
            invoker.run();
            JOptionPane.showMessageDialog(null,"아이템 생성이 완료되었습니다");
        }
        else{
            JOptionPane.showMessageDialog(null,"이미 존재하는 아이템입니다");
        }
    }
}
