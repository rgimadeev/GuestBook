package gbook;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class MessageServlet  extends HttpServlet {
    MessageService messageService=new MessageService();
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/Message.jsp").forward(req, resp);

    }
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=utf-8");
        Message message=createMessage(req);
        SaveResult result=messageService.saveMessage(message);
        PrintWriter out = resp.getWriter();
        String s=null;
        System.out.println(result.getHashMap());
        if (result.getHashMap()==null){
            s="{\"success\": true}" ;
        }
        else  if(result.getHashMap().get("s1") != null) {
            s = result.getHashMap().get("s1");
        }
       else if (result.getHashMap().get("s2") != null) {
            s = result.getHashMap().get("s2");
        }
        else if (result.getHashMap().get("s3") != null) {
            s = result.getHashMap().get("s3");
        }
        else if (result.getHashMap().get("s4") != null) {
            s = result.getHashMap().get("s4");
        }
        else if (result.getHashMap().get("s5") != null) {
            s = result.getHashMap().get("s5");
        }
        out.println(s);
        out.flush();
        out.close();
    }

    private Message createMessage(HttpServletRequest req)  {
        Message s =new Message();
        s.setAutorName(req.getParameter("autorName"));
        s.setMessageDesc(req.getParameter("messageDesc"));
        return s;
    }
}

