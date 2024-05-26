package CommandManage.InventoryItems;

import CommandManage.Command;
import CommandManage.Invoker;
import CommandManage.Users.UpdateUserCommand;
import user.User;

import javax.swing.*;

public class DeleteInvItemCommand implements Command {
    User user;
    String itemName;

    public DeleteInvItemCommand(User user, String itemName) {
        this.user = user;
        this.itemName = itemName;
    }

        @Override
    public void execute() {
        if(user.deleteItem(itemName)){
            UpdateUserCommand updateUserCommand = new UpdateUserCommand(user);
            Invoker invoker = new Invoker();
            invoker.setCommand(updateUserCommand);
            invoker.run();
            JOptionPane.showMessageDialog(null,"아이템이 삭제되었습니다");
        }
        else{
            JOptionPane.showMessageDialog(null,"해당 아이템을 찾을 수 없습니다");
        }
    }
}