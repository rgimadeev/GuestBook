package gbook;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DbConnect {
    Statement statement;
    ResultSet resultset;
    PreparedStatement stmt;
    String url = "jdbc:postgresql://localhost:5432/guest_book";
    Connection con = DriverManager.getConnection(url, "postgres", "317935");

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
        }

    }

    private static java.sql.Timestamp getCurrentDate() {

        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());

    }
}

