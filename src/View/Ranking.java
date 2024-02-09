package View;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import Model.DbConnector;
import Model.Player;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.Font;

public class Ranking extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	String username;
	int score;
	int level;
	Player player = Player.getInstance();
	
	JLabel lbl1stplaceName = new JLabel("NAME");
    JLabel lbl1stplacescore = new JLabel("SCORE");
    JLabel lbl3rdplaceName = new JLabel("NAME");
    JLabel lbl3rdplacescore = new JLabel("SCORE");
    JLabel lbl2ndplacescore = new JLabel("SCORE");
	JLabel lbl2ndplaceName = new JLabel("NAME");
	private final JLabel lblhome = new JLabel("");
	private final JLabel lblhome_1 = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ranking frame = new Ranking();
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
	public Ranking() {
		con = DbConnector.connect(); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 896, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblhome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				MainMenu main = new MainMenu();
				main.setVisible(true);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblhome.setIcon(new ImageIcon(Ranking.class.getResource("/Assest/hoverbtnhome.png")));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblhome.setIcon(new ImageIcon(Ranking.class.getResource("/Assest/btnhomen.png")));
			}
		});
		lblhome_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblhome_1.setIcon(new ImageIcon(Ranking.class.getResource("/Assest/hoverExit.png")));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblhome_1.setIcon(new ImageIcon(Ranking.class.getResource("/Assest/exitbtnn.png")));
			}
		});
		lblhome_1.setIcon(new ImageIcon(Ranking.class.getResource("/Assest/exitbtnn.png")));
		lblhome_1.setBounds(10, 11, 50, 50);
		
		contentPane.add(lblhome_1);
		lblhome.setIcon(new ImageIcon(Ranking.class.getResource("/Assest/btnhomen.png")));
		lblhome.setBounds(836, 11, 50, 50);
		
		contentPane.add(lblhome);
		lbl1stplaceName.setFont(new Font("Passion One", Font.PLAIN, 27));
		
		lbl1stplaceName.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1stplaceName.setVerticalAlignment(SwingConstants.CENTER);
		lbl1stplaceName.setForeground(Color.WHITE);
		lbl1stplaceName.setBounds(576, 420, 95, 27);
		contentPane.add(lbl1stplaceName);
		
		lbl1stplacescore.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1stplacescore.setVerticalAlignment(SwingConstants.CENTER);
		lbl1stplacescore.setForeground(Color.WHITE);
		lbl1stplacescore.setBounds(576, 447, 95, 27);
		contentPane.add(lbl1stplacescore);
		lbl3rdplaceName.setFont(new Font("Passion One", Font.PLAIN, 27));
		
		lbl3rdplaceName.setHorizontalAlignment(SwingConstants.CENTER);
		lbl3rdplaceName.setVerticalAlignment(SwingConstants.CENTER);
		lbl3rdplaceName.setForeground(Color.WHITE);
		lbl3rdplaceName.setBounds(681, 420, 95, 27);
		contentPane.add(lbl3rdplaceName);
		
		lbl3rdplacescore.setHorizontalAlignment(SwingConstants.CENTER);
		lbl3rdplacescore.setVerticalAlignment(SwingConstants.CENTER);
		lbl3rdplacescore.setForeground(Color.WHITE);
		lbl3rdplacescore.setBounds(681, 447, 95, 27);
		contentPane.add(lbl3rdplacescore);
		
		lbl2ndplacescore.setHorizontalAlignment(SwingConstants.CENTER);
		lbl2ndplacescore.setVerticalAlignment(SwingConstants.CENTER);
		lbl2ndplacescore.setForeground(Color.WHITE);
		lbl2ndplacescore.setBounds(471, 447, 95, 27);
		contentPane.add(lbl2ndplacescore);
		lbl2ndplaceName.setFont(new Font("Passion One", Font.PLAIN, 27));
		
	
		lbl2ndplaceName.setHorizontalAlignment(SwingConstants.CENTER);
		lbl2ndplaceName.setVerticalAlignment(SwingConstants.CENTER);
		lbl2ndplaceName.setForeground(new Color(255, 255, 255));
		lbl2ndplaceName.setBounds(471, 420, 95, 27);
		contentPane.add(lbl2ndplaceName);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Ranking.class.getResource("/Assest/frameleaderboard.png")));
		lblNewLabel_1.setBounds(20, 41, 318, 448);
		contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
		scrollPane.setOpaque(false);
		scrollPane.setBackground(new Color(0, 0, 0, 0));
		scrollPane.setBounds(48, 132, 268, 332);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBorder(null);
		table.setFont(new Font("Tahoma", Font.PLAIN, 17));
		table.setOpaque(false);
		table.setBackground(new Color(0, 0, 0, 0));
		table.setRowHeight(50);
		scrollPane.setViewportView(table);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Ranking.class.getResource("/Assest/rankingbg2.png")));
		lblNewLabel.setBounds(0, 0, 896, 512);
		contentPane.add(lblNewLabel);
		setUndecorated(true);
		
		try {
                        
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Rank");
            model.addColumn("Username");
            model.addColumn("Score");
            model.addColumn("Level");
            table.setModel(model);
            
            scoreBoard();

        } catch (Exception e) {
            e.printStackTrace();
        }
		
		rank();
		
		CustomDecorate.getInstance().setCustomCursor(this).setIcon(this).setFrametoCenter(this);

	}
	
	public void scoreBoard() {
	    String query = "SELECT username, MAX(score) as max_score, MAX(level) as max_level FROM scoreboard GROUP BY username ORDER BY max_score DESC;";
	    try {
	        ps = con.prepareStatement(query);
	        rs = ps.executeQuery();

	        int rank = 1;
	        while (rs.next()) {
	            String username = rs.getString("username");
	            int score = rs.getInt("max_score");
	            int level = rs.getInt("max_level");

	            Object[] rowData = { rank, username, score, level };
	            ((DefaultTableModel) table.getModel()).addRow(rowData);

	            rank++;
	        }

	        // Center-align the content in each column
	        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
	        
	        // Apply the renderer to each column in the table
	        for (int i = 0; i < table.getColumnCount(); i++) {
	            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
	        }

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}
	
	public void rank() {
		String query = "SELECT username, score FROM scoreboard ORDER BY score DESC;";
	    try {
	        ps = con.prepareStatement(query);
	        rs = ps.executeQuery();

	        int rank = 1;
	        Set<String> rankedUsernames = new HashSet<>();

	        while (rs.next() && rank <= 3) {
	            String username = rs.getString("username");
	            int score = rs.getInt("score");
	            if (!rankedUsernames.contains(username)) {
	                if (rank == 1) {
	                    lbl1stplaceName.setText(username);
	                    lbl1stplacescore.setText(String.valueOf(score));                  
	                } else if (rank == 2) {
	                    lbl2ndplaceName.setText(username);
	                    lbl2ndplacescore.setText(String.valueOf(score));  
	                } else if (rank == 3) {
	                    lbl3rdplaceName.setText(username);
	                    lbl3rdplacescore.setText(String.valueOf(score));  
	                }

	                rank++;
	                rankedUsernames.add(username);
	            }
	            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

	
}


