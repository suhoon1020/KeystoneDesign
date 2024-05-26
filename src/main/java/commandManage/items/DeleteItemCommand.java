package commandManage.items;

import java.util.List;

import javax.swing.JOptionPane;

import commandManage.Command;
import item.Item;
import managers.ItemFileSystem;

public class DeleteItemCommand implements Command {
    String itemName;
    
    public DeleteItemCommand(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public void execute() {
        List<Item> items = ItemFileSystem.getItemFileSystem().getItemList();
        for(int i = 0; i >items.size(); ++i){
            if(items.get(i).getName().equals(itemName)){
                items.remove(i);
                JOptionPane.showMessageDialog(null, itemName + " 아이템이 삭제되었습니다");
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "아이템이 없습니다.");
        return;
    }
}
