/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.hahieu;

import dal.DBContext;
import dal.donghieu.PostDBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Blog;
import model.Category;
import model.haha.Course;
import model.Price_Package;

/**
 *
 * @author LAPTOP D&N
 */
public class CourseDBContext {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

//    public List<Course> getAllCourses() {
//        
//        List<Course> ls = new ArrayList<>();
//        String sql = "select * from course";
//        
//        try{
//            PreparedStatement st = conn.prepareStatement(sql);
//            ResultSet rs = st.executeQuery();
//            
//            while(rs.next()){
//                Course c = new Course(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7));
//                ls.add(c);
//            }
//        }catch(SQLException e){
//            System.out.println(e);
//        }
//        return ls;
//    }
    public List<Course> getCourseByPriceUp() {
        List<Course> list = new ArrayList<>();
        String query = "select * from course \n"
                + "join Category ON course.categoryId = Category.id\n"
                + "JOIN dbo.Price_Package ON Price_Package.courseId = course.id\n"
                + "JOIN dbo.Package ON Package.id = Price_Package.packageId\n"
                + "where isActive = 1"
                + "Order by Price_Package.priceSale ASC";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Course(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("isActive"),
                        rs.getString("created_by"),
                        rs.getString("description"),
                        rs.getInt("categoryId"),
                        rs.getString("img"),
                        rs.getInt("price"),
                        rs.getInt("priceSale"),
                        rs.getString("package_name"),
                        rs.getInt("packageId")));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String query = "select * from Category";

        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public Course getCourseByCourseIdAndPackageId(int courseid, int packageId) {
        String query = "select * from course \n"
                + "JOIN Category ON course.categoryId = Category.id\n"
                + "JOIN dbo.Price_Package ON Price_Package.courseId = course.id\n"
                + "JOIN dbo.Package ON Package.id = Price_Package.packageId\n"
                + "where course.id = ? and Price_Package.packageId = ?";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setInt(1, courseid);
            ps.setInt(2, packageId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Course(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("isActive"),
                        rs.getString("created_by"),
                        rs.getString("description"),
                        rs.getInt("categoryId"),
                        rs.getString("img"),
                        rs.getInt("price"),
                        rs.getInt("priceSale"),
                        rs.getString("package_name"),
                        rs.getInt("packageId"));
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public List<Course> getCourseDetailWithPriceServlet(int courseid) {
        List<Course> list = new ArrayList<>();
        String query = "select * from course\n"
                + "join Category ON course.categoryId = Category.id\n"
                + "JOIN dbo.Price_Package ON Price_Package.courseId = course.id\n"
                + "JOIN dbo.Package ON Package.id = Price_Package.packageId\n"
                + "where course.id = ?\n"
                + "Order by Price_Package.priceSale ASC";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setInt(1, courseid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Course(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("isActive"),
                        rs.getString("created_by"),
                        rs.getString("description"),
                        rs.getInt("categoryId"),
                        rs.getString("img"),
                        rs.getInt("price"),
                        rs.getInt("priceSale"),
                        rs.getString("package_name"),
                        rs.getInt("packageId")));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public List<Course> getCourseByPriceDown() {
        List<Course> list = new ArrayList<>();
        String query = "select * from course \n"
                + "join Category ON course.categoryId = Category.id\n"
                + "JOIN dbo.Price_Package ON Price_Package.courseId = course.id\n"
                + "JOIN dbo.Package ON Package.id = Price_Package.packageId\n"
                + "where isActive = 1"
                + "Order by Price_Package.priceSale DESC";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Course(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("isActive"),
                        rs.getString("created_by"),
                        rs.getString("description"),
                        rs.getInt("categoryId"),
                        rs.getString("img"),
                        rs.getInt("price"),
                        rs.getInt("priceSale"),
                        rs.getString("package_name"),
                        rs.getInt("packageId")));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public List<Course> getCourseByCourseNameAndCategoryName(String course_name, String cate_name) {
        List<Course> list = new ArrayList<>();
        String query = "select *\n"
                + "from course\n"
                + "join Category ON course.categoryId = Category.id\n"
                + "JOIN dbo.Price_Package ON Price_Package.courseId = course.id\n"
                + "JOIN dbo.Package ON Package.id = Price_Package.packageId\n"
                + "where course.name like ? AND Category.categoryName like ?";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setString(1, course_name);
            ps.setString(2, cate_name);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Course(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("isActive"),
                        rs.getString("created_by"),
                        rs.getString("description"),
                        rs.getInt("categoryId"),
                        rs.getString("img"),
                        rs.getInt("price"),
                        rs.getInt("priceSale"),
                        rs.getString("package_name"),
                        rs.getInt("packageId")));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public int totalCourse() throws SQLException {
        String query = "select COUNT(*)\n"
                + "from course\n"
                + "join Category ON course.categoryId = Category.id\n"
                + "JOIN dbo.Price_Package ON Price_Package.courseId = course.id\n"
                + "JOIN dbo.Package ON Package.id = Price_Package.packageId";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public List<Course> getAllCourseWithPaging(int page) {
        List<Course> list = new ArrayList<>();
        String query = "select * from course\n"
                + "join Category ON course.categoryId = Category.id\n"
                + "JOIN dbo.Price_Package ON Price_Package.courseId = course.id\n"
                + "JOIN dbo.Package ON Package.id = Price_Package.packageId\n"
                + "order by course.id\n"
                + "offset ? rows fetch next 8 rows only";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setInt(1, (page - 1) * 8);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Course(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("isActive"),
                        rs.getString("created_by"),
                        rs.getString("description"),
                        rs.getInt("categoryId"),
                        rs.getString("img"),
                        rs.getInt("price"),
                        rs.getInt("priceSale"),
                        rs.getString("package_name"),
                        rs.getInt("packageId")));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public void editCourseDetail(String name, int isActive, String description, int categoryId, String img, int courseId, int packageId) {
        String query = "update [course]\n"
                + "SET course.name = ?,\n"
                + "course.isActive = ?,\n"
                + "course.description = ?,\n"
                + "course.categoryId = ?,\n"
                + "course.img = ?\n"
                + "FROM [course]\n"
                + "JOIN Category ON course.categoryId = Category.id\n"
                + "JOIN dbo.Price_Package ON Price_Package.courseId = course.id\n"
                + "JOIN dbo.Package ON Package.id = Price_Package.packageId\n"
                + "WHERE Price_Package.packageId = ? and course.id = ?";
        try {
            conn = new DBContext().connection;//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setInt(2, isActive);
            ps.setString(3, description);
            ps.setInt(4, categoryId);
            ps.setString(5, img);
            ps.setInt(6, packageId);
            ps.setInt(7, courseId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editCourseDetailPrice(int price, int priceSale, int courseId, int packageId) {
        String query = "update Price_Package\n"
                + "SET\n"
                + "Price_Package.price = ?,\n"
                + "Price_Package.priceSale = ?\n"
                + "FROM [course]\n"
                + "JOIN Category ON course.categoryId = Category.id\n"
                + "JOIN dbo.Price_Package ON Price_Package.courseId = course.id\n"
                + "JOIN dbo.Package ON Package.id = Price_Package.packageId\n"
                + "WHERE course.id = ? and Price_Package.packageId = ?";
        try {
            conn = new DBContext().connection;//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, price);
            ps.setInt(2, priceSale);
            ps.setInt(3, courseId);
            ps.setInt(4, packageId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        CourseDBContext db = new CourseDBContext();
        List<Course> list = db.getCourseDetailWithPriceServlet(1);
        for (Course c : list) {
            System.out.println(c.toString());
        }
    }

}
