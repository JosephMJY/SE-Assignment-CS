import java.awt.EventQueue;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class Guard extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Guard frame = new Guard();
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
	public Guard() throws Exception {
		setTitle("Guard System");
		Connection conn = DbConnection.database();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 469, 368);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		btnLogOut.setBounds(360, 13, 83, 22);
		contentPane.add(btnLogOut);
		
		JComboBox comboTicketGuard = new JComboBox();
		comboTicketGuard.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboTicketGuard.setBounds(115, 40, 125, 22);
		contentPane.add(comboTicketGuard);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.setForeground(new Color(255, 250, 250));
		btnEnter.setBackground(new Color(70, 130, 180));
		
		
		btnEnter.setBounds(53, 283, 89, 23);
		contentPane.add(btnEnter);
		btnEnter.setEnabled(false);
		JButton btnLeave = new JButton("Leave");
		btnLeave.setForeground(new Color(255, 250, 250));
		btnLeave.setBackground(new Color(70, 130, 180));
		btnLeave.setBounds(152, 283, 89, 23);
		contentPane.add(btnLeave);
		btnLeave.setEnabled(false);
		JTextPane txtGuardDetails = new JTextPane();
		txtGuardDetails.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtGuardDetails.setEditable(false);
		txtGuardDetails.setBounds(52, 73, 313, 207);
		contentPane.add(txtGuardDetails);
		String approve = "Approved";
		JButton btnDnT = new JButton("Did Not Turn Up");
		btnDnT.setForeground(new Color(255, 250, 250));
		btnDnT.setBackground(new Color(70, 130, 180));
		btnDnT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String absent = "Did Not Turn Up";
				String inCharge = Login.txtStaffID.getText();
				LocalDate lt = LocalDate.now();
				int ldth = LocalDateTime.now().getHour();
				int ldtm = LocalDateTime.now().getMinute();
				
				String handledBy = inCharge+" at "+lt+" Time: "+ldth+":"+ldtm;
				System.out.println(handledBy);
				int updateEnter = JOptionPane.showConfirmDialog(null, "Are you sure you want to enter the time?");
				//Testing
				//updateEnter=0;
				//
				if(updateEnter==0) {
				try {
					
					PreparedStatement statement = conn.prepareStatement("update user_request set entertime= ? , leavetime=? ,managedBy= ? where ticketid= ?");
					statement.setString(1, absent);
					statement.setString(2, absent);
					statement.setString(3, handledBy);
					statement.setInt(4, Integer.parseInt(comboTicketGuard.getSelectedItem().toString()));
					statement.execute();
					PreparedStatement stm = conn.prepareStatement("select ticketid from user_request where status = ? order by ticketid desc");
					stm.setString(1, approve);
					ResultSet rs = stm.executeQuery();
					btnEnter.setEnabled(false);
					btnLeave.setEnabled(false);
					btnDnT.setEnabled(false);
					comboTicketGuard.removeAllItems();
					while(rs.next()) {
						String id = rs.getString("ticketid");
						comboTicketGuard.addItem(id);
					}
					
				}
				
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			}
		});
		btnDnT.setBounds(251, 283, 114, 23);
		contentPane.add(btnDnT);
		btnDnT.setEnabled(false);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setEnabled(false);
		dateChooser.setDateFormatString("d,MM,yyyy");
		java.util.Date date = new java.util.Date();
		dateChooser.setDate(date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String cDate = sdf.format(date);
		dateChooser.setBounds(257, 40, 108, 20);
		contentPane.add(dateChooser);
		
		JLabel lblTicketID = new JLabel("Ticket ID: ");
		lblTicketID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTicketID.setBounds(35, 44, 83, 14);
		contentPane.add(lblTicketID);
		
		JLabel lblGuard = new JLabel("");
		lblGuard.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGuard.setBounds(10, 15, 180, 14);
		contentPane.add(lblGuard);
		String inCharge = Login.txtStaffID.getText();
		lblGuard.setText("Welcome, "+inCharge+".");
		
		JButton btnTestGuard = new JButton("Test Guard");
		btnTestGuard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboTicketGuard.getSelectedIndex();
				btnDnT.doClick();
				
			}
		});
		btnTestGuard.setBounds(364, 306, 89, 23);
		contentPane.add(btnTestGuard);
		
		PreparedStatement stm = conn.prepareStatement("select ticketid from user_request where status = ? AND dov = ? order by ticketid desc");
		stm.setString(1, approve);
		stm.setDate(2, Date.valueOf(cDate));
		ResultSet rs = stm.executeQuery();
		while(rs.next()) {
			String id = rs.getString("ticketid");
			comboTicketGuard.addItem(id);
		}
		if(comboTicketGuard.getItemCount()==0) {	
			comboTicketGuard.getModel().setSelectedItem("No Ticket Today.");
			btnEnter.setEnabled(false);
			btnLeave.setEnabled(false);
			txtGuardDetails.setText("No Ticket Selected.");
			}
		else {
		if(comboTicketGuard.getSelectedIndex()==0) {
			try {
				System.out.println(comboTicketGuard.getSelectedIndex()==0);
				PreparedStatement statement = conn.prepareStatement("select name,ic,phone,purpose,tov,dov,entertime,leavetime from user_request where ticketid = ?");
				statement.setString(1, comboTicketGuard.getSelectedItem().toString());  
				ResultSet  rsd = statement.executeQuery();
				
				
				String details = "";
				while(rsd.next()) {
					String name = rsd.getString("name");
					String ic = rsd.getString("ic");
					String phone = rsd.getString("phone");
					String purpose = rsd.getString("purpose");
					String tov = rsd.getString("tov");
					String dov = rsd.getString("dov");
					String entertime = rsd.getString("entertime");
					String leavetime = rsd.getString("leavetime");
					
					if(rsd.getString("entertime")==null) {
						btnLeave.setEnabled(false);
						btnEnter.setEnabled(true);
						btnDnT.setEnabled(true);
						entertime = rsd.getString("entertime");
						leavetime = rsd.getString("leavetime");
					}
					if(rsd.getString("entertime")!=null&& rsd.getString("leavetime")==null) {
						btnLeave.setEnabled(true);
						btnEnter.setEnabled(false);
						btnDnT.setEnabled(false);
						entertime = rsd.getString("entertime");
						leavetime = rsd.getString("leavetime");
					}
					
					
					 details = "Name: "+name +"\n"+"IC: " +ic +"\n"+"Phone Num: " +phone +"\n"+"Purpose of Visit : " +purpose +"\n"+"Time of Visit : " +tov +"\n"+"Date of Visit : " +dov +"\nEnter Time:" +entertime+ "\nLeave Time:"+leavetime  ;					 
				}
				
				txtGuardDetails.setText(details);
				
			}
			catch (Exception e1) {
			// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		}
		comboTicketGuard.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					
				if (comboTicketGuard.getSelectedItem()!=null) {
					PreparedStatement statement = conn.prepareStatement("select name,ic,phone,purpose,tov,dov,entertime,leavetime from user_request where ticketid = ?");
					statement.setString(1, comboTicketGuard.getSelectedItem().toString());  
					ResultSet  rs = statement.executeQuery();
					
					
					String details = "";
					while(rs.next()) {
						String name = rs.getString("name");
						String ic = rs.getString("ic");
						String phone = rs.getString("phone");
						String purpose = rs.getString("purpose");
						String tov = rs.getString("tov");
						String dov = rs.getString("dov");
						String entertime = rs.getString("entertime");
						String leavetime = rs.getString("leavetime");
						if(rs.getString("entertime")== null) {
							btnLeave.setEnabled(false);
							btnEnter.setEnabled(true);
							btnDnT.setEnabled(true);
							entertime = rs.getString("entertime");
							leavetime = rs.getString("leavetime");
						}
						if(rs.getString("entertime")!=null&& rs.getString("leavetime")==null) {
							btnLeave.setEnabled(true);
							btnEnter.setEnabled(false);
							btnDnT.setEnabled(false);
							entertime = rs.getString("entertime");
							leavetime = rs.getString("leavetime");
						}
						
							
						
						 details = "Name: "+name +"\n"+"IC: " +ic +"\n"+"Phone Num: " +phone +"\n"+"Purpose of Visit : " +purpose +"\n"+"Time of Visit : " +tov +"\n"+"Date of Visit : " +dov +"\nEnter Time:" +entertime+ "\nLeave Time:"+leavetime  ;
					}
					
					txtGuardDetails.setText(details);
				}
				
			} catch (Exception e1) {
			// TODO Auto-generated catch block
				e1.printStackTrace();
			}				
			}
		});
		btnEnter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String inCharge = Login.txtStaffID.getText();
				LocalDate lt = LocalDate.now();
				int ldth = LocalDateTime.now().getHour();
				int ldtm = LocalDateTime.now().getMinute();
				String handledBy = inCharge+" at "+lt+" Time: "+ldth+":"+ldtm;
				System.out.println(handledBy);
				int getEnterHour= LocalDateTime.now().getHour();
				int getEnterMinute = LocalDateTime.now().getMinute();
				String getEnterTime = getEnterHour+":"+getEnterMinute;
				System.out.println(getEnterTime);
				int updateEnter = JOptionPane.showConfirmDialog(null, "Are you sure you want to enter the time?");
				if(updateEnter==0) {
				try {
					
					PreparedStatement statement = conn.prepareStatement("update user_request set entertime= ? ,managedEnterBy = ? where ticketid= ?");
					statement.setString(1, getEnterTime);
					statement.setString(2, handledBy);
					statement.setInt(3, Integer.parseInt(comboTicketGuard.getSelectedItem().toString()));
					statement.execute();
					PreparedStatement stm = conn.prepareStatement("select ticketid from user_request where status = ? AND dov = ? order by ticketid desc");
					stm.setString(1, approve);
					stm.setDate(2, Date.valueOf(cDate));
					ResultSet rs = stm.executeQuery();
					comboTicketGuard.removeAllItems();
					while(rs.next()) {
						String id = rs.getString("ticketid");
						comboTicketGuard.addItem(id);
					}
					
				}
				
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			}
		});
		btnLeave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String inCharge = Login.txtStaffID.getText();
				LocalDate lt = LocalDate.now();
				int ldth = LocalDateTime.now().getHour();
				int ldtm = LocalDateTime.now().getMinute();
				
				String handledBy = inCharge+" at "+lt+" Time: "+ldth+":"+ldtm;
				System.out.println(handledBy);
				int getLeaveHour= LocalDateTime.now().getHour();
				int getLeaveMinute = LocalDateTime.now().getMinute();
				String getLeaveTime = getLeaveHour+":"+getLeaveMinute;
				System.out.println(getLeaveTime);
				int updateLeave = JOptionPane.showConfirmDialog(null, "Are you sure you want to enter the time?");
				if(updateLeave==0) {
				try {
					
					PreparedStatement statement = conn.prepareStatement("update user_request set leavetime= ? ,managedLeaveBy = ? where ticketid= ?");
					statement.setString(1, getLeaveTime);
					statement.setString(2, handledBy);
					statement.setInt(3, Integer.parseInt(comboTicketGuard.getSelectedItem().toString()));
					statement.execute();
					PreparedStatement stm = conn.prepareStatement("select ticketid from user_request where status = ? AND dov = ? order by ticketid desc");
					stm.setString(1, approve);
					stm.setDate(2, Date.valueOf(cDate));
					ResultSet rs = stm.executeQuery();
					btnLeave.setEnabled(false);
					comboTicketGuard.removeAllItems();;
					while(rs.next()) {
						String id = rs.getString("ticketid");
						comboTicketGuard.addItem(id);
					}
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			}
		});
		//Testing
		//btnTestGuard.doClick();
		//line 120-121 for testing
		btnTestGuard.setVisible(false);
		

	}
}
