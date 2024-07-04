package com.bankapplication;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class YourDetails extends JFrame {

	private JPanel contentPane;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	String fName, mName, lName, add, uName, mobileNumber, upiName;
	int bals;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					YourDetails frame = new YourDetails();
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
	public YourDetails() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Customer Bank Application");
		setBounds(100, 100, 611, 601);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblName = new JLabel("Name:-");
		lblName.setBounds(108, 128, 72, 41);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblName);

		JLabel lblDetail = new JLabel("Your Details");
		lblDetail.setBounds(-23, 61, 608, 31);
		lblDetail.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetail.setFont(new Font("Tahoma", Font.PLAIN, 22));
		contentPane.add(lblDetail);

		JLabel lblAddress = new JLabel("Address:-");
		lblAddress.setBounds(108, 168, 93, 41);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblAddress);

		JLabel lblBal = new JLabel("Bank Balance:-\r\n");
		lblBal.setBounds(108, 260, 150, 41);
		lblBal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblBal);

		JLabel lblUsername = new JLabel("Username:-");
		lblUsername.setBounds(108, 305, 133, 41);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblUsername);

		JLabel lblMobileNumber = new JLabel("Mobile Number:-");
		lblMobileNumber.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMobileNumber.setBounds(108, 214, 150, 41);
		contentPane.add(lblMobileNumber);

		JButton btnExit = new JButton("");
		btnExit.setIcon(new ImageIcon(YourDetails.class.getResource("/img/exit (1).png")));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnExit.setBounds(258, 455, 87, 31);
		contentPane.add(btnExit);

		JLabel lblShowName = new JLabel("");
		lblShowName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblShowName.setBounds(174, 128, 395, 41);
		contentPane.add(lblShowName);
		lblShowName.setText(fName() + " " + mName() + " " + lName());

		JLabel lblShowAddress = new JLabel("");
		lblShowAddress.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblShowAddress.setBounds(190, 168, 362, 41);
		contentPane.add(lblShowAddress);
		lblShowAddress.setText(add());

		JLabel lblShowBal = new JLabel("");
		lblShowBal.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblShowBal.setBounds(233, 260, 336, 41);
		contentPane.add(lblShowBal);
		lblShowBal.setText("" + bal());

		JLabel lblShowUsername = new JLabel("");
		lblShowUsername.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblShowUsername.setBounds(207, 305, 362, 41);
		contentPane.add(lblShowUsername);
		lblShowUsername.setText(uName());

		JLabel lblShowMobileNumber = new JLabel("");
		lblShowMobileNumber.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblShowMobileNumber.setBounds(246, 214, 330, 41);
		contentPane.add(lblShowMobileNumber);
		lblShowMobileNumber.setText("" + mobileNumber());

		JLabel lblUpiName = new JLabel("UPI Name:-");
		lblUpiName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUpiName.setBounds(108, 352, 133, 41);
		contentPane.add(lblUpiName);

		JLabel lblCardNumber = new JLabel("Card Number:-");
		lblCardNumber.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCardNumber.setBounds(108, 393, 133, 41);
		contentPane.add(lblCardNumber);
		lblShowMobileNumber.setText("" + mobileNumber());

		JLabel lblShowUpiId = new JLabel("");
		lblShowUpiId.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblShowUpiId.setBounds(207, 352, 362, 41);
		contentPane.add(lblShowUpiId);
		lblShowUpiId.setText(upiName());

		JLabel lblShowCardNumber = new JLabel("");
		lblShowCardNumber.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblShowCardNumber.setBounds(207, 393, 362, 41);
		contentPane.add(lblShowCardNumber);

		JLabel png = new JLabel("");
		png.setIcon(new ImageIcon(YourDetails.class.getResource("/img/square1.png")));
		png.setBounds(0, 0, 595, 562);
		contentPane.add(png);
	}

	private String upiName() {
		try {
			connection();
			String u = LoginPage.u, p = LoginPage.p, userName = LoginPage.u, password = LoginPage.p;

			while (rs.next()) {

				upiName = rs.getString(10);
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
		return upiName;

	}

	private String mobileNumber() {
		try {
			connection();
			String u = LoginPage.u, p = LoginPage.p, userName = LoginPage.u, password = LoginPage.p;

			while (rs.next()) {

				mobileNumber = rs.getString(9);
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
		return mobileNumber;
	}

	private String uName() {
		try {
			connection();
			String u = LoginPage.u, p = LoginPage.p, userName = LoginPage.u, password = LoginPage.p;

			while (rs.next()) {

				uName = rs.getString(6);
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
		return uName;
	}

	private String add() {
		try {
			connection();
			String u = LoginPage.u, p = LoginPage.p, userName = LoginPage.u, password = LoginPage.p;

			while (rs.next()) {

				add = rs.getString(5);
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
		return add;
	}

	private String lName() {
		try {
			connection();
			String u = LoginPage.u, p = LoginPage.p, userName = LoginPage.u, password = LoginPage.p;

			while (rs.next()) {

				lName = rs.getString(4);
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
		return lName;
	}

	private String mName() {
		try {
			connection();
			String u = LoginPage.u, p = LoginPage.p, userName = LoginPage.u, password = LoginPage.p;

			while (rs.next()) {

				mName = rs.getString(3);
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
		return mName;
	}

	private String fName() {
		try {
			connection();
			String u = LoginPage.u, p = LoginPage.p, userName = LoginPage.u, password = LoginPage.p;

			while (rs.next()) {

				fName = rs.getString(2);
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
		return fName;

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
