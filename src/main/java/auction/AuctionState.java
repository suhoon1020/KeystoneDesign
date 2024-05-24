package auction;


public interface AuctionState {
    public boolean login(String id, String password);
    public AuctionState changeState();
    public boolean isOpen();
}
