package swing;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.regex.Pattern;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import auction.Auction;
import auctionData.TradeHistory;
import auctionData.TradeHistoryFileSystem;
import auctionData.TradeItem;
import auctionData.TradeItemFileSystem;
import commandManage.*;
import commandManage.users.CreateUserCommand;
import commandManage.users.DeleteUserCommand;
import commandManage.users.UpdateUserCommand;
import commandManage.inventoryItems.CreateInvItemCommand;
import commandManage.inventoryItems.DeleteInvItemCommand;
import commandManage.inventoryItems.UpdateInvItemCommand;
import commandManage.items.CreateItemCommand;
import commandManage.items.DeleteItemCommand;
import commandManage.items.UpdateItemCommand;
import commandManage.tradeItems.CreateTradeItemCommand;
import commandManage.tradeItems.DeleteTradeItemCommand;
import commandManage.tradeItems.UpdateTradeItemCommand;
import itemInfos.Item;
import itemInfos.ItemBuilder;
import itemInfos.ItemFileSystem;
import user.InventoryItem;
import user.User;
import user.UserFileSystem;


public class SwingAdmin extends JFrame {
    private User currentUser;
    private Invoker invoker = new Invoker();
    private static SwingAdmin swingAdmin = new SwingAdmin();

    private JPanel contentPane;

    // 거래목록 테이블
    private String[] tradeItemHeader = {"TRADEID", "USERID", "NAME", "GRADE", "COUNT", "PRICE"};
    private JTable T_tradeItemList;
    private JScrollPane S_tradeItemList;
    private DefaultTableModel tradeItemTableModel;

    // 거래목록 아이템 정보 목록 테이블 
    private JTable T_tradeItemInfoList;
    private JScrollPane S_tradeItemInfoList;
    private DefaultTableModel tradeItemInfoTableModel;

    // 거래목록 택스트필드
    private JTextField In_tradeItemId;
    private JTextField In_tradeItemUserName;
    private JTextField In_tradeItemName;
    private JTextField In_tradeItemGrade;
    private JTextField In_tradeItemCount;
    private JTextField In_tradeItemPrice;


    // 거래기록 테이블
    private String[] tradeHistoryHeader = {"BUYER", "SELLER", "ITEMNAME", "PRICE", "CHARGE"};
    private JTable T_tradeHistoryList;
    private JScrollPane S_tradeHistoryList;
    private DefaultTableModel tradeHistoryTableModel;

    // 거래기록 택스트필드
    private JTextField In_tradeHistoryBuyer;
    private JTextField In_tradeHistorySeller;
    private JTextField In_tradeHistoryName;
    private JTextField In_tradeHistoryPrice;
    private JTextField In_tradeHistoryCharge;
    private JTextField In_TotalCharge;


    // 아이템 정보 테이블
    private String[] itemInfoHeader = {"TYPE", "NAME", "GRADE", "DESC", "OPTION1"};
    private JTable T_itemInfoList;
    private JScrollPane S_itemInfoList;
    private DefaultTableModel itemInfoTableModel;

    // 아이템 정보 택스트필드 + 콤보박스
    private JComboBox<String> C_itemInfoType;
    private JTextField In_itemInfoName;
    private JComboBox<String> C_itemInfoGrade;
    private JTextField In_itemInfoDesc;
    private JTextField In_itemInfoOp1;
    private String[] itemTypes = {"Equipment", "Material", "Potion", "Weapon"};
    private String[] itemGrades = {"Common", "Uncommon", "Eqic", "Legendary"};


    // 유저 테이블
    private String[] userHeader = {"ID", "PW", "NAME", "PHONE", "GOLD", "ISADMIN"};
    private JTable T_userList;
    private JScrollPane S_userList;
    private DefaultTableModel userTableModel;

    // 유저 택스트필드 
    private JTextField In_userID;
    private JTextField In_userPassword;
    private JTextField In_userName;
    private JTextField In_userPhoneNumber;
    private JTextField In_userGold;

    // 유저 인벤토리 테이블
    private String[] userItemHeader = {"TYPE", "NAME", "GRADE", "DESC", "OPTION1", "COUNT"};
    private JTable T_userItemList;
    private JScrollPane S_userItemList;
    private DefaultTableModel userItemTableModel;

    // 유저 아이템 정보 리스트 테이블 
    private JTable T_userItemInfoList;
    private JScrollPane S_userItemInfoList;
    private DefaultTableModel userItemInfoTableModel;

    // 유저 인벤토리 택스트 필드
    private JTextField In_inventoryUser;
    private JTextField In_userItemType;
    private JTextField In_userItemName;
    private JTextField In_userItemGrade;
    private JTextField In_userItemDesc;
    private JTextField In_userItemOp1;
    private JTextField In_userItemCount;

    public static SwingAdmin getSwingAdmin() {
        return swingAdmin;
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SwingAdmin.getSwingAdmin().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public SwingAdmin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1102, 693);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel contents = new JPanel();
        CardLayout mainCardLayout = new CardLayout(0, 0);
        contents.setBorder(new LineBorder(new Color(0, 0, 0)));
        contents.setBounds(12, 107, 1062, 546);
        contents.setLayout(mainCardLayout);
        contentPane.add(contents);

        /*
         *      AuctionManage Page
         */

        JPanel tradeItemPage = new JPanel();
        tradeItemPage.setLayout(null);
        contents.add(tradeItemPage, "AuctionManagePage");

        JPanel tradeItemList = new JPanel();
        tradeItemList.setBorder(new LineBorder(new Color(0, 0, 0)));
        tradeItemList.setBounds(10, 10, 452, 526);
        tradeItemPage.add(tradeItemList);
        tradeItemList.setLayout(new GridLayout(0, 1, 0, 0));

        tradeItemTableModel = new DefaultTableModel(tradeItemHeader, 0);
        T_tradeItemList = new JTable(tradeItemTableModel);
        T_tradeItemList.getTableHeader().setReorderingAllowed(false);
        T_tradeItemList.getTableHeader().setResizingAllowed(false);
        S_tradeItemList = new JScrollPane();
        tradeItemList.add(S_tradeItemList);
        refreshTradeItemTable();

        JPanel tradeitemInfoList = new JPanel();
        tradeitemInfoList.setBorder(new LineBorder(new Color(0, 0, 0)));
        tradeitemInfoList.setBounds(474, 10, 393, 526);
        tradeItemPage.add(tradeitemInfoList);
        tradeitemInfoList.setLayout(new GridLayout(0, 1, 0, 0));

        tradeItemInfoTableModel = new DefaultTableModel(itemInfoHeader, 0);
        T_tradeItemInfoList = new JTable(tradeItemInfoTableModel);
        T_tradeItemInfoList.getTableHeader().setReorderingAllowed(false);
        T_tradeItemInfoList.getTableHeader().setResizingAllowed(false);
        S_tradeItemInfoList = new JScrollPane();
        tradeitemInfoList.add(S_tradeItemInfoList);
        refreshTradeItemInfoTable();

        JPanel tradeItemManageContent = new JPanel();
        tradeItemManageContent.setBorder(new LineBorder(new Color(0, 0, 0)));
        tradeItemManageContent.setBounds(879, 10, 171, 526);
        tradeItemPage.add(tradeItemManageContent);
        tradeItemManageContent.setLayout(null);

