package Auction;


import DataManager.FileFacade;
import SwingManager.SwingLogin;

public class Auction {

    public void LoadAuction(){
        FileFacade.getFacade();
    }
    
    public void run(){
        new SwingLogin();
    }
}
