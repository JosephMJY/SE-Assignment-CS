package seassignment;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Guard implements ActionListener{

	public static JFrame frmGuard;
	public static JPanel contentPane;

	/**
	 * Launch the application.
	 * @wbp.parser.entryPoint
	 */
	public static void showMenu(){
		frmGuard = new JFrame();
		frmGuard.setResizable(false);
		frmGuard.setTitle("Guard");
		frmGuard.setBounds(100, 100, 405, 313);
		frmGuard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGuard.getContentPane().setLayout(null);
		
		frmGuard.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}