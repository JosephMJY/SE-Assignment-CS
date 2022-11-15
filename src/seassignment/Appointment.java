package seassignment;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Color;

import javax.swing.SwingConstants;

import Function.Data;

import javax.swing.JPanel;
import java.awt.event.*;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Component;

public class Appointment {

	public static JFrame frmMain;
	public static JTextField txtStaffID, txtVisitorID;
	public static JButton StaffLogin_btn, VisitorLogin_btn, RegisterVisitor_btn;
	public static JPasswordField passStaff, passVisitor;

	/**
	 * Create the application.Initialize the contents of the frame.
	 */	
	public Appointment() throws Exception{
		//initialize();
		frmMain = new JFrame();
		frmMain.setResizable(false);
		frmMain.setTitle("Appointment");
		frmMain.setBounds(100, 100, 776, 481);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMain.getContentPane().setLayout(null);
		
		JLabel StaffLogin_lbl = new JLabel("Staff Login");
		StaffLogin_lbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		StaffLogin_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		StaffLogin_lbl.setBounds(161, 106, 89, 27);
		frmMain.getContentPane().add(StaffLogin_lbl);
		
		JLabel StaffID_lbl = new JLabel("Staff ID");
		StaffID_lbl.setBounds(33, 169, 46, 14);
		frmMain.getContentPane().add(StaffID_lbl);
		
		JLabel StaffPass_lbl = new JLabel("Password");
		StaffPass_lbl.setBounds(33, 220, 70, 14);
		frmMain.getContentPane().add(StaffPass_lbl);
		
		txtStaffID = new JTextField();
		txtStaffID.setBounds(113, 166, 194, 20);
		frmMain.getContentPane().add(txtStaffID);
		txtStaffID.setColumns(10);
		
		passStaff = new JPasswordField();
		passStaff.setBounds(113, 217, 194, 20);
		frmMain.getContentPane().add(passStaff);
		
		StaffLogin_btn = new JButton("Login");
		StaffLogin_btn.addActionListener(new TempListener());
		StaffLogin_btn.setBounds(161, 283, 89, 23);
		frmMain.getContentPane().add(StaffLogin_btn);
		
		JPanel Separater = new JPanel();
		Separater.setBackground(Color.BLACK);
		Separater.setBounds(371, 11, 2, 420);
		frmMain.getContentPane().add(Separater);
		
		JLabel VisitorLogin_lbl = new JLabel("Visitor Login");
		VisitorLogin_lbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		VisitorLogin_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		VisitorLogin_lbl.setBounds(555, 106, 89, 27);
		frmMain.getContentPane().add(VisitorLogin_lbl);
		
		JLabel VisitorID_lbl = new JLabel("Email");
		VisitorID_lbl.setBounds(427, 163, 46, 27);
		frmMain.getContentPane().add(VisitorID_lbl);
		
		JLabel VisitorPass_lbl = new JLabel("Password");
		VisitorPass_lbl.setBounds(427, 220, 70, 14);
		frmMain.getContentPane().add(VisitorPass_lbl);
		
		txtVisitorID = new JTextField();
		txtVisitorID.setToolTipText("");
		txtVisitorID.setColumns(10);
		txtVisitorID.setBounds(507, 166, 194, 20);
		frmMain.getContentPane().add(txtVisitorID);
		
		passVisitor = new JPasswordField();
		passVisitor.setBounds(507, 217, 194, 20);
		frmMain.getContentPane().add(passVisitor);
		
		VisitorLogin_btn = new JButton("Login");
		VisitorLogin_btn.addActionListener(new TempListener());
		VisitorLogin_btn.setBounds(507, 283, 73, 23);
		frmMain.getContentPane().add(VisitorLogin_btn);
		
		RegisterVisitor_btn = new JButton("New Visitor");
		RegisterVisitor_btn.addActionListener(new TempListener());
		RegisterVisitor_btn.setBounds(590, 283, 110, 23);
		frmMain.getContentPane().add(RegisterVisitor_btn);
				
		frmMain.setVisible(true);
	}
	
	//Actions for the corresponding actions
	public class TempListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Opens window to register Visitors
			if (e.getSource() == RegisterVisitor_btn) {
				frmMain.setVisible(false);
				VisitorRegister.Registration();
			}
			
			if (e.getSource() == StaffLogin_btn) {
				Data.SearchStaff();
			}
			
			if (e.getSource() == VisitorLogin_btn) {
				Data.searchVisitor();
			}
		}
		
	}
		
	/**
	 * Launch the application.
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		new Appointment();
	}
}
