/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DepartmentDBContext;
import dal.PlanDBContext;
import model.Department;
import model.Plan;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author nlong
 */
public class UpdatePlanController extends HttpServlet {

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
            out.println("<title>Servlet UpdatePlanController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdatePlanController at " + request.getContextPath() + "</h1>");
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
        // Get plan id from request
        int plid = Integer.parseInt(request.getParameter("plid"));

        // Fetch plan details from DB
        PlanDBContext planDB = new PlanDBContext();
        Plan plan = planDB.get(plid);

        // Fetch list of departments
        DepartmentDBContext departmentDB = new DepartmentDBContext();
        ArrayList<Department> departments = departmentDB.list();

        // Set attributes for JSP
        request.setAttribute("plan", plan);
        request.setAttribute("departments", departments);

        // Forward to updatePlan.jsp
        request.getRequestDispatcher("updatePlan.jsp").forward(request, response);
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
        // Get plan details from request
        int plid = Integer.parseInt(request.getParameter("plid"));
        Date start = Date.valueOf(request.getParameter("start"));
        Date end = Date.valueOf(request.getParameter("end"));
        int did = Integer.parseInt(request.getParameter("did"));

        // Create plan object
        Plan plan = new Plan();
        plan.setPlId(plid);
        plan.setStart(start);
        plan.setEnd(end);

        Department department = new Department();
        department.setDid(did);
        plan.setDept(department);

        // Update plan in DB
        PlanDBContext planDB = new PlanDBContext();
        planDB.update(plan);

        // Redirect to home page after update
        response.sendRedirect("home.jsp");
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
