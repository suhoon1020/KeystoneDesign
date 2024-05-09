package SwingManage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SwingLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField In_userID;
	private JTextField In_password;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_1;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;

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
		L_loginTitle.setFont(new Font("궁서", Font.BOLD, 35));
		L_loginTitle.setBounds(170, 30, 250, 45);
		loginPage.add(L_loginTitle);
		
		In_userID = new JTextField();
		In_userID.setBounds(100, 151, 247, 41);
		loginPage.add(In_userID);
		In_userID.setColumns(10);
		
		In_password = new JTextField();
		In_password.setColumns(10);
		In_password.setBounds(100, 202, 247, 41);
		loginPage.add(In_password);
		
		JButton Btt_login = new JButton("로그인");
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
		L_registerTitle.setFont(new Font("궁서", Font.BOLD, 35));
		L_registerTitle.setBounds(200, 30, 150, 45);
		registerPage.add(L_registerTitle);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(223, 140, 247, 41);
		registerPage.add(textField);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(223, 215, 247, 41);
		registerPage.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(223, 293, 247, 41);
		registerPage.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(223, 368, 247, 41);
		registerPage.add(textField_4);
		
		JButton Btt_tryRegister = new JButton("들어오다");
		Btt_tryRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Todo 회원가입 검증 후 팝업창 띄우기

				cardLayout.show(getContentPane(), "LoginPage");
			}
		});
		Btt_tryRegister.setFont(new Font("굴림", Font.PLAIN, 35));
		Btt_tryRegister.setBounds(150, 550, 250, 70);
		registerPage.add(Btt_tryRegister);
		
		
		/*
		 * 아이디 찾기 페이지
		 * 
		 * */
		
		JPanel findIDPage = new JPanel();
		contentPane.add(findIDPage, "FindIDPage");
		findIDPage.setLayout(null);
		
		JLabel L_findIDTitle = new JLabel("아이디 찾기");
		L_findIDTitle.setFont(new Font("궁서", Font.BOLD, 35));
		L_findIDTitle.setBounds(190, 30, 200, 45);
		findIDPage.add(L_findIDTitle);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(200, 122, 247, 41);
		findIDPage.add(textField_1);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(200, 190, 247, 41);
		findIDPage.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(200, 256, 247, 41);
		findIDPage.add(textField_6);
		
		JButton Btt_tryFindID = new JButton("아이디 찾기");
		Btt_tryFindID.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Todo 아이디 찾기 팝업창 띄우기

				cardLayout.show(getContentPane(), "LoginPage");
			}
		});
		Btt_tryFindID.setFont(new Font("굴림", Font.PLAIN, 35));
		Btt_tryFindID.setBounds(150, 550, 250, 70);
		findIDPage.add(Btt_tryFindID);
		
		/*
		 * 비밀번호 찾기 페이지
		 * 
		 * */
		
		
		JPanel FindPasswordPage = new JPanel();
		contentPane.add(FindPasswordPage, "FindPasswordPage");
		FindPasswordPage.setLayout(null);
		
		JLabel L_findPasswordTitle = new JLabel("비밀번호 찾기");
		L_findPasswordTitle.setFont(new Font("궁서", Font.BOLD, 35));
		L_findPasswordTitle.setBounds(170, 30, 230, 45);
		FindPasswordPage.add(L_findPasswordTitle);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(206, 155, 247, 41);
		FindPasswordPage.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(206, 223, 247, 41);
		FindPasswordPage.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(206, 292, 247, 41);
		FindPasswordPage.add(textField_9);
		
		JButton Btt_tryFindPassword = new JButton("비밀번호 찾기");
		Btt_tryFindPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Todo 비밀번호 찾기 팝업창 띄우기

				cardLayout.show(getContentPane(), "LoginPage");
			}
		});
		Btt_tryFindPassword.setFont(new Font("굴림", Font.PLAIN, 32));
		Btt_tryFindPassword.setBounds(150, 550, 250, 70);
		FindPasswordPage.add(Btt_tryFindPassword);
	}
}