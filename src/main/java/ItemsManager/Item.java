package ItemsManager;

public interface Item {
    Item createItemInfos(ItemBuilder itemFactory);
    String getName();
    int getCount();
    String[] getData();
    void gainItem(int gainCount);
    boolean loseItem(int loseCount);
}