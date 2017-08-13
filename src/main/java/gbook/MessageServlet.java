package gbook;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.ParseException;

public class MessageServlet  extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException {
        insertMessage(req);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/Message.jsp").forward(req, resp);

    }
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String s=null;
        String reqAut=req.getParameter("autorName");
        String reqMesc=req.getParameter("messageDesc");
        if (reqAut != "" && reqMesc != "" && reqMesc.length() <= 4000 && reqAut.length() <= 35) {
            s = "{\"success\": true}";
            processRequest(req, resp);
        }
        else if (reqAut == "" && reqMesc != "")
        {

             s = "{\"success\": false, \"errors\":{\"autorErr\": \"Поле 'Автор' должно быть заполнено\",\"TextErr\": \"\"}}";

        }
             else if  (reqAut != "" && reqMesc == "") {
            s = "{\"success\": false, \"errors\":{\"autorErr\": \"\",\"TextErr\": \"Поле 'Текст сообщения' должно быть заполнено\"}}";

        }
        else if(reqAut != "" && reqMesc != "" && reqMesc.length() > 4000 ) {
            s = "{\"success\": false, \"errors\":{\"autorErr\": \"\",\"TextErr\": \"Максимальный размер поля 4000 символов\"}}";
           }
        else if (reqAut != "" && reqMesc != "" && reqAut.length() > 35 ){
            s = "{\"success\": false, \"errors\":{\"autorErr\": \"Максимальный размер поля 35 символов\",\"TextErr\": \"\"}}";
        }
            else {
            s = "{\"success\": false, \"errors\":{\"autorErr\": \"Поле 'Автор' должно быть заполнено\",\"TextErr\": \"Поле 'Текст сообщения' должно быть заполнено\"}}";

        }
        out.println(s);
        out.flush();
        out.close();

    }
    private void insertMessage(HttpServletRequest req)  {
        DbConnect db = new DbConnect();
        Message s = prepareMessage(req);
        try {
            db.insertMessage(s);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private Message prepareMessage(HttpServletRequest req)  {
        Message s =new Message();
        s.setAutorName(req.getParameter("autorName"));
        s.setMessageDesc(req.getParameter("messageDesc"));
        return s;
    }
}

