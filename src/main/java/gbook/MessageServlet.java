package gbook;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;

public class MessageServlet  extends HttpServlet {

        protected void processRequest (HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException, NamingException {
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
            if(req.getParameter("messageDesc").length()<7000) {
                try {
                    processRequest(req, resp);
                    resp.setContentType("text/html;charset=utf-8");
                    String add_message = "Сообщение было добавлено";
                    req.setAttribute("message", add_message);
                    MainServlet ms=new MainServlet();
                    ms.doGet(req,resp);
                } catch (NamingException e) {
                    e.printStackTrace();
                }

            }
            else {
                String autorNameText=req.getParameter("autorName");
                req.setAttribute("autorNameTxt",autorNameText);//чтоб текст оставался при нажатие на "сохранить"
                String textMessage = req.getParameter("messageDesc");
                req.setAttribute("messageTxt", textMessage);//чтоб текст оставался при нажатие на "сохранить"
                String maxMessageLenght="Максимальное кол-во символов в тексте: 7000";
                req.setAttribute("maxMes",maxMessageLenght);
                req.getRequestDispatcher("/Message.jsp").forward(req, resp);
            }
        }
         else if(req.getParameter("Save")!=null && req.getParameter("autorName")=="" && req.getParameter("messageDesc")!=""){
                  String textMessage = req.getParameter("messageDesc");
                  req.setAttribute("messageTxt", textMessage);//чтоб текст оставался при нажатие на "сохранить"
                  String autor_message = "Поле 'Автор' не должно быть пустым";
                  req.setAttribute("autorMes", autor_message);
                  req.getRequestDispatcher("/Message.jsp").forward(req, resp);
              }
          else if(req.getParameter("Save")!=null && req.getParameter("autorName")!="" && req.getParameter("messageDesc")=="") {
            String autorNameText=req.getParameter("autorName");
            req.setAttribute("autorNameTxt",autorNameText);//чтоб текст оставался при нажатие на "сохранить"
            String text_message = "Поле 'Текст сообщения' не должно быть пустым";
            req.setAttribute("textMes",text_message);
            req.getRequestDispatcher("/Message.jsp").forward(req, resp);
        }
          else {
            String text_message = "Поле 'Текст сообщения' не должно быть пустым";
            String autor_message = "Поле 'Автор' не должно быть пустым" ;
            req.setAttribute("textMes",text_message);
            req.setAttribute("autorMes",autor_message);
            req.getRequestDispatcher("/Message.jsp").forward(req, resp);
        }
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

