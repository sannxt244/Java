/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.marketing;

import controller.authorization.BaseAuthController;
import dal.donghieu.MarketingDBContext;
import dal.donghieu.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;
import model.Course;
import model.CourseRegister;
import model.RevenuesByCategory;
import model.RevenuesByTime;
import model.Role;

import model.TrendOrder;
import model.User;

/**
 *
 * @author dell
 */
public class DashboardServlet extends BaseAuthController {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long millis = System.currentTimeMillis();
        Date from = new Date(millis - 1000 * 60 * 60 * 24 * 7);
        Date to = new Date(millis);
        MarketingDBContext db = new MarketingDBContext();
        UserDBContext uDb = new UserDBContext();

        //Get all users that register account from date1 to date2
        ArrayList<User> users = db.getAllUserFromDateToDate(from, to);
        ArrayList<User> allUsers = db.getAllUser();
        //Get all courses from date1 to date2
        ArrayList<Course> courses = db.getAllcourseFromDatetoDate(from, to);
        //Get all courses esixt
        ArrayList<Course> allCourses = db.getAllcourses();
        //Get all information about customers and registered courses
        ArrayList<CourseRegister> courseRegisters = db.getAllCourseRegistersFromDateToDate(from, to);

        ArrayList<User> userBought = db.getUserBoughtFromDateToDate(from, to);

        //Sort registered courses by state
        ArrayList<CourseRegister> courseSuccessRegisters = new ArrayList<>();
        ArrayList<CourseRegister> courseCancelledRegisters = new ArrayList<>();
        ArrayList<CourseRegister> courseSubmittedRegisters = new ArrayList<>();
        for (CourseRegister c : courseRegisters) {
            if (c.getState().getId() == 1) {
                courseSuccessRegisters.add(c);
            } else if (c.getState().getId() == 2) {
                courseCancelledRegisters.add(c);
            } else {
                courseSubmittedRegisters.add(c);
            }
        }
        int categoriesId = 0;
        String categoryId_Raw = request.getParameter("categoryId");
        if (categoryId_Raw != null) {
            categoriesId = Integer.parseInt(categoryId_Raw);
        }
        int revenues = db.getRevenuesFromDateToDate(categoriesId, from, to);
        int allRevenue = db.getAllRevenues(categoriesId);

        String isSuccess = request.getParameter("state");
        ArrayList<TrendOrder> successTrendOrder = db.getTrendSuccessOrderFromDateToDate(from, to);

        ArrayList<TrendOrder> allTrendOrder = db.getTrendAllOrderFromDateToDate(from, to);

        ArrayList<RevenuesByCategory> revenuesByCategory = new ArrayList<>();
        ArrayList<Category> allCategory = db.getAllCategorySubject();
        for (Category category : allCategory) {
            RevenuesByCategory r = new RevenuesByCategory();
            r.setCategory(category);
            r.setRevenues(db.getRevenuesFromDateToDate(category.getId(), from, to));
            revenuesByCategory.add(r);
        }
        ArrayList<RevenuesByTime> revenuesByMonth = db.getAllRevenuesByMonth(categoriesId);
        ArrayList<RevenuesByTime> revenuesByYear = db.getAllRevenuesByYear(categoriesId);

