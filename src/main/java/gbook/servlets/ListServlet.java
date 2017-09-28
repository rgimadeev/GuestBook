package gbook.servlets;


import gbook.model.Message;
import gbook.model.MessageService;
import gbook.model.MessageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/messages")
public class ListServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MessageService messageService = new MessageServiceImpl();
        List<Message> list = messageService.selectMessage();
        request.setAttribute("messageList", list);
        request.getRequestDispatcher("/pages/ListMessage.jsp").forward(request, response);
    }

}







