package Auction;

import java.util.List;

import DataManager.FileFacade;
import ItemsManager.Item;
import SwingManager.SwingLogin;

public class Auction {

    public void LoadAuction(){
        FileFacade.getFacade();
    }
    
    public void run(){
        new SwingLogin();
    }
}
