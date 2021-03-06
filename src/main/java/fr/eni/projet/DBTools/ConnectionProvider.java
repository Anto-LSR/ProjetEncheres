package fr.eni.projet.DBTools;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
public class ConnectionProvider {
    private static DataSource dataSource;
    static {
        Context context;
            try {
                context = new InitialContext();
                dataSource = (DataSource) context.lookup("java:comp/env/jdbc/encheresCNXPool");
            } catch (NamingException e) {
            	System.out.println("probleme de contexte"+e.getCause());
                e.printStackTrace();
            }
    }
    public static Connection getConnection() {
		Connection cnx = null;
    	try {
			cnx = dataSource.getConnection();
		} catch (SQLException e) {
			System.out.println("probleme de connexion"+e.getCause());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnx;
    }
    public static void closeConnection (Connection cnx) {
        try {
            cnx.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void closeConnection (Connection cnx, PreparedStatement pstmt) {
        try {
            pstmt.close();
            cnx.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void closeConnection (Connection cnx, Statement stmt) {
        try {
            stmt.close();
            cnx.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}