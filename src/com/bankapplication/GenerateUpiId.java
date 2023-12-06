package com.bankapplication;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class GenerateUpiId extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfUpiId;
	private JTextField tfUpiPin;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerateUpiId frame = new GenerateUpiId();
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
	public GenerateUpiId() {
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
		
		tfUpiId = new JTextField();
		tfUpiId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfUpiId.setColumns(10);
		tfUpiId.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "UPI ID", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tfUpiId.setBounds(101, 81, 195, 54);
		contentPane.add(tfUpiId);
		
		JButton btnWithdraw = new JButton("");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				upiIdGenerate();
			}
		});
		btnWithdraw.setIcon(new ImageIcon(GenerateUpiId.class.getResource("/img/confirm.png")));
		btnWithdraw.setBounds(152, 273, 102, 31);
		contentPane.add(btnWithdraw);
		
		tfUpiPin = new JTextField();
		tfUpiPin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfUpiPin.setColumns(10);
		tfUpiPin.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "UPI Pin", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tfUpiPin.setBounds(101, 174, 195, 54);
		contentPane.add(tfUpiPin);
		
		JLabel png = new JLabel("");
		png.setIcon(new ImageIcon(Withdraw.class.getResource("/img/square1 (2).png")));
		png.setBounds(0, 0, 399, 387);
		contentPane.add(png);
		
	}

	protected void upiIdGenerate() {
		String upiName, upiPin;
		int upiPins, id = LoginPage.id;
		
		
		upiName = tfUpiId.getText().trim();
		upiPin = tfUpiPin.getText().trim();
		
		if(upiName.isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "UPI ID is required");
			tfUpiId.requestFocus();
			return;
		}
		if(upiPin.isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "UPI pin required");
			tfUpiPin.requestFocus();
			return;
		}
		if(upiPin.length() == 4) {
			try {
				upiPins = Integer.parseInt(upiPin);
			}
			catch(Exception be) {
				JOptionPane.showMessageDialog(contentPane, "UPI pin cannot contain character");
				tfUpiPin.requestFocus();
				return;
			}
			if(upiPins < 10000) {
				connection();
				try {
					pst = con.prepareStatement("update signup set upi_username=?, upi_pin=? where id = ?");
					pst.setString(1, upiName);
					pst.setString(2, upiPin);
					pst.setInt(3, id);
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(contentPane, "UPI Id created Successfully");
					dispose();
					
				}catch(Exception ae) {
					JOptionPane.showMessageDialog(null, "Data Update in Database faild");
					
				}
			}
			
		}
		if(upiPin.length() != 4) {
			JOptionPane.showMessageDialog(contentPane, "UPI pin must be of 4 digits");
			tfUpiPin.requestFocus();
			return;
		}
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
