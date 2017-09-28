package gbook.db;

import gbook.model.Message;
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

    public Connection getConnection() {
        InitialContext ctx = null;
        DataSource ds = null;
        Connection conn = null;
        try {
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/GuestBookDS");
            if (ds == null) {
                throw new DbConnectException("datasource is not find");
            }
            return ds.getConnection();
        } catch (NamingException | SQLException e) {
            userLogger.error("error in class DbConnectImpl method getConnection (NamingException,SQLException): " + e.getMessage());
            throw new DbConnectException(e.getMessage());
        }


    }

    public List<Message> getMessages() {

        Message mes = null;
        List<Message> messages = new ArrayList<Message>();
        try (Connection dbConnection = getConnection();
             Statement statement = dbConnection.createStatement()) {
            ResultSet resultset;
            String sql = "SELECT author_name, text_message, publication_date FROM message_table order by publication_date desc";
            resultset = statement.executeQuery(sql);
            while (resultset.next()) {
                mes = new Message();
                mes.setAuthorName(resultset.getString("author_name"));
                mes.setMessageText(resultset.getString("text_message"));
                mes.setPublicationDate(resultset.getTimestamp("publication_date"));
                messages.add(mes);
                System.out.println(mes.getAuthorName() + " "
                        + mes.getMessageText() + " " + mes.getPublicationDate());
            }
        } catch (SQLException e) {
            userLogger.error("error in class DbConnectImpl(SQLException) : " + e.getMessage());
            throw new DbConnectException(e.getMessage());
        }
        return messages;

    }

    public void insertMessage(Message message) {
        try (Connection dbConnection = getConnection();
             PreparedStatement stmt = dbConnection.prepareStatement("INSERT INTO message_table"
                     + "(author_name, text_message,publication_date)"
                     + "VALUES( ?,  ?, ?)");) {
            stmt.setString(1, message.getAuthorName());
            stmt.setString(2, message.getMessageText());
            stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            userLogger.error("error in class DbConnectImpl method insert (SQLException): " + e.getMessage());
            throw new DbConnectException(e.getMessage());
        }
    }


}



