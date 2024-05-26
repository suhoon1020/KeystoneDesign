package itemInfos;

public abstract class Item {
    protected String type;
    protected String name;
    protected String grade;
    protected String desc;

    //템플릿 메소드
    public Item createItemInfos(ItemBuilder itemBuilder) {
        this.type = itemBuilder.type;
        this.name = itemBuilder.name;
        this.grade = itemBuilder.grade;
        this.desc = itemBuilder.desc;
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

    //하위 클래스에서 구현
    protected abstract void setSpecificAttributes(ItemBuilder itemBuilder);
    public abstract int getOption1();
    public abstract Item clone();
}