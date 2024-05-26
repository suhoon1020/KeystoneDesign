package commandManage.inventoryItems;

import commandManage.Command;
import itemInfos.Item;
import managers.UserFileSystem;
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
            UserFileSystem.getUserFileSystem().saveInfosToFile();
            JOptionPane.showMessageDialog(null,"아이템 생성이 완료되었습니다");
        }
        else{
            JOptionPane.showMessageDialog(null,"이미 존재하는 아이템입니다");
        }
    }
}
