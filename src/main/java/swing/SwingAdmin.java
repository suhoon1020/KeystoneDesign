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
import managers.FileFacade;
import user.inventoryItem.Item;
import user.inventoryItem.ItemBuilder;
import user.userprivacy.User;


public class SwingAdmin extends JFrame {
    private String[] itemTypes = {"Equipment", "Material", "Potion", "Weapon"};
    private String[] itemGrades = {"Common", "Uncommon", "Eqic", "Legendary"};

    private String[] tradeItemHeader = {"TRADEID", "USERID", "TYPE", "NAME", "GRADE", "DESC", "COUNT", "OPTION1", "PRICE"};
    private String[] userHeader = {"ID", "PW", "NAME", "PHONE", "GOLD"};
    private String[] userItemHeader = {"TYPE", "NAME", "GRADE", "DESC", "COUNT", "OPTION1"};

    private static final long serialVersionUID = 1L;
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

    private JTextField In_inventoryUserName;
    private JComboBox<String> C_userItemType;
    private JTextField In_userItemName;
    private JComboBox<String> C_userItemGrade;
    private JTextField In_userItemDesc;
    private JFormattedTextField In_userItemCount;
    private JFormattedTextField In_userItemOp1;

    private JTable T_ItemList;
    private JScrollPane S_itemList;
    private DefaultTableModel itemTableModel;

    private JTable T_userList;
    private JScrollPane S_userList;
    private DefaultTableModel userTableModel;

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

        itemTableModel = new DefaultTableModel(tradeItemHeader, 0);
        T_ItemList = new JTable(itemTableModel);
        T_ItemList.getTableHeader().setReorderingAllowed(false);
        T_ItemList.getTableHeader().setResizingAllowed(false);
        S_itemList = new JScrollPane();
        tradeItemList.add(S_itemList);
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
                        Item item = new ItemBuilder()
                                .type(C_tradeItemType.getSelectedItem().toString())
                                .name(In_tradeItemName.getText())
                                .grade(C_tradeItemGrade.getSelectedItem().toString())
                                .desc(In_tradeItemDesc.getText())
                                .count(Integer.valueOf(In_tradeItemCount.getText().replace(",", "")))
                                .option1(Integer.valueOf(In_tradeItemOp1.getText().replace(",", "")))
                                .build();

                        TradeItem newitem = new TradeItem("AUCTION", Integer.valueOf(In_tradeItemPrice.getText().replace(",", "")), item);
                        FileFacade.getFacade().putTradeItem(newitem);
                        JOptionPane.showMessageDialog(null, "아이템 생성이 완료 되었습니다");
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
                        Item item = new ItemBuilder()
                                .type(C_tradeItemType.getSelectedItem().toString())
                                .name(In_tradeItemName.getText())
                                .grade(C_tradeItemGrade.getSelectedItem().toString())
                                .desc(In_tradeItemDesc.getText())
                                .count(Integer.valueOf(In_tradeItemCount.getText().replace(",", "")))
                                .option1(Integer.valueOf(In_tradeItemOp1.getText().replace(",", "")))
                                .build();

                        TradeItem newitem = new TradeItem("AUCTION", Integer.valueOf(In_tradeItemPrice.getText().replace(",", "")), item);

