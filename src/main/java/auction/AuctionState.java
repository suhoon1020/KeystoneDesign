package auction;

public interface AuctionState {
    public boolean login(String id, String password);
    public void setOpen();
    public void setClose();
    public String getStateString();
}
