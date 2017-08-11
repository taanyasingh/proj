package atmpackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class Withdrawl extends JDialog implements ActionListener{
	private JTextField twithdraw;
	JButton bback, bwithdraw;
	static int transactid;

	public Withdrawl() 
	{
		setTitle("Withdraw");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Withdrawl.class.getResource("/atmpackage/images/atm3.jpg")));
		setModal(true);
		getContentPane().setLayout(null);
		
		//ImageIcon imageForOne = new ImageIcon(getClass().getResource("resources//orange.jpg"));
		bwithdraw = new JButton("Withdraw");
		bwithdraw.setIcon(null);
		bwithdraw.setForeground(Color.BLUE);
		bwithdraw.setBackground(Color.BLUE);
		bwithdraw.setFont(new Font("Tahoma", Font.BOLD, 18));
		bwithdraw.setBounds(51, 185, 149, 27);
		getContentPane().add(bwithdraw);
		
		 bback = new JButton("Back");
		 bback.setForeground(Color.BLUE);
		bback.setFont(new Font("Tahoma", Font.BOLD, 18));
		bback.setBounds(229, 185, 132, 27);
		getContentPane().add(bback);
		
		JLabel lblUsername = new JLabel(MainPage.user);
		lblUsername.setBounds(344, 11, 80, 14);
		getContentPane().add(lblUsername);
		
		JLabel lblEnterTheAmount = new JLabel("Enter the Amount");
		lblEnterTheAmount.setForeground(Color.BLACK);
		lblEnterTheAmount.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEnterTheAmount.setBounds(33, 80, 167, 27);
		getContentPane().add(lblEnterTheAmount);
		
		twithdraw = new JTextField();
		twithdraw.setBackground(Color.WHITE);
		twithdraw.setBounds(216, 79, 189, 27);
		getContentPane().add(twithdraw);
		twithdraw.setColumns(10);
		
		ImageIcon ii= new ImageIcon(getClass().getResource("images/blue3.jpg"));
		JLabel label= new JLabel(ii);
		label.setOpaque(true);
		label.setBounds(0,0,434,400);
		getContentPane().add(label);
		
		gettransactionId();
		bwithdraw.addActionListener(this);
		bback.addActionListener(this);
		
		setSize(450, 400);
		setVisible(true);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		//new Withdrawl();
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object ob=e.getSource();
		if(ob==bwithdraw)
		{
			int amt= Integer.parseInt(twithdraw.getText());
			String s="insert into transaction (transactionid, cardnumber, withdrawl, date, deposit ) values(?, ?, ?, ?,0.0) ";
			try
			{
				Connection c=MyConnection.connect();
				PreparedStatement ps=c.prepareStatement(s);
				
				ps.setInt(1, transactid);
				ps.setString(2, MainPage.card);
				ps.setDouble(3, amt);
				java.util.Date dt = new java.util.Date();
				java.sql.Date dat = new java.sql.Date(dt.getTime());
				ps.setDate(4,dat );
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "transaction done successfully... ");
				
				//int bal= 
				String s1="update accountdetails set balance=balance-? where cardnumber=?";
				PreparedStatement p1=c.prepareStatement(s1);
				p1.setDouble(1, amt);
				p1.setString(2, MainPage.card);
				p1.executeUpdate();
				//dispose();
				
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		if (ob== bback)
		{
			new UserAccount();
			dispose();
		}


	}
	
	
	void gettransactionId()
	{
		String s="select max(transactionid) from transaction";
		Connection c= MyConnection.connect();
		try
		{
			PreparedStatement ps= c.prepareStatement(s);
			ResultSet rs= ps.executeQuery();
			while(rs.next())
			{
				transactid=rs.getInt(1);
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		transactid++;
		//tid.setText(String.valueOf(id));
	}

}
