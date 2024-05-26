package Item;

public class Equipment extends Item{
    protected int defence;

    @Override
    protected void setSpecificAttributes(ItemBuilder itemBuilder) {
        this.defence=itemBuilder.option1;
    }

    @Override
    public int getOption1() {
        return defence;
    }

}
