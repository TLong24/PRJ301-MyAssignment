/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import controller.accesscontrol.BaseRBACController;
import dal.PlanDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Plan;
import dal.PlanDBContext;
import model.accesscontrol.User;

/**
 *
 * @author nlong
 */
@WebServlet(name = "listPlan", urlPatterns = {"/listplan"})
public class PlanController extends BaseRBACController {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private void processRequests(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PlanDBContext db = new PlanDBContext();
        List<Plan> listPlan = db.getPlans();

        request.setAttribute("listPlan", listPlan);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }


    @Override
    protected void doAuthorizedGet(HttpServletRequest req, HttpServletResponse resp, User loggeduser) throws ServletException, IOException {
        processRequests(req, resp);
    }

    @Override
    protected void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User loggeduser) throws ServletException, IOException {
        processRequests(req, resp);
    }

}
