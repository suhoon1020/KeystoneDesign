package user;

import java.util.ArrayList;
import java.util.List;

import itemInfos.Item;
import itemObserver.ItemObserver;

public class User implements ItemObserver{
    private String id;
    private String password;
    private String name;
    private String phoneNumber;
    private int gold;
    private boolean isAdmin;
    
    private List<InventoryItem> itemList;

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getGold(){
        return gold;
    }

    public boolean isAdmin(){
        return isAdmin;
    }

    public List<InventoryItem> getItemList() {
        return itemList;
    }

    // 스윙 데이터 출력
    public String[] getListData(){
        return new String[]{id, password, name, phoneNumber, Integer.toString(gold), String.valueOf(isAdmin)};
    }

    public void setGold(int gold){
        this.gold = gold;
    }

    public void setAdmin(boolean isAdmin){
        this.isAdmin = isAdmin;
    }

    //Product
    private User(UserBuilder builder) {
        this.id = builder.ID;
        this.password = builder.password;
        this.name = builder.name;
        this.phoneNumber = builder.phoneNumber;
        this.isAdmin = builder.isAdmin;
        this.gold = builder.gold;
        
        if(builder.itemList == null)
            this.itemList = new ArrayList<InventoryItem>();
        else
            this.itemList = builder.itemList;
    }

    /*
     *      유저 인벤토리 관리
     */

    public InventoryItem getItemByName(String itemName){
        for(InventoryItem item : itemList){
            if(item.getName().equals(itemName)){
                return item;
            }
        }

        return null;
    }

    public void addItemCount(InventoryItem item){
        String addItemName = item.getName();

        // 유저의 아이템 이름이 중복이 되면 카운트 추가
        for(int i = 0; i < itemList.size(); ++i){
            if(itemList.get(i).getName().equals(addItemName)){
                itemList.get(i).setCount(itemList.get(i).getCount() + item.getCount());
                return;
            }
        }

        // 그게아니면 그냥 add
        itemList.add(item);
        return;
    }

    public boolean addItem(InventoryItem addItem){
        String addItemName = addItem.getName();

        // 유저의 아이템 이름이 중복이 되면 추가 x
        for(int i = 0; i < itemList.size(); ++i){
            if(itemList.get(i).getName().equals(addItemName)){
                return false;
            }
        }

        // 그게아니면 그냥 add
        itemList.add(addItem);
        return true;
    }

    public boolean updateItem(InventoryItem updateItem){
        String updateItemName = updateItem.getName();

        for(int i = 0; i < itemList.size(); ++i){
            if(itemList.get(i).getName().equals(updateItemName)){
                itemList.set(i, updateItem);
                return true;
            }
        }

        return false;
    }

    public boolean deleteItem(String deleteItemName){
        for(int i = 0; i < itemList.size(); ++i){
            if(itemList.get(i).getName().equals(deleteItemName)){
                itemList.remove(i);
                return true;
            }
        }

        return false;
    }

    // 아이템 업데이트
    @Override
    public void updateItem(Item newItem, String option) {
        String itemName = newItem.getName();
        
        switch (option) {
            case "Update":
                for (int i = 0; i < itemList.size(); ++i) {
                    if (itemList.get(i).getName().equals(itemName)) {
                        InventoryItem inventoryItem = itemList.get(i);
                        inventoryItem = new InventoryItem(newItem.clone(), inventoryItem.getCount());
                        itemList.set(i, inventoryItem);
                        break;
                    }
                }
                break;
            case "Delete":
                for (int i = 0; i < itemList.size(); ++i) {
                    if (itemList.get(i).getName().equals(itemName)) {
                        itemList.remove(i);
                        break;
                    }
                }
                break;
        }
    }


    //빌더 세팅
    public static class UserBuilder {
        private String ID;
        private String password;
        private String name;
        private String phoneNumber;
        private int gold;
        private boolean isAdmin;

        private List<InventoryItem> itemList;

        public UserBuilder id(String ID) {
            this.ID = ID;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserBuilder gold(int gold){
            this.gold = gold;
            return this;
        }

        public UserBuilder isAdmin(boolean isAdmin){
            this.isAdmin = isAdmin;
            return this;
        }

        public UserBuilder itemList(List<InventoryItem> itemList){
            this.itemList = itemList;
            return this;
        }
        

        public User build() {
            return new User(this);
        }
    }
}
