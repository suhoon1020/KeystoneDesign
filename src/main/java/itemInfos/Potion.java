package itemInfos;

public class Potion extends Item {
    protected int effect;

    @Override
    public int getOption1() {
        return effect;
    }

    @Override
    protected void setSpecificAttributes(ItemBuilder itemBuilder) {
        effect = itemBuilder.option1;
    }

    @Override
    public String[] getListData() {
        return new String[]{type, name, grade, desc, Integer.toString(effect)};
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
