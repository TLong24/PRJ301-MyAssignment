/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DepartmentDBContext;
import dal.PlanDBContext;
import dal.ProductDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import model.Department;
import model.Plan;
import model.PlanCampaign;
import model.Product;

/**
 *
 * @author nlong
 */
public class ProductionPlanCreateController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductionPlanCreateController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductionPlanCreateController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DepartmentDBContext dbDept = new DepartmentDBContext();
        ProductDBContext dbProduct = new ProductDBContext();

        request.setAttribute("products", dbProduct.list());
        request.setAttribute("depts", dbDept.getDepartment("WS"));
        request.getRequestDispatcher("../insertPlan.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] pids = request.getParameterValues("pid");

        if (pids == null) {
            request.setAttribute("message", "No products were selected. Please select at least one product.");
            request.getRequestDispatcher("../insertPlan.jsp").forward(request, response);
            return;
        }

        Plan plan = new Plan();

        try {
            if (request.getParameter("from") != null && !request.getParameter("from").isBlank()) {
                plan.setStart(Date.valueOf(request.getParameter("from")));
            }
            if (request.getParameter("to") != null && !request.getParameter("to").isBlank()) {
                plan.setEnd(Date.valueOf(request.getParameter("to")));
            }
        } catch (IllegalArgumentException e) {
            request.setAttribute("message", "Invalid date format. Please enter a valid date.");
            request.getRequestDispatcher("../insertPlan.jsp").forward(request, response);
            return;
        }

        Department d = new Department();
        try {
            d.setDid(Integer.parseInt(request.getParameter("did")));
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid department ID. Please select a valid department.");
            request.getRequestDispatcher("../insertPlan.jsp").forward(request, response);
            return;
        }
        plan.setDept(d);
        plan.setCampains(new ArrayList<>());

        for (String pid : pids) {
            Product p = new Product();
            p.setPid(Integer.parseInt(pid));

            PlanCampaign c = new PlanCampaign();
            c.setProduct(p);

            String raw_quantity = request.getParameter("quantity" + pid);
            String raw_effort = request.getParameter("effort" + pid);

            try {
                c.setQuantity(raw_quantity != null && !raw_quantity.isBlank() ? Integer.parseInt(raw_quantity) : 0);
            } catch (NumberFormatException e) {
                c.setQuantity(0);
            }

            try {
                c.setEstimatedeffort(raw_effort != null && !raw_effort.isBlank() ? Float.parseFloat(raw_effort) : 0);
            } catch (NumberFormatException e) {
                c.setEstimatedeffort(Float.valueOf(0));
            }

            c.setPlan(plan);
            if (c.getQuantity() != 0 && c.getEstimatedeffort() != 0) {
                plan.getCampains().add(c);
            }
        }

        if (plan.getCampains().size() > 0) {
            PlanDBContext db = new PlanDBContext();
            db.insert(plan);
            response.getWriter().println("created a new plan!");
        } else {
            response.getWriter().println("your plan did not have any campains");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
