package gbook;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DbConnect {
    Connection con;

    public DbConnect() {
    }

    public ArrayList<Message> getMessages() {
        Message mes = null;

        ArrayList<Message> messages = new ArrayList<Message>();
        try {

            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/guest_book";
            con = DriverManager.getConnection(url, "postgres", "317935");
            Statement statement = con.createStatement();
            String requete1 = "SELECT autor_name, text_message, publication_date FROM message_table order by publication_date desc";
            ResultSet resultset = statement.executeQuery(requete1);

            while (resultset.next()) {
                mes = new Message();
                mes.setAutorName(resultset.getString("autor_name"));
                mes.setMessageDesc(resultset.getString("text_message"));
                mes.setPublicationDate(resultset.getDate("publication_date"));
                messages.add(mes);
                System.out.println(mes.getAutorName() + " "
                        + mes.getMessageDesc() + " " + mes.getPublicationDate());

            }
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur lors du chargement du pilote : " + e);
        } catch (SQLException sqle) {
            System.err.print("Erreur SQL : " + sqle);
        }
        return messages;
    }

    public void insertMessage(Message message) {

        try {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO message_table"
                    + "(autor_name, text_message, publication_date)"
                    + "VALUES( ?,  ?,  ?)");
            stmt.setString(1, message.getAutorName());
            stmt.setString(2, message.getMessageDesc());
            stmt.setDate(3, new Date(message.getPublicationDate().getTime()));
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
   /* public static void main(String[] args) {
        DbConnect b=new DbConnect();
        b.insertMessage();
    }
}*/

