package gbook;


import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DbPool {
    private static Connection con;
    private static DbConnect instance;
    private static DataSource dataSource;

    public DbPool() throws ClassNotFoundException, SQLException {

    }

    public static synchronized DbConnect getInstance() {
        if (instance == null) {
            try {
                instance = new DbConnect();
                Context ctx = new InitialContext();
                instance.dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/GuestBookDS");
                con = dataSource.getConnection();
            } catch (NamingException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}
