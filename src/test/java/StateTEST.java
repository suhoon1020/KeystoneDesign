import auction.Auction;
import auction.CloseState;
import auction.OpenState;

public class StateTEST {
    public static void main(String[] args) {
        boolean isAuctionOpen;
        boolean isLoginSuccess;

        Auction.getAuction().setState(new OpenState());

        // 현재 경매장 상태
        isAuctionOpen = Auction.getAuction().isOpen();
        System.out.println("경매장 열림 상태: " + Boolean.toString(isAuctionOpen));
        
        // 관리자 로그인
        isLoginSuccess = Auction.getAuction().login("Admin", "1234");
        System.out.println("관리자 로그인 성공 : " + Boolean.toString(isLoginSuccess));

        // 유저 로그인
        isLoginSuccess = Auction.getAuction().login("User", "1234");
        System.out.println("관리자 로그인 성공 : " + Boolean.toString(isLoginSuccess));

        // 경매장 상태 바꾸기
        Auction.getAuction().setState(new CloseState());

        // 현재 경매장 상태
        isAuctionOpen = Auction.getAuction().isOpen();
        System.out.println("경매장 열림 상태: " + Boolean.toString(isAuctionOpen));
        
        // 관리자 로그인
        isLoginSuccess = Auction.getAuction().login("Admin", "1234");
        System.out.println("관리자 로그인 성공 : " + Boolean.toString(isLoginSuccess));

        // 유저 로그인
        isLoginSuccess = Auction.getAuction().login("User", "1234");
        System.out.println("관리자 로그인 성공 : " + Boolean.toString(isLoginSuccess));
    }
}
