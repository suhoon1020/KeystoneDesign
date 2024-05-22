package SwingManager;

import AuctionManager.Auction;
import DataManager.FileFacade;
import ItemsManager.Item;
import ItemsManager.ItemBuilder;
import UserOption.User;

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

public class SwingAdmin extends JFrame {
    private String[] ItemTypes = {"Equipment", "Material", "Potion", "Weapon"};
    private String[] ItemGrades = {"Common", "Uncommon", "Eqic", "Legendary"};

    private String[] itemHeader = {"TYPE", "NAME", "GRADE", "DESC", "COUNT", "OPTION1"};
    private String[] userHeader = {"ID", "PW", "NAME", "PHONE", "GOLD"};

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField In_itemName;
    private JTextField In_itemDesc;
    private JFormattedTextField In_ItemCount;
    private JFormattedTextField In_ItemOp1;
    private JTextField In_userID;
    private JTextField In_userPassword;
    private JTextField In_userName;
    private JTextField In_userPhoneNumber;
    private JFormattedTextField In_userGold;

    private JTable T_ItemList;
    private JScrollPane S_itemList;
    private DefaultTableModel itemTableModel;

    private JTable T_userList;
    private JScrollPane S_userList;
    private DefaultTableModel userTableModel;
    private JTextField jtext_State;



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
        setBounds(100, 100, 1100, 700);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel contents = new JPanel();
        CardLayout cardLayout = new CardLayout(0, 0);
        contents.setBorder(new LineBorder(new Color(0, 0, 0)));
        contents.setBounds(12, 107, 1062, 546);
        contents.setLayout(cardLayout);
        contentPane.add(contents);


        /*
         * AuctionManage Page
         */

        JPanel auctionPage = new JPanel();
        contents.add(auctionPage, "AuctionManagePage");
        auctionPage.setLayout(null);

        jtext_State = new JTextField();
        jtext_State.setBounds(850, 32, 116, 21);
        auctionPage.add(jtext_State);
        jtext_State.setColumns(10);

