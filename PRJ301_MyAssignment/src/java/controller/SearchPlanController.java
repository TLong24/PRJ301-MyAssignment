/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import controller.accesscontrol.BaseRBACController;
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
import model.accesscontrol.User;

/**
 *
 * @author nlong
 */
@WebServlet(name = "SearchPlanController", urlPatterns = {"/searchPlan"})
public class SearchPlanController extends BaseRBACController {

    @Override
    protected void doAuthorizedGet(HttpServletRequest request, HttpServletResponse response, User loggeduser) throws ServletException, IOException {
        request.getRequestDispatcher("search.jsp").forward(request, response);
    }

    @Override
    protected void doAuthorizedPost(HttpServletRequest request, HttpServletResponse response, User loggeduser) throws ServletException, IOException {
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
