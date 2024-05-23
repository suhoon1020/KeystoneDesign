package ItemsManager;

import DataManager.FileFacade;

public abstract class Item {
    protected String type;
    protected String name;
    protected String grade;
    protected String desc;
    protected int count;

    public String getName(){
        return name;
    }
    
    public int getCount(){
        return count;
    }

    public void gainItem(int buyCount) {
        count += buyCount;
    }

    public boolean loseItem(int sellCount) {
        if(count < sellCount)
            return false;

        count -= sellCount;
        return true;
    }

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

    //하위 클래스에서 구현
    protected abstract void setSpecificAttributes(ItemBuilder itemBuilder);

    public abstract String[] getData();

    public static void makeDefaultItem(String userID){
        Item item = new ItemBuilder()
                .type("Material")
                .name("나뭇가지")
                .grade("Common")
                .desc("")
                .count(1)
                .build();

        Item item2 = new ItemBuilder()
                .type("Weapon")
                .name("대검")
                .grade("Common")
                .desc("")
                .option1(8)
                .count(1)
                .build();

        Item item3 = new ItemBuilder()
                .type("Equipment")
                .name("투구")
                .grade("Legendary")
                .desc("")
                .option1(25)
                .count(1)
                .build();

        FileFacade.getFacade().getUser(userID).getItems().add(item);
        FileFacade.getFacade().getUser(userID).getItems().add(item2);
        FileFacade.getFacade().getUser(userID).getItems().add(item3);
    }
}