        JPanel tradeItemLab = new JPanel();
        tradeItemLab.setBounds(0, 0, 66, 414);
        tradeItemManageContent.add(tradeItemLab);
        tradeItemLab.setLayout(new GridLayout(0, 1, 0, 0));

        JLabel L_tradeItemTradeId = new JLabel("번호 :");
        L_tradeItemTradeId.setHorizontalAlignment(SwingConstants.CENTER);
        L_tradeItemTradeId.setFont(new Font("굴림", Font.PLAIN, 15));
        tradeItemLab.add(L_tradeItemTradeId);

        JLabel L_tradeItemUserName = new JLabel("판매자 :");
        L_tradeItemUserName.setHorizontalAlignment(SwingConstants.CENTER);
        L_tradeItemUserName.setFont(new Font("굴림", Font.PLAIN, 15));
        tradeItemLab.add(L_tradeItemUserName);

        JLabel L_tradeItemName = new JLabel("이름 :");
        L_tradeItemName.setHorizontalAlignment(SwingConstants.CENTER);
        L_tradeItemName.setFont(new Font("굴림", Font.PLAIN, 15));
        tradeItemLab.add(L_tradeItemName);

        JLabel L_tradeItemGrade = new JLabel("등급 :");
        L_tradeItemGrade.setHorizontalAlignment(SwingConstants.CENTER);
        L_tradeItemGrade.setFont(new Font("굴림", Font.PLAIN, 15));
        tradeItemLab.add(L_tradeItemGrade);

        JLabel L_tradeItemCount = new JLabel("개수 :");
        L_tradeItemCount.setHorizontalAlignment(SwingConstants.CENTER);
        L_tradeItemCount.setFont(new Font("굴림", Font.PLAIN, 15));
        tradeItemLab.add(L_tradeItemCount);

        JLabel L_tradeItemPrice = new JLabel("가격 :");
        L_tradeItemPrice.setHorizontalAlignment(SwingConstants.CENTER);
        L_tradeItemPrice.setFont(new Font("굴림", Font.PLAIN, 15));
        tradeItemLab.add(L_tradeItemPrice);

        JPanel tradeItemIn = new JPanel();
        tradeItemIn.setBounds(66, 0, 105, 414);
        tradeItemManageContent.add(tradeItemIn);
        tradeItemIn.setLayout(new GridLayout(0, 1, 0, 0));

        In_tradeItemId = new JTextField();
        In_tradeItemId.setEditable(false);
        tradeItemIn.add(In_tradeItemId);
        In_tradeItemId.setColumns(10);

        In_tradeItemUserName = new JTextField();
        In_tradeItemUserName.setEditable(false);
        tradeItemIn.add(In_tradeItemUserName);
        In_tradeItemUserName.setColumns(10);

        In_tradeItemName = new JTextField();
        In_tradeItemName.setEditable(false);
        tradeItemIn.add(In_tradeItemName);
        In_tradeItemName.setColumns(10);

        In_tradeItemGrade = new JTextField();
        In_tradeItemGrade.setEditable(false);
        tradeItemIn.add(In_tradeItemGrade);
        In_tradeItemGrade.setColumns(10);

        In_tradeItemCount = new JTextField();
        tradeItemIn.add(In_tradeItemCount);
        In_tradeItemCount.setColumns(10);

        In_tradeItemPrice = new JTextField();
        tradeItemIn.add(In_tradeItemPrice);
        In_tradeItemPrice.setColumns(10);

        JPanel tradeItemButtons = new JPanel();
        tradeItemButtons.setBounds(0, 414, 171, 112);
        tradeItemManageContent.add(tradeItemButtons);
        tradeItemButtons.setLayout(new GridLayout(0, 1, 0, 0));

        JButton Btt_createTradeItem = new JButton("아이템 생성");
        tradeItemButtons.add(Btt_createTradeItem);
        Btt_createTradeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String itemName = In_tradeItemName.getText();
                String countString = In_tradeItemCount.getText();
                String priceString = In_tradeItemPrice.getText();

