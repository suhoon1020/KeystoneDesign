package ItemManager;

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
    public Item clone() {
        return new ItemBuilder()
            .type(type)
            .name(name)
            .grade(grade)
            .desc(desc)
            .option1(damage)
            .build();
    }
}
