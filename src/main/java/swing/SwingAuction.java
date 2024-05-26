package swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import auction.Auction;
import auctionData.TradeItem;
import managers.TradeItemFileSystem;
import sort.TradeItemSort;
import sort.TradeItemSortByName;
import sort.TradeItemSortByNameRev;
import sort.TradeItemSortByPrice;
import sort.TradeItemSortByPriceRev;
import user.InventoryItem;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.CardLayout;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class SwingAuction extends JFrame {
    private String[] tradeItemTypes = {"ALL", "Equipment", "Material", "Potion", "Weapon"};
    private String[] tradeItemGrades = {"ALL", "Common", "Uncommon", "Eqic", "Legendary"};
    private String[] tradeItemHeader = {"USER", "TYPE", "NAME", "GRADE", "DESC", "OPTION1", "COUNT", "PRICE"};
    
    private String[] inventoryItemHeader = {"TYPE", "NAME", "GRADE", "DESC", "OPTION1", "COUNT"};
    
    private JPanel contentPane;
    private JTextField ItemSearch;
    private JTextField In_itemSellCount;

    private JComboBox<String> C_filterItemType;
    private JComboBox<String> C_filterItemGrades;

    private JTable T_TradeItemList;
    private JTable T_InventoryTable;
    private JScrollPane S_itemList;
    private JScrollPane S_inventory;
    private DefaultTableModel TradeItemTableModel;
    private DefaultTableModel inventoryTableModel;
    
    private TradeItemSort itemSort = new TradeItemSortByPrice();

    private static SwingAuction swingAuction = new SwingAuction();
    private JTextField In_itemSellPrice;
    private JTextField In_userItemType;
    private JTextField In_userItemDesc;
    private JTextField In_userItemGrade;
    private JTextField In_userItemName;
    private JTextField In_userItemCount;
    private JTextField In_userItemOp1;
    private JTextField In_userGold;

    public static SwingAuction getSwingAuction() {
        return swingAuction;
    }
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SwingAuction.getSwingAuction().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public SwingAuction() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 760);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel contents = new JPanel();
        CardLayout cardLayout = new CardLayout(0, 0);
        contents.setBorder(new LineBorder(new Color(0, 0, 0)));
        contents.setBounds(12, 105, 1162, 608);
        contents.setLayout(cardLayout);
        contentPane.add(contents);

        JPanel AuctionPage = new JPanel();
        AuctionPage.setLayout(null);
        contents.add(AuctionPage, "AuctionPage");

        JPanel list = new JPanel();
        list.setBorder(new LineBorder(new Color(0, 0, 0)));
        list.setBounds(278, 78, 872, 503);
        AuctionPage.add(list);
        list.setLayout(new GridLayout(0, 1, 0, 0));

        TradeItemTableModel = new DefaultTableModel(tradeItemHeader, 0);
        T_TradeItemList = new JTable(TradeItemTableModel);
        T_TradeItemList.getTableHeader().setReorderingAllowed(false);
        T_TradeItemList.getTableHeader().setResizingAllowed(false);
        S_itemList = new JScrollPane();
        list.add(S_itemList);
        refreshTradeItemTable();

        JPanel filter = new JPanel();
        filter.setBorder(new LineBorder(new Color(0, 0, 0)));
        filter.setBounds(12, 10, 258, 498);
        AuctionPage.add(filter);
        filter.setLayout(null);

        JPanel filterItemOption = new JPanel();
        filterItemOption.setBounds(12, 362, 234, 126);
        filter.add(filterItemOption);
        filterItemOption.setLayout(new GridLayout(1, 0, 0, 0));

        JPanel filterLab = new JPanel();
        filterItemOption.add(filterLab);
        filterLab.setLayout(new GridLayout(0, 1, 0, 0));

        JLabel L_filterItemType = new JLabel("아이템 타입");
        L_filterItemType.setHorizontalAlignment(SwingConstants.CENTER);
        L_filterItemType.setFont(new Font("굴림", Font.PLAIN, 15));
        filterLab.add(L_filterItemType);

        JLabel L_filterItemGrade = new JLabel("아이템 등급");
        L_filterItemGrade.setHorizontalAlignment(SwingConstants.CENTER);
        L_filterItemGrade.setFont(new Font("굴림", Font.PLAIN, 15));
        filterLab.add(L_filterItemGrade);

        JPanel filterCombo = new JPanel();
        filterItemOption.add(filterCombo);
        filterCombo.setLayout(new GridLayout(0, 1, 0, 0));

        C_filterItemType = new JComboBox<String>(tradeItemTypes);
        filterCombo.add(C_filterItemType);

        C_filterItemGrades = new JComboBox<String>(tradeItemGrades);
        filterCombo.add(C_filterItemGrades);
        

        C_filterItemType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshFilterTradeItemTable();
            }
        });

        C_filterItemGrades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshFilterTradeItemTable();
            }
        });

        JLabel L_filterTitle = new JLabel("필터적용");
        L_filterTitle.setHorizontalAlignment(SwingConstants.CENTER);
        L_filterTitle.setFont(new Font("굴림", Font.PLAIN, 25));
        L_filterTitle.setBounds(34, 21, 189, 37);
        filter.add(L_filterTitle);
        
        JButton Btt_Sort_Name = new JButton("이름(오름차순)");
        Btt_Sort_Name.setBounds(12, 68, 234, 44);
        filter.add(Btt_Sort_Name);
        
        JButton Btt_Reverse_Sort_Name = new JButton("이름(내림차순)");
        Btt_Reverse_Sort_Name.setBounds(12, 140, 234, 44);
        filter.add(Btt_Reverse_Sort_Name);
        
        JButton Btt_Sort_Price = new JButton("가격(오름차순)");
        Btt_Sort_Price.setBounds(12, 211, 234, 44);
        filter.add(Btt_Sort_Price);
        
        JButton Btt_Reverse_Sort_Price = new JButton("가격(내림차순)");
        Btt_Reverse_Sort_Price.setBounds(12, 278, 234, 44);
        filter.add(Btt_Reverse_Sort_Price);
        
        Btt_Reverse_Sort_Price.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                itemSort = new TradeItemSortByPriceRev();
                refreshFilterTradeItemTable();
            }
        });
        
        Btt_Sort_Price.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                itemSort = new TradeItemSortByPrice();
                refreshFilterTradeItemTable();
            }
        });
        
        Btt_Reverse_Sort_Name.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                itemSort = new TradeItemSortByNameRev();
                refreshFilterTradeItemTable();
            }
        });
        
        Btt_Sort_Name.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                itemSort = new TradeItemSortByName();
                refreshFilterTradeItemTable();
            }
        });

        JPanel search = new JPanel();
        search.setLayout(null);
        search.setBounds(278, 10, 872, 56);
        AuctionPage.add(search);

        ItemSearch = new JTextField();
        ItemSearch.setColumns(10);
        ItemSearch.setBounds(0, 0, 760, 56);
        search.add(ItemSearch);

        JButton B_goSearch = new JButton("검색");
        B_goSearch.setBounds(772, -1, 100, 56);
        search.add(B_goSearch);
        
        JButton Btt_buyItem = new JButton("구매");
        Btt_buyItem.setFont(new Font("굴림", Font.PLAIN, 25));
        Btt_buyItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = T_TradeItemList.getSelectedRow();
                int tradeId = Integer.parseInt(T_TradeItemList.getValueAt(selectedRow, 0).toString());

                if(Auction.getAuction().buyItem(tradeId)){
                    // 구매 성공
                    JOptionPane.showMessageDialog(null, "성공.");
                }
                else{
                    // 구매 실패
                    JOptionPane.showMessageDialog(null, "실패.");
                }

                refreshFilterTradeItemTable();
            }
        });
        Btt_buyItem.setBounds(12, 518, 254, 63);
        AuctionPage.add(Btt_buyItem);

        JPanel inventoryPage = new JPanel();
        contents.add(inventoryPage, "InventoryPage");
        inventoryPage.setLayout(null);

        JPanel invItemList = new JPanel();
        invItemList.setBorder(new LineBorder(new Color(0, 0, 0)));
        invItemList.setBounds(12, 10, 816, 586);
        inventoryPage.add(invItemList);
        invItemList.setLayout(new GridLayout(0, 1, 0, 0));

        inventoryTableModel = new DefaultTableModel(inventoryItemHeader, 0);
        T_InventoryTable = new JTable(inventoryTableModel);
        T_InventoryTable.getTableHeader().setReorderingAllowed(false);
        T_InventoryTable.getTableHeader().setResizingAllowed(false);
        S_inventory = new JScrollPane();
        invItemList.add(S_inventory);
        refreshInventoryTable();

        JPanel sellItemInfos = new JPanel();
        sellItemInfos.setBorder(new LineBorder(new Color(0, 0, 0)));
        sellItemInfos.setBounds(852, 10, 298, 586);
        inventoryPage.add(sellItemInfos);
        sellItemInfos.setLayout(null);

        JPanel itemOption = new JPanel();
        itemOption.setBorder(new LineBorder(new Color(0, 0, 0)));
        itemOption.setBounds(0, 0, 298, 373);
        sellItemInfos.add(itemOption);
        itemOption.setLayout(null);
        
        JPanel inventoryItemIn = new JPanel();
        inventoryItemIn.setBounds(121, 10, 165, 353);
        itemOption.add(inventoryItemIn);
        inventoryItemIn.setLayout(new GridLayout(0, 1, 0, 0));
        
        In_userItemType = new JTextField();
        In_userItemType.setEditable(false);
        In_userItemType.setColumns(10);
        inventoryItemIn.add(In_userItemType);
        
        In_userItemName = new JTextField();
        In_userItemName.setEditable(false);
        In_userItemName.setColumns(10);
        inventoryItemIn.add(In_userItemName);
        
        In_userItemGrade = new JTextField();
        In_userItemGrade.setEditable(false);
        In_userItemGrade.setColumns(10);
        inventoryItemIn.add(In_userItemGrade);
        
        In_userItemDesc = new JTextField();
        In_userItemDesc.setEditable(false);
        In_userItemDesc.setColumns(10);
        inventoryItemIn.add(In_userItemDesc);
        
        In_userItemCount = new JTextField();
        In_userItemCount.setEditable(false);
        In_userItemCount.setColumns(10);
        inventoryItemIn.add(In_userItemCount);
        
        In_userItemOp1 = new JTextField();
        In_userItemOp1.setEditable(false);
        In_userItemOp1.setColumns(10);
        inventoryItemIn.add(In_userItemOp1);
        
        JPanel tradeItemLab = new JPanel();
        tradeItemLab.setBounds(12, 10, 97, 353);
        itemOption.add(tradeItemLab);
        tradeItemLab.setLayout(new GridLayout(0, 1, 0, 0));
        
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

        JPanel itemSell = new JPanel();
        itemSell.setBorder(new LineBorder(new Color(0, 0, 0)));
        itemSell.setBounds(0, 385, 298, 201);
        sellItemInfos.add(itemSell);
        itemSell.setLayout(null);
        
        JPanel sellUtemLab = new JPanel();
        sellUtemLab.setBounds(12, 10, 99, 108);
        itemSell.add(sellUtemLab);
        sellUtemLab.setLayout(new GridLayout(0, 1, 0, 0));
        
        JLabel L_itemSellPrice = new JLabel("아이템 가격 :");
        L_itemSellPrice.setHorizontalAlignment(SwingConstants.CENTER);
        sellUtemLab.add(L_itemSellPrice);
        
        JLabel L_itemSellCount = new JLabel("아이템 수량 :");
        L_itemSellCount.setHorizontalAlignment(SwingConstants.CENTER);
        sellUtemLab.add(L_itemSellCount);
        
        JPanel sellItemIn = new JPanel();
        sellItemIn.setBounds(123, 10, 163, 108);
        itemSell.add(sellItemIn);
        sellItemIn.setLayout(new GridLayout(0, 1, 0, 0));
        
        In_itemSellPrice = new JTextField();
        sellItemIn.add(In_itemSellPrice);
        In_itemSellPrice.setColumns(10);
        
        In_itemSellCount = new JTextField();
        sellItemIn.add(In_itemSellCount);
        In_itemSellCount.setColumns(10);
        
        JButton Btt_itemSell = new JButton("판매");
        Btt_itemSell.setBounds(12, 142, 274, 51);
        itemSell.add(Btt_itemSell);
        Btt_itemSell.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = T_InventoryTable.getSelectedRow();
                String sellItemName = T_InventoryTable.getValueAt(selectedRow, 1).toString();
                int sellItemCount = Integer.parseInt(In_itemSellCount.getText());
                int sellItemPrice = Integer.parseInt(In_itemSellPrice.getText());

                if(Auction.getAuction().sellItem(sellItemName, sellItemCount, sellItemPrice)){
                    // 판매 등록 성공
                    JOptionPane.showMessageDialog(null, "성공.");
                }
                else{
                    // 판매 등록 실패
                    JOptionPane.showMessageDialog(null, "실패.");
                }
                
            }
        });

        T_InventoryTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && T_InventoryTable.getSelectedRow() != -1) {
                    // 선택된 행의 데이터 출력
                    int selectedRow = T_InventoryTable.getSelectedRow();

                    In_userItemType.setText(T_InventoryTable.getValueAt(selectedRow, 0).toString());
                    In_userItemName.setText(T_InventoryTable.getValueAt(selectedRow, 1).toString());
                    In_userItemGrade.setText(T_InventoryTable.getValueAt(selectedRow, 2).toString());
                    In_userItemDesc.setText(T_InventoryTable.getValueAt(selectedRow, 3).toString());
                    In_userItemCount.setText(T_InventoryTable.getValueAt(selectedRow, 4).toString());
                    In_userItemOp1.setText(T_InventoryTable.getValueAt(selectedRow, 5).toString());
                }
            }
        });

        /*
         *      USER INFO PAGE
         */

        JPanel UserInfoPage = new JPanel();
        UserInfoPage.setLayout(null);
        contents.add(UserInfoPage, "UserInfoPage");

        JPanel userInfo = new JPanel();
        userInfo.setBounds(12, 10, 379, 535);
        UserInfoPage.add(userInfo);

        JPanel tradingHistory = new JPanel();
        tradingHistory.setBounds(403, 10, 747, 535);
        UserInfoPage.add(tradingHistory);

        JPanel manu = new JPanel();
        manu.setBounds(285, 21, 889, 63);
        contentPane.add(manu);
        manu.setLayout(new GridLayout(1, 0, 0, 0));

        JButton Btt_goAuction = new JButton("거래소");
        Btt_goAuction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contents, "AuctionPage");
                refreshTradeItemTable();
            }
        });
        Btt_goAuction.setFont(new Font("굴림", Font.PLAIN, 25));
        manu.add(Btt_goAuction);

        JButton Btt_goInventory = new JButton("인벤토리");
        Btt_goInventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contents, "InventoryPage");
                refreshInventoryTable();
            }
        });
        Btt_goInventory.setFont(new Font("굴림", Font.PLAIN, 25));
        manu.add(Btt_goInventory);

        JButton Btt_goUserInfo = new JButton("유저 정보");
        Btt_goUserInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contents, "UserInfoPage");
            }
        });
        Btt_goUserInfo.setFont(new Font("굴림", Font.PLAIN, 25));
        manu.add(Btt_goUserInfo);
        
        JPanel panel = new JPanel();
        panel.setBounds(12, 10, 262, 74);
        contentPane.add(panel);
        panel.setLayout(new GridLayout(1, 0, 0, 0));
        
        JLabel L_userGold = new JLabel("골드 :");
        L_userGold.setHorizontalAlignment(SwingConstants.CENTER);
        L_userGold.setFont(new Font("굴림", Font.PLAIN, 25));
        panel.add(L_userGold);
        
        In_userGold = new JTextField();
        In_userGold.setEditable(false);
        panel.add(In_userGold);
        In_userGold.setColumns(10);
        In_userGold.setText(Integer.toString(Auction.getAuction().getGold()));
    }


    public void setItemSort(TradeItemSort newSort){
        itemSort = newSort;

        refreshTradeItemTable();
    }
    
    public void refreshTradeItemTable() {
        TradeItemTableModel.setRowCount(0);

        List<TradeItem> L = itemSort.sort(TradeItemFileSystem.getTradeItemFileSystem().getTradeItemList());

        for(TradeItem i : L){
            Object[] rowData = i.getListData();
            TradeItemTableModel.addRow(rowData);
        }

        S_itemList.setViewportView(T_TradeItemList);

        if(In_userGold != null)
            In_userGold.setText(Integer.toString(Auction.getAuction().getGold()));
    }

    public void refreshFilterTradeItemTable(){
        TradeItemTableModel.setRowCount(0);

        List<TradeItem> L = itemSort.sort(TradeItemFileSystem.getTradeItemFileSystem().getTradeItemList());

        String filterString1 = C_filterItemGrades.getSelectedItem().toString();
        String filterString2 = C_filterItemType.getSelectedItem().toString();
        String searchString = ItemSearch.getText();

        for(TradeItem i : L){
            if(!filterString1.equals("All"))
                if(i.getGrade().equals(filterString1))
                    continue;

            if(!filterString2.equals("All"))
                if(i.getType().equals(filterString2))
                    continue;

            if(!searchString.isEmpty())
                if(!i.getName().contains(searchString))
                    continue;

            Object[] rowData = i.getListData();
            TradeItemTableModel.addRow(rowData);
        }

        S_itemList.setViewportView(T_TradeItemList);

        if(In_userGold != null)
            In_userGold.setText(Integer.toString(Auction.getAuction().getGold()));
    }


    public void refreshInventoryTable() {
        inventoryTableModel.setRowCount(0);

        List<InventoryItem> L = Auction.getAuction().getInventory();
        
        for(InventoryItem i : L){
            Object[] rowData = i.getListData();
            inventoryTableModel.addRow(rowData);
        }

        S_inventory.setViewportView(T_InventoryTable);

        if(In_userGold != null)
            In_userGold.setText(Integer.toString(Auction.getAuction().getGold()));
    }
}