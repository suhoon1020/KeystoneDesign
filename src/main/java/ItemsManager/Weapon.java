package ItemsManager;

public class Weapon implements Item {
    private String type;
    private String name;
    private String grade;
    private String desc;
    private int count;
    private int damage;

    @Override
    public Item createItemInfos(ItemBuilder itemBuilder) {
        this.type = itemBuilder.type;
        this.name = itemBuilder.name;
        this.grade = itemBuilder.grade;
        this.desc = itemBuilder.desc;
        this.count = itemBuilder.count;
        this.damage = itemBuilder.option1;

        return this;
    }

    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public int getCount() {
        return count;
    }

    @Override
    public String[] getData() {
        return new String[]{type, name, grade, desc, Integer.toString(count), Integer.toString(damage)};
    }

    @Override
    public void gainItem(int buyCount) {
        count += buyCount;
    }

    @Override
    public boolean loseItem(int sellCount) {
        if(count < sellCount)
            return false;

        count -= sellCount;
        return true;
    }
}
