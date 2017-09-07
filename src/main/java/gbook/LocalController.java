package gbook;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LocalController {
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String redirectToMainController()  {
        return "redirect:/messages";
    }
}






