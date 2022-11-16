import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class VisitorCheck extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisitorCheck frame = new VisitorCheck();
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
	public VisitorCheck() throws Exception {
		setTitle("Check Ticket ");
		Connection conn = DbConnection.database();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 423, 425);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtSearch = new JTextField();
		txtSearch.setForeground(new Color(153, 153, 153));
		txtSearch.setText("Enter IC Number To Check Request.");
		txtSearch.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtSearch.getText().equals("Enter IC Number To Check Request.")) {
					txtSearch.setText("");
					txtSearch.setForeground(new Color(0,0,0));
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtSearch.getText().equals("")) {
					txtSearch.setText("Enter IC Number To Check Request.");
					txtSearch.setForeground(new Color(153,153,153));
				}
			}
		});
		
		txtSearch.setBounds(56, 23, 205, 32);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		JTextPane txtpCheck = new JTextPane();
		txtpCheck.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtpCheck.setLocation(0, 103);
		txtpCheck.setEditable(false);
		contentPane.add(txtpCheck);
		
		JScrollPane jsp = new JScrollPane(txtpCheck,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setBounds(57,56,295,241);
		contentPane.add(jsp);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setForeground(new Color(255, 250, 250));
		btnSearch.setBackground(new Color(70, 130, 180));
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtpCheck.setText("");
				String ic = txtSearch.getText();
				String details = "";
				try {
					PreparedStatement stm = conn.prepareStatement("select ticketid,status,tov,dov from user_request where ic = ? ");
					stm.setString(1, ic);
					ResultSet rs = stm.executeQuery();
					while(rs.next()) {
						String id = rs.getString("ticketid");
						String status = rs.getString("status");
						String tov = rs.getString("tov");
						String dov = rs.getString("dov");
						
						details = txtpCheck.getText()+"Ticket ID: " + id +"\n"+"Time of Visit: "+tov +"\n" +"Date of Time: "+dov +"\n"+"Status: "+status+"\n"+"-----------\n ";
						txtpCheck.setText(details);
					}
					if (txtpCheck.getText() == "" ) {
						JOptionPane.showMessageDialog(null, "No Ticket Found.");
						txtpCheck.setText("Not Found");
					}
					
				} 
				 
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnSearch.setBounds(263, 23, 89, 32);
		contentPane.add(btnSearch);
		
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(new Color(255, 250, 250));
		btnBack.setBackground(new Color(70, 130, 180));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				visitor vs = null;
				try {
					vs = new visitor();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				vs.setVisible(true);
			}
		});
		btnBack.setBounds(174, 319, 89, 32);
		contentPane.add(btnBack);
		
		JButton btnTestVisitorCheck = new JButton("Test Visitor Check");
		btnTestVisitorCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSearch.setText("123");
				btnSearch.doClick();
			}
		});
		btnTestVisitorCheck.setBounds(161, 362, 135, 23);
		contentPane.add(btnTestVisitorCheck);
		//btnTestVisitorCheck.doClick();
		
	}
}