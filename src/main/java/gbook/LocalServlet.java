package gbook;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LocalServlet extends HttpServlet{

        protected void doGet(HttpServletRequest request, HttpServletResponse response){
            try {
                response.sendRedirect("/messages");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }





