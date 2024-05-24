package deu.cse.user.inventoryItem;

public class Potion extends Item {
    protected int effect;

    @Override
    protected void setSpecificAttributes(ItemBuilder itemBuilder) {

    }
    @Override
    public int getOption1() {
        return effect;
    }

    @Override
    public String[] getListData() {
        return new String[]{type, name, grade, desc, Integer.toString(count), Integer.toString(effect)};
    }
}
