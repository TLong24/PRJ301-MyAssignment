/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import model.accesscontrol.Feature;
import model.accesscontrol.User;

/**
 *
 * @author nlong
 */
public class UserDBContext extends DBContext<User> {
    
    public User get(String username, String password) {
        String sql = "SELECT [uid]\n"
                + "      ,[username]\n"
                + "      ,[password]\n"
                + "      ,[isLocked]\n"
                + "  FROM [dbo].[User]\n"
                + "Where [username] = ? AND [password] = ? AND isLocked ='false'";
        User user = null;
        
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setIsLocked(rs.getBoolean("isLocked"));
                user.setUsername(username);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(UserDBContext.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                java.util.logging.Logger.getLogger(UserDBContext.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
            }
        }
        return user;
    }
    
    public List<Feature> getFeature(String username) {
        String sql = "SELECT f.fid, f.fname, f.url\n"
                + "  FROM [dbo].[Feature] f\n"
                + "  JOIN [dbo].[UserFeature] uf ON uf.fid = f.fid\n"
                + "  JOIN [dbo].[User] u ON u.uid = uf.uid\n"
                + "  WHERE u.username = ?";
        List<Feature> ft = new ArrayList<>();
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Feature feature = new Feature();
                feature.setFid(rs.getInt("fid"));
                feature.setFname(rs.getNString("fname"));
                feature.setUrl(rs.getNString("url"));
                
                ft.add(feature);
            }
        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(UserDBContext.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
        return ft;
    }
    
    @Override
    public void insert(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void update(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public ArrayList<User> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public User get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
//    public static void main(String[] args) {
//        UserDBContext ud = new UserDBContext();
//        List<Feature> ft = ud.getFeature("btt");
//        for (Feature feature : ft) {
//            System.out.println(feature.getFname());
//        }
//    }
}
