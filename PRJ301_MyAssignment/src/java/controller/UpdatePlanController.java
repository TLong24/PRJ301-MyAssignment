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
import java.util.List;

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

        // Fetch list of departments
        DepartmentDBContext departmentDB = new DepartmentDBContext();
        List<Department> departments = departmentDB.getDepartment("WS");

        // Set attributes for JSP
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
        
        // Fetch list of departments
        DepartmentDBContext departmentDB = new DepartmentDBContext();
        List<Department> departments = departmentDB.getDepartment("WS");

        // Set attributes for JSP
        request.setAttribute("departments", departments);

        // Kiểm tra và lấy thông tin từ request
        String rawPlid = request.getParameter("plid");
        request.setAttribute("plid", rawPlid);
        String rawStart = request.getParameter("start");
        request.setAttribute("start", rawStart);
        String rawEnd = request.getParameter("end");
        request.setAttribute("end", rawEnd);
        String rawDid = request.getParameter("did");
        request.setAttribute("did", rawDid);
        

        // Kiểm tra các trường bắt buộc
        if (rawPlid == null || rawPlid.isBlank()
                || rawStart == null || rawStart.isBlank()
                || rawEnd == null || rawEnd.isBlank()
                || rawDid == null || rawDid.isBlank()) {
            request.setAttribute("error", "Please ensure all fields are filled.");
            request.getRequestDispatcher("updatePlan.jsp").forward(request, response);
        }

        int plid = Integer.parseInt(rawPlid);
        Date start = Date.valueOf(rawStart);
        Date end = Date.valueOf(rawEnd);
        int did = Integer.parseInt(rawDid);

        // Kiểm tra xem plan có tồn tại hay không
        PlanDBContext planDB = new PlanDBContext();
        Plan existingPlan = planDB.get(plid);

        if (existingPlan == null) {
            request.setAttribute("error", "Invalid Plan Id (not found)");
            request.getRequestDispatcher("updatePlan.jsp").forward(request, response);
        }

        // Tạo đối tượng plan
        Plan plan = new Plan();
        plan.setPlId(plid);
        plan.setStart(start);
        plan.setEnd(end);

        Department department = new Department();
        department.setDid(did);
        plan.setDept(department);
        // Cập nhật plan trong DB
        
        try {
            PlanDBContext db =new PlanDBContext();
            db.update(plan);
            // Nếu không xảy ra ngoại lệ, cập nhật thành công
            response.sendRedirect(request.getContextPath() + "/listplan");
        } catch (IOException e) {
            request.setAttribute("error", "Update failed! Please try again.");
            request.getRequestDispatcher("updatePlan.jsp").forward(request, response);
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
