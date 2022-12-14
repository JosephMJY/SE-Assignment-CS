import java.awt.EventQueue;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;

public class Officer extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Officer frame = new Officer();
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
	public Officer() throws Exception {
		setTitle("Officer System");
		Connection conn = DbConnection.database();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 446, 358);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		
		JTextPane txtDetails = new JTextPane();
		txtDetails.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtDetails.setBounds(59, 69, 291, 187);
		contentPane.add(txtDetails);
		txtDetails.setEditable(false);
		
		
		JButton btnApprove = new JButton("Approve");
		btnApprove.setForeground(Color.GREEN);
		
		btnApprove.setBackground(new Color(255, 250, 250));
		btnApprove.setBounds(86, 267, 89, 23);
		contentPane.add(btnApprove);
		
		JButton btnReject = new JButton("Reject");
		btnReject.setForeground(Color.RED);
		
		btnReject.setBackground(new Color(255, 250, 250));
		btnReject.setBounds(218, 267, 89, 23);
		contentPane.add(btnReject);
		JComboBox comboTicket = new JComboBox();
		comboTicket.setFont(new Font("Tahoma", Font.PLAIN, 15));
	
		
		comboTicket.setBounds(231, 36, 119, 22);
		contentPane.add(comboTicket);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setForeground(new Color(255, 250, 250));
		btnLogOut.setBackground(new Color(70, 130, 180));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
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
		btnLogOut.setBounds(331, 9, 89, 23);
		contentPane.add(btnLogOut);
		
		JLabel lblID = new JLabel("Ticket ID:");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblID.setBounds(160, 40, 72, 14);
		contentPane.add(lblID);
		
		JComboBox comboStatus = new JComboBox();
		comboStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboStatus.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				PreparedStatement stm;
				comboTicket.removeAllItems();
				
					try {
						
						stm = conn.prepareStatement("select ticketid from user_request where status = ? order by ticketid desc");
						stm.setString(1,comboStatus.getSelectedItem().toString());
					ResultSet rs = stm.executeQuery();
					while(rs.next()) {
						String id = rs.getString("ticketid");
						comboTicket.addItem(id);
					}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					
				}if(comboTicket.getItemCount()==0) {	
					comboTicket.getModel().setSelectedItem("No New Ticket");
					btnApprove.setEnabled(false);
					btnReject.setEnabled(false);
					txtDetails.setText("No Ticket Selected.");
					}
			}
		});
		
		PreparedStatement stm;
		try {
			
			stm = conn.prepareStatement("select ticketid from user_request where status = ? order by ticketid desc");
			stm.setString(1,"Pending");
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
			String id = rs.getString("ticketid");
			comboTicket.addItem(id);
			}
			
			} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		
	}	
			
			
			
		comboStatus.setModel(new DefaultComboBoxModel(new String[] {"Pending", "Approved", "Rejected"}));
		comboStatus.setBounds(59, 36, 91, 23);
		contentPane.add(comboStatus);
		
		JLabel lblOfficer = new JLabel("New label");
		lblOfficer.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblOfficer.setBounds(10, 11, 201, 14);
		contentPane.add(lblOfficer);
		String inCharge = Login.txtStaffID.getText();
		lblOfficer.setText("Welcome, "+inCharge+".");
		
		JButton btnTestOfficer = new JButton("Test Officer");
		btnTestOfficer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnApprove.doClick();
				
			}
		});
		btnTestOfficer.setBounds(341, 296, 89, 23);
		contentPane.add(btnTestOfficer);
		if(comboTicket.getItemCount()==0) {	
		comboTicket.getModel().setSelectedItem("No New Ticket");
		btnApprove.setEnabled(false);
		btnReject.setEnabled(false);
		txtDetails.setText("No Ticket Selected.");
		}
		else {
		if(comboTicket.getSelectedIndex()==0) {
			try {
				System.out.println(comboTicket.getSelectedIndex()==0);
				PreparedStatement statement = conn.prepareStatement("select name,ic,phone,purpose,tov,dov,status from user_request where ticketid = ?");
				statement.setString(1, comboTicket.getSelectedItem().toString());  
				ResultSet  rsd = statement.executeQuery();
				
				
				String details = "";
				while(rsd.next()) {
					String name = rsd.getString("name");
					String ic = rsd.getString("ic");
					String phone = rsd.getString("phone");
					String purpose = rsd.getString("purpose");
					String tov = rsd.getString("tov");
					String dov = rsd.getString("dov");
					String status = rsd.getString("status");
					details = name +"\n" +ic +"\n" +phone +"\n" +purpose +"\n" +tov +"\n" +dov +"\n"+status ;
					
					if(status.equals("Pending")) {
						btnApprove.setEnabled(true);
						btnReject.setEnabled(true);
					}
					else {
						btnApprove.setEnabled(false);
						btnReject.setEnabled(false);
					}
				}
				
				txtDetails.setText(details);
			}
			catch (Exception e1) {
			// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		}
		
		

		comboTicket.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
					
				if (comboTicket.getItemCount()!=0&&comboTicket.getSelectedItem()!=null) {
					try {
					System.out.println(comboTicket.getSelectedIndex()==0);
					PreparedStatement statement = conn.prepareStatement("select name,ic,phone,purpose,tov,dov,status from user_request where ticketid = ?");
					statement.setString(1, comboTicket.getSelectedItem().toString());  
					ResultSet  rs = statement.executeQuery();
					
					
					String details = "";
					while(rs.next()) {
						String name = rs.getString("name");
						String ic = rs.getString("ic");
						String phone = rs.getString("phone");
						String purpose = rs.getString("purpose");
						String tov = rs.getString("tov");
						String dov = rs.getString("dov");
						String status = rs.getString("status");
						if(status.equals("Pending")) {
							btnApprove.setEnabled(true);
							btnReject.setEnabled(true);
						}
						else {
							btnApprove.setEnabled(false);
							btnReject.setEnabled(false);
						}
						details = name +"\n" +ic +"\n" +phone +"\n" +purpose +"\n" +tov +"\n" +dov +"\n" +status +"\n";
						
					}
					
					txtDetails.setText(details);
				}
				catch (Exception e1) {
				// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				
				
			}
		});
		btnApprove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					String approve = "Approved";
					String inCharge = Login.txtStaffID.getText();
					LocalDate lt = LocalDate.now();
					int ldth = LocalDateTime.now().getHour();
					int ldtm = LocalDateTime.now().getMinute();
					
					String handledBy = inCharge+" at "+lt+" Time: "+ldth+":"+ldtm;
					System.out.println(handledBy);
					int updateApprove = JOptionPane.showConfirmDialog(null, "Confirm Approve?");
					
					//Testing
					//updateApprove=0;
				  
					if(updateApprove==0) {
				try {
					PreparedStatement statement = conn.prepareStatement("update user_request set status = ? , approvedBy = ? where ticketid = ?");
					statement.setString(1, approve);
					statement.setString(2, handledBy);
					statement.setInt(3, Integer.parseInt(comboTicket.getSelectedItem().toString()));
					statement.execute();
					PreparedStatement stm= conn.prepareStatement("select ticketid from user_request where status = ? order by ticketid desc");
					stm.setString(1, comboStatus.getSelectedItem().toString());
					ResultSet rs = stm.executeQuery();
					txtDetails.setText("");
					comboTicket.removeItem(comboTicket.getSelectedItem().toString());
					comboTicket.removeAllItems();
					while(rs.next()) {
						String id = rs.getString("ticketid");
						comboTicket.addItem(id);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					}
			}
		});
		btnReject.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String reject= "Rejected";
				String inCharge = Login.txtStaffID.getText();
				LocalDate lt = LocalDate.now();
				int ldth = LocalDateTime.now().getHour();
				int ldtm = LocalDateTime.now().getMinute();
				
				String handledBy = inCharge+" at "+lt+" Time: "+ldth+":"+ldtm;
				System.out.println(handledBy);
				String reason =JOptionPane.showInputDialog(null,"Reason for reject") ;
				
				if(reason!=null) {
				try {
					PreparedStatement statement = conn.prepareStatement("update user_request set status = ? , reason = ? , approvedBy  = ? where ticketid = ?");
					statement.setString(1, reject);
					statement.setString(2, reason);
					statement.setString(3, handledBy);
					statement.setInt(4, Integer.parseInt(comboTicket.getSelectedItem().toString()));
					statement.execute();
					
					PreparedStatement stm= conn.prepareStatement("select ticketid from user_request where status = ? order by ticketid desc");
					stm.setString(1, comboStatus.getSelectedItem().toString());
					ResultSet rs = stm.executeQuery();
					txtDetails.setText("");
					comboTicket.removeItem(comboTicket.getSelectedItem().toString());
					comboTicket.removeAllItems();
					while(rs.next()) {
						String id = rs.getString("ticketid");
						comboTicket.addItem(id);
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			}
		});
		//Testing Done
		//btnTestOfficer.doClick();
		//line 279-280
		btnTestOfficer.setVisible(false);
	}
}
