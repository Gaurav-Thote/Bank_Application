package com.practice.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Your_Detail extends JFrame {

	private JPanel contentPane;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	String fName, mName, lName, add, uName;
	int bals;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Your_Detail frame = new Your_Detail();
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
	public Your_Detail() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 611, 601);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name:-");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblName.setBounds(108, 128, 72, 41);
		contentPane.add(lblName);
		
		JLabel lblDetail = new JLabel("Your Details");
		lblDetail.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetail.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblDetail.setBounds(-23, 61, 608, 31);
		contentPane.add(lblDetail);
		
		JLabel lblAddress = new JLabel("Address:-");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAddress.setBounds(108, 204, 93, 41);
		contentPane.add(lblAddress);
		
		JLabel lblBal = new JLabel("Bank Balance:-\r\n");
		lblBal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBal.setBounds(108, 275, 150, 41);
		contentPane.add(lblBal);
		
		JLabel lblUsername = new JLabel("Username:-");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsername.setBounds(108, 340, 133, 41);
		contentPane.add(lblUsername);
		
		JButton btnExit = new JButton("");
		btnExit.setIcon(new ImageIcon(Your_Detail.class.getResource("/img/exit (1).png")));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			setVisible(false);;
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnExit.setBounds(255, 427, 87, 31);
		contentPane.add(btnExit);
		
		JLabel lblShowName = new JLabel("");
		lblShowName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblShowName.setBounds(169, 128, 395, 41);
		contentPane.add(lblShowName);
		lblShowName.setText(fName()+" "+mName()+" "+lName());
		
		JLabel lblShowAddress = new JLabel("");
		lblShowAddress.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblShowAddress.setBounds(186, 204, 374, 41);
		contentPane.add(lblShowAddress);
		lblShowAddress.setText(add());
		
		JLabel lblShowBal = new JLabel("");
		lblShowBal.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblShowBal.setBounds(228, 275, 336, 41);
		contentPane.add(lblShowBal);
		lblShowBal.setText(""+bal());
		
		JLabel lblShowUsername = new JLabel("");
		lblShowUsername.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblShowUsername.setBounds(202, 340, 362, 41);
		contentPane.add(lblShowUsername);
		lblShowUsername.setText(uName());
		
		JLabel png = new JLabel("");
		png.setIcon(new ImageIcon(Your_Detail.class.getResource("/img/square1.png")));
		png.setBounds(0, 0, 595, 562);
		contentPane.add(png);}
		private String uName() {
			try {
				connection();
				String u= LoginPage.u, p= LoginPage.p,userName= LoginPage.u, password= LoginPage.p;
				
				while (rs.next()) {
					
					uName = rs.getString(6);
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
			return uName;
	}

		private String add() {
			try {
				connection();
				String u= LoginPage.u, p= LoginPage.p,userName= LoginPage.u, password= LoginPage.p;
				
				while (rs.next()) {
					
					add = rs.getString(5);
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
			return add;
	}

		private String lName() {
			try {
				connection();
				String u= LoginPage.u, p= LoginPage.p,userName= LoginPage.u, password= LoginPage.p;
				
				while (rs.next()) {
					
					lName = rs.getString(4);
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
			return lName;
	}

		private String mName() {
			try {
				connection();
				String u= LoginPage.u, p= LoginPage.p,userName= LoginPage.u, password= LoginPage.p;
				
				while (rs.next()) {
					
					mName = rs.getString(3);
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
			return mName;
	}

		private String fName() {
			try {
				connection();
				String u= LoginPage.u, p= LoginPage.p,userName= LoginPage.u, password= LoginPage.p;
				
				while (rs.next()) {
					
					fName = rs.getString(2);
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
			return fName;
		
	}

		private int bal() {
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
