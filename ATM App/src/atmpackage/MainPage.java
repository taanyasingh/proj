package atmpackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Toolkit;

public class MainPage extends JDialog implements ActionListener
{
	private JTextField tcardnumber;
	private JPasswordField tatmpin;
	JButton blogin;
	JButton bregister;
	static String card;
	static String user;

	public MainPage() 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainPage.class.getResource("/atmpackage/images/atm3.jpg")));
		setSize(500, 400);
		setForeground(Color.BLACK);
		setTitle("Login");
		getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel("Login/ Register");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblLogin.setBounds(161, 38, 172, 35);
		getContentPane().add(lblLogin);
		
		JLabel lblCardNumber = new JLabel("Card Number");
		lblCardNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCardNumber.setBounds(63, 118, 101, 14);
		getContentPane().add(lblCardNumber);
		
		JLabel lblPin = new JLabel("ATM Pin ");
		lblPin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPin.setBounds(63, 174, 77, 14);
		getContentPane().add(lblPin);
		
		tcardnumber = new JTextField();
		tcardnumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tcardnumber.setBounds(202, 117, 203, 20);
		getContentPane().add(tcardnumber);
		tcardnumber.setColumns(10);
		
		tatmpin = new JPasswordField();
		tatmpin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tatmpin.setBounds(202, 173, 203, 20);
		getContentPane().add(tatmpin);
		
		blogin = new JButton("Login");
		blogin.setOpaque(false);
		blogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		blogin.setBounds(107, 248, 89, 23);
		getContentPane().add(blogin);
		

		bregister = new JButton("Register");
		bregister.setOpaque(false);
		bregister.setFont(new Font("Tahoma", Font.BOLD, 14));
		bregister.setBounds(278, 248, 101, 23);
		getContentPane().add(bregister);
		
		ImageIcon ii= new ImageIcon(getClass().getResource("images/blue3.jpg"));
		JLabel label= new JLabel(ii);
		label.setOpaque(true);
		label.setBounds(0,0,484,400);
		getContentPane().add(label);
		
		blogin.addActionListener(this);
		bregister.addActionListener(this);
		
		setVisible(true);
		
	}

	public static void main(String[] args) 
	{
		new MainPage();

	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object ob = e.getSource();
		if(ob== blogin)
		{
			card=tcardnumber.getText();
			String atmpin = new String(tatmpin.getPassword());
			String s ="select * from accountdetails where cardnumber=? and atmpin=?";
			Connection c= MyConnection.connect();
			try
			{
				PreparedStatement ps =c.prepareStatement(s);
				ps.setString(1,card);
				ps.setString(2,atmpin);
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					rs.beforeFirst();
					while(rs.next())
					{
						user=rs.getString("name");
						System.out.println(user);
					}
					dispose();
					new UserAccount();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "invalid");
					tcardnumber.setText("");
					tatmpin.setText("");
					tcardnumber.requestFocusInWindow();
		
				}
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			//new UserAccount() ;
		}
		if(ob== bregister)
		{
			new RegistrationForm() ;
		}


		
	}
}
