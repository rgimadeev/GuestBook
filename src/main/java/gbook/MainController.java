package gbook;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

@Controller
public class MainController {
    private MessageService messageService= new  MessageService();
  @RequestMapping(value = "/messages", method = RequestMethod.GET)
  public String showAllMessages(Model model ){
      List<Message> list = messageService.selectMessage();
      model.addAttribute("messageList", list);
      return "ListMessage";
      }
    }









