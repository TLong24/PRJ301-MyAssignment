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
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.PlanCampaign;
import model.accesscontrol.User;

/**
 *
 * @author nlong
 */
public class PlanCampaignController extends BaseRBACController {

    private void processRequests(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PlanCampaignDBContext dbContext = new PlanCampaignDBContext();
        ArrayList<PlanCampaign> campaigns = dbContext.list();

        request.setAttribute("campaigns", campaigns);
        request.setAttribute("alertMessage", request.getAttribute("alertMessage"));
        request.getRequestDispatcher("planCampaign.jsp").forward(request, response);
    }



    @Override
    protected void doAuthorizedGet(HttpServletRequest request, HttpServletResponse response, User loggeduser) throws ServletException, IOException {
        processRequests(request, response);
    }

    @Override
    protected void doAuthorizedPost(HttpServletRequest request, HttpServletResponse response, User loggeduser) throws ServletException, IOException {
        processRequests(request, response);
    }

}
