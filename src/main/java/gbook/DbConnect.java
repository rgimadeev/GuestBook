package gbook;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.text.ParseException;
import java.util.*;
import  javax.sql.DataSource;


public class DbConnect {

  //  String url = "jdbc:postgresql://localhost:5432/guest_book";
  //  Connection con = getConnection(url, "postgres", "317935");
 //  private static Connection con;
   // private static DbConnect instance;
 //   private static DataSource dataSource;
    Statement statement;
    ResultSet resultset;
    PreparedStatement stmt;
    InitialContext ctx = new InitialContext();
    DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/GuestBookDS");
    Connection conn = ds.getConnection();
    public DbConnect() throws ClassNotFoundException, SQLException, NamingException {
    }

    public ArrayList<Message> getMessages() {
        Message mes = null;

        ArrayList<Message> messages = new ArrayList<Message>();
        try {
            statement = conn.createStatement();
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
        }
        return messages;
    }

    public void insertMessage(Message message) throws SQLException {

        try {
            stmt = conn.prepareStatement("INSERT INTO message_table"
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
        }

    }
    public  java.sql.Timestamp getCurrentDate() throws ParseException {

        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());

    }
}



