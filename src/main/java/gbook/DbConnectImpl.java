package gbook;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbConnectImpl implements DbConnect {
    static final Logger userLogger = LogManager.getLogger(DbConnectImpl.class);

    public Connection getDbConnection() {
        Connection conn = null;
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/GuestBookDS");
            if (ds == null) {
                throw new DbConnectException("cannot find datasource");
            }
            return ds.getConnection();

        } catch (NamingException | SQLException ex) {
            userLogger.error("error message: " + ex.getMessage());
            throw new DbConnectException(ex.getMessage());
        }

    }

    public List<Message> getMessages() {
        Message mes = null;
        ResultSet resultset;
        List<Message> messages = new ArrayList<Message>();
        try (Connection dbconnection = getDbConnection();
             Statement statement = dbconnection.createStatement();
        ) {
            String sql = "SELECT author_name, text_message, publication_date FROM message_table order by publication_date desc";
            resultset = statement.executeQuery(sql);
            while (resultset.next()) {
                mes = new Message();
                mes.setAuthorName(resultset.getString("author_name"));
                mes.setMessageDesc(resultset.getString("text_message"));
                mes.setPublicationDate(resultset.getTimestamp("publication_date"));
                messages.add(mes);
                System.out.println(mes.getAuthorName() + " "
                        + mes.getMessageDesc() + " " + mes.getPublicationDate());
            }
        } catch (SQLException e) {
            userLogger.error("error sql: " + e.getMessage());
            throw new DbConnectException(e.getMessage());
        }
        return messages;
    }

    public void insertMessage(Message message) {

        try (Connection dbconnection = getDbConnection();
             PreparedStatement stmt = dbconnection.prepareStatement("INSERT INTO message_table"
                     + "(author_name, text_message,publication_date)"
                     + "VALUES( ?,  ?, ?)");
        ) {
            stmt.setString(1, message.getAuthorName());
            stmt.setString(2, message.getMessageDesc());
            stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            userLogger.error("error sql: " + e.getMessage());
            throw new DbConnectException(e.getMessage());
        }
    }
}

