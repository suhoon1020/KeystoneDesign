package AuctionManager;


import DataManager.FileFacade;
import ItemsManager.Item;
import SwingManager.SwingLogin;
import UserOption.User;

import java.util.List;

public class Auction {

    User user;
    private static Auction auction;

    public static Auction getAuction() {
        if (auction == null) {
            auction = new Auction();
        }
        return auction;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void LoadAuction() {
        FileFacade.getFacade();
    }

    public void run() {
        new SwingLogin();
    }
}
