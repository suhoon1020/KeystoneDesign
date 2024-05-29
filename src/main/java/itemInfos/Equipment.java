package itemInfos;

public class Equipment extends Item{
    protected int defence;

    @Override
    public int getOption1() {
        return defence;
    }

    @Override
    public String[] getListData() {
        return new String[]{type, name, grade, desc, Integer.toString(defence)};
    }


    @Override
    protected void setSpecificAttributes(ItemBuilder itemBuilder) {
        this.defence = itemBuilder.option1;
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
