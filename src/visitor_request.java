import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DateFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.regex.Pattern;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;
import javax.swing.JSeparator;
import com.toedter.calendar.JMonthChooser;
import java.awt.SystemColor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextPane;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;

import java.awt.Font;
import javax.swing.JSpinner;

public class visitor_request extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtIC;
	private JTextField txtPhone;
	private JTextField txtPurpose;

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					visitor_request frame = new visitor_request();
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
	public visitor_request() throws Exception {
		setTitle("Request Ticket");
		Connection conn = DbConnection.database();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 310);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		JDateChooser visitDate = new JDateChooser();
		visitDate.setDateFormatString("d,MM,yyyy");
		visitDate.setBounds(435, 51, 152, 30);
		java.util.Date date = new java.util.Date();
		visitDate.setDate(date);
		visitDate.getJCalendar().setMinSelectableDate(date);
		contentPane.add(visitDate);
		JTextPane txtpnPurpose = new JTextPane();
		txtpnPurpose.setForeground(new Color(153, 153, 153));
		
		txtpnPurpose.setText("What's your purpose of visit?");
		txtpnPurpose.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtpnPurpose.getText().equals("What's your purpose of visit?")) {
					txtpnPurpose.setText("");
					txtpnPurpose.setForeground(new Color(0,0,0));
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtpnPurpose.getText().equals("")) {
					txtpnPurpose.setText("What's your purpose of visit?");
					txtpnPurpose.setForeground(new Color(153,153,153));
				}
			}
		});
		txtpnPurpose.setBounds(436, 144, 151, 70);
		contentPane.add(txtpnPurpose);
		JLabel lblTime = new JLabel("Time of Visit");
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTime.setBounds(305, 94, 97, 30);
		contentPane.add(lblTime);
		
			java.util.Date date3 = new java.util.Date();
		    SpinnerDateModel model = new SpinnerDateModel();
		    model.setValue(date3);
		    JSpinner spinner = new JSpinner(model);
		    spinner.setBounds(458, 101, 46, 32);
		    contentPane.add(spinner);
		    JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH");
		    DateFormatter formatter = (DateFormatter)editor.getTextField().getFormatter();
		    formatter.setAllowsInvalid(false); // this makes what you want
		    formatter.setOverwriteMode(true);
		    spinner.setEditor(editor);
		
		    
		    java.util.Date dateMin = new java.util.Date();
		    SpinnerDateModel modelMin = new SpinnerDateModel();
		    modelMin.setValue(dateMin);
			JSpinner spinnerMin = new JSpinner(modelMin);
			spinnerMin.setBounds(541, 101, 46, 32);
			contentPane.add(spinnerMin);
			JSpinner.DateEditor editor2 = new JSpinner.DateEditor(spinnerMin, "mm");
		    DateFormatter formatter2 = (DateFormatter)editor2.getTextField().getFormatter();
		    formatter2.setAllowsInvalid(false); // this makes what you want
		    formatter2.setOverwriteMode(true);
		    spinnerMin.setEditor(editor2);
		    
		JLabel lblDate = new JLabel("Date of Visit");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDate.setBounds(305, 51, 120, 30);
		contentPane.add(lblDate);
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setForeground(Color.GREEN);
		btnSubmit.setBackground(new Color(255, 250, 250));
		JButton btnTestVisitor = new JButton("Test Visitor");
		btnTestVisitor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtName.setText("Low");
				txtIC.setText("021126070323");
				txtPhone.setText("0174299252");
				txtpnPurpose.setText("Testing");
				btnSubmit.doClick();
			}
		});
		btnTestVisitor.setBounds(30, 201, 89, 23);
		contentPane.add(btnTestVisitor);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean invalid = false;
				String name = txtName.getText();
				String ic = txtIC.getText();
				String phone = txtPhone.getText();
				String purpose = txtpnPurpose.getText();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(visitDate.getDate());
				int date2 = LocalDateTime.now().getHour();
				int dateMin = LocalDateTime.now().getMinute();
				java.util.Date tempdateMin = (java.util.Date)spinnerMin.getValue();
				java.util.Date tempdate = (java.util.Date)spinner.getValue();
				int mytimeMin = tempdateMin.getMinutes();
				int mytime = tempdate.getHours();
				String timeVisit = mytime+":"+mytimeMin;
				System.out.println( mytime);
				String pending = "Pending";
				System.out.println(name+ic+phone+purpose+timeVisit+date);
				if(name.isEmpty()||name.matches(".*\\d+.*")||name.equals("Enter Name")) {
					JOptionPane.showMessageDialog(null,"Invalid name format.\nOnly Alphabets Allowed.");
					invalid=true;
				}
				if(ic.isEmpty()||ic.matches("[a-zA-Z]+\\.?")||ic.equals("Enter IC Number")||ic.length()!=12) {
					JOptionPane.showMessageDialog(null,"Invalid IC format.\nOnly 12 Digits IC Number Allowed.");
					invalid=true;
				}
				if(phone.isEmpty()||phone.matches("[a-zA-Z]+\\.?")||phone.equals("Enter Phone No.")||phone.length()!=10) {
					JOptionPane.showMessageDialog(null,"Invalid phone format.\nOnly 10 Digits Number Allowed.");
					invalid=true;
				}
				if(purpose.isEmpty()||purpose.equals("What's your purpose of visit?")) {
					JOptionPane.showMessageDialog(null,"Invalid purpose format.\nPlease State Your Purpose of Visit.");
					invalid=true;
				}
				if(mytime<date2||mytime<8||mytime>17) {
				JOptionPane.showMessageDialog(null,"Time cannot be earlier than current time.\nTime of Visit(8.00am-17.59pm).");
					invalid=true;
				}
				if(mytimeMin<dateMin) {
					JOptionPane.showMessageDialog(null,"Time cannot be earlier than current time.\nTime of Visit(8.00am-17.59pm).");
					invalid=true;
				}
				if(invalid==false) {
				int submit = JOptionPane.showConfirmDialog(null,"Confirm to submit?");
				//Testing
				submit=0;
				if(submit==0) {
				try {
					
					String QUERY = "insert into user_request (name,ic,phone,purpose,tov,dov,status) values(?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement preparedStmt = conn.prepareStatement(QUERY);
					preparedStmt.setString(1, name);
					preparedStmt.setString(2, ic);
					preparedStmt.setString(3, phone);
					preparedStmt.setString(4, purpose);
					preparedStmt.setString(5, timeVisit);
					preparedStmt.setDate(6, Date.valueOf(date));
					preparedStmt.setString(7, pending);
					preparedStmt.execute();
					PreparedStatement stm = conn.prepareStatement("select ticketid from user_request order by ticketid desc limit 1 ");
				
					ResultSet rs = stm.executeQuery();
					while(rs.next()) {
						String id = rs.getString("ticketid");
						JOptionPane.showMessageDialog(null,"Your Ticket Number is: "+id+".\nPlease Wait and check for approval upon visit.");
					}
					txtName.setText("");
					txtIC.setText("");
					txtPhone.setText("");
					txtpnPurpose.setText("");
					
					System.out.println("Automated Testing Successful.");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				}
			}
			}
		});
		btnSubmit.setBounds(506, 225, 89, 35);
		contentPane.add(btnSubmit);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(30, 57, 46, 14);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtName.getText().equals("Enter Name")) {
					txtName.setText("");
					txtName.setForeground(new Color(0,0,0));
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtName.getText().equals("")) {
					txtName.setText("Enter Name");
					txtName.setForeground(new Color(153,153,153));
				}
			}
		});
		txtName.setForeground(new Color(153, 153, 153));
		txtName.setText("Enter Name");
		txtName.setBounds(134, 51, 151, 30);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblIC = new JLabel("IC No.");
		lblIC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIC.setBounds(30, 98, 86, 14);
		contentPane.add(lblIC);
		
		txtIC = new JTextField();
		txtIC.setForeground(new Color(153, 153, 153));
		txtIC.setText("Enter IC Number");
		txtIC.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtIC.getText().equals("Enter IC Number")) {
					txtIC.setText("");
					txtIC.setForeground(new Color(0,0,0));
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtIC.getText().equals("")) {
					txtIC.setText("Enter IC Number");
					txtIC.setForeground(new Color(153,153,153));
				}
			}
		});
		txtIC.setBounds(134, 92, 151, 30);
		contentPane.add(txtIC);
		txtIC.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone No.");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhone.setBounds(30, 150, 84, 14);
		contentPane.add(lblPhone);
		
		txtPhone = new JTextField();
		txtPhone.setForeground(new Color(153, 153, 153));
		txtPhone.setText("Enter Phone No.");
		txtPhone.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtPhone.getText().equals("Enter Phone No.")) {
					txtPhone.setText("");
					txtPhone.setForeground(new Color(0,0,0));
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtPhone.getText().equals("")) {
					txtPhone.setText("Enter Phone No.");
					txtPhone.setForeground(new Color(153,153,153));
				}
			}
		});
		txtPhone.setBounds(134, 144, 151, 30);
		contentPane.add(txtPhone);
		txtPhone.setColumns(10);
		
		JLabel lblPurpose = new JLabel("Purpose of Visit");
		lblPurpose.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPurpose.setBounds(315, 151, 111, 23);
		contentPane.add(lblPurpose);
		
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(Color.RED);
		btnCancel.setBackground(new Color(255, 250, 250));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
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
		btnCancel.setBounds(383, 225, 89, 35);
		contentPane.add(btnCancel);
		
		JLabel lblNewLabel_5 = new JLabel("Please Fill in the Form");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(204, 11, 235, 22);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblHour = new JLabel("H :");
		lblHour.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHour.setBounds(435, 92, 31, 41);
		contentPane.add(lblHour);
		
		JLabel lblMin = new JLabel("M :");
		lblMin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMin.setBounds(514, 92, 31, 41);
		contentPane.add(lblMin);
		
		//Testing
		//btnTestVisitor.doClick();
		//line 201-202
		btnTestVisitor.setVisible(false);

		
		
	    
		
		
		
	}
}
