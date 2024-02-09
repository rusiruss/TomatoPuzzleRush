package View;

import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Player;
import Model.SoundPlayer;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.Dimension;

public class MainMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	String uname;
	JLabel lblname;
	Player player = Player.getInstance();



	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
	                MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MainMenu() {
		System.out.println(player.getUsername());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 896, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel exitbtn = new JLabel("");
		exitbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				welcomeUi welcome = new welcomeUi();
				welcome.setVisible(true);
				SoundPlayer.getInstance().stopBackgroundMusic();
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				SoundPlayer.getInstance().selectmain();
				exitbtn.setIcon(new ImageIcon(MainMenu.class.getResource("/Assest/exitbtnchange.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitbtn.setIcon(new ImageIcon(MainMenu.class.getResource("/Assest/exitbtn.png")));
			}
		});	

		JLabel btnuser = new JLabel("");
		btnuser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				profile Prof = new profile();
				Prof.setVisible(true);
				
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnuser.setIcon(new ImageIcon(MainMenu.class.getResource("/Assest/btnuserhover.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnuser.setIcon(new ImageIcon(MainMenu.class.getResource("/Assest/btnuser.png")));
			}
		});
		
		JLabel lblexit = new JLabel("");
		lblexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblexit.setIcon(new ImageIcon(MainMenu.class.getResource("/Assest/hoverExit.png")));
				
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblexit.setIcon(new ImageIcon(MainMenu.class.getResource("/Assest/exitbtnn.png")));
			}
		});
		lblexit.setIcon(new ImageIcon(MainMenu.class.getResource("/Assest/exitbtnn.png")));
		lblexit.setBounds(10, 11, 50, 50);
		contentPane.add(lblexit);
		btnuser.setIcon(new ImageIcon(MainMenu.class.getResource("/Assest/btnuser.png")));
		btnuser.setBounds(836, 11, 50, 50);
		contentPane.add(btnuser);
		exitbtn.setIcon(new ImageIcon(MainMenu.class.getResource("/Assest/exitbtn.png")));
		exitbtn.setBounds(377, 350, 169, 69);
		contentPane.add(exitbtn);
		
		JLabel rankbtn = new JLabel("");
		rankbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {		
					dispose();
					Ranking ranking = new Ranking();
					ranking.setVisible(true);


					
									
				}catch (Exception e1) {
					System.out.println(e1);
				}
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				SoundPlayer.getInstance().selectmain();
				rankbtn.setIcon(new ImageIcon(MainMenu.class.getResource("/Assest/rankbtnchange.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rankbtn.setIcon(new ImageIcon(MainMenu.class.getResource("/Assest/rankbtn.png")));
			}
		});
		rankbtn.setIcon(new ImageIcon(MainMenu.class.getResource("/Assest/rankbtn.png")));
		rankbtn.setBounds(377, 251, 169, 69);
		contentPane.add(rankbtn);
		
		JLabel playbtn = new JLabel("");
		playbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				gameUi gameui = new gameUi();
				gameui.setVisible(true);
				gameui.uname1=uname;
				
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				SoundPlayer.getInstance().selectmain();
				playbtn.setIcon(new ImageIcon(MainMenu.class.getResource("/Assest/playbtnchange.png")));
				
				
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				playbtn.setIcon(new ImageIcon(MainMenu.class.getResource("/Assest/playbtn.png")));
			}
		});

		playbtn.setIcon(new ImageIcon(MainMenu.class.getResource("/Assest/playbtn.png")));
		playbtn.setBounds(377, 155, 163, 69);
		contentPane.add(playbtn);
		
		JLabel menubg = new JLabel("");
		menubg.setIcon(new ImageIcon(MainMenu.class.getResource("/Assest/menuBG.png")));
		menubg.setBounds(0, 0, 896, 512);
		contentPane.add(menubg);
		
		setUndecorated(true);
		CustomDecorate.getInstance().setCustomCursor(this).setIcon(this).setFrametoCenter(this);

		
		
		
	}
}
