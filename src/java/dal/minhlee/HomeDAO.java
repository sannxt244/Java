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
import model.Blog;
import model.Course;
import model.Slider;

/**
 *
 * @author Minh Lee
 */
public class HomeDAO extends DBContext{
    
    public List<Slider> getListSlider() {
        ArrayList<Slider> ls = new ArrayList<>();
        String sql = "select * from slider";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                Slider s = new Slider(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                ls.add(s);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return ls;
    }
    
    public List<Course> getListCourse() {
        ArrayList<Course> ls = new ArrayList<>();
        String sql = "select * from course";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Course c = new Course(rs.getInt(1), rs.getString(2), rs.getBoolean(3), rs.getString(4), rs.getString(5),rs.getInt(6), rs.getString(7));
                ls.add(c);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return ls;
    }
    
    public List<Blog> getListBlog() {
        List<Blog> ls = new ArrayList<>();
        String sql = "select * from blog";
        
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                Blog b = new Blog(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getInt(5), rs.getBoolean(6), rs.getString(7), rs.getString(8));
                ls.add(b);
                    
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return ls;
    }
}
