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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import auctionData.TradeItem;
import auctionData.TradeItemFileSystem;
import commandManage.*;
import commandManage.inventoryItems.CreateInvItemCommand;
import commandManage.inventoryItems.DeleteInvItemCommand;
import commandManage.inventoryItems.UpdateInvItemCommand;
import commandManage.items.CreateItemCommand;
import commandManage.items.DeleteItemCommand;
import commandManage.items.UpdateItemCommand;
import commandManage.tradeItems.CreateTradeItemCommand;
import commandManage.tradeItems.DeleteTradeItemCommand;
import commandManage.tradeItems.UpdateTradeItemCommand;
import commandManage.users.CreateUserCommand;
import commandManage.users.DeleteUserCommand;
import commandManage.users.UpdateUserCommand;
import itemInfos.Item;
import itemInfos.ItemBuilder;
import itemInfos.ItemFileSystem;
import user.InventoryItem;
import user.User;
import user.UserFileSystem;


public class SwingAdmin extends JFrame {
    Invoker invoker = new Invoker();
    private String[] tradeItemHeader = {"TRADEID", "USERID", "TYPE", "NAME", "GRADE", "DESC", "OPTION1", "COUNT", "PRICE"};
    private String[] userItemHeader = {"TYPE", "NAME", "GRADE", "DESC", "OPTION1", "COUNT"};
    private String[] itemInfoHeader = {"TYPE", "NAME", "GRADE", "DESC", "OPTION1"};

    private String[] itemTypes = {"Equipment", "Material", "Potion", "Weapon"};
    private String[] itemGrades = {"Common", "Uncommon", "Eqic", "Legendary"};

    private String[] userHeader = {"ID", "PW", "NAME", "PHONE", "GOLD", "ISADMIN"};

    private User currentUser;
    private JPanel contentPane;

    private JTextField In_tradeItemId;
    private JComboBox<String> C_tradeItemType;
    private JTextField In_tradeItemName;
    private JComboBox<String> C_tradeItemGrade;
    private JTextField In_tradeItemDesc;
    private JFormattedTextField In_tradeItemCount;
    private JFormattedTextField In_tradeItemPrice;
    private JFormattedTextField In_tradeItemOp1;

    private JTextField In_userID;
    private JTextField In_userPassword;
    private JTextField In_userName;
    private JTextField In_userPhoneNumber;
    private JFormattedTextField In_userGold;

    private JTextField In_inventoryUser;
    private JTextField In_userItemType;
    private JTextField In_userItemName;
    private JTextField In_userItemGrade;
    private JTextField In_userItemDesc;
    private JTextField In_userItemOp1;

    private JTextField In_itemInfoName;
    private JTextField In_itemInfoDesc;

    // 거래목록 테이블
    private JTable T_tradeItemList;
    private JScrollPane S_tradeItemList;
    private DefaultTableModel tradeItemTableModel;

    // 유저 리스트 테이블
    private JTable T_userList;
    private JScrollPane S_userList;
    private DefaultTableModel userTableModel;

    // 유저 인벤토리 테이블
    private JTable T_userItemList;
    private JScrollPane S_userItemList;
    private DefaultTableModel userItemTableModel;

    // 유저 아이템 정보 리스트 테이블 
    private JTable T_userItemInfoList;
    private JScrollPane S_userItemInfoList;
    private DefaultTableModel userItemInfoTableModel;

    // 아이템 정보 테이블
    private JTable T_itemInfoList;
    private JScrollPane S_itemInfoList;
    private DefaultTableModel itemInfoTableModel;

    private static SwingAdmin swingAdmin = new SwingAdmin();

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

        // 숫자 입력 포멧
        NumberFormatter F_NumberFormet = new NumberFormatter();
        F_NumberFormet.setValueClass(Integer.class);
        F_NumberFormet.setMinimum(Integer.valueOf(1));
        F_NumberFormet.setMaximum(Integer.valueOf(100000));


        /*
         *      AuctionManage Page
         */

        JPanel tradeItemPage = new JPanel();
        contents.add(tradeItemPage, "AuctionManagePage");
        tradeItemPage.setLayout(null);

