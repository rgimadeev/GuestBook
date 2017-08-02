package gbook;


import gbook.BaseConnect;
import gbook.Message;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MainServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BaseConnect baseconnect = new BaseConnect();
        ArrayList<Message> list = baseconnect.getMessages();
        request.setAttribute("messageList", list);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

}







