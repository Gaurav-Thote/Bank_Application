package com.practice.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Withdraw extends JFrame {

	private JPanel contentPane;
	private JTextField tfWithdraw;
	Connection con;
	PreparedStatement pst;
	int newBalance;
	ResultSet rs;
	int bals;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Withdraw frame = new Withdraw();
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
	public Withdraw() {
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
		
		tfWithdraw = new JTextField();
		tfWithdraw.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Withdraw Amount", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tfWithdraw.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfWithdraw.setBounds(99, 129, 195, 54);
		contentPane.add(tfWithdraw);
		tfWithdraw.setColumns(10);
		
		JButton btnWithdraw = new JButton("");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int withdraw;
				int ids = LoginPage.id;
				withdraw = Integer.parseInt(tfWithdraw.getText());
				newBalance = bal1() - withdraw;
				//JOptionPane.showMessageDialog(contentPane, newBalance);
				if (withdraw < 0) {
					JOptionPane.showMessageDialog(contentPane, "Add amount more than 1");
					tfWithdraw.requestFocus();
					return;
				}
				if(withdraw < bal1()) {
					try {
						
						Class.forName("com.mysql.cj.jdbc.Driver");
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/account","root","");
						pst = con.prepareStatement("update signup set balance=? where id = ?");
						pst.setLong(1, newBalance);
						pst.setInt(2, ids);
						pst.executeUpdate();
						
						
						JOptionPane.showMessageDialog(contentPane, "Withdraw successfull");
						
						dispose();
						
						
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						
						e1.printStackTrace();
					}}else {
						JOptionPane.showMessageDialog(contentPane, "Insufficent Balance");
					}
				
				
			}
		});
		btnWithdraw.setIcon(new ImageIcon(Withdraw.class.getResource("/img/withdraw.png")));
		btnWithdraw.setBounds(137, 240, 126, 31);
		contentPane.add(btnWithdraw);
		
		JLabel png = new JLabel("");
		png.setIcon(new ImageIcon(Withdraw.class.getResource("/img/square1 (2).png")));
		png.setBounds(0, 0, 399, 387);
		contentPane.add(png);
	}

	
		private int bal1() {
			try {
				connection();
				String u= LoginPage.u, p= LoginPage.p,userName= LoginPage.u, password= LoginPage.p;
				
				while (rs.next()) {
					
					bals = rs.getInt(8);
					u = rs.getString(6);
					p = rs.getString(7);
					
					if (userName.equals(u) && password.equals(p)) {	
//						JOptionPane.showMessageDialog(contentPane, "Login Successfully");
						
						break;
					}
					
				}
				
			}catch(Exception ae) {
				ae.printStackTrace();
			}
			return bals;
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
