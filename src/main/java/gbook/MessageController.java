package gbook;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
public class MessageController extends HttpServlet {
      private MessageService messageService= new  MessageService();
    @RequestMapping(value = "/new-message", method = RequestMethod.GET)
    public String transitionOnPage(){
        return "Message";
    }
    @RequestMapping(value = "/new-message", method = RequestMethod.POST)
    public String changesOnPage(HttpServletRequest req,Model model) throws UnsupportedEncodingException{
        req.setCharacterEncoding("UTF-8");
        Message mes = createMessage(req);
        SaveResult s = messageService.saveMessage(mes);
        if (s.getErrors()== null) {
            return "redirect:/messages?sendMes=1";
        }
        else {
            model.addAttribute("autorNameTxt",mes.getAutorName());//чтоб текст в поле "Автор" оставался при нажатие на "сохранить"
            model.addAttribute("messageTxt", mes.getTextMessage());//чтоб текст в поле "Текст сообщения" оставался при нажатие на "сохранить"
            model.addAttribute("errorTxt",s.getErrors());
            return "Message";
        }
    }
    private Message createMessage(HttpServletRequest req)  {
        Message s =new Message();
        s.setAutorName(req.getParameter("autorName"));
        s.setTextMessage(req.getParameter("messageDesc"));
        return s;
    }

}

