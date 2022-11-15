import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class Login extends JFrame {

	private JPanel contentPane;
	static JTextField txtStaffID;
	private JPasswordField staffpass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() throws Exception {
		setTitle("Visitor Management System");
		Connection conn = DbConnection.database();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtStaffID = new JTextField();
		txtStaffID.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtStaffID.setBounds(163, 69, 148, 23);
		contentPane.add(txtStaffID);
		txtStaffID.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setForeground(new Color(255, 250, 250));
		btnNewButton.setBackground(new Color(70, 130, 180));
		
		btnNewButton.setBounds(194, 159, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Not Staff? Click to Request For Visit...");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.setForeground(new Color(255, 250, 250));
		btnNewButton_1.setBackground(new Color(70, 130, 180));
		btnNewButton_1.addActionListener(new ActionListener() {
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
		btnNewButton_1.setBounds(80, 205, 306, 45);
		contentPane.add(btnNewButton_1);
		
		JLabel lblID = new JLabel("Staff ID: ");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblID.setBounds(75, 72, 63, 14);
		contentPane.add(lblID);
		
		JLabel lblPass = new JLabel("Password: ");
		lblPass.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPass.setBounds(75, 118, 78, 14);
		contentPane.add(lblPass);
		
		staffpass = new JPasswordField();
		staffpass.setFont(new Font("Tahoma", Font.PLAIN, 13));
		staffpass.setBounds(163, 115, 148, 23);
		contentPane.add(staffpass);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sID = txtStaffID.getText();
				String sPass = String.valueOf(staffpass.getPassword());
				String gst = "";
				Boolean chk = false;
				System.out.println(sPass);
				try {
					
					PreparedStatement stm = conn.prepareStatement("select staffType from staff_login where staffID = ? AND staffPass = ?");
					stm.setString(1, sID);
					stm.setString(2, sPass.toString());
					ResultSet rs = stm.executeQuery();
					while(rs.next()) {
						String st = rs.getString("staffType");
						
						
					
						if(st.equals("Officer")) {
							chk =true;
							dispose();
							Officer ofc = null;
							try {
								ofc = new Officer();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							ofc.setVisible(true);
						}
						if(st.equals("Guard")) {
							chk=true;
							dispose();
							Guard grd = null;
							try {
								grd = new Guard();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							grd.setVisible(true);
						}
						if(st.equals("Admin")) {
							chk=true;
							dispose();
							admin ad = null;
							try {
								ad = new admin();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							ad.setVisible(true);
						}
						
				
					}if(chk==false){
						System.out.println("No acc");
						JOptionPane.showMessageDialog(null, "Account Not Found.");
					}
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}
}
