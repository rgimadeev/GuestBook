package gbook;


import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class MainServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DbConnect baseconnect = null;
        try {
            baseconnect = new DbConnect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        ArrayList<Message> list = null;
        try {
            list = baseconnect.getMessages();
            request.setAttribute("messageList", list);
            request.getRequestDispatcher("/ListMessage.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return;
    }

}







