package itemInfos;

public class Material extends Item{

    @Override
    public int getOption1() {
        return 0;
    }
    
    @Override
    public String[] getListData() {
        return new String[]{type, name, grade, desc, "0"};
    }

    @Override
    public void setSpecificAttributes(ItemBuilder itemBuilder){

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
