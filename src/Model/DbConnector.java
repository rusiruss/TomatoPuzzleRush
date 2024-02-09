package Model;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbConnector {
	/*
     * This method creates a database connection
     */
	public static Connection connect() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tomatorushpuzzle", "root", "");
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            System.out.println("inter.DBConnect.connect()" + e);
        }
        return con;
    }
}
