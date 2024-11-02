/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Plan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Department;
import model.PlanCampaign;

/**
 *
 * @author nlong
 */
public class PlanDBContext extends DBContext<Plan> {

    public List<Plan> getPlans() {
        List<Plan> plans = new ArrayList<>();
        String sql = "SELECT p.plid, p.[start], p.[end], d.dname\n"
                + "  FROM [dbo].[Plan] p \n"
                + "  join [dbo].[Department] d on p.did = d.did";

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql); ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Integer plId = resultSet.getInt("plid");
                String start = resultSet.getString("start");
                String end = resultSet.getString("end");
                String department = resultSet.getString("dname");

                Department d = new Department();
                d.setDname(department);

                Plan plan = new Plan(plId, d, Date.valueOf(start), Date.valueOf(end));
                plans.add(plan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }

        return plans;
    }

    @Override
    public void insert(Plan model) {
        try {
            connection.setAutoCommit(false);
            String sql_insert_plan = "INSERT INTO [dbo].[Plan]\n"
                    + "           ([start]\n"
                    + "           ,[end]\n"
                    + "           ,[did])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?)";
            String sql_select_plan = "SELECT @@IDENTITY as plid";
            String sql_insert_campain = "INSERT INTO [dbo].[PlanCampainn]\n"
                    + "           ([plid]\n"
                    + "           ,[pid]\n"
                    + "           ,[quantity]\n"
                    + "           ,[estimatedeffort])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            PreparedStatement stm_insert_plan = connection.prepareStatement(sql_insert_plan);
            stm_insert_plan.setDate(1, model.getStart());
            stm_insert_plan.setDate(2, model.getEnd());
            stm_insert_plan.setInt(3, model.getDept().getDid());
            stm_insert_plan.executeUpdate();

            PreparedStatement stm_select_plan = connection.prepareStatement(sql_select_plan);
            ResultSet rs = stm_select_plan.executeQuery();
            if (rs.next()) {
                model.setPlId(rs.getInt("plid"));
            }
            for (PlanCampaign campain : model.getCampains()) {
                PreparedStatement stm_insert_campain = connection.prepareStatement(sql_insert_campain);
                stm_insert_campain.setInt(1, model.getPlId());
                stm_insert_campain.setInt(2, campain.getProduct().getPid());
                stm_insert_campain.setInt(3, campain.getQuantity());
                stm_insert_campain.setFloat(4, campain.getEstimatedeffort());
                stm_insert_campain.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<Plan> searchPlans(Plan p) {
        List<Plan> plans = new ArrayList<>();
        String sql = "SELECT p.[plid], p.[start], p.[end], d.[did], d.dname \n"
                + "FROM [dbo].[Plan] p\n"
                + "JOIN [dbo].[Department] d ON p.did = d.did\n"
                + "WHERE 1=1";

        if (p.getPlId() != null) {
            sql += " AND p.plid = ?";
        }

        if (p.getStart() != null) {
            sql += " AND p.[start] >= ?";
        }
        if (p.getEnd() != null) {
            sql += " AND p.[end] <= ?";
        }

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            Integer paramIndex = 1;

            if (p.getPlId() != null) {
                stm.setInt(paramIndex++, p.getPlId());
            }

            if (p.getStart() != null) {
                stm.setDate(paramIndex++, p.getStart());
            }
            if (p.getEnd() != null) {
                stm.setDate(paramIndex++, p.getEnd());
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Plan pl = new Plan();
                pl.setPlId(rs.getInt("plid"));
                pl.setStart(rs.getDate("start"));
                pl.setEnd(rs.getDate("end"));
                pl.setDept(new Department(rs.getInt("did"), rs.getString("dname"), null));
                plans.add(pl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return plans;
    }

    @Override
    public void update(Plan plan) {
        String sql = "UPDATE [Plan] SET [start] = ?, [end] = ?, [did] = ? WHERE [plid] = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setDate(1, plan.getStart());
            stm.setDate(2, plan.getEnd());
            stm.setInt(3, plan.getDept().getDid());
            stm.setInt(4, plan.getPlId());

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void delete(Plan model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Plan> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Plan get(int id) {
        String sql = "SELECT plid, [start], [end] FROM [dbo].[Plan] WHERE plid = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        Plan plan = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();

            if (rs.next()) {
                plan = new Plan();
                plan.setPlId(rs.getInt("plid"));
                plan.setStart(rs.getDate("start"));
                plan.setEnd(rs.getDate("end"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return plan;
    }
   
    

}
