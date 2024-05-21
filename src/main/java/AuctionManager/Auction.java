package AuctionManager;


import DataManager.FileFacade;
import ItemsManager.Item;
import SwingManager.SwingLogin;
import UserOption.User;

import javax.swing.*;
import java.util.List;

public class Auction {


    private AuctionState state;
    User user;
    private static Auction auction;

    private Auction() {
        this.state = new OpenState();
    }

    public static Auction getAuction() {
        if (auction == null) {
            auction = new Auction();
        }
        return auction;
    }

    public void setState(AuctionState state) {
        this.state = state;
    }

    public AuctionState getState() {
        return state;
    }

    public void handleRequest(JButton jButton) {
        state.handleRequest(jButton);
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
