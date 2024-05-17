package SwingManager;

import DataManager.FileFacade;
import UserOption.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class SwingLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField In_ID;
    private JTextField In_password;
    private JTextField In_register_ID;
    private JTextField In_register_Password;
    private JTextField In_regisiter_Name;
    private JTextField In_register_PhoneNumber;
    private JTextField In_findID_Name;
    private JTextField In_findID_PhoneNumber;
    private JTextField In_findPassword_ID;
    private JTextField In_findPassword_Name;
    private JTextField In_findPassword_PhoneNumber;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SwingLogin frame = new SwingLogin();
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
    public SwingLogin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 750);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        CardLayout cardLayout = new CardLayout(0, 0);
        contentPane.setLayout(cardLayout);

        /*
         * 로그인 페이지
         *
         * */

        JPanel loginPage = new JPanel();
        contentPane.add(loginPage, "LoginPage");
        loginPage.setLayout(null);

        JLabel L_loginTitle = new JLabel("거래소 시스템");
        L_loginTitle.setFont(new Font("굴림", Font.BOLD, 35));
        L_loginTitle.setBounds(170, 30, 250, 45);
        loginPage.add(L_loginTitle);

        In_ID = new JTextField();
        In_ID.setBounds(100, 151, 247, 41);
        loginPage.add(In_ID);
        In_ID.setColumns(10);

        In_password = new JTextField();
        In_password.setColumns(10);
        In_password.setBounds(100, 202, 247, 41);
        loginPage.add(In_password);

        JButton Btt_login = new JButton("로그인");
        Btt_login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                List<User> users = FileFacade.getFacade().getUsersList();

                for (User user : users) {
                    if (user.getUserID().equals(In_ID.getText()) && user.getUserPW().equals(In_password.getText())) {
                        JOptionPane.showMessageDialog(null, "로그인 되었습니다.");
                        return;
                    }
                }

                JOptionPane.showMessageDialog(null, "로그인에 실패하였습니다");
            }
        });

        Btt_login.setFont(new Font("굴림", Font.PLAIN, 20));
        Btt_login.setBounds(371, 151, 107, 92);
        loginPage.add(Btt_login);


        JButton Btt_findID = new JButton("아이디 찾기");
        Btt_findID.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(getContentPane(), "FindIDPage");
            }
        });
        Btt_findID.setFont(new Font("굴림", Font.PLAIN, 20));
        Btt_findID.setBounds(100, 310, 165, 54);
        loginPage.add(Btt_findID);

        JButton Btt_findPassword = new JButton("비밀번호 찾기");
        Btt_findPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(getContentPane(), "FindPasswordPage");
            }
        });
        Btt_findPassword.setFont(new Font("굴림", Font.PLAIN, 20));
        Btt_findPassword.setBounds(303, 310, 165, 54);
        loginPage.add(Btt_findPassword);


        JLabel L_passwordDis = new JLabel("거래소를 처음 이용하시나요?");
        L_passwordDis.setForeground(new Color(0, 128, 255));
        L_passwordDis.setFont(new Font("굴림", Font.PLAIN, 18));
        L_passwordDis.setBounds(154, 545, 268, 22);
        loginPage.add(L_passwordDis);

        JButton Btt_register = new JButton("회원가입");
        Btt_register.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(getContentPane(), "RegisterPage");
            }
        });
        Btt_register.setFont(new Font("굴림", Font.PLAIN, 20));
        Btt_register.setBounds(197, 577, 168, 41);
        loginPage.add(Btt_register);

        /*
         * 회원가입 페이지
         *
         * */

        JPanel registerPage = new JPanel();
        contentPane.add(registerPage, "RegisterPage");
        registerPage.setLayout(null);

        JLabel L_registerTitle = new JLabel("회원가입");
        L_registerTitle.setFont(new Font("굴림", Font.BOLD, 35));
        L_registerTitle.setBounds(200, 30, 150, 45);
        registerPage.add(L_registerTitle);

        In_register_ID = new JTextField();
        In_register_ID.setColumns(10);
        In_register_ID.setBounds(223, 140, 247, 41);
        registerPage.add(In_register_ID);

        In_register_Password = new JTextField();
        In_register_Password.setColumns(10);
        In_register_Password.setBounds(223, 215, 247, 41);
        registerPage.add(In_register_Password);

        In_regisiter_Name = new JTextField();
        In_regisiter_Name.setColumns(10);
        In_regisiter_Name.setBounds(223, 293, 247, 41);
        registerPage.add(In_regisiter_Name);

        In_register_PhoneNumber = new JTextField();
        In_register_PhoneNumber.setColumns(10);
        In_register_PhoneNumber.setBounds(223, 368, 247, 41);
        registerPage.add(In_register_PhoneNumber);

        JButton Btt_tryRegister = new JButton("회원가입");
        Btt_tryRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO : 회원가입 검증 후 팝업창 띄우기

                if (In_register_ID.getText().isEmpty() || In_register_Password.getText().isEmpty() || In_regisiter_Name.getText().isEmpty() || In_register_PhoneNumber.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "모두 입력하여 주십시오");
                } else {
                    User user = new User.UserBuilder()
                            .ID(In_register_ID.getText())
                            .PW(In_register_Password.getText())
                            .name(In_regisiter_Name.getText())
                            .phone(In_register_PhoneNumber.getText())
                            .build();

                    if (FileFacade.getFacade().putUser(user)) {
                        JOptionPane.showMessageDialog(null, "회원가입이 완료 되었습니다");
                        FileFacade.getFacade().saveUsers();
                        cardLayout.show(getContentPane(), "LoginPage");
                    } else {
                        JOptionPane.showMessageDialog(null, "중복된 ID가 있습니다");
                    }

                }

            }


        });
        Btt_tryRegister.setFont(new Font("굴림", Font.PLAIN, 35));
        Btt_tryRegister.setBounds(150, 550, 250, 70);
        registerPage.add(Btt_tryRegister);

        JLabel L_register_ID = new JLabel("아이디 :");
        L_register_ID.setFont(new Font("굴림", Font.PLAIN, 25));
        L_register_ID.setBounds(71, 140, 140, 41);
        registerPage.add(L_register_ID);

        JLabel L_register_Password = new JLabel("비밀번호 :");
        L_register_Password.setFont(new Font("굴림", Font.PLAIN, 25));
        L_register_Password.setBounds(71, 215, 140, 41);
        registerPage.add(L_register_Password);

        JLabel L_register_Name = new JLabel("이름 :");
        L_register_Name.setFont(new Font("굴림", Font.PLAIN, 25));
        L_register_Name.setBounds(71, 293, 140, 41);
        registerPage.add(L_register_Name);

        JLabel L_register_PhoneNumber = new JLabel("전화번호 :");
        L_register_PhoneNumber.setFont(new Font("굴림", Font.PLAIN, 25));
        L_register_PhoneNumber.setBounds(71, 368, 140, 41);
        registerPage.add(L_register_PhoneNumber);


        /*
         * 아이디 찾기 페이지
         *
         * */

        JPanel findIDPage = new JPanel();
        contentPane.add(findIDPage, "FindIDPage");
        findIDPage.setLayout(null);

        JLabel L_findIDTitle = new JLabel("아이디 찾기");
        L_findIDTitle.setFont(new Font("굴림", Font.BOLD, 30));
        L_findIDTitle.setBounds(200, 30, 165, 45);
        findIDPage.add(L_findIDTitle);

        In_findID_Name = new JTextField();
        In_findID_Name.setColumns(10);
        In_findID_Name.setBounds(223, 140, 247, 41);
        findIDPage.add(In_findID_Name);

        In_findID_PhoneNumber = new JTextField();
        In_findID_PhoneNumber.setColumns(10);
        In_findID_PhoneNumber.setBounds(223, 215, 247, 41);
        findIDPage.add(In_findID_PhoneNumber);

        JLabel L_findID_Name = new JLabel("이름 :");
        L_findID_Name.setFont(new Font("굴림", Font.PLAIN, 25));
        L_findID_Name.setBounds(71, 140, 140, 41);
        findIDPage.add(L_findID_Name);

        JLabel L_findID_PhoneNum = new JLabel("전화번호 :");
        L_findID_PhoneNum.setFont(new Font("굴림", Font.PLAIN, 25));
        L_findID_PhoneNum.setBounds(71, 215, 140, 41);
        ;
        findIDPage.add(L_findID_PhoneNum);

        JButton Btt_tryFindID = new JButton("아이디 찾기");
        Btt_tryFindID.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO : 아이디 찾기 팝업창 띄우기
                List<User> users = FileFacade.getFacade().getUsersList();

                for (User user : users) {
                    if (user.getUserName().equals(L_findID_PhoneNum.getText()) && user.getUserPhoneNum().equals(L_findID_PhoneNum.getText())) {
                        String ID = user.getUserID();
                        JOptionPane.showMessageDialog(null, user.getUserName() + "님의 ID는 " + ID + "입니다");
                        cardLayout.show(getContentPane(), "LoginPage");
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "회원님의 정보를 찾을 수 없습니다");
            }
        });
        Btt_tryFindID.setFont(new Font("굴림", Font.PLAIN, 35));
        Btt_tryFindID.setBounds(150, 550, 250, 70);
        findIDPage.add(Btt_tryFindID);

        JPanel findPasswordPage = new JPanel();
        findPasswordPage.setLayout(null);
        contentPane.add(findPasswordPage, "FindPasswordPage");

        JLabel L_findPasswordTitle = new JLabel("비밀번호 찾기");
        L_findPasswordTitle.setFont(new Font("굴림", Font.BOLD, 30));
        L_findPasswordTitle.setBounds(182, 30, 200, 45);
        findPasswordPage.add(L_findPasswordTitle);

        In_findPassword_ID = new JTextField();
        In_findPassword_ID.setColumns(10);
        In_findPassword_ID.setBounds(223, 140, 247, 41);
        findPasswordPage.add(In_findPassword_ID);

        In_findPassword_Name = new JTextField();
        In_findPassword_Name.setColumns(10);
        In_findPassword_Name.setBounds(223, 215, 247, 41);
        findPasswordPage.add(In_findPassword_Name);

        In_findPassword_PhoneNumber = new JTextField();
        In_findPassword_PhoneNumber.setColumns(10);
        In_findPassword_PhoneNumber.setBounds(223, 295, 247, 41);
        findPasswordPage.add(In_findPassword_PhoneNumber);

        JLabel L_findPassword_ID = new JLabel("아이디 :");
        L_findPassword_ID.setFont(new Font("굴림", Font.PLAIN, 25));
        L_findPassword_ID.setBounds(71, 140, 140, 41);
        findPasswordPage.add(L_findPassword_ID);

        JLabel L_findPassword_Name = new JLabel("이름 :");
        L_findPassword_Name.setFont(new Font("굴림", Font.PLAIN, 25));
        L_findPassword_Name.setBounds(71, 215, 140, 41);
        findPasswordPage.add(L_findPassword_Name);


        JLabel L_findPassword_PhoneNumber = new JLabel("전화번호 :");
        L_findPassword_PhoneNumber.setFont(new Font("굴림", Font.PLAIN, 25));
        L_findPassword_PhoneNumber.setBounds(71, 295, 140, 41);
        findPasswordPage.add(L_findPassword_PhoneNumber);

        JButton Btt_tryFindID_1 = new JButton("비밀번호 찾기");
        Btt_tryFindID_1.setFont(new Font("굴림", Font.PLAIN, 30));
        Btt_tryFindID_1.setBounds(150, 550, 250, 70);
        findPasswordPage.add(Btt_tryFindID_1);


    }
}


