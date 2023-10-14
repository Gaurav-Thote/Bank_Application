package com.practice.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Update_Detail extends JFrame {

	private JPanel contentPane;
	private JTextField tfUpFName;
	private JTextField tfUpMName;
	private JTextField tfUpLName;
	private JTextField tfUpAddress;
	private JTextField tfUpUsername;
	private JPasswordField tfUpPass;
	Connection con;
	PreparedStatement pst;
	 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Update_Detail frame = new Update_Detail();
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
	public Update_Detail() {
		setTitle("Bank Application\r\n");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 611, 601);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnUpdate = new JButton("");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 connection();
				 updateData();
				 	
			}
		});
		btnUpdate.setIcon(new ImageIcon(Update_Detail.class.getResource("/img/Update.png")));
		btnUpdate.setBounds(141, 422, 109, 48);
		contentPane.add(btnUpdate);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFirstName.setBounds(83, 123, 100, 25);
		contentPane.add(lblFirstName);
		
		JLabel lblMiddleName = new JLabel("Middle Name");
		lblMiddleName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMiddleName.setBounds(232, 123, 116, 25);
		contentPane.add(lblMiddleName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLastName.setBounds(385, 124, 100, 23);
		contentPane.add(lblLastName);
		
		tfUpFName = new JTextField();
		tfUpFName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tfUpFName.setColumns(10);
		tfUpFName.setBounds(83, 147, 126, 30);
		contentPane.add(tfUpFName);
		tfUpFName.setText(LoginPage.fName);
		
		tfUpMName = new JTextField();
		tfUpMName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tfUpMName.setColumns(10);
		tfUpMName.setBounds(232, 147, 126, 30);
		contentPane.add(tfUpMName);
		tfUpMName.setText(LoginPage.mName);
		
		tfUpLName = new JTextField();
		tfUpLName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tfUpLName.setColumns(10);
		tfUpLName.setBounds(385, 147, 126, 30);
		contentPane.add(tfUpLName);
		tfUpLName.setText(LoginPage.lName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAddress.setBounds(83, 220, 80, 25);
		contentPane.add(lblAddress);
		
		tfUpAddress = new JTextField();
		tfUpAddress.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tfUpAddress.setColumns(10);
		tfUpAddress.setBounds(83, 245, 428, 30);
		contentPane.add(tfUpAddress);
		tfUpAddress.setText(LoginPage.add);
		
		tfUpUsername = new JTextField();
		tfUpUsername.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tfUpUsername.setColumns(10);
		tfUpUsername.setBounds(83, 345, 178, 30);
		contentPane.add(tfUpUsername);
		tfUpUsername.setText(LoginPage.u);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setToolTipText("");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsername.setBounds(83, 322, 100, 25);
		contentPane.add(lblUsername);
		
		tfUpPass = new JPasswordField();
		tfUpPass.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tfUpPass.setBounds(333, 345, 178, 30);
		contentPane.add(tfUpPass);
		tfUpPass.setText(LoginPage.p);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(333, 323, 100, 23);
		contentPane.add(lblPassword);
		
		JLabel lblUpDetail = new JLabel("Update Your Details");
		lblUpDetail.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpDetail.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblUpDetail.setBounds(-13, 46, 608, 31);
		contentPane.add(lblUpDetail);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				BankApplication ba = new BankApplication();
				ba.setVisible(true);
				
			}
		});
		btnNewButton.setIcon(new ImageIcon(Update_Detail.class.getResource("/img/cancel.png")));
		btnNewButton.setBounds(340, 422, 109, 48);
		contentPane.add(btnNewButton);
		
		JLabel png = new JLabel("");
		png.setIcon(new ImageIcon(Update_Detail.class.getResource("/img/square1.png")));
		png.setBounds(-3, 0, 598, 562);
		contentPane.add(png);	
		
	}

	@SuppressWarnings("deprecation")
	protected void updateData() {
		String fName,mName,lName,address,userName,password;
		int id = LoginPage.id;
		
		// Getting Data from TextField
		fName = tfUpFName.getText().toString().trim();
		mName = tfUpMName.getText().toString().trim();
		lName = tfUpLName.getText().toString().trim();
		address = tfUpAddress.getText().toString().trim();
		userName = tfUpUsername.getText().toString().trim();
		password = tfUpPass.getText().toString().trim();
		
		//Validations
		if (fName.isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "First Name Required");
			tfUpFName.requestFocus();
			return;
		}
		if (mName.isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Middle Name Required");
			tfUpMName.requestFocus();
			return;
		}
		if (lName.isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Last Name Required");
			tfUpLName.requestFocus();
			return;
		}
		if (address.isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Address Required");
			tfUpAddress.requestFocus();
			return;
		}
		if (userName.isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "User Name Required");
			tfUpUsername.requestFocus();
			return;
		}
		if (password.isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Password Required");
			tfUpPass.requestFocus();
			return;
		}
		if (password.length() < 8) {
			JOptionPane.showMessageDialog(contentPane, "Password Must be Atleast 8 Character");
			tfUpPass.requestFocus();
			return;
		}
		
		try {
			pst = con.prepareStatement("update signup set first_Name=?,middle_Name=?,last_Name=?,address=?,username=?,password=? where id = ?");
			pst.setString(1, fName);
			pst.setString(2, mName);
			pst.setString(3, lName);
			pst.setString(4, address);
			pst.setString(5, userName);
			pst.setString(6, password);
			pst.setInt(7, id);
			
			pst.executeUpdate();
			JOptionPane.showMessageDialog(contentPane, "Data Updated Successfully");
			dispose();
			LoginPage lp = new LoginPage();
			lp.setVisible(true);
		}catch(Exception ae) {
			JOptionPane.showMessageDialog(null, "Data Update in Database faild");
			
		}
		
	}

	protected void connection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/account","root","");
			
//			JOptionPane.showMessageDialog(contentPane, "DataBase Connected");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
