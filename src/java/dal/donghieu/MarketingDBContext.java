/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.donghieu;

import dal.DBContext;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import model.Course;
import model.CourseRegister;
import model.Role;
import model.User;
import model.Package;
import model.RevenuesByTime;

import model.State;
import model.TrendOrder;

/**
 *
 * @author dell
 */
public class MarketingDBContext extends DBContext {

    public ArrayList<User> getAllUserFromDateToDate(Date from, Date to) {
        ArrayList<User> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM dbo.[user]\n"
                    + "WHERE registerDate >= ? AND registerDate <= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, from);
            stm.setDate(2, to);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setUserName(rs.getString(1));
                u.setPassword(rs.getString(2));
                Role r = new Role();
                r.setId(rs.getInt(3));
                u.setRole(r);
                u.setFullName(rs.getString(4));
                u.setGender(rs.getBoolean(5));
                u.setEmail(rs.getString(6));
                u.setPhoneNumber(rs.getString(7));
                u.setAvatar(rs.getString(8));
                u.setVerify(rs.getBoolean(9));
                u.setRegisterDate(rs.getDate(10));
                u.setRegisterStatus(rs.getInt(11));
                users.add(u);
            }
            return users;
        } catch (SQLException ex) {
            Logger.getLogger(MarketingDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<User> getAllUser() {
        ArrayList<User> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM dbo.[user]";
            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setUserName(rs.getString(1));
                u.setPassword(rs.getString(2));
                Role r = new Role();
                r.setId(rs.getInt(3));
                u.setRole(r);
                u.setFullName(rs.getString(4));
                u.setGender(rs.getBoolean(5));
                u.setEmail(rs.getString(6));
                u.setPhoneNumber(rs.getString(7));
                u.setAvatar(rs.getString(8));
                u.setVerify(rs.getBoolean(9));
                u.setRegisterDate(rs.getDate(10));
                u.setRegisterStatus(rs.getInt(11));
                users.add(u);
            }
            return users;
        } catch (SQLException ex) {
            Logger.getLogger(MarketingDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Course> getAllcourseFromDatetoDate(Date from, Date to) {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String sql = "SELECT * FROM dbo.course WHERE createdDate >= ? AND createdDate <= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, from);
            stm.setDate(2, to);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Course c = new Course();
                c.setId(rs.getInt(1));
                c.setName(rs.getString(2));
                c.setIsActive(rs.getBoolean(3));
                c.setCreated_by(rs.getString(4));
                c.setDescription(rs.getString(5));
                c.setCategoryId(rs.getInt(6));
                c.setImg(rs.getString(7));
                c.setCreatedDate(rs.getDate(8));
                courses.add(c);
            }
            return courses;
        } catch (SQLException ex) {
            Logger.getLogger(MarketingDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Course> getAllcourses() {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String sql = "SELECT * FROM dbo.course";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Course c = new Course();
                c.setId(rs.getInt(1));
                c.setName(rs.getString(2));
                c.setIsActive(rs.getBoolean(3));
                c.setCreated_by(rs.getString(4));
                c.setDescription(rs.getString(5));
                c.setCategoryId(rs.getInt(6));
                c.setImg(rs.getString(7));
                c.setCreatedDate(rs.getDate(8));
                courses.add(c);
            }
            return courses;
        } catch (SQLException ex) {
            Logger.getLogger(MarketingDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<CourseRegister> getAllCourseRegistersFromDateToDate(Date from, Date to) {
        ArrayList<CourseRegister> courseRegisters = new ArrayList<>();
        try {
            String sql = "SELECT * FROM dbo.Course_Register\n"
                    + "WHERE date_register > ? AND date_register < ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, from);
            stm.setDate(2, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                CourseRegister cr = new CourseRegister();
                Course c = new Course();
                Package p = new Package();
                User u = new User();
                State s = new State();
                u.setUserName(rs.getString(1));
                c.setId(rs.getInt(2));
                cr.setDateRegister(rs.getDate(3));
                s.setId(rs.getInt(4));
                p.setId(rs.getInt(5));
                cr.setUser(u);
                cr.setState(s);
                cr.setPackagee(p);
                cr.setCourse(c);
                courseRegisters.add(cr);
            }
            return courseRegisters;
        } catch (SQLException ex) {
            Logger.getLogger(MarketingDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getRevenuesFromDateToDate(int categoriesId, Date from, Date to) {
        int revenues = 0;
        try {
            String sql = "SELECT SUM(priceSale) AS revenues FROM dbo.Course_Register \n"
                    + "                    JOIN dbo.Price_Package\n"
                    + "                    ON Price_Package.packageId = Course_Register.packageId AND Price_Package.courseId = Course_Register.courseId\n"
                    + "                    JOIN dbo.course \n"
                    + "                    ON course.id = Course_Register.courseId\n"
                    + "                    JOIN dbo.Category\n"
                    + "                    ON Category.id = course.categoryId\n"
                    + "			   WHERE stateId = 1 AND date_register > ? AND date_register < ?";
//                    + "WHERE categoryId = 2\n"
//                    + "GROUP BY stateId";

            if (categoriesId != 0) {
                sql += ""
                        + " and categoryId = ?";
            }

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, from);
            stm.setDate(2, to);
            if (categoriesId != 0) {
                stm.setInt(3, categoriesId);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                revenues += rs.getInt(1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(MarketingDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return revenues;
    }

    public int getAllRevenues(int categoriesId) {
        int revenues = 0;
        try {
            String sql = "SELECT SUM(priceSale) AS revenues FROM dbo.Course_Register \n"
                    + "                    JOIN dbo.Price_Package\n"
                    + "                    ON Price_Package.packageId = Course_Register.packageId AND Price_Package.courseId = Course_Register.courseId\n"
                    + "                    JOIN dbo.course \n"
                    + "                    ON course.id = Course_Register.courseId\n"
                    + "                    JOIN dbo.Category\n"
                    + "                    ON Category.id = course.categoryId\n"
                    + "			   WHERE stateId = 1";
//                    + "WHERE categoryId = 2\n"
//                    + "GROUP BY stateId";

            if (categoriesId != 0) {
                sql += "WHERE categoryId = ?";
            }
            PreparedStatement stm = connection.prepareStatement(sql);

            if (categoriesId != 0) {
                stm.setInt(1, categoriesId);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                revenues += rs.getInt(1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(MarketingDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return revenues;
    }

    public ArrayList<RevenuesByTime> getAllRevenuesByMonth(int categoriesId) {
        ArrayList<RevenuesByTime> revenuesByTime = new ArrayList<>();
        try {
            String sql = "SELECT FORMAT(DATEFROMPARTS(2022,MONTH(date_register),1), 'MMMM', 'en-US') AS [Month] , SUM(priceSale) AS Revenues  FROM dbo.Course_Register \n"
                    + "JOIN dbo.Price_Package\n"
                    + "ON Price_Package.packageId = Course_Register.packageId AND Price_Package.courseId = Course_Register.courseId\n"
                    + "JOIN dbo.course \n"
                    + "ON course.id = Course_Register.courseId\n"
                    + "JOIN dbo.Category\n"
                    + "ON Category.id = course.categoryId\n"
                    + "WHERE stateId = 1 AND YEAR(date_register) = YEAR(GETDATE())";
//                    + "WHERE categoryId = 2\n"
//                    + "GROUP BY stateId";

            if (categoriesId != 0) {
                sql += "and categoryId = ? "
                        + "GROUP BY MONTH(date_register)";
            } else {
                sql += "GROUP BY MONTH(date_register)";
            }
            PreparedStatement stm = connection.prepareStatement(sql);

            if (categoriesId != 0) {
                stm.setInt(1, categoriesId);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                RevenuesByTime rbt = new RevenuesByTime();
                rbt.setTime(rs.getString(1));
                rbt.setRevenues(rs.getInt(2));
                revenuesByTime.add(rbt);
            }
            return revenuesByTime;
        } catch (SQLException ex) {
            Logger.getLogger(MarketingDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<RevenuesByTime> getAllRevenuesByYear(int categoriesId) {
        ArrayList<RevenuesByTime> revenuesByTime = new ArrayList<>();
        try {
            String sql = "SELECT FORMAT(DATEFROMPARTS(YEAR(date_register),1,1), 'yyyy', 'en-US') AS [Month] , SUM(priceSale) AS Revenues  FROM dbo.Course_Register \n"
                    + "JOIN dbo.Price_Package\n"
                    + "ON Price_Package.packageId = Course_Register.packageId AND Price_Package.courseId = Course_Register.courseId\n"
                    + "JOIN dbo.course \n"
                    + "ON course.id = Course_Register.courseId\n"
                    + "JOIN dbo.Category\n"
                    + "ON Category.id = course.categoryId\n"
                    + "WHERE stateId = 1";


            if (categoriesId != 0) {
                sql += "and categoryId = ? "
                        + "GROUP BY YEAR(date_register)";
            } else {
                sql += "GROUP BY YEAR(date_register)";
            }
            PreparedStatement stm = connection.prepareStatement(sql);

            if (categoriesId != 0) {
                stm.setInt(1, categoriesId);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                RevenuesByTime rbt = new RevenuesByTime();
                rbt.setTime(rs.getString(1));
                rbt.setRevenues(rs.getInt(2));
                revenuesByTime.add(rbt);
            }
            return revenuesByTime;
        } catch (SQLException ex) {
            Logger.getLogger(MarketingDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<TrendOrder> getTrendSuccessOrderFromDateToDate(Date from, Date to) {
        ArrayList<TrendOrder> trendOrders = new ArrayList<>();
        try {
            String sql = "SELECT id,name,COUNT(courseId) AS number\n"
                    + "FROM dbo.Course_Register\n"
                    + "JOIN dbo.course\n"
                    + "ON course.id = Course_Register.courseId\n"
                    + "WHERE stateId = 1 and date_register >= ? AND date_register <= ?\n"
                    + "GROUP BY courseId,name,id\n"
                    + "HAVING COUNT(courseId) =(SELECT MAX(N) FROM (SELECT COUNT(courseId) AS N\n"
                    + "						FROM dbo.Course_Register\n"
                    + "						JOIN dbo.course\n"
                    + "						ON course.id = Course_Register.courseId\n"
                    + "						WHERE stateId = 1 and  date_register >= ? AND date_register <= ?\n"
                    + "						GROUP BY courseId,name) A)";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, from);
            stm.setDate(2, to);
            stm.setDate(3, from);
            stm.setDate(4, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                TrendOrder trendOrder = new TrendOrder();
                Course c = new Course();
                c.setId(rs.getInt(1));
                c.setName(rs.getString(2));
                trendOrder.setOrderNumber(rs.getInt(3));
                trendOrder.setCourse(c);
                trendOrders.add(trendOrder);
            }
            return trendOrders;
        } catch (SQLException ex) {
            Logger.getLogger(MarketingDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<TrendOrder> getTrendAllOrderFromDateToDate(Date from, Date to) {
        ArrayList<TrendOrder> trendOrders = new ArrayList<>();
        try {
            String sql = "SELECT id,name,COUNT(courseId) AS number\n"
                    + "FROM dbo.Course_Register\n"
                    + "JOIN dbo.course\n"
                    + "ON course.id = Course_Register.courseId\n"
                    + "WHERE date_register >= ? AND date_register <= ?\n"
                    + "GROUP BY courseId,name,id\n"
                    + "HAVING COUNT(courseId) =(SELECT MAX(N) FROM (SELECT COUNT(courseId) AS N\n"
                    + "						FROM dbo.Course_Register\n"
                    + "						JOIN dbo.course\n"
                    + "						ON course.id = Course_Register.courseId\n"
                    + "						WHERE  date_register >= ? AND date_register <= ?\n"
                    + "						GROUP BY courseId,name) A)";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, from);
            stm.setDate(2, to);
            stm.setDate(3, from);
            stm.setDate(4, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                TrendOrder trendOrder = new TrendOrder();
                Course c = new Course();
                c.setId(rs.getInt(1));
                c.setName(rs.getString(2));
                trendOrder.setOrderNumber(rs.getInt(3));
                trendOrder.setCourse(c);
                trendOrders.add(trendOrder);
            }
            return trendOrders;
        } catch (SQLException ex) {
            Logger.getLogger(MarketingDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<User> getUserBoughtFromDateToDate(Date from, Date to) {
        ArrayList<User> users = new ArrayList<>();
        try {
            String sql = "SELECT username FROM dbo.Course_Register\n"
                    + "WHERE date_register > ? AND date_register < ?\n"
                    + "GROUP BY username";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, from);
            stm.setDate(2, to);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User u = new UserDBContext().getUserByUsername(rs.getString(1));
                users.add(u);
            }
            return users;
        } catch (SQLException ex) {
            Logger.getLogger(MarketingDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Category> getAllCategorySubject() {
        ArrayList<Category> categorys = new ArrayList<>();
        try {
            String sql = "SELECT Category.id,categoryName FROM dbo.Category LEFT JOIN dbo.course\n"
                    + "ON course.categoryId = Category.id\n"
                    + "GROUP BY Category.id,categoryName";

            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                Category c = new Category();
                c.setId(rs.getInt(1));
                c.setCategoryName(rs.getString(2));

                categorys.add(c);
            }
            return categorys;
        } catch (SQLException ex) {
            Logger.getLogger(MarketingDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
