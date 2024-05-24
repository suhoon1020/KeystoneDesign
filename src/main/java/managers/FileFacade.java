package managers;

import java.util.List;

import auctionData.TradeItem;
import user.userprivacy.User;

public class FileFacade {
    private static FileFacade fileFacade;

    private UserFileSystem userFileSystem;
    private TradeItemFileSystem tradeItemFileSystem;
    private TradeHistoryFileSystem tradeHistoryFileSystem;

    private FileFacade() {
        this.userFileSystem = new UserFileSystem();
        this.tradeItemFileSystem = new TradeItemFileSystem();
        this.tradeHistoryFileSystem = new TradeHistoryFileSystem();

        userFileSystem.loadInfosFromFile();
        tradeItemFileSystem.loadInfosFromFile();
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

    public boolean updateUser(String id, User user){
        return userFileSystem.updateUser(id, user);
    }

    public Boolean deleteUser(String id){
        return userFileSystem.deleteUser(id);
    }

    /*
     *       TRADE ITEM
     */

    public void saveTradeItems(){
        tradeItemFileSystem.saveInfosToFile();
    }

    public void loadTradeItems(){
        tradeItemFileSystem.loadInfosFromFile();
    }

    public void putTradeItem(TradeItem newTradeItem){
        tradeItemFileSystem.putTradeItem(newTradeItem);
    }

    public boolean updateTradeItem(int id, TradeItem item){
        return tradeItemFileSystem.updateItem(id, item);
    }

    public Boolean deleteTradeItem(int id){
        return tradeItemFileSystem.deleteTradeItem(id);
    }

    public List<TradeItem> getTradeItemList(){
        return tradeItemFileSystem.getTradeItemList();
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