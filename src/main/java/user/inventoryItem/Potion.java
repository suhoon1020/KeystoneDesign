package user.inventoryItem;

public class Potion extends Item {
    protected int effect;

    @Override
    protected void setSpecificAttributes(ItemBuilder itemBuilder) {
        effect = itemBuilder.option1;
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
