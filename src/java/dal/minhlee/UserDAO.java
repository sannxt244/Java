/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.minhlee;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Role;
import model.User;

/**
 *
 * @author Minh Lee
 */
public class UserDAO extends DBContext{
    
    public String checkRoleName(int roleId){
        String sql = "select * from role where id = " + roleId;
        String name = "";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                name = rs.getString("roleName");
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return name;
    }
    
    public List<User> getAllUser (){ // for userList
        List<User> ls = new ArrayList<>();
        String sql = "select * from [user]";
        
        
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                
                Role r = new Role(rs.getInt("roleId"), checkRoleName(rs.getInt("roleId")));
                User u = new User(rs.getString("avatar"),rs.getString("username") , r, rs.getDate("registerDate"), rs.getInt("registerStatus"));
                u.setVerify(rs.getBoolean("verify"));
                ls.add(u);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        
        return ls;
    }
    
    public void uploadAvatar (String userName, String address ){
        String sql = "update [user] set avatar = ? where username = ?";
        
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, address);
            st.setString(2, userName);
            st.executeUpdate();
            
        }catch(SQLException e){
            System.out.println("upload img");
        }
    }
    
    public void deleteUser (String username){
        String sql = "delete [user] where username = ? "
                + "delete [Course_Register] where username = ?";
        
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, username);
            st.execute();
        }catch(SQLException e){
            System.out.println("delete User");
        }
    }
    
    public void updateRole_and_Status (String userName, int roleId, boolean verify){
        String sql = "update [user] set roleId = ? , verify = ? where username = ?";
        
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, roleId);
            st.setBoolean(2, verify);
            st.setString(3, userName);
            st.execute();
        }catch(SQLException e){
            System.out.println("updateRole_and_Status");
        }
        
    }
    
    public static void main(String[] args) {
        UserDAO ud = new UserDAO();
        
        ud.uploadAvatar("aalflattkp", "http://t2.gstatic.com/licensed-image?q=tbn:ANd9GcSw8ex3JKxYUjY_DpUYMRcxMTsRF4PWPAMqekSDWCbLYUBtJDonO-CwM0lW6sCYdTq3PXLI5zpCYpFiK-GoP74");
    }
}
