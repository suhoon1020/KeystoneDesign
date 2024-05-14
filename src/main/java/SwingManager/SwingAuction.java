package SwingManager;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class SwingAuction extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SwingAuction frame = new SwingAuction();
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
    public SwingAuction() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 760);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel Items = new JPanel();
        Items.setBounds(12, 158, 1162, 555);
        contentPane.add(Items);
        Items.setLayout(null);

        JPanel list = new JPanel();
        list.setBounds(278, 78, 872, 467);
        Items.add(list);
        list.setLayout(new GridLayout(0, 1, 0, 0));

        JScrollPane scrollPane = new JScrollPane();
        list.add(scrollPane);

        JPanel filter = new JPanel();
        filter.setBounds(12, 10, 252, 535);
        Items.add(filter);

        JPanel search = new JPanel();
        search.setBounds(278, 10, 872, 56);
        Items.add(search);
        search.setLayout(null);

        textField = new JTextField();
        textField.setBounds(0, 0, 760, 56);
        search.add(textField);
        textField.setColumns(10);

        JButton btnNewButton_4 = new JButton("New button");
        btnNewButton_4.setBounds(772, -1, 100, 56);
        search.add(btnNewButton_4);

        JPanel manu = new JPanel();
        manu.setBounds(195, 32, 979, 86);
        contentPane.add(manu);
        manu.setLayout(new GridLayout(1, 0, 0, 0));

        JButton btnNewButton_3 = new JButton("New button");
        manu.add(btnNewButton_3);

        JButton btnNewButton_2 = new JButton("New button");
        manu.add(btnNewButton_2);

        JButton btnNewButton_1 = new JButton("New button");
        manu.add(btnNewButton_1);

        JButton btnNewButton = new JButton("New button");
        manu.add(btnNewButton);
    }
}