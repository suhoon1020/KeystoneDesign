package SwingManage;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DataManager.SaveUserInfo;
import UserOption.User;

public class SwingSignUp extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textID;
    private JTextField textPW;
    private JTextField textCorrectPW;
    private JTextField textName;
    private JTextField textPhoneNum;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SwingSignUp frame = new SwingSignUp();
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
    public SwingSignUp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("ID");
        lblNewLabel.setBounds(32, 10, 57, 15);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("PW");
        lblNewLabel_1.setBounds(32, 50, 57, 15);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("PW 확인");
        lblNewLabel_2.setBounds(32, 90, 57, 15);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("이름");
        lblNewLabel_3.setBounds(32, 130, 57, 15);
        contentPane.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("전화번호");
        lblNewLabel_4.setBounds(32, 170, 57, 15);
        contentPane.add(lblNewLabel_4);

        textID = new JTextField();
        textID.setBounds(120, 7, 116, 21);
        contentPane.add(textID);
        textID.setColumns(10);

        textPW = new JTextField();
        textPW.setBounds(120, 47, 116, 21);
        contentPane.add(textPW);
        textPW.setColumns(10);

        textCorrectPW = new JTextField();
        textCorrectPW.setBounds(120, 87, 116, 21);
        contentPane.add(textCorrectPW);
        textCorrectPW.setColumns(10);

        textName = new JTextField();
        textName.setBounds(120, 127, 116, 21);
        contentPane.add(textName);
        textName.setColumns(10);

        textPhoneNum = new JTextField();
        textPhoneNum.setBounds(120, 167, 116, 21);
        contentPane.add(textPhoneNum);
        textPhoneNum.setColumns(10);

        JButton SigCBut = new JButton("확인");
        SigCBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userID = textID.getText();
                String userPW = textID.getText();
                String userName = textName.getText();
                String userPhoneNum = textPhoneNum.getText();

                User user = new User.UserBuilder()
                        .ID(userID)
                        .PW(userPW)
                        .name(userName)
                        .phone(userPhoneNum)
                        .build();

                //TODO : 파일 저장 시스템
                SaveUserInfo dataOption = new SaveUserInfo();
                dataOption.saveInfo(user.getUserID(),user.getUserPW(),user.getUserName(),user.getUserPhoneNum());
            }
        });
        SigCBut.setBounds(178, 228, 97, 23);
        contentPane.add(SigCBut);
    }

}
