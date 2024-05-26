package CommandManage.InventoryItems;

import CommandManage.Command;
import managers.UserFileSystem;
import user.InventoryItem;
import user.User;

import javax.swing.*;

public class UpdateInvItemCommand implements Command {
    InventoryItem item;
    User user;

    public UpdateInvItemCommand(User user, InventoryItem item) {
        this.item = item;
        this.user = user;
    }

    @Override
    public void execute() {
        if(user.updateItem(item)){
            UserFileSystem.getUserFileSystem().saveInfosToFile();
            JOptionPane.showMessageDialog(null,"아이템 정보가 수정되었습니다");
        }
        else{
            JOptionPane.showMessageDialog(null,"해당 아이템을 찾을 수 없습니다");
        }
    }
}
