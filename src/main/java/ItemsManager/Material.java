package ItemsManager;

public class Material extends Item{
    @Override
    public Item createItemInfos(ItemBuilder itemBuilder) {
        this.type = itemBuilder.type;
        this.name = itemBuilder.name;
        this.grade = itemBuilder.grade;
        this.desc = itemBuilder.desc;
        this.count = itemBuilder.count;

        return this;
    }
    
    @Override
    public String[] getData() {
        return new String[]{type, name, grade, desc, Integer.toString(count), "NONE"};
    }
}
