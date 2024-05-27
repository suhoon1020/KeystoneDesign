package swing;


import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import auction.Auction;
import commandManage.users.CreateUserCommand;
import user.User;
import commandManage.Invoker;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;

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

    private static SwingLogin swingLogin = new SwingLogin();

    public static SwingLogin getSwingLogin() {
        return swingLogin;
    }

    Invoker invoker = new Invoker();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SwingLogin.getSwingLogin().setVisible(true);
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
         */

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
                if (Auction.getAuction().login(In_ID.getText(), In_password.getText())) {
                    In_ID.setText("");
                    In_password.setText("");
                    
                    JOptionPane.showMessageDialog(null, "로그인이 완료 되었습니다");

                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "회원정보가 없습니다.");
                }

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
                if (In_register_ID.getText().isEmpty()
                        || In_register_Password.getText().isEmpty()
                        || In_regisiter_Name.getText().isEmpty()
                        || In_register_PhoneNumber.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "정보를 모두 입력하여 주십시오");
                } else {
                    if(Pattern.matches("010-\\d{3,4}-\\d{4}",In_register_PhoneNumber.getText())
                    ||In_register_ID.getText().length() <=5
                    ||In_register_Password.getText().length()<=8) {
                        User user = new User.UserBuilder()
                                .ID(In_register_ID.getText())
                                .password(In_register_Password.getText())
                                .name(In_regisiter_Name.getText())
                                .phoneNumber(In_register_PhoneNumber.getText())
                                .gold(10000)
                                .build();

                        CreateUserCommand command = new CreateUserCommand(user);
                        invoker.setCommand(command);
                        invoker.run();

                        cardLayout.show(getContentPane(), "LoginPage");
                    }
                    else
                        JOptionPane.showMessageDialog(null,"올바른 입력 형식이 아닙니다");
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
                String result = Auction.getAuction().findID(In_findID_Name.getText(), In_findID_PhoneNumber.getText());

                if (!result.isEmpty())
                    JOptionPane.showMessageDialog(null, "회원님의 아이디는 " + result + " 입니다.");
                else
                    JOptionPane.showMessageDialog(null, "회원님의 정보를 찾을 수 없습니다");

                cardLayout.show(getContentPane(), "LoginPage");
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

        JButton Btt_tryFindPW = new JButton("비밀번호 찾기");
        Btt_tryFindPW.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String result = Auction.getAuction().findPassword(In_findPassword_ID.getText(), In_findID_Name.getText(), In_findID_PhoneNumber.getText());

                if (!result.isEmpty())
                    JOptionPane.showMessageDialog(null, "회원님의 비밀번호는 " + result + " 입니다.");
                else
                    JOptionPane.showMessageDialog(null, "회원님의 정보를 찾을 수 없습니다");

                cardLayout.show(getContentPane(), "LoginPage");
            }

        });
        Btt_tryFindPW.setFont(new Font("굴림", Font.PLAIN, 30));
        Btt_tryFindPW.setBounds(150, 550, 250, 70);
        findPasswordPage.add(Btt_tryFindPW);
    }
}