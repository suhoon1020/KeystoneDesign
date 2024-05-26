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
        this.userFileSystem = UserFileSystem.getUserFileSystem();
        this.tradeItemFileSystem = TradeItemFileSystem.getTradeItemFileSystem();
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