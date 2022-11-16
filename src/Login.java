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
		setBounds(100, 100, 487, 346);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtStaffID = new JTextField();
		txtStaffID.setBounds(163, 69, 148, 23);
		txtStaffID.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(txtStaffID);
		txtStaffID.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(194, 159, 89, 23);
		btnLogin.setForeground(new Color(255, 250, 250));
		btnLogin.setBackground(new Color(70, 130, 180));
		contentPane.add(btnLogin);
		
		JButton btnVisitor = new JButton("Not Staff? Click to Request For Visit...");
		btnVisitor.setBounds(80, 205, 306, 45);
		btnVisitor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVisitor.setForeground(new Color(255, 250, 250));
		btnVisitor.setBackground(new Color(70, 130, 180));
		btnVisitor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				visitor vs = null;
				try {
					vs = new visitor();
					System.out.println("Visitor Test Successful");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				vs.setVisible(true);
				}
			
		});
		contentPane.add(btnVisitor);
		
		JLabel lblID = new JLabel("Staff ID: ");
		lblID.setBounds(75, 72, 63, 14);
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblID);
		
		JLabel lblPass = new JLabel("Password: ");
		lblPass.setBounds(75, 118, 78, 14);
		lblPass.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblPass);
		
		staffpass = new JPasswordField();
		staffpass.setBounds(163, 115, 148, 23);
		staffpass.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(staffpass);
		
		JButton btnTestGuard = new JButton("Test Guard");
		btnTestGuard.setBounds(307, 261, 89, 23);
		contentPane.add(btnTestGuard);
		
		JButton btnTestOfficer = new JButton("Test Officer");
		btnTestOfficer.setBounds(109, 261, 89, 23);
		btnTestOfficer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtStaffID.setText("SO1");
				staffpass.setText("123456");
				
				btnLogin.doClick();
				System.out.println("Btn Officer Clicked.");
			}
		});
		contentPane.add(btnTestOfficer);
		
		JButton btnTestVisitor = new JButton("Test Visitor");
		btnTestVisitor.setBounds(10, 261, 89, 23);
		btnTestVisitor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnVisitor.doClick();
			}
		});
		contentPane.add(btnTestVisitor);
		
		JButton btnTestVisitorCheck = new JButton("Test Check");
		btnTestVisitorCheck.setBounds(208, 261, 89, 23);
		btnTestVisitorCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnVisitor.doClick();
			}
		});
		contentPane.add(btnTestVisitorCheck);
		
		JButton btnTestAdmin = new JButton("Test Admin");
		btnTestAdmin.setBounds(402, 261, 89, 23);
		btnTestAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtStaffID.setText("ADMIN");
				staffpass.setText("123456789");
				
				btnLogin.doClick();
				System.out.println("Btn Admin Clicked.");
			}
		});
		contentPane.add(btnTestAdmin);
		
		JButton btnTestLoginInvalid = new JButton("Test Invalid Login");
		btnTestLoginInvalid.setBounds(237, 284, 89, 23);
		btnTestLoginInvalid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtStaffID.setText("123");
				staffpass.setText("123");
				
				btnLogin.doClick();
				System.out.println("Btn Invalid Login Clicked.");
			}
		});
		contentPane.add(btnTestLoginInvalid);
		
		btnTestGuard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtStaffID.setText("GRD1");
				staffpass.setText("123");
				
				btnLogin.doClick();
				System.out.println("Btn Guard Clicked.");
			}
		});
		
		
		btnLogin.addActionListener(new ActionListener() {
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
								System.out.println("Officer Test Successful");
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
								System.out.println("Guard Test Successful");
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
								System.out.println("Admin Test Successful.");
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
		//btnTestVisitor.doClick();
		//btnTestOfficer.doClick();
		//btnTestVisitorCheck.doClick();
		//btnTestGuard.doClick();
		//btnTestAdmin.doClick();
		//btnTestLoginInvalid.doClick();
		btnTestVisitor.setVisible(false);
		btnTestOfficer.setVisible(false);
		btnTestVisitorCheck.setVisible(false);
		btnTestGuard.setVisible(false);
		btnTestAdmin.setVisible(false);
		btnTestLoginInvalid.setVisible(false);
		
	}
}
