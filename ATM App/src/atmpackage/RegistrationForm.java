package atmpackage;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Toolkit;


public class RegistrationForm extends JDialog implements ActionListener
{
	private JTextField accnumber;
	private JTextField name;
	private JTextField dob;
	private JTextField address;
	private JPasswordField pin;
	private JPasswordField confirmpin;
	JButton register, reset;
	JRadioButton m,f;
	JRadioButton savings,current;
	public int acn;
	
	

	public RegistrationForm() 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrationForm.class.getResource("/atmpackage/images/atm3.jpg")));
		setTitle("Registration");
		
		getContentPane().setLayout(null);
		
		JLabel lblRegistrationForm = new JLabel("Registration Form");
		lblRegistrationForm.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblRegistrationForm.setBounds(115, 23, 172, 22);
		getContentPane().add(lblRegistrationForm);
		
		JLabel lblName = new JLabel("Account Number");
		lblName.setBackground(new Color(70, 130, 180));
		lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblName.setForeground(new Color(0, 0, 0));
		lblName.setBounds(21, 71, 117, 14);
		getContentPane().add(lblName);
				
		JLabel lblAge = new JLabel("Name");
		lblAge.setBackground(new Color(70, 130, 180));
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAge.setForeground(new Color(0, 0, 0));
		lblAge.setBounds(21, 98, 46, 14);
		getContentPane().add(lblAge);
		
		accnumber = new JTextField();
		accnumber.addFocusListener(new FocusAdapter()
		{
			public void focusLost(FocusEvent fe)
			{
				fillInfo(accnumber.getText());
			}
		});
		accnumber.setBounds(148, 68, 208, 20);
		getContentPane().add(accnumber);
		accnumber.setColumns(10);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(148, 95, 208, 20);
		getContentPane().add(name);
		
		JLabel lblAccountType = new JLabel("DOB");
		lblAccountType.setBackground(new Color(70, 130, 180));
		lblAccountType.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAccountType.setForeground(new Color(0, 0, 0));
		lblAccountType.setBounds(22, 163, 86, 14);
		getContentPane().add(lblAccountType);
		
		JLabel lblAccountNumber = new JLabel("Account Type");
		lblAccountNumber.setBackground(new Color(70, 130, 180));
		lblAccountNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAccountNumber.setForeground(new Color(0, 0, 0));
		lblAccountNumber.setBounds(21, 194, 118, 14);
		getContentPane().add(lblAccountNumber);
		
		dob = new JTextField();
		dob.setColumns(10);
		dob.setBounds(148, 160, 208, 20);
		getContentPane().add(dob);
		
		JLabel label = new JLabel("Address");
		label.setBackground(new Color(70, 130, 180));
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setForeground(new Color(0, 0, 0));
		label.setBounds(21, 225, 87, 14);
		getContentPane().add(label);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(148, 222, 208, 20);
		getContentPane().add(address);
		
		JLabel lblEnterAtmPin = new JLabel("Enter ATM Pin");
		lblEnterAtmPin.setBackground(new Color(70, 130, 180));
		lblEnterAtmPin.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEnterAtmPin.setForeground(new Color(0, 0, 0));
		lblEnterAtmPin.setBounds(21, 268, 118, 14);
		getContentPane().add(lblEnterAtmPin);
		
		pin = new JPasswordField();
		pin.setBounds(148, 265, 208, 20);
		getContentPane().add(pin);
		
		JLabel lblConfirmPin = new JLabel("Confirm Pin");
		lblConfirmPin.setBackground(new Color(70, 130, 180));
		lblConfirmPin.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblConfirmPin.setForeground(new Color(0, 0, 0));
		lblConfirmPin.setBounds(21, 313, 118, 14);
		getContentPane().add(lblConfirmPin);
		
		confirmpin = new JPasswordField();
		confirmpin.setBounds(148, 310, 208, 20);
		getContentPane().add(confirmpin);
		

		register = new JButton("Register");
		register.setFont(new Font("Tahoma", Font.BOLD, 14));
		register.setForeground(new Color(139, 0, 0));
		register.setBounds(50, 361, 89, 23);
		getContentPane().add(register);
		

		reset = new JButton("Reset");
		reset.setFont(new Font("Tahoma", Font.BOLD, 14));
		reset.setForeground(new Color(139, 0, 0));
		reset.setBounds(217, 361, 89, 23);
		getContentPane().add(reset);
		
		register.addActionListener(this);
		reset.addActionListener(this);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBackground(new Color(70, 130, 180));
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGender.setForeground(new Color(0, 0, 0));
		lblGender.setBounds(21, 123, 46, 14);
		getContentPane().add(lblGender);
		

		m = new JRadioButton("Male");
		m.setFont(new Font("Tahoma", Font.BOLD, 12));
		m.setOpaque(false);
		m.setBounds(148, 122, 63, 23);
		getContentPane().add(m);
		

		f = new JRadioButton("Female");
		f.setFont(new Font("Tahoma", Font.BOLD, 12));
		f.setOpaque(false);
		f.setBounds(235, 120, 86, 23);
		getContentPane().add(f);
		

		current = new JRadioButton("Current");
		current.setFont(new Font("Tahoma", Font.BOLD, 12));
		current.setOpaque(false);
		current.setBounds(145, 190, 87, 23);
		getContentPane().add(current);
		

		savings = new JRadioButton("Savings");
		savings.setFont(new Font("Tahoma", Font.BOLD, 12));
		savings.setOpaque(false);
		savings.setBounds(247, 190, 109, 23);
		getContentPane().add(savings);
		
		ImageIcon ii= new ImageIcon(getClass().getResource("images/blue3.jpg"));
		JLabel label2= new JLabel(ii);
		label2.setBackground(new Color(100, 149, 237));
		label2.setForeground(new Color(255, 255, 0));
		label2.setOpaque(true);
		label2.setBounds(0,0,389,400);
		getContentPane().add(label2);
		
		setSize(405, 434);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		Object ob =e.getSource();
		if(ob== register )
		{	
		String atmpin=pin.getText();
		if(confirmpin.getText().equalsIgnoreCase(atmpin))
				{
					int ppin =Integer.parseInt(pin.getText());
					long ccardnumber =System.currentTimeMillis();
					String s= "update accountdetails set atmpin=?,cardnumber=? where accnumber=?";
					try
					{
						//System.out.println("hello");
						Connection c= MyConnection.connect();
						PreparedStatement ps= c.prepareStatement(s);
						ps.setInt(1,ppin);
						ps.setLong(2,ccardnumber);
						ps.setInt(3,acn );
						ps.executeUpdate();
						String s1=Long.toString(ccardnumber);
						JOptionPane.showMessageDialog(null, "Successfully Registered... your card number is --->"+s1);
						dispose();
					}
					catch(SQLException se)
					{
						se.printStackTrace();
					}
				}
		else
		{
			JOptionPane.showMessageDialog(null, "Pin does NOT match");
		}
		
		}
		if(ob==reset)
		{
			accnumber.setText("");
			name.setText("");
			dob.setText("");
			address.setText("");
			pin.setText("");
			confirmpin.setText("");
			
		}
		
		
		
		
		
	
	}
	public static void main(String[] args) 
	{
		//System.out.println(System.currentTimeMillis());
		
		new RegistrationForm();
	}
	void fillInfo(String acno)
	{
		int fn=0;
		String s = "select * from accountdetails where accnumber=?";
		Connection c = MyConnection.connect();
		try
		{
			PreparedStatement ps = c.prepareStatement(s);
			ps.setString(1, acno);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				++fn;
				name.setText(rs.getString("name"));
				if(rs.getString("gender").equalsIgnoreCase("Male"))
				{
					m.setSelected(true);
				}
				else
				{
					f.setSelected(true);
				}
				dob.setText(rs.getString("dob"));
				if(rs.getString("acctype").equalsIgnoreCase("savings"))
				{
					savings.setSelected(true);
				}
				else
				{
					current.setSelected(true);
				}
				address.setText(rs.getString("address"));
				acn=Integer.parseInt(accnumber.getText());
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		if(fn == 0)
		{
			JOptionPane.showMessageDialog(null, "Account Number Not Found");
			accnumber.setText("");
			accnumber.requestFocusInWindow();
		}
	}
}



