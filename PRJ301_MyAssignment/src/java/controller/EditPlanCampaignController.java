/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import controller.accesscontrol.BaseRBACController;
import dal.PlanCampaignDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Plan;
import model.PlanCampaign;
import model.Product;
import model.accesscontrol.User;

/**
 *
 * @author nlong
 */
public class EditPlanCampaignController extends BaseRBACController {

    

    @Override
    protected void doAuthorizedGet(HttpServletRequest request, HttpServletResponse response, User loggeduser) throws ServletException, IOException {
        int camid = Integer.parseInt(request.getParameter("camid"));

        PlanCampaignDBContext db = new PlanCampaignDBContext();
        PlanCampaign campaign = db.get(camid);

        request.setAttribute("campaign", campaign);
        request.getRequestDispatcher("editCampaign.jsp").forward(request, response);
    }

    @Override
    protected void doAuthorizedPost(HttpServletRequest request, HttpServletResponse response, User loggeduser) throws ServletException, IOException {
        try {
            // Kiểm tra và lấy giá trị camid
            String camidStr = request.getParameter("camid");
            if (camidStr == null || camidStr.isEmpty()) {
                request.setAttribute("error", "Error: Campaign ID is required");
                return;
            }
            int camid = Integer.parseInt(camidStr);

            // Kiểm tra và lấy giá trị plid
            String plidStr = request.getParameter("plid");
            if (plidStr == null || plidStr.isEmpty()) {
                response.getWriter().println("Error: Plan ID is required.");
                return;
            }
            int plid = Integer.parseInt(plidStr);

            // Kiểm tra và lấy giá trị pid
            String pidStr = request.getParameter("pid");
            if (pidStr == null || pidStr.isEmpty()) {
                response.getWriter().println("Error: Product ID is required.");
                return;
            }
            int pid = Integer.parseInt(pidStr);

            // Kiểm tra và lấy giá trị quantity
            String quantityStr = request.getParameter("quantity");
            if (quantityStr == null || quantityStr.isEmpty()) {
                response.getWriter().println("Error: Quantity is required.");
                return;
            }
            int quantity = Integer.parseInt(quantityStr);

            // Kiểm tra và lấy giá trị estimatedeffort
            String effortStr = request.getParameter("estimatedeffort");
            if (effortStr == null || effortStr.isEmpty()) {
                response.getWriter().println("Error: Estimated Effort is required.");
                return;
            }
            int estimatedeffort = Integer.parseInt(effortStr);

            // Tạo đối tượng PlanCampaign và cập nhật giá trị
            PlanCampaign campaign = new PlanCampaign();
            campaign.setCamid(camid);

            Plan plan = new Plan();
            plan.setPlId(plid);
            campaign.setPlan(plan);

            Product product = new Product();
            product.setPid(pid);
            campaign.setProduct(product);

            campaign.setQuantity(quantity);
            campaign.setEstimatedeffort(estimatedeffort);

            // Cập nhật PlanCampaign trong cơ sở dữ liệu
            PlanCampaignDBContext db = new PlanCampaignDBContext();
            db.update(campaign);

            request.setAttribute("alertMessage", "Updated successfully");

            request.getRequestDispatcher("/PlanCampaignController").forward(request, response);

        } catch (NumberFormatException e) {
            response.getWriter().println("Error: Invalid input format. Please check your values and try again.");
        } catch (IOException e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

}
