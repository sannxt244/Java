/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.homePage;

import dal.minhlee.HomeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Blog;
import model.Course;
import model.Slider;

/**
 *
 * @author Minh Lee
 */
public class homeServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet hoemServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet hoemServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    public List<Course> Search(String name, List<Course> ls) {
        List<Course> temp = new ArrayList<>();
        
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i).getName().toLowerCase().contains(name.toLowerCase())){
                temp.add(ls.get(i));
            }
        }
        if (temp.isEmpty() == true)
            return null;
        else
            return temp;
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String search = request.getParameter("search");
        
       
        
        HomeDAO hd = new HomeDAO();
        
        // Slider
        List<Slider> l = hd.getListSlider();
        request.setAttribute("sliderList", l);
        // Course
        List<Course> ls = hd.getListCourse();
        request.setAttribute("courseList", ls);
        //Blog
        List<Blog> list = hd.getListBlog();
        request.setAttribute("blogList", list);
        // Search
        if (search != null){
            request.setAttribute("searchCourses", Search(search, ls));
        }
            
        
        
        request.getRequestDispatcher("home.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
    
//    public static void main(String[] args) {
//        HomeDAO hd = new HomeDAO();
//        
//        List<Course> ls = hd.getListCourse();
//        
//        for (int i = 0; i < ls.size(); i++) {
//            System.out.println(ls.get(i).getName());
//        }
//        
//        homeServlet h = new homeServlet();
//        List<Course> temp = h.Search("Java", ls);
//        for (int i = 0; i < temp.size(); i++) {
//            System.out.println(temp.get(i).getName());
//        }
//    }
}
