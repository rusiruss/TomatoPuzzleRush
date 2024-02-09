package View;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.*;
import Controller.Engine;
import Model.DbConnector;
import Model.Player;
import Model.SoundPlayer;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;




public class gameUi extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	

	/**
	 * Method that is called when a button has been pressed.
	 */
	private int chancesRemaining = 3;
	JLabel btnmute = new JLabel("");
	JLabel lblNewLabel = new JLabel("");
	String uname1;
	Connection con = null;
	PreparedStatement ps = null;
	int score ;
	int level;
	Player player = Player.getInstance();
	public void passVlues() {
		System.out.println(level + " " + player.getUsername());
		String query = "INSERT INTO `scoreboard`(`username`, `score`, `level`) VALUES ('" + player.getUsername() + "','" + score + "','" + level + "')";
		try {
			ps = con.prepareStatement(query);
			ps.execute();
		}catch(SQLException ex) {
			System.out.println(ex);
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		score= myGame.getScore(); 
		int solution = Integer.parseInt(e.getActionCommand());
		boolean correct = myGame.checkSolution(solution);
		level=myGame.getlevel();
		String corsol = "Correct solution entered!";
		String worsol = "Wrong solution entered!";		
		if (correct) {
			System.out.println(corsol);
			currentGame = myGame.nextGame(); 
			
			ImageIcon ii = new ImageIcon(currentGame);
			questArea.setIcon(ii);
			lblscore.setText(""+score);
			chancesRemaining = 3;
			
		
			
			
		} else { 
			System.out.println(worsol); 
			
			SoundPlayer.getInstance().wrong();
			chancesRemaining--;
			
			if (chancesRemaining == 0) {
				timer.start();
				dispose();
				SoundPlayer.getInstance().stopBackgroundMusic();
	            System.out.println("Out of chances. Game over!");
	            SoundPlayer.getInstance().failed();
	            passVlues();
	            JOptionPane.showMessageDialog(null, "Out of chances. Game over! please Try Again");  
	            MainMenu menu = new MainMenu();
	            menu.setVisible(true);
	            
	            // Handle game over logic here if needed
	        } else {
	            System.out.println("Chances remaining: " + chancesRemaining);
	        }
			
		}
		
		lblLevel.setText(""+level);
		lblremaind.setText(""+chancesRemaining);
		
	}
	
	JLabel questArea = null;
	Engine myGame = null;
	BufferedImage currentGame = null;
	JTextArea infoArea = null;
/**
 * 
 * Initializes the game. 
 * @param player
 */
	
	JLabel lblLevel = new JLabel("1");
	JLabel lblscore = new JLabel("0");	
	JLabel lblremaind = new JLabel("3");
	JLabel lbltime = new JLabel(" ");
	Timer timer;	
	int second=0, minute=1;
	String ddSecond, ddMinute;	
	DecimalFormat dFormat = new DecimalFormat("00");
	
	private void initGame(String player) {
		
		countdownTimer();
		timer.start();	
		SoundPlayer.getInstance().playBackgroundMusic();
		setSize(896, 588);
		setTitle("What is the missing value?");
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 896, 588);
		

		myGame = new Engine(player);
		currentGame = myGame.nextGame();
		panel.setLayout(null);

		
		JLabel closebtn = new JLabel("");
		closebtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SoundPlayer.getInstance().stopBackgroundMusic();
				passVlues();
				dispose();
				MainMenu main = new MainMenu();
				main.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				closebtn.setIcon(new ImageIcon(gameUi.class.getResource("/Assest/hoverbtnhome.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				closebtn.setIcon(new ImageIcon(gameUi.class.getResource("/Assest/btnhomen.png")));
			}
		});
		
		JPanel remindpannel_1 = new JPanel();
		remindpannel_1.setLayout(null);
		remindpannel_1.setOpaque(false);
		remindpannel_1.setBackground(Color.WHITE);
		remindpannel_1.setBounds(777, 21, 70, 42);
		panel.add(remindpannel_1);
		
		
		lbltime.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbltime.setBackground(Color.WHITE);
		lbltime.setBounds(10, 0, 77, 42);
		remindpannel_1.add(lbltime);
		lbltime.setText("00:30");
		
		JPanel remindpannel = new JPanel();
		remindpannel.setLayout(null);
		remindpannel.setOpaque(false);
		remindpannel.setBackground(Color.WHITE);
		remindpannel.setBounds(620, 21, 70, 42);
		panel.add(remindpannel);
		

		lblremaind.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblremaind.setBackground(Color.WHITE);
		lblremaind.setBounds(10, 0, 77, 42);
		remindpannel.add(lblremaind);
		closebtn.setIcon(new ImageIcon(gameUi.class.getResource("/Assest/btnhomen.png")));
		closebtn.setBounds(10, 10, 51, 53);
		panel.add(closebtn);
		
		JPanel scorepanel = new JPanel();
		scorepanel.setOpaque(false);
		scorepanel.setLayout(null);
		scorepanel.setBackground(Color.WHITE);
		scorepanel.setBounds(407, 21, 70, 42);
		panel.add(scorepanel);
		

		lblscore.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblscore.setBackground(Color.WHITE);
		lblscore.setBounds(10, 0, 77, 42);
		scorepanel.add(lblscore);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(211, 21, 76, 42);
		panel.add(panel_1);
		panel_1.setLayout(null);
		lblLevel.setBackground(Color.WHITE);
		lblLevel.setBounds(0, 0, 77, 42);
		
		lblLevel.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_1.add(lblLevel);
		


		JPanel ans = new JPanel();
		ans.setOpaque(false);
		ans.setBackground(Color.WHITE);
		ans.setBounds(0, 531, 896, 53);
		panel.add(ans);

		
		ImageIcon ii = new ImageIcon(currentGame);
		questArea = new JLabel(ii);
		questArea.setBackground(Color.WHITE);
	    questArea.setSize(330, 600);
	    questArea.setOpaque(false);
	    //this
	    
		JScrollPane Qpanel = new JScrollPane(questArea);
		Qpanel.getViewport().getView().setBackground(Color.WHITE);
		Qpanel.getViewport().getView().setForeground(Color.WHITE);
		Qpanel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		Qpanel.setOpaque(true);
		Qpanel.setBounds(107, 115, 683, 380);
		panel.add(Qpanel);

		for (int i = 0; i < 10; i++) {
			
			JButton btn = new JButton(String.valueOf(i));
			btn.setBackground(Color.white);
			btn.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 0, 51)));
			ans.add(btn);
			btn.setPreferredSize(new Dimension(60, 40)); 
			btn.addActionListener(this);
			btn.setFont(new Font("Tahoma", Font.BOLD, 20));
			btn.addMouseListener(new java.awt.event.MouseAdapter(){
				public void mouseEntered(java.awt.event.MouseEvent evt) {
	                btn.setBackground(new Color(255, 0, 51));
	                btn.setForeground(new Color(255, 255, 255));
	                btn.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(2, 9, 29)));
	                SoundPlayer.getInstance().playSoundHover();
	                
	               
	            }
				public void mouseExited(java.awt.event.MouseEvent evt) {
	                btn.setBackground(Color.white);
	                btn.setForeground(new Color(0, 0, 0));
	                btn.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 0, 51)));
	                
	                
	                
	            }
				
			});
			
				
		}
		
		getContentPane().add(panel);
		panel.repaint();
		
		
		getContentPane().setLayout(null);
		getContentPane().add(panel);
		
		
		lblNewLabel.setEnabled(true);
		lblNewLabel.setIcon(new ImageIcon(gameUi.class.getResource("/Assest/game_ui.png")));
		lblNewLabel.setBounds(0, 0, 896, 588);
		panel.add(lblNewLabel);
		panel.repaint();
		
		setUndecorated(true);
		CustomDecorate.getInstance().setCustomCursor(this).setIcon(this).setFrametoCenter(this);

		
		
		
		


	}
	public void countdownTimer() {
	    timer = new Timer(1000, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            second--;
	            ddSecond = dFormat.format(second);
	            ddMinute = dFormat.format(minute);
	            lbltime.setText(ddMinute + ":" + ddSecond);

	            if (second == -1) {
	                second = 59;
	                minute--;
	                ddSecond = dFormat.format(second);
	                ddMinute = dFormat.format(minute);
	                lbltime.setText(ddMinute + ":" + ddSecond);
	            }

	            if (minute == 0 && second == 0) {
	                timer.stop();
	                SoundPlayer.getInstance().timer();
	                JOptionPane.showMessageDialog(null, "Game Finish. Go And View Your Score And Rank");
	                dispose();
	                passVlues();
	                SoundPlayer.getInstance().stopBackgroundMusic();
	                MainMenu mainMenu = new MainMenu();
	                mainMenu.setVisible(true);
	                passVlues();
	            }

	            if (second == 3) {
	            	SoundPlayer.getInstance().counter();
	                
	            }if(second<10) {
	            	lbltime.setForeground(Color.RED);
	            }
	            else {
	                lbltime.setForeground(Color.BLACK);
	            }
	        }
	    });
	}


	
	
/**
 * Default player is null. 
 */
	
	public gameUi() {
		super();
		initGame(null);
		con = DbConnector.connect();
	}

	/**
	 * Use this to start GUI, e.g., after login.
	 * 
	 * @param player
	 */
	public gameUi(String player) {
		super();
		initGame(player);
	}

	/**
	 * Main entry point into the equation game. Can be used without login for testing. 
	 * 
	 * @param args not used.
	 */
	public static void main(String[] args) {
		gameUi myGUI = new gameUi();
		myGUI.setVisible(true);

	}
}