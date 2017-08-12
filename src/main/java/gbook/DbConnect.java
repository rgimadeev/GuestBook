package gbook;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

import static jdk.nashorn.internal.objects.NativeMath.log;


public class DbConnect {
    public  Connection getDBConnection() {
        Connection conn = null;
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/GuestBookDS");
            conn = ds.getConnection();
        } catch (NamingException ex) {
            log("Error", ex);
        } catch (SQLException ex) {
            log("Error SQL", ex);
        }
        return conn;

    }
        public ArrayList<Message> getMessages () {
        Connection dbconnection=null;
        Message mes = null;
        ResultSet resultset;
        ArrayList<Message> messages = new ArrayList<Message>();
        try  {
            dbconnection=getDBConnection();
            Statement statement =dbconnection.createStatement();
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
        } catch (SQLException e) {
            log("Error SQL", e);
        }
            return messages;
    }

    public void insertMessage(Message message) {

        try (Connection dbconnection=getDBConnection();
             PreparedStatement stmt = dbconnection.prepareStatement("INSERT INTO message_table"
                     + "(autor_name, text_message,publication_date)"
                     + "VALUES( ?,  ?, ?)");
        ){
            stmt.setString(1, message.getAutorName());
            stmt.setString(2, message.getMessageDesc());
            stmt.setTimestamp(3,new java.sql.Timestamp (getCurrentDate().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            log( "Error SQL" , e);
        }
    }
    public  java.sql.Timestamp getCurrentDate()  {
        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());

    }
}

