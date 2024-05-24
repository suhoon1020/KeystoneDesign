package deu.cse.user.inventoryItem;

public class Material extends Item{

    @Override
    public void setSpecificAttributes(ItemBuilder itemBuilder){

    }

    @Override
    public int getOption1() {
        return 0;
    }

    @Override
    public String[] getListData() {
        return new String[]{type, name, grade, desc, Integer.toString(count), "NONE"};
    }
}
