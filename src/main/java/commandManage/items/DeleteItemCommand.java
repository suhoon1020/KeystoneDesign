package commandManage.items;

import java.util.List;

import javax.swing.JOptionPane;

import commandManage.Command;
import itemInfos.Item;
import itemInfos.ItemFileSystem;

public class DeleteItemCommand implements Command {
    Item item;
    
    public DeleteItemCommand(Item item) {
        this.item = item;
    }

    @Override
    public void execute() {
        List<Item> items = ItemFileSystem.getItemFileSystem().getItemList();
        String itemName = item.getName();

        for(int i = 0; i < items.size(); ++i){
            if(items.get(i).getName().equals(itemName)){
                items.remove(i);
                
                ItemFileSystem.getItemFileSystem().saveInfosToFile();
                ItemFileSystem.getItemFileSystem().notifyObservers(item, "Delete");

                JOptionPane.showMessageDialog(null, itemName + " 아이템이 삭제되었습니다");
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "아이템이 없습니다.");
        return;
    }
}
