/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.accesscontrol;

import dal.UserDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.accesscontrol.Feature;
import model.accesscontrol.User;

/**
 *
 * @author sonnt-local
 */
public abstract class BaseRBACController extends BaseRequiredAuthenticationController {

    private boolean isAuthorized(HttpServletRequest req, User loggeduser)
    {
        UserDBContext db = new UserDBContext();
        List<Feature> ft = db.getFeature(loggeduser.getUsername());
        loggeduser.setFeature(ft);
        String c_url = req.getServletPath();
        for (Feature feature : ft) {
            if(feature.getUrl().equals(c_url))
                    return true;
        }
        return false;
    }
    
    protected abstract void doAuthorizedGet(HttpServletRequest req, HttpServletResponse resp, User loggeduser) throws ServletException, IOException;
    protected abstract void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User loggeduser) throws ServletException, IOException;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User loggeduser) throws ServletException, IOException {
        if(isAuthorized(req, loggeduser))
        {
            doAuthorizedGet(req, resp, loggeduser);
        }
        else
            resp.sendError(403, "you do not have right to access this feature!");
    
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User loggeduser) throws ServletException, IOException {
        if(isAuthorized(req, loggeduser))
        {
            doAuthorizedPost(req, resp, loggeduser);
        }
        else
            resp.sendError(403, "you do not have right to access this feature!");
    }
    
}
