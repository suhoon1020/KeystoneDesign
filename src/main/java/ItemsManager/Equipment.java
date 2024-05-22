package ItemsManager;

public class Equipment extends Item{
    protected int defence;

    @Override
    public Item createItemInfos(ItemBuilder itemBuilder) {
        this.type = itemBuilder.type;
        this.name = itemBuilder.name;
        this.grade = itemBuilder.grade;
        this.desc = itemBuilder.desc;
        this.count = itemBuilder.count;
        this.defence = itemBuilder.option1;

        return this;
    }
    
    @Override
    public String[] getData() {
        return new String[]{type, name, grade, desc, Integer.toString(count), Integer.toString(defence)};
    }
}
