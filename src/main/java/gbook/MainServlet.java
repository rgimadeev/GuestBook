package gbook;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainServlet extends HttpServlet {

    private BaseContact basecontact;
    private BaseBean basebean;

    public void init(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        basecontact = new BaseContact();
        basebean = new BaseBean();

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<Message> messages = (ArrayList<Message>) basecontact.getMessages();
        basebean.setMessage( messages);
        HttpSession session = request.getSession();
        session.setAttribute("userbase", basebean);
        RequestDispatcher rDispatch = getServletContext().getRequestDispatcher(
                "/index.jsp");
        rDispatch.forward(request, response);
    }

}
