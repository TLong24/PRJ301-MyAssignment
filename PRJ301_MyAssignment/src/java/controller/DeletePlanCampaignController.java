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
import model.accesscontrol.User;

/**
 *
 * @author nlong
 */
public class DeletePlanCampaignController extends BaseRBACController {

    @Override
    protected void doAuthorizedGet(HttpServletRequest req, HttpServletResponse resp, User loggeduser) throws ServletException, IOException {
        int camid = Integer.parseInt(req.getParameter("camid"));

        PlanCampaignDBContext db1 = new PlanCampaignDBContext();
        if (db1.hasChildren(camid)){
            req.setAttribute("alertMessage", "This campaign has children. Delete fail");
            req.getRequestDispatcher("/PlanCampaignController").forward(req, resp);
            return;
        }
        
        PlanCampaignDBContext db = new PlanCampaignDBContext();
        db.delete(camid);

        req.setAttribute("alertMessage", "Updated successfully");
        req.getRequestDispatcher("/PlanCampaignController").forward(req, resp);
    }

    @Override
    protected void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User loggeduser) throws ServletException, IOException {
    }

}
