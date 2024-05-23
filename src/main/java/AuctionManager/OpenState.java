package AuctionManager;

import SwingManager.SwingAuction;
import SwingManager.SwingLogin;

import javax.swing.*;

public class OpenState implements AuctionState {
    @Override
    public void auctionOff(){
        JOptionPane.showMessageDialog(null,"거래소가 닫혔습니다");
        Auction.getAuction().setState(new CloseState());
    }

    @Override
    public void auctionOn() {
        JOptionPane.showMessageDialog(null,"거래소가 이미 열려있습니다");
    }

    @Override
    public void login() {
        JOptionPane.showMessageDialog(null,"로그인 되었습니다");
        SwingAuction.getSwingAuction().setVisible(true);
    }
}

