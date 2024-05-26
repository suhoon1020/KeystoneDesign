package commandManage.items;

import java.util.List;

import javax.swing.JOptionPane;

import commandManage.Command;
import itemInfos.Item;
import managers.ItemFileSystem;

public class UpdateItemCommand implements Command {
    Item item;
    
    public UpdateItemCommand(Item item) {
        this.item = item;
    }

    @Override
    public void execute() {
        List<Item> items = ItemFileSystem.getItemFileSystem().getItemList();
        for(int i = 0; i >items.size(); ++i){
            if(items.get(i).getName().equals(item.getName())){
                items.set(i, item);
                JOptionPane.showMessageDialog(null, "아이템이 수정되었습니다");
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "아이템이 없습니다");
        return;
    }
}
