package View;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Model.DbConnector;
import Model.Player;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class profile extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
			


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					profile frame = new profile();
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
	
	String username;
	int score;
	int level;
	Player player = Player.getInstance();
	JLabel lblRank = new JLabel("0");
	JLabel lblscore = new JLabel("0");
	private final JLabel homebtn = new JLabel("");
	private final JLabel lblNewLabel_1 = new JLabel("Higher Score :");
	private final JLabel lblNewLabel_1_1 = new JLabel("Higher Level :");
	
	public profile() {
		
		con = DbConnector.connect();
		getMaxScoreAndLevel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 896, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		homebtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				MainMenu main = new MainMenu();
				main.setVisible(true);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				homebtn.setIcon(new ImageIcon(profile.class.getResource("/Assest/hoverbtnhome.png")));
			}
		});
		lblNewLabel_1_1.setForeground(UIManager.getColor("Button.light"));
		lblNewLabel_1_1.setFont(new Font("Passion One", Font.PLAIN, 42));
		lblNewLabel_1_1.setBounds(312, 276, 242, 44);
		
		contentPane.add(lblNewLabel_1_1);
		lblNewLabel_1.setForeground(UIManager.getColor("Button.light"));
		lblNewLabel_1.setFont(new Font("Passion One", Font.PLAIN, 42));
		lblNewLabel_1.setBounds(312, 214, 242, 44);
		
		contentPane.add(lblNewLabel_1);
		homebtn.setIcon(new ImageIcon(profile.class.getResource("/Assest/btnhomen.png")));
		homebtn.setBounds(10, 11, 51, 53);
		
		contentPane.add(homebtn);
		
		
		JLabel lblname = new JLabel("Hello,"+player.getUsername());
		lblname.setHorizontalAlignment(SwingConstants.CENTER);
		lblname.setVerticalAlignment(SwingConstants.CENTER);
		lblname.setForeground(new Color(255, 255, 255));
		lblname.setFont(new Font("Sweet Affogato", Font.PLAIN, 39));
		lblname.setBounds(332, 140, 261, 60);
		contentPane.add(lblname);
		lblRank.setForeground(UIManager.getColor("Button.light"));
		
		
		lblRank.setFont(new Font("Passion One", Font.PLAIN, 42));
		lblRank.setBounds(541, 276, 98, 44);
		contentPane.add(lblRank);
		lblscore.setForeground(UIManager.getColor("Button.light"));
		
		
		lblscore.setFont(new Font("Passion One", Font.PLAIN, 42));
		lblscore.setBounds(541, 214, 98, 44);
		contentPane.add(lblscore);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(profile.class.getResource("/Assest/profilebg.png")));
		lblNewLabel.setBounds(0, 0, 896, 512);
		contentPane.add(lblNewLabel);
		
		setUndecorated(true);
		CustomDecorate.getInstance().setCustomCursor(this).setIcon(this).setFrametoCenter(this);
		
	}
	
	public void getMaxScoreAndLevel() {
	    String query = "SELECT score, level FROM `scoreboard` WHERE username=? ORDER BY score DESC LIMIT 1;";
	    try {
	        ps = con.prepareStatement(query);
	        ps.setString(1, player.getUsername());
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            int highestScore = rs.getInt("score");
	            int highestLevel = rs.getInt("level");

	            // Display the highest score and level in your JLabels or wherever you want
	            lblscore.setText(String.valueOf(highestScore));
	            lblRank.setText(String.valueOf(highestLevel));

	            System.out.println("Highest score is " + highestScore);
	            System.out.println("Highest level is " + highestLevel);
	        } else {
	            System.out.println("No records found for the user");
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}

	}
	
	