        JPanel tradeItemList = new JPanel();
        tradeItemList.setBounds(12, 10, 751, 526);
        tradeItemList.setBorder(new LineBorder(new Color(0, 0, 0)));
        tradeItemList.setLayout(new GridLayout(0, 1, 0, 0));
        tradeItemPage.add(tradeItemList);

        tradeItemTableModel = new DefaultTableModel(tradeItemHeader, 0);
        T_tradeItemList = new JTable(tradeItemTableModel);
        T_tradeItemList.getTableHeader().setReorderingAllowed(false);
        T_tradeItemList.getTableHeader().setResizingAllowed(false);
        S_tradeItemList = new JScrollPane();
        tradeItemList.add(S_tradeItemList);
        refreshTradeItemTable();

        JPanel tradeItemManageContent = new JPanel();
        tradeItemManageContent.setBorder(new LineBorder(new Color(0, 0, 0)));
        tradeItemManageContent.setBounds(775, 10, 275, 526);
        tradeItemPage.add(tradeItemManageContent);
        tradeItemManageContent.setLayout(null);

        JPanel tradeItemLab = new JPanel();
        tradeItemLab.setBounds(0, 0, 109, 393);
        tradeItemManageContent.add(tradeItemLab);
        tradeItemLab.setLayout(new GridLayout(0, 1, 0, 0));

        JLabel L_tradeItemTradeId = new JLabel("거래 번호 :");
        L_tradeItemTradeId.setHorizontalAlignment(SwingConstants.CENTER);
        L_tradeItemTradeId.setFont(new Font("굴림", Font.PLAIN, 15));
        tradeItemLab.add(L_tradeItemTradeId);

        JLabel L_tradeItemType = new JLabel("타입 :");
        L_tradeItemType.setHorizontalAlignment(SwingConstants.CENTER);
        L_tradeItemType.setFont(new Font("굴림", Font.PLAIN, 15));
        tradeItemLab.add(L_tradeItemType);

        JLabel L_tradeItemName = new JLabel("이름 :");
        L_tradeItemName.setHorizontalAlignment(SwingConstants.CENTER);
        L_tradeItemName.setFont(new Font("굴림", Font.PLAIN, 15));
        tradeItemLab.add(L_tradeItemName);

        JLabel L_tradeItemGrade = new JLabel("등급 :");
        L_tradeItemGrade.setHorizontalAlignment(SwingConstants.CENTER);
        L_tradeItemGrade.setFont(new Font("굴림", Font.PLAIN, 15));
        tradeItemLab.add(L_tradeItemGrade);

        JLabel L_tradeItemDesc = new JLabel("설명 :");
        L_tradeItemDesc.setHorizontalAlignment(SwingConstants.CENTER);
        L_tradeItemDesc.setFont(new Font("굴림", Font.PLAIN, 15));
        tradeItemLab.add(L_tradeItemDesc);

        JLabel L_tradeItemCount = new JLabel("개수 :");
        L_tradeItemCount.setHorizontalAlignment(SwingConstants.CENTER);
        L_tradeItemCount.setFont(new Font("굴림", Font.PLAIN, 15));
        tradeItemLab.add(L_tradeItemCount);

        JLabel L_tradeItemOp1 = new JLabel("옵션 1 :");
        L_tradeItemOp1.setHorizontalAlignment(SwingConstants.CENTER);
        L_tradeItemOp1.setFont(new Font("굴림", Font.PLAIN, 15));
        tradeItemLab.add(L_tradeItemOp1);

        JLabel L_tradeItemPrice = new JLabel("가격 :");
        L_tradeItemPrice.setHorizontalAlignment(SwingConstants.CENTER);
        L_tradeItemPrice.setFont(new Font("굴림", Font.PLAIN, 15));
        tradeItemLab.add(L_tradeItemPrice);

        JPanel tradeItemIn = new JPanel();
        tradeItemIn.setBounds(110, 0, 165, 393);
        tradeItemManageContent.add(tradeItemIn);
        tradeItemIn.setLayout(new GridLayout(0, 1, 0, 0));

