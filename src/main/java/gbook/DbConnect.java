package gbook;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.text.ParseException;
import java.util.*;



public class DbConnect {

    public Connection getConnection ()  {
        InitialContext ctx = null;
        DataSource ds = null;
        try {
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/GuestBookDS");
        } catch (NamingException e) {
            System.out.println(e.getMessage());;
        }
        Connection conn = null;
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;

    }
    public ArrayList<Message> getMessages() throws SQLException {
        Connection dbConnection = null;
        Message mes = null;
        ArrayList<Message> messages = new ArrayList<Message>();
        try {
            dbConnection = getConnection();
            ResultSet resultset;
            Statement statement = dbConnection.createStatement();
            String sql = "SELECT autor_name, text_message, publication_date FROM message_table order by publication_date desc";
            resultset = statement.executeQuery(sql);
            while (resultset.next()) {
                mes = new Message();
                mes.setAutorName(resultset.getString("autor_name"));
                mes.setMessageDesc(resultset.getString("text_message"));
                mes.setPublicationDate(resultset.getTimestamp("publication_date"));
                messages.add(mes);
                System.out.println(mes.getAutorName() + " "
                        + mes.getMessageDesc() + " " + mes.getPublicationDate());
            }
        } catch (SQLException sql) {
            System.err.print("Error SQL : " + sql);
        } finally {
            if (dbConnection != null) {
                dbConnection.close();
            }
            return messages;

        }
    }

    public void insertMessage(Message message) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement stmt=null;
        try {
            dbConnection=getConnection();
            stmt = dbConnection.prepareStatement("INSERT INTO message_table"
                    + "(autor_name, text_message,publication_date)"
                    + "VALUES( ?,  ?, ?)");
            stmt.setString(1, message.getAutorName());
            stmt.setString(2, message.getMessageDesc());
            stmt.setTimestamp(3,new java.sql.Timestamp (getCurrentDate().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        if (dbConnection != null) {
            dbConnection.close();
        }
    }
    }
    public  java.sql.Timestamp getCurrentDate() throws ParseException {

        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());

    }
}



