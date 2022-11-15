package Function;

import javax.swing.JOptionPane;
import java.sql.*;
import java.util.Random;

import seassignment.*;

public class Data {
	static ResultSet staff, staffPass, visitor, visitorPass;
	
	
	//Insert new Visitor details
	public static void InsertToDB() {
		try {
			
			DataCon.insertData("INSERT INTO testing.visitor(VisitorId, VisitorPass, VisitorEmail) VALUES(200,'"+ 
					VisitorRegister.txtVisitorPassword.getText()+"','"+ VisitorRegister.txtVisitorEmail.getText() +"')");
			
			 VisitorRegister.frmRegister.setVisible(false);
			 Appointment.frmMain.setVisible(true);
			 
			JOptionPane.showMessageDialog(null, "Account Created");
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Credentials Provided have Existed");
		}
	}
	
	//< Login Function for Staff >Search Database for Staff 
	public static void SearchStaff() {
		String passStaffString = String.valueOf(Appointment.passStaff.getPassword());
		String staffR = " ";
		
		try {
			staff = DataCon.searchData("SELECT * FROM testing.staff WHERE staffID = '"+ Appointment.txtStaffID.getText() +"'" );
			staffPass = DataCon.searchData("SELECT * FROM testing.staff WHERE staffPass = '"+ passStaffString +"'");
			
			//loop to display window according to staff role
			if (staff.next() && staffPass.next()) {		
				Appointment.frmMain.setVisible(false);
				staffR = staff.getString("staffType");
				
				if (staffR.equals("officer") || staffR.equals("Officer")) {
					Officer.showMenu();
					
				} else if (staffR.equals("guard")) {
					Guard.showMenu();
					
				} else if (staffR.equals("admin")) {
					Admin.showMenu();
				}
			} else {
				
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public static void searchVisitor() {
		//String passVisitorString = String.valueOf(Appointment.passVisitor.getPassword());
		try {
			visitor = DataCon.searchData("SELECT * FROM testing.visitor WHERE VisitorEmail = '"+ Appointment.txtVisitorID.getText() +"'" );
			visitorPass = DataCon.searchData("SELECT * FROM testing.visitor WHERE VisitorPass = '"+ String.valueOf(Appointment.passVisitor.getPassword()) +"'" );
			
			if (visitor.next() && visitorPass.next()) {
				Appointment.frmMain.setVisible(false);
				Visitor.showMenu();
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
