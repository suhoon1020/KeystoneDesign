package SwingManager;

import DataManager.FileFacade;
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

public class SwingAdmin extends JFrame {
    private String[] ItemTypes = {"Equipment", "Material", "Potion", "Weapon"};
    private String[] ItemGrades = {"Common", "Uncommon", "Eqic", "Legendary"};
    String[] userTableHeader = {"ID", "PW", "NAME", "PHONE"};
    DefaultTableModel userTableModel = new DefaultTableModel(userTableHeader, 0);

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField In_itemName;
    private JTextField In_itemgrade;
    private JTextField In_ItemPrice;
    private JTextField In_ItemOp1;
    private JTextField In_userID;
    private JTextField In_userPassword;
    private JTextField In_userName;
    private JTextField In_userPhoneNumber;
    private JTextField In_ItemOp2;
    private JTable T_ItemList;
    private JTable T_userList;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SwingAdmin frame = new SwingAdmin();
                    frame.setVisible(true);
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

        JPanel panel = new JPanel();
        contents.add(panel, "AuctionManagePage");

        /*
         * ItemManage Page
         */

        JPanel itemPage = new JPanel();
        contents.add(itemPage, "ItemManagePage");
        itemPage.setLayout(null);

        JPanel itemList = new JPanel();
        itemList.setBounds(12, 10, 641, 526);
        itemList.setLayout(new GridLayout(0, 1, 0, 0));
        itemPage.add(itemList);

        T_ItemList = new JTable();
        itemList.add(T_ItemList);

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

        JLabel L_itemPrice = new JLabel("아이템 가격 :");
        L_itemPrice.setHorizontalAlignment(SwingConstants.CENTER);
        L_itemPrice.setFont(new Font("굴림", Font.PLAIN, 15));
        itemLab.add(L_itemPrice);

        JLabel L_ItemOp1 = new JLabel("옵션 1 :");
        L_ItemOp1.setHorizontalAlignment(SwingConstants.CENTER);
        L_ItemOp1.setFont(new Font("굴림", Font.PLAIN, 15));
        itemLab.add(L_ItemOp1);

        JLabel L_ItemOp2 = new JLabel("옵션 2 :");
        L_ItemOp2.setHorizontalAlignment(SwingConstants.CENTER);
        L_ItemOp2.setFont(new Font("굴림", Font.PLAIN, 15));
        itemLab.add(L_ItemOp2);

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

        In_itemgrade = new JTextField();
        itemIn.add(In_itemgrade);
        In_itemgrade.setColumns(10);

        In_ItemPrice = new JTextField();
        itemIn.add(In_ItemPrice);
        In_ItemPrice.setColumns(10);

        In_ItemOp1 = new JTextField();
        itemIn.add(In_ItemOp1);
        In_ItemOp1.setColumns(10);

        In_ItemOp2 = new JTextField();
        In_ItemOp2.setColumns(10);
        itemIn.add(In_ItemOp2);

        JButton Btt_createItem = new JButton("아이템 생성");
        Btt_createItem.setFont(new Font("굴림", Font.PLAIN, 15));
        Btt_createItem.setBounds(67, 340, 258, 43);
        itemManageContent.add(Btt_createItem);

        JButton Btt_updateItem = new JButton("아이템 수정");
        Btt_updateItem.setFont(new Font("굴림", Font.PLAIN, 15));
        Btt_updateItem.setBounds(67, 406, 258, 43);
        itemManageContent.add(Btt_updateItem);

        JButton Btt_deleteItem = new JButton("아이템 삭제");
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

        JScrollPane userList = new JScrollPane();
        userList.setBorder(new LineBorder(new Color(0, 0, 0)));
        userList.setBounds(12, 10, 641, 526);


        JTable T_userList = new JTable(userTableModel); // 직접 모델을 설정하여 JTable 생성


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

