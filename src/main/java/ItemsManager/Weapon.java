package ItemsManager;

public class Weapon extends Item {
    protected int damage;

    @Override
    protected void setSpecificAttributes(ItemBuilder itemBuilder) {
        this.damage = itemBuilder.option1;
    }

    @Override
    public String[] getData() {
        return new String[]{type, name, grade, desc, Integer.toString(count), Integer.toString(damage)};
    }
}
