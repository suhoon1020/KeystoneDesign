package AuctionManager;

import javax.swing.*;

public class CloseState implements AuctionState {
    @Override
    public void handleRequest(JButton jButton) {
        jButton.setEnabled(false);
    }
}
