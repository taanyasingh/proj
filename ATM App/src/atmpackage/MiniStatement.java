package atmpackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import atmpackage.MyConnection;

public class MiniStatement 
{
	
	JDialog dialog;
	JButton back,balance;
	JTable table;
	String []columns;
	String [][]data;
	int r, c, cnt;
	JScrollPane jsp;

	public MiniStatement()
	{
		String s="select * from transaction where cardnumber=?";
		Connection cn= MyConnection.connect();
		try
		{
			PreparedStatement ps=cn.prepareStatement(s);
			ps.setInt(1, Integer.parseInt(MainPage.card));
			System.out.println("Card from main page="+MainPage.card);
//			ps.setInt(1, 11223304);
			ResultSet rs=ps.executeQuery();
			rs.last();
			cnt=rs.getRow(); //numbr of rows in  result
			System.out.println("cnt = " + cnt);
			rs.beforeFirst();
			columns =new String[]{"TransactionID","Card Number","Deposit","Withdrawl","Date"};
			data = new String[cnt][5];
			System.out.println("r = " + r);
			System.out.println("c = "+c);
			if(cnt == 0)
			{
				JOptionPane.showMessageDialog(null, "No Transactions Found");
				return;
			}
			while(rs.next())
			{
				
				data[r][c] =rs.getString("transactionid");
				++c;
				data[r][c]=Integer.toString(rs.getInt("cardnumber"));
				++c;
				data[r][c]=Double.toString(rs.getDouble("deposit"));
				++c;
				data[r][c]=Double.toString(rs.getDouble("withdrawl"));
				++c;
				data[r][c]=rs.getDate("date").toString();
				
				c=0;
				++r;
			}
			table= new JTable(data,columns);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			dialog =new JDialog();
			dialog.getContentPane().setLayout(null);
			dialog.setSize(493, 433);
			dialog.setModal(true);
			jsp = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			jsp.setBounds(57,63,381,181);
			back= new JButton("Back");
			back.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					new UserAccount();
					
				}
			});
			
			balance=new JButton();
			balance.setText("Balance");
			balance.setBounds(208, 22, 80, 30);
			dialog.getContentPane().add(balance);
			balance.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
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
			});
			
			back =new JButton();
			back.setText("Back");
			
			back.setBounds(208,266,80,30);
			dialog.getContentPane().add(back);
			dialog.getContentPane().add(jsp);
			
			ImageIcon ii= new ImageIcon(getClass().getResource("images/blue3.jpg"));
			JLabel label= new JLabel(ii);
			label.setOpaque(true);
			label.setBounds(0,0,484,400);
			dialog.getContentPane().add(label);
			
			
			back.addActionListener(new ActionListener()
			{
				
				@Override
				public void actionPerformed(ActionEvent e) {
					dialog.dispose();
					new UserAccount();
					
				}
			});
			dialog.setVisible(true);
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new MiniStatement();
	}
}
