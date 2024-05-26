package commandManage.items;

import javax.swing.JOptionPane;

import commandManage.Command;
import itemInfos.Item;
import managers.ItemFileSystem;

public class CreateItemCommand implements Command {
    Item item;
    
    public CreateItemCommand(Item item) {
        this.item = item;
    }

    @Override
    public void execute() {
        if(ItemFileSystem.getItemFileSystem().getItem(item.getName()) == null){
            ItemFileSystem.getItemFileSystem().getItemList().add(item);
            ItemFileSystem.getItemFileSystem().saveInfosToFile();

            JOptionPane.showMessageDialog(null,"아이템 생성에 성공하였습니다");
            return;
        }
        
        JOptionPane.showMessageDialog(null,"중복된 이름이 있습니다");
        return;
    }
}
