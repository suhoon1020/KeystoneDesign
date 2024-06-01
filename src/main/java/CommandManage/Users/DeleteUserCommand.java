package commandManage.users;

import commandManage.Command;
import itemInfos.ItemFileSystem;
import user.User;
import user.UserFileSystem;

import java.util.List;

import javax.swing.*;

import auctionData.TradeItem;
import auctionData.TradeItemFileSystem;

public class DeleteUserCommand implements Command {
    User user;

    public DeleteUserCommand(User user) {
        this.user = user;

    }

    @Override
    public void execute() {
        List<User> users = UserFileSystem.getUserFileSystem().getUserList();
        String userId = user.getId();

        for (int i = 0; i < users.size(); ++i) {
            if (users.get(i).getId().equals(userId)) {
                List<TradeItem> tradeItems = TradeItemFileSystem.getTradeItemFileSystem().getTradeItemList();

                // 유저 거래 아이템 삭제
                for (int j = 0; j < tradeItems.size(); ++j) {
                    if (tradeItems.get(i).getUserId().equals(userId)) {
                        tradeItems.remove(j);
                    }
                }

                // 유저 옵저버 삭제
                ItemFileSystem.getItemFileSystem().removeObserver(user);

                // 유저 삭제
                users.remove(i);

                TradeItemFileSystem.getTradeItemFileSystem().saveInfosToFile();
                UserFileSystem.getUserFileSystem().saveInfosToFile();
                JOptionPane.showMessageDialog(null, userId + " 회원님의 정보가 삭제되었습니다");
                return;
            }
        }
        JOptionPane.showMessageDialog(null,"해당 유저를 찾을 수 없습니다");
    }
}
