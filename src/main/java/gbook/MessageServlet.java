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
            throws ServletException, IOException, NamingException {
        // Установка кодировки для принятия параметров
        try {
            insertMessage(req);
        } catch (ParseException e) {
            e.printStackTrace();

        } catch (SQLException sql_e) {
            sql_e.printStackTrace();
            throw new IOException(sql_e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

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
        if (reqAut != "" && reqMesc != "" && req.getParameter("messageDesc").length() <= 4000) {
            try {
                s = "{\"success\": true}";
                processRequest(req, resp);
                String queryString = req.getQueryString();
                if (queryString=="1"){
                    req.setAttribute("sMes",queryString );
                    req.getRequestDispatcher("/ListMessage.jsp").forward(req, resp);

                }
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
        else if (reqAut == "" && reqMesc != "")
        {

             s = "{\"success\": false, \"errors\":{\"autorErr\": \"Поле 'Автор' должно быть заполнено\",\"TextErr\": \"\"}}";

        }
             else if  (reqAut != "" && reqMesc == "") {
            s = "{\"success\": false, \"errors\":{\"autorErr\": \"\",\"TextErr\": \"Поле 'Текст сообщения' должно быть заполнено\"}}";

        }
        else if(reqAut != "" && reqMesc != "" && req.getParameter("messageDesc").length() > 4000 ) {
            s = "{\"success\": false, \"errors\":{\"autorErr\": \"\",\"TextErr\": \"Максимальный размер поля 4000 символов\"}}";
           }
            else {
            s = "{\"success\": false, \"errors\":{\"autorErr\": \"Поле 'Автор' должно быть заполнено\",\"TextErr\": \"Поле 'Текст сообщения' должно быть заполнено\"}}";

        }
        out.println(s);
        out.flush();
        out.close();

    }

    private void insertMessage(HttpServletRequest req) throws SQLException, ParseException, IOException, ClassNotFoundException, NamingException {
        DbConnect db = new DbConnect();
        Message s = prepareMessage(req);
        db.insertMessage(s);
    }

    private Message prepareMessage(HttpServletRequest req) throws ParseException, IOException {
        Message s =new Message();
        s.setAutorName(req.getParameter("autorName"));
        s.setMessageDesc(req.getParameter("messageDesc"));
        return s;
    }
}

