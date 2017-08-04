package gbook;

import org.apache.log4j.LogManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageServlet  extends HttpServlet {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:MM:SS");

        protected void processRequest (HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
            // Установка кодировки для принятия параметров
            req.setCharacterEncoding("UTF-8");
            PrintWriter out=resp.getWriter();
            // Если пользователь нажал кнопку ОК – тогда добавляем новое сообщение)
            if (req.getParameter("SAVE") != null) {
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

       }

   public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
       req.getRequestDispatcher("/Message.jsp").forward(req, resp);


    }
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
        resp.sendRedirect("/messages");
    }



    private void insertMessage(HttpServletRequest req) throws SQLException, ParseException, IOException, ClassNotFoundException {
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

