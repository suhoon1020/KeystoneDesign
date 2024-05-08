package SwingManage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SwingLogIn extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textID;
	private JTextField textPW;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingLogIn frame = new SwingLogIn();
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
	public SwingLogIn() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 398, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(46, 50, 57, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("PW");
		lblNewLabel_1.setBounds(46, 118, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		textID = new JTextField();
		textID.setBounds(148, 47, 116, 21);
		contentPane.add(textID);
		textID.setColumns(10);
		
		textPW = new JTextField();
		textPW.setBounds(148, 115, 116, 21);
		contentPane.add(textPW);
		textPW.setColumns(10);
		
		JButton LogBut = new JButton("로그인");
		LogBut.setBounds(69, 195, 97, 23);
		contentPane.add(LogBut);
		
		JButton SigBut = new JButton("회원가입");
		SigBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingSignUp signUpFrame = new SwingSignUp(); // 회원가입 창 객체 생성
		        signUpFrame.setVisible(true); // 회원가입 창 표시)
			}
		});
		SigBut.setBounds(203, 195, 97, 23);
		contentPane.add(SigBut);
		
		JButton IDFBut = new JButton("ID 찾기");
		IDFBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingIDFind idfindFrame = new SwingIDFind(); // 아이디찾기 창 객체 생성
				idfindFrame.setVisible(true); // 아이디찾기 창 표시
			}
		});
		IDFBut.setBounds(69, 245, 97, 23);
		contentPane.add(IDFBut);
		
		JButton PWFBut = new JButton("PW 찾기");
		PWFBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingPWFind pwfindFrame = new SwingPWFind(); // 비밀번호찾기 창 객체 생성
				pwfindFrame.setVisible(true); // 비밀번호찾기 창 표시
			}
		});
		PWFBut.setBounds(203, 245, 97, 23);
		contentPane.add(PWFBut);
	}
}