                        if (FileFacade.getFacade().updateTradeItem(Integer.parseInt(In_tradeItemId.getText()), newitem)) {
                            JOptionPane.showMessageDialog(null, "아이템 수정이 완료 되었습니다");
                            refreshTradeItemTable();
                        } else {
                            JOptionPane.showMessageDialog(null, "존재하지 않는 아이템입니다");
                        }
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
                    if (FileFacade.getFacade().deleteTradeItem(Integer.parseInt(In_tradeItemId.getText()))) {
                        JOptionPane.showMessageDialog(null, "아이템 삭제가 완료 되었습니다");
                        refreshTradeItemTable();
                    } else
                        JOptionPane.showMessageDialog(null, "존재하지 않는 아이템입니다");
                }
            }
        });
        Btt_deleteTradeItem.setFont(new Font("굴림", Font.PLAIN, 15));

        T_ItemList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && T_ItemList.getSelectedRow() != -1) {
                    // 선택된 행의 데이터 출력
                    int selectedRow = T_ItemList.getSelectedRow();

                    In_tradeItemId.setText(T_ItemList.getValueAt(selectedRow, 0).toString());
                    C_tradeItemType.setSelectedItem(T_ItemList.getValueAt(selectedRow, 2).toString());
                    In_tradeItemName.setText(T_ItemList.getValueAt(selectedRow, 3).toString());
                    C_tradeItemGrade.setSelectedItem(T_ItemList.getValueAt(selectedRow, 4).toString());
                    In_tradeItemDesc.setText(T_ItemList.getValueAt(selectedRow, 5).toString());
                    In_tradeItemCount.setText(T_ItemList.getValueAt(selectedRow, 6).toString());
                    In_tradeItemOp1.setText(T_ItemList.getValueAt(selectedRow, 7).toString());
                    In_tradeItemPrice.setText(T_ItemList.getValueAt(selectedRow, 8).toString());
                }
            }
        });
        
        /*
         * UserManage Page
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


                if (FileFacade.getFacade().putUser(user)) {
                    JOptionPane.showMessageDialog(null, "유저 생성이 완료 되었습니다");
                    refreshUserTable();
                } else {
                    JOptionPane.showMessageDialog(null, "중복된 ID가 있습니다");
                }

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
                if (FileFacade.getFacade().updateUser(In_userID.getText(), user)) {
                    JOptionPane.showMessageDialog(null, "유저 수정이 완료 되었습니다");
                    refreshUserTable();
                } else {
                    JOptionPane.showMessageDialog(null, "존재하지 않는 ID입니다");
                }
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
                    if (FileFacade.getFacade().deleteUser(In_userID.getText())) {
                        JOptionPane.showMessageDialog(null, "유저 삭제가 완료 되었습니다");
                        refreshUserTable();
                    } else
                        JOptionPane.showMessageDialog(null, "존재하지 않는 ID입니다");
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
         *      HISTORY PAGE
         */
        
        JPanel tradeHistoryPage = new JPanel();
        tradeHistoryPage.setLayout(null);
        contents.add(tradeHistoryPage, "TradeHistoryPage");

        /*
         *      USER INVENTORY PAGE
         */
        
        JPanel userInventoryPage = new JPanel();
        userInventoryPage.setLayout(null);
        contents.add(userInventoryPage, "UserInventoryPage");
        
        JPanel userItemList = new JPanel();
        userItemList.setBorder(new LineBorder(new Color(0, 0, 0)));
        userItemList.setBounds(10, 10, 751, 526);
        userInventoryPage.add(userItemList);
        userItemList.setLayout(new GridLayout(0, 1, 0, 0));
        
        JScrollPane S_userItemList = new JScrollPane();
        userItemList.add(S_userItemList);
        
        JPanel useItemManageContent = new JPanel();
        useItemManageContent.setLayout(null);
        useItemManageContent.setBorder(new LineBorder(new Color(0, 0, 0)));
        useItemManageContent.setBounds(773, 10, 275, 526);
        userInventoryPage.add(useItemManageContent);
        
        JPanel userItemLab = new JPanel();
        userItemLab.setBounds(0, 0, 109, 393);
        useItemManageContent.add(userItemLab);
        userItemLab.setLayout(new GridLayout(0, 1, 0, 0));
        
        JLabel L_inventoryUserName = new JLabel("유저 이름 :");
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
        
        JLabel L_userItemCount = new JLabel("개수 :");
        L_userItemCount.setHorizontalAlignment(SwingConstants.CENTER);
        L_userItemCount.setFont(new Font("굴림", Font.PLAIN, 15));
        userItemLab.add(L_userItemCount);
        
        JLabel L_userItemOp1 = new JLabel("옵션 1 :");
        L_userItemOp1.setHorizontalAlignment(SwingConstants.CENTER);
        L_userItemOp1.setFont(new Font("굴림", Font.PLAIN, 15));
        userItemLab.add(L_userItemOp1);
        
        JPanel tradeItemIn_1 = new JPanel();
        tradeItemIn_1.setBounds(110, 0, 165, 393);
        useItemManageContent.add(tradeItemIn_1);
        tradeItemIn_1.setLayout(new GridLayout(0, 1, 0, 0));
        
        In_inventoryUserName = new JTextField();
        In_inventoryUserName.setEditable(false);
        In_inventoryUserName.setColumns(10);
        tradeItemIn_1.add(In_inventoryUserName);
        
        C_userItemType = new JComboBox<String>(itemTypes);
        tradeItemIn_1.add(C_userItemType);
        
        In_userItemName = new JTextField();
        In_userItemName.setColumns(10);
        tradeItemIn_1.add(In_userItemName);
        
        C_userItemGrade = new JComboBox<String>(itemGrades);
        tradeItemIn_1.add(C_userItemGrade);
        
        In_userItemDesc = new JTextField();
        In_userItemDesc.setColumns(10);
        tradeItemIn_1.add(In_userItemDesc);
        
        In_userItemCount = new JFormattedTextField(F_NumberFormet);
        In_userItemCount.setColumns(10);
        tradeItemIn_1.add(In_userItemCount);
        
        In_userItemOp1 = new JFormattedTextField(F_NumberFormet);
        In_userItemOp1.setColumns(10);
        tradeItemIn_1.add(In_userItemOp1);
        
        JPanel tradeItemButtons_1 = new JPanel();
        tradeItemButtons_1.setBounds(0, 391, 275, 135);
        useItemManageContent.add(tradeItemButtons_1);
        tradeItemButtons_1.setLayout(new GridLayout(0, 1, 0, 0));
        
        JButton Btt_createUserItem = new JButton("아이템 생성");
        Btt_createUserItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        Btt_createUserItem.setFont(new Font("굴림", Font.PLAIN, 15));
        tradeItemButtons_1.add(Btt_createUserItem);
        
        JButton Btt_updateUserItem = new JButton("아이템 수정");
        Btt_updateUserItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        Btt_updateUserItem.setFont(new Font("굴림", Font.PLAIN, 15));
        tradeItemButtons_1.add(Btt_updateUserItem);
        
        JButton Btt_deleteUserItem = new JButton("아이템 삭제");
        Btt_deleteUserItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        Btt_deleteUserItem.setFont(new Font("굴림", Font.PLAIN, 15));
        tradeItemButtons_1.add(Btt_deleteUserItem);


        
        /*
         * 		SUB MANU
         */
        
        JPanel subManu = new JPanel();
        CardLayout subCardLayout = new CardLayout(0, 0);
        subManu.setBounds(382, 33, 692, 55);
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
                mainCardLayout.show(contents, "UserInventoryPage");
        	}
        });
        userSubManu.add(Btt_userInventory);
        
        /*
         * 		MAIN MANU
         */
        
        
        JPanel manu = new JPanel();
        manu.setBounds(12, 10, 365, 78);
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
        itemTableModel.setRowCount(0);

        for (TradeItem item : FileFacade.getFacade().getTradeItemList()) {
            Object[] rowData = item.getListData();
            itemTableModel.addRow(rowData);
        }

        S_itemList.setViewportView(T_ItemList);
    }

    public void refreshUserTable() {
        userTableModel.setRowCount(0);

        for (User user : FileFacade.getFacade().getUserList()) {
            Object[] rowData = user.getListData();
            userTableModel.addRow(rowData);
        }

        S_userList.setViewportView(T_userList);
    }

    public void refreshUserInventory(){
        
    }
}