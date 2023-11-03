package com.practice.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class BankApplication extends JFrame {

	private JPanel contentPane;
	public JLabel lblBal;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	
	
	private JLabel lblYourDetails;
	private JLabel lblUpDetalis;
	private JLabel lblDelAccount;
	private JLabel lblDeposit;
	private JLabel lblWithdraw;
	static int bals;
	JButton btnRefresh;
	private JLabel lblLogout;
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BankApplication ba = new BankApplication();
					ba.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BankApplication() {
		setTitle("Customer Bank Application");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 895, 545);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUser = new JLabel("");
		lblUser.setForeground(new Color(255, 255, 255));
		lblUser.setFont(new Font("Algerian", Font.PLAIN, 18));
		lblUser.setBounds(97, 26, 406, 37);
		contentPane.add(lblUser);
		lblUser.setText(LoginPage.fName+" "+LoginPage.lName);
		
	
		JLabel lblWelcome = new JLabel("welcome");
		lblWelcome.setForeground(new Color(255, 255, 255));
		lblWelcome.setFont(new Font("Algerian", Font.PLAIN, 18));
		lblWelcome.setBounds(10, 26, 98, 37);
		contentPane.add(lblWelcome);
		
		JLabel lblAvailableBalance = new JLabel("Available Balance\r\n");
		lblAvailableBalance.setForeground(new Color(255, 255, 255));
		lblAvailableBalance.setHorizontalAlignment(SwingConstants.CENTER);
		lblAvailableBalance.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAvailableBalance.setVerticalAlignment(SwingConstants.TOP);
		lblAvailableBalance.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblAvailableBalance.setBounds(10, 59, 871, 37);
		contentPane.add(lblAvailableBalance);
		
		lblBal = new JLabel("");
		lblBal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BankApplication ba= new BankApplication();
				ba.revalidate();
				ba.repaint();
			}
		});
		lblBal.setForeground(new Color(255, 255, 255));
		lblBal.setHorizontalAlignment(SwingConstants.CENTER);
		lblBal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBal.setBounds(364, 93, 159, 37);
		contentPane.add(lblBal);
		lblBal.setText(""+bal());
		
		lblYourDetails = new JLabel("Your Details");
		lblYourDetails.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				YourDetails details = new YourDetails();
				details.setVisible(true);
			}
		});
		lblYourDetails.setForeground(new Color(255, 255, 255));
		lblYourDetails.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblYourDetails.setBounds(205, 219, 103, 27);
		contentPane.add(lblYourDetails);
		
		lblUpDetalis = new JLabel("Update Details");
		lblUpDetalis.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUpDetalis.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Update_Detail uDetails =  new Update_Detail();
				dispose();				
				uDetails.setVisible(true);
				
			}
		});
		lblUpDetalis.setForeground(new Color(255, 255, 255));
		lblUpDetalis.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUpDetalis.setBounds(596, 219, 128, 27);
		contentPane.add(lblUpDetalis);
		
		lblDeposit = new JLabel("Deposit");
		lblDeposit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Deposit dep = new Deposit();
				dep.setVisible(true);
			}
		});
		lblDeposit.setForeground(new Color(255, 255, 255));
		lblDeposit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDeposit.setBounds(205, 332, 103, 27);
		contentPane.add(lblDeposit);
		
		lblWithdraw = new JLabel("Withdraw");
		lblWithdraw.setHorizontalAlignment(SwingConstants.TRAILING);
		lblWithdraw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Withdraw wd =new Withdraw();
				wd.setVisible(true);
			}
		});
		lblWithdraw.setForeground(new Color(255, 255, 255));
		lblWithdraw.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblWithdraw.setBounds(621, 332, 103, 27);
		contentPane.add(lblWithdraw);
		
		lblDelAccount = new JLabel("Remove Account");
		lblDelAccount.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				deleteAccount();
			}
		});
		lblDelAccount.setForeground(new Color(255, 255, 255));
		lblDelAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblDelAccount.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDelAccount.setBounds(360, 433, 206, 27);
		contentPane.add(lblDelAccount);
		
		btnRefresh = new JButton("");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				BankApplication ba = new BankApplication();
				ba.setVisible(true);
			}
		});
		btnRefresh.setBackground(Color.BLACK);
		btnRefresh.setIcon(new ImageIcon(BankApplication.class.getResource("/img/refresh.png")));
		btnRefresh.setBounds(514, 88, 49, 42);
		btnRefresh.setOpaque(false);
		btnRefresh.setFocusPainted(false);
		btnRefresh.setBorderPainted(false);
		btnRefresh.setContentAreaFilled(false);
		btnRefresh.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		contentPane.add(btnRefresh);
		
		lblLogout = new JLabel("Logout");
		lblLogout.setHorizontalAlignment(SwingConstants.TRAILING);
		lblLogout.setForeground(Color.WHITE);
		lblLogout.setFont(new Font("Algerian", Font.PLAIN, 18));
		lblLogout.setBounds(783, 26, 86, 37);
		contentPane.add(lblLogout);
		
		JLabel png = new JLabel("");
		png.setIcon(new ImageIcon(BankApplication.class.getResource("/img/bank.png")));
		png.setBounds(0, 11, 887, 512);
		contentPane.add(png);
	}

	protected void deleteAccount() {
		String y="Are you sure you want to delete your account"; 
		int yes = JOptionPane.showConfirmDialog(contentPane, y);
	if (JOptionPane.YES_OPTION== yes) {
			int id = LoginPage.id;
			try {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/account","root","");
				} catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
				}
				
				PreparedStatement pst;
				
				pst= con.prepareStatement("delete from signup where id=?");
				pst.setInt(1, id);
				pst.executeUpdate();
				dispose();
				LoginPage lp = new LoginPage();
				lp.setVisible(true);
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		
		}
		
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
//					JOptionPane.showMessageDialog(contentPane, "Login Successfully");
					
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
protected void refresh() {
	dispose();
	BankApplication ba = new BankApplication();
	ba.setVisible(true);
}
}







