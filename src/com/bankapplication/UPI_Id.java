package com.bankapplication;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class UPI_Id extends JFrame {

	private JPanel contentPane;
	private JTextField tfUpiId;
	private JTextField tfUpiAmount;
	private JTextField tfUpiPIn;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	int bals, id2, bals2, attempt = 3;
	String upi, pin, upiName, mobileNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UPI_Id frame = new UPI_Id();
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
	public UPI_Id() {
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

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 0, 0);
		contentPane.add(panel);

		JLabel lblWithdraw = new JLabel("Withdraw");
		lblWithdraw.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblWithdraw.setBounds(140, 39, 102, 31);
		contentPane.add(lblWithdraw);

		tfUpiId = new JTextField();
		tfUpiId.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"UPI ID OR MOBILE NUMBER", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tfUpiId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfUpiId.setBounds(101, 81, 195, 54);
		contentPane.add(tfUpiId);
		tfUpiId.setColumns(10);

		JButton btnWithdraw = new JButton("");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transction();
			}
		});
		btnWithdraw.setIcon(new ImageIcon(UPI_Id.class.getResource("/img/confirm.png")));
		btnWithdraw.setBounds(152, 273, 102, 31);
		contentPane.add(btnWithdraw);

		tfUpiAmount = new JTextField();
		tfUpiAmount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfUpiAmount.setColumns(10);
		tfUpiAmount.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"UPI AMOUNT", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tfUpiAmount.setBounds(101, 146, 195, 54);
		contentPane.add(tfUpiAmount);

		tfUpiPIn = new JPasswordField();
		((JPasswordField) tfUpiPIn).setEchoChar('*');
		tfUpiPIn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfUpiPIn.setColumns(10);
		tfUpiPIn.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "UPI PIN",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tfUpiPIn.setBounds(101, 208, 195, 54);
		contentPane.add(tfUpiPIn);

		JLabel png = new JLabel("");
		png.setIcon(new ImageIcon(Withdraw.class.getResource("/img/square1 (2).png")));
		png.setBounds(0, 0, 399, 387);
		contentPane.add(png);

	}

	protected void transction() {
		int amount, newBalance, pins;

		upi = tfUpiId.getText().trim();
		pin = tfUpiPIn.getText().trim();
		pins = Integer.parseInt(pin);
		amount = Integer.parseInt(tfUpiAmount.getText().trim());
		if (upi.isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "UPI Id or Mobile Number required");
			tfUpiId.requestFocus();
			return;
		}
		if (!(upi.equals(checkUpiId()) || upi.equals(checkNumber()))) {
			JOptionPane.showMessageDialog(contentPane, "UPI Id or Mobile Number is not registered");
			tfUpiId.requestFocus();
			return;
		}
		if (LoginPage.j.equals("na")) {
			JOptionPane.showMessageDialog(contentPane, "Create Your UPI Account");
			dispose();
			GenerateUpiId gui = new GenerateUpiId();
			gui.setVisible(true);
		}

		if (amount > 200000) {
			JOptionPane.showMessageDialog(contentPane, "Limit Exceed");
			tfUpiAmount.requestFocus();
			return;
		}

		if (pins != LoginPage.k) {
			attempt--;
			JOptionPane.showMessageDialog(contentPane, "Incorrect Pin \n Attempt Left:- " + attempt);
			tfUpiPIn.requestFocus();

			if (attempt == 0) {
				dispose();
			}
			return;
		}

		int ids = LoginPage.id;
		newBalance = bal1() - amount;
		// JOptionPane.showMessageDialog(contentPane, newBalance);
		if (amount < 0) {
			JOptionPane.showMessageDialog(contentPane, "Add amount more than 1");
			tfUpiAmount.requestFocus();
			return;
		}
		if (amount < bal1()) {
			try {

				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/account", "root", "");
				pst = con.prepareStatement("update signup set balance=? where id = ?");
				pst.setLong(1, newBalance);
				pst.setInt(2, ids);
				pst.executeUpdate();

			} catch (SQLException e1) {

				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {

				e1.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(contentPane, "Insufficent Balance");
		}

		// To the person money is transferred
		newBalance = bal2() + amount;
		// JOptionPane.showMessageDialog(contentPane, newBalance);

		if (amount < bal1()) {
			try {

				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/account", "root", "");
				pst = con.prepareStatement("update signup set balance=? where id = ?");
				pst.setLong(1, newBalance);
				pst.setInt(2, id2);
				pst.executeUpdate();

				JOptionPane.showMessageDialog(contentPane, "Transaction successfull");

				dispose();

			} catch (SQLException e1) {

				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {

				e1.printStackTrace();
			}
		}
	}

	private String checkNumber() {
		try {
			connection();
			String u, mobile = upi;

			while (rs.next()) {

				mobileNumber = rs.getString(9);
				u = rs.getString(9);

				if (mobile.equals(u)) {
//					JOptionPane.showMessageDialog(contentPane, "Login Successfully");

					break;
				}

			}

		} catch (Exception ae) {
			ae.printStackTrace();
		}
//		JOptionPane.showMessageDialog(contentPane, mobileNumber);
		return mobileNumber;
	}

	private String checkUpiId() {
		try {
			connection();
			String u, userName = upi;

			while (rs.next()) {

				upiName = rs.getString(10);
				u = rs.getString(10);

				if (userName.equals(u)) {
//					JOptionPane.showMessageDialog(contentPane, "Login Successfully");

					break;
				}
			}
		} catch (Exception ae) {
			ae.printStackTrace();
		}
//		JOptionPane.showMessageDialog(contentPane, upiName);
		return upiName;
	}

	private int bal2() {
		try {
			connection();
			String u = upi, p = upi, userName = upi, mobile = upi;

			while (rs.next()) {

				id2 = rs.getInt(1);
				bals2 = rs.getInt(8);
				u = rs.getString(10);
				p = rs.getString(9);

				if (userName.equals(u) || mobile.equals(p)) {
//					JOptionPane.showMessageDialog(contentPane, "Login Successfully");

					break;
				}

			}

		} catch (Exception ae) {
			ae.printStackTrace();
		}
		return bals2;
	}

	private int bal1() {
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
