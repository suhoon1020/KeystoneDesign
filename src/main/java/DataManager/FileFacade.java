package DataManager;

import java.util.List;

import ItemsManager.Item;
import UserOption.User;


public class FileFacade {
    private static FileFacade fileFacade;

    private ItemFileSystem itemFileSystem;
    private UserFileSystem userFileSystem;
    private AuctionFileSystem auctionFileSystem;
    private TradeHistory tradeHistory;

    private FileFacade() {
        this.itemFileSystem = new ItemFileSystem();
        this.userFileSystem = new UserFileSystem();

        itemFileSystem.loadInfosFromFile();
        userFileSystem.loadInfosFromFile();
        //auctionFileSystem.loadRegisterdItems();
    }

    public static FileFacade getFacade(){
        if(fileFacade == null){
            fileFacade = new FileFacade();
        }
        return fileFacade;
    }

    /* 
     *      USERS
     */

    public void saveUsers() {
        userFileSystem.saveInfosToFile();
    }

    public void loadUsers() {
        userFileSystem.loadInfosFromFile();
    }

    public User getUser(String id) {
        return userFileSystem.getUser(id);
    }

    public List<User> getUsersList(){
        return userFileSystem.getUsersList();
    }

    public boolean putUser(User user) {
        return userFileSystem.putUser(user);
    }

    public Boolean isExistingUser(String id) {return userFileSystem.isExistID(id);}

    public boolean updateUser(String id, User user){
        return userFileSystem.updateUser(id, user);
    }

    public Boolean deleteUser(String id){
        return userFileSystem.deleteUser(id);
    }

    /* 
     *      ITEMS
     */

    public void saveItems() {
        itemFileSystem.saveInfosToFile();
    }

    public void loadItems() {
        itemFileSystem.loadInfosFromFile();
    }

    public boolean putItem(Item item) {
        return itemFileSystem.putItem(item);
    }

    public Item getItem(String name){
        return itemFileSystem.getItem(name);
    }

    public List<Item> getItemList(){
        return itemFileSystem.getItemList();
    }

    public boolean updateItem(String name, Item item){
        return itemFileSystem.updateItem(name, item);
    }

    public boolean deleteItem(String name){
        return itemFileSystem.deleteItem(name);
    }


    /*
     *       AuctionItems
     */
    //todo auctionitems 퍼사드에 넣어야함
    public void saveAuctionItems(){
        auctionFileSystem.saveRegisterdItems();
    }
    public void loadAuctionItems(){
        auctionFileSystem.loadRegisterdItems();
    }

    public List<AuctionItem> getAuctionItems(){
        return auctionFileSystem.getAuctionItems();
    }


    /*
    TRADING
     */
    //todo 거래 시스템 구현 해야함
    public void uploadItem(AuctionItem auctionItem){
        /*
        유저 인벤에서 삭제
        경매장 파일에 등록
         */
        for(User user : FileFacade.fileFacade.getUsersList()){
            if(user.getUserID().equals(auctionItem.getUserId())){
                user.getItems().remove(auctionItem.getAuctionItem());
                //경매장에 등록
                AuctionFileSystem.getAuctionItems().add(auctionItem);
            }
        }
    }

    public void unloadItem(AuctionItem auctionItem){
        AuctionFileSystem.getAuctionItems().remove(auctionItem);
    }

    public void buyItem(String buyerID, String sellerID, AuctionItem auctionItem){
        /*
         buyer 인벤에 아이템 추가
         경매장 에서 아이템 삭제
         buyer 골드 차감
         seller 골드 추가
         */

        //buyer 인벤에 아이템 추가 , 골드 차감
        for(User user : FileFacade.fileFacade.getUsersList()){
            if(user.getUserID().equals(buyerID)){
                user.getItems().add(auctionItem.getAuctionItem());
                user.setUserGold(user.getGold()-auctionItem.getPrice());
            }
        }

        //경매장에서 아이템 삭제
        unloadItem(auctionItem);

        //seller에게 골드 추가
        for(User user : FileFacade.fileFacade.getUsersList()){
            if(user.getUserID().equals(sellerID)){
                user.setUserGold(user.getGold()+auctionItem.getPrice());
            }
        }

    }
}