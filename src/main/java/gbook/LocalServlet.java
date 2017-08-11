package gbook;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//редирект
public class LocalServlet extends HttpServlet{

        protected void doGet(HttpServletRequest request, HttpServletResponse response){
            try {
                response.sendRedirect("/messages");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }





