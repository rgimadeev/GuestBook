package gbook;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MessageServlet  extends HttpServlet {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:MM:SS");

        protected void processRequest (HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
            // Установка кодировки для принятия параметров
            req.setCharacterEncoding("UTF-8");
            // Если пользователь нажал кнопку ОК – тогда добавляем новое сообщение)
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

        if (  req.getParameter("Save")!=null && req.getParameter("autorName")!="" && req.getParameter("messageDesc")!="") {
                processRequest(req, resp);
            DbConnect baseconnect= null;
            try {
                resp.setContentType("text/html;charset=utf-8");
                baseconnect = new DbConnect();
                ArrayList<Message> list = baseconnect.getMessages();
                req.setAttribute("messageList", list);
                String add_message = "Сообщение было добавлено";
                req.setAttribute("message", add_message);
                req.getRequestDispatcher("/ListMessage.jsp").forward(req, resp);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            }
         else if(req.getParameter("Save")!=null && req.getParameter("autorName")=="" && req.getParameter("messageDesc")!=""){
                String autor_message = "Поле 'Автор' не должно быть пустым" ;
                 req.setAttribute("autormes",autor_message);
            req.getRequestDispatcher("/Message.jsp").forward(req, resp);
            }
          else if(req.getParameter("Save")!=null && req.getParameter("autorName")!="" && req.getParameter("messageDesc")=="") {
            String text_message = "Поле 'Текст сообщения' не должно быть пустым";
            req.setAttribute("textmes",text_message);
            req.getRequestDispatcher("/Message.jsp").forward(req, resp);
        }
          else if(req.getParameter("Save")!=null && req.getParameter("autorName")=="" && req.getParameter("messageDesc")=="") {
            String text_message = "Поле 'Текст сообщения' не должно быть пустым";
            String autor_message = "Поле 'Автор' не должно быть пустым" ;
            req.setAttribute("textmes",text_message);
            req.setAttribute("autormes",autor_message);
            req.getRequestDispatcher("/Message.jsp").forward(req, resp);
        }
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