        In_tradeItemId = new JTextField();
        In_tradeItemId.setEditable(false);
        tradeItemIn.add(In_tradeItemId);
        In_tradeItemId.setColumns(10);

        C_tradeItemType = new JComboBox<String>(itemTypes);
        tradeItemIn.add(C_tradeItemType);

        In_tradeItemName = new JTextField();
        tradeItemIn.add(In_tradeItemName);
        In_tradeItemName.setColumns(10);

        C_tradeItemGrade = new JComboBox<String>(itemGrades);
        tradeItemIn.add(C_tradeItemGrade);

        In_tradeItemDesc = new JTextField();
        tradeItemIn.add(In_tradeItemDesc);
        In_tradeItemDesc.setColumns(10);

        In_tradeItemCount = new JFormattedTextField(F_NumberFormet);
        tradeItemIn.add(In_tradeItemCount);
        In_tradeItemCount.setColumns(10);

        In_tradeItemOp1 = new JFormattedTextField(F_NumberFormet);
        tradeItemIn.add(In_tradeItemOp1);
        In_tradeItemOp1.setColumns(10);

        In_tradeItemPrice = new JFormattedTextField(F_NumberFormet);
        tradeItemIn.add(In_tradeItemPrice);
        In_tradeItemPrice.setColumns(10);

        JPanel tradeItemButtons = new JPanel();
        tradeItemButtons.setBounds(0, 391, 275, 135);
        tradeItemManageContent.add(tradeItemButtons);
        tradeItemButtons.setLayout(new GridLayout(0, 1, 0, 0));

