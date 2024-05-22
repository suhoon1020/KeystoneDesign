package ItemsManager;

public class Potion extends Item {
    protected int effect;

    @Override
    public Item createItemInfos(ItemBuilder itemBuilder) {
        this.type = itemBuilder.type;
        this.name = itemBuilder.name;
        this.grade = itemBuilder.grade;
        this.desc = itemBuilder.desc;
        this.count = itemBuilder.count;
        this.effect = itemBuilder.option1;

        return this;
    }

    @Override
    public String[] getData() {
        return new String[]{type, name, grade, desc, Integer.toString(count), Integer.toString(effect)};
    }
}
