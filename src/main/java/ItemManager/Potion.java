package ItemManager;

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
    public Item clone() {
        return new ItemBuilder()
            .type(type)
            .name(name)
            .grade(grade)
            .desc(desc)
            .option1(effect)
            .build();
    }

}