        JButton Btt_createTradeItem = new JButton("아이템 생성");
        tradeItemButtons.add(Btt_createTradeItem);
        Btt_createTradeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (In_tradeItemName.getText().isEmpty())
                    JOptionPane.showMessageDialog(null, "이름을 채워주세요");
                else {
                    try {
                        String type = C_tradeItemType.getSelectedItem().toString();
                        String name = In_tradeItemName.getText().toString();
                        String grade = C_tradeItemGrade.getSelectedItem().toString();
                        String desc = In_tradeItemDesc.getText();
                        int option1 = Integer.valueOf(In_tradeItemOp1.getText().replace(",", ""));
                        int count = Integer.valueOf(In_tradeItemCount.getText().replace(",", ""));
                        int price = Integer.valueOf(In_tradeItemPrice.getText().replace(",", ""));

                        Item item = new ItemBuilder()
                                .type(type)
                                .name(name)
                                .grade(grade)
                                .desc(desc)
                                .option1(option1)
                                .build();

                        CreateTradeItemCommand createTItemCommand = new CreateTradeItemCommand("AUCTION", item,  price, count);
                        invoker.setCommand(createTItemCommand);
                        invoker.run();
                        refreshTradeItemTable();

                    } catch (NumberFormatException err) {
                        JOptionPane.showMessageDialog(null, "가격과 옵션에 숫자를 입력하세요");
                    }
                }
            }
        });
        Btt_createTradeItem.setFont(new Font("굴림", Font.PLAIN, 15));

        JButton Btt_updateTradeItem = new JButton("아이템 수정");
        tradeItemButtons.add(Btt_updateTradeItem);
        Btt_updateTradeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (In_tradeItemId.getText().isEmpty())
                    JOptionPane.showMessageDialog(null, "이름을 채워주세요");
                else {
                    try {
                        String type = C_tradeItemType.getSelectedItem().toString();
                        String name = In_tradeItemName.getText().toString();
                        String grade = C_tradeItemGrade.getSelectedItem().toString();
                        String desc = In_tradeItemDesc.getText();
                        int option1 = Integer.valueOf(In_tradeItemOp1.getText().replace(",", ""));
                        int count = Integer.valueOf(In_tradeItemCount.getText().replace(",", ""));
                        int price = Integer.valueOf(In_tradeItemPrice.getText().replace(",", ""));

                        Item item = new ItemBuilder()
                                .type(type)
                                .name(name)
                                .grade(grade)
                                .desc(desc)
                                .option1(option1)
                                .build();

                        TradeItem newitem = new TradeItem("AUCTION", item, count, price);
                        UpdateTradeItemCommand updateTItemCommand = new UpdateTradeItemCommand(Integer.parseInt(In_tradeItemId.getText()), newitem);
                        invoker.setCommand(updateTItemCommand);
                        invoker.run();
                        refreshTradeItemTable();

                    } catch (NumberFormatException err) {
                        JOptionPane.showMessageDialog(null, "가격과 옵션에 숫자를 입력하세요");
                    }
                }
            }
        });
        Btt_updateTradeItem.setFont(new Font("굴림", Font.PLAIN, 15));

        JButton Btt_deleteTradeItem = new JButton("아이템 삭제");
        tradeItemButtons.add(Btt_deleteTradeItem);
        Btt_deleteTradeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (In_tradeItemId.getText().isEmpty())
                    JOptionPane.showMessageDialog(null, "아이템을 선택해주세요.");
                else {
                    DeleteTradeItemCommand deleteTItemCommand = new DeleteTradeItemCommand(Integer.parseInt(In_tradeItemId.getText()));

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
                    C_tradeItemType.setSelectedItem(T_tradeItemList.getValueAt(selectedRow, 2).toString());
                    In_tradeItemName.setText(T_tradeItemList.getValueAt(selectedRow, 3).toString());
                    C_tradeItemGrade.setSelectedItem(T_tradeItemList.getValueAt(selectedRow, 4).toString());
                    In_tradeItemDesc.setText(T_tradeItemList.getValueAt(selectedRow, 5).toString());
                    In_tradeItemCount.setText(T_tradeItemList.getValueAt(selectedRow, 6).toString());
                    In_tradeItemOp1.setText(T_tradeItemList.getValueAt(selectedRow, 7).toString());
                    In_tradeItemPrice.setText(T_tradeItemList.getValueAt(selectedRow, 8).toString());
                }
            }
        });

       
        /*
         *      HISTORY PAGE
         */

        JPanel tradeHistoryPage = new JPanel();
        tradeHistoryPage.setLayout(null);
        contents.add(tradeHistoryPage, "TradeHistoryPage");

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
        
        JComboBox<String> C_itemInfoType = new JComboBox<String>(itemTypes);
        itemInfoIn.add(C_itemInfoType);
        
        In_itemInfoName = new JTextField();
        In_itemInfoName.setColumns(10);
        itemInfoIn.add(In_itemInfoName);
        
        JComboBox<String> C_itemInfoGrade = new JComboBox<String>(itemGrades);
        itemInfoIn.add(C_itemInfoGrade);
        
        In_itemInfoDesc = new JTextField();
        In_itemInfoDesc.setColumns(10);
        itemInfoIn.add(In_itemInfoDesc);
        
        JFormattedTextField In_itemInfoOp1 = new JFormattedTextField(F_NumberFormet);
        In_itemInfoOp1.setColumns(10);
        itemInfoIn.add(In_itemInfoOp1);
        
        JPanel itemInfoButtons = new JPanel();
        itemInfoButtons.setBounds(0, 391, 275, 135);
        itemInfoManageContent.add(itemInfoButtons);
        itemInfoButtons.setLayout(new GridLayout(0, 1, 0, 0));
        
        JButton Btt_createTradeItem_1 = new JButton("아이템 생성");
        Btt_createTradeItem_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!In_itemInfoName.getText().isEmpty() && !In_itemInfoDesc.getText().isEmpty() && !In_itemInfoOp1.getText().isEmpty()){
                    String type = C_itemInfoType.getSelectedItem().toString();
                    String name = In_itemInfoName.getText();
                    String grade = C_itemInfoGrade.getSelectedItem().toString();
                    String desc = In_itemInfoDesc.getText();
                    int option1 = Integer.valueOf(In_itemInfoOp1.getText().replace(",", ""));
    
    
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
                else{
                    JOptionPane.showMessageDialog(null,"값을 입력하세요");
                }
                
            }
        });
        Btt_createTradeItem_1.setFont(new Font("굴림", Font.PLAIN, 15));
        itemInfoButtons.add(Btt_createTradeItem_1);
        
        JButton Btt_updateTradeItem_1 = new JButton("아이템 수정");
        Btt_updateTradeItem_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!In_itemInfoName.getText().isEmpty() && !In_itemInfoDesc.getText().isEmpty() && !In_itemInfoOp1.getText().isEmpty()){
                    String type = C_itemInfoType.getSelectedItem().toString();
                    String name = In_itemInfoName.getText();
                    String grade = C_itemInfoGrade.getSelectedItem().toString();
                    String desc = In_itemInfoDesc.getText();
                    int option1 = Integer.valueOf(In_itemInfoOp1.getText().replace(",", ""));
    
    
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
                else{
                    JOptionPane.showMessageDialog(null,"값을 입력하세요");
                }
            }
        });
        Btt_updateTradeItem_1.setFont(new Font("굴림", Font.PLAIN, 15));
        itemInfoButtons.add(Btt_updateTradeItem_1);
        
        JButton Btt_deleteTradeItem_1 = new JButton("아이템 삭제");
        Btt_deleteTradeItem_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!In_itemInfoName.getText().isEmpty()){
                    DeleteItemCommand deleteItemCommand = new DeleteItemCommand(ItemFileSystem.getItemFileSystem().getItemByName(In_itemInfoName.getText()));
                    invoker.setCommand(deleteItemCommand);
                    invoker.run();

                    refreshItemInfoTable();
                }
                else{
                    JOptionPane.showMessageDialog(null,"이름을 입력하세요");
                }

            }
        });
        Btt_deleteTradeItem_1.setFont(new Font("굴림", Font.PLAIN, 15));
        itemInfoButtons.add(Btt_deleteTradeItem_1);

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
 
         In_userGold = new JFormattedTextField(F_NumberFormet);
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
 
         JButton Btt_createUser = new JButton("유저 생성");
         Btt_createUser.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 User user = new User.UserBuilder()
                         .ID(In_userID.getText())
                         .password(In_userPassword.getText())
                         .name(In_userName.getText())
                         .phoneNumber(In_userPhoneNumber.getText())
                         .gold(Integer.valueOf(In_userGold.getText().replace(",", "")))
                         .build();
 
                 CreateUserCommand command = new CreateUserCommand(user);
                 invoker.setCommand(command);
                 invoker.run();
                 refreshUserTable();
 
             }
         });
         Btt_createUser.setFont(new Font("굴림", Font.PLAIN, 15));
         userButtons.add(Btt_createUser);
 
         JButton Btt_updateUser = new JButton("유저 수정");
         Btt_updateUser.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 User user = new User.UserBuilder()
                         .ID(In_userID.getText())
                         .password(In_userPassword.getText())
                         .name(In_userName.getText())
                         .phoneNumber(In_userPhoneNumber.getText())
                         .gold(Integer.valueOf(In_userGold.getText().replace(",", "")))
                         .build();
 
                 UpdateUserCommand command = new UpdateUserCommand(user);
                 invoker.setCommand(command);
                 invoker.run();
                 refreshUserTable();
 
             }
         });
         Btt_updateUser.setFont(new Font("굴림", Font.PLAIN, 15));
         userButtons.add(Btt_updateUser);
 
         JButton Btt_deleteUser = new JButton("유저 삭제");
         Btt_deleteUser.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 if (In_userID.getText().isEmpty())
                     JOptionPane.showMessageDialog(null, "아이디를 채워주세요");
                 else {
                     DeleteUserCommand command = new DeleteUserCommand(UserFileSystem.getUserFileSystem().getUserById(In_userID.getText()));
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
        
        In_userItemOp1 = new JFormattedTextField(F_NumberFormet);
        In_userItemOp1.setEditable(false);
        In_userItemOp1.setColumns(10);
        userItemIn.add(In_userItemOp1);
        
        JFormattedTextField In_userItemCount = new JFormattedTextField(F_NumberFormet);
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
                if(!In_userItemCount.getText().isEmpty()){
                    String type = In_userItemType.getText();
                    String name = In_userItemName.getText();
                    String grade = In_userItemGrade.getText();
                    String desc = In_userItemDesc.getText();
                    int option1 = Integer.valueOf(In_userItemOp1.getText().replace(",", ""));
                    int count = Integer.valueOf(In_userItemCount.getText().replace(",", ""));
    
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
                else{
                    JOptionPane.showMessageDialog(null, "아이템 개수를 입력해주세요");
                }
            }
        });
        userItemButtons.add(Btt_createUserItem);
        
        JButton Btt_updateUserItem = new JButton("아이템 수정");
        Btt_updateUserItem.setFont(new Font("굴림", Font.PLAIN, 15));
        Btt_updateUserItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!In_userItemCount.getText().isEmpty()){
                    String type = In_userItemType.getText();
                    String name = In_userItemName.getText();
                    String grade = In_userItemGrade.getText();
                    String desc = In_userItemDesc.getText();
                    int option1 = Integer.valueOf(In_userItemOp1.getText().replace(",", ""));
                    int count = Integer.valueOf(In_userItemCount.getText().replace(",", ""));
    
    
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
                else{
                    JOptionPane.showMessageDialog(null, "아이템 개수를 입력해주세요");
                }

            }
        });
        userItemButtons.add(Btt_updateUserItem);
        
        JButton Btt_deleteUserItem = new JButton("아이템 삭제");
        Btt_deleteUserItem.setFont(new Font("굴림", Font.PLAIN, 15));
        Btt_deleteUserItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!In_userItemName.getText().isEmpty()){
                    DeleteInvItemCommand deleteInvItemCommand = new DeleteInvItemCommand(currentUser, In_userItemName.getText());
                    invoker.setCommand(deleteInvItemCommand);
                    invoker.run();

                    refreshUserItemTable();

                }
                else{
                    JOptionPane.showMessageDialog(null, "아이템 개수를 입력해주세요");
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
                    In_userItemCount.setText(T_userItemList.getValueAt(selectedRow, 4).toString());
                    In_userItemOp1.setText(T_userItemList.getValueAt(selectedRow, 5).toString());
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
                }
            }
        });

        /*
         * 		SUB MANU
         */

        JPanel subManu = new JPanel();
        CardLayout subCardLayout = new CardLayout(0, 0);
        subManu.setBounds(518, 33, 556, 55);
        subManu.setLayout(subCardLayout);
        contentPane.add(subManu);

        JPanel auctionSubMnau = new JPanel();
        subManu.add(auctionSubMnau, "AuctionSubManu");
        auctionSubMnau.setLayout(new GridLayout(1, 0, 0, 0));

        JButton Btt_auctionItemList = new JButton("경매장 물품 관리");
        Btt_auctionItemList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainCardLayout.show(contents, "AuctionManagePage");
            }
        });
        auctionSubMnau.add(Btt_auctionItemList);

        JButton Btt_autionManage = new JButton("경매장 기록");
        Btt_autionManage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainCardLayout.show(contents, "TradeHistoryPage");
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
                    refreshUserItemTable();
                    refreshUserItemInfoTable();
                    mainCardLayout.show(contents, "UserInventoryPage");

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
        manu.setBounds(12, 10, 459, 78);
        contentPane.add(manu);
        manu.setLayout(new GridLayout(1, 0, 0, 0));

        JButton Btt_goAution = new JButton("경매장 관리");
        Btt_goAution.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainCardLayout.show(contents, "AuctionManagePage");
                subCardLayout.show(subManu, "AuctionSubManu");
                refreshTradeItemTable();
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
    }

    public void refreshTradeItemTable() {
        tradeItemTableModel.setRowCount(0);

        for (TradeItem item : TradeItemFileSystem.getTradeItemFileSystem().getTradeItemList()) {
            Object[] rowData = item.getListData();
            tradeItemTableModel.addRow(rowData);
        }

        S_tradeItemList.setViewportView(T_tradeItemList);
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

    public void refreshItemInfoTable(){
        itemInfoTableModel.setRowCount(0);

        for (Item item : ItemFileSystem.getItemFileSystem().getItemList()) {
            Object[] rowData = item.getListData();
            itemInfoTableModel.addRow(rowData);
        }

        S_itemInfoList.setViewportView(T_itemInfoList);
    }
}