        request.setAttribute("from", from);
        request.setAttribute("to", to);
        request.setAttribute("users", users);
        request.setAttribute("allUser", allUsers);
        request.setAttribute("courses", courses);
        request.setAttribute("allCourses", allCourses);
        request.setAttribute("courseRegisters", courseRegisters);
        request.setAttribute("userBought", userBought);
        request.setAttribute("courseSuccessRegisters", courseSuccessRegisters);
        request.setAttribute("courseCancelledRegisters", courseCancelledRegisters);
        request.setAttribute("courseSubmittedRegisters", courseSubmittedRegisters);
        request.setAttribute("revenues", revenues);
        request.setAttribute("allRevenue", allRevenue);
        request.setAttribute("trendOrder", successTrendOrder);
        request.setAttribute("allTrendOrder", allTrendOrder);
        request.setAttribute("revenuesByCategory", revenuesByCategory);
        request.setAttribute("revenuesByMonth", revenuesByMonth);
        request.setAttribute("revenuesByYear", revenuesByYear);

//        User user = new User();
//        user.setUserName("dongngochieu");
//        user.setPassword("1");
//        Role r = new Role();
//        r.setId(3);
//        user.setRole(r);
//        request.getSession().setAttribute("account", user);
        if (request.getSession().getAttribute("link") != null) {
            String link = (String) request.getSession().getAttribute("link");
            request.getSession().removeAttribute("link");
            response.sendRedirect(link.substring(1));
        } else {
            request.getRequestDispatcher("view/dashboard.jsp").forward(request, response);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long millis = System.currentTimeMillis();
        Date from = new Date(millis - 1000 * 60 * 60 * 24 * 7);
        Date to = new Date(millis);
        MarketingDBContext db = new MarketingDBContext();
        UserDBContext uDb = new UserDBContext();
        String from_raw = request.getParameter("from");
        String to_raw = request.getParameter("to");
        if (from_raw != null && from_raw.length() > 0 && to_raw != null && to_raw.length() > 0) {
            from = Date.valueOf(from_raw);
            to = Date.valueOf(to_raw);
        } else if (from_raw == null || to_raw == null || to_raw.length() <= 0 || from_raw.length() <= 0) {
            request.setAttribute("fillterError", "Please select both Start Date and End Date or omit both to get the latest 7 days information");
        }

        //Get all users that register account from date1 to date2
        ArrayList<User> users = db.getAllUserFromDateToDate(from, to);
        ArrayList<User> allUsers = db.getAllUser();
        //Get all courses from date1 to date2
        ArrayList<Course> courses = db.getAllcourseFromDatetoDate(from, to);
        //Get all courses esixt
        ArrayList<Course> allCourses = db.getAllcourses();
        //Get all information about customers and registered courses
        ArrayList<CourseRegister> courseRegisters = db.getAllCourseRegistersFromDateToDate(from, to);

        ArrayList<User> userBought = db.getUserBoughtFromDateToDate(from, to);

        //Sort registered courses by state       
        ArrayList<CourseRegister> courseSuccessRegisters = new ArrayList<>();
        ArrayList<CourseRegister> courseCancelledRegisters = new ArrayList<>();
        ArrayList<CourseRegister> courseSubmittedRegisters = new ArrayList<>();
        for (CourseRegister c : courseRegisters) {
            if (c.getState().getId() == 1) {
                courseSuccessRegisters.add(c);
            } else if (c.getState().getId() == 2) {
                courseCancelledRegisters.add(c);
            } else {
                courseSubmittedRegisters.add(c);
            }
        }
        int categoriesId = 0;
        String categoryId_Raw = request.getParameter("categoryId");
        if (categoryId_Raw != null) {
            categoriesId = Integer.parseInt(categoryId_Raw);
        }
        int revenues = db.getRevenuesFromDateToDate(categoriesId, from, to);
        int allRevenue = db.getAllRevenues(categoriesId);
        String isSuccess = request.getParameter("state");
        ArrayList<TrendOrder> trendOrder = db.getTrendSuccessOrderFromDateToDate(from, to);
        ArrayList<TrendOrder> allTrendOrder = db.getTrendAllOrderFromDateToDate(from, to);
        ArrayList<RevenuesByCategory> revenuesByCategory = new ArrayList<>();
        ArrayList<Category> allCategory = db.getAllCategorySubject();
        for (Category category : allCategory) {
            RevenuesByCategory r = new RevenuesByCategory();
            r.setCategory(category);
            r.setRevenues(db.getRevenuesFromDateToDate(category.getId(), from, to));
            revenuesByCategory.add(r);
        }
        ArrayList<RevenuesByTime> revenuesByMonth = db.getAllRevenuesByMonth(categoriesId);
        ArrayList<RevenuesByTime> revenuesByYear = db.getAllRevenuesByYear(categoriesId);

        request.setAttribute("from", from);
        request.setAttribute("to", to);
        request.setAttribute("users", users);
        request.setAttribute("allUser", allUsers);
        request.setAttribute("courses", courses);
        request.setAttribute("allCourses", allCourses);
        request.setAttribute("userBought", userBought);
        request.setAttribute("courseRegisters", courseRegisters);
        request.setAttribute("courseSuccessRegisters", courseSuccessRegisters);
        request.setAttribute("courseCancelledRegisters", courseCancelledRegisters);
        request.setAttribute("courseSubmittedRegisters", courseSubmittedRegisters);
        request.setAttribute("revenues", revenues);
        request.setAttribute("allRevenue", allRevenue);
        request.setAttribute("trendOrder", trendOrder);
        request.setAttribute("allTrendOrder", allTrendOrder);
        request.setAttribute("revenuesByCategory", revenuesByCategory);
        request.setAttribute("revenuesByMonth", revenuesByMonth);
        request.setAttribute("revenuesByYear", revenuesByYear);

//        User user = new User();
//        user.setUserName("dongngochieu");
//        user.setPassword("1");
//        Role r = new Role();
//        r.setId(3);
//        user.setRole(r);
//        request.getSession().setAttribute("account", user);
        request.getRequestDispatcher("view/dashboard.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
