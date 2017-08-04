package gbook;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.activation.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import static java.sql.DriverManager.getConnection;


public class DbConnect {

    String url = "jdbc:postgresql://localhost:5432/guest_book";
    Connection con = getConnection(url, "postgres", "317935");
    Statement statement;
    ResultSet resultset;
    PreparedStatement stmt;
    public DbConnect() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
    }



    public ArrayList<Message> getMessages() {
        Message mes = null;

        ArrayList<Message> messages = new ArrayList<Message>();
        try {
            statement = con.createStatement();
            String requete1 = "SELECT autor_name, text_message, publication_date FROM message_table order by publication_date desc";
            resultset = statement.executeQuery(requete1);

            while (resultset.next()) {
                mes = new Message();
                mes.setAutorName(resultset.getString("autor_name"));
                mes.setMessageDesc(resultset.getString("text_message"));
                mes.setPublicationDate(resultset.getDate("publication_date"));
                messages.add(mes);
                System.out.println(mes.getAutorName() + " "
                        + mes.getMessageDesc() + " " + mes.getPublicationDate());

            }
        } catch (SQLException sqle) {
            System.err.print("Erreur SQL : " + sqle);
        }
        return messages;
    }

    public void insertMessage(Message message) {

        try {
            stmt = con.prepareStatement("INSERT INTO message_table"
                    + "(autor_name, text_message,publication_date)"
                    + "VALUES( ?,  ?, ?)");
            stmt.setString(1, message.getAutorName());
            stmt.setString(2, message.getMessageDesc());
            stmt.setTimestamp(3, getCurrentDate());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private static java.sql.Timestamp getCurrentDate() throws ParseException {

        java.util.Date today = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:MM:SS");
        String str=sdf.format(today);//перевод текущей даты в строку
        java.util.Date date=  sdf.parse(str);
        return new java.sql.Timestamp(date.getTime());

    }

}

