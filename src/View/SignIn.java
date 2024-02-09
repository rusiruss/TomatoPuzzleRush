package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import Model.DbConnector;
import Model.Player;
import Model.SoundPlayer;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JPasswordField;

public class SignIn extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtuname;
	private JPasswordField txtpswd;
	
    Connection con = null;
    PreparedStatement pst = null;
    Player player;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignIn frame = new SignIn();
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
	
	
	public SignIn() {
		con = DbConnector.connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 896, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblhome_1 = new JLabel("");
		lblhome_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblhome_1.setIcon(new ImageIcon(SignIn.class.getResource("/Assest/hoverExit.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblhome_1.setIcon(new ImageIcon(SignIn.class.getResource("/Assest/exitbtnn.png")));
			}
		});
		lblhome_1.setIcon(new ImageIcon(SignIn.class.getResource("/Assest/exitbtnn.png")));
		lblhome_1.setBounds(10, 11, 50, 50);
		contentPane.add(lblhome_1);
		
		txtpswd = new JPasswordField();
		txtpswd.setOpaque(false);
		txtpswd.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		txtpswd.setBounds(512, 292, 336, 38);
		contentPane.add(txtpswd);
		
		JLabel lblNewLabel_1 = new JLabel("Dont have an account?");
		lblNewLabel_1.setForeground(SystemColor.text);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(512, 373, 180, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Sign Up");
		lblNewLabel_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				SignUp signup = new SignUp();
				signup.setVisible(true);
				
			}
		});
		lblNewLabel_1_1.setForeground(new Color(64, 224, 208));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(691, 368, 128, 25);
		contentPane.add(lblNewLabel_1_1);
		
		
		
		JLabel createbtn = new JLabel("");

		// ...

		createbtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean log = false;
                String username = txtuname.getText();
                String password = txtpswd.getText();

                if (isValidInput(username, password)) {
                    try {
                        pst = con.prepareStatement("SELECT * FROM `users` WHERE username=?");
                        pst.setString(1, username);
                        ResultSet r = pst.executeQuery();

                        if (r.next()) {
                            String storedPassword = r.getString("password");
                            if (password.equals(storedPassword)) {
                                log = true;
                            } else {
                            	SoundPlayer.getInstance().error();
                                JOptionPane.showMessageDialog(null, "Incorrect password", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                        	SoundPlayer.getInstance().error();
                            JOptionPane.showMessageDialog(null, "Username not found", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        if (log) {
                            player = Player.getInstance();
                            player.setUsername(username);
                            new MainMenu().setVisible(true);
                            dispose();
                        }
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }
        });


		    


		createbtn.setIcon(new ImageIcon(SignIn.class.getResource("/Assest/login.png")));
		createbtn.setBounds(625, 415, 149, 58);
		contentPane.add(createbtn);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(SystemColor.text);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(512, 267, 336, 25);
		contentPane.add(lblPassword);
		
		txtuname = new JTextField();
		txtuname.setOpaque(false);
		txtuname.setForeground(Color.WHITE);
		txtuname.setColumns(10);
		txtuname.setBorder(new MatteBorder(0, 0, 2, 0, (Color) SystemColor.menu));
		txtuname.setBackground(UIManager.getColor("Button.light"));
		txtuname.setBounds(512, 218, 336, 38);
		contentPane.add(txtuname);
		
		JLabel lblNewLabel = new JLabel("User Name");
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(512, 197, 336, 25);
		contentPane.add(lblNewLabel);
		
		JLabel signinbg = new JLabel("");
		signinbg.setIcon(new ImageIcon(SignIn.class.getResource("/Assest/signinbg2.png")));
		signinbg.setBounds(0, 0, 896, 512);
		contentPane.add(signinbg);
		
		setUndecorated(true);
		CustomDecorate.getInstance().setCustomCursor(this);
		CustomDecorate.getInstance().setIcon(this);
		CustomDecorate.getInstance().setFrametoCenter(this);


	}
	private boolean isValidInput(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            SoundPlayer.getInstance().error();
            JOptionPane.showMessageDialog(null, "Username and password are required.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
	
	
}
