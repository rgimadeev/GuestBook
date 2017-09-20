package gbook;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/new-message")
public class FormServlet extends HttpServlet {
    MessageService messageService = new MessageServiceImpl();
    static final Logger userLogger = LogManager.getLogger(FormServlet.class);

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/NewMessage.jsp").forward(req, resp);

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Message message = createMessage(req);
        SaveResult result = messageService.saveMessage(message);
        String s = null;
        resp.setContentType("application/json;charset=utf-8");
        try (PrintWriter out = resp.getWriter()) {
            req.setCharacterEncoding("UTF-8");
            Gson gson = new GsonBuilder().create();
            if (result.getErrors() == null)

            {
                s = "{\"success\": true}";

            } else {
                Map<String, Object> responseMap = new HashMap<>();
                responseMap.put("success", false);
                responseMap.put("errors", result.getErrors());
                String json = gson.toJson(responseMap);
                s = json.toString();

            }
            out.println(s);
        } catch (IOException e) {
            userLogger.error("error in class FormServlet (IOException): " + e.getMessage());
        }
    }

    private Message createMessage(HttpServletRequest req) {
        Message s = new Message();
        s.setAuthorName(req.getParameter("authorName"));
        s.setMessageText(req.getParameter("messageText"));
        return s;
    }
}

