package atmpackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.Color;

public class Deposit extends JDialog implements ActionListener
{
	private JTextField damount;
	JButton bDeposit, bBack;
	static int transactid;

	public Deposit() 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(Deposit.class.getResource("/atmpackage/images/atm3.jpg")));
		setTitle("Deposit");
		getContentPane().setLayout(null);
		
		JLabel lblUsrname = new JLabel(MainPage.user);
		lblUsrname.setBounds(358, 11, 66, 14);
		getContentPane().add(lblUsrname);
		
		JLabel lblEnterTheAmount = new JLabel("Enter the amount ");
		lblEnterTheAmount.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEnterTheAmount.setBounds(31, 68, 169, 27);
		getContentPane().add(lblEnterTheAmount);
		
		damount = new JTextField();
		damount.setBounds(209, 67, 201, 27);
		getContentPane().add(damount);
		damount.setColumns(10);
		
		 bDeposit = new JButton("Deposit");
		 bDeposit.setForeground(new Color(139, 0, 0));
		bDeposit.setFont(new Font("Tahoma", Font.BOLD, 18));
		bDeposit.setBounds(54, 152, 122, 27);
		getContentPane().add(bDeposit);
		
		 bBack = new JButton("Back");
		 bBack.setForeground(new Color(139, 0, 0));
		bBack.setFont(new Font("Tahoma", Font.BOLD, 18));
		bBack.setBounds(245, 152, 122, 27);
		getContentPane().add(bBack);
		
		ImageIcon ii= new ImageIcon(getClass().getResource("images/blue3.jpg"));
		JLabel label= new JLabel(ii);
		label.setOpaque(true);
		label.setBounds(0,0,484,400);
		getContentPane().add(label);
		
		gettransactionId();
		bDeposit.addActionListener(this);
		bBack.addActionListener(this);
		
		setVisible(true);
		setSize(500, 400);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) 
	{
		
		


	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object ob= e.getSource();
		if(ob== bDeposit)
		{
			
			
			int amt= Integer.parseInt(damount.getText());
			String s="insert into transaction (transactionid, cardnumber, deposit, date, withdrawl) values(?, ?, ?, ?,0.0) ";
			try
			{
				Connection c=MyConnection.connect();
				PreparedStatement ps=c.prepareStatement(s);
				ps.setInt(1, transactid);
				ps.setString(2, MainPage.card);
				ps.setDouble(3, amt);
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date dt = new java.util.Date();
				java.sql.Date dat = new java.sql.Date(dt.getTime());
				ps.setDate(4,dat );
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "transaction done successfully... ");
				
				//int bal= 
				String s1="update accountdetails set balance=balance+? where cardnumber=?";
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
		if (ob== bBack)
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
