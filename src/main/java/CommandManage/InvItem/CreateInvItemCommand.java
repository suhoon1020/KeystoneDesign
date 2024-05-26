package CommandManage.InvItem;

import CommandManage.Command;
import CommandManage.Invoker;
import CommandManage.Users.UpdateUserCommand;
import user.InventoryItem;
import user.User;

import javax.swing.*;

public class CreateInvItemCommand implements Command {
    User user;
    InventoryItem item;

    public CreateInvItemCommand(User user, InventoryItem Item) {
        this.item = Item;
        this.user = user;
    }

    @Override
    public void execute() {
        if(user.addItem(item)){
            UpdateUserCommand updateUserCommand = new UpdateUserCommand(user);
            Invoker invoker = new Invoker();
            invoker.setCommand(updateUserCommand);
            invoker.buttonPressed();
            JOptionPane.showMessageDialog(null,"아이템 생성이 완료되었습니다");
        }
        else{
            JOptionPane.showMessageDialog(null,"이미 존재하는 아이템입니다");
        }
    }
}