        T_userList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && T_userList.getSelectedRow() != -1) {
                    // 선택된 행의 데이터 출력
                    int selectedRow = T_userList.getSelectedRow();
                    int selectedColumn = T_userList.getSelectedColumn();

                    In_userID.setText(T_userList.getValueAt(selectedRow, 0).toString());
                    In_userPassword.setText(T_userList.getValueAt(selectedRow, 1).toString());
                    In_userName.setText(T_userList.getValueAt(selectedRow, 2).toString());
                    In_userPhoneNumber.setText(T_userList.getValueAt(selectedRow, 3).toString());
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
                        .build();
                if (FileFacade.getFacade().putUser(user)) {
                    JOptionPane.showMessageDialog(null, "유저 생성이 완료 되었습니다");
                    FileFacade.getFacade().saveUsers();
                    refreshUserTable(userTableModel);
                    // JScrollPane에 JTable 설정
                    userList.setViewportView(T_userList);
                    // 최종적으로 JScrollPane을 담고 있는 컨테이너에 추가
                    userPage.add(userList);
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
            //todo 유저 수정 로직
            if(In_userID.getText().isEmpty() || In_userPassword.getText().isEmpty() || In_userName.getText().isEmpty() || In_userPhoneNumber.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"올바른 입력이 아닙니다");
            }else{
                User user = new User.UserBuilder()
                        .ID(In_userID.getText())
                        .PW(In_userPassword.getText())
                        .name(In_userName.getText())
                        .phone(In_userPhoneNumber.getText())
                        .build();
                if(FileFacade.getFacade().isExistingUser(In_userID.getText())) {
                    FileFacade.getFacade().updateUser(In_userID.getText(), user);
                    FileFacade.getFacade().saveUsers();
                    refreshUserTable(userTableModel);
                    userList.setViewportView(T_userList);
                    userPage.add(userList);
                    JOptionPane.showMessageDialog(null,In_userID.getText() + "님의 정보가 수정되었습니다");
                }else {
                    JOptionPane.showMessageDialog(null,"존재하지 않는 유저입니다");
                }
            }
            }
        });
        Btt_updateUser.setFont(new Font("굴림", Font.PLAIN, 15));
        Btt_updateUser.setBounds(67, 406, 258, 43);
        userManageContent.add(Btt_updateUser);

        JButton Btt_deleteUser = new JButton("유저 삭제");
        Btt_deleteUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (In_userID.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "올바른 입력이 아닙니다");
                } else {
                    if (FileFacade.getFacade().isExistingUser(In_userID.getText())) {
                        FileFacade.getFacade().deleteUser(In_userID.getText());
                        FileFacade.getFacade().saveUsers();
                        refreshUserTable(userTableModel);
                        // JScrollPane에 JTable 설정
                        userList.setViewportView(T_userList);
                        // 최종적으로 JScrollPane을 담고 있는 컨테이너에 추가
                        userPage.add(userList);
                    } else {
                        JOptionPane.showMessageDialog(null, "존재하지 않는 ID입니다");
                    }
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
            }
        });
        Btt_goItem.setFont(new Font("굴림", Font.PLAIN, 20));
        manu.add(Btt_goItem);

        JButton Btt_goUser = new JButton("유저 관리");
        Btt_goUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contents, "UserManagePage");
                refreshUserTable(userTableModel);
                // JScrollPane에 JTable 설정
                userList.setViewportView(T_userList);
                // 최종적으로 JScrollPane을 담고 있는 컨테이너에 추가
                userPage.add(userList);
            }
        });
        Btt_goUser.setFont(new Font("굴림", Font.PLAIN, 20));
        manu.add(Btt_goUser);

        JButton btnNewButton_3 = new JButton("New button");
        btnNewButton_3.setFont(new Font("굴림", Font.PLAIN, 20));
        manu.add(btnNewButton_3);

    }

    public void refreshUserTable(DefaultTableModel dtm) {
        dtm.setRowCount(0);
        for (User user : FileFacade.getFacade().getUsersList()) {
            Object[] rowData = {user.getUserID(), user.getUserPW(), user.getUserName(), user.getUserPhoneNum()};
            dtm.addRow(rowData);
        }

    }

}