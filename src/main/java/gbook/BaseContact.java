package gbook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseContact {
    public BaseContact() {
    }

    public List<Message> getMessages() {
        Message mes = null;
        List<Message> messages = new ArrayList<Message>();
        try {

            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/guest_book";
            Connection con = DriverManager.getConnection(url, "postgres", "317935");
            Statement statement = con.createStatement();
            String requete1 = "SELECT autor_name, text_message, publication_date FROM message_table";
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
            con.close();
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur lors du chargement du pilote : " + e);
        } catch (SQLException sqle) {
            System.err.print("Erreur SQL : " + sqle);
        }
        return messages;
    }
}

