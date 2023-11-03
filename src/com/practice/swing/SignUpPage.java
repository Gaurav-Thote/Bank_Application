package com.practice.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class SignUpPage extends JFrame {

	private JPanel contentPane;
	private JTextField tfFirstName;
	private JTextField tfMiddleName;
	private JTextField tfLastName;
	private JTextField tfAdddress;
	private JTextField tfUsername;
	private JPasswordField tfPassword;
	private JButton btnSignUp;
	Connection con;
	PreparedStatement pst;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpPage frame = new SignUpPage();
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
	public SignUpPage() {
		setTitle("Customer Bank Application");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(650, 200, 618, 597);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbSignUp = new JLabel("SignUp");
		lbSignUp.setFont(new Font("Tahoma", Font.BOLD, 32));
		lbSignUp.setBounds(93, 56, 126, 48);
		contentPane.add(lbSignUp);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFirstName.setBounds(93, 129, 100, 25);
		contentPane.add(lblFirstName);
		
		tfFirstName = new JTextField();
		tfFirstName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tfFirstName.setBounds(93, 153, 126, 30);
		contentPane.add(tfFirstName);
		tfFirstName.setColumns(10);
		
		JLabel lblMiddleName = new JLabel("Middle Name");
		lblMiddleName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMiddleName.setBounds(242, 129, 116, 25);
		contentPane.add(lblMiddleName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLastName.setBounds(395, 130, 100, 23);
		contentPane.add(lblLastName);
		
		tfMiddleName = new JTextField();
		tfMiddleName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tfMiddleName.setBounds(242, 153, 126, 30);
		contentPane.add(tfMiddleName);
		tfMiddleName.setColumns(10);
		
		tfLastName = new JTextField();
		tfLastName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tfLastName.setBounds(395, 153, 126, 30);
		contentPane.add(tfLastName);
		tfLastName.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAddress.setBounds(94, 231, 80, 25);
		contentPane.add(lblAddress);
		
		tfAdddress = new JTextField();
		tfAdddress.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tfAdddress.setBounds(93, 253, 428, 30);
		contentPane.add(tfAdddress);
		tfAdddress.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsername.setToolTipText("");
		lblUsername.setBounds(93, 328, 100, 25);
		contentPane.add(lblUsername);
		
		tfUsername = new JTextField();
		tfUsername.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tfUsername.setBounds(93, 351, 178, 30);
		contentPane.add(tfUsername);
		tfUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(343, 329, 100, 23);
		contentPane.add(lblPassword);
		
		tfPassword = new JPasswordField();
		tfPassword.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tfPassword.setBounds(343, 351, 178, 30);
		contentPane.add(tfPassword);
		
		btnSignUp = new JButton("");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				connection();
				
				insertData();	
			}
		});
		btnSignUp.setIcon(new ImageIcon(SignUpPage.class.getResource("/img/signup (1).png")));
		btnSignUp.setBounds(242, 404, 116, 41);
		contentPane.add(btnSignUp);
		
		JLabel png = new JLabel("");
		png.setIcon(new ImageIcon(SignUpPage.class.getResource("/img/square1.png")));
		png.setBounds(0, 0, 602, 592);
		contentPane.add(png);
	}

	@SuppressWarnings("deprecation")
	protected void insertData() {
		String fName,mName,lName,address,userName,password;
		long amount;
		// Getting Data from TextField
		fName = tfFirstName.getText().toString().trim();
		mName = tfMiddleName.getText().toString().trim();
		lName = tfLastName.getText().toString().trim();
		address = tfAdddress.getText().toString().trim();
		userName = tfUsername.getText().toString().trim();
		password = tfPassword.getText().toString().trim();
		amount = (int)Math.ceil(Math.random() * 5000);
		//Validations
		if (fName.isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "First Name Required");
			tfFirstName.requestFocus();
			return;
		}
		if (mName.isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Middle Name Required");
			tfMiddleName.requestFocus();
			return;
		}
		if (lName.isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Last Name Required");
			tfLastName.requestFocus();
			return;
		}
		if (address.isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Address Required");
			tfAdddress.requestFocus();
			return;
		}
		if (amount < 1500) {
			amount = 2000 + amount;
		}
		if (userName.isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "User Name Required");
			tfUsername.requestFocus();
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
			pst = con.prepareStatement("insert into signup(first_Name,middle_Name,last_Name,address,username,password,balance) values(?,?,?,?,?,?,?)");
			pst.setString(1, fName);
			pst.setString(2, mName);
			pst.setString(3, lName);
			pst.setString(4, address);
			pst.setString(5, userName);
			pst.setString(6, password);
			pst.setLong(7, amount);
			pst.execute();
			
//			JOptionPane.showMessageDialog(contentPane, "User Registered Successfully");
			
			/*
			 * tfFirstName.setText(""); tfMiddleName.setText(""); tfLastName.setText("");
			 * tfAdddress.setText(""); tfUsername.setText(""); tfPassword.setText("");
			 */
			
			con.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		LoginPage login = new LoginPage();
		dispose();
		login.setVisible(true);
	}

	protected void connection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/account","root","");
			
			//JOptionPane.showMessageDialog(contentPane, "DataBase Connected");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
