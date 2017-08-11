package atmpackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;

public class ChangePin extends JDialog implements ActionListener
{
	private JPasswordField oldpin;
	private JPasswordField newpin;
	private JPasswordField confirmnwpin;
	JButton bChangePin,bBack;


	public ChangePin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ChangePin.class.getResource("/atmpackage/images/atm3.jpg")));
		
		
		setTitle("Change Pin");
		getContentPane().setLayout(null);
		
		JLabel u = new JLabel(MainPage.user);
		u.setBounds(340, 11, 66, 14);
		getContentPane().add(u);
		
		JLabel lblEnterOldPin = new JLabel("Enter old Pin");
		lblEnterOldPin.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEnterOldPin.setBounds(22, 68, 96, 14);
		getContentPane().add(lblEnterOldPin);
		
		JLabel lblEnterNewPin = new JLabel("Enter new Pin");
		lblEnterNewPin.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEnterNewPin.setBounds(22, 106, 96, 14);
		getContentPane().add(lblEnterNewPin);
		
		JLabel lblReenterPin = new JLabel("Re-enter Pin");
		lblReenterPin.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblReenterPin.setBounds(22, 142, 96, 14);
		getContentPane().add(lblReenterPin);
		
		oldpin = new JPasswordField();
		oldpin.setBounds(181, 65, 189, 20);
		getContentPane().add(oldpin);
		
		newpin = new JPasswordField();
		newpin.setBounds(181, 103, 189, 20);
		getContentPane().add(newpin);
		
		confirmnwpin = new JPasswordField();
		confirmnwpin.setBounds(181, 139, 189, 20);
		getContentPane().add(confirmnwpin);
		
		 bChangePin = new JButton("Change PIN");
		 bChangePin.setForeground(new Color(139, 0, 0));
		 bChangePin.setFont(new Font("Tahoma", Font.BOLD, 14));
		bChangePin.setBounds(53, 203, 134, 23);
		getContentPane().add(bChangePin);
		
		bChangePin.addActionListener(this);
		
		 bBack = new JButton("Back");
		 bBack.setForeground(new Color(139, 0, 0));
		 bBack.setFont(new Font("Tahoma", Font.BOLD, 14));
		bBack.addActionListener(this);
		bBack.setBounds(222, 203, 131, 23);
		getContentPane().add(bBack);
		
		ImageIcon ii= new ImageIcon(getClass().getResource("images/blue3.jpg"));
		JLabel label= new JLabel(ii);
		label.setOpaque(true);
		label.setBounds(0,0,443,332);
		getContentPane().add(label);
		
		
		setSize(459, 371);
		setVisible(true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object ob=e.getSource();
		if(ob== bChangePin)
		{
			if(newpin.getText().equals(confirmnwpin.getText()))
			{
				int newp=Integer.parseInt(confirmnwpin.getText());
			
			
			String s="update accountdetails set atmpin=? where cardnumber=?";
			
			try
			{
				Connection c=MyConnection.connect();
				PreparedStatement ps=c.prepareStatement(s);
				ps.setInt(1, newp);
				ps.setString(2, MainPage.card);
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "pin changes succesfully");
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			}
		}
		if(ob== bBack)
		{
			new UserAccount();
			dispose();
		}
			
	}

}
