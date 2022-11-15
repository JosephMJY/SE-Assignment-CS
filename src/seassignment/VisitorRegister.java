package seassignment;
import java.awt.EventQueue;
import javax.swing.*;

import Function.Data;

import java.awt.event.*;
import java.sql.*;

public class VisitorRegister implements ActionListener{
	
	public static JFrame frmRegister;
	public static JTextField txtVisitorName;
	public static JTextField txtVisitorEmail;
	public static JTextField txtVisitorPassword;
	public static JTextField txtVisitorRepeat;
	public static JButton Cancel_btn, Submit_btn;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void Registration() {	    
		frmRegister = new JFrame();
		frmRegister.setResizable(false);
		frmRegister.setTitle("Registration - New Visitors");
		frmRegister.setBounds(100, 100, 405, 313);
		frmRegister.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegister.getContentPane().setLayout(null);
		
		JLabel VisitorName_lbl = new JLabel("Name");
		VisitorName_lbl.setHorizontalAlignment(SwingConstants.LEFT);
		VisitorName_lbl.setBounds(30, 32, 46, 14);
		frmRegister.getContentPane().add(VisitorName_lbl);
		
		JLabel VisitorName_lbl_1 = new JLabel("Email");
		VisitorName_lbl_1.setHorizontalAlignment(SwingConstants.LEFT);
		VisitorName_lbl_1.setBounds(30, 72, 46, 14);
		frmRegister.getContentPane().add(VisitorName_lbl_1);
		
		JLabel VisitorName_lbl_2 = new JLabel("Password");
		VisitorName_lbl_2.setHorizontalAlignment(SwingConstants.LEFT);
		VisitorName_lbl_2.setBounds(30, 116, 66, 14);
		frmRegister.getContentPane().add(VisitorName_lbl_2);
		
		JLabel VisitorName_lbl_3 = new JLabel("<html>Repeat </br>Password</html>");
		VisitorName_lbl_3.setHorizontalAlignment(SwingConstants.LEFT);
		VisitorName_lbl_3.setBounds(30, 155, 66, 28);
		frmRegister.getContentPane().add(VisitorName_lbl_3);
		
		txtVisitorName = new JTextField();
		txtVisitorName.setBounds(109, 29, 212, 20);
		frmRegister.getContentPane().add(txtVisitorName);
		txtVisitorName.setColumns(10);
		
		txtVisitorEmail = new JTextField();
		txtVisitorEmail.setColumns(10);
		txtVisitorEmail.setBounds(109, 69, 212, 20);
		frmRegister.getContentPane().add(txtVisitorEmail);
		
		txtVisitorPassword = new JTextField();
		txtVisitorPassword.setColumns(10);
		txtVisitorPassword.setBounds(109, 113, 212, 20);
		frmRegister.getContentPane().add(txtVisitorPassword);
		
		txtVisitorRepeat = new JTextField();
		txtVisitorRepeat.setColumns(10);
		txtVisitorRepeat.setBounds(109, 155, 212, 20);
		frmRegister.getContentPane().add(txtVisitorRepeat);
		
		Cancel_btn = new JButton("Cancel");
		Cancel_btn.addActionListener(new VisitorRegister());
		Cancel_btn.setBounds(232, 240, 89, 23);
		frmRegister.getContentPane().add(Cancel_btn);
		
		Submit_btn = new JButton("Submit");
		Submit_btn.addActionListener(new VisitorRegister());
		Submit_btn.setBounds(109, 240, 89, 23);
		frmRegister.getContentPane().add(Submit_btn);
		
		JLabel RegistrationError_lbl = new JLabel("");
		RegistrationError_lbl.setEnabled(false);
		RegistrationError_lbl.setBounds(109, 186, 212, 43);
		frmRegister.getContentPane().add(RegistrationError_lbl);
		
		frmRegister.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Cancel_btn) {
			// TODO Auto-generated method stub
			frmRegister.setVisible(false);
				Appointment ap;
				try {
					ap = new Appointment();
					ap.frmMain.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		
		if (e.getSource() == Submit_btn) {
			Data.InsertToDB();
		}
	}
}




