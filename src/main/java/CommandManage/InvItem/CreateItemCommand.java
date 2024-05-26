package CommandManage.InvItem;

import CommandManage.Command;
import user.inventoryItem.Item;
import user.userprivacy.User;

import javax.swing.*;

public class CreateItemCommand implements Command {
    Item item;
    User user;
    public CreateItemCommand(User user,Item Item) {
        this.item = Item;
        this.user = user;
    }
    @Override
    public void execute() {
        if(user.addItem(user,item)){
            user.updateUser(user.getId(),user);
            JOptionPane.showMessageDialog(null,"아이템 생성이 완료되었습니다");
        }
        else{
            JOptionPane.showMessageDialog(null,"이미 존재하는 아이템입니다");
        }
    }
}
