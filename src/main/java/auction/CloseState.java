package auction;


public class CloseState implements AuctionState {

    @Override
    public boolean login(String id, String password) {
        return false;
    }

    @Override
    public AuctionState changeState() {
        return new OpenState();
    }

    @Override
    public boolean isOpen() {
        return false;
    }
}
