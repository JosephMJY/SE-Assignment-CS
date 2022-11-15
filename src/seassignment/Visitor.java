package seassignment;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class Visitor implements ActionListener{

	public static JFrame frmVisitor;
	public static JPanel contentPane;

	/**
	 * Launch the application.
	 * @wbp.parser.entryPoint
	 */
	public static void showMenu(){
		frmVisitor = new JFrame();
		frmVisitor.setResizable(false);
		frmVisitor.setTitle("Visitor");
		frmVisitor.setBounds(100, 100, 405, 313);
		frmVisitor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVisitor.getContentPane().setLayout(null);
		
		frmVisitor.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		}
		// TODO Auto-generated method stub
		
}