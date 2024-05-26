package itemInfos;

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

    @Override
    public Item clone() {
        return new ItemBuilder()
            .type(type)
            .name(name)
            .grade(grade)
            .desc(desc)
            .option1(defence)
            .build();
    }

}
