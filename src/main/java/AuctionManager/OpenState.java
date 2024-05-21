package AuctionManager;

import javax.swing.*;

public class OpenState implements AuctionState{
    @Override
    public void handleRequest(JButton jButton){
        jButton.setEnabled(true);
    }
}
