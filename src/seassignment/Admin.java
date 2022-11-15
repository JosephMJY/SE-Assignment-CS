package seassignment;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Admin implements ActionListener{

	public static JFrame frmAdmin;
	public static JPanel contentPane;

	/**
	 * Launch the application.
	 * @wbp.parser.entryPoint
	 */
	public static void showMenu(){
		frmAdmin = new JFrame();
		frmAdmin.setResizable(false);
		frmAdmin.setTitle("Admin");
		frmAdmin.setBounds(100, 100, 405, 313);
		frmAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdmin.getContentPane().setLayout(null);
		
		frmAdmin.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}