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
        String sql = "SELECT [camid]\n"
                + "      ,[plid]\n"
                + "      ,[pid]\n"
                + "      ,[quantity]\n"
                + "      ,[estimatedeffort]\n"
                + "  FROM [dbo].[PlanCampaign]";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql); ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                PlanCampaign campaign = new PlanCampaign();
                campaign.setCamid(rs.getInt("camid"));

                Plan pla = new Plan();
                pla.setPlId(rs.getInt("plid"));
                campaign.setPlan(pla);

                Product pro = new Product();
                pro.setPid(rs.getInt("pid"));
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
            } catch (Exception e) {
            }
        }
        return campaigns;
    }

    @Override
    public PlanCampaign get(int camid) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