                if (itemName.isEmpty())
                    JOptionPane.showMessageDialog(null, "항목을 선택해주세요");
                else if(!Pattern.matches("^[1-9]\\d*$", countString)){
                    JOptionPane.showMessageDialog(null, "개수에 올바른 숫자를 입력해주세요");
                }
                else if(!Pattern.matches("^[1-9]\\d*$", priceString)){
                    JOptionPane.showMessageDialog(null, "가격에 올바른 숫자를 입력해주세요");
                }
                else{
                    Item item = ItemFileSystem.getItemFileSystem().getItemByName(itemName);
                    int count = Integer.valueOf(countString);
                    int price = Integer.valueOf(priceString);

                    CreateTradeItemCommand createTItemCommand = new CreateTradeItemCommand("AUCTION", item.clone(), count, price);
                    invoker.setCommand(createTItemCommand);
                    invoker.run();

                    refreshTradeItemTable();
                }
            }
        });
        Btt_createTradeItem.setFont(new Font("굴림", Font.PLAIN, 15));

        JButton Btt_updateTradeItem = new JButton("아이템 수정");
        tradeItemButtons.add(Btt_updateTradeItem);
        Btt_updateTradeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String tradeIdString = In_tradeItemId.getText();
                String userName = In_tradeItemUserName.getText();
                String itemName = In_tradeItemName.getText();
                String countString = In_tradeItemCount.getText();
                String priceString = In_tradeItemPrice.getText();

                if(!Pattern.matches("^[1-9]\\d*$", tradeIdString)){
                    JOptionPane.showMessageDialog(null, "거래번호를 확인해주세요");
                }
                if (itemName.isEmpty())
                    JOptionPane.showMessageDialog(null, "이름을 채워주세요");
                else if(!Pattern.matches("^[1-9]\\d*$", countString)){
                    JOptionPane.showMessageDialog(null, "옵션에 올바른 숫자를 입력해주세요");
                }
                else if(!Pattern.matches("^[1-9]\\d*$", priceString)){
                    JOptionPane.showMessageDialog(null, "가격에 올바른 숫자를 입력해주세요");
                }
                else{
                    int tradeId = Integer.valueOf(tradeIdString);
                    Item item = ItemFileSystem.getItemFileSystem().getItemByName(itemName);
                    int count = Integer.valueOf(countString);
                    int price = Integer.valueOf(priceString);

                    TradeItem newitem = new TradeItem(userName, item.clone(), count, price);
                    
                    UpdateTradeItemCommand updateTItemCommand = new UpdateTradeItemCommand(tradeId, newitem);
                    invoker.setCommand(updateTItemCommand);
                    invoker.run();

                    refreshTradeItemTable();
                }
            }
        });
        Btt_updateTradeItem.setFont(new Font("굴림", Font.PLAIN, 15));

        JButton Btt_deleteTradeItem = new JButton("아이템 삭제");
        tradeItemButtons.add(Btt_deleteTradeItem);
        Btt_deleteTradeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String tradeIdString = In_tradeItemId.getText();

                if (tradeIdString.isEmpty())
                    JOptionPane.showMessageDialog(null, "아이템을 선택해주세요");
                else {
                    TradeItem tradeItem = TradeItemFileSystem.getTradeItemFileSystem().getTradeItemByTradeId(Integer.parseInt(In_tradeItemId.getText()));
                   
                    DeleteTradeItemCommand deleteTItemCommand = new DeleteTradeItemCommand(tradeItem);
                    invoker.setCommand(deleteTItemCommand);
                    invoker.run();

                    refreshTradeItemTable();
                }
            }
        });
        Btt_deleteTradeItem.setFont(new Font("굴림", Font.PLAIN, 15));

        T_tradeItemList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && T_tradeItemList.getSelectedRow() != -1) {
                    // 선택된 행의 데이터 출력
                    int selectedRow = T_tradeItemList.getSelectedRow();

                    In_tradeItemId.setText(T_tradeItemList.getValueAt(selectedRow, 0).toString());
                    In_tradeItemUserName.setText(T_tradeItemList.getValueAt(selectedRow, 1).toString());
                    In_tradeItemName.setText(T_tradeItemList.getValueAt(selectedRow, 2).toString());
                    In_tradeItemGrade.setText(T_tradeItemList.getValueAt(selectedRow, 3).toString());
                    In_tradeItemCount.setText(T_tradeItemList.getValueAt(selectedRow, 4).toString());
                    In_tradeItemPrice.setText(T_tradeItemList.getValueAt(selectedRow, 5).toString());
                }
            }
        });

        T_tradeItemInfoList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && T_tradeItemInfoList.getSelectedRow() != -1) {
                    // 선택된 행의 데이터 출력
                    int selectedRow = T_tradeItemInfoList.getSelectedRow();

                    In_tradeItemUserName.setText("AUCTION");
                    In_tradeItemName.setText(T_tradeItemInfoList.getValueAt(selectedRow, 1).toString());
                    In_tradeItemGrade.setText(T_tradeItemInfoList.getValueAt(selectedRow, 2).toString());
                    In_tradeItemCount.setText("");
                    In_tradeItemPrice.setText("");
                }
            }
        });

        /*
         *      HISTORY PAGE
         */

        JPanel tradeHistoryPage = new JPanel();
        tradeHistoryPage.setLayout(null);
        contents.add(tradeHistoryPage, "TradeHistoryPage");

        JPanel tradeHistoryList = new JPanel();
        tradeHistoryList.setBorder(new LineBorder(new Color(0, 0, 0)));
        tradeHistoryList.setBounds(12, 10, 822, 526);
        tradeHistoryPage.add(tradeHistoryList);
        tradeHistoryList.setLayout(new GridLayout(0, 1, 0, 0));

        tradeHistoryTableModel = new DefaultTableModel(tradeHistoryHeader, 0);
        T_tradeHistoryList = new JTable(tradeHistoryTableModel);
        T_tradeHistoryList.getTableHeader().setReorderingAllowed(false);
        T_tradeHistoryList.getTableHeader().setResizingAllowed(false);
        S_tradeHistoryList = new JScrollPane();
        tradeHistoryList.add(S_tradeHistoryList);
        
        JPanel tradeHistroyContent = new JPanel();
        tradeHistroyContent.setBounds(846, 10, 202, 524);
        tradeHistoryPage.add(tradeHistroyContent);
        tradeHistroyContent.setLayout(new GridLayout(1, 0, 0, 0));

        JPanel tradeHistoryLab = new JPanel();
        tradeHistroyContent.add(tradeHistoryLab);
        tradeHistoryLab.setLayout(new GridLayout(0, 1, 0, 0));

        JLabel L_tradeHistoryBuyer = new JLabel("구매자 :");
        L_tradeHistoryBuyer.setFont(new Font("굴림", Font.PLAIN, 15));
        L_tradeHistoryBuyer.setHorizontalAlignment(SwingConstants.CENTER);
        tradeHistoryLab.add(L_tradeHistoryBuyer);

        JLabel L_tradeHistorySeller = new JLabel("판매자 :");
        L_tradeHistorySeller.setFont(new Font("굴림", Font.PLAIN, 15));
        L_tradeHistorySeller.setHorizontalAlignment(SwingConstants.CENTER);
        tradeHistoryLab.add(L_tradeHistorySeller);

        JLabel L_tradeHistoryName = new JLabel("아이템 이름 :");
        L_tradeHistoryName.setFont(new Font("굴림", Font.PLAIN, 15));
        L_tradeHistoryName.setHorizontalAlignment(SwingConstants.CENTER);
        tradeHistoryLab.add(L_tradeHistoryName);

        JLabel L_tradeHistoryPrice = new JLabel("아이템 가격 ;");
        L_tradeHistoryPrice.setFont(new Font("굴림", Font.PLAIN, 15));
        L_tradeHistoryPrice.setHorizontalAlignment(SwingConstants.CENTER);
        tradeHistoryLab.add(L_tradeHistoryPrice);

        JLabel L_tradeHistoryCharge = new JLabel("수수료 :");
        L_tradeHistoryCharge.setFont(new Font("굴림", Font.PLAIN, 15));
        L_tradeHistoryCharge.setHorizontalAlignment(SwingConstants.CENTER);
        tradeHistoryLab.add(L_tradeHistoryCharge);
        
        JLabel lblTotalCharge = new JLabel("총 수수료 :");
        lblTotalCharge.setHorizontalAlignment(SwingConstants.CENTER);
        tradeHistoryLab.add(lblTotalCharge);

        JPanel tradeHistoryIn = new JPanel();
        tradeHistroyContent.add(tradeHistoryIn);
        tradeHistoryIn.setLayout(new GridLayout(0, 1, 0, 0));

        In_tradeHistoryBuyer = new JTextField();
        In_tradeHistoryBuyer.setEditable(false);
        tradeHistoryIn.add(In_tradeHistoryBuyer);
        In_tradeHistoryBuyer.setColumns(10);

        In_tradeHistorySeller = new JTextField();
        In_tradeHistorySeller.setEditable(false);
        tradeHistoryIn.add(In_tradeHistorySeller);
        In_tradeHistorySeller.setColumns(10);

        In_tradeHistoryName = new JTextField();
        In_tradeHistoryName.setEditable(false);
        tradeHistoryIn.add(In_tradeHistoryName);
        In_tradeHistoryName.setColumns(10);

        In_tradeHistoryPrice = new JTextField();
        In_tradeHistoryPrice.setEditable(false);
        tradeHistoryIn.add(In_tradeHistoryPrice);
        In_tradeHistoryPrice.setColumns(10);

        In_tradeHistoryCharge = new JTextField();
        In_tradeHistoryCharge.setEditable(false);
        tradeHistoryIn.add(In_tradeHistoryCharge);
        In_tradeHistoryCharge.setColumns(10);
        
        In_TotalCharge = new JTextField();
        In_TotalCharge.setEditable(false);
        tradeHistoryIn.add(In_TotalCharge);
        In_TotalCharge.setColumns(10);

        T_tradeHistoryList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && T_tradeHistoryList.getSelectedRow() != -1) {
                    // 선택된 행의 데이터 출력
                    int selectedRow = T_tradeHistoryList.getSelectedRow();

                    In_tradeHistoryBuyer.setText(T_tradeHistoryList.getValueAt(selectedRow, 0).toString());
                    In_tradeHistorySeller.setText(T_tradeHistoryList.getValueAt(selectedRow, 1).toString());
                    In_tradeHistoryName.setText(T_tradeHistoryList.getValueAt(selectedRow, 2).toString());
                    In_tradeHistoryPrice.setText(T_tradeHistoryList.getValueAt(selectedRow, 3).toString());
                    In_tradeHistoryCharge.setText(T_tradeHistoryList.getValueAt(selectedRow, 4).toString());
                }
            }
        });

        /*
         *      ITEM INFO PAGE
         */

        JPanel itemInfoPage = new JPanel();
        itemInfoPage.setLayout(null);
        contents.add(itemInfoPage, "ItemInfoPage");

        JPanel itemInfoList = new JPanel();
        itemInfoList.setBorder(new LineBorder(new Color(0, 0, 0)));
        itemInfoList.setBounds(12, 10, 751, 526);
        itemInfoPage.add(itemInfoList);
        itemInfoList.setLayout(new GridLayout(0, 1, 0, 0));

        itemInfoTableModel = new DefaultTableModel(itemInfoHeader, 0);
        T_itemInfoList = new JTable(itemInfoTableModel);
        T_itemInfoList.getTableHeader().setReorderingAllowed(false);
        T_itemInfoList.getTableHeader().setResizingAllowed(false);
        S_itemInfoList = new JScrollPane();
        itemInfoList.add(S_itemInfoList);

        JPanel itemInfoManageContent = new JPanel();
        itemInfoManageContent.setLayout(null);
        itemInfoManageContent.setBorder(new LineBorder(new Color(0, 0, 0)));
        itemInfoManageContent.setBounds(775, 10, 275, 526);
        itemInfoPage.add(itemInfoManageContent);

        JPanel itemInfoLab = new JPanel();
        itemInfoLab.setBounds(0, 0, 109, 393);
        itemInfoManageContent.add(itemInfoLab);
        itemInfoLab.setLayout(new GridLayout(0, 1, 0, 0));

        JLabel L_itemInfoType = new JLabel("타입 :");
        L_itemInfoType.setHorizontalAlignment(SwingConstants.CENTER);
        L_itemInfoType.setFont(new Font("굴림", Font.PLAIN, 15));
        itemInfoLab.add(L_itemInfoType);

        JLabel L_itemInfoName = new JLabel("이름 :");
        L_itemInfoName.setHorizontalAlignment(SwingConstants.CENTER);
        L_itemInfoName.setFont(new Font("굴림", Font.PLAIN, 15));
        itemInfoLab.add(L_itemInfoName);

        JLabel L_itemInfoGrade = new JLabel("등급 :");
        L_itemInfoGrade.setHorizontalAlignment(SwingConstants.CENTER);
        L_itemInfoGrade.setFont(new Font("굴림", Font.PLAIN, 15));
        itemInfoLab.add(L_itemInfoGrade);

        JLabel L_itemInfoDesc = new JLabel("설명 :");
        L_itemInfoDesc.setHorizontalAlignment(SwingConstants.CENTER);
        L_itemInfoDesc.setFont(new Font("굴림", Font.PLAIN, 15));
        itemInfoLab.add(L_itemInfoDesc);

        JLabel L_itemInfoOp1 = new JLabel("옵션 1 :");
        L_itemInfoOp1.setHorizontalAlignment(SwingConstants.CENTER);
        L_itemInfoOp1.setFont(new Font("굴림", Font.PLAIN, 15));
        itemInfoLab.add(L_itemInfoOp1);

        JPanel itemInfoIn = new JPanel();
        itemInfoIn.setBounds(110, 0, 165, 393);
        itemInfoManageContent.add(itemInfoIn);
        itemInfoIn.setLayout(new GridLayout(0, 1, 0, 0));

        C_itemInfoType = new JComboBox<String>(itemTypes);
        itemInfoIn.add(C_itemInfoType);

        In_itemInfoName = new JTextField();
        In_itemInfoName.setColumns(10);
        itemInfoIn.add(In_itemInfoName);

        C_itemInfoGrade = new JComboBox<String>(itemGrades);
        itemInfoIn.add(C_itemInfoGrade);

        In_itemInfoDesc = new JTextField();
        In_itemInfoDesc.setColumns(10);
        itemInfoIn.add(In_itemInfoDesc);

        In_itemInfoOp1 = new JTextField();
        In_itemInfoOp1.setColumns(10);
        itemInfoIn.add(In_itemInfoOp1);

        JPanel itemInfoButtons = new JPanel();
        itemInfoButtons.setBounds(0, 391, 275, 135);
        itemInfoManageContent.add(itemInfoButtons);
        itemInfoButtons.setLayout(new GridLayout(0, 1, 0, 0));

        JButton Btt_createItemInfo = new JButton("아이템 생성");
        Btt_createItemInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String type = C_itemInfoType.getSelectedItem().toString();
                String name = In_itemInfoName.getText();
                String grade = C_itemInfoGrade.getSelectedItem().toString();
                String desc = In_itemInfoDesc.getText();
                String optionString1 = In_itemInfoOp1.getText();

                if(In_itemInfoName.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "아이템 이름을 입력해주세요");
                }
                else if(In_itemInfoDesc.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "아이템 설명을 입력해주세요");
                }
                else if (!Pattern.matches("^[1-9]\\d*$", optionString1)){
                    JOptionPane.showMessageDialog(null, "옵션에 올바른 숫자를 입력해주세요");
                }
                else{
                    int option1 = Integer.parseInt(optionString1);

                    Item item = new ItemBuilder()
                            .type(type)
                            .name(name)
                            .grade(grade)
                            .desc(desc)
                            .option1(option1)
                            .build();

                    CreateItemCommand createItemCommand = new CreateItemCommand(item);
                    invoker.setCommand(createItemCommand);
                    invoker.run();

                    refreshItemInfoTable();
                }
            }
        });
        Btt_createItemInfo.setFont(new Font("굴림", Font.PLAIN, 15));
        itemInfoButtons.add(Btt_createItemInfo);

        JButton Btt_updateItemInfo = new JButton("아이템 수정");
        Btt_updateItemInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String type = C_itemInfoType.getSelectedItem().toString();
                String name = In_itemInfoName.getText();
                String grade = C_itemInfoGrade.getSelectedItem().toString();
                String desc = In_itemInfoDesc.getText();
                String optionString1 = In_itemInfoOp1.getText();

                if(In_itemInfoName.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "아이템 이름을 입력해주세요");
                }
                else if(In_itemInfoDesc.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "아이템 설명을 입력해주세요");
                }
                else if (!Pattern.matches("^[1-9]\\d*$", optionString1)){
                    JOptionPane.showMessageDialog(null, "옵션에 올바른 숫자를 입력해주세요");
                }
                else{
                    int option1 = Integer.parseInt(optionString1);
                    
                    Item item = new ItemBuilder()
                            .type(type)
                            .name(name)
                            .grade(grade)
                            .desc(desc)
                            .option1(option1)
                            .build();

                    UpdateItemCommand updateItemCommand = new UpdateItemCommand(item);
                    invoker.setCommand(updateItemCommand);
                    invoker.run();

                    refreshItemInfoTable();
                }
            }
        });
        Btt_updateItemInfo.setFont(new Font("굴림", Font.PLAIN, 15));
        itemInfoButtons.add(Btt_updateItemInfo);

        JButton Btt_deleteItemInfo = new JButton("아이템 삭제");
        Btt_deleteItemInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (In_itemInfoName.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "이름을 채워주세요");
                }
                else{
                    Item item = ItemFileSystem.getItemFileSystem().getItemByName(In_itemInfoName.getText());

                    DeleteItemCommand deleteItemCommand = new DeleteItemCommand(item);
                    invoker.setCommand(deleteItemCommand);
                    invoker.run();

                    refreshItemInfoTable();
                }
            }
        });
        Btt_deleteItemInfo.setFont(new Font("굴림", Font.PLAIN, 15));
        itemInfoButtons.add(Btt_deleteItemInfo);

        T_itemInfoList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && T_itemInfoList.getSelectedRow() != -1) {
                    // 선택된 행의 데이터 출력
                    int selectedRow = T_itemInfoList.getSelectedRow();

                    C_itemInfoType.setSelectedItem(T_itemInfoList.getValueAt(selectedRow, 0).toString());
                    In_itemInfoName.setText(T_itemInfoList.getValueAt(selectedRow, 1).toString());
                    C_itemInfoType.setSelectedItem(T_itemInfoList.getValueAt(selectedRow, 2).toString());
                    In_itemInfoDesc.setText(T_itemInfoList.getValueAt(selectedRow, 3).toString());
                    In_itemInfoOp1.setText(T_itemInfoList.getValueAt(selectedRow, 4).toString());
                }
            }
        });

        /*
         *      UserManage Page
         */

        JPanel userInfoPage = new JPanel();
        userInfoPage.setBorder(new LineBorder(new Color(0, 0, 0)));
        userInfoPage.setLayout(null);
        contents.add(userInfoPage, "UserManagePage");

        JPanel userList = new JPanel();
        userList.setBounds(12, 10, 758, 526);
        userList.setBorder(new LineBorder(new Color(0, 0, 0)));
        userList.setLayout(new GridLayout(0, 1, 0, 0));
        userInfoPage.add(userList);

        userTableModel = new DefaultTableModel(userHeader, 0);
        T_userList = new JTable(userTableModel);
        T_userList.getTableHeader().setReorderingAllowed(false);
        T_userList.getTableHeader().setResizingAllowed(false);
        S_userList = new JScrollPane();
        userList.add(S_userList);

        JPanel userManageContent = new JPanel();
        userManageContent.setBorder(new LineBorder(new Color(0, 0, 0)));
        userManageContent.setBounds(782, 10, 268, 526);
        userInfoPage.add(userManageContent);
        userManageContent.setLayout(null);

        JPanel userIn = new JPanel();
        userIn.setBounds(143, 0, 125, 324);
        userManageContent.add(userIn);
        userIn.setLayout(new GridLayout(0, 1, 0, 0));

        In_userID = new JTextField();
        In_userID.setColumns(10);
        userIn.add(In_userID);

        In_userPassword = new JTextField();
        In_userPassword.setColumns(10);
        userIn.add(In_userPassword);

        In_userName = new JTextField();
        In_userName.setColumns(10);
        userIn.add(In_userName);

        In_userPhoneNumber = new JTextField();
        In_userPhoneNumber.setColumns(10);
        userIn.add(In_userPhoneNumber);

        In_userGold = new JTextField();
        In_userGold.setColumns(10);
        userIn.add(In_userGold);

        JPanel userLab = new JPanel();
        userLab.setBounds(0, 0, 141, 324);
        userManageContent.add(userLab);
        userLab.setLayout(new GridLayout(0, 1, 0, 0));

        JLabel L_userID = new JLabel("아이디 :");
        L_userID.setHorizontalAlignment(SwingConstants.CENTER);
        L_userID.setFont(new Font("굴림", Font.PLAIN, 20));
        userLab.add(L_userID);

        JLabel L_userPassword = new JLabel("비밀번호 :");
        L_userPassword.setHorizontalAlignment(SwingConstants.CENTER);
        L_userPassword.setFont(new Font("굴림", Font.PLAIN, 20));
        userLab.add(L_userPassword);

        JLabel L_userName = new JLabel("이름 :");
        L_userName.setHorizontalAlignment(SwingConstants.CENTER);
        L_userName.setFont(new Font("굴림", Font.PLAIN, 20));
        userLab.add(L_userName);

        JLabel L_userPhoneNumber = new JLabel("전화번호 :");
        L_userPhoneNumber.setHorizontalAlignment(SwingConstants.CENTER);
        L_userPhoneNumber.setFont(new Font("굴림", Font.PLAIN, 20));
        userLab.add(L_userPhoneNumber);

        JLabel L_userGold = new JLabel("골드 :");
        L_userGold.setHorizontalAlignment(SwingConstants.CENTER);
        L_userGold.setFont(new Font("굴림", Font.PLAIN, 20));
        userLab.add(L_userGold);

        JPanel userButtons = new JPanel();
        userButtons.setBounds(0, 322, 268, 204);
        userManageContent.add(userButtons);
        userButtons.setLayout(new GridLayout(0, 1, 0, 0));

        
        JButton Btt_modityAdmin = new JButton("유저 권한부여");
        Btt_modityAdmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userId = In_userID.getText();

                if (Auction.getAuction().getId().equals(userId)) {
                    JOptionPane.showMessageDialog(null, "자신의 권한은 수정할 수 없습니다.");
                } 
                else {
                    User user = UserFileSystem.getUserFileSystem().getUserById(userId);
                    user.setAdmin(!user.isAdmin());

                    UpdateUserCommand command = new UpdateUserCommand(user);
                    invoker.setCommand(command);
                    invoker.run();

                    refreshUserTable();
                }
            }
        });

        JButton Btt_createUser = new JButton("유저 생성");
        Btt_createUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = In_userID.getText();
                String password = In_userPassword.getText();
                String name = In_userName.getText();
                String phoneNumber = In_userPhoneNumber.getText();
                String goldString = In_userGold.getText();

                if (!Pattern.matches("^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$", id)) {
                    JOptionPane.showMessageDialog(null, "아이디 형식은 영어로 시작하는 5 ~ 12 영어, 숫자 조합입니다");
                }
                else if (!Pattern.matches("^[a-zA-Z0-9_]{5,12}$", password)) {
                    JOptionPane.showMessageDialog(null, "비밀번호 형식은  5 ~ 12 영어, 숫자 조합입니다");
                }
                else if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "이름을 채워주세요");
                }
                else if (!Pattern.matches("010-\\d{3,4}-\\d{4}", phoneNumber)) {
                    JOptionPane.showMessageDialog(null, "전화번호는 010-(3 ~ 4자리 숫자)-(4자리 숫자) 형식입니다.");
                }
                else if(!Pattern.matches("^[1-9]\\d*$", goldString)){
                    JOptionPane.showMessageDialog(null, "골드에 올바른 숫자를 입력해주세요");
                }
                else{
                    int gold = Integer.valueOf(In_userGold.getText());
                    
                    User user = new User.UserBuilder()
                            .id(id)
                            .password(password)
                            .name(name)
                            .phoneNumber(phoneNumber)
                            .gold(gold)
                            .build();

                    CreateUserCommand command = new CreateUserCommand(user);
                    invoker.setCommand(command);
                    invoker.run();

                    refreshUserTable();
                }
            }
        });

        Btt_modityAdmin.setFont(new Font("굴림", Font.PLAIN, 15));
        userButtons.add(Btt_modityAdmin);
        Btt_createUser.setFont(new Font("굴림", Font.PLAIN, 15));
        userButtons.add(Btt_createUser);

        JButton Btt_updateUser = new JButton("유저 수정");
        Btt_updateUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = In_userID.getText();
                String password = In_userPassword.getText();
                String name = In_userName.getText();
                String phoneNumber = In_userPhoneNumber.getText();
                String goldString = In_userGold.getText();

                if (!Pattern.matches("^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$", id)) {
                    JOptionPane.showMessageDialog(null, "아이디 형식은 영어로 시작하는 5 ~ 12 영어, 숫자 조합입니다");
                }
                else if (!Pattern.matches("^[a-zA-Z0-9_]{5,12}$", password)) {
                    JOptionPane.showMessageDialog(null, "비밀번호 형식은  5 ~ 12 영어, 숫자 조합입니다");
                }
                else if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "이름을 채워주세요");
                }
                else if (!Pattern.matches("010-\\d{3,4}-\\d{4}", phoneNumber)) {
                    JOptionPane.showMessageDialog(null, "전화번호는 010-(3 ~ 4자리 숫자)-(4자리 숫자) 형식입니다.");
                }
                else if(!Pattern.matches("^[1-9]\\d*$", goldString)){
                    JOptionPane.showMessageDialog(null, "골드에 올바른 숫자를 입력해주세요");
                }
                else{
                    int gold = Integer.valueOf(goldString);
                    
                    User user = new User.UserBuilder()
                            .id(id)
                            .password(password)
                            .name(name)
                            .phoneNumber(phoneNumber)
                            .gold(gold)
                            .build();

                    UpdateUserCommand command = new UpdateUserCommand(user);
                    invoker.setCommand(command);
                    invoker.run();

                    refreshUserTable();
                }
            }
        });
        Btt_updateUser.setFont(new Font("굴림", Font.PLAIN, 15));
        userButtons.add(Btt_updateUser);

        JButton Btt_deleteUser = new JButton("유저 삭제");
        Btt_deleteUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userId = In_userID.getText();

                if (userId.isEmpty())
                    JOptionPane.showMessageDialog(null, "아이디를 채워주세요");
                else {
                    User user = UserFileSystem.getUserFileSystem().getUserById(userId);

                    DeleteUserCommand command = new DeleteUserCommand(user);
                    invoker.setCommand(command);
                    invoker.run();

                    refreshUserTable();
                }
            }
        });
        Btt_deleteUser.setFont(new Font("굴림", Font.PLAIN, 15));
        userButtons.add(Btt_deleteUser);

        T_userList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && T_userList.getSelectedRow() != -1) {
                    // 선택된 행의 데이터 출력
                    int selectedRow = T_userList.getSelectedRow();

                    In_userID.setText(T_userList.getValueAt(selectedRow, 0).toString());
                    In_userPassword.setText(T_userList.getValueAt(selectedRow, 1).toString());
                    In_userName.setText(T_userList.getValueAt(selectedRow, 2).toString());
                    In_userPhoneNumber.setText(T_userList.getValueAt(selectedRow, 3).toString());
                    In_userGold.setText(T_userList.getValueAt(selectedRow, 4).toString());
                }
            }
        });


        /*
         *      USER INVENTORY PAGE
         */

        JPanel userInventoryPage = new JPanel();
        userInventoryPage.setLayout(null);
        contents.add(userInventoryPage, "UserInventoryPage");

        JPanel userItemList = new JPanel();
        userItemList.setBorder(new LineBorder(new Color(0, 0, 0)));
        userItemList.setBounds(10, 10, 430, 526);
        userInventoryPage.add(userItemList);
        userItemList.setLayout(new GridLayout(0, 1, 0, 0));

        userItemTableModel = new DefaultTableModel(userItemHeader, 0);
        T_userItemList = new JTable(userItemTableModel);
        T_userItemList.getTableHeader().setReorderingAllowed(false);
        T_userItemList.getTableHeader().setResizingAllowed(false);
        S_userItemList = new JScrollPane();
        userItemList.add(S_userItemList);

        JPanel useritemInfoList = new JPanel();
        useritemInfoList.setBorder(new LineBorder(new Color(0, 0, 0)));
        useritemInfoList.setBounds(457, 10, 410, 526);
        userInventoryPage.add(useritemInfoList);
        useritemInfoList.setLayout(new GridLayout(0, 1, 0, 0));

        userItemInfoTableModel = new DefaultTableModel(itemInfoHeader, 0);
        T_userItemInfoList = new JTable(userItemInfoTableModel);
        T_userItemInfoList.getTableHeader().setReorderingAllowed(false);
        T_userItemInfoList.getTableHeader().setResizingAllowed(false);
        S_userItemInfoList = new JScrollPane();
        useritemInfoList.add(S_userItemInfoList);
        refreshItemInfoTable();

        JPanel useItemManageContent = new JPanel();
        useItemManageContent.setLayout(null);
        useItemManageContent.setBorder(new LineBorder(new Color(0, 0, 0)));
        useItemManageContent.setBounds(879, 10, 169, 526);
        userInventoryPage.add(useItemManageContent);

        JPanel userItemLab = new JPanel();
        userItemLab.setBounds(0, 0, 63, 393);
        useItemManageContent.add(userItemLab);
        userItemLab.setLayout(new GridLayout(0, 1, 0, 0));

        JLabel L_inventoryUserName = new JLabel("유저 :");
        L_inventoryUserName.setHorizontalAlignment(SwingConstants.CENTER);
        L_inventoryUserName.setFont(new Font("굴림", Font.PLAIN, 15));
        userItemLab.add(L_inventoryUserName);

        JLabel L_userItemType = new JLabel("타입 :");
        L_userItemType.setHorizontalAlignment(SwingConstants.CENTER);
        L_userItemType.setFont(new Font("굴림", Font.PLAIN, 15));
        userItemLab.add(L_userItemType);

        JLabel L_userItemName = new JLabel("이름 :");
        L_userItemName.setHorizontalAlignment(SwingConstants.CENTER);
        L_userItemName.setFont(new Font("굴림", Font.PLAIN, 15));
        userItemLab.add(L_userItemName);

        JLabel L_userItemGrade = new JLabel("등급 :");
        L_userItemGrade.setHorizontalAlignment(SwingConstants.CENTER);
        L_userItemGrade.setFont(new Font("굴림", Font.PLAIN, 15));
        userItemLab.add(L_userItemGrade);

        JLabel L_userItemDesc = new JLabel("설명 :");
        L_userItemDesc.setHorizontalAlignment(SwingConstants.CENTER);
        L_userItemDesc.setFont(new Font("굴림", Font.PLAIN, 15));
        userItemLab.add(L_userItemDesc);

        JLabel L_userItemOp1 = new JLabel("옵션 1 :");
        L_userItemOp1.setHorizontalAlignment(SwingConstants.CENTER);
        L_userItemOp1.setFont(new Font("굴림", Font.PLAIN, 15));
        userItemLab.add(L_userItemOp1);

        JLabel L_userItemCount = new JLabel("개수 :");
        L_userItemCount.setHorizontalAlignment(SwingConstants.CENTER);
        L_userItemCount.setFont(new Font("굴림", Font.PLAIN, 15));
        userItemLab.add(L_userItemCount);

        JPanel userItemIn = new JPanel();
        userItemIn.setBounds(63, 0, 106, 393);
        useItemManageContent.add(userItemIn);
        userItemIn.setLayout(new GridLayout(0, 1, 0, 0));

        In_inventoryUser = new JTextField();
        In_inventoryUser.setEditable(false);
        In_inventoryUser.setColumns(10);
        userItemIn.add(In_inventoryUser);

        In_userItemType = new JTextField();
        In_userItemType.setEditable(false);
        In_userItemType.setColumns(10);
        userItemIn.add(In_userItemType);

        In_userItemName = new JTextField();
        In_userItemName.setEditable(false);
        In_userItemName.setColumns(10);
        userItemIn.add(In_userItemName);

        In_userItemGrade = new JTextField();
        In_userItemGrade.setEditable(false);
        In_userItemGrade.setColumns(10);
        userItemIn.add(In_userItemGrade);

        In_userItemDesc = new JTextField();
        In_userItemDesc.setEditable(false);
        In_userItemDesc.setColumns(10);
        userItemIn.add(In_userItemDesc);

        In_userItemOp1 = new JTextField();
        In_userItemOp1.setEditable(false);
        In_userItemOp1.setColumns(10);
        userItemIn.add(In_userItemOp1);

        In_userItemCount = new JTextField();
        In_userItemCount.setColumns(10);
        userItemIn.add(In_userItemCount);

        JPanel userItemButtons = new JPanel();
        userItemButtons.setBounds(0, 391, 169, 135);
        useItemManageContent.add(userItemButtons);
        userItemButtons.setLayout(new GridLayout(0, 1, 0, 0));

        JButton Btt_createUserItem = new JButton("아이템 생성");
        Btt_createUserItem.setFont(new Font("굴림", Font.PLAIN, 15));
        Btt_createUserItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String type = In_userItemType.getText();
                String name = In_userItemName.getText();
                String grade = In_userItemGrade.getText();
                String desc = In_userItemDesc.getText();
                int option1 = Integer.parseInt(In_userItemOp1.getText());
                String countString = In_userItemCount.getText();

                if (type.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "항목을 선택해주세요");
                } 
                else if (!Pattern.matches("^[1-9]\\d*$", countString)){
                    JOptionPane.showMessageDialog(null, "개수에 올바른 숫자를 입력해주세요");
                }
                else{
                    int count = Integer.valueOf(In_userItemCount.getText());

                    Item item = new ItemBuilder()
                            .type(type)
                            .name(name)
                            .grade(grade)
                            .desc(desc)
                            .option1(option1)
                            .build();

                    CreateInvItemCommand createInvItemCommand = new CreateInvItemCommand(currentUser, item, count);
                    invoker.setCommand(createInvItemCommand);
                    invoker.run();

                    refreshUserItemTable();
                }
            }
        });
        userItemButtons.add(Btt_createUserItem);

        JButton Btt_updateUserItem = new JButton("아이템 수정");
        Btt_updateUserItem.setFont(new Font("굴림", Font.PLAIN, 15));
        Btt_updateUserItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String type = In_userItemType.getText();
                String name = In_userItemName.getText();
                String grade = In_userItemGrade.getText();
                String desc = In_userItemDesc.getText();
                int option1 = Integer.parseInt(In_userItemOp1.getText());
                String countString = In_userItemCount.getText();

                if (type.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "항목을 선택해주세요");
                }
                else if (!Pattern.matches("^[1-9]\\d*$", countString)){
                    JOptionPane.showMessageDialog(null, "개수에 올바른 숫자를 입력해주세요");
                }
                else{
                    int count = Integer.valueOf(In_userItemCount.getText());

                    Item item = new ItemBuilder()
                            .type(type)
                            .name(name)
                            .grade(grade)
                            .desc(desc)
                            .option1(option1)
                            .build();

                    InventoryItem inventoryItem = new InventoryItem(item, count);

                    UpdateInvItemCommand updateInvItemCommand = new UpdateInvItemCommand(currentUser, inventoryItem);
                    invoker.setCommand(updateInvItemCommand);
                    invoker.run();

                    refreshUserItemTable();
                }

            }
        });
        userItemButtons.add(Btt_updateUserItem);

        JButton Btt_deleteUserItem = new JButton("아이템 삭제");
        Btt_deleteUserItem.setFont(new Font("굴림", Font.PLAIN, 15));
        Btt_deleteUserItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String itemName = In_userItemName.getText();

                if (itemName.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "아이템 이름를 입력해주세요");
                } else {
                    DeleteInvItemCommand deleteInvItemCommand = new DeleteInvItemCommand(currentUser, itemName);
                    invoker.setCommand(deleteInvItemCommand);
                    invoker.run();

                    refreshUserItemTable();
                }

            }
        });
        userItemButtons.add(Btt_deleteUserItem);

        T_userItemList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && T_userItemList.getSelectedRow() != -1) {
                    // 선택된 행의 데이터 출력
                    int selectedRow = T_userItemList.getSelectedRow();

                    In_userItemType.setText(T_userItemList.getValueAt(selectedRow, 0).toString());
                    In_userItemName.setText(T_userItemList.getValueAt(selectedRow, 1).toString());
                    In_userItemGrade.setText(T_userItemList.getValueAt(selectedRow, 2).toString());
                    In_userItemDesc.setText(T_userItemList.getValueAt(selectedRow, 3).toString());
                    In_userItemOp1.setText(T_userItemList.getValueAt(selectedRow, 4).toString());
                    In_userItemCount.setText(T_userItemList.getValueAt(selectedRow, 5).toString());

                }
            }
        });

        T_userItemInfoList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && T_userItemInfoList.getSelectedRow() != -1) {
                    // 선택된 행의 데이터 출력
                    int selectedRow = T_userItemInfoList.getSelectedRow();

                    In_userItemType.setText(T_userItemInfoList.getValueAt(selectedRow, 0).toString());
                    In_userItemName.setText(T_userItemInfoList.getValueAt(selectedRow, 1).toString());
                    In_userItemGrade.setText(T_userItemInfoList.getValueAt(selectedRow, 2).toString());
                    In_userItemDesc.setText(T_userItemInfoList.getValueAt(selectedRow, 3).toString());
                    In_userItemOp1.setText(T_userItemInfoList.getValueAt(selectedRow, 4).toString());
                    In_userItemCount.setText("");
                }
            }
        });

        /*
         * 		SUB MANU
         */

        JPanel subManu = new JPanel();
        CardLayout subCardLayout = new CardLayout(0, 0);
        subManu.setBounds(765, 33, 309, 55);
        subManu.setLayout(subCardLayout);
        contentPane.add(subManu);

        JPanel auctionSubMnau = new JPanel();
        subManu.add(auctionSubMnau, "AuctionSubManu");
        auctionSubMnau.setLayout(new GridLayout(1, 0, 0, 0));

        JButton Btt_auctionItemList = new JButton("경매장 물품 관리");
        Btt_auctionItemList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainCardLayout.show(contents, "AuctionManagePage");

                refreshTradeItemTable();
                refreshTradeItemInfoTable();
            }
        });
        auctionSubMnau.add(Btt_auctionItemList);

        JButton Btt_autionManage = new JButton("경매장 기록");
        Btt_autionManage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainCardLayout.show(contents, "TradeHistoryPage");

                refreshTradeHistoryTable();
            }
        });
        auctionSubMnau.add(Btt_autionManage);

        /*
         *      USER SUB MANU
         */

        JPanel userSubManu = new JPanel();
        subManu.add(userSubManu, "UserSubManu");
        userSubManu.setLayout(new GridLayout(1, 0, 0, 0));

        JButton Btt_userList = new JButton("유저 리스트");
        Btt_userList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainCardLayout.show(contents, "UserManagePage");
            }
        });
        userSubManu.add(Btt_userList);

        JButton Btt_userInventory = new JButton("유저 인벤토리");
        Btt_userInventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentUser = UserFileSystem.getUserFileSystem().getUserById(In_userID.getText());

                if (currentUser != null) {
                    In_inventoryUser.setText(In_userID.getText());
                    mainCardLayout.show(contents, "UserInventoryPage");

                    refreshUserItemTable();
                    refreshUserItemInfoTable();

                } else
                    JOptionPane.showMessageDialog(null, "유저 정보가 없습니다.");
            }
        });
        userSubManu.add(Btt_userInventory);

        JPanel itemInfoSubManu = new JPanel();
        subManu.add(itemInfoSubManu, "ItemInfoSubManu");

        /*
         * 		MAIN MANU
         */

        JPanel manu = new JPanel();
        manu.setBounds(12, 10, 707, 78);
        contentPane.add(manu);
        manu.setLayout(new GridLayout(1, 0, 0, 0));

        JButton Btt_goAution = new JButton("경매장 관리");
        Btt_goAution.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainCardLayout.show(contents, "AuctionManagePage");
                subCardLayout.show(subManu, "AuctionSubManu");
                refreshTradeItemTable();
                refreshTradeItemInfoTable();
            }
        });
        Btt_goAution.setFont(new Font("굴림", Font.PLAIN, 20));
        manu.add(Btt_goAution);

        JButton Btt_goItemInfo = new JButton("아이템 관리");
        Btt_goItemInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainCardLayout.show(contents, "ItemInfoPage");
                subCardLayout.show(subManu, "ItemInfoSubManu");
                refreshItemInfoTable();
            }
        });
        Btt_goItemInfo.setFont(new Font("굴림", Font.PLAIN, 20));
        manu.add(Btt_goItemInfo);

        JButton Btt_goUser = new JButton("유저 관리");
        Btt_goUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainCardLayout.show(contents, "UserManagePage");
                subCardLayout.show(subManu, "UserSubManu");
                refreshUserTable();
            }
        });
        Btt_goUser.setFont(new Font("굴림", Font.PLAIN, 20));
        manu.add(Btt_goUser);

        JButton Btt_ChangeAuctionState = new JButton("경매장 상태");
        Btt_ChangeAuctionState.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Auction.getAuction().changeState();

                if(Auction.getAuction().isOpen()){
                    Btt_ChangeAuctionState.setText("경매장 열림");
                }
                else{
                    Btt_ChangeAuctionState.setText("경매장 닫힘");
                }
            }
        });
        Btt_ChangeAuctionState.setFont(new Font("굴림", Font.PLAIN, 20));
        manu.add(Btt_ChangeAuctionState);

        if(Auction.getAuction().isOpen()){
            Btt_ChangeAuctionState.setText("경매장 열림");
        }
        else{
            Btt_ChangeAuctionState.setText("경매장 닫힘");
        }

        JButton Btt_logOut = new JButton("종료");
        Btt_logOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingLogin.getSwingLogin().setVisible(true);
                dispose();
            }
        });
        Btt_logOut.setFont(new Font("굴림", Font.PLAIN, 20));
        manu.add(Btt_logOut);
    }

    public void refreshTradeItemTable() {
        tradeItemTableModel.setRowCount(0);

        for (TradeItem item : TradeItemFileSystem.getTradeItemFileSystem().getTradeItemList()) {
            Object[] rowData = item.getSimpleListData();
            tradeItemTableModel.addRow(rowData);
        }

        S_tradeItemList.setViewportView(T_tradeItemList);
    }

    public void refreshTradeItemInfoTable() {
        tradeItemInfoTableModel.setRowCount(0);

        for (Item item : ItemFileSystem.getItemFileSystem().getItemList()) {
            Object[] rowData = item.getListData();
            tradeItemInfoTableModel.addRow(rowData);
        }

        S_tradeItemInfoList.setViewportView(T_tradeItemInfoList);
    }

    public void refreshTradeHistoryTable() {
        int totalCharge = 0;

        tradeHistoryTableModel.setRowCount(0);

        for (TradeHistory history : TradeHistoryFileSystem.getTradeHistoryFileSystem().getTradeHistoryList()) {
            totalCharge += history.getCharge();

            Object[] rowData = history.getListData();
            tradeHistoryTableModel.addRow(rowData);
        }
        
        In_TotalCharge.setText(Integer.toString(totalCharge));

        S_tradeHistoryList.setViewportView(T_tradeHistoryList);
    }

    public void refreshItemInfoTable() {
        itemInfoTableModel.setRowCount(0);

        for (Item item : ItemFileSystem.getItemFileSystem().getItemList()) {
            Object[] rowData = item.getListData();
            itemInfoTableModel.addRow(rowData);
        }

        S_itemInfoList.setViewportView(T_itemInfoList);
    }

    public void refreshUserTable() {
        userTableModel.setRowCount(0);

        for (User user : UserFileSystem.getUserFileSystem().getUserList()) {
            Object[] rowData = user.getListData();
            userTableModel.addRow(rowData);
        }

        S_userList.setViewportView(T_userList);
    }

    public void refreshUserItemTable() {
        userItemTableModel.setRowCount(0);

        for (InventoryItem item : currentUser.getItemList()) {
            Object[] rowData = item.getListData();
            userItemTableModel.addRow(rowData);
        }

        S_userItemList.setViewportView(T_userItemList);
    }

    public void refreshUserItemInfoTable() {
        userItemInfoTableModel.setRowCount(0);

        for (Item item : ItemFileSystem.getItemFileSystem().getItemList()) {
            Object[] rowData = item.getListData();
            userItemInfoTableModel.addRow(rowData);
        }

        S_userItemInfoList.setViewportView(T_userItemInfoList);
    }
}