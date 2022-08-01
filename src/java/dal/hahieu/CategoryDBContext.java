/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.hahieu;

import dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Course;
import model.Price_Package;

/**
 *
 * @author LAPTOP D&N
 */
public class CategoryDBContext {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Category> getAllCourseCategory() {
        List<Category> list = new ArrayList<>();
        String query = "select * from Category";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt("id"),
                        rs.getString("categoryName"),
                        rs.getString("description")));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public List<Course> getCourseByCategory(int id) {
        List<Course> list = new ArrayList<>();
        String query = "SELECT * FROM dbo.course JOIN dbo.Category \n"
                + "ON Category.id = course.categoryId\n"
                + "JOIN dbo.Price_Package\n"
                + "ON Price_Package.courseId = course.id\n"
                + "JOIN dbo.Package\n"
                + "ON Package.id = Price_Package.packageId\n"
                + "Where categoryId = ?";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Course(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getBoolean("isActive"),
                        rs.getString("created_by"),
                        rs.getString("description"),
                        rs.getInt("categoryId"),
                        rs.getString("img"),
                        rs.getInt("price"),
                        rs.getInt("priceSale"),
                        rs.getString("package_name")));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public static void main(String[] args) {
        CategoryDBContext db = new CategoryDBContext();
        List<Category> list = db.getAllCourseCategory();
        
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getDescription());
        }
        
    }
}
