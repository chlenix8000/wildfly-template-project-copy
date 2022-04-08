package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "FirstServlet", urlPatterns = "/first_servlet")
public class FirstServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(FirstServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("New GET request");
        resp.getWriter().printf("<h1>New GET request base example.. Add.. </h1>");

        /*resp.sendRedirect("https://ya.ru");*/

        /*req.setAttribute("newAttr", "attrValue");
        getServletContext().getRequestDispatcher("/basic_servlet").forward(req, resp);*/

        logger.info("New Get request with includes");
        getServletContext().getRequestDispatcher("/header.html").include(req, resp);
        resp.getWriter().println("<p>Response body from servlet</p>");
        getServletContext().getRequestDispatcher("/footer.html").include(req, resp);

        HttpSession sessionObj = req.getSession(true);
        sessionObj.setMaxInactiveInterval(1);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        logger.info("New POST request");
        resp.getWriter().printf("<h1>New POST request</h1>");
    }
}
