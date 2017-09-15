package gbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/new-message")
public class FormServlet extends HttpServlet {
    private MessageService messageService = new MessageServiceImpl();

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/NewMessage.jsp").forward(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Message mes = createMessage(req);
        SaveResult s = messageService.saveMessage(mes);
        if (s.getErrors() == null) {
            resp.sendRedirect("/messages?sendMes=1");
        } else {
            req.setAttribute("authorNameText", mes.getAuthorName());//чтоб текст в поле "Автор" оставался при нажатие на "сохранить"
            req.setAttribute("messageText", mes.getMessageDesc());//чтоб текст в поле "Текст сообщения" оставался при нажатие на "сохранить"
            req.setAttribute("errorText", s.getErrors());
            req.getRequestDispatcher("/NewMessage.jsp").forward(req, resp);
        }
    }

    private Message createMessage(HttpServletRequest req) {
        Message s = new Message();
        s.setAuthorName(req.getParameter("authorName"));
        s.setMessageDesc(req.getParameter("messageDesc"));
        return s;
    }

}

