/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.PlanCampaign;
import java.sql.*;
import model.Plan;
import model.Product;

/**
 *
 * @author nlong
 */
public class PlanCampaignDBContext extends DBContext<PlanCampaign> {

    @Override
    public void insert(PlanCampaign model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(PlanCampaign model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(PlanCampaign model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<PlanCampaign> list() {
        ArrayList<PlanCampaign> campaigns = new ArrayList<>();
        String sql = "SELECT pc.[camid]\n"
                + "      ,pc.[plid]\n"
                + "      ,pc.[pid]\n"
                + "      ,pc.[quantity]\n"
                + "      ,pc.[estimatedeffort]\n"
                + "	  ,pro.pname\n"
                + "  FROM [dbo].[PlanCampaign] pc\n"
                + "  JOIN [dbo].[Product] pro ON pc.pid = pro.pid";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                PlanCampaign campaign = new PlanCampaign();
                campaign.setCamid(rs.getInt("camid"));

                Plan pla = new Plan();
                pla.setPlId(rs.getInt("plid"));
                campaign.setPlan(pla);

                Product pro = new Product();
                pro.setPid(rs.getInt("pid"));
                pro.setPname(rs.getString("pname"));
                campaign.setProduct(pro);

                campaign.setQuantity(rs.getInt("quantity"));
                campaign.setEstimatedeffort(rs.getFloat("estimatedeffort"));

                campaigns.add(campaign);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
        return campaigns;
    }

    @Override
    public PlanCampaign get(int camid) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void main(String[] args) {
        PlanCampaignDBContext d = new PlanCampaignDBContext();
        ArrayList<PlanCampaign> n = d.list();
        for (PlanCampaign p : n) {
            System.out.println(p.getCamid());
        }
    }

}
