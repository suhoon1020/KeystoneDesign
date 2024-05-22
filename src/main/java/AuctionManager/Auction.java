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
    private static Auction auction = new Auction();

    public Auction(){
        this.state = new CloseState();
    }

    public static Auction getAuction() {
        return auction;
    }

    public void setState(AuctionState state) {
        this.state = state;
    }

    public AuctionState getState() {
        return state;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void LoadAuction() {
        FileFacade.getFacade();
    }

    public void run() {
        state.run();
    }

    public void aucitonOff() {
        state.auctionOff();
    }

    public void auctionOn() {
        state.auctionOn();
    }


}
