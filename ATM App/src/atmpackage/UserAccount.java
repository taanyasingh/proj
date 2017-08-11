package atmpackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Toolkit;

public class UserAccount extends JDialog implements ActionListener
{
	
	JButton bdeposit,bwithdrawl, bcheckbalance, bchangepin, bministatement, blogout ;
	JLabel lblUsername;
	public UserAccount() 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserAccount.class.getResource("/atmpackage/images/atm3.jpg")));
		//setModal(true);
		setTitle("User Account ");
		getContentPane().setLayout(null);
		System.out.println("Name =in UserAccount ="+MainPage.user);

		lblUsername = new JLabel("Username");
		lblUsername.setText(MainPage.user);
		lblUsername.setBounds(392, 0, 71, 14);
		getContentPane().add(lblUsername);
		
		JLabel lblChooseTheOption = new JLabel("Choose from the options below");
		lblChooseTheOption.setForeground(new Color(255, 255, 0));
		lblChooseTheOption.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblChooseTheOption.setBounds(129, 24, 250, 37);
		getContentPane().add(lblChooseTheOption);
		
		 bdeposit = new JButton("Deposit");
		 bdeposit.setForeground(new Color(178, 34, 34));
		 bdeposit.setFont(new Font("Tahoma", Font.BOLD, 14));
		bdeposit.setBounds(34, 86, 111, 23);
		getContentPane().add(bdeposit);
		
		 bwithdrawl = new JButton("Withdrawl");
		 bwithdrawl.setForeground(new Color(178, 34, 34));
		 bwithdrawl.setFont(new Font("Tahoma", Font.BOLD, 14));
		bwithdrawl.setBounds(188, 86, 111, 23);
		getContentPane().add(bwithdrawl);
		
		 bcheckbalance = new JButton("Check Balance");
		 bcheckbalance.setForeground(new Color(178, 34, 34));
		 bcheckbalance.setFont(new Font("Tahoma", Font.BOLD, 14));
		bcheckbalance.setBounds(82, 159, 143, 23);
		getContentPane().add(bcheckbalance);
		
		 bchangepin = new JButton("Change Pin");
		 bchangepin.setForeground(new Color(178, 34, 34));
		 bchangepin.setFont(new Font("Tahoma", Font.BOLD, 14));
		bchangepin.setBounds(338, 86, 125, 23);
		getContentPane().add(bchangepin);
		
		 bministatement = new JButton("Mini Statement");
		 bministatement.setForeground(new Color(178, 34, 34));
		 bministatement.setFont(new Font("Tahoma", Font.BOLD, 14));
		bministatement.setBounds(263, 159, 153, 23);
		getContentPane().add(bministatement);
		
		 blogout = new JButton("Log Out ");
		 blogout.setForeground(new Color(0, 0, 128));
		 blogout.setFont(new Font("Tahoma", Font.BOLD, 18));
		blogout.setBounds(176, 271, 143, 23);
		getContentPane().add(blogout);
		
		ImageIcon ii= new ImageIcon(getClass().getResource("images/blue3.jpg"));
		JLabel label= new JLabel(ii);
		label.setOpaque(true);
		label.setBounds(0,0,500,400);
		getContentPane().add(label);
		
		bdeposit.addActionListener(this);
		bwithdrawl.addActionListener(this);
		bchangepin.addActionListener(this);
		bcheckbalance.addActionListener(this);
		bministatement.addActionListener(this);
		blogout.addActionListener(this);
		
		setSize(500, 400);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		lblUsername.setText(MainPage.user);
		Object ob= e.getSource();
		if(ob== bdeposit)
		{
			new Deposit();
			dispose();
		}
		if(ob== bchangepin)
		{
			new ChangePin();
			dispose();
		}
		if(ob== bcheckbalance)
		{
			Connection c=MyConnection.connect();
			String s="select balance from accountdetails where cardnumber=?";
			try
			{
				PreparedStatement ps= c.prepareStatement(s);
				ps.setInt(1,Integer.parseInt(MainPage.card));
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					String s1=Double.toString(rs.getDouble("balance"));
					JOptionPane.showMessageDialog(null, "Balance = " + s1);
				}
					
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		if(ob== bwithdrawl)
		{
			new Withdrawl();
			dispose();
		}
		if(ob== blogout)
		{
			JOptionPane.showMessageDialog(null, "Thank you!!! ");
			dispose();
			 new Welcome();
		}
		if(ob== bministatement)
		{
			new MiniStatement();
			dispose();
		}
		
	}

}
