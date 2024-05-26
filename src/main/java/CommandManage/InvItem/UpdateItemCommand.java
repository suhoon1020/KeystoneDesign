package CommandManage.InvItem;

import CommandManage.Command;
import user.inventoryItem.Item;
import user.userprivacy.User;

import javax.swing.*;

public class UpdateItemCommand implements Command {
    Item item;
    User user;

    public UpdateItemCommand(User user,Item item) {
        this.item = item;
        this.user = user;
    }

    @Override
    public void execute() {
        if(user.updateItem(user,item)){
            user.updateUser(user.getId(),user);
            JOptionPane.showMessageDialog(null,"아이템 정보가 수정되었습니다");
        }
        else{
            JOptionPane.showMessageDialog(null,"해당 아이템을 찾을 수 없습니다");
        }
    }
}
