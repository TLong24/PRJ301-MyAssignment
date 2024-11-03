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
        String sql = "UPDATE [dbo].[PlanCampaign] SET\n"
                + "      [plid] = ?\n"
                + "      ,[pid] = ?\n"
                + "      ,[quantity] = ?\n"
                + "      ,[estimatedeffort] = ?\n"
                + "  WHERE [camid] = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, model.getPlan().getPlId());
            stm.setInt(2, model.getProduct().getPid());
            stm.setInt(3, model.getQuantity());
            stm.setFloat(4, model.getEstimatedeffort());
            stm.setInt(5, model.getCamid());

            stm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM [dbo].[PlanCampaign] WHERE [camid] = ?";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
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
                campaign.setEstimatedeffort(rs.getInt("estimatedeffort"));

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
        PlanCampaign campaign = null;
        String sql = "SELECT [camid]\n"
                + "      ,[plid]\n"
                + "      ,[pid]\n"
                + "      ,[quantity]\n"
                + "      ,[estimatedeffort]\n"
                + "  FROM [dbo].[PlanCampaign]\n"
                + "  WHERE [camid] = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, camid);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                campaign = new PlanCampaign();
                campaign.setCamid(rs.getInt("camid"));

                Plan plan = new Plan();
                plan.setPlId(rs.getInt("plid"));
                campaign.setPlan(plan);

                Product product = new Product();
                product.setPid(rs.getInt("pid"));
                campaign.setProduct(product);

                campaign.setQuantity(rs.getInt("quantity"));
                campaign.setEstimatedeffort(rs.getInt("estimatedeffort"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return campaign;
    }

}
