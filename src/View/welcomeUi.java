package View;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Model.SoundPlayer;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.Dimension;


public class welcomeUi extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					welcomeUi frame = new welcomeUi();
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


	public welcomeUi() {
		SoundPlayer.getInstance().playBackgroundMusic();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 896, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblstartbtn = new JLabel("");
		lblstartbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				SignIn signin = new SignIn();
				signin.setVisible(true);
				SoundPlayer.getInstance().stopBackgroundMusic();
				
			}
		});
		
		JLabel closebtn = new JLabel("");
		closebtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				dispose();
			}
		});
		closebtn.setIcon(new ImageIcon(welcomeUi.class.getResource("/Assest/closebtn.png")));
		closebtn.setBounds(10, 11, 38, 38);
		contentPane.add(closebtn);
		lblstartbtn.setIcon(new ImageIcon(welcomeUi.class.getResource("/Assest/Startbtn.png")));
		lblstartbtn.setBounds(364, 448, 164, 53);
		contentPane.add(lblstartbtn);
		
		JLabel welcomeUibg = new JLabel("");
		welcomeUibg.setIcon(new ImageIcon(welcomeUi.class.getResource("/Assest/WelcomeUi-01 - Copy.png")));
		welcomeUibg.setBounds(0, 0, 896, 512);
		contentPane.add(welcomeUibg);
		
		setUndecorated(true);
		CustomDecorate.getInstance().setCustomCursor(this).setIcon(this).setFrametoCenter(this);

		

		
	}
}
