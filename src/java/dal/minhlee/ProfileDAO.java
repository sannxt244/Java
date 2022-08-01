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
public class ProfileDAO extends DBContext {
    
    public List<User> getAll_User() {
        List<User> ls = new ArrayList<>();
        String sql = "select * from [user]";
        
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while (rs.next()){
                Role role = new Role(rs.getInt("roleId"), "");
                User u = new User(rs.getString("fullname"), rs.getBoolean("gender"), rs.getString("email"), rs.getString("mobile"), role, rs.getString("username"), rs.getString("password"), rs.getString("avatar"), rs.getBoolean("verify"));
                ls.add(u);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        
        return ls;
    }
    
}
