package gbook;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;


public class MessageServlet  extends HttpServlet {
      private MessageService messageService= new  MessageService();
      private MessageValidation messageValidation=new MessageValidation ();
      private HashMap<String,String> hashmap;

   public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
           req.getRequestDispatcher("/Message.jsp").forward(req, resp);

    }
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Message mes = createMessage(req);
        SaveResult s = messageService.saveMessage(mes);
        if (s.getErrors()== null) {
            resp.sendRedirect("/messages?sendMes=1");
        }
        else {
            req.setAttribute("autorNameTxt", mes.getAutorName());//чтоб текст оставался при нажатие на "сохранить"
            req.setAttribute("messageTxt", mes.getMessageDesc());
            req.setAttribute("errorTxt",s.getErrors());
            req.getRequestDispatcher("/Message.jsp").forward(req, resp);
        }
    }

    private Message createMessage(HttpServletRequest req)  {
        Message s =new Message();
        s.setAutorName(req.getParameter("autorName"));
        s.setMessageDesc(req.getParameter("messageDesc"));
        return s;
    }

}

