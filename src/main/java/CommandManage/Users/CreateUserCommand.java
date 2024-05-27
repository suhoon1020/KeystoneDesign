package commandManage.users;

import commandManage.Command;
import itemInfos.Item;
import itemInfos.ItemFileSystem;
import user.InventoryItem;
import user.User;
import user.UserFileSystem;

import javax.swing.*;

public class CreateUserCommand implements Command {
    User user;

    public CreateUserCommand(User user) {
        this.user = user;
    }

    public void addDefaultItems(){
        InventoryItem inventoryItem;
        Item item;

        item = ItemFileSystem.getItemFileSystem().getItemByName("Iron Ore");
        inventoryItem = new InventoryItem(item, 5);
        user.addItem(inventoryItem);

        item = ItemFileSystem.getItemFileSystem().getItemByName("Healing Potion");
        inventoryItem = new InventoryItem(item, 5);
        user.addItem(inventoryItem);
    }

    @Override
    public void execute() {
        if (UserFileSystem.getUserFileSystem().getUserById(user.getId()) == null){
            if(UserFileSystem.getUserFileSystem().getUserByName(user.getName()) == null){
                UserFileSystem.getUserFileSystem().getUserList().add(user);
                addDefaultItems();

                ItemFileSystem.getItemFileSystem().registerObserver(user);

                // 저장
                UserFileSystem.getUserFileSystem().saveInfosToFile();
                JOptionPane.showMessageDialog(null,"유저 생성에 성공하였습니다");
                return;
            }
            JOptionPane.showMessageDialog(null,"중복된 이름이 있습니다");
            return;
        }

        JOptionPane.showMessageDialog(null,"중복된 ID가 있습니다");
        return;
    }
}
