package seassignment;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Officer implements ActionListener{

	public static JFrame frmOfficer;
	public static JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void showMenu(){
		frmOfficer = new JFrame();
		frmOfficer.setResizable(false);
		frmOfficer.setTitle("Officer");
		frmOfficer.setBounds(100, 100, 405, 313);
		frmOfficer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOfficer.getContentPane().setLayout(null);
		
		frmOfficer.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
