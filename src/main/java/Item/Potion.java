package Item;

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

}
