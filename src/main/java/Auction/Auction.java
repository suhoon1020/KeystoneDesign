package Auction;

import java.util.List;

import DataManager.FileFacade;
import ItemsManager.Item;
import SwingManager.SwingLogin;

public class Auction {
    public List<Item> Items;

    public void LoadAuction(){
        FileFacade.getFacade();
    }
    
    public void run(){
        new SwingLogin();
    }

    // TODO 인벤토리 로딩

}
