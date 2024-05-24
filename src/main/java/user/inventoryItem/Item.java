package deu.cse.user.inventoryItem;

public abstract class Item {
    protected String type;
    protected String name;
    protected String grade;
    protected String desc;
    protected int count;

    //템플릿 메소드
    public Item createItemInfos(ItemBuilder itemBuilder) {
        this.type = itemBuilder.type;
        this.name = itemBuilder.name;
        this.grade = itemBuilder.grade;
        this.desc = itemBuilder.desc;
        this.count = itemBuilder.count;
        setSpecificAttributes(itemBuilder);
        return this;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getGrade() {
        return grade;
    }

    public String getDesc() {
        return desc;
    }

    public int getCount() {
        return count;
    }


    //하위 클래스에서 구현
    protected abstract void setSpecificAttributes(ItemBuilder itemBuilder);

    public abstract int getOption1();

    public abstract String[] getListData();
}