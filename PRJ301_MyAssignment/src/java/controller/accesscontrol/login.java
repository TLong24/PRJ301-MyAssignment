/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.accesscontrol;

import dal.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.accesscontrol.User;

/**
 *
 * @author nlong
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserDBContext u = new UserDBContext();
        User account = u.get(username, password);     
        
        req.setAttribute("username", username);
        req.setAttribute("passw", password);
        
        if (account != null) {
            req.getSession().setAttribute("user", account);
            req.getRequestDispatcher("listplan").forward(req, resp);
        } else {
            req.setAttribute("error", "invalid username or password");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
        
        
    }

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
             req.getRequestDispatcher("login.jsp").forward(req, resp);
        }

    }
