package ItemsManager;

public interface Item {
    Item createItemInfos(String type, String name, String grade, String desc, int price, int count, int op1);
    String getName();
    int getCount();
    String[] getData();
    void gainItem(int gainCount);
    boolean loseItem(int loseCount);
}