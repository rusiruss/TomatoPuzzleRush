package View;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import Model.DbConnector;
import Model.SendMail;
import Model.SoundPlayer;

import java.awt.Color;
import javax.swing.JPasswordField;


public class SignUp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtusername;
	private JTextField txtemail;
	private JPasswordField txtpswd;
	
	Connection con = null;
	PreparedStatement pst = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SignUp() {
		con = DbConnector.connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 896, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("Login");
		lblNewLabel_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				SignIn signin = new SignIn();
				signin.setVisible(true);
			}
		});
		
		JLabel lblhome_1 = new JLabel("");
		lblhome_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblhome_1.setIcon(new ImageIcon(SignUp.class.getResource("/Assest/hoverExit.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblhome_1.setIcon(new ImageIcon(SignUp.class.getResource("/Assest/exitbtnn.png")));
			}
		});
		lblhome_1.setIcon(new ImageIcon(SignUp.class.getResource("/Assest/exitbtnn.png")));
		lblhome_1.setBounds(10, 11, 50, 50);
		contentPane.add(lblhome_1);
		
		txtpswd = new JPasswordField();
		txtpswd.setOpaque(false);
		txtpswd.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		txtpswd.setBounds(517, 315, 336, 38);
		contentPane.add(txtpswd);
		lblNewLabel_1_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_1_1.setForeground(new Color(64, 224, 208));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(725, 375, 128, 25);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("Already  have an account ?");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setForeground(SystemColor.text);
		lblNewLabel_1.setBounds(517, 380, 241, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel createbtn = new JLabel("");
		createbtn.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        String username = txtusername.getText().trim();
		        String password = new String(txtpswd.getPassword()).trim();
		        String email = txtemail.getText().trim();

		        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
		        	SoundPlayer.getInstance().error();
		            JOptionPane.showMessageDialog(rootPane, "Please fill in all fields");
		        } else if (!isValidPassword(password)) {
		            SoundPlayer.getInstance().error();
		            JOptionPane.showMessageDialog(rootPane, "Invalid password. Password must have at least 8 characters, including uppercase, lowercase, numbers, and symbols.");
		            clearTextFields();
		        } else if (!isValidEmail(email)) {
		            SoundPlayer.getInstance().error();
		            JOptionPane.showMessageDialog(rootPane, "Invalid email address");
		            clearTextFields();
		        } else {
		            try {
		                String query = "INSERT INTO `users`(`username`, `email`, `password`) VALUES (?, ?, ?)";
		                pst = con.prepareStatement(query);
		                pst.setString(1, username);
		                pst.setString(2, email);
		                pst.setString(3, password);
		                pst.execute();
		                JOptionPane.showMessageDialog(rootPane, "Account Create Successfully");
		                SendMail sm = new SendMail();
	                    sm.sendMail(email);
		            } catch (Exception e1) {
		                JOptionPane.showMessageDialog(rootPane, "Error: " + e1.getMessage());
		            } finally {
		                try {
		                    if (pst != null) {
		                        pst.close();
		                    }
		                } catch (SQLException ex) {
		                    ex.printStackTrace();
		                }
		            }
		        }
		    }

		    // Validate email address format
		    private boolean isValidEmail(String email) {
		        // Basic email validation
		        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
		    }

		    // Validate password format
		    private boolean isValidPassword(String password) {
		        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$");
		    }

		    private void clearTextFields() {
		        txtusername.setText("");
		        txtpswd.setText("");
		        txtemail.setText("");
		    }
		});

		createbtn.setIcon(new ImageIcon(SignUp.class.getResource("/Assest/Create.png")));
		createbtn.setBounds(618, 403, 149, 58);
		contentPane.add(createbtn);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(SystemColor.text);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(517, 291, 336, 25);
		contentPane.add(lblPassword);
		
		JLabel lblEMail = new JLabel("E mail");
		lblEMail.setForeground(SystemColor.text);
		lblEMail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEMail.setBounds(517, 216, 336, 25);
		contentPane.add(lblEMail);
		
		JLabel lblNewLabel = new JLabel("User Name");
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(517, 140, 336, 25);
		contentPane.add(lblNewLabel);
		
		txtemail = new JTextField();
		txtemail.setForeground(UIManager.getColor("CheckBox.interiorBackground"));
		txtemail.setOpaque(false);
		txtemail.setBackground(UIManager.getColor("CheckBox.light"));
		txtemail.setBorder(new MatteBorder(0, 0, 2, 0, (Color) SystemColor.menu));
		txtemail.setColumns(10);
		txtemail.setBounds(517, 236, 336, 38);
		contentPane.add(txtemail);
		
		txtusername = new JTextField();
		txtusername.setForeground(UIManager.getColor("CheckBox.interiorBackground"));
		txtusername.setOpaque(false);
		txtusername.setBackground(UIManager.getColor("CheckBox.light"));
		txtusername.setBorder(new MatteBorder(0, 0, 2, 0, (Color) SystemColor.menu));
		txtusername.setBounds(517, 161, 336, 38);
		contentPane.add(txtusername);
		txtusername.setColumns(10);
		
		JLabel signupbg = new JLabel("");
		signupbg.setIcon(new ImageIcon(SignUp.class.getResource("/Assest/signupbg.png")));
		signupbg.setBounds(0, 0, 896, 512);
		contentPane.add(signupbg);
		
		setUndecorated(true);
		
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
		
		

	}
}
