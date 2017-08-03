package gbook;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageServlet  extends HttpServlet {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:MM:SS");
    Connection con;

        protected void processRequest (HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
            // Установка кодировки для принятия параметров
            req.setCharacterEncoding("UTF-8");
            req.getRequestDispatcher("/Message.jsp").forward(req, resp);

            // Если пользователь нажал кнопку ОК – тогда добавляем новое сообщение)
            if (req.getParameter("SAVE") != null) {
                try {
                    insertMessage(req);
                } catch (ParseException e) {
                    e.printStackTrace();

                } catch (SQLException sql_e) {
                    sql_e.printStackTrace();
                    throw new IOException(sql_e.getMessage());
                }

            }
        }


    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void insertMessage(HttpServletRequest req) throws SQLException, ParseException {
        DbConnect db = new DbConnect();
        Message s = prepareMessage(req);
        db.insertMessage(s);

    }

    private Message prepareMessage(HttpServletRequest req) throws ParseException {
        Message s = new Message();
        s.setAutorName(req.getParameter("autorName"));
        s.setMessageDesc(req.getParameter("messageDesc"));
        s.setPublicationDate(sdf.parse(req.getParameter("publication_date")));
        return s;
    }


}

