package com.bankapplication;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class InternetBanking extends JFrame {


	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InternetBanking frame = new InternetBanking();
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
	public InternetBanking() {
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
		
		JButton btnUpiId = new JButton("Generate UPI Id");
		btnUpiId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				GenerateUpiId gui = new GenerateUpiId();
				gui.setVisible(true);
				
			}
		});
		btnUpiId.setBackground(new Color(192, 192, 192));
		btnUpiId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUpiId.setBounds(89, 116, 226, 46);
		contentPane.add(btnUpiId);
		
		JButton btnDebitCard = new JButton("Generate Debit Card");
		btnDebitCard.setBackground(new Color(192, 192, 192));
		btnDebitCard.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDebitCard.setBounds(89, 213, 226, 46);
		contentPane.add(btnDebitCard);
		
		JLabel png = new JLabel("");
		png.setIcon(new ImageIcon(Withdraw.class.getResource("/img/square1 (2).png")));
		png.setBounds(0, 0, 399, 387);
		contentPane.add(png);
	}
}
