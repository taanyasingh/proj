package atmpackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JButton;

public class Welcome extends JDialog implements ActionListener {
	JButton Enter;

	public Welcome() 
	{
		getContentPane().setLayout(null);
		 Enter = new JButton("ENTER");
		 Enter.setFont(new Font("Tahoma", Font.BOLD, 24));
			Enter.setBounds(233, 94, 145, 23);
			getContentPane().add(Enter);
		
		ImageIcon ii= new ImageIcon(getClass().getResource("images/welcom2.png"));
		JLabel label= new JLabel(ii);
		label.setOpaque(true);
		label.setBounds(0,11,594,376);
		getContentPane().add(label);
		
		Enter.addActionListener(this);
		
		
		setSize(600, 437);
		setVisible(true);
		
	}

	public static void main(String[] args) {
		new Welcome();



	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object ob=e.getSource();
		if(ob==Enter)
		{
			new MainPage();
			dispose();
		}

		
	}
}
