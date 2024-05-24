package user.inventoryItem;

public class Weapon extends Item {
    protected int damage;

    @Override
    protected void setSpecificAttributes(ItemBuilder itemBuilder) {
        this.damage = itemBuilder.option1;
    }
    
    @Override
    public int getOption1() {
        return damage;
    }

    @Override
    public String[] getListData() {
        return new String[]{type, name, grade, desc, Integer.toString(count), Integer.toString(damage)};
    }
}
