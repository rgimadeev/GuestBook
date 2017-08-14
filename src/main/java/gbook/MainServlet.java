package gbook;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class MainServlet extends HttpServlet {
    private MessageService messageService= new  MessageService();
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Message> list = messageService.selectMessage();
        request.setAttribute("messageList", list);
        request.getRequestDispatcher("/ListMessage.jsp").forward(request, response);
    }

}







