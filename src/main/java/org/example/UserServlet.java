package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "JdbcServlet", urlPatterns = "/products")
public class UserServlet extends HttpServlet {
    private Logger logger = LoggerFactory.getLogger(UserServlet.class);
    private Connection conn;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        conn = (Connection) context.getAttribute("jdbcConnection");
        if (conn == null) {
            throw new ServletException("No JDBC connection in Servlet Context");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse
            resp) throws ServletException, IOException {
        logger.info("Get all tables");
        try {
            Statement stmt = conn.createStatement();
            ArrayList<Product> products = new ArrayList<Product>();
            ResultSet rs = stmt.executeQuery("SELECT * FROM product.names;");
            while (rs.next()) {
                int productId = rs.getInt(1);
                String productName = rs.getString(2);
                double productCost = rs.getDouble(3);
                products.add(new Product(productId,productName,productCost));
                resp.getWriter().println("<p> # " + productId + "</p>");
                resp.getWriter().println("<p> NAME: " + productName + "</p>");
                resp.getWriter().println("<p> COST: " + productCost + "</p>");
                resp.getWriter().println("<p>" + "-------------------------" + "</p>");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
