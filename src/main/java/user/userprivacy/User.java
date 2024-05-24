package user.userprivacy;

import java.util.ArrayList;
import java.util.List;

import managers.FileFacade;
import managers.UserFileSystem;
import user.inventoryItem.Item;

import javax.swing.*;


public class User {
    private String id;
    private String password;
    private String name;
    private String phoneNumber;
    private int gold;
    private List<Item> itemList;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getGold(){
        return gold;
    }

    public List<Item> getItems(){
        return itemList;
    }

    public String[] getListData(){
        return new String[]{id, password, name, phoneNumber, Integer.toString(gold)};
    }

    public boolean addItem(Item newItem){
        if (checkItemByName(newItem.getName()))
            return false;

        itemList.add(newItem);
        return true;
    }

    public Boolean checkItemByName(String name) {
        for (Item item : itemList) {
            if (item.getName().equals(name))
                return true;
        }
        return false;
    }

    public boolean updateItem(String name, Item item){
        for (int i = 0; i < itemList.size(); ++i) {
            if (itemList.get(i).getName().equals(name)) {
                itemList.set(i, item);
                return true;
            }
        }
        return false;
    }

    public Boolean deleteItem(String name) {
        for (int i = 0; i < itemList.size(); ++i) {
            if (itemList.get(i).getName().equals(name)) {
                itemList.remove(i);
                return true;
            }
        }
        return false;
    }

    //Product
    private User(UserBuilder builder) {
        this.id = builder.ID;
        this.password = builder.password;
        this.name = builder.name;
        this.phoneNumber = builder.phoneNumber;
        this.gold = builder.gold;
        
        if(builder.itemList == null)
            this.itemList = new ArrayList<Item>();
        else
            this.itemList = builder.itemList;
    }

    //빌더 세팅
    public static class UserBuilder {
        private String ID;
        private String password;
        private String name;
        private String phoneNumber;
        private int gold;
        private List<Item> itemList;

        public UserBuilder ID(String ID) {
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

        public UserBuilder itemList(List<Item> itemList){
            this.itemList = itemList;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public void createUser(User user){
        //유저 생성
        if(UserFileSystem.getUserFileSystem().putUser(user)){
            JOptionPane.showMessageDialog(null,"유저 생성이 완료 되었습니다");
        }
        else{
            JOptionPane.showMessageDialog(null,"중복된 ID 가 있습니다");
        }
    }
    public void deleteUser(User user){
        //유저 삭제
        if(UserFileSystem.getUserFileSystem().deleteUser(user.getId())){
            JOptionPane.showMessageDialog(null,"해당 유저가 삭제 되었습니다");
        }
        else{
            JOptionPane.showMessageDialog(null,"존재하지 않는 유저 입니다");
        }
    }
    public void updateUser(User user){
        //유저 업뎃
        if(UserFileSystem.getUserFileSystem().updateUser(user.getId(),user)){
            JOptionPane.showMessageDialog(null,"유저 정보가 수정되었습니다");
        }
        else{
            JOptionPane.showMessageDialog(null,"존재하지 않는 유저입니다");
        }
    }

}
