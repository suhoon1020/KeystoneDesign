package ItemsManager;

public interface Item {
    Item createItemInfos(String type, String name, String grade, String desc, int price, int op1);
    String getName();
}