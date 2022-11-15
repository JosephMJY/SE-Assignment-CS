import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class admin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin frame = new admin();
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
	public admin() throws Exception {
		setTitle("Admin System");
		Connection conn = DbConnection.database();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 431);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(274, 11, 148, 30);
		contentPane.add(dateChooser);
		java.util.Date date = new java.util.Date();
		dateChooser.setDate(date);
		
		JTextPane txtpCheck = new JTextPane();
		txtpCheck.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpCheck.setLocation(0, 103);
		txtpCheck.setEditable(false);
		contentPane.add(txtpCheck);
		
		JScrollPane jsp = new JScrollPane(txtpCheck,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setBounds(83,45,357,288);
		contentPane.add(jsp);
		JButton btnReport = new JButton("Genarate Report");
		btnReport.setForeground(new Color(255, 250, 250));
		btnReport.setBackground(new Color(70, 130, 180));
		
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtpCheck.setText("");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String cDate = sdf.format(dateChooser.getDate());
				
				LocalDate lt = LocalDate.now();
				String file_name= "C:\\Users\\leeli\\OneDrive\\Desktop\\LiRong INTI\\SE\\Visitor_Report\\Report_"+cDate+".pdf";
				Document document = new Document();
				
				
				try {
					
					PdfWriter.getInstance(document, new FileOutputStream(file_name));
					
					PreparedStatement ps = conn.prepareStatement("select* from user_request where dov = ? order by ticketId desc");
					ps.setDate(1,Date.valueOf(cDate) );
					ResultSet rs = ps.executeQuery();
					document.open();
		
					while(rs.next()) {
						
						String dateVisit = rs.getString("dov");
						System.out.println(dateVisit);
						if(dateVisit.equals(cDate)==true) {
						System.out.println("Same date");
						Paragraph para = new Paragraph(txtpCheck.getText() + "\nTicket ID:"+rs.getString("ticketId")+"\nName: "+rs.getString("name")+"\nIC:  "+rs.getString("ic")
		     			+"\nPhone No.:  "+rs.getString("phone")+"\nPurpose: "+rs.getString("purpose")+"\nTime of Visit:"+rs.getString("tov")+"\nDate of Visit: "+rs.getDate("dov")
					    +"\nEnter Time: "+rs.getString("entertime")+"\nLeave Time: "+rs.getString("leavetime")+"\nStatus: "+rs.getString("status")
						+"\nApproved By: "+rs.getString("approvedBy")+"\nEntry Time Enter By: "+rs.getString("managedEnterBy")+"\nLeave Time Enter By: "+rs.getString("managedLeaveBy")
						+"\nAbsent Enter By: "+rs.getString("managedBy")+"\n------------------------------------------------------------");
						System.out.println(para);
						txtpCheck.setText(para.toString());	
						document.add(para);
						
						
						
					}	
						
						
					}if(txtpCheck.getText()!="") {
						document.close();
					}
					
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}if(txtpCheck.getText()=="") {
					JOptionPane.showMessageDialog(null, "No record found.");
				}

				}
			
		});
		btnReport.setBounds(292, 344, 130, 37);
		contentPane.add(btnReport);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setForeground(new Color(255, 250, 250));
		btnGenerate.setBackground(new Color(70, 130, 180));
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtpCheck.setText("");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String cDate = sdf.format(dateChooser.getDate());
				String report ="";
				PreparedStatement ps;
				try {
					ps = conn.prepareStatement("select* from user_request where dov = ? order by ticketId desc");
				
				ps.setDate(1,Date.valueOf(cDate) );
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					
					String dateVisit = rs.getString("dov");
					System.out.println(dateVisit);
					if(dateVisit.equals(cDate)==true) {
					System.out.println("Same date");
					report =(txtpCheck.getText() + "\nTicket ID:"+rs.getString("ticketId")+"\nName: "+rs.getString("name")+"\nIC:  "+rs.getString("ic")
	     			+"\nPhone No.:  "+rs.getString("phone")+"\nPurpose: "+rs.getString("purpose")+"\nTime of Visit:"+rs.getString("tov")+"\nDate of Visit: "+rs.getDate("dov")
				    +"\nEnter Time: "+rs.getString("entertime")+"\nLeave Time: "+rs.getString("leavetime")+"\nStatus: "+rs.getString("status")
					+"\nApproved By: "+rs.getString("approvedBy")+"\nEntry Time Enter By: "+rs.getString("managedEnterBy")+"\nLeave Time Enter By: "+rs.getString("managedLeaveBy")
					+"\nAbsent Enter By: "+rs.getString("managedBy")+"\n------------------------------------------------------------\n");
				
					txtpCheck.setText(report);	
										
				}

					
				}
			}
			 catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}if(txtpCheck.getText()=="") {
				JOptionPane.showMessageDialog(null, "No record found.");
			}
			}
		});
		btnGenerate.setBounds(112, 344, 130, 37);
		contentPane.add(btnGenerate);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setForeground(new Color(255, 250, 250));
		btnLogOut.setBackground(new Color(70, 130, 180));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login lg = null;
				try {
					lg = new Login();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lg.setVisible(true);
			}
		});
		btnLogOut.setBounds(443, 11, 78, 30);
		contentPane.add(btnLogOut);
		
		JLabel lblAdmin = new JLabel("");
		lblAdmin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAdmin.setBounds(10, 11, 254, 30);
		contentPane.add(lblAdmin);
		String inCharge = Login.txtStaffID.getText();
		lblAdmin.setText("Welcome, "+inCharge+".");
		
		
	}
}
