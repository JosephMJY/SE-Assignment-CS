import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.mysql.jdbc.*;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.SystemColor;

public class visitor extends JFrame {

	private JPanel contentPane;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					visitor frame = new visitor();

					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public visitor() throws Exception {
		setTitle("Visitor Homepage");
		
		Connection conn = DbConnection.database();
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 327);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton btnVisitor_Apply = new JButton("Apply For Ticket");
		btnVisitor_Apply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				visitor_request VR = null;
				try {
					VR = new visitor_request();
					System.out.println("Visitor Test2 Successful");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				VR.setVisible(true);
				
			}
		});
		btnVisitor_Apply.setForeground(new Color(255, 250, 250));
		JLabel lblTitle = new JLabel("Request For Visit Here...");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitle.setBounds(116, 11, 227, 38);
		contentPane.add(lblTitle);
		JButton btnTestVisitor = new JButton("TestVisitor");
		

		
		btnTestVisitor.setBounds(335, 60, 89, 23);
		contentPane.add(btnTestVisitor);
		
	
		btnVisitor_Apply.setBackground(new Color(70, 130, 180));
		btnVisitor_Apply.setBounds(126, 62, 199, 77);
		contentPane.add(btnVisitor_Apply);
		btnTestVisitor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnVisitor_Apply.doClick();
				System.out.println("BtnTestVisitor Clicked.");
			}
		});
		JButton btnCheck = new JButton(" Check Ticket Status");
		btnCheck.setForeground(new Color(255, 250, 250));
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Check ch = null;
				try {
					ch = new Check();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ch.setVisible(true);
			}
		});
		btnCheck.setBackground(new Color(70, 130, 180));
		btnCheck.setBounds(126, 150, 199, 77);
		contentPane.add(btnCheck);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setForeground(new Color(255, 250, 250));
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
			}
		});
		btnExit.setBackground(new Color(70, 130, 180));
		btnExit.setBounds(177, 254, 89, 23);
		contentPane.add(btnExit);
		
		JButton btnTestVisitorCheck = new JButton("Test Visitor Check");
		btnTestVisitorCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCheck.doClick();
				
			}
		});
		btnTestVisitorCheck.setBounds(335, 150, 89, 23);
		contentPane.add(btnTestVisitorCheck);
		
		
		//btnTestVisitor.doClick();
		//btnTestVisitorCheck.doClick();
		btnTestVisitor.setVisible(false);
		btnTestVisitorCheck.setVisible(false);
		
	}
	
	
	
}

