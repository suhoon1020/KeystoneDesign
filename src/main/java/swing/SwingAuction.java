package swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import auction.Auction;
import auctionData.TradeItem;
import managers.FileFacade;
import sort.TradeItemSort;
import sort.TradeItemSortByCount;
import user.inventoryItem.Item;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.CardLayout;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class SwingAuction extends JFrame {
    private String[] tradeItemTypes = {"Equipment", "Material", "Potion", "Weapon"};
    private String[] tradeItemGrades = {"Common", "Uncommon", "Eqic", "Legendary"};
    private String[] tradeItemHeader = {"USER", "TYPE", "NAME", "GRADE", "DESC", "COUNT", "OPTION1", "PRICE"};
    
    private String[] inventoryItemHeader = {"TYPE", "NAME", "GRADE", "DESC", "COUNT", "OPTION1"};
    
    private JPanel contentPane;
    private JTextField ItemSearch;
    private JTextField In_itemSellCount;

    private JTable T_TradeItemList;
    private JTable T_InventoryTable;
    private JScrollPane S_itemList;
    private JScrollPane S_inventory;
    private DefaultTableModel TradeItemTableModel;
    private DefaultTableModel inventoryTableModel;

    private TradeItemSort itemSort = new TradeItemSortByCount();

    private static SwingAuction swingAuction = new SwingAuction();
    private JTextField text_BuyItem;
    private JTextField text_Price;

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
        contents.setBounds(12, 158, 1162, 555);
        contents.setLayout(cardLayout);
        contentPane.add(contents);

        JPanel AuctionPage = new JPanel();
        AuctionPage.setLayout(null);
        contents.add(AuctionPage, "AuctionPage");

        JPanel list = new JPanel();
        list.setBorder(new LineBorder(new Color(0, 0, 0)));
        list.setBounds(278, 78, 872, 467);
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
        filter.setBounds(12, 10, 258, 285);
        AuctionPage.add(filter);
        filter.setLayout(null);

        JPanel filterItemOption = new JPanel();
        filterItemOption.setBounds(12, 89, 234, 186);
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

        JComboBox<String> C_filterItemType = new JComboBox<String>(tradeItemTypes);
        filterCombo.add(C_filterItemType);

        JComboBox<String> C_filterItemGrades = new JComboBox<String>(tradeItemGrades);
        filterCombo.add(C_filterItemGrades);

        JLabel L_filterTitle = new JLabel("필터적용");
        L_filterTitle.setHorizontalAlignment(SwingConstants.CENTER);
        L_filterTitle.setFont(new Font("굴림", Font.PLAIN, 25));
        L_filterTitle.setBounds(34, 21, 189, 37);
        filter.add(L_filterTitle);

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

        JButton btn_Sort_Name = new JButton("이름(오름차순)");
        btn_Sort_Name.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btn_Sort_Name.setBounds(22, 297, 117, 38);
        AuctionPage.add(btn_Sort_Name);

        JButton btn_Reverse_Sort_Name = new JButton("이름(내림차순)");
        btn_Reverse_Sort_Name.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btn_Reverse_Sort_Name.setBounds(149, 297, 117, 38);
        AuctionPage.add(btn_Reverse_Sort_Name);

        JButton btn_Sort_Price = new JButton("가격(오름차순)");
        btn_Sort_Price.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btn_Sort_Price.setBounds(22, 343, 117, 38);
        AuctionPage.add(btn_Sort_Price);

        JButton btn_Reverse_Sort_Price = new JButton("가격(내림차순)");
        btn_Reverse_Sort_Price.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btn_Reverse_Sort_Price.setBounds(149, 343, 117, 38);
        AuctionPage.add(btn_Reverse_Sort_Price);
        
        JButton btn_BuyItem = new JButton("구매");
        btn_BuyItem.setBounds(85, 508, 97, 23);
        AuctionPage.add(btn_BuyItem);
        
        text_BuyItem = new JTextField();
        text_BuyItem.setBounds(50, 465, 178, 23);
        AuctionPage.add(text_BuyItem);
        text_BuyItem.setColumns(10);

        JPanel inventoryPage = new JPanel();
        contents.add(inventoryPage, "InventoryPage");
        inventoryPage.setLayout(null);


        JPanel invItemList = new JPanel();
        invItemList.setBorder(new LineBorder(new Color(0, 0, 0)));
        invItemList.setBounds(12, 10, 816, 535);
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
        sellItemInfos.setBounds(840, 10, 310, 535);
        inventoryPage.add(sellItemInfos);
        sellItemInfos.setLayout(null);

        JPanel itemOption = new JPanel();
        itemOption.setBorder(new LineBorder(new Color(0, 0, 0)));
        itemOption.setBounds(12, 10, 286, 311);
        sellItemInfos.add(itemOption);
        itemOption.setLayout(null);

        JPanel sellButton = new JPanel();
        sellButton.setBounds(12, 397, 286, 128);
        sellItemInfos.add(sellButton);
        sellButton.setLayout(new GridLayout(0, 1, 0, 0));
        
        text_Price = new JTextField();
        sellButton.add(text_Price);
        text_Price.setColumns(10);

        In_itemSellCount = new JTextField();
        sellButton.add(In_itemSellCount);
        In_itemSellCount.setColumns(10);

        JButton Btt_itemSell = new JButton("판매");
        Btt_itemSell.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        sellButton.add(Btt_itemSell);

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
        manu.setBounds(195, 32, 979, 86);
        contentPane.add(manu);
        manu.setLayout(new GridLayout(1, 0, 0, 0));

        JButton Btt_goAuction = new JButton("거래소");
        Btt_goAuction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contents, "AuctionPage");
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
    }


    public void setItemSort(TradeItemSort newSort){
        itemSort = newSort;

        refreshTradeItemTable();
    }

    public void refreshTradeItemTable() {
        TradeItemTableModel.setRowCount(0);

        List<TradeItem> L = itemSort.sort(FileFacade.getFacade().getTradeItemList());

        for(TradeItem i : L){
            Object[] rowData = i.getListData();
            TradeItemTableModel.addRow(rowData);
        }

        S_itemList.setViewportView(T_TradeItemList);
    }

    public void refreshInventoryTable() {
        inventoryTableModel.setRowCount(0);

        List<Item> L = Auction.getAuction().getInventory();
        
        for(Item i : L){
            Object[] rowData = i.getListData();
            inventoryTableModel.addRow(rowData);
        }

        S_inventory.setViewportView(T_InventoryTable);
    }
}