package ItemsManager;

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

    public abstract Item createItemInfos(ItemBuilder itemFactory);
    public abstract String[] getData();
}