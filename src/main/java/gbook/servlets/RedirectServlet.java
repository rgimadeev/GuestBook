package gbook.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("")
public class RedirectServlet extends HttpServlet {
    static final Logger userLogger = LogManager.getLogger(RedirectServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect("/messages");
        } catch (IOException e) {
            userLogger.error("error in class MessageValidationImpl (IOException) : " + e.getMessage());
        }
    }
}





