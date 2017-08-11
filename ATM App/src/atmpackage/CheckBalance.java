package atmpackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class CheckBalance extends JDialog implements ActionListener{

	public CheckBalance() 
	{
		getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("username");
		lblUsername.setBounds(357, 11, 67, 14);
		getContentPane().add(lblUsername);
	
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
