package com.practice.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Color;

@SuppressWarnings("serial")
public class LoginPage extends JFrame {

	private JPanel contentPane;
	private JTextField tfUserName;
	private JPasswordField tfPassword;
	private JButton btnLogin;
	private JLabel lblSignUp;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	static String u, p, fName, mName, lName, add, mobNumber;
	static int bal, id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage login = new LoginPage();
					login.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginPage() {
		setTitle("Customer Bank Application");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(650, 200, 618, 607);
		contentPane = new JPanel();
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(172, 175, 106, 23);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblUsername);
		
		tfUserName = new JTextField();
		tfUserName.setBounds(172, 198, 273, 45);
		tfUserName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(tfUserName);
		tfUserName.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(172, 262, 91, 25);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblPassword);
		
		tfPassword = new JPasswordField();
		tfPassword.setBounds(172, 287, 273, 45);
		tfPassword.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(tfPassword);
		
		btnLogin = new JButton("");
		btnLogin.setBackground(new Color(64, 0, 64));
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				String userName,password;
				
				userName = tfUserName.getText().toString().trim();
				password = tfPassword.getText().toString().trim();
				
				if (userName.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "User Name Required");
					tfUserName.requestFocus();
					return;
				}
				if (password.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Password Required");
					tfPassword.requestFocus();
					return;
				}
				if (password.length() < 8) {
					JOptionPane.showMessageDialog(contentPane, "Password Must be Atleast 8 Character");
					tfPassword.requestFocus();
					return;
				}
				
				try {
					connection();
					
					while (rs.next()) {
						fName = rs.getString(2);
						mName = rs.getString(3);
						lName = rs.getString(4);
						add = rs.getString(5);
						bal = rs.getInt(8);
						u = rs.getString(6);
						p = rs.getString(7);
						id = rs.getInt(1);
						mobNumber = rs.getString(9);
						
						if (userName.equals(u) && password.equals(p)) {	
//							JOptionPane.showMessageDialog(contentPane, mobileNumber);
							BankApplication bankApplication =  new BankApplication();
							dispose();
							bankApplication.setVisible(true);
							
							break;
						}
						
					}
					if (!userName.equals(u)) {
						JOptionPane.showMessageDialog(contentPane, "UserName or Password is incorrect");						
					}
				}catch(Exception ae) {
					ae.printStackTrace();
				}
				
				
			}
		});
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setBounds(172, 364, 116, 45);
		btnLogin.setIcon(new ImageIcon(LoginPage.class.getResource("/img/login.png")));
		contentPane.add(btnLogin);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblLogin.setBounds(172, 96, 106, 53);
		contentPane.add(lblLogin);
		
		JLabel lblMessage = new JLabel("Don't have an Account?");
		lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMessage.setBounds(172, 429, 196, 31);
		contentPane.add(lblMessage);
		
		lblSignUp = new JLabel("SignUp");
		lblSignUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SignUpPage signUpPage =  new SignUpPage();
				dispose();
				signUpPage.setVisible(true);
				
			}
		});
		lblSignUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblSignUp.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSignUp.setBounds(363, 429, 68, 31);
		contentPane.add(lblSignUp);
		
		JLabel png = new JLabel("");
		png.setIcon(new ImageIcon(LoginPage.class.getResource("/img/square1.png")));
		png.setBounds(0, 0, 601, 568);
		contentPane.add(png);
	}
	
	protected void connection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/account","root","");
			pst = con.prepareStatement("select * from signup");
			rs = pst.executeQuery();
			//JOptionPane.showMessageDialog(contentPane, "DataBase Connected");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
