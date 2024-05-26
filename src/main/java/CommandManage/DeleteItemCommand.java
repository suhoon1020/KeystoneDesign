package CommandManage;

import managers.UserFileSystem;
import user.inventoryItem.Item;
import user.userprivacy.User;

import javax.swing.*;

public class DeleteItemCommand implements Command{
    User user;
    String itemName;

    public DeleteItemCommand(User user,String itemName) {
        this.user = user;
        this.itemName = itemName;
    }

    @Override
    public void execute() {
        if(user.deleteItem(user,itemName)){
            user.updateUser(user.getId(),user);
            JOptionPane.showMessageDialog(null,"아이템이 삭제되었습니다");
        }
        else{
            JOptionPane.showMessageDialog(null,"해당 아이템을 찾을 수 없습니다");
        }
    }
}
