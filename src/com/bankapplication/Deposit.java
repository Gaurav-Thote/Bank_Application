package com.bankapplication;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Deposit extends JFrame {

	private JPanel contentPane;
	private JTextField tfDeposit;
	Connection con;
	PreparedStatement pst;
	int newBalance, bals;
	ResultSet rs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Deposit frame = new Deposit();
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
	public Deposit() {
		setTitle("Customer Bank Application");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 415, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDeposit = new JLabel("Deposit");
		lblDeposit.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblDeposit.setBounds(148, 39, 102, 31);
		contentPane.add(lblDeposit);

		tfDeposit = new JTextField();
		tfDeposit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfDeposit.setColumns(10);
		tfDeposit.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Deposit Amount", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tfDeposit.setBounds(99, 129, 195, 54);
		contentPane.add(tfDeposit);

		JButton btnDeposit = new JButton("");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int deposit;
				int ids = LoginPage.id;
				deposit = Integer.parseInt(tfDeposit.getText());
				newBalance = bal() + deposit;
				// JOptionPane.showMessageDialog(contentPane, newBalance);
				if (deposit < 0) {
					JOptionPane.showMessageDialog(contentPane, "Add amount more than 1");
					tfDeposit.requestFocus();
					return;
				}

				try {

					Class.forName("com.mysql.cj.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/account", "root", "");
					pst = con.prepareStatement("update signup set balance=? where id = ?");
					pst.setLong(1, newBalance);
					pst.setInt(2, ids);
					pst.executeUpdate();

					JOptionPane.showMessageDialog(contentPane, "Deposit successfull");

				} catch (SQLException e1) {

					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {

					e1.printStackTrace();
				}
				dispose();
//					BankApplication ba = new BankApplication(); 
//					ba.refresh();
			}

		});
		btnDeposit.setIcon(new ImageIcon(Deposit.class.getResource("/img/DEPOSIT+button.png")));
		btnDeposit.setBounds(124, 232, 126, 39);
		contentPane.add(btnDeposit);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Deposit.class.getResource("/img/square1 (2).png")));
		lblNewLabel.setBounds(0, 0, 399, 387);
		contentPane.add(lblNewLabel);
	}

	private int bal() {
		try {
			connection();
			String u = LoginPage.u, p = LoginPage.p, userName = LoginPage.u, password = LoginPage.p;

			while (rs.next()) {

				bals = rs.getInt(8);
				u = rs.getString(6);
				p = rs.getString(7);

				if (userName.equals(u) && password.equals(p)) {
//					JOptionPane.showMessageDialog(contentPane, "Login Successfully");

					break;
				}

			}

		} catch (Exception ae) {
			ae.printStackTrace();
		}
		return bals;
	}

	protected void connection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/account", "root", "");
			pst = con.prepareStatement("select * from signup");
			rs = pst.executeQuery();
			// JOptionPane.showMessageDialog(contentPane, "DataBase Connected");

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}