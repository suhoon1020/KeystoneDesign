package itemInfos;

public class Material extends Item{
    @Override
    public void setSpecificAttributes(ItemBuilder itemBuilder){

    }

    @Override
    public int getOption1() {
        return 0;
    }

    @Override
    public Item clone() {
        return new ItemBuilder()
            .type(type)
            .name(name)
            .grade(grade)
            .desc(desc)
            .option1(0)
            .build();
    }
}