        jtext_State.setText("닫힘");
        jtext_State.setEnabled(false);
        JButton btn_Auction_Open = new JButton("거래소 열기");
        btn_Auction_Open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Auction.getAuction().getState().auctionOn();
                jtext_State.setText("열림");
            }
        });
        btn_Auction_Open.setBounds(122, 116, 381, 213);
        auctionPage.add(btn_Auction_Open);

        JButton btn_Auction_Close = new JButton("거래소 닫기");
        btn_Auction_Close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Auction.getAuction().getState().auctionOff();
                jtext_State.setText("닫힘");
            }
        });
        btn_Auction_Close.setBounds(544, 116, 411, 213);
        auctionPage.add(btn_Auction_Close);

        JLabel lblNewLabel = new JLabel("현 거래소 상태 : ");
        lblNewLabel.setBounds(723, 29, 111, 27);
        auctionPage.add(lblNewLabel);



        /*
         * ItemManage Page
         */

        JPanel itemPage = new JPanel();
        contents.add(itemPage, "ItemManagePage");
        itemPage.setLayout(null);

        JPanel itemList = new JPanel();
        itemList.setBounds(12, 10, 641, 526);
        itemList.setBorder(new LineBorder(new Color(0, 0, 0)));
        itemList.setLayout(new GridLayout(0, 1, 0, 0));
        itemPage.add(itemList);

        itemTableModel = new DefaultTableModel(itemHeader, 0);
        T_ItemList = new JTable(itemTableModel);
        T_ItemList.getTableHeader().setReorderingAllowed(false);
        T_ItemList.getTableHeader().setResizingAllowed(false);
        S_itemList = new JScrollPane();
        itemList.add(S_itemList);

        JPanel itemManageContent = new JPanel();
        itemManageContent.setBorder(new LineBorder(new Color(0, 0, 0)));
        itemManageContent.setBounds(665, 10, 385, 526);
        itemPage.add(itemManageContent);
        itemManageContent.setLayout(null);

        JPanel itemLab = new JPanel();
        itemLab.setBounds(12, 10, 177, 320);
        itemManageContent.add(itemLab);
        itemLab.setLayout(new GridLayout(0, 1, 0, 0));

        JLabel L_itemType = new JLabel("아이템 타입 :");
        L_itemType.setHorizontalAlignment(SwingConstants.CENTER);
        L_itemType.setFont(new Font("굴림", Font.PLAIN, 15));
        itemLab.add(L_itemType);

        JLabel L_itemName = new JLabel("아이템 이름 :");
        L_itemName.setHorizontalAlignment(SwingConstants.CENTER);
        L_itemName.setFont(new Font("굴림", Font.PLAIN, 15));
        itemLab.add(L_itemName);

        JLabel L_itemGrade = new JLabel("아이템 등급 :");
        L_itemGrade.setHorizontalAlignment(SwingConstants.CENTER);
        L_itemGrade.setFont(new Font("굴림", Font.PLAIN, 15));
        itemLab.add(L_itemGrade);

        JLabel L_itemDesc = new JLabel("아이템 설명 :");
        L_itemDesc.setHorizontalAlignment(SwingConstants.CENTER);
        L_itemDesc.setFont(new Font("굴림", Font.PLAIN, 15));
        itemLab.add(L_itemDesc);

        JLabel L_itemCount = new JLabel("아이템 개수 :");
        L_itemCount.setHorizontalAlignment(SwingConstants.CENTER);
        L_itemCount.setFont(new Font("굴림", Font.PLAIN, 15));
        itemLab.add(L_itemCount);

        JLabel L_itemOp1 = new JLabel("옵션 1 :");
        L_itemOp1.setHorizontalAlignment(SwingConstants.CENTER);
        L_itemOp1.setFont(new Font("굴림", Font.PLAIN, 15));
        itemLab.add(L_itemOp1);

        JPanel itemIn = new JPanel();
        itemIn.setBounds(196, 10, 177, 320);
        itemManageContent.add(itemIn);
        itemIn.setLayout(new GridLayout(0, 1, 0, 0));

        JComboBox<String> C_itemType = new JComboBox<String>(ItemTypes);
        itemIn.add(C_itemType);

        In_itemName = new JTextField();
        itemIn.add(In_itemName);
        In_itemName.setColumns(10);

        JComboBox<String> C_itemGrade = new JComboBox<String>(ItemGrades);
        itemIn.add(C_itemGrade);

        In_itemDesc = new JTextField();
        itemIn.add(In_itemDesc);
        In_itemDesc.setColumns(10);

        NumberFormatter F_SetCountNumber = new NumberFormatter();
        F_SetCountNumber.setValueClass(Integer.class);
        F_SetCountNumber.setMinimum(Integer.valueOf(1));
        F_SetCountNumber.setMaximum(Integer.valueOf(100000));

        NumberFormatter F_SetOptionNumber = new NumberFormatter();
        F_SetOptionNumber.setValueClass(Integer.class);
        F_SetOptionNumber.setMinimum(Integer.valueOf(1));
        F_SetOptionNumber.setMaximum(Integer.valueOf(1000000));

        In_ItemCount = new JFormattedTextField(F_SetCountNumber);
        itemIn.add(In_ItemCount);
        In_ItemCount.setColumns(10);

        In_ItemOp1 = new JFormattedTextField(F_SetOptionNumber);
        itemIn.add(In_ItemOp1);
        In_ItemOp1.setColumns(10);

        T_ItemList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && T_ItemList.getSelectedRow() != -1) {
                    // 선택된 행의 데이터 출력
                    int selectedRow = T_ItemList.getSelectedRow();

                    C_itemType.setSelectedItem(T_ItemList.getValueAt(selectedRow, 0).toString());
                    In_itemName.setText(T_ItemList.getValueAt(selectedRow, 1).toString());
                    C_itemGrade.setSelectedItem(T_ItemList.getValueAt(selectedRow, 2).toString());
                    In_itemDesc.setText(T_ItemList.getValueAt(selectedRow, 3).toString());
                    In_ItemCount.setText(T_ItemList.getValueAt(selectedRow, 4).toString());
                    In_ItemOp1.setText(T_ItemList.getValueAt(selectedRow, 5).toString());
                }
            }
        });

        JButton Btt_createItem = new JButton("아이템 생성");
        Btt_createItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (In_itemName.getText().isEmpty())
                    JOptionPane.showMessageDialog(null, "이름을 채워주세요");
                else {
                    try {
                        Item item = new ItemBuilder()
                                .type(C_itemType.getSelectedItem().toString())
                                .name(In_itemName.getText())
                                .grade(C_itemGrade.getSelectedItem().toString())
                                .desc(In_itemDesc.getText())
                                .count(Integer.valueOf(In_ItemCount.getText().replace(",", "")))
                                .option1(Integer.valueOf(In_ItemOp1.getText().replace(",", "")))
                                .build();

                        if (FileFacade.getFacade().putItem(item)) {
                            JOptionPane.showMessageDialog(null, "아이템 생성이 완료 되었습니다");
                            FileFacade.getFacade().saveItems();
                            refreshItemTable();
                        } else {
                            JOptionPane.showMessageDialog(null, "중복된 이름이 있습니다");
                        }
                    } catch (NumberFormatException err) {
                        JOptionPane.showMessageDialog(null, "가격과 옵션에 숫자를 입력하세요");
                    }
                }
            }
        });
        Btt_createItem.setFont(new Font("굴림", Font.PLAIN, 15));
        Btt_createItem.setBounds(67, 340, 258, 43);
        itemManageContent.add(Btt_createItem);

        JButton Btt_updateItem = new JButton("아이템 수정");
        Btt_updateItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (In_itemName.getText().isEmpty())
                    JOptionPane.showMessageDialog(null, "이름을 채워주세요");
                else {
                    try {
                        Item item = new ItemBuilder()
                                .type(C_itemType.getSelectedItem().toString())
                                .name(In_itemName.getText())
                                .grade(C_itemGrade.getSelectedItem().toString())
                                .desc(In_itemDesc.getText())
                                .count(Integer.valueOf(In_ItemCount.getText().replace(",", "")))
                                .option1(Integer.parseInt(In_ItemOp1.getText().replace(",", "")))
                                .build();

                        if (FileFacade.getFacade().updateItem(In_itemName.getText(), item)) {
                            JOptionPane.showMessageDialog(null, "아이템 수정이 완료 되었습니다");
                            FileFacade.getFacade().saveItems();
                            refreshItemTable();
                        } else {
                            JOptionPane.showMessageDialog(null, "존재하지 않는 아이템입니다");
                        }
                    } catch (NumberFormatException err) {
                        JOptionPane.showMessageDialog(null, "가격과 옵션에 숫자를 입력하세요");
                    }
                }
            }
        });
        Btt_updateItem.setFont(new Font("굴림", Font.PLAIN, 15));
        Btt_updateItem.setBounds(67, 406, 258, 43);
        itemManageContent.add(Btt_updateItem);

        JButton Btt_deleteItem = new JButton("아이템 삭제");
        Btt_deleteItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (In_itemName.getText().isEmpty())
                    JOptionPane.showMessageDialog(null, "이름을 채워주세요");
                else {
                    if (FileFacade.getFacade().deleteItem(In_itemName.getText())) {
                        JOptionPane.showMessageDialog(null, "아이템 삭제가 완료 되었습니다");
                        FileFacade.getFacade().saveItems();
                        refreshItemTable();
                    } else
                        JOptionPane.showMessageDialog(null, "존재하지 않는 아이템입니다");
                }
            }
        });
        Btt_deleteItem.setFont(new Font("굴림", Font.PLAIN, 15));
        Btt_deleteItem.setBounds(67, 473, 258, 43);
        itemManageContent.add(Btt_deleteItem);

        /*
         * UserManage Page
         */

        JPanel userPage = new JPanel();
        userPage.setBorder(new LineBorder(new Color(0, 0, 0)));
        userPage.setLayout(null);
        contents.add(userPage, "UserManagePage");

        JPanel userList = new JPanel();
        userList.setBounds(12, 10, 641, 526);
        userList.setBorder(new LineBorder(new Color(0, 0, 0)));
        userList.setLayout(new GridLayout(0, 1, 0, 0));
        userPage.add(userList);

        userTableModel = new DefaultTableModel(userHeader, 0);
        T_userList = new JTable(userTableModel);
        T_userList.getTableHeader().setReorderingAllowed(false);
        T_userList.getTableHeader().setResizingAllowed(false);
        S_userList = new JScrollPane();
        userList.add(S_userList);

        JPanel userManageContent = new JPanel();
        userManageContent.setBorder(new LineBorder(new Color(0, 0, 0)));
        userManageContent.setLayout(null);
        userManageContent.setBounds(665, 10, 385, 526);
        userPage.add(userManageContent);

        JPanel userLab = new JPanel();
        userLab.setBounds(12, 10, 177, 320);
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

        JPanel userIn = new JPanel();
        userIn.setBounds(196, 10, 177, 320);
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

        NumberFormatter F_SetGoldNumber = new NumberFormatter();
        F_SetCountNumber.setValueClass(Integer.class);
        F_SetCountNumber.setMinimum(Integer.valueOf(1));
        F_SetCountNumber.setMaximum(Integer.valueOf(100000));

        In_userGold = new JFormattedTextField(F_SetGoldNumber);
        userIn.add(In_userGold);
        In_userGold.setColumns(10);

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

        JButton Btt_createUser = new JButton("유저 생성");
        Btt_createUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                User user = new User.UserBuilder()
                        .ID(In_userID.getText())
                        .PW(In_userPassword.getText())
                        .name(In_userName.getText())
                        .phone(In_userPhoneNumber.getText())
                        .gold(Integer.valueOf(In_userGold.getText().replace(",", "")))
                        .build();
                if (FileFacade.getFacade().putUser(user)) {
                    JOptionPane.showMessageDialog(null, "유저 생성이 완료 되었습니다");
                    FileFacade.getFacade().saveUsers();
                    refreshUserTable();
                } else {
                    JOptionPane.showMessageDialog(null, "중복된 ID가 있습니다");
                }
            }
        });
        Btt_createUser.setFont(new Font("굴림", Font.PLAIN, 15));
        Btt_createUser.setBounds(67, 340, 258, 43);
        userManageContent.add(Btt_createUser);

        JButton Btt_updateUser = new JButton("유저 수정");
        Btt_updateUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                User user = new User.UserBuilder()
                        .ID(In_userID.getText())
                        .PW(In_userPassword.getText())
                        .name(In_userName.getText())
                        .phone(In_userPhoneNumber.getText())
                        .gold(Integer.valueOf(In_userGold.getText().replace(",", "")))
                        .build();
                if (FileFacade.getFacade().updateUser(In_userID.getText(), user)) {
                    JOptionPane.showMessageDialog(null, "유저 수정이 완료 되었습니다");
                    FileFacade.getFacade().saveUsers();
                    refreshUserTable();
                } else {
                    JOptionPane.showMessageDialog(null, "존재하지 않는 ID입니다");
                }
            }
        });
        Btt_updateUser.setFont(new Font("굴림", Font.PLAIN, 15));
        Btt_updateUser.setBounds(67, 406, 258, 43);
        userManageContent.add(Btt_updateUser);

        JButton Btt_deleteUser = new JButton("유저 삭제");
        Btt_deleteUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (In_userID.getText().isEmpty())
                    JOptionPane.showMessageDialog(null, "아이디를 채워주세요");
                else {
                    if (FileFacade.getFacade().deleteUser(In_userID.getText())) {
                        JOptionPane.showMessageDialog(null, "유저 삭제가 완료 되었습니다");
                        FileFacade.getFacade().saveUsers();
                        refreshUserTable();
                    } else
                        JOptionPane.showMessageDialog(null, "존재하지 않는 ID입니다");
                }
            }
        });
        Btt_deleteUser.setFont(new Font("굴림", Font.PLAIN, 15));
        Btt_deleteUser.setBounds(67, 473, 258, 43);
        userManageContent.add(Btt_deleteUser);

        JPanel manu = new JPanel();
        manu.setBounds(172, 10, 902, 78);
        contentPane.add(manu);
        manu.setLayout(new GridLayout(1, 0, 0, 0));

        JButton Btt_goAution = new JButton("경매장 관리");
        Btt_goAution.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contents, "AuctionManagePage");
            }
        });
        Btt_goAution.setFont(new Font("굴림", Font.PLAIN, 20));
        manu.add(Btt_goAution);

        JButton Btt_goItem = new JButton("아이템 관리");
        Btt_goItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contents, "ItemManagePage");
                refreshItemTable();
            }
        });
        Btt_goItem.setFont(new Font("굴림", Font.PLAIN, 20));
        manu.add(Btt_goItem);

        JButton Btt_goUser = new JButton("유저 관리");
        Btt_goUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contents, "UserManagePage");
                refreshUserTable();
            }
        });
        Btt_goUser.setFont(new Font("굴림", Font.PLAIN, 20));
        manu.add(Btt_goUser);

        JButton btnNewButton_3 = new JButton("New button");
        btnNewButton_3.setFont(new Font("굴림", Font.PLAIN, 20));
        manu.add(btnNewButton_3);

    }

    public void refreshItemTable() {
        itemTableModel.setRowCount(0);

        for (Item item : FileFacade.getFacade().getItemList()) {
            Object[] rowData = item.getData();
            itemTableModel.addRow(rowData);
        }

        S_itemList.setViewportView(T_ItemList);
    }

    public void refreshUserTable() {
        userTableModel.setRowCount(0);

        for (User user : FileFacade.getFacade().getUsersList()) {
            Object[] rowData = user.getData();
            userTableModel.addRow(rowData);
        }

        S_userList.setViewportView(T_userList);
    }
}