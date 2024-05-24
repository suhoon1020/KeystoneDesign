package managers;

import java.util.List;

import auctionData.TradeItem;
import user.userprivacy.User;

public class FileFacade {
    private static FileFacade fileFacade;

    private UserFileSystem userFileSystem;
    private TradeItemFileSystem tradeItemFIleSystem;
    private TradeHistoryFileSystem tradeHistoryFileSystem;

    private FileFacade() {
        this.userFileSystem = new UserFileSystem();
        this.tradeItemFIleSystem = new TradeItemFileSystem();
        this.tradeHistoryFileSystem = new TradeHistoryFileSystem();

        userFileSystem.loadInfosFromFile();
        tradeItemFIleSystem.loadInfosFromFile();
        tradeHistoryFileSystem.loadInfosFromFile();
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

    public User getUserById(String id) {
        return userFileSystem.getUserById(id);
    }

    public User getUserByName(String name){
        return userFileSystem.getUserByName(name);
    }

    public List<User> getUserList(){
        return userFileSystem.getUserList();
    }

    public boolean putUser(User user) {
        return userFileSystem.putUser(user);
    }

    public Boolean isExistingUser(String id) {
        return userFileSystem.isExistID(id);
    }

    public boolean updateUser(String id, User user){
        return userFileSystem.updateUser(id, user);
    }

    public Boolean deleteUser(String id){
        return userFileSystem.deleteUser(id);
    }


    /*
     *       TRADE ITEMS
     */

    public void saveTradeItems(){
        tradeItemFIleSystem.saveInfosToFile();
    }

    public void loadTradeItems(){
        tradeItemFIleSystem.loadInfosFromFile();
    }

    public List<TradeItem> getTradeItemList(){
        return tradeItemFIleSystem.getTradeItemList();
    }

    /*
     *      TRADING
     */

    public void uploadItem(TradeItem tradeItem){

    }

    public void unloadItem(TradeItem auctionItem){

    }

    public void buyItem(String buyerID, String sellerID, TradeItem tradeItem){
        /*
         buyer 인벤에 아이템 추가
         경매장 에서 아이템 삭제
         buyer 골드 차감
         seller 골드 추가
         */

    }
}