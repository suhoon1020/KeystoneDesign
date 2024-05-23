package AuctionManager;

import javax.swing.*;

public class CloseState implements AuctionState {
    @Override
    public void auctionOff(){
        JOptionPane.showMessageDialog(null,"거래소가 이미 닫혀있습니다");
    }

    @Override
    public void auctionOn() {
        JOptionPane.showMessageDialog(null,"거래소가 열렸습니다");
        Auction.getAuction().setState(new OpenState());
    }

    @Override
    public void login() {
        JOptionPane.showMessageDialog(null,"거래소가 닫혀있어 로그인에 실패하였습니다");
    }
}
