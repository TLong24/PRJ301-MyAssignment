/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import controller.accesscontrol.BaseRBACController;
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
import model.accesscontrol.User;

/**
 *
 * @author nlong
 */
public class ProductionPlanCreateController extends BaseRBACController {

    @Override
    protected void doAuthorizedGet(HttpServletRequest request, HttpServletResponse response, User loggeduser) throws ServletException, IOException {
        DepartmentDBContext dbDept = new DepartmentDBContext();
        ProductDBContext dbProduct = new ProductDBContext();

        request.setAttribute("products", dbProduct.list());
        request.setAttribute("depts", dbDept.getDepartment("WS"));
        request.getRequestDispatcher("/insertPlan.jsp").forward(request, response);
    }

    @Override
    protected void doAuthorizedPost(HttpServletRequest request, HttpServletResponse response, User loggeduser) throws ServletException, IOException {
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
                c.setEstimatedeffort(raw_effort != null && !raw_effort.isBlank() ? Integer.parseInt(raw_effort) : 0);
            } catch (NumberFormatException e) {
                c.setEstimatedeffort(0);
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

}
