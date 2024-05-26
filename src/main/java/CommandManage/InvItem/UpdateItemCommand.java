package CommandManage.InvItem;

import CommandManage.Command;
import CommandManage.Invoker;
import CommandManage.Users.UpdateUserCommand;
import user.User;
import user.inventoryItem.Item;

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

        if(user.updateItem(item)){
            UpdateUserCommand updateUserCommand = new UpdateUserCommand(user);
            Invoker invoker = new Invoker();
            invoker.setCommand(updateUserCommand);
            invoker.buttonPressed();
            JOptionPane.showMessageDialog(null,"아이템 정보가 수정되었습니다");
        }
        else{
            JOptionPane.showMessageDialog(null,"해당 아이템을 찾을 수 없습니다");
        }
    }
}
