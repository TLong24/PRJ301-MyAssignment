/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.PlanDBContext;
import model.Plan;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.sql.Date;

/**
 *
 * @author nlong
 */
@WebServlet(name = "SearchPlanController", urlPatterns = {"/searchPlan"})
public class SearchPlanController extends HttpServlet {

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
            out.println("<title>Servlet SearchPlanController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchPlanController at " + request.getContextPath() + "</h1>");
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
        String planIdStr = request.getParameter("planId");
        Integer planId = null;

        // Kiểm tra nếu Plan ID là một số nguyên hợp lệ
        if (planIdStr != null && !planIdStr.isBlank()) {
            try {
                planId = Integer.valueOf(planIdStr);
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Invalid Plan ID. Please enter a valid integer.");
                request.getRequestDispatcher("search.jsp").forward(request, response);
                return;
            }
        }

        Date startDate = null;
        if (request.getParameter("startDate") != null && !request.getParameter("startDate").isBlank()) {
            startDate = Date.valueOf(request.getParameter("startDate"));
        }

        Date endDate = null;
        if (request.getParameter("endDate") != null && !request.getParameter("endDate").isBlank()) {
            endDate = Date.valueOf(request.getParameter("endDate"));
        }

        Plan searchCiteria = new Plan(planId, null, startDate, endDate);

        // Tạo đối tượng PlanDBContext để lấy dữ liệu
        PlanDBContext db = new PlanDBContext();
        List<Plan> listPlan = db.searchPlans(searchCiteria);

        // Đặt danh sách kế hoạch vào request và chuyển tiếp tới search.jsp
        request.setAttribute("listPlan", listPlan);
        request.getRequestDispatcher("search.jsp").forward(request, response);
    }

}
