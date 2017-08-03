package gbook;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class MainServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DbConnect baseconnect = new DbConnect();
        ArrayList<Message> list = baseconnect.getMessages();
        request.setAttribute("messageList", list);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
        return;
    }

}







